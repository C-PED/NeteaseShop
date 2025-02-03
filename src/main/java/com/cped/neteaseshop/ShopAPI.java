package com.cped.neteaseshop;

import com.cped.neteaseshop.object.ItemResponse;
import org.bukkit.entity.Player;

public interface ShopAPI {
    void tryShipItemFailed();
    void tryShipItemCancelled();
    void finPlayerOrderFailed();
    void finPlayerOrderCancelled();
    void completed(ItemResponse response,Player player);
    void shipmentCompleted(ItemResponse response, Player player);
    void jsonError(ItemResponse response, Player player);
}
