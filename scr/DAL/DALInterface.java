/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;

/**
 *
 * @author Hung
 * @param <T>
 */
public interface DALInterface<T> {
    public boolean insert(T t);
    
    public boolean update(T t);
    
    public boolean delete(int id);
    
    public ArrayList<T> getAll();
    
    public T getById(int id);
    
    public ArrayList<T> getByCondition(String condition);
}
