package com.mcatk.gem.sql;

import com.mcatk.gem.Gem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLManager {
    private String ip;
    private String databaseName;
    private String userName;
    private String userPassword;
    private Connection connection;
    private int port;
    private static MySQLManager instance = null;
    
    public static MySQLManager getInstance() {
        return instance == null ? instance = new MySQLManager() : instance;
    }
    
    public void enableMySQL() {
        ip = Gem.getPlugin().getConfig().getString("mysql.ip");
        databaseName = Gem.getPlugin().getConfig().getString("mysql.databasename");
        userName = Gem.getPlugin().getConfig().getString("mysql.username");
        userPassword = Gem.getPlugin().getConfig().getString("mysql.password");
        port = Gem.getPlugin().getConfig().getInt("mysql.port");
        connectMySQL();
        String cmd = SQLCommand.CREATE_TABLE.commandToString();
        try {
            PreparedStatement ps = connection.prepareStatement(cmd);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void shutdown() {
        try {
            connection.close();
        } catch (SQLException e) {
            //断开连接失败
            e.printStackTrace();
        }
    }
    
    private void connectMySQL() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + ip + ":" + port + "/" + databaseName + "?autoReconnect=true",
                    userName, userPassword
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void doCommand(PreparedStatement ps) {
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertData(String name) throws SQLException {
        PreparedStatement ps;
        String s = SQLCommand.INSERT_DATA.commandToString();
        ps = connection.prepareStatement(s);
        ps.setString(1, name);
        doCommand(ps);
    }
    
    public void deleteData(String name) throws SQLException {
        PreparedStatement ps;
        String s = SQLCommand.DELETE_DATA.commandToString();
        ps = connection.prepareStatement(s);
        ps.setString(1, name);
        doCommand(ps);
    }
    
    public Integer getGems(String name) throws SQLException {
        String s = SQLCommand.SELECT_DATA.commandToString();
        PreparedStatement ps = connection.prepareStatement(s);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("gems");
        } else {
            return null;
        }
    }
    
    public Integer getTotal(String name) throws SQLException {
        String s = SQLCommand.SELECT_DATA.commandToString();
        PreparedStatement ps = connection.prepareStatement(s);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return null;
        }
    }
    
    public void setGems(String name, int num) throws SQLException {
        PreparedStatement ps;
        String s = SQLCommand.UPDATE_GEMS.commandToString();
        ps = connection.prepareStatement(s);
        ps.setString(2, name);
        ps.setInt(1, num);
        doCommand(ps);
    }
    
    public void setTotal(String name, int num) throws SQLException {
        PreparedStatement ps;
        String s = SQLCommand.UPDATE_TOTAL.commandToString();
        ps = connection.prepareStatement(s);
        ps.setString(2, name);
        ps.setInt(1, num);
        doCommand(ps);
    }
    
}
