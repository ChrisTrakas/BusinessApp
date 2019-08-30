package mygui;




import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Mainfx extends Application {

    Stage window;
    BorderPane layout;
    Scene scene1, scene2;
    Resource resourceGiven;
    Command commandGiven;
    Consumption consumptionGiven;
    public static HashMap <String,Resource> resources = new HashMap <String,Resource>(); 
    public static HashMap <String,Command> commands = new HashMap <String,Command>(); 
    public static ArrayList<Consumption> consumptions=   new ArrayList<Consumption>();
    public static ArrayList<Consumption> consumptionsFound = new ArrayList<Consumption>();;
    TableView<Resource> resourceTable = Tools.newTable("Resources" , resources.values());
    TableView<Command> commandTable = Tools.newTable("Commands" , commands.values());
    TableView <Resource> resourceTable2  = Tools.newTable("Resources" , resources.values());
    TableView<Command> commandTable2 = Tools.newTable("Commands" , commands.values());
    TableView <Resource> resourceTable3  = Tools.newTable("Resources" , resources.values());
    TableView<Command> commandTable3 = Tools.newTable("Commands" , commands.values());
    TableView<Consumption> consumptionTable = Tools.newTable("Consumptions Found", consumptionsFound);

    public static void main(String[] args) {
    	
        launch(args);
        
        for (Resource r : resources.values() ) {
        	System.out.println(r);
        
        }
        
        for (Command r : commands.values() ) {
        	System.out.println(r);
        
        }
        for (Consumption r : consumptions ) {
        	System.out.println(r);
        
        }
        
      
        
    }

    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        window = primaryStage;
        window.setTitle("Factory Application");
        

        BorderPane layout = new BorderPane();
        
        Menu toAdd = new Menu("Add");
        Menu addResource = new Menu("Add a new resource");
        MenuItem addPerson = new MenuItem("New Person");
        MenuItem addMachine = new MenuItem("New Machine");
        addResource.getItems().addAll(addPerson, addMachine );
        
        
        MenuItem addCommand = new MenuItem("Add new a production command");
        MenuItem addConsumption = new MenuItem("Add a new consumption");
        toAdd.getItems().addAll(addResource, addCommand , addConsumption);
        	
        
        
        Menu toShow = new Menu("Show");
        MenuItem showResources = new MenuItem("Show the resources");
        MenuItem showConsumptions = new MenuItem("Show the consumptions of a command");
        toShow.getItems().addAll(showResources,showConsumptions);
        
        
        
        Menu toCalculate = new Menu("Calculate");
        MenuItem calculateCommand = new MenuItem("Calculate the cost of a command");					//menu and menu items at the top
        MenuItem calculateResource = new MenuItem("Calculate the cost of a resource");
        toCalculate.getItems().addAll(calculateCommand,calculateResource);
        
        Menu others = new Menu("Others");
        MenuItem close = new MenuItem("Close Application");
        MenuItem home = new MenuItem("Return to homepage");
        MenuItem loadFile = new MenuItem("Load Data from file");
        MenuItem saveFile = new MenuItem("Save Data to file");
        others.getItems().addAll(home ,loadFile , saveFile, close);
        	
        
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(toAdd, toShow, toCalculate,others);
        layout.setTop(menuBar);
        
        
        
        
        
        VBox layoutHome = new VBox(20);
        layoutHome.setPadding(new Insets(50, 50, 50, 50));
        layoutHome.getChildren().addAll(new Label("Welcome to the application. Navigate through the above menu.") );
        
        
        VBox layoutAddPerson = new VBox(20);
        layoutAddPerson.setPadding(new Insets(50, 50, 50, 50));
        
        TextField codeField1 = new TextField();
        codeField1.setMaxWidth(250);
        TextField nameField = new TextField();
        nameField.setMaxWidth(250);
        TextField specialtyField = new TextField();
        specialtyField.setMaxWidth(250);
        TextField rateField = new TextField();
        rateField.setMaxWidth(250);
        Button done1 = new Button("Done");
        
        done1.setOnAction( e->{	if (Tools.DoubleCheck(rateField.getText()) ) {
        							
        						resourceGiven = new Person( codeField1.getText() ,nameField.getText() , specialtyField.getText() ,
               					Double.parseDouble(rateField.getText() ) ); 
        						resources.put( resourceGiven.getCode() , resourceGiven );  	
        						resourceTable.getItems().add(resourceGiven);		
        						resourceTable2.getItems().add(resourceGiven);			
        						resourceTable3.getItems().add(resourceGiven);	}	
        
        						}
        						);
        
        
        
        
        layoutAddPerson.getChildren().addAll(new Label("Enter the code:") ,codeField1, new Label("Enter the name:") , nameField
        		, new Label("Enter the specialty:") , specialtyField, new Label("Enter the hourly rate:") , rateField , done1);
        
        
        
        
        
        
        
        
        
        
        
        VBox layoutAddMachine = new VBox(20);
        layoutAddMachine.setPadding(new Insets(50, 50, 50, 50));
        Button done2 = new Button("Done");
        
        TextField codeField2 = new TextField();
        codeField2.setMaxWidth(250);
        TextField descriptionField = new TextField();
        descriptionField.setMaxWidth(250);
        TextField kwhField = new TextField();
        kwhField.setMaxWidth(250);
        
        
        ComboBox<String> MachineType = new ComboBox<>() ;
        MachineType.getItems().addAll("Regular Machine" , "Special Setup Machine");
        MachineType.setValue("Regular Machine");
        
        
        
        TextField setupField = new TextField();
        setupField .setMaxWidth(250);
        setupField.setPromptText("Enter the minutes required for setup");
        setupField .setVisible(false);
        
        
        MachineType.setOnAction( e-> {if (MachineType.getValue().equals("Regular Machine")	) { setupField .setVisible(false);}
        							  else { setupField .setVisible(true); 
        							  		  }
        } );
    	

  
        
        
        //Machine input
        done2.setOnAction( e-> {if( !Tools.DoubleCheck(kwhField.getText()) || ( setupField.isVisible() && !Tools.DoubleCheck(setupField.getText()) ) ){	
        						}
        						else
        						{        							
        							if ( setupField.isVisible() ) {
        								
	        							resourceGiven = new SpecialMachine( codeField2.getText() , descriptionField.getText() , 
	        									Double.parseDouble(kwhField.getText() )  , Double.parseDouble(setupField.getText() ) ) ;
	        							
        							
        							
        							}else 
        							{
        							
	        							resourceGiven = new Machine ( codeField2.getText() , descriptionField.getText() ,
	        									Double.parseDouble(kwhField.getText() )  );      							
	        								
        							
        							}
        							
        							resources.put( resourceGiven.getCode() , resourceGiven );	
        							resourceTable.getItems().add(resourceGiven);
        							resourceTable2.getItems().add(resourceGiven);
        							resourceTable3.getItems().add(resourceGiven);
        						}
        					
        		
        			});
        
        
        
        layoutAddMachine.getChildren().addAll(new Label("Enter the code:"), codeField2, new Label("Enter the description:") , descriptionField ,
        									  new Label("Enter the kwh consumption:") , kwhField , MachineType , setupField , done2);
        
        
        
        
        
        
      
        
        //adding new command
        
        VBox layoutAddCommand = new VBox(20);
        layoutAddCommand.setPadding(new Insets(50, 50, 50, 50));
        TextField codeField3 = new TextField();
        codeField3.setMaxWidth(250);
        TextField descriptionField2 = new TextField();
        descriptionField2.setMaxWidth(250);
        Button done3 = new Button("Done");
        
        done3.setOnAction(e->{
        					commandGiven = new Command(codeField3.getText() , descriptionField2.getText()) ;
        					commands.put( commandGiven.getCode() ,commandGiven);  
        					commandTable.getItems().add(commandGiven);
        					commandTable2.getItems().add(commandGiven);
        					commandTable3.getItems().add(commandGiven);
        		
        					});
      
        
        layoutAddCommand.getChildren().addAll(new Label("Enter the code of the command") , codeField3, 
        		new Label("Enter the description of the command"), descriptionField2 , done3);
        
        
        
        
        
        
        //adding new consumption
        
        VBox layoutAddConsumption = new VBox(20);
        layoutAddConsumption.setPadding(new Insets(15, 50, 15, 50));
        
        Label choiceLabel = new Label("Choose the Resource and the Command");
        Label minsLabel = new Label("Enter the minutes of the consumption");
        TextField minsField = new TextField();
        minsField.setMaxWidth(100);
        Button done4 = new Button("Done");
        Label dateLabel = new Label("Enter the date of the consumption");
        DatePicker dateIn = new DatePicker();
        
        done4.setOnAction(e->{
			if ( Tools.DoubleCheck( minsField.getText()) && !commandTable.getSelectionModel().isEmpty()  && (dateIn.getValue() != null)
														 && !resourceTable.getSelectionModel().isEmpty()  ) 
				
			{
				consumptionGiven = new Consumption( commandTable.getSelectionModel().getSelectedItem() , 
													resourceTable.getSelectionModel().getSelectedItem() , dateIn.getValue()  , 
													Double.parseDouble(  descriptionField2.getText()) );
				consumptions.add( consumptionGiven);  
			}
        	});
        
        
       
        layoutAddConsumption.getChildren().addAll(choiceLabel , resourceTable , commandTable ,minsLabel, minsField , dateLabel , dateIn , done4);
        
        
        
        

        
        
        
        
        
        VBox layoutShowResources = new VBox(20);
        layoutShowResources.setPadding(new Insets(50, 50, 50, 50));
        layoutShowResources.getChildren().addAll(resourceTable2);
        
        
        VBox layoutShowConsumptions = new VBox(20);
        layoutShowConsumptions.setPadding(new Insets(50, 50, 50, 50));
        Button showConsumptionsButton = new Button("Show Consumptions");
        
        showConsumptionsButton.setOnAction(e ->{consumptionsFound=   new ArrayList<Consumption>();
        										for ( Consumption c : consumptions) {
        											if ( c.getCommand() == commandTable2.getSelectionModel().getSelectedItem() ) {
        												consumptionsFound.add(c);
        												
        											}
        										}
        										
        										consumptionTable.getItems().clear();
        										consumptionTable.getItems().addAll(consumptionsFound);
        									
        										
        											
        											
        });
        
        layoutShowConsumptions.getChildren().addAll( new Label("Choose a command and the relevant consumptions will  be shown.") , 
        		commandTable2 , showConsumptionsButton , consumptionTable);
        
        
        VBox layoutCalculateCommand = new VBox(20);
        layoutCalculateCommand.setPadding(new Insets(50, 50, 50, 50));
        Button calculateCommandButton = new Button("Calculate Cost");
        Label commandCostLabel = new Label();
        calculateCommandButton.setOnAction(e->{ 	if (!consumptions.isEmpty()) {
        													commandCostLabel.setText( "The cost is:" +
        													commandTable3.getSelectionModel().getSelectedItem().calculateCost(consumptions) );
										        }
										        });
        
        
        
        layoutCalculateCommand.getChildren().addAll( commandTable3 ,calculateCommandButton ,commandCostLabel);
        
        
        
        
        
        VBox layoutCalculateResource = new VBox(20);
        layoutCalculateResource.setPadding(new Insets(50, 50, 50, 50));
        Button calculateResourceButton = new Button("Calculate Cost");
        Label resourceCostLabel = new Label();
        calculateResourceButton.setOnAction(e->{ if (!consumptions.isEmpty()) {
												resourceCostLabel.setText( "The cost is: " +
												resourceTable3.getSelectionModel().getSelectedItem().calculateCost(consumptions) );
					}
					});
					        									
  
        
        layoutCalculateResource.getChildren().addAll(resourceTable3 , calculateResourceButton,resourceCostLabel);
        
        
        
        VBox layoutLoad = new VBox(20);
        layoutLoad.setPadding(new Insets(50, 50, 50, 50));
        Button loadResourcesButton = new Button("Load Resources File");
        loadResourcesButton.setOnAction(e->{ FileChooser resourcesChooser = new FileChooser();
        									resourcesChooser.setTitle("txt File Chooser");
        									FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        									resourcesChooser.getExtensionFilters().add(extFilter);
        									File selectedFile = resourcesChooser.showOpenDialog(primaryStage);
        									if (selectedFile!=null) {
        									try {
        										Reader.ResourceReader(  resources , selectedFile) ;
  											  }catch(Exception ex) {
  												  Tools.DoubleCheck("a");
  											  }
        									}
        									});
        
        
        
        
        Button loadCommandsButton = new Button("Load Commands File");
        loadCommandsButton.setOnAction(e->{ FileChooser commandsChooser = new FileChooser();
											  commandsChooser.setTitle("txt File Chooser");
											  FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
											  commandsChooser.getExtensionFilters().add(extFilter);
											  File selectedFile = commandsChooser.showOpenDialog(primaryStage);
											  if (selectedFile!=null) {
											  try {
											  Reader.OrderReader(  resources , commands ,  consumptions , selectedFile)  ;
											  }catch(Exception ex) {
												  Tools.DoubleCheck("a");
											  }
											  }
		        								});
        
        
        
        
        layoutLoad.getChildren().addAll( new Label("Choose the Resources  File") , 
        		loadResourcesButton ,   new Label("Choose the Commands  File")  ,loadCommandsButton );
        
      
        

        VBox layoutSave = new VBox(20);
        layoutSave.setPadding(new Insets(50, 50, 50, 50));
        Button saveResourcesButton = new Button("Save Resources");
        
        Button saveCommandsButton = new Button("Save Commands");
        
        saveCommandsButton.setOnAction(e->{ FileChooser commandsChooser = new FileChooser();
		commandsChooser.setTitle("txt File Saver");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		commandsChooser.getExtensionFilters().add(extFilter);
		File selectedFile = commandsChooser.showSaveDialog(primaryStage);
		if (selectedFile!=null) {
		try {
	    Writer.OrderWriter( commands ,  consumptions , selectedFile)  ;
		 }catch(Exception ex) {
		  Tools.DoubleCheck("a");
		 }
		 }
		 });
        
        
        
        saveResourcesButton.setOnAction(e->{ FileChooser resourcesChooser = new FileChooser();
        resourcesChooser.setTitle("txt File Saver");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		resourcesChooser.getExtensionFilters().add(extFilter);
		File selectedFile = resourcesChooser.showSaveDialog(primaryStage);
		if (selectedFile!=null) {
		try {
	    Writer.ResourceWriter( resources , selectedFile)  ;
		 }catch(Exception ex) {
		  Tools.DoubleCheck("a");
		 }
		 }
		 });
        
        
        
        
        layoutSave.getChildren().addAll( new Label("Choose the Resources  File") , 
        		saveResourcesButton ,   new Label("Choose the Commands  File")  ,saveCommandsButton );
        
        
        
        layout.setCenter(layoutHome);
        addPerson.setOnAction(e -> layout.setCenter(layoutAddPerson) );
        addMachine.setOnAction(e -> layout.setCenter(layoutAddMachine) );
        addCommand.setOnAction(e -> layout.setCenter(layoutAddCommand) );
        addConsumption.setOnAction(e -> layout.setCenter(layoutAddConsumption) );
        showResources.setOnAction(e -> layout.setCenter(layoutShowResources) );			//the action taken for each menu item chosen
        showConsumptions.setOnAction(e -> layout.setCenter(layoutShowConsumptions) );
        calculateCommand.setOnAction(e -> layout.setCenter(layoutCalculateCommand) );
        calculateResource.setOnAction(e -> layout.setCenter(layoutCalculateResource) );
        home.setOnAction(e -> layout.setCenter(layoutHome) );
        loadFile.setOnAction(e -> layout.setCenter(layoutLoad) );
        saveFile.setOnAction(e -> layout.setCenter(layoutSave) );
        close.setOnAction(e -> Tools.exitAlert( layoutSave , layout , window)   );
        
     
        

       
        Scene scene = new Scene(layout, 600, 500);

        window.setScene(scene);
        window.show();
    }

    
   
    
    
    
    
    
    
    

}