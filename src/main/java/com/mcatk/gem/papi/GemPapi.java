package com.mcatk.gem.papi;

import com.mcatk.gem.Gem;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class GemPapi extends PlaceholderExpansion {
    
    private Gem plugin;
    
    public GemPapi(Gem plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean persist() {
        return true;
    }
    
    @Override
    public boolean canRegister() {
        return true;
    }
    
    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }
    
    @Override
    public String getIdentifier() {
        return "gem";
    }
    
    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        switch (params) {
            case "gems":
                return String.valueOf(Gem.getPlugin().getGemExecutor().getGems(p.getName()));
            default:
        }
        return null;
    }
}
