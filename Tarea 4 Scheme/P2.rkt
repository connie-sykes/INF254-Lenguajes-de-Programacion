#lang scheme
; ; La función va "appendeando" los resultados de evaluar el parametro base en cada función de la lista mediante recursión simple
; ;
; ; base : Numero a evaluar en cada una de las funciones existentes en lista,
; ; lista : Lista que contiene diversas funciones lambda a evaluar,

(define (cantidades_simple base lista)
  (if (null? lista)
  '()
  (cons ((car lista) base) (cantidades_simple base (cdr lista))
  )))

; ; La función cantidades_cola llama recursivamente a una funcion auxiliar que agrega a la lista resultado las respectivas evaluaciones del numero base en la lista revisada.
; ; Una vez se termina de evaluar, se invierte la lista para mantener el orden original.
; ;
; ; base : Numero a evaluar en cada una de las funciones existentes en lista.
; ; lista : Lista que contiene diversas funciones lambda a evaluar.


; ; La función aux evalua el numero base en cada función de la lista, y va añadiendo su valor a la lista resultado cada vez que es llamada.
; ;
; ; base : Numero a evaluar en cada una de las funciones existentes en lista.
; ; lista : Lista que contiene diversas funciones lambda a evaluar.

(define (cantidades_cola base lista)
  (let aux ( (ls lista) (resultado '() ) )
    (if (null? ls)
        (reverse resultado)
        (aux (cdr ls) (cons ((car ls) base ) resultado )))))

        