package mygui;

import java.util.ArrayList;

public abstract class Resource {                                    
	private Double cost;
    protected String code;
    
    public Resource(String codeIn){                                     //constructor
        this.code = codeIn;
    
    }
    
    
    public String getCode(){                                                    //method gia epistrofh kwdikou
    
        return this.code;
    
    }
    
    public abstract double getCost( double mins);                                              //abstract method gia ypologismo kostous
    
   
     public String toString(){
        return ("Code: " + this.code );
     }

     
     public Double calculateCost( ArrayList<Consumption> consumptions ) {
    	 cost = 0.0;
    	 for (Consumption c : consumptions) {
    		 if (c.getResource() == this) {
    			 cost += c.getResource().getCost( c.getMins()  );
    		 }
    		 
    	 }
    	 
    	 
    	 return cost;
     }

} 




 
          