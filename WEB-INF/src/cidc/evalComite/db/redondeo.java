package cidc.evalMovilidad.db;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class redondeo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  double valor = 1254.525; 
      String val = valor+""; 
      BigDecimal big = new BigDecimal(val);
      big = big.setScale(0, RoundingMode.HALF_UP);
      System.out.println("Número : "+big);
	}
 
}
