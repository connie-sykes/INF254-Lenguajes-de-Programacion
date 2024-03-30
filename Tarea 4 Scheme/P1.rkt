#lang scheme
; ; La función checkear compara el valor supuesto del largo de la lista con el largo real de la lista entregada
; ;
; ; cantidad : Numero entero el cual supone ser el largo de la lista entregada como segundo parámetro
; ; lista : Lista a verificar su largo

; ; La función size calcula el tamaño de la lista mediante recursión
; ;
; ; lista : Lista a calcular su largo

(define (checkear cantidad lista)
  (define (size lista)
    (if (null? lista)
        0
        (+ 1 (size (cdr lista)))))
  
  (equal? cantidad (size lista)))
