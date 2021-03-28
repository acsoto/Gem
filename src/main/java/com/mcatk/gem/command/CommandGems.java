package com.mcatk.gem.command;

import com.mcatk.gem.Gem;
import com.mcatk.gem.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandGems implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    int gems = Gem.getPlugin().getGemExecutor().getGems(sender.getName());
                    sender.sendMessage(Message.INFO + "你的宝石：" + gems);
                }
            }.runTaskAsynchronously(Gem.getPlugin());
        } else {
            sender.sendMessage("指令只能由玩家发出");
        }
        return true;
    }
}
