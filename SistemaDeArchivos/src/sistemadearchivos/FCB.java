/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.ArrayList;

/**
 *Clase que contiene la información del archivo abierto.
 * 
 * Como su nombre lo dice FCB(file counter block) es el encargado de mostrar al sistema 
 * que archivos están abiertos y listos para ser usados.
 * 
 * @author MatiasParra
 * @author ManuelGonzalez
 */
public class FCB {
    
    int archivoSize;
    ArrayList<Integer> listaBloques;
    String nombreArchivo;
    Disco disco = new Disco();
    Bloque bloque = new Bloque();
    
    public FCB() {
        
    }

    public FCB(int archivoSize, ArrayList<Integer> listaBloques, String nombreArchivo) {
        this.archivoSize = archivoSize;
        this.listaBloques = listaBloques;
        this.nombreArchivo = nombreArchivo;
    }

    public int getArchivoSize() {
        return archivoSize;
    }

    public void setArchivoSize(int archivoSize) {
        this.archivoSize = archivoSize;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ArrayList<Integer> getListaBloques() {
        return listaBloques;
    }

    public void setListaBloques(ArrayList<Integer> listaBloques) {
        this.listaBloques = listaBloques;
    }  
    
    /**
     * Método imprimirContenidoArchivo muestra el contenido de un archivo abierto.
     * 
     * Si el archivo no se abrió previamente y almacenó en el FCB, entonces no mostrará contenido.
     *     
     */
    public void imprimirContenidoArchivo(){
        
        if(this.getNombreArchivo() == null){
            System.out.println("\nNo hay archivo abierto, debe abrir uno primero. \n");
        }
        else{
            System.out.println("\nEl contenido del archivo " + this.getNombreArchivo() + " es:  \n");
        
            for(int i=0; i<this.listaBloques.size(); i++){
                this.bloque = this.disco.getBloquePorIndice(this.listaBloques.get(i));
                System.out.println(this.bloque.getPalabra() + "\n");
            }
        }        
    }
}
