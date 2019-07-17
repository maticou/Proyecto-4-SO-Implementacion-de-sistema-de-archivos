/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *Clase directorio es la encargada de almacenar todos los nombres de los archivos en el disco 
 * y su bloque índice en un tipo de asignación indexada de disco.
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
    
    /**
     * Agrega el directorio al disco y lo carga a la memoria.
     * @param directorio String con los datos del directorio
     */
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
    
    /**
     * Muestra por consola el nombre del archivo, el índice "padre" y su tamaño en bytes 
     * de todos los archivos en el directorio.
     * @param disco Requiere el disco cargado en memoria
     */
    public void imprimirListaDirectorios(Disco disco){
        for (String i : this.listaDirectorios.keySet()) {
            System.out.println("Nombre archivo: " + i + " Index Block: " + this.listaDirectorios.get(i)
            + " File size: " + tamanoArchivo(i, disco));
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
    public FCB openFile(String nombreArchivo, Disco disco) {

        if(this.listaDirectorios.get(nombreArchivo) == null){
            System.out.println("\nEL ARCHIVO NO EXISTE O SU NOMBRE FUE ESCRITO INCORRECTAMENTE. \n");
            return new FCB();
        }else{
            System.out.println("\nEl archivo fue encontrado, ahora se abrirá en el FCB. \n");
            
            int bloqueIndice = this.listaDirectorios.get(nombreArchivo);  
            
            System.out.println("El bloque índice es: " + bloqueIndice);
            System.out.println("");
            
            this.bloque = disco.getBloquePorIndice(bloqueIndice-1);
            
            this.fcb = new FCB(tamanoArchivo(nombreArchivo, disco), this.bloque.getIndice(), nombreArchivo);//CAMBIAR EL 100 POR EL TAMAÑO REAL DEL ARCHIVO
            System.out.println("\nEl archivo fue abierto exitosamente. \n");
            System.out.println("\nindex block: "+ this.bloque.getIndice().toString());
            return this.fcb;
        }  
    }
    
    /**
     * Agrega un nuevo archivo en la lista de directorios.
     * @param nombre El nombre del nuevo archivo
     * @param indice El índice del archivo en la lista de directorios.
     */
    public void agregarArchivo(String nombre, int indice){
        this.listaDirectorios.put(nombre, indice);
        System.out.println("\nEl archivo fue creado exitosamente. \n");
    }
    
    /**
     * Calcula el tamaño del archivo en bytes.
     * @param nombre El nombre del archivo al que se le calculará el tamaño
     * @param disco El disco cargado en memoria
     * @return Un entero que representa el tamaño en bytes del archivo
     */
    public int tamanoArchivo(String nombre, Disco disco){
        
        int bloqueIndice = this.listaDirectorios.get(nombre);
        int tamano = 0;
        String contenido = "";
        
        ArrayList<Integer> listaIndex = disco.getBloquePorIndice(bloqueIndice).indice;
        
        for(int i=0; i<listaIndex.size(); i++){
            int valorIndex = listaIndex.get(i);
            contenido += disco.getBloquePorIndice(valorIndex).getPalabra();
        }
        
        System.out.println("\nEl contenido del archivo es: "+contenido+"\n");
        
        return contenido.length();
    }
}
