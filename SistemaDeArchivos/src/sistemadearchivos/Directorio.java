/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author mati_
 */
public class Directorio {
    
    HashMap<String, Integer> listaDirectorios = new HashMap<String, Integer>();
    Disco disco = new Disco();
    Bloque bloque = new Bloque();
    FCB fcb;

    public Directorio() {
        
    }
    
    public Directorio(HashMap<String, Integer> listaDirectorios) {
        this.listaDirectorios = listaDirectorios;
    }

    public HashMap<String, Integer> getListaDirectorios() {
        return listaDirectorios;
    }

    public void setListaDirectorios(HashMap<String, Integer> listaDirectorios) {
        this.listaDirectorios = listaDirectorios;
    }

    public void openFile(String nombreArchivo) {

        if(this.listaDirectorios.get(nombreArchivo) == null){
            System.out.println("");
            System.out.println("EL ARCHIVO NO EXISTE O SU NOMBRE FUE ESCRITO INCORRECTAMENTE. \n");
        }else{
            System.out.println("El archivo fue encontrado, ahora se abrirá en el FCB. \n");
            
            int bloqueIndice = this.listaDirectorios.get(nombreArchivo);  
            
            System.out.println("El bloque índice es: " + bloqueIndice);
            System.out.println("");
            
            this.bloque = this.disco.getBloquePorIndice(bloqueIndice);
            
            this.fcb = new FCB(100, this.bloque.getIndice(), nombreArchivo);//CAMBIAR EL 100 POR EL TAMAÑO REAL DEL ARCHIVO
            System.out.println("El archivo fue abierto exitosamente. \n");
        }  
    }
    
}
