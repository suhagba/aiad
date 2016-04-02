/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderHistory;

/**
 *
 * @author Ninad
 */

public class OrderHistoryDAO
{
    Connection conn;
    ResultSet rs;
    
    public OrderHistoryDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OrderHistory> getAll()
    {
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM mydb.order_history;").executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                order.setAmount(this.rs.getInt("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                ret.add(order);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public OrderHistory getOrderHistoryFromID(String id)
    {
        OrderHistory ret = new OrderHistory();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mydb.order_history"
                    + "WHERE id = ?;");
            ps.setString(1, id);
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                order.setAmount(this.rs.getInt("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                ret = order;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}