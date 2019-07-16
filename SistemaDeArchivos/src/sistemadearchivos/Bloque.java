/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.util.ArrayList;

/**
 *
 * @author MatiasParra
 * @author ManuelGonzalez
 */
public class Bloque {
    
    int size = 512;
    String palabra;
    private byte[] contenido;
    int identificador;
    ArrayList<Integer> indice;
    boolean ocupado = false;
    
    public Bloque() {
        this.indice = new ArrayList<Integer>();
    }
    
    public Bloque(String palabra, int identificador) {
        this.palabra = palabra;
        this.identificador = identificador;
    }

    public Bloque(String palabra, byte[] contenido, int identificador, ArrayList<Integer> indice) {
        this.palabra = palabra;
        this.contenido = contenido;
        this.identificador = identificador;
        this.indice = indice;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Integer> getIndice() {
        return indice;
    }

    public void setIndice(ArrayList<Integer> indice) {
        this.indice = indice;
    }    

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }        
    
}
