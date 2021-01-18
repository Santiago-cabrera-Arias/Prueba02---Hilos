/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import java.util.Random;

/**
 *
 * @author santi
 */
public class ControladorDeProcesos {

    private String[] realizacion = {"Ingresar", "Retirar"};
    private int[] valores = {100, 50, 20};
    private Random random;

    public ControladorDeProcesos() {
        
        random = new Random();
        
    }

    public String accion() {

        int i = random.nextInt(realizacion.length);
        return realizacion[i];
    }

    public int  valor() {

        int i = random.nextInt(valores.length);
        return valores[i];
    }

}
