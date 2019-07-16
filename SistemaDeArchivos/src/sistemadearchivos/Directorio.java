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
    
    HashMap<String, Integer> listaDirectorios = new HashMap<String, Integer>();

    public Directorio(HashMap<String, Integer> listaDirectorios) {
        this.listaDirectorios = listaDirectorios;
    }

    public HashMap<String, Integer> getListaDirectorios() {
        return listaDirectorios;
    }

    public void setListaDirectorios(HashMap<String, Integer> listaDirectorios) {
        this.listaDirectorios = listaDirectorios;
    }
    
}
