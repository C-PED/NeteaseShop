package com.cped.neteaseshop;

import com.cped.neteaseshop.object.ItemResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static ItemResponse parseResponse(Map<String, Object> result) {
        if (result != null) {
            int code = (int) result.get("code");
            String message = (String) result.get("message");
            String details = (String) result.get("details");

            ItemResponse response = new ItemResponse();
            response.setCode(code);
            response.setMessage(message);
            response.setDetails(details);

            List<ItemResponse.Entity> entities = new ArrayList<>();
            List<Map<String, Object>> entitiesData = (List<Map<String, Object>>) result.get("entities");

            for (Map<String, Object> entityData : entitiesData) {
                ItemResponse.Entity entity = new ItemResponse.Entity();
                entity.setItem_id((Long) entityData.get("item_id"));
                entity.setUuid((String) entityData.get("uuid"));
                entity.setItem_num((int) entityData.get("item_num"));
                entity.setOrderid((int) entityData.get("orderid"));
                entity.setCmd((String) entityData.get("cmd"));
                entity.setBuy_time((Long) entityData.get("buy_time"));
                entity.setGroup((int) entityData.get("group"));

                entities.add(entity);
            }

            response.setEntities(entities);
            return response;
        }
        return null;
    }

}
