package com.cmj.example.utils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/15
 */
public class CollectionUtils {

    /**
     * 判断集合是否是空
     *
     * @param collection
     * @return boolean
     * @author mengjie_chen
     * @date 2020/12/15
     */
    public static <E> boolean isNullOrEmpty(Collection<E> collection) {
        return Objects.isNull(collection) || collection.size() == 0;
    }

    /**
     * 判断集合是否不是空
     *
     * @param collection
     * @return boolean
     * @author mengjie_chen
     * @date 2020/12/15
     */
    public static <E> boolean isNotNullAndEmpty(Collection<E> collection) {
        return Objects.nonNull(collection) || collection.size() > 0;
    }

}
