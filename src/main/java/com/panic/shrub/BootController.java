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
    private ChoiceBox<String> bootOverUnder;
    
    @FXML
    private ChoiceBox<String> bootUnit;
    
    private String[] overunder = {"over","under"};
    private String[] units = {"calories","g of fat","g of protein","g of fiber","g of sugar","hours of exercise", "oz of water"};
    

    public void initialize(){
        
        bootOverUnder.getItems().addAll(overunder);
        bootUnit.getItems().addAll(units);
        
    }
    
    @FXML
    private void bootToBoot() throws IOException {
        App.setRoot("boot");
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bootOverUnder.getItems().addAll(overunder);
        bootUnit.getItems().addAll(units);
       
    }
}