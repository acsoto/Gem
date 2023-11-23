package com.mcatk.gem;

import com.mcatk.gem.sql.MySQLManager;

import java.sql.SQLException;

public class GemExecutor {

    public Integer getGems(String name) {
        Integer gems = MySQLManager.getInstance().getGems(name);
        if (gems == null) {
            return 0;
        }
        return gems;
    }

    public void setGems(String name, int gems) {
        if (MySQLManager.getInstance().getGems(name) == null) {
            MySQLManager.getInstance().insertData(name);
        }
        MySQLManager.getInstance().setGems(name, gems);
        Gem.getPlugin().log(name + "宝石设置为" + gems);
    }


    public boolean takeGems(String name, int gems) {
        int currentGems = MySQLManager.getInstance().getGems(name);
        if (currentGems < gems) {
            return false;
        } else {
            MySQLManager.getInstance().setGems(name, currentGems - gems);
            Gem.getPlugin().log(name + "花费宝石" + gems);
            return true;
        }
    }

    public Integer getTotalGems(String name) {
        Integer gems = MySQLManager.getInstance().getTotal(name);
        if (gems == null) {
            return 0;
        }
        return gems;
    }

    public void addGems(String name, int addGems) {
        Integer gems = MySQLManager.getInstance().getGems(name);
        if (MySQLManager.getInstance().getGems(name) == null) {
            MySQLManager.getInstance().insertData(name);
            gems = 0;
        }
        Integer total = MySQLManager.getInstance().getTotal(name);
        gems += addGems;
        total += addGems;
        MySQLManager.getInstance().setGems(name, gems);
        MySQLManager.getInstance().setTotal(name, total);
        Gem.getPlugin().log(name + "获得宝石" + addGems);
    }
}
