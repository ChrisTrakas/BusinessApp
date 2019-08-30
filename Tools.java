package mygui;

import java.util.Collection;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Tools {
	
	
	
	public static boolean DoubleCheck(String st){
		
		try {
			
			Double.parseDouble(st);
			return true;
		
		}catch(Exception e){
			
			Stage popUp = new Stage();
			popUp.setTitle("Error");
			popUp.initModality(Modality.APPLICATION_MODAL);
			
			BorderPane inside = new BorderPane();
			inside.setCenter( new Label("Wrong Input Type" ));
			
			Scene scene = new Scene(inside, 200, 100);
			popUp.setScene(scene);
			
			popUp.showAndWait();
			
			return false;
		}
	}
	
	
	
	public static <E> TableView<E> newTable(String title , Collection<E> values){
		
		TableView<E> myTable = new TableView<>();
		
		
		TableColumn<E , String> myColumn = new TableColumn<> (title) ;
		myColumn.setMinWidth(500);
		
	    
	    myTable.getItems().addAll(values);
	        
	    myColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().toString() +  "   (" + c.getValue().getClass().getSimpleName() + ")"  ));

	    myTable.getColumns().addAll(myColumn);


	    
		
		
		
		return myTable;
	}
	
	
	
	
	public static void exitAlert( Pane saveLayout , BorderPane basicLayout , Stage win ){
		
		
			
			
		
			
			Stage popUp = new Stage();
			popUp.setTitle("Save Date");
			popUp.initModality(Modality.APPLICATION_MODAL);
			
			VBox inside = new VBox(10);
			inside.setPadding(new Insets( 10, 10, 10, 10));
			Button saveYes = new Button("Yes");
			Button saveNo = new Button("No");
			
			saveYes.setOnAction( e->{ basicLayout.setCenter(saveLayout);
									  popUp.close(); });
			
			saveNo.setOnAction( e->{ popUp.close();
									 win.close();	});
			
			popUp.setOnCloseRequest( e-> win.close());
			
			inside.getChildren().addAll( new Label("Do you wish to save the data stored?" ) , saveYes , saveNo); 
			
			Scene scene = new Scene(inside, 250, 100);
			popUp.setScene(scene);
			
			popUp.showAndWait();
			
		
			
			
		
	}
	
	
	
	
	
	
}
