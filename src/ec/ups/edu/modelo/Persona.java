/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.modelo;

import java.util.Objects;
import javax.swing.JLabel;

/**
 *
 * @author santi
 */
public class Persona {

    private int id;
    private int cuenta;
    private int contador;
    

    public Persona(int id, int cuenta, int contador) {

        this.id = id;
        this.cuenta = cuenta;
        this.contador = 1;
    
    }

    public Persona(int id, int cuenta) {

        this.cuenta = cuenta;
        this.id = id;
    

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.cuenta;
        hash = 97 * hash + this.contador;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cuenta != other.cuenta) {
            return false;
        }
        if (this.contador != other.contador) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", cuenta=" + cuenta + '}';
    }

}
