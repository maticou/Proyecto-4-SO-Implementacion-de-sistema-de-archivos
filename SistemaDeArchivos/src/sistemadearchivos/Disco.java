/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *Clase Disco, es la encargada de representar un volumen en un disco duro.
 * 
 * @author MatiasParra
 * @author ManuelGonzalez
 */
public class Disco {
        
    ArrayList<Bloque> bloques;
    private File archivoDeDisco = new File("DISCO.txt");
    private int numSectores;
    Directorio directorio;

    public Disco(int numSectores) {
        this.bloques = new ArrayList<Bloque>();
        this.numSectores = numSectores;
        this.archivoDeDisco = new File("DISCO.txt");
    }
    
    public Disco(){
        this.bloques = new ArrayList<Bloque>();
        this.numSectores = 0;
        this.archivoDeDisco = new File("DISCO.txt");
    }
    
    /**
     * Método encargado de leer el disco (que en nuestro caso es un archivo .txt) 
     * y cargarlo al sistema.
     */
    void leerDisco(){
        this.numSectores = this.obtenerNumeroBloques();
        Scanner lector;
        
        try{
            lector = new Scanner(this.archivoDeDisco);

            for (int i=0; i<numSectores && lector.hasNextLine(); i++) 
            {
                if(i == 0){
                    //Lee desde el archivo los datos del directorio y los almacena
                    //en la clase directorio.
                    String linea = lector.nextLine();
                    String [] array = linea.split("/"); 
                
                    for(String s: array){
                        this.directorio.agregarDirectorioDisco(s);
                    }
                }
                else{
                    //Lee los datos de los sectores y los almacena en la lista de bloques
                    String linea = lector.nextLine();

                    if(linea.charAt(0) == '['){
                        this.leerBloqueIndice(i, linea);
                    }
                    else{
                        Bloque bloque = new Bloque(linea, i);
                        bloque.setOcupado(false);
                        this.bloques.add(bloque);
                    }
                }
            }                        
            lector.close();
            
            this.actualizarEstadoBloques();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Cuenta los bloques que hay en el disco para saber cuantas veces 
     * tiene que iterar al crear los bloques al momento de cargar el disco.
     * 
     * @return Un entero con la cantidad de bloques.
     */
    int obtenerNumeroBloques(){
        int num = 0;
        Scanner lector;
        
        try{
            lector = new Scanner(this.archivoDeDisco);

            while(lector.hasNextLine() == true) 
            {
                num++;
                lector.nextLine();
            }                        
            lector.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        return num;
    }
    
    /**
     * Método que verifica los bloques ocupados en disco y los modifica a ocupados en memoria.
     */
    void actualizarEstadoBloques(){
        for(Bloque bloque: this.bloques){
            if(bloque.indice.size() != 0){
                for(Integer i : bloque.indice){
                    if(i == -1){
                        break;
                    }
                    else{
                        this.bloques.get(i-1).setOcupado(true);
                    }
                }
            }
        }
    }
    
    /**
     * Se encarga de guardar los datos y cambios hechos al disco en memoria al disco.
     */
    void guardarDatos(){
        System.out.println("num de bloques: "+ this.bloques.size());
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            
            for(int i=0; i<this.numSectores; i++)
            {
                if(i == 0){
                    pw.println(this.guardarDirectorio());
                }
                else{
                    pw.println(this.bloques.get(i-1).getPalabra()); 
                }              
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {  
                if (null != fichero)
                   fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    /**
     * Obtiene el bloque especificado por un índice.
     * 
     * @param bloqueIndice Es el índice del bloque que quiero obtener.
     * @return Un bloque con todos los datos que incluye.
     */
    public Bloque getBloquePorIndice(int bloqueIndice) {
        return bloques.get(bloqueIndice);
    }
    
    /**
     * Sobre escribe en el disco con espacios en blanco, dejándolo limpio y listo 
     * para ser creado nuevamente con nuevas características.
     * 
     * @param numBloques Cantidad de bloques que se quiere tener en el nuevo disco.
     */
    void formatearDisco(int numBloques){
        this.numSectores = numBloques;
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            
            for(int i=0; i<this.numSectores; i++)
            {
                if(i == 0){
                    pw.println(" ");
                }
                else{
                    pw.println(" "); 
                }              
            }
            this.directorio.eliminarDirectorio();
            this.bloques.clear();
            System.out.println("Se ha formateado el disco");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {  
                if (null != fichero){
                    fichero.close();
                }
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
        this.leerDisco();
    }
    
    /**
     * Guarda el directorio en el disco, bloque 0.
     * @return el nuevo directorio.
     */
    String guardarDirectorio(){
        String directorio = "";
        
        for (String i : this.directorio.listaDirectorios.keySet()) {
            directorio = directorio + i +"-"+this.directorio.listaDirectorios.get(i)+"/";
        }
        return directorio;
    }
    
    /**
     * Lee el bloque que está en el índice dado en el disco.
     * 
     * @param idBloque El identificador de un bloque
     * @param indice La posición del bloque en el disco.
     */
    void leerBloqueIndice(int idBloque, String indice){
        Bloque bloque = new Bloque();
        bloque.setPalabra(indice);
        bloque.setIdentificador(idBloque);
        
        indice = indice.substring(1, indice.length()-1);
        String [] array = indice.split("->");
        
        for(String s: array){
            int index = Integer.parseInt(s);
            bloque.indice.add(index);
        }
        
        bloque.setOcupado(true);
        this.bloques.add(bloque);
        
        System.out.println(bloque.indice.toString());
    }
    
    void eliminarArchivo(String nombre){
        int index = this.directorio.listaDirectorios.get(nombre);
        
        ArrayList<Integer> indices = this.getBloquePorIndice(index-1).getIndice();
        
        for(Integer i : indices){
            if(i != -1){
                this.bloques.get(i-1).liberarBloque();
            }
            else{
                break;
            }
        }
        
        this.bloques.get(index-1).liberarBloque();
        this.directorio.eliminarArchivo(nombre);
        this.guardarDatos();
        System.out.println("Se ha eliminado correctamente el archivo.");
    }

    public ArrayList<Bloque> getBloque() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }
    
    public void setDirectorio(Directorio directorio){
        this.directorio = directorio;
    }
   
}
