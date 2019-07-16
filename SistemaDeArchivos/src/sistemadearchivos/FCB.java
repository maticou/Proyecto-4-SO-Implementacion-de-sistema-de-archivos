/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.ArrayList;

/**
 *
 * @author mati_
 */
public class FCB {
    
    int archivoSize;
    ArrayList<Integer> listaBloques;
    String nombreArchivo;
    
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
}
