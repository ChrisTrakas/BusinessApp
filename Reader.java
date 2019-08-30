package mygui;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reader {


    public static HashMap <String,Resource> resources = new HashMap <String,Resource>(); 
    public static HashMap <String,Command> commands = new HashMap <String,Command>(); 
    public static ArrayList<Consumption> consumptions=   new ArrayList<Consumption>();


        public static BufferedReader reader= null;
        public static StringTokenizer st;
        public static String word , line  , type , code , descr ,name,specialty , orderCode , orderDescr , date , delim=" ";
        public static boolean valid;
        public static double kwh , setup_mins , rate ,time;
        
    
    public static boolean checkNext(BufferedReader reader , String value){
        


     try {                                                                                                                                      //methodos pou epistrefei boolean timh , 
                                                                                                                                                    //true -------> an to "value" einai to epomeno string pou emfanizetai
        do{                                                                                                                                       //false------->an oxi
                       
            line = reader.readLine();                                   
            
            
            }while(  line!=null    && line.trim().equals("") );
            
            
            
            if ( line!=null){
            return line.trim().equalsIgnoreCase(value) ;                           
                }
            return false;
               
        
            


        }catch (IOException e){
      System.err.println("Error Reading File...");
      return false;
    }
    
    
    }
    
    
   
     public static void ResourceReader(  HashMap <String,Resource> resources , File fileGiven)   
     {
    
        
    
      try{
      
    	  	
            reader = new BufferedReader(new FileReader(fileGiven));
            
            
            line = "";
            
            
            valid =   checkNext(reader, "RESOURCE_LIST") ;                              //elegxei oti to file arxizei opws prepei
            
            valid = valid && checkNext(reader, "{")  ;
            
            
                     
            
            
            
            
            
            if  (valid){

               
                
                //anamesa sto resource_list {  .....      }:
                

                while(  !checkNext(reader, "}")   && line!=null  && valid   ){
                    
                    
                       
                      valid =   line.trim().equalsIgnoreCase("RESOURCE");                                                 //elegxos domhs resource
                      
                         
                      valid = valid && checkNext(reader, "{")  ;
                      
                      
                      
                       
                      
                      
                      
                            type = "";
                            code = "";
                            descr = "";
                            kwh = 0;                                                                                            //arxikopoihsh prin diavastei kathe resource 
                            setup_mins=0;
                            name = "";
                            specialty = "";
                            rate =0;

                        
                        
                       //anamesa sto resource{.....}
                       
                       
        
                      
                      if (valid ){
                         
                        line = reader.readLine();  
                        
                        
                        while  ( !line.trim().equals("}") ){                                                                                //anagnwsh kai apothikeush analoga me to TAGS
                        
                            
                         
                        
                            st = new StringTokenizer(line , " ");
                            if ( st.hasMoreTokens() ){
                                word = st.nextToken();
                                
                                
                                
                                if ( word.equalsIgnoreCase("TYPE") ) {
                                    
                                    if (st.hasMoreTokens() ){
                                        type = st.nextToken();
                                        
                                        
                                        }
                                
                                }else if ( word.equalsIgnoreCase("RESOURCE_CODE") ){
                                   
                                    
                                    if (st.hasMoreTokens() ){
                                        code = st.nextToken();
                                        
                                        }
                                
                                }else if ( word.equalsIgnoreCase("RESOURCE_DESCR") ){
                                    
                                    
                                    while (st.hasMoreTokens() ){
                                        descr += st.nextToken();
                                        }
                                
                                }else if ( word.equalsIgnoreCase("CONSUMPTION") ) {
                                    
                                    
                                    if (st.hasMoreTokens() ){
                                        kwh = Double.parseDouble( st.nextToken()  );
                                        
                                        }
                                
                                }else if ( word.equalsIgnoreCase("SETUP_TIME")){
                                    
                                    
                                    if (st.hasMoreTokens() ){
                                        setup_mins = Double.parseDouble( st.nextToken()  );
                                        }
                                
                                }else if ( word.equalsIgnoreCase("RESOURCE_NAME") ){
                                
                                    
                                    
                                    while (st.hasMoreTokens() ){
                                        name += st.nextToken() + " ";
                                        }
                                
                                }else if ( word.equalsIgnoreCase("SPECIALTY") ) {
                                    
                                   
                                    if (st.hasMoreTokens() ){
                                        specialty = st.nextToken();
                                        }
                                
                                }else if ( word.equalsIgnoreCase("HOURWAGE") ){
                                    
                                    
                                     if (st.hasMoreTokens() ){
                                        rate = Double.parseDouble( st.nextToken()  );
                                        
                                        }
                                
                                }
                                
                                                                                                     
                            
                            
                              }
                              
                              
                              
                              
                              
                              if ( checkNext(reader, "}") ){
                              
                                    if ( !code.equals("") ){
                            
                            
                                
                                
                                if (    type.equalsIgnoreCase("Machine")    ){
                                    
                                    
                                    
                                    if (!descr.equals("") ){
                                    
                                        resources.put(code, new Machine (code,descr,kwh ) );                                                              //ylopoihsh twn antikeimenwn symfwna me ton typo tou TAG
                                        
                                         
                                    }
                                
                                
                                }else if (    type.equalsIgnoreCase("SpecialMachine")    ){
                                    
                                    if (!descr.equals("") ){
                                    
                                        resources.put( code, new  SpecialMachine (code,descr,kwh,setup_mins ) );
                                    
                                    }
                            
                            
                                }else if (    type.equalsIgnoreCase("Person")    ){
                                    
                                    if (!name.equals("") ){
                                    
                                        resources.put(code, new  Person (code,name,specialty,rate ) );
                                    
                                    }

                                }
                      }

                              }

                        }

                }

                
                }

            
        }
        reader.close();
    

         // try
       }catch (IOException e){
          System.err.println("Error Reading File...");
       }
    
        
        }
        
        
        
        
     
        
        
        
        
        
        
        
        
        public static void OrderReader(  HashMap <String,Resource> resources , HashMap <String,Command> commands ,  ArrayList<Consumption> consumptions , File fileGiven) 
      {

        
         try{
      
      
            reader = new BufferedReader(new FileReader(fileGiven));
      
            
            line = "";    
            valid =   checkNext(reader, "PRODUCTION_ORDER_LIST") ;                              //elegxei oti to file arxizei opws prepei 
            valid = valid && checkNext(reader, "{")  ;
            
            

            
            if  (valid){

               
                
                //anamesa sto PRODUCTION_ORDER_LIST{  .....      }:
                
               
                while( !checkNext(reader, "}")   && line!=null  && valid   ){
                    
                    
                      valid =   line.trim().equalsIgnoreCase("PRODUCTION_ORDER");
                      
                         
                      valid = valid && checkNext(reader, "{")  ;
                       
                       
                      
                       
                      

                            code = "";                                                                                            //arxikopoihsh prin diavastei kathe order
                            orderCode = "";
                            orderDescr="";

                        
                        
                       //anamesa sto PRODUCTION_ORDER{.....}
                       
                       
        
                      
                      if (valid ){
                         
                        line = reader.readLine();  
                        
                        
                        while  ( !line.trim().equals("}") ){
                        
                        
                            
                         
                            
                            st = new StringTokenizer(line , " ");
                            if ( st.hasMoreTokens() ){
                                word = st.nextToken();
                                
                                
                                if ( word.equalsIgnoreCase("PRODUCTION_ORDER_CODE") ) {
                                    
                                    if (st.hasMoreTokens() ){
                                        orderCode = st.nextToken();
                                        
                                        
                                        }
                                
                                }else if ( word.equalsIgnoreCase("PRODUCTION_ORDER_DESCR") ){
                                   

                                        
                                        while (st.hasMoreTokens() ){
                                        orderDescr += st.nextToken() + " ";
                                        }
                                        
                                }
                            }
                            

                             if ( !orderCode.equals("") &&  !orderDescr.equals("")  && !commands.containsKey(orderCode) ){
                                    
                                    commands.put( orderCode , new Command(orderCode , orderDescr ) );
                                    
                                   
                              
                              
                              
                              valid =   checkNext(reader, "TRNS") ;                              //elegxei  an yparxei TRNS
                              
                              if  (valid){
                              valid = valid && checkNext(reader, "{")  ;
                              }
                              
                              
                              
                              
                              
                              
                              
                              
                            if  (valid)
                            {

               
                
                        //anamesa sto TRNS {  .....      }:
                        

                        while(  !checkNext(reader, "}")   && line!=null  && valid   ){
                            
                            
                               
                              valid =   line.trim().equalsIgnoreCase("TRN");
                              
                                 
                              valid = valid && checkNext(reader, "{")  ;
                              
                              
                              

                                    code = "";
                                    date = "";                                                                          //arxikopoihsh prin kathe TRN
                                    time=0;

  
                                
                               //anamesa sto TRN{.....}
                               
                               
                
                              
                              if (valid ){
                                 
                                line = reader.readLine();  
                                
                                
                                while  ( !line.trim().equals("}") ){
                                
                                    
                                 
                                
                                        st = new StringTokenizer(line , " ");
                                        if ( st.hasMoreTokens() ){
                                            word = st.nextToken();
                                            
                                        
                                        
                                         if ( word.equalsIgnoreCase("RESOURCE_CODE") ){
                                          
                                            
                                            if (st.hasMoreTokens() ){
                                                code = st.nextToken();
                                                
                                                }
                                        
                                        }else if ( word.equalsIgnoreCase("TIME") ) {                                                                                                                    //ylopoihsh twn antikeimenwn symfwna me ton typo tou TAG
                                            
                                            
                                            if (st.hasMoreTokens() ){
                                                time = Double.parseDouble( st.nextToken()  );
                                                
                                                }
                                        
                                        }else if ( word.equalsIgnoreCase("DATE") ) {
                                            
                                           
                                            if (st.hasMoreTokens() ){
                                                date = st.nextToken();
                                                }
                                        
                                        }
                                        
                                                                                                             
                                    
                                    
                                    }
                                      
                                      
                                      
                                      
                                      
                                      if ( checkNext(reader, "}") ){
                                      
                                            if ( !code.equals("") && time!=0 &&  !date.equals("")){
                                                
                                                if  ( resources.containsKey(code)   ) {                                                                                                     //elexgos gia to an yparxei to resource pou diavazetai  gia na dhmiourgithei kinhsh katanalwshs
                                                   
                                                
                                                    consumptions.add(  new Consumption( commands.get(orderCode) , resources.get(code), 
                                                    									LocalDate.parse(date) ,time) );
                                                
                                                }else{  System.out.println( "The resource code given does not exist.");
                                                }
                                        
                                        

                                        }
                                        
                                      }

                                      
                                    
                                }

                        
                                }

                                }

                            }
                            
                           }
                            orderDescr="";
                            line = reader.readLine();  
                            
                            

                        }

                    }

                }

            }   
        reader.close();
    

         // try
       }catch (IOException e){
          System.err.println("Error Reading File...");
       }
       
       
    
    
    }
    
   
    
    
    
    
    
    
    

    


}