#include "Bomba.h"
#include "Tierra.h"
#include "Tablero.h"
#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>

void TryExplotar(int fila, int columna){ 
/*
Recorre el tablero descontando en 1 el contador_turnos de cada bomba que encuentra,
en caso de que este contador llegue a 0 en alguna bomba, se llama a su respectiva
explosion.

_Parametros recibidos_
int fila
int columna

_Parametros entregados_
Ninguno, es una funcion void
*/
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    bomba->contador_turnos -= 1;
    if (bomba->contador_turnos == 0){
        bomba->explotar(fila,columna);
    }
    return;
}

void BorrarBomba(int fila, int columna){
/*
Reasigna el valor de la casilla en tablero con la informacion del struct
'tierra_debajo', volviendo a ser una casilla de tierra


_Parametros recibidos_
int fila
int columna

_Parametros entregados_
Ninguno, es una funcion void
*/
    Bomba* bomba = (Bomba*)tablero[fila][columna]; 
    tablero[fila][columna] = bomba->tierra_debajo; //reemplazo
    free(bomba); //borro bomba de heap

    tab_adyacente[fila][columna] = 0;
    return;
}

void ExplosionPunto(int fila, int columna){    
/*
En la casilla fila,columna del tablero, siendo de tipo bomba, disminuye la vida de 
la tierra bajo esta en 3, para luego borrar la bomba y reescribir el valor respectivo
en la matriz adyacente, pasando de 1 a 0 por la explosion.
Tambien lleva un conteo de los momentos en que una explosion revela un tesoro.

_Parametros recibidos_
int fila
int columna

_Parametros entregados_
Ninguno, es una funcion void
*/
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    bomba->tierra_debajo->vida -= 3;
    if(bomba->tierra_debajo->vida < 0){
        bomba->tierra_debajo->vida=0;
    } 
    if(bomba->tierra_debajo->vida == 0 && bomba->tierra_debajo->es_tesoro==1){
        remaining_treasure -=1; 
    }

    BorrarBomba(fila,columna);
    tab_adyacente[fila][columna] = 0;
    return;
}

void ExplosionX(int fila, int columna){
    /*
En la casilla fila,columna del tablero, siendo de tipo bomba, disminuye la vida de 
la tierra bajo esta en 1 y las de sus esquinas en 1 tambien, para luego borrar la bomba 
y reescribir el valor respectivo en la matriz adyacente, pasando de 1 a 0 por la explosion.
Tambien lleva un conteo de los momentos en que una explosion revela un tesoro.

_Parametros recibidos_
int fila
int columna

_Parametros entregados_
Ninguno, es una funcion void
*/
    int min_row = fila-1;
    int min_col = columna-1;
    int add_row = fila+1;
    int add_col = columna+1;
    int dim = dimension-1; //supon dimension = 7, en matrix =6
    if (add_row > dim){ //si la fila es mayor a...7
        add_row = add_row-dim;
    }
    if (add_col > dim){ //si la columna es mayor a...7
        add_col = add_col-dim;
    }
    if (min_row > dim){ //si la fila es menor a...0
        min_row = min_row+dim;
    }
    if (min_col > dim){ //si la columna es menor a...0
        min_col = min_col+dim;
    }

    //Luego de arreglar los indices, bajo la vida de las tierras

    //CENTRO
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    bomba->tierra_debajo->vida -= 1;
    
    if(bomba->tierra_debajo->vida < 0 ){ //If neg turn to 0
        bomba->tierra_debajo->vida=0;
    }
    else if(bomba->tierra_debajo->vida==0 && bomba->tierra_debajo->es_tesoro ==1){
        remaining_treasure -=1;
    }

    BorrarBomba(fila,columna);
    tab_adyacente[fila][columna] = 0;

    //DIAG SUP DER
    if (tab_adyacente[min_row][add_col]==1){
        Bomba* bomba = (Bomba*)tablero[min_row][add_col]; 
        bomba->tierra_debajo->vida -=1;
    } else {
        Tierra* tierra = (Tierra*)tablero[min_row][add_col];
        tierra->vida -=1;
        if(tierra->vida < 0 ){ //If neg turn to 0
            tierra->vida=0;
        }
        else if(tierra->vida ==0 && tierra->es_tesoro ==1){
            remaining_treasure -=1;
        }
    }

    //DIAG SUP IZQ
    if (tab_adyacente[min_row][min_col]==1){
        Bomba* bomba = (Bomba*)tablero[min_row][min_col]; 
        bomba->tierra_debajo -=1;
    } else {
        Tierra* tierra = (Tierra*)tablero[min_row][min_col]; 
        tierra->vida -=1;
        if(tierra->vida < 0 ){ //If neg turn to 0
            tierra->vida=0;
        }
        else if(tierra->vida ==0 && tierra->es_tesoro ==1){
            remaining_treasure -=1;
        }
    }

    //DIAG INF IZQ
    if (tab_adyacente[add_col][min_col]==1){
        Bomba* bomba = (Bomba*)tablero[add_col][min_col]; 
        bomba->tierra_debajo -=1;
    } else {
        Tierra* tierra = (Tierra*)tablero[add_col][min_col];  
        tierra->vida -=1;
        if(tierra->vida < 0 ){ //If neg turn to 0
            tierra->vida=0;
        }
        else if(tierra->vida ==0 && tierra->es_tesoro ==1){
            remaining_treasure -=1;
        }
    }

    //DIAG INF DER
    if (tab_adyacente[add_row][add_col]==1){
        Bomba* bomba = (Bomba*)tablero[add_row][add_col]; 
        bomba->tierra_debajo -=1;
    } else {
        Tierra* tierra = (Tierra*)tablero[add_row][add_col];
        tierra->vida -=1;
        if(tierra->vida < 0 ){ //If neg turn to 0
            tierra->vida=0;
        }
        else if(tierra->vida ==0 && tierra->es_tesoro ==1){
            remaining_treasure -=1;
        }
    }
 

    
    return;
}
