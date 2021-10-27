/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author israe
 */
public class  Resumo{
    
    
    private final SimpleStringProperty campo;
    private final SimpleStringProperty sac;
    private final SimpleStringProperty price;
    private final SimpleStringProperty diferenca;
    private final SimpleStringProperty perc;

    public void setCampo(String campo) {
        this.campo.set(campo);
    }

    public String getCampo() {
        return campo.get();
    }
    
    public Resumo(String campo, String sac, String price, String diferenca, String perc) {
        this.campo = new SimpleStringProperty(campo);
        this.sac = new SimpleStringProperty(sac);
        this.price = new SimpleStringProperty(price);
        this.diferenca = new SimpleStringProperty(diferenca);
        this.perc = new SimpleStringProperty(perc);
    }

    public String getSac() {
        return sac.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getDiferenca() {
        return diferenca.get();
    }

    public String getPerc() {
        return perc.get();
    }

    public void setSac(String sac) {
        this.sac.set(sac);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setDiferenca(String diferenca) {
        this.diferenca.set(diferenca);
    }

    public void setPerc(String perc) {
        this.perc.set(perc);
    }
    
}
