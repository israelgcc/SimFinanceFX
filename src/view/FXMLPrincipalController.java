/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import util.Util;

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
    
    @FXML
    private JFXSlider slTaxa;
    
    @FXML
    private JFXTextField tfMinTaxa; 

    @FXML
    private JFXTextField tfMaxTaxa;

    @FXML
    private JFXTextField tfTaxa;
    
    @FXML
    private JFXSlider slAmort;
    
    @FXML
    private JFXTextField tfMinAmort; 

    @FXML
    private JFXTextField tfMaxAmort;

    @FXML
    private JFXTextField tfAmort;
    
    @FXML
    private JFXRadioButton rbTaxaAno;
    
    @FXML
    private JFXRadioButton rbTaxaMes; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup groupTempo = new ToggleGroup();
        rbTempoAno.setToggleGroup(groupTempo);
        rbTempoMes.setToggleGroup(groupTempo);
        rbTempoMes.setSelected(true);
        slTempo.valueProperty().addListener((observable, oldValue, newValue) ->
        lblTempo.setText(String.valueOf(Math.abs(newValue.intValue()))));
        
        ToggleGroup groupTaxa = new ToggleGroup();
        rbTaxaAno.setToggleGroup(groupTaxa);
        rbTaxaMes.setToggleGroup(groupTaxa);
        rbTaxaAno.setSelected(true);
        
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
        
        rbTaxaMes.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue==true)
            {
                double auxTaxa = Double.valueOf(slTaxa.getValue());
                slTaxa.setMin(0);
                slTaxa.setMax(10);
                double termo1 = 1.0+(auxTaxa/100.0);
                double expoente = 1.0/12.0;
                double termo2 = Math.pow(termo1,expoente);
                termo2 = (termo2 - 1.0)*100.0;
                termo2 = Util.truncar(termo2);
                slTaxa.setValue(termo2);
            }
            else
            {
                double auxTaxa = Double.valueOf(slTaxa.getValue());
                slTaxa.setMin(0);
                slTaxa.setMax(215);
                auxTaxa = Math.pow((1+auxTaxa/100), 12)-1;
                slTaxa.setValue(auxTaxa*100);
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
        
        slTaxa.valueProperty().addListener((observable, oldValue, newValue) ->
        tfTaxa.setText(String.format("%.4f",newValue.doubleValue())));
        
        tfTaxa.textProperty().addListener((observable, oldValue, newValue) ->
        slTaxa.setValue(Double.valueOf(newValue.replace(",","."))));
        
        tfMinTaxa.textProperty().addListener((observable, oldValue, newValue) ->
        slTaxa.setMin(Math.abs(Double.valueOf(newValue))));
        
        tfMaxTaxa.textProperty().addListener((observable, oldValue, newValue) ->
        slTaxa.setMax(Math.abs(Double.valueOf(newValue))));
        
        slAmort.valueProperty().addListener((observable, oldValue, newValue) ->
        tfAmort.setText(String.valueOf(Math.abs(newValue.intValue()))));
        
        tfAmort.textProperty().addListener((observable, oldValue, newValue) ->
        slAmort.setValue(Math.abs(Double.valueOf(newValue))));
        
        tfMinAmort.textProperty().addListener((observable, oldValue, newValue) ->
        slAmort.setMin(Math.abs(Double.valueOf(newValue))));
        
        tfMaxAmort.textProperty().addListener((observable, oldValue, newValue) ->
        slAmort.setMax(Math.abs(Double.valueOf(newValue))));
    }    
    
}
