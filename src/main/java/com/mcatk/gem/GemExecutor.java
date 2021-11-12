package com.mcatk.gem;

import com.mcatk.gem.sql.MySQLManager;

import java.sql.SQLException;

public class GemExecutor {
    
    public Integer getGems(String name) {
        try {
            Integer gems = MySQLManager.getInstance().getGems(name);
            if (gems == null) {
                return 0;
            }
            return gems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void setGems(String name, int gems) {
        try {
            if (MySQLManager.getInstance().getGems(name) == null) {
                MySQLManager.getInstance().insertData(name);
            }
            MySQLManager.getInstance().setGems(name, gems);
            Gem.getPlugin().log(name + "宝石设置为" + gems);
        } catch (SQLException e) {
            e.printStackTrace();
            Gem.getPlugin().getLogger().warning("SQL错误");
        }
    }
    
    public void deleteGems(String name) {
        try {
            MySQLManager.getInstance().deleteData(name);
            Gem.getPlugin().log(name + "数据删除");
        } catch (SQLException e) {
            e.printStackTrace();
            Gem.getPlugin().getLogger().warning("SQL错误");
        }
    }
    
    public boolean takeGems(String name, int gems) {
        try {
            int currentGems = MySQLManager.getInstance().getGems(name);
            if (currentGems < gems) {
                return false;
            } else {
                MySQLManager.getInstance().setGems(name, currentGems - gems);
                Gem.getPlugin().log(name + "花费宝石" + gems);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Gem.getPlugin().getLogger().info("SQL异常");
        }
        return false;
    }
    
    public Integer getTotalGems(String name) {
        try {
            Integer gems = MySQLManager.getInstance().getTotal(name);
            if (gems == null) {
                MySQLManager.getInstance().insertData(name);
                return 0;
            }
            return gems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addGems(String name, int addGems) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            Gem.getPlugin().getLogger().info("SQL异常");
        }
    }
}
