package com.mcatk.gem.command;

import com.mcatk.gem.Gem;
import com.mcatk.gem.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandGem implements CommandExecutor {
    private CommandSender sender;
    private String[] args;
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.args = args;
        if (args.length == 0) {
            return false;
        }
        String cmd = args[0].toLowerCase();
        switch (cmd) {
            case "set":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        set();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            case "delete":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        delete();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            case "check":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        check();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            case "add":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        add();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            case "take":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        take();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            case "total":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        total();
                    }
                }.runTaskAsynchronously(Gem.getPlugin());
                break;
            default:
        }
        return true;
    }
    
    private void set() {
        if (args.length != 3) {
            sendParameterError();
        } else {
            try {
                Gem.getPlugin().getGemExecutor().setGems(args[1], Integer.parseInt(args[2]));
                sender.sendMessage(Message.INFO + args[1] + " 的宝石设置为： " + args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(Message.ERROR + "宝石必须是整数");
            }
        }
    }
    
    private void delete() {
        if (args.length != 2) {
            sendParameterError();
        } else {
            Gem.getPlugin().getGemExecutor().deleteGems(args[1]);
            sender.sendMessage(Message.INFO + args[1] + " 宝石数据已清空");
        }
    }
    
    private void check() {
        if (args.length != 2) {
            sendParameterError();
        } else {
            Integer gems = Gem.getPlugin().getGemExecutor().getGems(args[1]);
            sender.sendMessage(Message.INFO + args[1] + " 现在有 " + gems + " 宝石");
        }
    }
    
    private void total() {
        if (args.length != 2) {
            sendParameterError();
        } else {
            Integer gems = Gem.getPlugin().getGemExecutor().getTotalGems(args[1]);
            sender.sendMessage(Message.INFO + args[1] + " 累计 " + gems + " 宝石");
        }
    }
    
    private void take() {
        if (args.length != 3) {
            sendParameterError();
        } else {
            try {
                int gems = Integer.parseInt(args[2]);
                Gem.getPlugin().getGemExecutor().takeGems(args[1], gems);
                sender.sendMessage(Message.INFO + args[1] + " 减少 " + gems + " 宝石");
            } catch (NumberFormatException e) {
                sender.sendMessage(Message.ERROR + "宝石必须是整数");
            }
        }
    }
    
    private void add() {
        if (args.length != 3) {
            sendParameterError();
        } else {
            try {
                int addGems = Integer.parseInt(args[2]);
                Gem.getPlugin().getGemExecutor().addGems(args[1], addGems);
                int gems = Gem.getPlugin().getGemExecutor().getGems(args[1]);
                sender.sendMessage(Message.INFO + args[1] +
                        " 增加了 " + addGems + " 宝石, 现在有 " + gems + " 宝石");
                Bukkit.getPlayer(args[1]).sendMessage("收到 " + addGems + " 宝石, 现在有 " + gems + " 宝石");
            } catch (NumberFormatException e) {
                sender.sendMessage(Message.ERROR + "宝石必须是整数");
            }
        }
    }
    
    private void sendParameterError() {
        sender.sendMessage(Message.ERROR + "参数长度有误");
    }
    
}


