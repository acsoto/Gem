package com.mcatk.gem;

import com.mcatk.gem.command.CommandGem;
import com.mcatk.gem.command.CommandGems;
import com.mcatk.gem.papi.GemPapi;
import com.mcatk.gem.sql.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Gem extends JavaPlugin {
    
    private static Gem plugin;
    private GemExecutor gemExecutor;
    private FileLog fileLog;
    
    public static Gem getPlugin() {
        return plugin;
    }
    
    public GemExecutor getGemExecutor() {
        return gemExecutor;
    }
    
    @Override
    public void onEnable() {
        plugin = this;
        gemExecutor = new GemExecutor();
        fileLog = new FileLog();
        //配置文件
        saveDefaultConfig();
        reloadConfig();
        //连接MySQL
        new BukkitRunnable() {
            @Override
            public void run() {
                MySQLManager.getInstance().enableMySQL();
                getLogger().info("连接到MySQL");
            }
        }.runTaskAsynchronously(this);
        //注册指令
        Bukkit.getPluginCommand("gem").setExecutor(new CommandGem());
        Bukkit.getPluginCommand("gems").setExecutor(new CommandGems());
        getLogger().info("启动完毕");
        //PAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("检测到PlaceholderAPI，已启动PAPI变量");
            new GemPapi(this).register();
        }
    }
    
    @Override
    public void onDisable() {
        MySQLManager.getInstance().shutdown();
    }
    
    public void log(String s) {
        String log = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss]").format(new Date())
                + s;
        Gem.getPlugin().getLogger().info(log);
        Gem.getPlugin().fileLog.log(log);
    }
    
}
