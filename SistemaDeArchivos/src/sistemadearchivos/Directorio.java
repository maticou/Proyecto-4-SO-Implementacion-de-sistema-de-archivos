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
 * @author MatiasParra
 * @author ManuelGonzalez
 */
public class Directorio {
    
    HashMap<String, Integer> listaDirectorios;
    Bloque bloque;
    FCB fcb;

    public Directorio() {
        this.listaDirectorios = new HashMap<String, Integer>();
    }
    
    void agregarDirectorioDisco(String directorio){
        String [] array = directorio.split("-");
        
        if(array.length < 2){
            System.out.println("El directorio esta vacio.");
        }
        else{
            String nombreArchivo = array[0];
            int bloqueIndice = Integer.parseInt(array[1]);	
        
            this.listaDirectorios.put(nombreArchivo, bloqueIndice);
        }
    }
    
    void imprimirListaDirectorios(){
        for (String i : this.listaDirectorios.keySet()) {
            System.out.println("Nombre archivo: " + i + " Index Block: " + this.listaDirectorios.get(i));
        }
    }
    
    void eliminarDirectorio(){
        this.listaDirectorios.clear();
    }

    public HashMap<String, Integer> getListaDirectorios() {
        return listaDirectorios;
    }

    public void setListaDirectorios(HashMap<String, Integer> listaDirectorios) {
        this.listaDirectorios = listaDirectorios;
    }

    /**
     * Función que abre el archivo.
     * 
     * Primero pregunta si el archivo está abierto en el FCB, de lo contrario, lo abre 
     * y almacena en el FCB.
     * 
     * @param nombreArchivo String que almacena el nombre del archivo a buscar y abrir.
     */
    public void openFile(String nombreArchivo, Disco disco) {

        if(this.listaDirectorios.get(nombreArchivo) == null){
            System.out.println("\nEL ARCHIVO NO EXISTE O SU NOMBRE FUE ESCRITO INCORRECTAMENTE. \n");
        }else{
            System.out.println("\nEl archivo fue encontrado, ahora se abrirá en el FCB. \n");
            
            int bloqueIndice = this.listaDirectorios.get(nombreArchivo);  
            
            System.out.println("El bloque índice es: " + bloqueIndice);
            System.out.println("");
            
            this.bloque = disco.getBloquePorIndice(bloqueIndice);
            
            this.fcb = new FCB(100, this.bloque.getIndice(), nombreArchivo);//CAMBIAR EL 100 POR EL TAMAÑO REAL DEL ARCHIVO
            System.out.println("\nEl archivo fue abierto exitosamente. \n");
        }  
    }
    
}
