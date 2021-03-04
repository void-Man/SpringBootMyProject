package com.cmj.example;

import com.alibaba.fastjson.JSONArray;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.vo.DiscountInMerchantVo;
import com.cmj.example.vo.EbuyProductForBuy;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description date 2020/12/3
 */
public class JavaStreamTester {

    /**
     * reduce 三个参数方法
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/12/4
     */
    @Test
    public void reduce4ThreeParam() {
        List<EbuyProductForBuy> ebuyProductForBuyList = new ArrayList<>(10);
        ebuyProductForBuyList.add(EbuyProductForBuy.EbuyProductForBuyBuilder.ebuyProductForBuy().price(new BigDecimal("10")).buyQty(1).build());
        ebuyProductForBuyList.add(EbuyProductForBuy.EbuyProductForBuyBuilder.ebuyProductForBuy().price(new BigDecimal("20")).buyQty(2).build());
        System.out.println(ebuyProductForBuyList.stream().reduce(BigDecimal.ZERO, (init, vo) -> init.add(vo.getPrice().multiply(new BigDecimal(vo.getBuyQty()))), BigDecimal::add));
        ;
    }

    /**
     * filter 之后修改vo属性的值，原值是否也会改变
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/12/4
     */
    @Test
    public void afterFilterEditVo() {
        String json = "[{\"discountId\":\"5fc915471463478a9099f2905798d9af\",\"discountQuantity\":0.01,\"ebuyProductForBuyList\":[{\"activityBuyCount\":4,\"activityBuyQty\":3,\"activityLeftcount\":195,\"buyQty\":0,\"ebuyBuyCartId\":\"2f4a686013a74f868988100dfdfeb1b1\",\"ebuyProductParametersVoList\":[{\"id\":\"afc53304ea304f57a6c7895ff88c0bec\",\"propname\":\"净含量\",\"propvalue\":\"100\"}],\"ebuyProductShelfId\":\"4cb0f6d5a8114d33958ace0a350455c5\",\"isHasAcitvityProduct\":1,\"isHasLeftCount\":1,\"isSellOut\":0,\"isSetMealProduct\":0,\"maxCanBuy\":-1,\"picBaseMini\":\"http://download.wojiayun.cn/file/tempfile/none/7273/5dfa0fc2cdca4b6a8256d8bd175d7d4b.png\",\"price\":3,\"productId\":\"378cd2d7d6ed43b388bdc8b899557081\",\"productName\":\"橘子\",\"promotionId\":\"00136ef1067540fea714faf9b3b1bbe2\",\"promotionPrice\":2,\"shelfState\":0}],\"limitQuantity\":0.02,\"limitType\":1,\"type\":1},{\"discountId\":\"3bb6d6d5551e44f588a99157693e0c92\",\"discountQuantity\":5,\"ebuyProductForBuyList\":[{\"activityBuyCount\":0,\"activityBuyQty\":0,\"activityLeftcount\":0,\"buyQty\":2,\"ebuyBuyCartId\":\"5df4105e6937496299c742fc8bfe5aea\",\"ebuyProductParametersVoList\":[],\"ebuyProductShelfId\":\"c1759a7e3eed45fb96a54b074711ef6c\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSellOut\":0,\"isSetMealProduct\":0,\"picBaseMini\":\"http://wojiayun-1252177460.costj.myqcloud.com/d5c2fc00acd24f2a8ef65291e15efcbf/2020-04-30/jpg/40f1e2233fef49f2ae746a17303e7528.jpg?imageView2/3/w/190/h/190/q/85\",\"price\":1990,\"productId\":\"ac0106a78216431b9ac719dc98a76ac4\",\"productName\":\"待审核的商品\",\"shelfState\":0},{\"activityBuyCount\":0,\"activityBuyQty\":0,\"activityLeftcount\":0,\"buyQty\":2,\"ebuyBuyCartId\":\"4c2c74e8d84b4c928c07e2735563ce06\",\"ebuyProductParametersVoList\":[],\"ebuyProductShelfId\":\"c3441e475cdb469cad974717a20cacf1\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSellOut\":0,\"isSetMealProduct\":0,\"picBaseMini\":\"http://wojiayun-1252177460.file.myqcloud.com/d5c2fc00acd24f2a8ef65291e15efcbf/2020-06-10/jpg/ee7ec81e70014277b31fc625399f1153.jpg?imageView2/3/w/50/h/50/q/85\",\"price\":999,\"productId\":\"0acace2c92ce43d0bf0a345e30bc3f16\",\"productName\":\"满减测试0610002\",\"shelfState\":0}],\"limitQuantity\":5,\"limitType\":1,\"type\":2}]";
        List<DiscountInMerchantVo> discountInMerchantVoList = JSONArray.parseArray(json, DiscountInMerchantVo.class);

        DiscountInMerchantVo nonDisVo = discountInMerchantVoList.stream()
                .filter(discountInMerchantVo -> StringUtils.isEmpty(discountInMerchantVo.getDiscountId()))
                .findAny()
                .orElse(DiscountInMerchantVo.DiscountInMerchantVoBuilder.discountInMerchantVo().discountId("").ebuyProductForBuyList(new ArrayList<>(10)).build());

        for (DiscountInMerchantVo discountInMerchantVo : discountInMerchantVoList) {
            if (StringUtils.isEmpty(discountInMerchantVo.getDiscountId())) {
                continue;
            }
            List<EbuyProductForBuy> product = discountInMerchantVo.getEbuyProductForBuyList().stream()
                    .filter(ebuyProductForBuy -> !StringUtils.isEmpty(ebuyProductForBuy.getPromotionId()))
                    .collect(Collectors.toList());
            if (product.size() > 0) {
                discountInMerchantVo.getEbuyProductForBuyList().removeAll(product);
                nonDisVo.getEbuyProductForBuyList().addAll(product);
            }
        }
        List<DiscountInMerchantVo> removedList = discountInMerchantVoList.stream()
                .filter(discountInMerchantVo -> !StringUtils.isEmpty(discountInMerchantVo.getDiscountId()) && CollectionUtils.isNullOrEmpty(discountInMerchantVo.getEbuyProductForBuyList()))
                .collect(Collectors.toList());
        discountInMerchantVoList.removeAll(removedList);
    }

}
