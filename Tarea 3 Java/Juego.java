import java.io.*;
//import java.net.*;
//import java.nio.file.*;

import java.util.*;
public class Juego {
    

    public static void main(String[] args) throws IOException{
        Integer turnos = 1;
        Integer pos;
        boolean victory=false;
        Pieza key = new Pieza(1);
        Integer total_piezas = key.getPiezas();
        //generar mapa arreglo 
        Zona[] map = new Zona[11];
        map[0] = new Pieza(50);
        map[1] = new Enemigo(130,20,25);
        map[2] = new Enemigo(50,10,15);
        map[3] = new Pildora(25);
        map[4] = new Muralla(50);
        map[5] = new Pieza(100);
        map[6] = new Enemigo(45,8,10);
        map[7] = new Pieza(35);
        map[8] = new Pildora(15);
        map[9] = new Enemigo(75,15,20);
        map[10] = new Muralla(150);

        //Inicializo pikinims
        Cyan C = new Cyan();
        Magenta M = new Magenta();
        Amarillo A = new Amarillo();
        
        //Inicializo posición
        pos = 5;

        //flujo de juego lol
        Integer total_pikinim = C.getCantidad() + M.getCantidad() + A.getCantidad();
        Scanner lector = new Scanner(System.in);
        String s = System.getProperty("user.dir");
        System.out.println(s);

        File f = new File(s+"/Start.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        br.lines().forEach(System.out::println);
        br.close();

        System.out.println("Choqué con un asteroide en un viaje de rutina y ahora me encuentro solo en un planeta extraño. Mi nave está destrozada y no sé a dónde fueron a parar sus piezas... Donde estaran?");
        


        
        while(turnos<=30 && total_pikinim!=0 && !victory){
            if(key.getPiezas()==3){
                victory=true;
            } else{
            total_piezas = key.getPiezas();
            total_pikinim = C.getCantidad() + M.getCantidad() + A.getCantidad();
            System.out.println("_______________________________________________________________");
            System.out.println();
            System.out.println("                    Turno (Hora)  n° "+ turnos+"");
            System.out.println("_______________________________________________________________");
            System.out.println("*Pikinims en equipo: " + "Cyan="+C.getCantidad()+" Magenta="+M.getCantidad()+" Amarillo="+A.getCantidad());
            System.out.println("*Posicion actual: "+pos);
            System.out.println("*Piezas recolectadas: "+total_piezas);
            //boolean is_Muralla;       RESTRINGIR MOVIMIENTO SI HAY MURALLA
            System.out.println("*¿A dónde irá el capitán?");
            System.out.print("\t 1. Ir a la izquierda  "); 
            if(pos-1<0){
                map[10].QueHay();
            }else{
                map[pos-1].QueHay();
            }
            System.out.print("\t 2. Quedarse            "); 
            map[pos].QueHay();
            System.out.print("\t 3. Ir a la derecha    "); 
            if(pos+1>10){
                map[0].QueHay();
            }else{
                map[pos+1].QueHay();
            }
            
            System.out.print("Respuesta: ");
            Integer input = lector.nextInt();
            System.out.println();
            switch(input){
                case 1:
                    pos-=1;
                    if(pos<0){
                        pos=10;
                    }
                    map[pos].Interactuar(C,M,A);
                    //si es muro, mov hacia muralla esta prohibido
                    break;
                case 2:
                    map[pos].Interactuar(C,M,A);
                    break;
                case 3:
                    pos+=1;
                    if(pos>10){
                        pos=0;
                    }
                    map[pos].Interactuar(C,M,A);
                    break;
            }
            turnos +=1;
            }
        }
        if(victory){
            System.out.println();
            System.out.println("~El capitán Lomiar junto a los Pikinims recuperaron las 3 piezas necesarias para reparar su nave~");
            System.out.println("Felicidades, has ganado.");
        }else if(turnos>30){
            System.out.println();
            System.out.println("~Se han acabado los soportes vitales del Capitan Lomiar... ~");
            System.out.println("Has perdido.");
        }else if(total_pikinim==0){
            System.out.println();
            System.out.println("~Los pikinims no resistieron ante el ataque de los enemigos...~");
            System.out.println("Has perdido.");
        }

        lector.close(); 
        return;
    }

}
