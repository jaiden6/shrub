package com.panic.shrub;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SecondaryController implements Initializable{
    
    static int goalsCounter = 2;
    static float goalsMet = 0;
    static private ArrayList<Object> goals = new ArrayList<>();
    static private ArrayList<Object> entries = new ArrayList<>();
    
    private ArrayList loadGoals() throws IOException{
        ArrayList<Object> g = new ArrayList<>();
        DataInputStream save = new DataInputStream(new BufferedInputStream(new FileInputStream("goals.sav")));
        try {
            while (true) {
                g.add(save.readUTF());
            }
        } catch (EOFException e) {
            save.close();
            return g;
        }
    }
    
    @FXML
    private TextField quantity;
    
    @FXML
    private Label units;
    
    @FXML
    private ImageView tree;
    
    @FXML
    private void switchView() throws IOException {
        entries.add(quantity.getText());
        goalsCounter = goalsCounter + 3;
        if(goalsCounter<goals.size()){
            units.setText((String)goals.get(goalsCounter));
        }else{
            for(int i=0; i<entries.size(); i=i+1){
                if("Over".equals((String)goals.get(i))){
                    if(Integer.valueOf((String)entries.get(i))>Integer.valueOf((String)goals.get((3*i)+1))){
                        goalsMet = goalsMet + 1;
                    }
                }
                if("Under".equals((String)goals.get(i))){
                    if(Integer.valueOf((String)entries.get(i))<Integer.valueOf((String)goals.get((3*i)+1))){
                        goalsMet = goalsMet + 1;
                    }
                }
            }
            DataInputStream stateIn = new DataInputStream(new BufferedInputStream(new FileInputStream("state.sav")));
            int state[] = new int[4];
            for(int i=0; i<4; i=i+1){
                state[i] = stateIn.readInt();
            }
            state[0] = state[0] + 1;
            if(goalsMet / entries.size()>0.8){
                state[1] = state[1] + 1;
                state[3] = 0;
            }
            if(goalsMet / entries.size()<0.2){
                if(state[3]==1){
                    state[2] = 1;
                }
                state[3] = 1;
            }
            DataOutputStream stateOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("state.sav")));
            for(int i=0; i<state.length; i=i+1){
                stateOut.writeInt(state[i]);
            }
            stateOut.close();
            App.setRoot("primary");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            goals = loadGoals();
            units.setText((String)goals.get(2));
        } catch (IOException i){

        }
    }
}