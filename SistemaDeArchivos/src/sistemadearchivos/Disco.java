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
    private File archivoDeDisco;
    private int numSectores;
    Directorio directorio;

    public Disco(int numSectores) {
        this.bloques = new ArrayList<Bloque>();
        this.numSectores = numSectores;
        this.archivoDeDisco = new File("DISCO.txt");
    }
    
    void leerDisco(){
        Scanner lector;
        
        try{
            lector = new Scanner(this.archivoDeDisco);

            for (int i=0; i<numSectores && lector.hasNextLine(); i++) 
            {
                if(i == 0){
                    String linea = lector.nextLine();
                    String [] array = linea.split("/"); 
                
                    for(String s: array){
                        this.directorio.agregarDirectorioDisco(s);
                    }
                }
                else{
                    String linea = lector.nextLine();
                    Bloque bloque = new Bloque(linea, i);
                    this.bloques.add(bloque);
                }
            }                        
            lector.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
    void guardarDatos(){
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
                    pw.println(this.bloques.get(i).getPalabra()); 
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
    
    void formatearDisco(){
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
                    pw.println(" "); 
                }              
            }
            this.directorio.eliminarDirectorio();
            
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
    }
    
    String guardarDirectorio(){
        String directorio = "";
        
        for (String i : this.directorio.listaDirectorios.keySet()) {
            directorio = directorio + i +"-"+this.directorio.listaDirectorios.get(i)+"/";
        }
        return directorio;
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
