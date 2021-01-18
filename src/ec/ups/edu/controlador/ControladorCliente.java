/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Persona;
import ec.ups.edu.vista.VistaPrincipal;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author santi
 */
public class ControladorCliente implements Runnable {

    ControladorCola cola = ControladorCola.getInstance();
    Persona persona;
    Random rand = new Random();
    Thread tread;
    String proceso;
    JTextArea txtArea;

    public ControladorCliente(Persona persona, JTextArea txtArea) {

        this.persona = persona;
        this.txtArea = txtArea;
        tread = new Thread(this);
        tread.start();

    }

    @Override
    public void run() {

        synchronized (cola) {
            if (persona.getContador() >= 2) {
                System.out.println("La cuenta ha alcanzado el maximo");
                proceso = "La cuenta ha alcanzado el maximo\n";

            } else {
                if (!cola.comprobarCola(persona)) {

                    esperar();

                    try {
                        LlegarAlLugar();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void esperar() {

        try {
            int r = rand.nextInt(((10) + 1));
            r = 1 * 1000;
            System.out.println(persona + " - " + r);

            proceso = persona + " - " + r + "\n";

            Thread.sleep(r);
            //Thread.sleep((randomico.nextInt(10)+1)*1000);
        } catch (InterruptedException ex) {

        }

        txtArea.append(proceso);

    }

    public synchronized void LlegarAlLugar() throws InterruptedException {

        if (!cola.comprobarCola(persona)) {
            boolean a = cola.ingresoCola(persona);
            if (a) {

                System.out.println("El cliente " + persona.getId() + " entro a la cola");

                proceso = "El cliente " + persona.getId() + " entro a la cola\n";

            } else {

                System.out.println("La cola esta llena el cliente " + persona.getId() + " se va");

                proceso = "La cola esta llene el cliente " + persona.getId() + "se va\n";

            }

        } else {
            System.out.println("El cliente " + persona.getId() + " ya esta en la cola\n");

            proceso = "El cliente " + persona.getId() + " ya esta en la cola\n";
        }

        txtArea.append(proceso);

    }

}
