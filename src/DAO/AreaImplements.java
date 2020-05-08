/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Area;
import db.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranq
 */
public class AreaImplements implements AreaDAO {

    @Override
    public List<Area> getAllAreas() {
        List<Area> list = new ArrayList<>();
        Area a = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call getAllAreas }");
            rs = cs.executeQuery();
            while (rs.next()) {
                a = new Area();
                a.setAreaId(rs.getInt("AreaId"));
                a.setAreaName(rs.getString("AreaName"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean createNewArea(Area a) {
        boolean bl = false;
        Connection con = null;
        CallableStatement cs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call createNewArea(?) }");
            cs.setString(1, a.getAreaName());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public Area findAreaByName(String nameArea) {
        Area a = null;
        Connection con = null;
        PreparedStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareStatement("select * from Area where AreaName = ?");
            cs.setString(1, nameArea);
            rs = cs.executeQuery();
            if (rs.next()) {
                a = new Area();
                a.setAreaId(rs.getInt("AreaId"));
                a.setAreaName(rs.getString("AreaName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public boolean updateArea(Area a) {
        boolean bl = false;
        Connection con = null;
        CallableStatement cs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call updateArea(?,?) }");
            cs.setInt(1, a.getAreaId());
            cs.setString(2, a.getAreaName());
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public List<Area> checkExistArea(String oldNameArea, String newNameArea) {
        List<Area> list = new ArrayList<>();
        Area a = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call checkExistArea(?,?) }");
            cs.setString(1, oldNameArea);
            cs.setString(2, newNameArea);
            rs = cs.executeQuery();
            while (rs.next()) {
                a = new Area();
                a.setAreaId(rs.getInt("AreaId"));
                a.setAreaName(rs.getString("AreaName"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean deleteArea(int areaId,String nameArea) {
        boolean bl = false;
        Connection con = null;
        CallableStatement cs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call deleteArea(?,?) }");
            cs.setInt(1, areaId);
            cs.setString(2, nameArea);
            int check = cs.executeUpdate();
            if (check > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl;
    }

    @Override
    public Area findAreaById(int areaId) {
        Area a = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        con = ConnectDB.openConnection();
        try {
            cs = con.prepareCall("{ call findAreaById(?) }");
            cs.setInt(1, areaId);
            rs = cs.executeQuery();
            if (rs.next()) {
                a = new Area();
                a.setAreaId(rs.getInt("AreaId"));
                a.setAreaName(rs.getString("AreaName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

}
