package com.cped.neteaseshop;

import java.util.HashMap;
import java.util.Map;

public class NeteaseShopFacaty {
    private Map<String, ItemAchieve> types;

    public NeteaseShopFacaty() {
        types = new HashMap<>();
    }

    public void registerType(ItemAchieve type) {
        types.put(type.getType(), type);
    }

    public ItemAchieve getType(String type) {
        return types.get(type);
    }
}
