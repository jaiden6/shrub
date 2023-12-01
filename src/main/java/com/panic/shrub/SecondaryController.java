package com.panic.shrub;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    }
