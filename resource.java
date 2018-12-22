/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultima;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author conno
 */
public class resource {
    private SimpleStringProperty ordering;
    private String count;
    private String actual;
    private int[] cntty;
    
    resource(String res, int cnt[]){
    this.ordering = new SimpleStringProperty(res);
    count = ar_calc.ret_int(cnt);
    actual = ar_calc.ret_tot(cnt);
    this.cntty = cnt;
    }
    public String getOrdering(){
    return ordering.get();
    }
    public void setOrdering(String res){
    ordering.set(res);
    }
    
    public String getPrice(){
    return count;
    }
    public void setPrice(int prc){
    count = String.valueOf(prc);
    }
    
    public String getActual(){
    return actual;
    }
    public void setActual(double actl){
    actual= String.valueOf(actl);
    }
    public int[] getCount(){
    return cntty;
    }
    public void setCount(int cnt[]){
    cntty = cnt;
    }
}
