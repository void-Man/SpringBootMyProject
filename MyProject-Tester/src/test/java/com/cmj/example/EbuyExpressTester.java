package com.cmj.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.vo.*;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyExpressTester {

    @Test
    public void testValidateProductQty() {
        String dbExpressVoListStr = "";
        String ebuyDeliveryVoListStr = "";
        String unImpotListStr = "";
        String totalOrderNoPrdtNameMapStr = "";
        List<EbuyOrderExpressVo> dbExpressVoList = JSONArray.parseArray(dbExpressVoListStr, EbuyOrderExpressVo.class);
        List<EbuyDeliveryVo> ebuyDeliveryVos = JSONArray.parseArray(ebuyDeliveryVoListStr, EbuyDeliveryVo.class);
        List<EbuyOrderExpressVo> unImpotList = JSONArray.parseArray(unImpotListStr, EbuyOrderExpressVo.class);
        HashMap<String, EbuyOrderExpressVo> totalOrderNoPrdtNameMap = JSON.parseObject(totalOrderNoPrdtNameMapStr, new TypeReference<HashMap<String, EbuyOrderExpressVo>>() {
        });
        this.validateProductQty(dbExpressVoList, ebuyDeliveryVos, unImpotList, totalOrderNoPrdtNameMap);
        System.out.println();
    }

    /**
     * 校验商品数量
     *
     * @param dbExpressVoList
     * @param ebuyDeliveryVoList
     * @param unImpotList
     * @param totalOrderNoPrdtNameMap
     * @return void
     * @author mengjie_chen
     * @date 2021/2/23
     */
    private void validateProductQty(List<EbuyOrderExpressVo> dbExpressVoList, List<EbuyDeliveryVo> ebuyDeliveryVoList, List<EbuyOrderExpressVo> unImpotList, Map<String, EbuyOrderExpressVo> totalOrderNoPrdtNameMap) {
        if (CollectionUtils.isNullOrEmpty(ebuyDeliveryVoList)) {
            return;
        }
        // 数据库已存在的快递信息，格式：订单号&商品名称&商品数量
        Set<String> deliveryedOrderNoPrdtNameQtySet = new HashSet<>(8);
        for (EbuyDeliveryVo ebuyDeliveryVo : ebuyDeliveryVoList) {
            for (EbuyDeliveryHasProductVo ebuyDeliveryHasProductVo : ebuyDeliveryVo.getEbuyDeliveryHasProductVoList()) {
                deliveryedOrderNoPrdtNameQtySet.add(ebuyDeliveryVo.getOrderNo() + "&" + ebuyDeliveryHasProductVo.getProductName() + "&" + ebuyDeliveryHasProductVo.getProductQty());
            }
        }
        // 导入的快递信息，key：订单号&商品名称  value:vo
        Map<String, EbuyOrderExpressVo> unImportVoMap = new HashMap<>(8);
        // 导入的快递信息，key：订单号&商品名称  value:商品数量
        Map<String, Integer> unImportPrdtQtyMap = new HashMap<>(8);
        for (
                EbuyOrderExpressVo ebuyOrderExpressVo : unImpotList) {
            EbuyOrderExpressVo expressVo = unImportVoMap.get(ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName());
            if (Objects.isNull(expressVo)) {
                unImportVoMap.put(ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName(), ebuyOrderExpressVo);
            }

            Integer prdtQty = unImportPrdtQtyMap.get(ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName());
            if (Objects.isNull(prdtQty)) {
                unImportPrdtQtyMap.put(ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName(), ebuyOrderExpressVo.getProductQty());
                continue;
            }
            unImportPrdtQtyMap.put(ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName(), ebuyOrderExpressVo.getProductQty() + prdtQty);
        }

        // 订单商品表商品信息，格式：订单号&商品名称&商品数量
        Set<String> orderHasProductOrderNoPrdtNameQtySet = dbExpressVoList.stream()
                .map(ebuyOrderExpressVo -> ebuyOrderExpressVo.getOrderNo() + "&" + ebuyOrderExpressVo.getProductName() + "&" + ebuyOrderExpressVo.getProductQty())
                .collect(Collectors.toSet());

        // 先比较导入的数据中，商品数量是否大于下单时的商品总数。再比较导入的商品数量是否大于已发货（已有快递信息）的商品数量
        unImportVoMap.forEach((unImportData, expressVo) ->

        {
            String[] umImportDatas = unImportData.split("&");
            String unImportOrderNo = umImportDatas[0], unImportPrdtName = umImportDatas[1];
            for (String orderHasProductData : orderHasProductOrderNoPrdtNameQtySet) {
                String orderHasProductQty = orderHasProductData.split("&")[2];
                if (!orderHasProductData.replaceAll("&", "").startsWith(unImportOrderNo + unImportPrdtName)) {
                    continue;
                }
                Integer unImportQty = unImportPrdtQtyMap.get(unImportData);
                if (unImportQty.compareTo(Integer.valueOf(orderHasProductQty)) > 0) {
                    expressVo.setSuccess(0);
                    expressVo.setRemark("订单总商品超出未发货数量");
                    continue;
                }
                for (String deliveryedData : deliveryedOrderNoPrdtNameQtySet) {
                    if (deliveryedData.startsWith(unImportData)) {
                        String deliveryedQty = deliveryedData.split("&")[2];
                        if (unImportQty > Integer.parseInt(orderHasProductQty) - Integer.parseInt(deliveryedQty)) {
                            expressVo.setSuccess(0);
                            expressVo.setRemark("商品超出未发货数量");
                            break;
                        }
                    }
                }
            }
        });
    }

    public void test(){
        String orderNoListStr = "";
        String ebuyOrderNoVoListStr = "";
        List<String> orderNoList = JSONArray.parseArray(orderNoListStr, String.class);
        List<EbuyOrderHasProductFlagMapVo> ebuyOrderNoVoList = JSONArray.parseArray(ebuyOrderNoVoListStr, EbuyOrderHasProductFlagMapVo.class);
        this.updateOrderStatus(orderNoList, ebuyOrderNoVoList);
        System.out.println();
    }

    protected void updateOrderStatus(List<String> orderNoList, List<EbuyOrderHasProductFlagMapVo> ebuyOrderNoVoList) {
        HashMap<String, Optional<EbuyOrderHasProductFlagMapVo>> orderProductMap = ebuyOrderNoVoList.stream()
                .collect(groupingBy(EbuyOrderHasProductFlagMapVo::getOrderNo, HashMap::new, reducing((v1, v2) -> {
                    v1.setProductQty(v1.getProductQty() + v2.getProductQty());
                    return v1;
                })));
        List<EbuyDeliveryQtyVo> ebuyDeliveryQtyVoList = null;

        HashMap<String, Optional<EbuyDeliveryQtyVo>> deliveryOrderMap = ebuyDeliveryQtyVoList.stream()
                .collect(groupingBy(EbuyDeliveryQtyVo::getOrderNo, HashMap::new, reducing((v1, v2) -> {
                    v1.setProductQty(v1.getProductQty() + v2.getProductQty());
                    return v1;
                })));
        deliveryOrderMap.forEach((orderNo, productQty) -> {
            EbuyOrderHasProductFlagMapVo ebuyOrderHasProductFlagMapVo = orderProductMap.get(orderNo).get();
            if (productQty.get().getProductQty().equals(ebuyOrderHasProductFlagMapVo.getProductQty())) {
                System.out.println();
            }
        });

    }
}
