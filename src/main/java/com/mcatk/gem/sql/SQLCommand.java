package com.mcatk.gem.sql;

public enum SQLCommand {
    CREATE_TABLE(
            "CREATE TABLE IF NOT EXISTS `GEM` (" +
                    "`id` MEDIUMINT UNSIGNED AUTO_INCREMENT," +
                    "`username` VARCHAR(128) NOT NULL," +
                    "`gems` INT NOT NULL DEFAULT 0," +
                    "`total` INT NOT NULL DEFAULT 0," +
                    "UNIQUE (username)," +
                    "PRIMARY KEY (`id`))"
    ),
    INSERT_DATA(
            "INSERT INTO `GEM` " +
                    "(`username`)" +
                    "VALUES (?)"
    ),
    DELETE_DATA(
            "DELETE FROM `GEM` WHERE `username` = ?"
    ),
    SELECT_DATA(
            "SELECT * FROM `GEM` WHERE `username` = ?"
    ),
    UPDATE_GEMS(
            "UPDATE `GEM` SET `gems` = ? WHERE `username` = ?"
    ),
    UPDATE_TOTAL(
            "UPDATE `GEM` SET `total` = ? WHERE `username` = ?"
    );
    
    /*
     * 这里可以添加更多的MySQL命令，格式如下
     * COMMAND_NAME(
     *    "YOUR_COMMAND_HERE" +
     *    "YOUR_COMMAND_HERE"
     * );
     */
    
    private String command;
    
    SQLCommand(String command) {
        this.command = command;
    }
    
    public String commandToString() {
        return command;
    }
}
