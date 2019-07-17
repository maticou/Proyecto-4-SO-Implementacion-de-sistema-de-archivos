/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
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
    
    public Bloque getBloquePorIndice(int bloqueIndice) {
        return bloques.get(bloqueIndice);
    }
    
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
    
    String guardarDirectorio(){
        String directorio = "";
        
        for (String i : this.directorio.listaDirectorios.keySet()) {
            directorio = directorio + i +"-"+this.directorio.listaDirectorios.get(i)+"/";
        }
        return directorio;
    }
    
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
