package mygui;

public class Machine extends Resource {                              //epekteinei thn abstract class Resource
    private String description;
    private double kwh;
    
    
    public Machine(String codeIn , String description , double kwh){                         //kataskeuasths
        super(codeIn);
        this.description = description;
        this.kwh = kwh;
    
    
    
    }
    
    
    public double getCost(double mins){                                            //ylopoihsh ths getCost
        return  kwh *  (mins / 60) * 0.089;
    
    
    }
    
    public double getKwh(){                                            //ylopoihsh ths getCost
        return  kwh ;
    }
    
    
     public String getDescr(){                                            //ylopoihsh ths getCost
        return  description ;
    
    
    }


    public String toString(){
        return ( super.toString()  + "   Description: " + this.description + "     Consumption in kwh:" + this.kwh);
        }

}