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
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
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
    private JFXTextField tfEntrada;
    
    @FXML
    private JFXSlider slTaxa;
    
    

    @FXML
    private JFXTextField tfMaxTaxa;

    @FXML
    private JFXTextField tfTaxa;
    
    @FXML
    private JFXSlider slAmort;
    


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

    @FXML
    private LineChart<?,?> grafico1;
    @FXML
    private BarChart<?, ?> grafico2;    
    
    @FXML
    private CategoryAxis catAxisParcelas;
    @FXML
    private NumberAxis numberAxisPrestacao;
    
    @FXML
    private NumberAxis grafico2y;

    @FXML
    private CategoryAxis grafico2x;
    

    @FXML
    private TextArea dicaText;
        
    public void carrega()
    {
        qtdTempo = Integer.parseInt(lblTempo.getText());
        valBem = Double.parseDouble(tfValorBem.getText());
        valEntrada = Double.parseDouble(tfEntrada.getText());
        valTaxa = Double.parseDouble(tfTaxa.getText().replace(",", "").replace(".", ""))/10000;
        frequencia = (String)(cbFreq.getSelectionModel().getSelectedItem());        
        tipo = (String)(cbTipo.getSelectionModel().getSelectedItem());
        aporte = Double.parseDouble(tfAmort.getText());
        dicaText.setText("Seu tempo é "+aporte);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Variaveis
        
        configureTextFieldToAcceptOnlyDecimalValues(tfTaxa);
        configureTextFieldToAcceptOnlyDecimalValues(tfAmort);
        configureTextFieldToAcceptOnlyDecimalValues(tfEntrada);
        configureTextFieldToAcceptOnlyDecimalValues(tfMaxTaxa);
        configureTextFieldToAcceptOnlyDecimalValues(tfMaxValorBem);
        configureTextFieldToAcceptOnlyDecimalValues(tfMinValorBem);
        configureTextFieldToAcceptOnlyDecimalValues(tfValorBem);
        
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
        rbTaxaMes.setSelected(true);
        
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
                //slTaxa.setMin(0);
                //slTaxa.setMax(10);
                double termo1 = 1.0+(auxTaxa/100.0);
                double expoente = 1.0/12.0;
                double termo2 = Math.pow(termo1,expoente);
                termo2 = (termo2 - 1.0)*100.0;
                termo2 = Util.truncar(termo2);
                //slTaxa.setValue(termo2);
                slTaxa.setValue(Double.valueOf(String.format("%.4f", termo2).replace(",", ".")));
            }
            else
            {
                double auxTaxa = Double.valueOf(slTaxa.getValue());
                //slTaxa.setMin(0);
                //slTaxa.setMax(215);
                auxTaxa = Math.pow((1+auxTaxa/100), 12)-1;
                slTaxa.setValue(Double.valueOf(String.format("%.4f", auxTaxa*100).replace(",", ".")));
            }
        });
        
        slValorBem.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            tfValorBem.setText(String.valueOf(Math.abs(newValue.intValue())));
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
        cbFreq.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
        cbTipo.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
        tfValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        {
            formataTextField(newValue,oldValue,slValorBem,tfValorBem,slEntrada,slAmort);
        });
        
        
    
        tfMinValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        {
            formataTextFieldMin(newValue,oldValue,slValorBem,tfMinValorBem,(Double.valueOf(tfValorBem.getText())));
        });
        
        tfMaxValorBem.textProperty().addListener((observable, oldValue, newValue) ->
        slValorBem.setMax(Math.abs(Double.valueOf(newValue))));
        
        slEntrada.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            tfEntrada.setText(String.valueOf(Math.abs(newValue.intValue())));
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
        
        tfEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        slEntrada.setValue(Math.abs(Double.valueOf(newValue))));
        
        
        slTaxa.valueProperty().addListener((observable, oldValue, newValue) ->
        {
                if (rbTaxaAno.isSelected())
                    tfTaxa.setText(String.valueOf(newValue).replace(".", ","));
                else
                    tfTaxa.setText(String.valueOf(newValue).replace(".", ","));
                carrega();
                calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
            
            
        });
        
        tfTaxa.textProperty().addListener((observable, oldValue, newValue) ->
        {
            
            if (newValue.length()>0)
            {
                slTaxa.setValue(Double.valueOf(newValue.replace(",",".")));
                carrega();
                calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
            }
            else
            {
                tfTaxa.setText(oldValue);
                carrega();
                calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
            }
        });
        
        
        
        
        
        tfMaxTaxa.textProperty().addListener((observable, oldValue, newValue) ->
        slTaxa.setMax(Math.abs(Double.valueOf(newValue))));
        
        slAmort.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            tfAmort.setText(String.valueOf(Math.abs(newValue.intValue())));
            carrega();
            calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        });
        
        
        tfAmort.textProperty().addListener((observable, oldValue, newValue) ->
        slAmort.setValue(Math.abs(Double.valueOf(newValue))));
        

        
        
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
        
        
        carrega();
        calcular(qtdTempo,valBem,valEntrada,valTaxa,frequencia,aporte,tipo);
        
    }

    public void calcular(int qtdTempo,double valBem,double valEntrada, double valTaxa, String frequencia, double aporte, String tipo)
    {
        double aporteSac = 0;
        double aportePrice = 0;
        if (frequencia.equals("Anual"))
        {
            aporteSac=aporte/12.0;
            aportePrice=aporte/12.0;
        }
        else
        {
            aporteSac=aporte;
            aportePrice=aporte;
        }
        
        double valTaxaSac = 0;
        double valTaxaPrice = 0;
        if (rbTaxaAno.isSelected())
        {
            double termo1 = 1.0+(valTaxa/100.0);
            double expoente = 1.0/12.0;
            double termo2 = Math.pow(termo1,expoente);
            termo2 = (termo2 - 1.0)*100.0;
            termo2 = Util.truncar(termo2);
             valTaxaSac = termo2;
             valTaxaPrice = termo2;
            
        }
        else
        {
            valTaxaSac=valTaxa;
            valTaxaPrice=valTaxa;
        }
            
        
        
        //SAC
        double valSaldoSac = valBem-valEntrada;
        int parcelaSac = 0;
        Date dtInicialSac = new Date();
        Date dtDataSac = new Date();
        double valorPrincipalOriginalSac = valSaldoSac/qtdTempo;
        double valorOriginalSac = valorPrincipalOriginalSac + (valSaldoSac*valTaxaSac/100);
        ArrayList<Simulacao> simSac = new ArrayList<Simulacao>();
        double prestacaoSac = 0;
        double jurosSac = 0;
        int ultParcelaSac = 0;
        double somaAportesSac = 0;
        double somaPrestacoesSac = 0;
        
        //PRICE
        double valSaldoPRICE = valBem-valEntrada;
        int parcelaPRICE = 0;
        Date dtDataPRICE = new Date();
        System.out.println(valTaxaPrice);
        double parc1 = (Math.pow((1+(valTaxaPrice/100)), qtdTempo)*(valTaxaPrice/100));
        System.out.println("parc1 "+parc1);
        double parc2 = (Math.pow((1+(valTaxaPrice/100)), qtdTempo)-1);
        System.out.println("parc2 "+parc2);
        double valorPrincipalOriginalPRICE = (valSaldoPRICE*(Math.pow((1+(valTaxaPrice/100)), qtdTempo)*(valTaxaPrice/100))/(Math.pow((1+(valTaxaPrice/100)), qtdTempo)-1));
        System.out.println("valor "+valorPrincipalOriginalPRICE);
        ArrayList<Simulacao> simPRICE = new ArrayList<Simulacao>();        
        double prestacaoPRICE = 0;
        double jurosPRICE = 0;
        int ultParcelaPRICE = 0;
        double somaAportesPRICE = 0;
        double somaPrestacoesPRICE = 0;
        double amortizacao = 0;
        
        boolean bolSac = false;
        boolean bolPrice = false;
        int parcelaComum = 0;
        String dataComum = "";
        
        for(int i = 0;i<qtdTempo;i++)
        {
            LocalDate localData = dtDataSac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localData = localData.plusMonths(1);            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data = (df.format(Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant())));
            if (!bolSac)
            {
                    if (valSaldoSac>0)
                        jurosSac = valSaldoSac*valTaxaSac/100;
                    else
                        jurosSac=0;

                    prestacaoSac = valorPrincipalOriginalSac + jurosSac;
                    parcelaSac=i;

                    
                    simSac.add(new Simulacao(String.valueOf(parcelaSac),data,String.format("%.2f",prestacaoSac),String.format("%.2f",valorPrincipalOriginalSac),
                            String.format("%.2f",jurosSac),String.format("%.2f",valSaldoSac)));

                    if (valSaldoSac>=(valorPrincipalOriginalSac+aporteSac))
                    {
                        if (tipo=="Saldo Devedor")
                        {
                            valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporteSac;
                        }
                        else
                        {
                            valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporteSac;
                            valorPrincipalOriginalSac = valSaldoSac/(qtdTempo-parcelaSac+1);
                        }
                        ultParcelaSac = i;
                        somaAportesSac = somaAportesSac+aporteSac;
                        somaPrestacoesSac = somaPrestacoesSac+prestacaoSac;
                    }
                    else 
                    {   
                        aporteSac=valSaldoSac-valorPrincipalOriginalSac;
                        somaAportesSac = somaAportesSac+aporteSac;
                        valSaldoSac=valSaldoSac-valorPrincipalOriginalSac-aporteSac;
                        somaPrestacoesSac = somaPrestacoesSac+prestacaoSac;
                        ultParcelaSac = i;
                        bolSac = true;                
                    }      
            }
            
            if (!bolPrice)
            {
                if (valSaldoPRICE>0)
                    jurosPRICE = valSaldoPRICE*valTaxaPrice/100;
                else
                    jurosPRICE=0;

                amortizacao = valorPrincipalOriginalPRICE-jurosPRICE;
                prestacaoPRICE = valorPrincipalOriginalPRICE;
                parcelaPRICE=i;

                df = new SimpleDateFormat("dd/MM/yyyy");
                data = (df.format(Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant())));
                simPRICE.add(new Simulacao(String.valueOf(parcelaPRICE),data,String.format("%.2f",prestacaoPRICE),String.format("%.2f",amortizacao),
                        String.format("%.2f",jurosPRICE),String.format("%.2f",valSaldoPRICE)));

                if (valSaldoPRICE>=(amortizacao+aportePrice))
                {
                    if (tipo=="Saldo Devedor")
                    {
                        valSaldoPRICE=valSaldoPRICE-amortizacao-aportePrice;
                    }
                    else
                    {
                        valSaldoPRICE=valSaldoPRICE-amortizacao-aportePrice;
                        valorPrincipalOriginalPRICE=(valSaldoPRICE*(Math.pow((1+(valTaxaPrice/100)), (qtdTempo-parcelaPRICE+1))*(valTaxaPrice/100))/(Math.pow((1+(valTaxaPrice/100)), (qtdTempo-parcelaPRICE+1))-1));
                    }
                    ultParcelaPRICE = i;    
                    somaAportesPRICE = somaAportesPRICE+aportePrice;
                    somaPrestacoesPRICE = somaPrestacoesPRICE+prestacaoPRICE;
                }
                else 
                {   
                    aporte=valSaldoPRICE-amortizacao;
                    somaAportesPRICE = somaAportesPRICE+aportePrice;
                    valSaldoPRICE=valSaldoPRICE-amortizacao-aportePrice;
                    somaPrestacoesPRICE = somaPrestacoesPRICE+prestacaoPRICE;
                    ultParcelaPRICE = i;
                    bolPrice=true;                
                }
            }
            
            if ((prestacaoPRICE>prestacaoSac)&&(parcelaComum==0))
            {
                parcelaComum=i;
                
            }
            
            if (i==parcelaComum*2)
            {
                dataComum = data;
            }
            
            dtDataSac=Date.from(localData.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
        }
        double qtd = qtdTempo;
        double ultSac = ultParcelaSac;
        double ultPrice = ultParcelaPRICE;
        
        tabelaResumo.getItems().clear();
        tabelaResumo.getItems().add(new Resumo("Meses Previstos", String.format("%.0f",qtd), String.format("%.0f",qtd),"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Pagando", String.format("%.0f",ultSac+1), String.format("%.0f",ultPrice+1),"",""));
        tabelaResumo.getItems().add(new Resumo("Meses Amortizados", String.format("%.0f",(qtd-ultSac)),String.format("%.0f",(qtd-ultPrice)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Previstos", String.format("%.0f",(qtd/12)),String.format("%.0f",(qtd/12)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Pagando", String.format("%.0f",(ultSac/12)),String.format("%.0f",(ultPrice/12)),"",""));
        tabelaResumo.getItems().add(new Resumo("Anos Amortizados", String.format("%.0f",(qtd-ultSac)/12), String.format("%.0f",(qtd-ultPrice)/12),"",""));
        tabelaResumo.getItems().add(new Resumo("Valor Médio das Prestações", String.format("%.2f",somaPrestacoesSac/(ultSac+1)), String.format("%.2f",somaPrestacoesPRICE/(ultPrice+1)),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Aportado", String.format("%.2f",somaAportesSac), String.format("%.2f",somaAportesPRICE),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Prestações", String.format("%.2f",somaPrestacoesSac), String.format("%.2f",somaPrestacoesPRICE),"",""));
        tabelaResumo.getItems().add(new Resumo("Total Pago", String.format("%.2f",somaPrestacoesSac+somaAportesSac+valEntrada), String.format("%.2f",somaPrestacoesPRICE+somaAportesPRICE+valEntrada),"",""));
        
        
        tabelaDetSAC.getItems().clear();
        tabelaDetSAC.getItems().addAll(simSac);
        tabelaDetPRICE.getItems().clear();
        tabelaDetPRICE.getItems().addAll(simPRICE);
        
        
        
        XYChart.Series sac = new XYChart.Series();
        sac.setName("SAC");
        for(int i=0;i<simSac.size();i++)
        {
            sac.getData().add(new XYChart.Data(simSac.get(i).getParcela(), Double.valueOf(simSac.get(i).getPrestacao().replace(",", "."))));
        }
        
        XYChart.Series price = new XYChart.Series();
        price.setName("PRICE");
        for(int i=0;i<simPRICE.size();i++)
        {
            price.getData().add(new XYChart.Data(simPRICE.get(i).getParcela(), Double.valueOf(simPRICE.get(i).getPrestacao().replace(",", "."))));
        }
        
        grafico1.getData().clear();
        grafico1.getData().addAll(sac,price);
        
        numberAxisPrestacao.setUpperBound(valorOriginalSac+1000);
        
        XYChart.Series sacBarra = new XYChart.Series<>();
        sacBarra.setName("SAC");
        sacBarra.getData().add(new XYChart.Data("APORTE", somaAportesSac));
        sacBarra.getData().add(new XYChart.Data("PARCELAS", somaPrestacoesSac));
        sacBarra.getData().add(new XYChart.Data("PAGO", somaPrestacoesSac+somaAportesSac+valEntrada));
        
        XYChart.Series priceBarra = new XYChart.Series<>();
        priceBarra.setName("PRICE");
        priceBarra.getData().add(new XYChart.Data("APORTE", somaAportesPRICE));
        priceBarra.getData().add(new BarChart.Data("PARCELAS", somaPrestacoesPRICE));
        priceBarra.getData().add(new BarChart.Data("PAGO", somaPrestacoesPRICE+somaAportesPRICE+valEntrada));
        grafico2.getData().clear();
        grafico2.getData().addAll(sacBarra,priceBarra);
        
        grafico2y.setUpperBound(somaPrestacoesPRICE+somaAportesPRICE+valEntrada+1000);
        
        String texto = "";
        if (parcelaComum>0)
        {
            texto = " - A quitação antes da parcela "+(parcelaComum*2)+" em "+dataComum+" faz com que o financiamento pela modalidade PRICE seja mais vantajoso, caso contrário o SAC é mais econômico.";
        }
        double totalSac = somaPrestacoesSac+somaAportesSac+valEntrada;
        double totalPrice = somaPrestacoesPRICE+somaAportesPRICE+valEntrada;
        
        if (totalSac>totalPrice)
        {
            texto = texto + "/n"+" - Na modalidade PRICE você pagará "+(String.format("%.2f",totalSac-totalPrice))+" reais a menos. Uma diferença de "+(String.format("%.2f",(totalSac-totalPrice)*100/totalSac))+"% .";
        }
        
        if (totalPrice>totalSac)
        {
            texto = texto + "\n"+" - Na modalidade SAC você pagará "+(String.format("%.2f",totalPrice-totalSac))+" reais a menos. Uma diferença de "+(String.format("%.2f",(totalPrice-totalSac)*100/totalPrice))+"% .";
        }
        
        dicaText.setText(texto);
    }
    
    
    
    public void formataTextField(String newValue, String oldValue, JFXSlider slider, JFXTextField textfield, JFXSlider sliderEntrada, JFXSlider sliderAmort)
    {
        if (!newValue.isEmpty())
            {
                if((newValue.matches("^[0-9]*[.]{0,1}[0-9]*$")))
                {
                    slider.setValue(Math.abs(Double.valueOf(newValue)));
                    sliderEntrada.setMax(Math.abs(Double.valueOf(newValue))*80/100);
                    sliderAmort.setMax(Math.abs(Double.valueOf(newValue))*80/100);
                }
                else
                {
                    newValue=oldValue;
                    textfield.setText(String.valueOf(oldValue));
                }
            }
            else
            {
                newValue=oldValue;
                textfield.setText(String.valueOf(0));
            }
    }
    
    public void formataTextFieldMin(String newValue, String oldValue, JFXSlider slider, JFXTextField textfield, double valorMaximo)
    {
        if (!newValue.isEmpty())
            {
                if((newValue.matches("^[0-9]*[.]{0,1}[0-9]*$")))
                {
                    if (valorMaximo<Math.abs(Double.valueOf(newValue)))
                    {
                        slider.setMin(Math.abs(valorMaximo-1)); 
                        textfield.setText(String.format("%.0f",(valorMaximo-1)));
                    }
                    else
                        slider.setMin(Math.abs(Double.valueOf(newValue)));
                }
                else
                {
                    newValue=oldValue;
                    textfield.setText(String.valueOf(oldValue));
                }
            }
            else
            {
                newValue=oldValue;
                textfield.setText(String.valueOf(oldValue));
            }
    }
    
    public static void configureTextFieldToAcceptOnlyDecimalValues(JFXTextField textField) {

        DecimalFormat format = new DecimalFormat("#");

        final TextFormatter<Object> decimalTextFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().isEmpty()) {
                return change;
            }
            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(change.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < change.getControlNewText().length()) {
                return null;
            } else {
                return change;
            }
        });
        textField.setTextFormatter(decimalTextFormatter);
    }
    
    
}
