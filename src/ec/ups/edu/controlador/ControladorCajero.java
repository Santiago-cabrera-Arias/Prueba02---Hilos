/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Persona;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author santi
 */
public class ControladorCajero implements Runnable {

    private ControladorDeProcesos controladroProcesos = new ControladorDeProcesos();
    private ControladorCola cola = ControladorCola.getInstance();
    private Random randomico = new Random();
    private boolean bandera;
    private Thread tread;
    private JLabel cajero;
    private JTextArea txtArea;
    
    String proceso;
    
    public ControladorCajero(JLabel cajero, JTextArea txtArea) {

        bandera = true;
        this.cajero = cajero;
        this.txtArea = txtArea;
        tread = new Thread(this);
        tread.start();

    }

    @Override
    public void run() {

        while (bandera == true) {
            cobrar();
        }
    }

    public void cobrar() {

        if (!cola.colaVacia()) {
            Persona cliente = cola.salirCola();
            System.out.println("El cliente " + cliente.getId() + " Ingreso a Caja");
            cajero.setText("Ocupado");
            

            int valor = cliente.getCuenta();
            int aux = valor;

            boolean cent = false;
            while (cent == false) {
                String accionG = controladroProcesos.accion();

                int acc = controladroProcesos.valor();
                if (accionG.equals("Ingresar")) {
                    aux = aux + acc;
                } else {
                    aux = aux - acc;
                }

                if (aux >= 0) {
                    cent = true;
                    System.out.println("El cliente " + cliente.getId() + " " + accionG + " el valor de: " + acc);
                    proceso = "El cliente " + cliente.getId() + " " + accionG + " el valor de: " + acc;
                    
                    int cont = cliente.getContador();
                    cliente.setContador(cont);
                } else {
                    aux = valor;
                }
            }

            cliente.setCuenta(aux);

            cola.actualizarLista(cliente);

            int rand = randomico.nextInt(3);
            try {

                Thread.sleep((rand + 3) * 1000);

            } catch (InterruptedException ex) {

            }
            cajero.setText("Libre");
            System.out.println("El cliente " + cliente.getId() + " Salio a Caja");
            proceso = "El cliente " + cliente.getId() + " Salio a Caja";
            
            try {
                Thread.sleep((5) * 1000);
            } catch (InterruptedException ex) {

            }
        } else {
            try {
                int r = randomico.nextInt(3);
                Thread.sleep((r + 2) * 1000);
            } catch (InterruptedException ex) {

            }
        }
        
        txtArea.append(proceso);

    }
}
