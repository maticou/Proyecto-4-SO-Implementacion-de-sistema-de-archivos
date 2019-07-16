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
 * @author mati_
 */
public class Disco {
        
    ArrayList<Bloque> bloques;
    private File archivoDeDisco;
    private int numSectores;

    public Disco() {
        this.bloques = new ArrayList<Bloque>();
        this.numSectores = 4;
        this.archivoDeDisco = new File("DISCO.txt");
    }
    
    void leerDisco(){
        Scanner lector;
        
        try{
            lector = new Scanner(this.archivoDeDisco);

            for (int i=0; i<numSectores && lector.hasNextLine(); i++) 
            {
                String linea = lector.nextLine();
                Bloque bloque = new Bloque(linea, i);
                this.bloques.add(bloque);

            }                        
            lector.close();
            System.out.println(bloques);
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
                pw.println("00000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "0000000000000000000000000000000000000000000"
                        + "00000000000000000000000000000000000000000");               
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

    public ArrayList<Bloque> getBloque() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }
   
}
