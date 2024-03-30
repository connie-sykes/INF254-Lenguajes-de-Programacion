#lang scheme
; ; Calcula el largo de la lista
; ;
; ; lista : lista a calcular su largo

(define (size lista)
    (if (null? lista)
        0
        (+ 1 (size (cdr lista)))))

; ; Compara cada cantidad necesaria con la cantidad de ingredientes, si la cantidad necesaria es mayor a la cantidad de ingredientes disponibles se agrega
; ; a una lista de compras, la cual indicará cuánto y qué ingrediente falta por comprar para la receta.
; ;
; ; stock : Lista de de listas de la forma (cantidad_necesaria ingredientes)

(define (armar_lista stock)
  (if (null? stock)
  '()
  (if (<= (car (car stock)) (size (car(cdar stock))))
      (armar_lista (cdr stock)) 
      (cons (list (- (car (car stock)) (size (car(cdar stock)))) (caar(cdar stock))) (armar_lista (cdr stock))  ) ) ))

