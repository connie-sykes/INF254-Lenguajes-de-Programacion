#include "Tierra.h"
#include "Bomba.h"
#include "Tablero.h"
#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>

void*** tablero;
int** tab_adyacente;
int dimension=0;
int any_treasure=0;
int total_treasure=0;
int remaining_treasure;

void IniciarTablero(int n){
/*
La funcion se encarga de crear el tablero necesario del juego, pidiendo
la memoria necesaria para cada celda.

_Parametros recibidos_
Int n

_Parametros entregados_
Ninguno, es una funcion void
*/
    n= dimension;

    tablero = (void***)malloc(n * sizeof(void**));
    tab_adyacente = (int**)malloc(n * sizeof(int*));
    for (int i = 0; i < n; i++) {
        tab_adyacente[i] = (int*)calloc(n, sizeof(int));
        (tablero)[i] = (void**)malloc(n * sizeof(void*));
        for (int j = 0; j < n; j++) {

            Tierra* tierra = (Tierra*)malloc(sizeof(Tierra));
            tierra->vida = (rand() % 3) + 1;
            tierra->es_tesoro = (rand() % 20) == 0 ? 1 : 0;
            if(tierra->es_tesoro ==1){ 
                any_treasure=1;
                total_treasure+=1;
            }
            tablero[i][j] = tierra;
        }
    }
    remaining_treasure = total_treasure; //Contador tesoros mostrados    
    

    return;
}

void PasarTurno(){
/*
Recorre la matriz adyacente (que cuenta con la informacion de la ubicacion de 
las bombas) y va descontando en 1 la celda 'contador_turnos' de cada bomba
del tablero.

_Parametros recibidos_
Ninguno

_Parametros entregados_
Ninguno, es una funcion void
*/

    for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            if (tab_adyacente[i][j] == 1){  //Si en el tab ad hay un 1, es bomba, debe ser explotado
                TryExplotar(i,j);
            }
        }
    }
    return;
}


void ColocarBomba(Bomba* b, int fila, int columna){ 
/*
Guarda en la matriz adyacente la ubicacion de la bomba y reemplaza el struct
tierra de la cerda original con uno bomba, traspasando la informacion de
tierra al campo 'tierra_debajo' de la nueva celda.

_Parametros recibidos_
Puntero struct Bomba b
int fila
int columna

_Parametros entregados_
Ninguno, es una funcion void
*/

    tab_adyacente[fila][columna] = 1; //hago un 1 en la tab_ad
    
    Tierra* t_aux = tablero[fila][columna]; //guardo referencia a tierra
    tablero[fila][columna]= b; //reemplazo por struct bomba
    b->tierra_debajo=t_aux; //tierra_debajo = tierra original
    //t_aux = NULL;
    return;
}


void MostrarTablero() {
/*
Recorre el tablero imprimiento la informacion de cada casilla, dependiendo
de si es bomba muestra una 'o', si es un tesoro sin tierra encima muestra '*'
y si es tierra muestra su vida respectiva

_Parametros recibidos_
Ninguno

_Parametros entregados_
Ninguno, es una funcion void
*/
    for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            if (tab_adyacente[i][j] == 1) { // Si es bomba
                if (j == dimension - 1) {
                    printf(" o ");
                } else {
                    printf(" o |");
                }
            } else { // Si no es bomba...
                Tierra* tierra = (Tierra*)tablero[i][j]; // Cast void* to Tierra*
                if (tierra->vida == 0 && tierra->es_tesoro == 1) { // Es tesoro?
                    
                    if (j == dimension - 1) {
                        printf(" * ");
                    } else {
                        printf(" * |");
                    }
                } else { // Entonces es tierra.
                    if (j == dimension - 1) {
                        printf(" %d ", tierra->vida);
                    } else {
                        printf(" %d |", tierra->vida);
                    }
                }
            }
        }printf("\n\n");    
    }
    return;
}



void MostrarBombas(){
/*
Recorre el tablero junto con la matriz adyacente al mismo tiempo imprimiendo
la informacion de las bombas presentes en el tablero. 
Del tablero imprime los turnos para explotar de la bomba, la forma de su explosion
y la vida de la tierra_debajo, mientras que de la matriz adyacente permite imprimir
la ubicacion de la bomba misma.

_Parametros recibidos_
Ninguno

_Parametros entregados_
Ninguno, es una funcion void
*/
    int are_bombs=0;
    for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            if (tab_adyacente[i][j] == 1){
                Bomba* bomba = (Bomba*)tablero[i][j]; //Casteo void* a Bomba*
                printf("_____________________________________\n\n");
                printf("Turnos para explotar: %d \n", bomba->contador_turnos);
                printf("Coordenada: %d %d \n", i, j);
                if(bomba->explotar == &ExplosionPunto){
                    printf("Forma explosion: ExplosionPunto \n");      
                }
                else if(bomba->explotar == &ExplosionX){
                    printf("Forma explosion: ExplosionX \n");                 
                }
                printf("Vida de Tierra Debajo: %d \n",bomba->tierra_debajo->vida);
                printf("_____________________________________\n");
                are_bombs=1;
            }
        }
    }
    if( !are_bombs){printf("~~~No hay bombas en el tablero :( ~~~\n\n");}
    return;
}

void VerTesoros(){ 
/*
Recorre el tablero imprimiendo el valor de la tierra en caso de que no tenga un
tesoro y un '*' en caso de que exista un tesoro bajo esa tierra, independiente
de la vida de esta.

_Parametros recibidos_
Ninguno

_Parametros entregados_
Ninguno, es una funcion void
*/
    for (int i = 0; i < dimension; i++) {

        for (int j = 0; j < dimension; j++) {
            if (tab_adyacente[i][j] == 1){ //Si es bomba...
                Bomba* bomba = (Bomba*)tablero[i][j]; //Casteo void* a Bomba*
                if (bomba->tierra_debajo->es_tesoro == 1){ //Es tesoro?

                    if (j == dimension - 1) {
                        printf(" * ");
                    } else {
                        printf(" * |");
                    }
                } else{ //No era tesoro, es bomba, imprimo o
                    if (j == dimension - 1) {
                        printf(" o ");
                    } else {
                        printf(" o |");
                    }

                }
            } else { //No era bomba, es tierra
                Tierra* tierra = (Tierra*)tablero[i][j]; // Casteo void* a Tierra*
                if (tierra->es_tesoro == 1) { // Es tesoro?
                    if ( j == dimension - 1) {

                        printf(" * ");
                    } else {
                        printf(" * |");
                    }
                } else { //No es tesoro, es tierra, imprimo vida
                    if ( j == dimension - 1) {
                        printf(" %d ", tierra->vida);
                    } else {
                        printf(" %d |", tierra->vida);
                    }
                }
            }
        } printf("\n\n");
    }
    return;
}

void BorrarTablero(){
/*
Recorre el tablero y la matriz adyacente liberando la memoria solicitada por
IniciarTablero(int n).

_Parametros recibidos_
Ninguno

_Parametros entregados_
Ninguno, es una funcion void
*/
    for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            free(tablero[i][j]);
        }
        free(tablero[i]);
        free(tab_adyacente[i]);
    }
    free(tablero);
    free(tab_adyacente); 



    return;
}