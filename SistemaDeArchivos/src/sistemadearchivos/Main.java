/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

/**
 *
 * @author mati_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Directorio directorio = new Directorio();
        Disco disco =  new Disco(4);
        disco.setDirectorio(directorio);
        disco.leerDisco();
        //disco.formatearDisco();
        //disco.obtenerDirectorio();
        //disco.guardarDatos();
        // TODO code application logic here
    }
    
}
