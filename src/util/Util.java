/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author israe
 */
public class Util {
    
    public static java.util.Date formataDataUtil(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
 		
         java.util.Date date = null;
         try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date)formatter.parse(data);
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
    
    public static java.sql.Date formataDataSQL(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
 		
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
    
    public static double truncar(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String aux = decimalFormat.format(valor);
        double retorno = Double.valueOf(aux.replace(",","."));
        return retorno;
    }
}
