/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

/**
 *
 * @author mati_
 */
public class Bloque {
    
    int size = 512;
    String name;
    int identificador;

    public Bloque(String name, int identificador) {
        this.name = name;
        this.identificador = identificador;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
       
    
}
