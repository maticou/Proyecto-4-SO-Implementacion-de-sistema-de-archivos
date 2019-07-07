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
public class Directorio {
    
    String archivoNombre;    
    ArrayList<Bloque> bloques;

    public Directorio(String archivoNombre, ArrayList<Bloque> bloques) {
        this.archivoNombre = archivoNombre;
        this.bloques = bloques;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }
 
}
