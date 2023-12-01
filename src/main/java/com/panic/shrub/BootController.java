package com.panic.shrub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class BootController implements Initializable{


    @FXML
    private void bootToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private ChoiceBox<String> cb1;
    
    @FXML
    private ChoiceBox<String> cb2;
    
    private String[] overunder = {"over","under"};
    private String[] units = {"calories","g of fat","g of protein","g of fiber","g of sugar","hours", "oz of water"};
    

    public void initialize(){
        
        cb1.getItems().addAll(overunder);
        cb2.getItems().addAll(units);
        
    }
    
    @FXML
    private void bootToBoot() throws IOException {
        App.setRoot("boot");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb1.getItems().addAll(overunder);
        cb2.getItems().addAll(units);
       
    }
}