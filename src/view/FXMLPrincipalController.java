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
    
    @FXML
    private TableColumn<Simulacao, String> colSacTarifa;

    @FXML
    private TableColumn<Simulacao, String> colPriceData;

    @FXML
    private TableColumn<Simulacao, String> colSacParcela;
    @FXML
    private TableColumn<Simulacao, String> colPriceDFI;

    @FXML
    private TableColumn<Simulacao, String> colSacJuros;

    @FXML
    private TableColumn<Simulacao, String> colSacValPrincipal;

    @FXML
    private TableColumn<Simulacao, String> colSacValPrestacao;

    @FXML
    private TableColumn<Simulacao, String> colSacDFI;

    @FXML
    private TableColumn<Simulacao, String> colSacPriceDevedor;

    @FXML
    private TableView<Simulacao> tabelaDetSAC;

    @FXML
    private TableColumn<Simulacao, String> colSacMIP;

    @FXML
    private TableColumn<Simulacao, String> colSacSaldoDevedor;


    @FXML
    private TableColumn<Simulacao, String> colPriceTarifa;

    @FXML
    private TableColumn<Simulacao, String> colSacData;

    @FXML
    private TableColumn<Simulacao, String> colPriceMIP;

    @FXML
    private TableColumn<Simulacao, String> colPriceJuros;

    @FXML
    private TableColumn<Simulacao, String> colPriceValPrestacao;

    @FXML
    private TableView<Simulacao> tabelaDetPRICE;

    @FXML
    private TableColumn<Simulacao, String> colPriceValPrincipal;

    @FXML
    private TableColumn<Simulacao, String> colPriceParcela;
    
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
        
        colSacDFI.setCellValueFactory(
                new PropertyValueFactory<>("dfi"));
        colSacData.setCellValueFactory(
                new PropertyValueFactory<>("dtData"));
        colSacJuros.setCellValueFactory(
                new PropertyValueFactory<>("juros"));
        colSacMIP.setCellValueFactory(
                new PropertyValueFactory<>("mip"));
        colSacParcela.setCellValueFactory(
                new PropertyValueFactory<>("parcela"));
        colSacSaldoDevedor.setCellValueFactory(
                new PropertyValueFactory<>("saldo"));
        colSacTarifa.setCellValueFactory(
                new PropertyValueFactory<>("tarifa"));
        colSacValPrestacao.setCellValueFactory(
                new PropertyValueFactory<>("prestacao"));
        colSacValPrincipal.setCellValueFactory(
                new PropertyValueFactory<>("principal"));
        
        colPriceDFI.setCellValueFactory(
                new PropertyValueFactory<>("dfi"));
        colPriceData.setCellValueFactory(
                new PropertyValueFactory<>("dtData"));
        colPriceJuros.setCellValueFactory(
                new PropertyValueFactory<>("juros"));
        colPriceMIP.setCellValueFactory(
                new PropertyValueFactory<>("mip"));
        colPriceParcela.setCellValueFactory(
                new PropertyValueFactory<>("parcela"));
        colSacPriceDevedor.setCellValueFactory(
                new PropertyValueFactory<>("saldo"));
        colPriceTarifa.setCellValueFactory(
                new PropertyValueFactory<>("tarifa"));
        colPriceValPrestacao.setCellValueFactory(
                new PropertyValueFactory<>("prestacao"));
        colPriceValPrincipal.setCellValueFactory(
                new PropertyValueFactory<>("principal"));
        
        
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
            simSac.add(new Simulacao(String.valueOf(parcelaSac),data,String.format("%.2f",prestacaoSac),String.format("%.2f",valorPrincipalOriginalSac),
                    String.format("%.2f",jurosSac),String.format("%.2f",valSaldoSac)));
             
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
        double ultSac = ultParcelaSac;
        
        //PRICE
        double saldoPRICE = valBem-valEntrada;
        int parcelaPRICE = 0;
        Date dtDataPRICE = new Date();
        double parc1 = (Math.pow((1+(valTaxa/100)), qtd)*(valTaxa/100));
        double parc2 = (Math.pow((1+(valTaxa/100)), qtd)-1);
        double valorPrincipalOriginalPRICE = (saldoPRICE*(Math.pow((1+(valTaxa/100)), qtd)*(valTaxa/100))/(Math.pow((1+(valTaxa/100)), qtd)-1));
        ArrayList<Simulacao> simPRICE = new ArrayList<Simulacao>();        
        double prestacaoPRICE = 0;
        double jurosPRICE = 0;
        int ultParcelaPRICE = 0;
        double somaAportesPRICE = 0;
        double somaPrestacoesPRICE = 0;
        double amortizacao = 0;
        for(int i = 0;i<qtd;i++)
        {
            if (saldoPRICE>0)
            jurosPRICE = saldoPRICE*valTaxa/100;
            else
                jurosPRICE=0;
            amortizacao = valorPrincipalOriginalPRICE-jurosPRICE;
            prestacaoPRICE = valorPrincipalOriginalPRICE;
            parcelaPRICE=i;
            LocalDate localData = dtDataPRICE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localData = localData.plusMonths(1);
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data = (df.format(Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant())));
            simPRICE.add(new Simulacao(String.valueOf(parcelaPRICE),data,String.format("%.2f",prestacaoPRICE),String.format("%.2f",amortizacao),
                    String.format("%.2f",jurosPRICE),String.format("%.2f",saldoPRICE)));
            //chart.addItemPRICE(parcelaPRICE, prestacaoPRICE);
            if (saldoPRICE>=(amortizacao+aporte))
            {
                //chart.addItemDataset(prestacaoPRICE, "PRICE", data);
                if (tipo=="Saldo Devedor")
                {
                    saldoPRICE=saldoPRICE-amortizacao-aporte;
                }
                else
                {
                    saldoPRICE=saldoPRICE-amortizacao-aporte;
                    valorPrincipalOriginalPRICE=(saldoPRICE*(Math.pow((1+(valTaxa/100)), (qtd-parcelaPRICE+1))*(valTaxa/100))/(Math.pow((1+(valTaxa/100)), (qtd-parcelaPRICE+1))-1));
                }
                    
                somaAportesPRICE = somaAportesPRICE+aporte;
                somaPrestacoesPRICE = somaPrestacoesPRICE+prestacaoPRICE;
            }
            else 
            {   
                aporte=saldoPRICE-amortizacao;
                somaAportesPRICE = somaAportesPRICE+aporte;
                saldoPRICE=saldoPRICE-amortizacao-aporte;
                somaPrestacoesPRICE = somaPrestacoesPRICE+prestacaoPRICE;
                ultParcelaPRICE = i;
                /*chartBarra.addItem(somaPrestacoesPRICE, "PRICE", "Prestações");
                chartBarra.addItem(somaAportesPRICE, "PRICE", "Aportes");
                chartBarra.addItem(somaPrestacoesPRICE+somaAportesPRICE+entrada, "PRICE", "Total Pago");*/
                break;                
            }
            dtDataPRICE=Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        double ultPrice = ultParcelaPRICE;
        
        tabelaResumo.getItems().clear();
        tabelaResumo.getItems().add(new Resumo("Meses Previstos", String.format("%.0f",qtd), String.format("%.0f",qtd),"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Pagando", String.format("%.0f",ultSac), String.format("%.0f",ultPrice),"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Amortizados", String.format("%.0f",(qtd-ultSac)),String.format("%.0f",(qtd-ultPrice)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Previstos", String.format("%.0f",(qtd/12)),String.format("%.0f",(qtd/12)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Pagando", String.format("%.0f",(ultSac/12)),String.format("%.0f",(ultPrice/12)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Amortizados", String.format("%.0f",(qtd-ultSac)/12), String.format("%.0f",(qtd-ultPrice)/12),"",""));
        tabelaResumo.getItems().add(new Resumo("Valor Principal", String.format("%.2f",prestacaoSac), String.format("%.2f",prestacaoPRICE),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Aportado", String.format("%.2f",somaAportesSac), String.format("%.2f",somaAportesPRICE),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Prestações", String.format("%.2f",somaPrestacoesSac), String.format("%.2f",somaPrestacoesPRICE),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Pago", String.format("%.2f",somaPrestacoesSac+somaAportesSac+valEntrada), String.format("%.2f",somaPrestacoesPRICE+somaAportesPRICE+valEntrada),"",""));
        
        
        tabelaDetSAC.getItems().clear();
        tabelaDetSAC.getItems().addAll(simSac);
        tabelaDetPRICE.getItems().clear();
        tabelaDetPRICE.getItems().addAll(simPRICE);
        
        
                
    }
    
    
    
}
