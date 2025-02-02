package com.cped.neteaseshop;

import java.util.Map;

public class NeteaseShopFacaty {
    private Map<String, ItemType> types;

    public void registerType(ItemType type) {
        types.put(type.getType(), type);
    }

    public ItemType getType(String type) {
        return types.get(type);
    }
}
