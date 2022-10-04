import java.io.File;
import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException  {
		// TODO Auto-generated method stub
		
		
	     Polynomial p = new Polynomial(); 
	     System.out.println(p.evaluate(3));
		
	     double [] c1 = {2, 4}; 
	     int [] e1 = {2, 1};
             Polynomial p1 = new Polynomial(c1, e1); 
             double [] c2 = {3, 2, 1}; 
   	     int [] e2 = {1, 0, 3};
	     Polynomial p2 = new Polynomial(c2, e2);  
	     Polynomial s = p1.add(p2);
	     Polynomial m = p1.multiply(p2);
	
	     File test = new File ("C:\\Users\\karkh\\OneDrive\\Desktop\\file.txt");
	     Polynomial p3 = new Polynomial(test);
	

	    System.out.println("s(2) = " + s.evaluate(2)); 
	    System.out.println("m(2) = " + m.evaluate(2));
	    if(s.hasRoot(1)) 
		   System.out.println("1 is a root of s"); 
	    else 
		   System.out.println("1 is not a root of s"); 

	}

}