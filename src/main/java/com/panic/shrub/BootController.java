package com.panic.shrub;

import java.io.IOException;
import javafx.fxml.FXML;

public class BootController {

    @FXML
    private void bootToPrimary() throws IOException {
        App.setRoot("secondary");
    }
}