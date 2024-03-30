%Se establece como hecho una cerradura numérica cualquiera.
%El primer caso de verificar es cuando los números ingresados coinciden en su totalidad con la cerradura establecida.
%El siguiente caso, calcula la distancia entre el valor de la cerradura y el valor ingresado en la función.
%	Considerando la distancia como la diferencia absoluta de los valores total dividido en 5; si la distancia 
%	es menor a 1 se determina "cerca", caso contrario, se determina lejos.
cerradura(1, 4, 5, 1, 0).

verificar(X1, X2, X3, X4, X5, "Contraseña descubierta") :-
    cerradura(X1_C, X2_C, X3_C, X4_C, X5_C),
    X1 = X1_C,
    X2 = X2_C,
    X3 = X3_C,
    X4 = X4_C,
    X5 = X5_C, !.

verificar(X1, X2, X3, X4, X5, "Cerca") :-
    cerradura(X1_C, X2_C, X3_C, X4_C, X5_C),
    D1 is abs(X1 - X1_C),
    D2 is abs(X2 - X2_C),
    D3 is abs(X3 - X3_C),
    D4 is abs(X4 - X4_C),
    D5 is abs(X5 - X5_C),
    E is (D1 + D2 + D3 + D4 + D5) / 5,
    E < 1, !.

verificar(_, _, _, _, _, "Lejos").