#lang scheme
; ; Verifica que el ingrediente existe dentro de la lista
; ;
; ; ingrediente : Elemento a buscar en la lista.
; ; lista : Lista de ingredientes, puede contener al parámetro 'ingrediente' o no.


(define (ingrediente_in_lista ingrediente lista)
  (cond
    ((null? lista) #f)
    ((eqv? ingrediente (car lista)) #t)
    (else (ingrediente_in_lista ingrediente (cdr lista)))))


; ; Checkea si el ingrediente es nulo, si la lista es nula, y luego, si el ingrediente pertenece a la primera lista de ingredientes, se añade a la lista de recetas, sino,
; ; sigue buscando.
; ;
; ; ingrediente : Elemento a buscar en las listas de ingredientes
; ; lista : Lista de listas de ingredientes, cuyo primer elemento es el nombre de la receta y el resto son los ingredientes que la componen.

(define (buscar_recetas ingrediente lista)
  (if (null? ingrediente)
      '()
      (if (null? lista)
          '()
          (if (eqv? (ingrediente_in_lista (car ingrediente) (car lista)) #t)
              (cons (caar lista) (buscar_recetas ingrediente (cdr lista)))
              (buscar_recetas ingrediente (cdr lista))))))
      



        


