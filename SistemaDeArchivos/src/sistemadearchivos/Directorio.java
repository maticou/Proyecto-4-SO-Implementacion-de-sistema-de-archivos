/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.HashMap;

/**
 *
 * @author mati_
 */
public class Directorio {
    
    HashMap<String, Integer> listaDirectorios;

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
    
}
