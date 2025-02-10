package com.cped.neteaseshop.object;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ItemResponse {
    private int code;
    private String message;
    private String details;
    private List<Entity> entities;

    @Getter
    @Setter
    public static class Entity {
        private long item_id;
        private String uuid;
        private int item_num;
        private Long orderid;
        private String cmd;
        private long buy_time;
        private long group;
    }
}
