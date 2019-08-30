package mygui;

import java.util.ArrayList;

public class Command{
    private String code;
    private String description;
    private Double cost;
    
    public Command(String code,String description){                         //kataskeuasths
        this.code = code;
        this.description = description;
    
    
    }

    public String getCode(){
        return this.code;
        }
        
        public String getDescr(){
        return this.description;
        }
     
   
     
     public Double calculateCost( ArrayList<Consumption> consumptions ) {
    	 cost = 0.0;
    	 for (Consumption c : consumptions) {
    		 if (c.getCommand() == this) {
    			 cost += c.getResource().getCost( c.getMins()  );
    		 }
    		 
    	 }
    	 
    	 
    	 return cost;
     }
     
     public String toString(){
        return "Command code: " + this.code + "     Description: " + this.description ;
        }
     
     }

