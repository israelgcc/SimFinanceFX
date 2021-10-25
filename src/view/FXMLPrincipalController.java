/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Resumo;
import util.Simulacao;
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
    
    @FXML
    private JFXComboBox<String> cbFreq;
        
    @FXML
    private JFXComboBox<String> cbTipo;    
    
    //Criar objeto
    @FXML
    private TableView<Resumo> tabelaResumo;
    
    @FXML
    private TableColumn<Resumo, Double> colResSac;

    @FXML
    private TableColumn<Resumo, Double> colResPrice;

    @FXML
    private TableColumn<Resumo, String> colResCampos;

    @FXML
    private TableColumn<Resumo, String> colResPerc;

    @FXML
    private TableColumn<Resumo, String> colResDiferenca;
    
    int qtdTempo;
    double valBem;
    double valEntrada;
    double valTaxa;
    String frequencia;        
    String tipo;
    double aporte;    
    
    public void carrega()
    {
        qtdTempo = Integer.parseInt(lblTempo.getText());
        valBem = Double.parseDouble(tfValorBem.getText());
        valEntrada = Double.parseDouble(tfEntrada.getText());
        valTaxa = Double.parseDouble(tfTaxa.getText().replace(",", "").replace(".", ""))/10000;
        frequencia = (String)(cbFreq.getSelectionModel().getSelectedItem());        
        tipo = (String)(cbTipo.getSelectionModel().getSelectedItem());
        aporte = Double.parseDouble(tfAmort.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Variaveis
        ObservableList<String> freq = FXCollections.observableArrayList("Anual","Mensal");
        ObservableList<String> tip = FXCollections.observableArrayList("Saldo Devedor","Parcelas");
        
        cbFreq.setItems(freq);
        cbTipo.setItems(tip);
        cbFreq.setValue("Mensal");
        cbTipo.setValue("Saldo Devedor");
        
        carrega();   
                
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
        {
            tfValorBem.setText(String.valueOf(Math.abs(newValue.intValue())));
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
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
        
        
        colResCampos.setCellValueFactory(
                new PropertyValueFactory<>("campo"));
        colResSac.setCellValueFactory(
                new PropertyValueFactory<>("sac"));
        colResPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        colResDiferenca.setCellValueFactory(
                new PropertyValueFactory<>("diferenca"));
        colResPerc.setCellValueFactory(
                new PropertyValueFactory<>("perc"));
    }

    public void calcular(int qtdTempo,double valBem,double valEntrada, double valTaxa, String frequencia, double aporte, String tipo)
    {
                
        if (frequencia.equals("Anual"))
            aporte=aporte/12.0;
        double valSaldoSac = valBem-valEntrada;
        
        //SAC
        int parcelaSac = 0;
        Date dtInicialSac = new Date();
        Date dtDataSac = new Date();
        double valorPrincipalOriginalSac = valSaldoSac/qtdTempo;
        ArrayList<Simulacao> simSac = new ArrayList<Simulacao>();
        double prestacaoSac = 0;
        double jurosSac = 0;
        int ultParcelaSac = 0;
        double somaAportesSac = 0;
        double somaPrestacoesSac = 0;
        double valTaxaSac = valTaxa;
        for(int i = 0;i<qtdTempo;i++)
        {
            if (valSaldoSac>0)
            jurosSac = valSaldoSac*valTaxaSac/100;
            else
                jurosSac=0;
            prestacaoSac = valorPrincipalOriginalSac + jurosSac;
            parcelaSac=i;
            LocalDate localData = dtDataSac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localData = localData.plusMonths(1);
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data = (df.format(Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant())));
            simSac.add(new Simulacao(parcelaSac,data,prestacaoSac,valorPrincipalOriginalSac,jurosSac,valSaldoSac));
             
            //chart.addItemSAC(parcela, prestacao);
            if (valSaldoSac>=(valorPrincipalOriginalSac+aporte))
            {
                if (tipo=="Saldo Devedor")
                {
                    valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporte;
                }
                else
                {
                    valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporte;
                    valorPrincipalOriginalSac = valSaldoSac/(qtdTempo-parcelaSac+1);
                }
                somaAportesSac = somaAportesSac+aporte;
                somaPrestacoesSac = somaPrestacoesSac+prestacaoSac;
            }
            else 
            {   
                aporte=valSaldoSac-valorPrincipalOriginalSac;
                somaAportesSac = somaAportesSac+aporte;
                valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporte;
                somaPrestacoesSac = somaPrestacoesSac+prestacaoSac;
                /*chartBarra.addItem(somaPrestacoes, "SAC", "Prestações");
                chartBarra.addItem(somaAportes, "SAC", "Aportes");
                chartBarra.addItem(somaPrestacoes+somaAportes+entrada, "SAC", "Total Pago");*/
                ultParcelaSac = i;
                break;                
            }
            
            
            dtDataSac=Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        double qtd = qtdTempo;
        double ult = ultParcelaSac;
        tabelaResumo.getItems().clear();
        tabelaResumo.getItems().add(new Resumo("Meses Previstos", qtd, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Pagando", ult, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Amortizados", (qtd-ult), 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Previstos", (qtd/12), 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Pagando", (ult/12), 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Amortizados", (qtd-ult)/12, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Valor Principal", prestacaoSac, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Total Aportado", somaAportesSac, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Total Prestações", somaPrestacoesSac, 0.0,"",""));
        tabelaResumo.getItems().add(new Resumo("Total Pago", somaPrestacoesSac+somaAportesSac+valEntrada, 0.0,"",""));
               
        
        
        
        
        
                
    }
    
    
    
}
