package com.cped.neteaseshop;

import org.bukkit.entity.Player;

public interface ItemAchieve {
    void run(Player player, String[] args);
    String getType();
}
