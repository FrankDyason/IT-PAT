/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DyasonFra
 */
public class CarManager {
    //fields

    //constructor
    public CarManager() {

    }

    public String getCarData(String sql) {
        String temp = "";
        DBConnect db = new DBConnect();
        try {
            ResultSet rs = db.queryTbl(sql);
            while (rs.next()) {
                temp = temp + rs.getInt("OwnerID");
                temp = temp + rs.getString("OwnerName");
                temp = temp + rs.getString("ContactNum");
                temp = temp + rs.getString("IDNum");
                temp = temp + rs.getString("Address");
                temp = temp + rs.getString("CreditCardNum") + "\n";
                System.out.println(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public boolean login(String un, String pw) {
        boolean correct = false;
        DBConnect db = new DBConnect();
        try {
            ResultSet rs = db.queryTbl("SELECT * FROM Login WHERE Username = '" + un + "'");
            while (rs.next()) {
                String pass = rs.getString("Password");

                if (pass.equals(pw)) {
                    correct = true;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correct;
    }

    public void addUser(String un, String pass, boolean cl, boolean man, String fn, String sn) {
        DBConnect db = new DBConnect();
        try {
            db.updateTbl("INSERT INTO Login (Username, Password, Client, Manager) VALUES('" + un + "','" + pass + "'," + cl + "," + man + ")");
            db.updateTbl("INSERT INTO Owner (Username) VALUES('" + un + "','" + pass + "')");
        } catch (SQLException ex) {
            Logger.getLogger(CarManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
