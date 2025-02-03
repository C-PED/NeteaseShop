package com.cped.neteaseshop;

import com.cped.neteaseshop.object.ItemResponse;
import org.bukkit.entity.Player;

public interface ShopAPI {
    void tryShipItemFailed(Player player);
    void tryShipItemCancelled(Player player);
    void finPlayerOrderFailed();
    void finPlayerOrderCancelled(Player player);
    void completed(ItemResponse response,Player player);
    void shipmentCompleted(ItemResponse response, Player player);
    void jsonError(ItemResponse response, Player player);
}
