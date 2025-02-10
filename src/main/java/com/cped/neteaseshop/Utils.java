package com.cped.neteaseshop;

import com.cped.neteaseshop.object.ItemResponse;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static ItemResponse parseResponse(Map<String, Object> result) {
        if (result != null) {
            Object jsonResultObj = result.get("json_result");
            if (jsonResultObj instanceof JSONObject) {
                JSONObject jsonResult = (JSONObject) jsonResultObj;
                try {
                    int code = (int) jsonResult.getOrDefault("code", 0);
                    String message = (String) jsonResult.get("message");
                    String details = (String) jsonResult.get("details");
                    ItemResponse response = new ItemResponse();
                    response.setCode(code);
                    response.setMessage(message);
                    response.setDetails(details);
                    JSONArray entitiesData = (JSONArray) jsonResult.get("entities");
                    if (!(entitiesData == null || entitiesData.isEmpty())) {
                        List<ItemResponse.Entity> entities = new ArrayList<>();
                        for (Object entityDataObj : entitiesData) {
                            JSONObject entityData = (JSONObject) entityDataObj;
                            ItemResponse.Entity entity = new ItemResponse.Entity();
                            entity.setItem_id(Long.valueOf(entityData.get("item_id").toString()));
                            entity.setUuid((String) entityData.get("uuid"));
                            entity.setItem_num(((Number) entityData.get("item_num")).intValue());
                            entity.setOrderid(Long.valueOf(entityData.get("orderid").toString()));
                            entity.setCmd((String) entityData.get("cmd"));
                            entity.setBuy_time(Long.parseLong((String) entityData.get("buy_time")));
                            entity.setGroup(Long.valueOf(entityData.get("group").toString()));
                            entities.add(entity);
                        }
                        response.setEntities(entities);
                        return response;
                    } else {
                        response.setEntities(new ArrayList<>());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
