package com.panic.shrub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.util.ArrayList;

public class BootController implements Initializable{

    @FXML
    private void bootToPrimary() throws IOException {
        writeGoals(goals);
        writeState(defaultState);
        App.setRoot("primary");
    }
    
    @FXML
    private void bootToBoot() throws IOException {
        App.setRoot("boot");
        goals.add(bootOverUnder.getSelectionModel().getSelectedItem());
        goals.add(bootQuantity.getText());
        goals.add(bootUnit.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private ChoiceBox<String> bootOverUnder;
    
    @FXML
    private ChoiceBox<String> bootUnit;
    
    @FXML
    private TextField bootQuantity;
    
    private final String[] overUnderArr = {"Over","Under"};
    private final String[] unitsArr = {"calories","g of fat","g of protein","g of fiber","g of sugar","hours of exercise", "oz of water"};
    private final int[] defaultState = {0, 0, 0, 0};
    static private ArrayList<Object> goals = new ArrayList<>();
    
    private void writeGoals(ArrayList g) throws IOException {
        DataOutputStream save = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("goals.sav")));
        for(int i=0; i<g.size(); i=i+1){
            save.writeUTF((String)g.get(i));
            i=i+1;
            save.writeUTF((String)g.get(i));
            i=i+1;
            save.writeUTF((String)g.get(i));
        }
        save.close();
    }
    
        private void writeState(int[] defaultState) throws IOException {
        DataOutputStream state = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("state.sav")));
        for(int i=0; i<defaultState.length; i=i+1){
            state.writeInt(defaultState[i]);
        }
        state.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bootOverUnder.getItems().addAll(overUnderArr);
        bootUnit.getItems().addAll(unitsArr);
    }
}