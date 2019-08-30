package mygui;

public class Person extends Resource{               //epekteinei thn abstract class Resource

    private String name;
    private String job;
    private double hourlyRate;

    public Person(String codeIn, String name ,String job , double hourlyRate){          //kataskeuasths
            
            super(codeIn);
            this.name = name;
            this.job = job;
            this.hourlyRate = hourlyRate;
            
            
        
    }

    public double getCost(double mins){                                        //ylopoihsh ths getCost
    
        return this.hourlyRate*(mins/60);
    
    }
    
    
    public String getName(){

        return this.name;
    }
    
    
    
    public String getJob(){
        
        return this.job;
    }
    
    public double getRate(){
        
        return this.hourlyRate;
    }
    
   
    
    public String toString(){                                           //ylopoihsh ths toString
        return (super.toString() + "    Name: " + this.name  + "    Position : " +  this.job  + "   Hourly rate : "   + this.hourlyRate     )  ;
        }





}