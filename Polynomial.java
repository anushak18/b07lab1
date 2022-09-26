public class Polynomial {
	double [] coeffs; 
	
	
	public Polynomial(){ 
		coeffs = new double[1]; 
		coeffs[0] = 0;
		
	}
	
	public Polynomial(double [] array){ 
		coeffs = new double[array.length]; 
		for (int i = 0; i < array.length; i++) {
			coeffs[i] = array[i];
		
			
		}
	}
	
	
	public Polynomial add(Polynomial a) {
		if(a.coeffs.length >= coeffs.length) {
			double [] c = new double[a.coeffs.length]; 
			
			for (int i = 0; i < c.length; i++){
				if (i >= coeffs.length){
					c[i] = a.coeffs[i];
				}
				else {
					c[i] = a.coeffs[i] + coeffs[i];
				}
				
			
			}
			return new Polynomial(c);
	    	
	    	}
		else {
			double [] c = new double[coeffs.length]; 
			for (int i = 0; i < coeffs.length; i++){
	    	
				c[i] = a.coeffs[i] + coeffs[i];
			}
			return new Polynomial(c);
			
		}
	}
	
	
	public double evaluate(double x){
		double [] new_coeffs = new double[coeffs.length];
        	double p = 0.0;
        	for(int i = 0; i < coeffs.length; i++) {
        		if (i == 0 && coeffs[i] == 0) {
        			new_coeffs[i] = 0;
        		}
        		else {
        			new_coeffs[i] = Math.pow(x, i) * coeffs[i];
        		
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
	
	

}