/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author mati_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        while(true){
            Scanner in = new Scanner(System.in);        
            System.out.print("Bienvenido al sistema de archivos, estas son las acciones posibles a realizar: \n");
            System.out.print("\n1.- Format \n");
            System.out.print("2.- Create \n");
            System.out.print("3.- Remove \n");
            System.out.print("4.- Open \n");
            System.out.print("5.- Read at \n");
            System.out.print("6.- Write at \n");
            System.out.print("7.- Print file \n");
            System.out.print("8.- List \n");
            System.out.print("9.- Salir \n");
            System.out.print("\n");
            System.out.print("Ingrese qué tipo de simulación desea ver: ");
            if(in.hasNextInt()){

                int opcion = in.nextInt();  

                if(opcion>0 && opcion<10){
                   switch(opcion){
                        case 1: Disco disco =  new Disco();
                                disco.leerDisco();
                                break;
                        case 2: System.out.print("Entró a create\n");
                                break;
                        case 3: System.out.print("Entró a remove\n");
                                break;
                        case 4: System.out.print("Entró a open\n");
                                break;
                        case 5: System.out.print("Entró a read at\n");
                                break;
                        case 6: System.out.print("Entró a write at\n");
                                break;
                        case 7: System.out.print("Entró a print file\n");
                                break;
                        case 8: System.out.print("Entró a list\n");
                                break;
                        case 9: exit(0);
                                break;
                    } 
                    System.out.println(""); 
                }else{
                    System.out.println("");
                    System.out.println("INGRESE UN NÚMERO VÁLIDO!!!");
                    System.out.println("");
                }
            }else{
                System.out.println("");
                System.out.println("INGRESE UN NÚMERO VÁLIDO!!!");
                System.out.println("");
            }
        }
    
    }
}
