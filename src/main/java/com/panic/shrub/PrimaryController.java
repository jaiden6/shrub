package com.panic.shrub;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable{
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private Label healthTargets;
    
    @FXML
    private Label streakLabel;
    
    @FXML
    private ImageView tree;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ArrayList goals = loadGoals();
            String text = new String();
            for(int i=0; i<goals.size(); i=i+3){
                text = text + (String)goals.get(i) + ' ' + (String)goals.get(i+1) + ' ' + (String)goals.get(i+2) + "\n";
            }
            healthTargets.setText(text);
            DataInputStream stateIn = new DataInputStream(new BufferedInputStream(new FileInputStream("state.sav")));
            int state[] = new int[4];
            for(int i=0; i<4; i=i+1){
                state[i] = stateIn.readInt();
            }
            tree.setImage(new Image("file:///C:/Users/Jaiden/Documents/GitHub/shrub/src/main/resources/com/panic/shrub/" + Integer.toString(state[1]) + ".png"));
            if (state[3] == 1){
                tree.setImage(new Image("file:///C:/Users/Jaiden/Documents/GitHub/shrub/src/main/resources/com/panic/shrub/dying/" + Integer.toString(state[1]) + ".png"));
            }
            if (state[2] == 1){
                tree.setImage(new Image("file:///C:/Users/Jaiden/Documents/GitHub/shrub/src/main/resources/com/panic/shrub/dead.png"));
            }
            int streak = state[0];
            streakLabel.setText(Integer.toString(streak));
        } catch (IOException i) {
        }
    }
}
