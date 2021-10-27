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
    
    
    private final SimpleStringProperty parcela;
    private final SimpleStringProperty dtData;
    private final SimpleStringProperty prestacao;
    private final SimpleStringProperty principal;
    private final SimpleStringProperty juros;
    private final SimpleStringProperty saldo;

    public void setSaldo(String saldo) {
        this.saldo.set(saldo);
    }

    public String getSaldo() {
        return saldo.get();
    }
    
    public Simulacao(String parcela, String dtData, String prestacao, String principal, String juros, String saldo) {
        this.parcela = new SimpleStringProperty(parcela);
        this.dtData = new SimpleStringProperty(dtData);
        this.prestacao = new SimpleStringProperty(prestacao);
        this.principal = new SimpleStringProperty(principal);
        this.juros = new SimpleStringProperty(juros);
        this.saldo = new SimpleStringProperty(saldo);
    }

    public String getParcela() {
        return parcela.get();
    }

    public String getDtData() {
        return dtData.get();
    }

    public String getPrestacao() {
        return prestacao.get();
    }

    public String getPrincipal() {
        return principal.get();
    }

    public String getJuros() {
        return juros.get();
    }

    public void setParcela(String parcela) {
        this.parcela.set(parcela);
    }

    public void setDtData(String dtData) {
        this.dtData.set(dtData);
    }

    public void setPrestacao(String prestacao) {
        this.prestacao.set(prestacao);
    }

    public void setPrincipal(String principal) {
        this.principal.set(principal);
    }

    public void setJuros(String juros) {
        this.juros.set(juros);
    }
    
}
