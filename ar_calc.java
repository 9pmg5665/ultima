/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultima;

/**
 *
 * @author conno
 */
public class ar_calc {
    
    public ar_calc(){

}
    public static String ret_tot(int[] county){
       double total = 0.00;
       double[] pricey = new double[4];
        pricey[0] = 1.50;
        pricey[1] = .40;
        pricey[2] = 1.00;
        pricey[3] = .50;
        for(int i=0; i<4; i++){
       total = total +(county[i]*pricey[i]);
       } 
        
    return String.valueOf(total);
    }
    public static String ret_int(int[] county){
    int total = 0;
    for(int i = 0; i<4; i++){
    total = total + county[i];
    }
    return String.valueOf(total);
    }
    public static String[] ret_prc(){
    String[] pricey = new String[4];
        pricey[0] = String.valueOf(1.50);
        pricey[1] = String.valueOf(.40);
        pricey[2] = String.valueOf(1.00);
        pricey[3] = String.valueOf(.50);
        return pricey;
    }
}
