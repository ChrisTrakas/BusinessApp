package mygui;

import java.util.*;
import java.io.*;



public class Writer {
	
	public static HashMap <String,Resource> resources = new HashMap <String,Resource>(); 
    public static HashMap <String,Command> commands = new HashMap <String,Command>(); 
    public static ArrayList<Consumption> consumptions=   new ArrayList<Consumption>();
    public static String gap = "    ";
	

    
    
    
	
	public static void ResourceWriter( HashMap <String,Resource> resources , File fileGiven) {                                   //dexetai hashmap me resources ta grafei se txt
		
		
		
		FileWriter writer = null;

		try	{
			writer = new FileWriter(fileGiven);
            
            writer.write (   "RESOURCE_LIST"  + "\r\n"+"{"  );
           
		
			for (Resource re : resources.values()) {
            
                writer.write ( "\r\n"+ "\r\n"+  gap + "RESOURCE"  + "\r\n"  + gap + "{"  );

				if (re instanceof  Person) {
					writer.write (           "\r\n" +gap +  "TYPE Person" +                                                                                             //analoga me ton typo grafei tis plhrofories
                                                    "\r\n" +gap + "RESOURCE_CODE " + re.getCode()  +
                                                    "\r\n" +gap + "RESOURCE_NAME " +   ((Person) re).getName() +
                                                    "\r\n" +gap +  "SPECIALTY  " +   ( (Person) re).getJob()  +
                                                    "\r\n" +gap +  "HOURWAGE  " +  ( (Person) re).getRate()        
                                                    );
                    
				}
				else if (re instanceof Machine) {
                
                        if (re instanceof SpecialMachine) {
                            writer.write (      " \r\n" + gap +"TYPE SpecialMachine"   );
                                               
                        }else{
                                writer.write (   " \r\n" +gap + "TYPE Machine"   );
                                           
                        }
                            
                            
                            writer.write (       "\r\n" +gap + "RESOURCE_CODE " + re.getCode()  +
                                                        "\r\n" + gap +"CONSUMPTION " +   ( (Machine) re).getKwh()   +
                                                        "\r\n" + gap +"RESOURCE_DESCR  " + ( (Machine) re).getDescr()
                                                        );
                            
                            if (re instanceof SpecialMachine) {
                            writer.write (      "\r\n" +gap + "SETUP_TIME  " +    ( (SpecialMachine) re).getMins()     
                                                );
                        }
                
                }
                writer.write (   "\r\n"+gap +"}"  );
            }
                
                writer.write (   "\r\n"+"}"  );
            
                
				writer.close();
				
		}//try
			
			catch (IOException e) {
				System.err.println("Error writing file.");
			}
	}
	
	
    
	
	
    
    
    public static void OrderWriter( HashMap <String,Command> commands ,   ArrayList<Consumption> consumptions , File fileGiven) {                                                   //grafei  tis entoles kai tis kinhseis katanalwshs se txt
		
		
		boolean found;
		FileWriter writer = null;

		try	{
			writer = new FileWriter(fileGiven);
            
            writer.write (   "PRODUCTION_ORDER_LIST"  + "\r\n"+"{"  );
            
		
			for (Command com : commands.values() ) {
            
                writer.write ( "\r\n"+  gap + "PRODUCTION_ORDER"  + "\r\n"+ gap +"{"  );

				
				writer.write (  "\r\n" +gap +  "PRODUCTION_ORDER_CODE  " + com.getCode()  +
                                    "\r\n"+gap + "PRODUCTION_ORDER_DESCR  " + com.getDescr() 
                                );
				
                
                
               found = false;

                for (Consumption con : consumptions){
                    if (  con.getCommand() == com  ){
                        found = true;
                        
                    }
                }
                
                
                if (found){
                        writer.write (  "\r\n" + "\r\n"+ gap +  "TRNS"  + "\r\n"+ gap +"{"  );
                        
                        for (Consumption con : consumptions){
                            if (  con.getCommand() == com )  {
                            
                                writer.write (       "\r\n\r\n"   +gap + "TRN" + "\r\n"+gap + "{" +
                                                            "\r\n" + gap + "RESOURCE_CODE "  +  con.getResource().getCode()   +
                                                            "\r\n" + gap + "TIME "  +   con.getMins() +
                                                            "\r\n" + gap + "DATE "  +   con.getDate().toString()  +
                                                            "\r\n"+ gap +"}"
                                                    );
                                
                            }
                        }

                        writer.write (  "\r\n"  + gap +"}" + "\r\n"  );
                }
                
                
                
                                
                
            }
                
                writer.write (   "\r\n"+"}"  );
            
                
				writer.close();
				
		}//try
			
			catch (IOException e) {
				System.err.println("Error writing file.");
                
			}
        }
    
    
				
	
	
	
}


