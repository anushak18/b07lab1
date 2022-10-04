import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Polynomial {
	double [] coeffs; // just declaring an array of doubles, no "new" here
	int [] exponents;
	
	
	public Polynomial(){ //zero arg. constructor
		coeffs = new double[] {0}; //making an array with 1 box, do this inside constructor
		exponents = new int[] {0};
		
		
	}
	
	public Polynomial(double [] coarray, int [] exarray){ // one arg. constructor
		coeffs = new double[coarray.length]; 
		exponents = new int[exarray.length];
		for (int i = 0; i < coarray.length; i++) {
			coeffs[i] = coarray[i];
			exponents[i] = exarray[i];
			
			
			
		}
	}
	
	public Polynomial(File newFile) throws FileNotFoundException {
		Scanner scan = new Scanner(newFile); //scanner
		String line = scan.nextLine(); //"5-367x2+7x8+1x-8"
		
		
		int terms = 1;
		for (int i = 0; i < line.length(); i++) {
		  if (line.charAt(i) == '-' || line.charAt(i) == '+') {
			  terms++;			 
		  }
		}
		  
	    	coeffs = new double[terms+1];
	    	exponents = new int[terms+1];
	    	int main_ind = 0;
		String [] newString = new String [10];
		newString = line.split("[+]");
		
		for (int i = 0; i < newString.length; i++) {
			
			if(newString[i].length() == 1 || newString[i].length() == 2) {
				// ex, "5" or "-5". make exponents 0 and coeff 5/-5
				double f = Double.parseDouble(newString[i]);
				//System.out.println(f);
				if (main_ind != coeffs.length) {
					coeffs[main_ind] = f;
					exponents[main_ind] = 0;
					main_ind++;
				}
				
				
			}
			else { // 5-378x2
				if (newString[i].contains("-") == true) {
					int ind = 0;
					int count = 0;
					for (int v = 0; v < newString[i].length(); v++) {
						if (newString[i].charAt(v) == '-') {
							count++;	
						}
					}
					while (count != 0) {
						if (newString[i].charAt(ind) != '-') {
							String temp = "";
							
							while (newString[i].charAt(ind) != '-'){
								temp = temp + newString[i].charAt(ind);
								ind++;
							}
							//System.out.println(temp);
							if(temp.length() == 1 || temp.length() == 2) {
								// ex, "5" or "-5". make exponents 0 and coeff 5/-5
								double f = Double.parseDouble(temp);
								//System.out.println(f);
								if (main_ind != coeffs.length) {
									coeffs[main_ind] = f;
									//System.out.println(coeffs[0] + "coeffs at beginning");
									exponents[main_ind] = 0;
									main_ind++;
								}
								
								
							}
							else { //32x5
								int ix = 0;
								String coeffstr = "";
								while (temp.charAt(ix) != 'x') {
									coeffstr = coeffstr + temp.charAt(ix);
									ix++;
									
								}
								if (main_ind != coeffs.length) {
									double j = Double.parseDouble(coeffstr);
									
									coeffs[i] = j;
									
									ix++;
									String ex_str = "";
									ex_str = ex_str + newString[i].charAt(ix);
									int m = Integer.parseInt(ex_str);
									exponents[i] = m;
									main_ind++;
								}
								
								
							}
							String costr = "-";
							ind++;
							while (newString[i].charAt(ind) != 'x') {
								costr = costr + newString[i].charAt(ind); //this has the negative in it
								ind++;
							}
							if (main_ind != coeffs.length) {
								double j = Double.parseDouble(costr);
								coeffs[main_ind] = j;
								ind++;
								String exstr = "";
								exstr = exstr + newString[i].charAt(ind);
								int m = Integer.parseInt(exstr);
								exponents[main_ind] = m;
								count--;
								main_ind++;
							}
							
						}
				
					}
					
				}
				// 7x8, 523x2
				else {
					int ix = 0;
					String cf = "";
					
					while (newString[i].charAt(ix) != 'x') {
						cf = cf + newString[i].charAt(ix);
						ix++;
						
					}
					if (main_ind != coeffs.length) {
						double j = Double.parseDouble(cf);
						coeffs[main_ind] = j;
						ix++;
						String ex = "";
						ex = ex + newString[i].charAt(ix);
						int m = Integer.parseInt(ex);
						exponents[main_ind] = m;
						main_ind++;
					}
					
				}
					
			}
		
			
		}
		scan.close();		
	}
		
	
	public Polynomial add(Polynomial a) {
		
		int [] new_e = new int [a.coeffs.length + coeffs.length];
		double [] new_c = new double [a.coeffs.length + coeffs.length];
	
		// getting new length
		for (int i = 0; i < a.coeffs.length; i++){
			new_e[i] = a.exponents[i];	
		}
		for (int i = 0; i < a.coeffs.length; i++){
			new_c[i] = a.coeffs[i];	
		}
		
		for (int i = 0; i < coeffs.length; i++) {
			new_e[i+a.exponents.length] = exponents[i];
		}
		for (int i = 0; i < coeffs.length; i++) {
			new_c[i+a.coeffs.length] = coeffs[i];
		}

		int x = new_e.length;
		int [] temp = new int [x];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = -1;
		}
		List placeholder = Arrays.asList(temp);
		int index = 0;
		for (int i = 0; i < new_e.length; i++) {
			if (placeholder.contains(new_e[i]) == false) {
				for (int j = i + 1; j < new_e.length; j++) {
					if (new_e[i] == new_e[j]) {
						x--;
						temp[index] = new_e[i];
						index++;
						break;
					}
				}
			}
			
		}
		
		int ind = 0;
		int [] final_e = new int [x];
		double [] final_c = new double [x];
		
	
		
		for (int i = 0; i < new_e.length; i++) {
			int flag = 0;
			for (int j = i+1; j < new_e.length; j++) {
				if ((new_e[i] == new_e[j]) && (ind != x) && (i != j)) {
					final_c[ind] = new_c[i] + new_c[j];
					final_e[ind] = new_e[i];
					flag = 1;
					ind++;
					
					
				}
			}
			if ((flag == 0) && (ind != x)) {
				final_c[ind] = new_c[i];
				final_e[ind] = new_e[i];
				ind++;

				
			}
			
				
		}
		
		return new Polynomial(final_c, final_e);
	
	}
	
	
	public Polynomial multiply(Polynomial a) {
		int [] new_e = new int [a.exponents.length * exponents.length];
		double [] new_c = new double [a.coeffs.length * coeffs.length];
		
		int ind = 0;
		for (int i = 0; i < a.coeffs.length; i++) {
			for (int j = 0; j < coeffs.length; j++) {
				if (ind != new_c.length) {
					new_c[ind] = a.coeffs[i] * coeffs[j];
					new_e[ind] = a.exponents[i] + exponents[j];
					ind++;
				}
			}
		} 
		
		int x = new_e.length;
		int [] temp = new int [x];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = -1;
		}
		
		List tempp = Arrays.asList(temp);
		int index = 0;
		for (int i = 0; i < new_e.length; i++) {
			if (tempp.contains(new_e[i]) == false) {
				for (int j = i + 1; j < new_e.length; j++) {
					if (new_e[i] == new_e[j]) {
						x--;
						temp[index] = new_e[i];
						index++;
						break;
					}
				}
			}
			
		}
	
		//removing duplicates
		for (int i = 0; i < new_e.length; i++) {
			for (int j = i + 1; j < new_e.length; j++) {
				if (new_e[i] == new_e[j]) {
					new_c[i] = new_c[i] + new_c[j];
					new_c[j] = -2;
					new_e[j] = -2;
				}
			}
		}
		// removing -2's
		double [] final_c = new double [x];
		int [] final_e = new int [x];
		int id = 0;
		for (int i = 0; i < new_e.length; i++) {
			if((new_e[i] != -2 && new_c[i] != -2) && id != final_e.length) {
				final_e[id] = new_e[i];
				final_c[id] = new_c[i];
				id++;
			}
		}

		
		return new Polynomial(final_c, final_e);
		
	}
	
	
	
	public double evaluate(double x){
		double [] new_coeffs = new double[coeffs.length];
		
        	double p = 0.0;
        	for(int i = 0; i < coeffs.length; i++) {
        		if (exponents[i] == 0) {
        			new_coeffs[i] = coeffs[i];
        		}
        		else {
        			new_coeffs[i] = Math.pow(x, exponents[i]) * coeffs[i];
        		} 
        		p = p + new_coeffs[i];
        	}
        	return p;
        }
	
	public boolean hasRoot(double x) {
		if (this.evaluate(x) == 0) {
			return true;
		}
		
		return false;
		
	}
	
	void saveToFile(String fileName) throws FileNotFoundException {
		String poly = "";
		  for (int i = 0; i < coeffs.length; i++) {
			  String coeff = String.valueOf(coeffs[i]);
			  
			  if(coeff.charAt(0) == '-') { //make first coefficient negative
				  poly = poly + '-';
				  if (exponents[i] == 0) { // doesnt need an x
					  poly = poly + String.valueOf(coeffs[i]);	  
				  }
				  else {
					  poly = poly + String.valueOf(coeffs[i]) + 'x' + String.valueOf(exponents[i]);
				  } 
			  }		  
			  else { // first coeff not negative
				  if (i != 0) {
					  poly=poly+'+';
				  }
				  if (exponents[i] == 0) { // doesn't need an x
					  poly = poly + String.valueOf(coeffs[i]);
				  }
				  else {
					  poly = poly + String.valueOf(coeffs[i]) + 'x' + String.valueOf(exponents[i]);
				  }
			  }

		  }
		  PrintStream prnt = new PrintStream(fileName);
		  prnt.println(poly); //save +close file
		  prnt.close();
	}
	
	

}
