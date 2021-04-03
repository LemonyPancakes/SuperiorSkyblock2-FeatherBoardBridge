package me.swagpancakes.superiorskyblock2.featherboardbridge;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandJoinEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandQuitEvent;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;

import be.maximvdw.featherboard.api.FeatherBoardAPI;
import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.LogoutEvent;
import fr.xephi.authme.events.RegisterEvent;
import fr.xephi.authme.events.RestoreSessionEvent;
import fr.xephi.authme.events.UnregisterByAdminEvent;
import fr.xephi.authme.events.UnregisterByPlayerEvent;

public class AuthMe implements Listener {
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Player player = event.getPlayer();
                SuperiorPlayer sp = SuperiorSkyblockAPI.getPlayer(player);

                if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                    FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("AuthMe.NotAuthenticated"));
                }

                if (AuthMeApi.getInstance().isAuthenticated(player)) {
                    if(sp.getIsland() != null) {
                        FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));
                    } else{
                        FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));
                    }
                }

            }

        }.runTaskLater(plugin, plugin.getConfig().getLong("AuthMe.TicksToExecute"));
    }

    @EventHandler
    public void onRestoreSessionEvent(RestoreSessionEvent event) {
        Player player = event.getPlayer();
        SuperiorPlayer sp = SuperiorSkyblockAPI.getPlayer(player);

        if (sp.getIsland() != null) {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));

        }else{
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));

        }

    }

    @EventHandler
    public void onRegisterEvent(RegisterEvent event) {
        Player player = event.getPlayer();
        SuperiorPlayer sp = SuperiorSkyblockAPI.getPlayer(player);

        if (sp.getIsland() != null) {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));

        }else{
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));

        }

    }

    @EventHandler
    public void onUnregisterByPlayerEvent(UnregisterByPlayerEvent event) {
        Player player = event.getPlayer();
        FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("AuthMe.NotAuthenticated"));
    }

    @EventHandler
    public void onUnregisterByAdminEvent(UnregisterByAdminEvent event) {
        Player player = event.getPlayer();

        if (player != null) {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("AuthMe.NotAuthenticated"));
        }
    }

    @EventHandler
    public void onLoginEvent(LoginEvent event) {
        Player player = event.getPlayer();
        SuperiorPlayer sp = SuperiorSkyblockAPI.getPlayer(player);

        if (sp.getIsland() != null) {
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.HasIsland"));

        }else{
            FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("SuperiorSkyblock2.NoIsland"));

        }

    }

    @EventHandler
    public void onLogoutEvent(LogoutEvent event) {
        Player player = event.getPlayer();
        FeatherBoardAPI.showScoreboard(player, plugin.getConfig().getString("AuthMe.NotAuthenticated"));
    }

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

}