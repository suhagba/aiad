/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.Product;

/**
 *
 * @author Ninad
 */

public interface ProductDAO
{
    public List<Product> getAll();
    
    public int getNoOfProducts();
    
    public Product getProductFromID(String id);
    
    public Product getBestSelling();
    
    public List<Product> getProductFromName(String name);
    
    public List<Product> getAllPending();
    
    public Product getLatestPending();
    
    public List<Product> getLatest();
    
    public List<Product> getLatestRecycled();
    
    public List<Product> getByFilter(String name, String modelNum, int categoryId);
    
    public List<Product> getRecycledByFilter(String name, String modelNum, int categoryId, String owner);
    
    public List<Product> getByFilter(List<String> brand, List<Integer> categoryId);
    
    public List<Product> getRecycledByOwner(String owner);
    
    //if recycled enter owner, else if new, owner input can be anything
    public boolean addProduct(Product product, String category);
    
    public boolean updateProduct(Product product);
    
    public boolean approveRecycled(String id);
    
    public int getQuantityById(String id);
    
    public String getNameById(String id);
    
    public double getPriceById(String id);
    
    public boolean updateQuantity(String productID, int quantity);
    
    public String[] getRelated(String productId);
    
    public List<String> listAllBrands();
    
    public boolean checkIfNew(String productId);
    
}
