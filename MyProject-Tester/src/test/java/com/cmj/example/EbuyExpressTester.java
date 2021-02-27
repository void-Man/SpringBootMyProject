package com.cmj.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.vo.EbuyDeliveryHasProductVo;
import com.cmj.example.vo.EbuyDeliveryVo;
import com.cmj.example.vo.EbuyOrderExpressVo;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/27
 */
public class EbuyExpressTester {

    @Test
    public void testValidateProductQty() {
        String dbExpressVoListStr = "[{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"220022832737168\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"232483828627776\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"促销商品测试0629\",\"productQty\":4,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"小风扇-1元换购\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"彩云间\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"锦鲤抄\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"234701184325952\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"orderNo\":\"292286550176016\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverName\":\"攻击机\",\"deliverPhone\":\"19088224444\",\"orderNo\":\"297956971832608\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1}]";
        String ebuyDeliveryVoListStr = "[{\"deliveryId\":\"b6bb0357dc9e4b6abdcbf1fc012becd4\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"32b46f013b1c404ab51e13cc5773d82c\",\"productName\":\"第一个\",\"productQty\":1}],\"expressNumber\":\"12345678910\",\"orderId\":\"0e73655de31342b29c89cc61b3bef28c\",\"orderNo\":\"203470429779248\"},{\"deliveryId\":\"3a25f93d2e9949f6a2fcba3feef68fb4\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"38017e12bd53479497d8a5f4ca2bbf32\",\"productName\":\"黄桃1元\",\"productQty\":1}],\"expressNumber\":\"12345678915\",\"orderId\":\"216bf88ba69e42f0b22d593f046f4b36\",\"orderNo\":\"292990009112880\"},{\"deliveryId\":\"b0b9ef713b5247df8090723834e09d32\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"51c8ed6c991d40f1a10fc629ac783641\",\"productName\":\"黄桃1元\",\"productQty\":1}],\"expressNumber\":\"12345678913\",\"orderId\":\"189f36533c214ac3ab020743dac2ee4c\",\"orderNo\":\"298930965076272\"},{\"deliveryId\":\"ad3076a26e7f48649c9ceb43420c4b22\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"abf70b41508a40569d631475b465a3ac\",\"productName\":\"苦瓜\",\"productQty\":3}],\"expressNumber\":\"12345678914\",\"orderId\":\"1da83e9d951b4aadb7f48b68f3bc35a3\",\"orderNo\":\"292751951500576\"},{\"deliveryId\":\"5922fd5cacff4224b36e2860f9e47476\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"b488a134fa5b4b08be054edea42e8074\",\"productName\":\"无花果5元\",\"productQty\":1}],\"expressNumber\":\"12345678912\",\"orderId\":\"119d865f557d4d51a5e9075e6618ea45\",\"orderNo\":\"290161742743856\"},{\"deliveryId\":\"c2822ece2a744545b752305f393ed9e7\",\"ebuyDeliveryHasProductVoList\":[{\"deliveryProductId\":\"c5a41a4da14e4447ba08082016d6ebaf\",\"productName\":\"杏子2元\",\"productQty\":1}],\"expressNumber\":\"12345678911\",\"orderId\":\"1184bacd4e4d4cf2b915abab09f5a53e\",\"orderNo\":\"292989020141872\"}]";
        String unImpotListStr = "[{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"攻击机\",\"deliverPhone\":\"19088224444\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678916\",\"orderNo\":\"297956971832608\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678917\",\"orderNo\":\"292286550176016\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678918\",\"orderNo\":\"232483828627776\",\"productName\":\"促销测试\",\"productQty\":1,\"success\":1},{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678919\",\"orderNo\":\"220022832737168\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"success\":1},{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678920\",\"orderNo\":\"234701184325952\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"success\":1},{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678921\",\"orderNo\":\"234701184325952\",\"productName\":\"促销商品测试0629\",\"productQty\":5,\"success\":1}]";
        String totalOrderNoPrdtNameMapStr = "{\"234701184325952草莓毛衣\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678920\",\"orderNo\":\"234701184325952\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"remark\":\"成功\",\"success\":1},\"234701184325952促销商品测试0629\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678921\",\"orderNo\":\"234701184325952\",\"productName\":\"促销商品测试0629\",\"productQty\":5,\"remark\":\"成功\",\"success\":1},\"220022832737168草莓毛衣\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678919\",\"orderNo\":\"220022832737168\",\"productName\":\"草莓毛衣\",\"productQty\":1,\"remark\":\"成功\",\"success\":1},\"297956971832608促销测试\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"攻击机\",\"deliverPhone\":\"19088224444\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678916\",\"orderNo\":\"297956971832608\",\"productName\":\"促销测试\",\"productQty\":1,\"remark\":\"成功\",\"success\":1},\"232483828627776促销测试\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678918\",\"orderNo\":\"232483828627776\",\"productName\":\"促销测试\",\"productQty\":1,\"remark\":\"成功\",\"success\":1},\"292286550176016促销测试\":{\"deliverDetail\":\"广东省深圳市南山区金蝶软件园\",\"deliverName\":\"junjun买买买\",\"deliverPhone\":\"18359936743\",\"expressCompany\":\"顺丰速递\",\"expressNumber\":\"12345678917\",\"orderNo\":\"292286550176016\",\"productName\":\"促销测试\",\"productQty\":1,\"remark\":\"成功\",\"success\":1}}";
        List<EbuyOrderExpressVo> dbExpressVoList = JSONArray.parseArray(dbExpressVoListStr, EbuyOrderExpressVo.class);
        List<EbuyDeliveryVo> ebuyDeliveryVos = JSONArray.parseArray(ebuyDeliveryVoListStr, EbuyDeliveryVo.class);
        List<EbuyOrderExpressVo> unImpotList = JSONArray.parseArray(unImpotListStr, EbuyOrderExpressVo.class);
        HashMap<String, EbuyOrderExpressVo> totalOrderNoPrdtNameMap = JSON.parseObject(totalOrderNoPrdtNameMapStr, new TypeReference<HashMap<String, EbuyOrderExpressVo>>() {});
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
}
