package me.swagpancakes.superiorskyblock2.featherboardbridge;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandJoinEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandQuitEvent;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;

import be.maximvdw.featherboard.api.FeatherBoardAPI;

public class EventClass implements Listener {
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onIslandJoinEvent(IslandJoinEvent event) {
        SuperiorPlayer player = event.getPlayer();
        FeatherBoardAPI.showScoreboard(player.asPlayer(), plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));

    }

    @EventHandler
    public void onIslandCreateEvent(IslandCreateEvent event) {
        SuperiorPlayer player = event.getPlayer();
        FeatherBoardAPI.showScoreboard(player.asPlayer(), plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));


    }

    @EventHandler
    public void onIslandDisbandEvent(IslandDisbandEvent event) {
        SuperiorPlayer player = event.getPlayer();
        if (player.isOnline())
            FeatherBoardAPI.showScoreboard(player.asPlayer(), plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));


    }

    @EventHandler
    public void onIslandQuitEvent(IslandQuitEvent event) {
        SuperiorPlayer player = event.getPlayer();
        FeatherBoardAPI.showScoreboard(player.asPlayer(), plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));


    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SuperiorPlayer sp = SuperiorSkyblockAPI.getPlayer(player);

        if(sp.getIsland() != null) {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));
        }else {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));

        }


    }


}