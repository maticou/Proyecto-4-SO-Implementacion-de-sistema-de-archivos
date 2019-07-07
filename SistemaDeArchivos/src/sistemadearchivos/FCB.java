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
public class FCB {
    
    int archivoSize;
    int posicionInicial;
    int posicionFinal;
    String nombreDirectorio;
    String fechaCreacion;

    public FCB(int archivoSize, int posicionInicial, int posicionFinal, String nombreDirectorio, String fechaCreacion) {
        this.archivoSize = archivoSize;
        this.posicionInicial = posicionInicial;
        this.posicionFinal = posicionFinal;
        this.nombreDirectorio = nombreDirectorio;
        this.fechaCreacion = fechaCreacion;
    }

    public int getArchivoSize() {
        return archivoSize;
    }

    public void setArchivoSize(int archivoSize) {
        this.archivoSize = archivoSize;
    }

    public int getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(int posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public int getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(int posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public String getNombreDirectorio() {
        return nombreDirectorio;
    }

    public void setNombreDirectorio(String nombreDirectorio) {
        this.nombreDirectorio = nombreDirectorio;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   
    
}
