/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadearchivos;

import static java.lang.System.exit;
import java.util.ArrayList;
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
        int bloquesLibres = 0;
        
        for (int a=0; a<disco.getBloque().size(); a++){
            if(!disco.getBloquePorIndice(a).isOcupado()){
                bloquesLibres++;
            }
        }

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
                        case 1: System.out.print("Ingrese el número de sectores que tendra el nuevo disco \n");
                                int numBloques = in.nextInt();
                                if(numBloques < 129 && numBloques > 14){
                                    disco.formatearDisco(numBloques);
                                    break;
                                }
                                else{
                                    System.out.print("Ingrese un número de sectores que sea mayor a 14 y menor a 129 \n");
                                    break;
                                }
                        case 2: crearArchivo(directorio, disco, bloquesLibres);
                                break;
                        case 3: System.out.print("Ingrese el nombre del archivo que desea eliminar: ");
                                Scanner nom = new Scanner(System.in);
                                if(nom.hasNextLine()){
                                    String nombreArchivo = nom.nextLine();                                    
                                    disco.eliminarArchivo(nombreArchivo);
                                    break;
                                }
                                else{
                                    System.out.print("\nINGRESE UN NOMBRE VÁLIDO!!!\n");
                                    break;
                                }
                                
                        case 4: fcb = abrirArchivo(directorio, fcb, disco);
                                break;
                        case 5: System.out.print("Ingrese el numero de sectores que desea leer: \n");
                                int indiceArrchivo = in.nextInt();
                                fcb.setDisco(disco);
                                fcb.imprimirContenidoArchivoHasta(indiceArrchivo);
                                break;
                        case 6: System.out.print("Ingrese la posicion en la que se insertará el texto: \n");
                                int indice = in.nextInt();
                                fcb.setDisco(disco);
                                
                                System.out.print("Ingrese el texto que se va a incertar: \n");
                                Scanner nomnom = new Scanner(System.in);
                                if(nomnom.hasNextLine()){
                                    String texto = nomnom.nextLine();                                    
                                    fcb.imprimirEn(indice, texto);
                                    break;
                                }
                                else{
                                    System.out.print("\nDebe ingresar un texto!!!\n");
                                    break;
                                }
                        case 7: fcb.setDisco(disco);
                                fcb.imprimirContenidoArchivo();
                                break;
                        case 8: directorio.imprimirListaDirectorios(disco);
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
    static public FCB abrirArchivo(Directorio directorio, FCB fcb, Disco disco) {
        Scanner in = new Scanner(System.in);        
        System.out.print("Ingrese el nombre del archivo que quiere abrir: ");
        
        if(in.hasNextLine()){
            
            String nombre = in.nextLine();
            
            if(fcb.getNombreArchivo() != null && fcb.getNombreArchivo().equals(nombre)){
                System.out.println("\nEl archivo está abierto."); 
                return fcb;
            }else{
                System.out.println("\nEl archivo no está abierto. Se buscará en el directorio");                
                fcb = directorio.openFile(nombre, disco);
                return fcb;
            }  
        }else{
            System.out.println("\nINGRESE UN NOMBRE VÁLIDO!!!\n");
            return new FCB();
        }
    }
    
    
    /**
     * Método crearArchivo pregunta al usuraio qué archivo desea crear.
     * 
     * Se pregunta por el nombre del archivo, y el tamaño de este en bytes. Se busca 
     * si hay bloques disponibles en la cantidad suficiente para que concuerde con el tamaño ingresado 
     * por el usuario más un bloque extra por el bloque índice.
     * 
     * @param directorio Objeto de tipo directorio, en donde se tendrán los nombres de los archivos y su bloque índice.
     * @param disco Objeto de tipo disco, el cual representa la memoria secundaria del sistema y servirá para ver los bloques vacíos.
     * @param bloquesLibres Objeto entero que muestra cuantos bloques vacíos existen en el sistema.
     */
    static public void crearArchivo(Directorio directorio, Disco disco, int bloquesLibres) {
        Scanner in = new Scanner(System.in);        
        System.out.print("Ingrese el nombre del archivo que quiere crear: ");
        
        if(in.hasNextLine()){
            
            String nombre = in.nextLine();
            
            if(nombre.length()<9 && nombre.length()>0){
                Scanner on = new Scanner(System.in);        
                System.out.print("Ingrese el tamaño del archivo en bytes: ");

                if(on.hasNextInt()){

                    int size = on.nextInt();
                    int caract_totales = size;
                    //String contenido = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ";
                    String contenido = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
                    int resto =(size%512);
                    int cantidadBloquesNecesarios = 0;
                    int contador = 1;
                    ArrayList<Integer> bloquesIndices = new ArrayList<Integer>();
                    if(resto == 0){
                        cantidadBloquesNecesarios = ((size/512)+1);
                    }
                    else{
                        cantidadBloquesNecesarios = ((size/512)+2);               
                    }                    
                    String palabra = "[";
                    if(bloquesLibres > cantidadBloquesNecesarios){                    
                        for (int a=0; a<disco.getBloque().size(); a++){
                            if(!disco.getBloquePorIndice(a).isOcupado()){
                                if(contador < cantidadBloquesNecesarios){
                                    int aux = a+1;                                    
                                    bloquesIndices.add(a+1);
                                    palabra = palabra + aux + "->";
                                    
                                    if(caract_totales > 512){
                                        disco.getBloquePorIndice(a).setPalabra(contenido);
                                    }
                                    else{
                                        String aux_contenido = contenido.substring(0, caract_totales);
                                        disco.getBloquePorIndice(a).setPalabra(aux_contenido);
                                    }
                                
                                    caract_totales = obtenerCaracteres(caract_totales);

                                    disco.getBloquePorIndice(a).setOcupado(true);
                                    contador++;
                                }
                                else{
                                    disco.getBloquePorIndice(a).setIndice(bloquesIndices);
                                    disco.getBloquePorIndice(a).setOcupado(true);  
                                    disco.getBloquePorIndice(a).setPalabra(palabra+"-1]");
                                    directorio.agregarArchivo(nombre, a+1);
                                    disco.guardarDatos();
                                    break;
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("\nNO HAY ESPACIO SUFICIENTE EN EL DISCO PARA ÉSTE ARCHIVO\n");
                    }
                }
                else{
                    System.out.println("\nINGRESE UN NÚMERO VÁLIDO!!!\n");
                }
            }  
            else{
                System.out.println("\nINGRESE UN NOMBRE DE MÁXIMO 8 CARACTERES Y MÍNIMO 1\n");
            }
        }
        else{
            System.out.println("\nINGRESE UN NOMBRE VÁLIDO\n");
        }
    }
    
    static int obtenerCaracteres(int caracteres){
        int aux = 0;
        
        if(caracteres < 512){
            aux = caracteres;
        }
        else{
            aux = caracteres - 512;
        }
        return aux;
    }
}
