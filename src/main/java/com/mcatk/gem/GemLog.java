package com.mcatk.gem;

public class GemLog {
    public static void log(String s) {
        Gem.getPlugin().getLogger().info(s);
    }
}
