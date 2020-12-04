package com.cmj.example;

import com.alibaba.fastjson.JSONArray;
import com.cmj.example.utils.common.CommonUtils;
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
        String json = "[{\"discountId\":\"\",\"limitType\":null,\"limitQuantity\":null,\"type\":null,\"discountQuantity\":null,\"ebuyProductForBuyList\":[{\"productId\":\"0fc6f4ea663e474dbca348ab79c68238\",\"ebuyProductShelfId\":\"5b9c752dec2c4ccaaca44370bd710f0b\",\"ebuyBuyCartId\":\"9ce2a8105145439fbdccd4f23867bb45\",\"promotionId\":null,\"productName\":\"芒果干#全新包装\",\"productMiniName\":null,\"price\":0.01,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://api.kingdee.com/kdrive/user/file/thumbnail?file_id=45348692&client_id=200376&scode=TEZtTmYzS3oxMzVnMFVDRkhSZnpp&width=100&height=100&sign=3de7827c3eaaab5417dda65c375a2bb4932e28ad\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":0,\"ebuyProductParametersVoList\":[{\"id\":\"bef180224e8b47dfb7bf5da23602a056\",\"propname\":\"产地\",\"propvalue\":\"广东\"},{\"id\":\"823d0d572d564cbdaeb7122bfe3e8bd2\",\"propname\":\"味道\",\"propvalue\":\"甜\"},{\"id\":\"76f4ed8825fc4093a525d4d2ecaf6d5c\",\"propname\":\"规格\",\"propvalue\":\"100g\"},{\"id\":\"7097793865bc473bb32f90e55fa8eb01\",\"propname\":\"适宜人群\",\"propvalue\":\"老少皆宜\"},{\"id\":\"5d6a18f3272843e0aefad88ed0f0a0c3\",\"propname\":\"种类\",\"propvalue\":\"芒果干\"}]}]},{\"discountId\":\"64f6ddad4e584a8db835039d150fd606\",\"limitType\":1,\"limitQuantity\":5,\"type\":1,\"discountQuantity\":4.9,\"ebuyProductForBuyList\":[{\"productId\":\"c3eb7843440e4607821db917e901cae1\",\"ebuyProductShelfId\":\"c931c59190e24f76997856aeb9990850\",\"ebuyBuyCartId\":\"a4ba3bda453d44019d10108e5e907525\",\"promotionId\":\"0504854808448484\",\"productName\":\"促销0305002\",\"productMiniName\":null,\"price\":2,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://wojiacpre-1252177460.cosbj.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-03-05/jpg/7114aeb8e6324b669e630cabe334f6a0.jpg?imageView2/3/w/50/h/50/q/85\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":1,\"ebuyProductParametersVoList\":[]},{\"productId\":\"1d0b22eaaf8e44128e9a3153af40d7b3\",\"ebuyProductShelfId\":\"f52e89b6109548cab07715a3b20b8e54\",\"ebuyBuyCartId\":\"477d3d3956484d77950c7e0f82afadf4\",\"promotionId\":\"1818480950990044598\",\"productName\":\"倩女幽魂\",\"productMiniName\":null,\"price\":1.01,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"https://wojiacpre-1252177460.file.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-09-17/jpg/730e72043cb44825b633baa03f3ea40d.jpg?imageView2/3/w/190/h/190/q/95\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":1,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":1,\"ebuyProductParametersVoList\":[]},{\"productId\":\"320ba741e7a6402eb0756a5086137ee8\",\"ebuyProductShelfId\":\"4ff34c28171f4e1d9b6c33382926a1ba\",\"ebuyBuyCartId\":\"5d0415bb23f24597aa0aaa553870b8ea\",\"promotionId\":null,\"productName\":\"测试031201\",\"productMiniName\":null,\"price\":1,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://wojiacpre-1252177460.cosbj.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-03-12/jpg/56857e8420a94d26a1678a26ab8a90ff.jpg?imageView2/3/w/50/h/50/q/85\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":1,\"ebuyProductParametersVoList\":[]},{\"productId\":\"afdaa9023f7a4333b4a7b51d4102b905\",\"ebuyProductShelfId\":\"a60580e520274c1aa9db44aa70cd07c5\",\"ebuyBuyCartId\":\"f1c8742ef044491b8bcbb32345f0774c\",\"promotionId\":null,\"productName\":\"雪梨\",\"productMiniName\":null,\"price\":0.01,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://wojiacpre-1252177460.file.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-05-14/jpg/2425ca783e984344a0e6a277784e457e.jpg?imageView2/3/w/50/h/50/q/85\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":1,\"ebuyProductParametersVoList\":[]},{\"productId\":\"d3acdf180e6b4a70aeebb6b7951eb644\",\"ebuyProductShelfId\":\"684e5a9b760542fa943d6819b90208fc\",\"ebuyBuyCartId\":\"c27b560daf9b4ba3a6cacea1c5b38a04\",\"promotionId\":null,\"productName\":\"促销促销促销\",\"productMiniName\":null,\"price\":0.02,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://wojiacpre-1252177460.cosbj.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-03-05/jpg/e89f1d8580fb4defacf27ded80fa89e8.jpg?imageView2/3/w/50/h/50/q/85\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":1,\"ebuyProductParametersVoList\":[]}]},{\"discountId\":\"049a0043e83246aba27157eba7089bd1\",\"limitType\":1,\"limitQuantity\":1,\"type\":1,\"discountQuantity\":0.9,\"ebuyProductForBuyList\":[{\"productId\":\"23307ca83e8849d4b51ba1c4ff511bb0\",\"ebuyProductShelfId\":\"5295ffd9094c44cc83dd7b960a371b7c\",\"ebuyBuyCartId\":\"602bff07917544f39f131dc12727b172\",\"promotionId\":\"sdf19e7g87rg18r7g87r\",\"productName\":\"好看的眼影\",\"productMiniName\":null,\"price\":0.05,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://api.kingdee.com/kdrive/user/file/thumbnail?client_id=200376&file_id=80235079&height=100&scode=NDdQc08vS2NQUW9KT2tmclNZL3NK&sign=c4d9b4049e3968be9b994ef180de9e393201901d&width=100\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":2,\"ebuyProductParametersVoList\":[]}]},{\"discountId\":\"07d6b87ba11a4d659b01f7b9a4a30bef\",\"limitType\":1,\"limitQuantity\":6,\"type\":1,\"discountQuantity\":3,\"ebuyProductForBuyList\":[{\"productId\":\"2f18db46908f4e4a8629b0edefae3617\",\"ebuyProductShelfId\":\"815f86fec1f14919ba24eeca23a5388a\",\"ebuyBuyCartId\":\"de103f930c6044a28b2417ec75f5ea95\",\"promotionId\":null,\"productName\":\"小紫花售罄\",\"productMiniName\":null,\"price\":8.85,\"discountedPrice\":null,\"promotionPrice\":null,\"buyQty\":1,\"activityBuyQty\":0,\"maxCanBuy\":null,\"picBaseMini\":\"http://wojiacpre-1252177460.file.myqcloud.com/5c4969cb51394395b25e78d1dac2f3e0/2020-05-21/jpg/94f7bc61ecc544dcbd6bb68898280ff7.jpg?imageView2/3/w/50/h/50/q/85\",\"isHasAcitvityProduct\":0,\"isHasLeftCount\":1,\"isSetMealProduct\":0,\"isSellOut\":0,\"shelfState\":0,\"activityLeftcount\":0,\"activityBuyCount\":0,\"discountQuantity\":2,\"ebuyProductParametersVoList\":[]}]}]";
        List<DiscountInMerchantVo> discountInMerchantVoList = JSONArray.parseArray(json, DiscountInMerchantVo.class);

        DiscountInMerchantVo nonDisVo = discountInMerchantVoList.stream()
                .filter(discountInMerchantVo -> StringUtils.isEmpty(discountInMerchantVo.getDiscountId()))
                .findAny()
                .orElse(DiscountInMerchantVo.DiscountInMerchantVoBuilder.discountInMerchantVo().discountId("").build());

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
                .filter(discountInMerchantVo -> !StringUtils.isEmpty(discountInMerchantVo.getDiscountId()) && CommonUtils.isNullOrEmpty(discountInMerchantVo.getEbuyProductForBuyList()))
                .collect(Collectors.toList());
        discountInMerchantVoList.removeAll(removedList);
    }

}
