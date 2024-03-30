%En cifrado se establece la traducción de las combinaciones numéricas a base nitrogenadas.
%Descifrar recibe en Mensaje la lista de bits, y secciona la lista recibida en una lista
% con los primeros 2 elementos, para luego comparar esta sublista con los cifrados.
%Luego de revisado, se llama recursivamente con el resto de la lista siguiendo el mismo método.


cifrado([0,0], a).
cifrado([0,1], g).
cifrado([1,0], c).
cifrado([1,1], t).

descifrar([], []).
descifrar(Mensaje, Respuesta) :-
    Mensaje = [X,Y|Resto],
    Respuesta = [Base|Answer],
    cifrado([X,Y], Base),
    descifrar(Resto, Answer), !.