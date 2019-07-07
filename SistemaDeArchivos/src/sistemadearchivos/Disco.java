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
public class Disco {
        
    ArrayList<Directorio> directorios;

    public Disco(ArrayList<Directorio> directorios) {
        this.directorios = directorios;
    }

    public ArrayList<Directorio> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(ArrayList<Directorio> directorios) {
        this.directorios = directorios;
    }
   
}
