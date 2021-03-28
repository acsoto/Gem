package com.mcatk.gem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLog {
    private FileWriter logger;
    
    public FileLog() {
        try {
            logger = new FileWriter(new File(Gem.getPlugin().getDataFolder(), "logs.log"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void log(String str) {
        try {
            logger.append(str).append("\n");
            logger.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
