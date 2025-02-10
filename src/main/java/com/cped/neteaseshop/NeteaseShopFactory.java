package com.cped.neteaseshop;

import java.util.HashMap;
import java.util.Map;

public class NeteaseShopFactory {
    private Map<String, ItemAchieve> types = new HashMap<>();

    public void registerType(ItemAchieve type) {
        types.put(type.getType(), type);
    }

    public ItemAchieve getType(String type) {
        return types.get(type);
    }
}
