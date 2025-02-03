package com.cped.neteaseshop;

import com.cped.neteaseshop.object.ItemResponse;
import com.neteasemc.spigotmaster.SpigotMaster;
import com.neteasemc.spigotmaster.enumData.SpigotMasterEvent;
import lombok.Getter;
import org.apache.http.concurrent.FutureCallback;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class NeteaseShop {
    private NeteaseShopFactory facaty;
    private SpigotMaster master;
    private ShopAPI api;

    public NeteaseShop(SpigotMaster master,ShopAPI api){
        this.master = master;
        this.api = api;
        this.facaty = new NeteaseShopFactory();
        getMaster().listenForSpigotMasterEvent(SpigotMasterEvent.PLAYER_BUY_ITEM_SUCCESS, (player, map) -> {tryShipItem(player);});
        getMaster().listenForSpigotMasterEvent(SpigotMasterEvent.PLAYER_URGE_SHIP, (player, map) -> {tryShipItem(player);});
    }

    private void finPlayerOrder(Player player, List<String> finOrderList) {
        getMaster().finPlayerOrder(player, finOrderList, new FutureCallback<>() {
            @Override
            public void completed(Map<String, Object> result) {
                ItemResponse response = Utils.parseResponse(result);
                api.completed(response,player);
                handleOrderCompletion(response, player);
            }

            @Override
            public void failed(Exception ex) {
                api.finPlayerOrderFailed();
            }

            @Override
            public void cancelled() {
                api.finPlayerOrderCancelled();
            }
        });
    }

    private void tryShipItem(Player player) {
        getMaster().getPlayerOrderList(player, new FutureCallback<>() {
            @Override
            public void completed(Map<String, Object> result) {
                ItemResponse response = Utils.parseResponse(result);
                api.completed(response,player);
                processOrderItems(response, player);
            }

            @Override
            public void failed(Exception ex) {
                getApi().tryShipItemFailed();
            }

            @Override
            public void cancelled() {
                getApi().tryShipItemCancelled();
            }
        });
    }

    private void handleOrderCompletion(ItemResponse response, Player player) {
        if (response == null) {
            getApi().jsonError(response,player);
            return;
        }
        processOrderItems(response,player);
    }

    private void processOrderItems(ItemResponse response, Player player) {
        if (response == null) {
            getApi().jsonError(response,player);
            return;
        }
        List<String> finOrderIds = new ArrayList<>();
        for (ItemResponse.Entity entity : response.getEntities()) {
            int orderId = entity.getOrderid();
            String cmd = entity.getCmd();
            String[] args = cmd.split(":");
            facaty.getType(args[0]).run(player,args);
            finOrderIds.add(String.valueOf(orderId));
            getApi().shipmentCompleted(response,player);
        }
        if (!finOrderIds.isEmpty()) {
            finPlayerOrder(player, finOrderIds);
        }
    }
}
