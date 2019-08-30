package mygui;
class SpecialMachine extends Machine{                               //epekteinei thn class Machine
    private double minsSetup;
    
    
    public SpecialMachine(String codeIn , String description , double Kwh, double minsSetup){                       //kataskeuasths
        super(codeIn,description,Kwh);
        this.minsSetup =minsSetup;
        
       }    
       

    public double getCost(double mins){                                                                                 //ylopoihsh ths getCost
        return this.minsSetup*0.45 + super.getCost(mins) ;
        }
        
    
    public double getMins(){                                            //ylopoihsh ths getCost
        return  minsSetup ;
    
    
    }
    
    
   

}