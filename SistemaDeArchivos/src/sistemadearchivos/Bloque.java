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
    String palabra;
    private byte[] contenido;
    int identificador;

    public Bloque(String palabra, int identificador) {
        this.palabra = palabra;
        this.identificador = identificador;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return palabra;
    }

    public void setName(String palabra) {
        this.palabra = palabra;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
       
    
}
