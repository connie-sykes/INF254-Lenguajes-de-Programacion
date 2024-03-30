%Se establece una cerradura cualquiera como hecho.
%Verificar recibe un intento de cerradura, y llamando a contarCoincidencias, que si el primer número
%	del intento coincide con el primer número de la cerradura, cuenta como un 1, caso contrario es un 0.
%	Se suman estas coincidencias y R toma el valor de la cantidad de estas. 

%isCerradura se encarga de verificar si se descubrió la contraseña, comparando si el total
%	de coincidencias es igual a 5, R toma el valor "Contraseña descubierta".

cerradura(1, 4, 5, 1, 0).

verificar(X1, X2, X3, X4, X5, R) :-
    cerradura(C1, C2, C3, C4, C5),
    contarCoincidencias(X1, C1, C1n),
    contarCoincidencias(X2, C2, C2n),
    contarCoincidencias(X3, C3, C3n),
    contarCoincidencias(X4, C4, C4n),
    contarCoincidencias(X5, C5, C5n),
    Total is C1n + C2n + C3n + C4n + C5n,
    isCerradura(Total, R), !.


isCerradura(5, "Contraseña descubierta") :- !.
isCerradura(Total, Total).
    
contarCoincidencias(X, X, 1) :- !.
contarCoincidencias(_, _, 0).