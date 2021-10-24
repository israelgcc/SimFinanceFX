/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author israe
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private JFXSlider slTempo;

    @FXML
    private Label lblTempo;

    @FXML
    private JFXRadioButton rbTempoAno;

    @FXML
    private JFXRadioButton rbTempoMes;
    
    @FXML
    private JFXTextField tfValorBem;
    
    @FXML
    private JFXSlider slValorBem;
    
    @FXML
    private JFXTextField tfMaxValorBem;

    @FXML
    private JFXTextField tfMinValorBem;
    
    @FXML
    private JFXSlider slEntrada;
    
    @FXML
    private JFXTextField tfMinEntrada; 

    @FXML
    private JFXTextField tfMaxEntrada;

    @FXML
    private JFXTextField tfEntrada;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup groupTempo = new ToggleGroup();
        rbTempoAno.setToggleGroup(groupTempo);
        rbTempoMes.setToggleGroup(groupTempo);
        rbTempoMes.setSelected(true);
        slTempo.valueProperty().addListener((observable, oldValue, newValue) ->
        lblTempo.setText(String.valueOf(Math.abs(newValue.intValue()))));
        
        rbTempoMes.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue==true)
            {
                double auxTempo = Double.valueOf(slTempo.getValue());
                slTempo.setMin(0);
                slTempo.setMax(420);                
                auxTempo = auxTempo * 12;
                slTempo.setValue(auxTempo);
            }
            else
            {
                double auxTempo = Double.valueOf(slTempo.getValue());
                slTempo.setMin(0);
                slTempo.setMax(35);                
                auxTempo = auxTempo / 12;
                slTempo.setValue(auxTempo);
            }
        });
        
        slValorBem.valueProperty().addListener((observable, oldValue, newValue) ->
        tfValorBem.setText(String.valueOf(Math.abs(newValue.intValue()))));
        
        tfValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        slValorBem.setValue(Math.abs(Double.valueOf(newValue))));
        
        tfMinValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        slValorBem.setMin(Math.abs(Double.valueOf(newValue))));
        
        tfMaxValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        slValorBem.setMax(Math.abs(Double.valueOf(newValue))));
        
        slEntrada.valueProperty().addListener((observable, oldValue, newValue) ->
        tfEntrada.setText(String.valueOf(Math.abs(newValue.intValue()))));
        
        tfEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        slEntrada.setValue(Math.abs(Double.valueOf(newValue))));
        
        tfMinEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        slEntrada.setMin(Math.abs(Double.valueOf(newValue))));
        
        tfMaxEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        slEntrada.setMax(Math.abs(Double.valueOf(newValue))));
        
        
    }    
    
}
