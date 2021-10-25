/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author israe
 */
public class  Simulacao{
    
    
    private final SimpleIntegerProperty parcela;
    private final SimpleStringProperty dtData;
    private final SimpleDoubleProperty prestacao;
    private final SimpleDoubleProperty principal;
    private final SimpleDoubleProperty juros;
    private final SimpleDoubleProperty saldo;

    public void setSaldo(double saldo) {
        this.saldo.set(saldo);
    }

    public double getSaldo() {
        return saldo.get();
    }
    
    public Simulacao(int parcela, String dtData, Double prestacao, double principal, double juros, double saldo) {
        this.parcela = new SimpleIntegerProperty(parcela);
        this.dtData = new SimpleStringProperty(dtData);
        this.prestacao = new SimpleDoubleProperty(prestacao);
        this.principal = new SimpleDoubleProperty(principal);
        this.juros = new SimpleDoubleProperty(juros);
        this.saldo = new SimpleDoubleProperty(saldo);
    }

    public int getParcela() {
        return parcela.get();
    }

    public String getDtData() {
        return dtData.get();
    }

    public double getPrestacao() {
        return prestacao.get();
    }

    public double getPrincipal() {
        return principal.get();
    }

    public double getJuros() {
        return juros.get();
    }

    public void setParcela(int parcela) {
        this.parcela.set(parcela);
    }

    public void setDtData(String dtData) {
        this.dtData.set(dtData);
    }

    public void setPrestacao(double prestacao) {
        this.prestacao.set(prestacao);
    }

    public void setPrincipal(double principal) {
        this.principal.set(principal);
    }

    public void setJuros(double juros) {
        this.juros.set(juros);
    }
    
}
