package mygui;

import java.time.LocalDate;

public class Consumption{
    private Command command;                                                     
    private LocalDate date;
    private Resource resource;
    private double mins;
    
    public Consumption(Command command , Resource resource , LocalDate date , double mins){
        this.command = command;
        this.date = date;
        this.resource = resource;
        this.mins = mins;
        
    
    
    }
    
    public Command getCommand(){
        return command;
        }
        
        
     public LocalDate getDate(){
        return this.date;
        }
     
     
     public Resource getResource(){
        return resource;
        }
        
        public double getMins(){
        return this.mins;
        }
     
        
    public String toString(){
        return ( "Command: "+ this.command  + "     Resource: " +  this.resource + "    Minutes: " + this.mins + "     Date: " + this.date  );
        }


}