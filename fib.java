import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.BigInteger;


public class fib {
  
	
	public static BigInteger getBInt(String val){
		return new BigInteger(val);
		}
	
	public static BigInteger[] Reader(){
		File file = new File("datasets/rosalind_fib.txt");
		String pattern = "(\\d+) (\\d)";
				
		try {
			Scanner in = new Scanner(file);
			String line = in.nextLine();

			BigInteger n = getBInt(line.replaceAll(pattern,"$1"));
			BigInteger k = getBInt(line.replaceAll(pattern,"$2"));
		    
		  BigInteger[] a = {n,k}; 
			return a;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null; 
	    
		
	}
	
  
  
    public static BigInteger getFib(BigInteger n, BigInteger k) {
    	if(n.intValue()==0) return getBInt("0");
    	else if (n.intValue() <= 2) return getBInt("1");
        else return getFib(n.subtract(getBInt("1")), k).add((k.multiply(getFib(n.subtract(getBInt("2")), k))));
    }



    
    public static void main(String[] args) {
    	
    	BigInteger[] r = Reader();
    	BigInteger n = r[0];
    	BigInteger k = r[1];
    	
      System.out.println(getFib(n,k));
      
    }
}

