package com.victor.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.victor.model.Card.CardColor;

public class ColorComparator implements Comparator<CardColor> {

    // private Map<String, Integer> sortOrder;

    // public ColorComparator(Map<String, Integer> sortOrder) {
    // this.sortOrder = sortOrder;
    // }

    // @Override
    // public int compare(CardColor cardColor1, CardColor cardColor2) {
    // Integer colorPos1 = sortOrder.get(cardColor1.name());
    // if (colorPos1 == null) {
    // throw new IllegalArgumentException("Bad color encountered: " +
    // cardColor1.name());
    // }

    // Integer colorPos2 = sortOrder.get(cardColor2.name());
    // if (colorPos2 == null) {
    // throw new IllegalArgumentException("Bad color encountered: " +
    // cardColor2.name());
    // }

    // return colorPos1.compareTo(colorPos2);
    // }

    public static void sortList(List<?> objectsToOrder, List<?> orderedObjects) {

        HashMap<Object, Integer> indexMap = new HashMap<>();
        int index = 0;
        for (Object object : orderedObjects) {
            indexMap.put(object, index);
            index++;
        }

        Collections.sort(objectsToOrder, new Comparator<Object>() {

            public int compare(Object left, Object right) {

                Integer leftIndex = indexMap.get(left);
                Integer rightIndex = indexMap.get(right);
                if (leftIndex == null) {
                    return -1;
                }
                if (rightIndex == null) {
                    return 1;
                }

                return Integer.compare(leftIndex, rightIndex);
            }
        });
    }

    @Override
    public int compare(CardColor arg0, CardColor arg1) {
        // TODO Auto-generated method stub
        return 0;
    }
}
