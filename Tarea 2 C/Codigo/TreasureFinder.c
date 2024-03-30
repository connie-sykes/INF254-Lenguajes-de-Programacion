#include <stdlib.h>
#include <time.h>
#include "Tierra.h"
#include "Bomba.h"
#include "Tablero.h"
#include <stdio.h>


int main(int argc, char const *argv[]){
    int win=0;
    int turno=1;
    int input_tab=0,input_row,input_col,next_turn=1,input_bomb,bomb_form;

    srand(time(NULL));printf("\n");
    printf("¡Bienvenido a TreasureFinder! \n");
    printf("Indique el tamaño del tablero a jugar: \n");
    printf("1.7x7   2.10x10   3.12x2\n");
    scanf("%d", &input_tab);printf("\n");
    //Seleccion de tablero
    if (input_tab == 1){
        dimension=7;
    } else if (input_tab ==2){
        dimension=10;
    } else if (input_tab ==3){
        dimension = 12;
    }
    else{
        scanf("%d", &input_tab);printf("\n");
    }

    printf("Empezando juego...\n");

    IniciarTablero(dimension);

    while(any_treasure == 0){ //Aseguro min. 1 tesoro
        BorrarTablero();
        IniciarTablero(dimension);
        printf("____Se creo de nuevo el tablero por ausencia de tesoros_____\n");
    }

    printf(" ¡listo! \n\n");
    Bomba* bomb = NULL;

    while(win == 0){

        //printf("Tesoros por encontrar: %d\n\n",remaining_treasure);

        while(next_turn==1 && win !=1){
            PasarTurno();
            
            if(remaining_treasure==0){
                next_turn=0;
                win = 1;
                printf("~VICTORY~\n\n");
                MostrarTablero();
            }
            else{
            printf("Tablero (Turno %d)\n", turno);
            MostrarTablero();
            printf("Seleccione una accion:\n\n");
            printf("1.Colocar Bomba  2.Mostrar Bombas  3.Mostrar Tesoros\n\n");
            printf("Escoja una opcion: "); scanf("%d",&input_bomb); printf("\n");
            

            if (input_bomb == 1){ //Colocar bomba
                printf("Indique coordenadas de la bomba\n\n");
                printf("Fila: "); scanf("%d",&input_row);
                printf("Columna: "); scanf("%d",&input_col); printf("\n");
                printf("Indique forma en que explota la bomba\n\n");
                printf("1.Punto  2.X \n\n");
                printf("Su input: "); scanf("%d",&bomb_form); printf("\n\n"); //Forma explosion


                //Bomba* bomb = (Bomba*)malloc(sizeof(Bomba));
                bomb = (Bomba*)malloc(sizeof(Bomba));
                if (bomb_form == 1) {
                    bomb->contador_turnos = 1;
                    bomb->explotar = &ExplosionPunto;
                    bomb->tierra_debajo = NULL;       
                }
                else if (bomb_form == 2) {
                    bomb->contador_turnos = 3;
                    bomb->explotar = &ExplosionX;
                    bomb->tierra_debajo = NULL; 
                }

                ColocarBomba(bomb, input_row-1, input_col-1);

                next_turn = 1;
                turno += 1;

                

            } else if (input_bomb ==2){ //Mostrar Bombas
                MostrarBombas();
            } else if (input_bomb ==3){ //Mostrar tesoros
                VerTesoros();
            } 
            
            }
          
        }
             
    }
    
    printf("         Felicidades! \n Har Tabarba ha conseguido sus tesoros, ahora es un pirata feliz\n");
    BorrarTablero();

    
    return 0;
}
