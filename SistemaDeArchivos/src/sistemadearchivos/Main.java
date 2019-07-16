/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *Clase Main que se encarga de mostrar el menú de opciones e inicializar las funcionalidades.
 * 
 * @author MatiasParra
 * @author ManuelGonzalez
 */
public class Main {

    /**
     * Método Main que se encarga de mostrar el menú de opciones e inicializar las funcionalidades.
     * 
     * Utiliza un simple scanner para leer la opción del usuario, después verrifica con un switch-
     * Case la opción elegida. Si la opción es incorrecta, el mení se dezplegará de nuevo. Para 
     * salir, escriba la opción 9.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Directorio directorio = new Directorio();
        Disco disco =  new Disco();
        disco.setDirectorio(directorio);
        FCB fcb = new FCB();
        
        disco.leerDisco();
        
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

            System.out.print("\nIngrese qué tipo de acción desea hacer: ");
            if(in.hasNextInt()){

                int opcion = in.nextInt();  

                if(opcion>0 && opcion<10){
                   switch(opcion){
                        case 1: System.out.print("Ingrese el numero de sectores que tendra el nuevo disco \n");
                                int numBloques = in.nextInt();
                                disco.formatearDisco(numBloques);
                                break;
                        case 2: System.out.print("Entró a create\n");
                                break;
                        case 3: System.out.print("Entró a remove\n");
                                break;
                        case 4: abrirArchivo(directorio, fcb, disco);
                                break;
                        case 5: System.out.print("Entró a read at\n");
                                break;
                        case 6: System.out.print("Entró a write at\n");
                                break;
                        case 7: fcb.setDisco(disco);
                                fcb.imprimirContenidoArchivo();
                                break;
                        case 8: System.out.print("Entró a list\n");
                                break;
                        case 9: exit(0);
                                break;
                    } 
                    System.out.println(""); 
                }else{
                    System.out.println("\nINGRESE UN NÚMERO VÁLIDO!!!\n");
                }
            }else{
                System.out.println("\nINGRESE UN NÚMERO VÁLIDO!!!\n");
            }
        }    
    }
    
    /**
     * Método abrirArchivo pregunta al usuraio qué archivo desea abrir.
     * 
     * Se pregunta por el nombre del archivo, se busca en el FCB si ya está abierto, si 
     * no lo está, entonces va al directorio y lo abre a través de la función openFile.
     *     
     */
    static public void abrirArchivo(Directorio directorio, FCB fcb, Disco disco) {
        Scanner in = new Scanner(System.in);        
        System.out.print("Ingrese el nombre del archivo que quiere abrir: ");
        
        if(in.hasNextLine()){
            
            if(fcb.getNombreArchivo() != null && fcb.getNombreArchivo().equals(in.toString())){
                System.out.println("\nEl archivo está abierto.");  
            }else{
                System.out.println("\nEl archivo no está abierto. Se buscará en el directorio");
                
                directorio.openFile(in.toString(), disco);
            }  
        }else{
            System.out.println("\nINGRESE UN NOMBRE VÁLIDO!!!\n");
        }
    }
}
