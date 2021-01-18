/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Persona;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author santi
 */
public class ControladorCola {

    //Inicio Singleton
    private static ControladorCola instance = new ControladorCola();

    private int MAXIMO = 10;
    private List<Persona> lista;
    LinkedList<Persona> cola;

    public static ControladorCola getInstance() {

        return instance;

    }

    //Fin singleton.
    public ControladorCola() {

        lista = new ArrayList<>();
        cola = new LinkedList<>();

    }

    public boolean ingresoCola(Persona persona) {
        if (colaLlena()) {
            Persona c = reemplazoCliente(persona);
            cola.addLast(c);
            return true;
        }
        return false;
    }

    public Persona salirCola() {
        if (!colaVacia()) {
            return cola.removeFirst();
        }
        return null;
    }
    
    public boolean colaLlena() {

        if (cola.size() <= MAXIMO - 1) {
            return true;
        }
        return false;
    }

    public boolean colaVacia() {

        if (cola.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean comprobarCola(Persona persona) {
        if (cola.contains(persona)) {

            return true;
        }
        return false;
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }

    public Persona reemplazoCliente(Persona persona) {

        for (Persona persona1 : lista) {
            if (persona1.getId() == persona.getId()) {
                return persona1;
            }
        }

        return null;
    }

    public void actualizarLista(Persona persona) {

        for (Persona persona1 : lista) {
            if (persona1.getId() == persona.getId()) {
                int posicion = lista.indexOf(persona1);
                lista.set(posicion, persona);
            }
        }
    }

}
