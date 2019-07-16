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
    ArrayList<Bloque> listaBloques = new ArrayList<Bloque>();
    String nombreArchivo;

    public FCB(int archivoSize, String nombreArchivo) {
        this.archivoSize = archivoSize;
        this.nombreArchivo = nombreArchivo;
    }

    public int getArchivoSize() {
        return archivoSize;
    }

    public void setArchivoSize(int archivoSize) {
        this.archivoSize = archivoSize;
    }

    public ArrayList<Bloque> getListaBloques() {
        return listaBloques;
    }

    public void setListaBloques(ArrayList<Bloque> listaBloques) {
        this.listaBloques = listaBloques;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    
}
