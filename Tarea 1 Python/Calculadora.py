import re
############# REGEX DEFINITIONS ######################

digito = '[1-9]'
digito_o_zero =  digito+'|0' 
entero = digito+'('+digito_o_zero+')*'+'|0'
espacio = '\s'
clave = 'ANS|CUPON\(' +'('+espacio+')*'+'('+entero+'|ANS'+')'+'('+'('+espacio+'*'+'\,' + espacio+'*'+'('+entero+'|ANS'+')'+')?'+espacio+'*'+'\)'+')'
operador = '('+espacio+')*' + '('+ r'\+|\-|\*|//'+')' +'('+espacio+')*'
operacion = '('+clave+'|'+entero+')' + operador + '(?!.*' + operador + operador + ')('+clave+'|'+entero+')'
sentencia = operacion + '(?:' + operador + '(' + entero + '|' + clave + '))*'
sentencia_parentesis =  '\(' + '('+sentencia+')' + '\)'
wrong_sentencia_parentesis = '\(' + '('+entero+')' + '\)'
parentesis = '\(' + '|' + '\)'
binsum_rest = '('+entero+')' + '('+'\\+|\\-'+')' + '('+entero+')'
binmult_div = '('+entero+')' + '('+'\\*|\\/\\/'+')' + '('+entero+')'

cupon = 'CUPON\(' +'('+espacio+')*'+'('+entero+'|ANS'+')'+'('+'('+espacio+'*'+'\,' + espacio+'*'+'('+entero+'|ANS'+')'+')?'+espacio+'*'+'\)'+')'
#####################################################

def issyntax(string):
    '''
    ***
    *string : string
    ***
    El string que se ingresa es un conjunto de numeros y operadores, detecta la presencia de la expresion
    regular 'sentencia' y se verifica la correcta sintaxis de esta.
    Retorna True si la sintaxis de la operacion es correcta y False si esta es incorrecta
    '''

    match = re.fullmatch(sentencia, string)
    if match==None:
        return False
    else:
        return True
      
def has_parentesis(string):
    '''
    ***
    *string : string
    ***
    Se ingresa a la funcion un string y se verifica la presencia de parentesis en esta.
    
    Retorna True si el string posee parentesis  y False si no los tiene.
    '''
    marker = '\"'
    string = re.sub(r'//', marker, string)
    string = re.split(r'(?<=[^a-zA-Z0-9])|(?=[^a-zA-Z0-9])', string)
    b=0
    for a in string:
        if a == '\"':
            string[b]='//'
        b+=1
    if ('(' or ')') in string:
        return True
    else:
        return False
    
def check_balance_parent(string):
    '''
    ***
    *string : string
    ***
    El string que se ingresa es una sentencia que posee parentesis. La funcion se encarga de evaluar si estos 
    se encuentran correctamente balanceados o no.

    Retorna True si los parentesis se encuentran balanceados y False si no.
    '''
    
    marker = '\"'
    string =  re.sub(r'//', marker, string)
    string = re.split(r'(?<=[^a-zA-Z0-9])|(?=[^a-zA-Z0-9])', string)
    b=0
    par_total = 0
    for a in string:
        if a == '\"':
            string[b]='//'
        b+=1
    if '(' or ')' in string:
        for k in string:
            if k == '(':
                par_total +=1
            elif k == ')':
                par_total -=1
    if par_total != 0:
        return False
    else:
        return True

def CUPON(x,y=20): 
    '''
    ***
    *x : int
    *y : int
    ***
    En caso de que la funcion sea de tipo CUPON(x), calcula el 20% de x truncado, de otro modo, si 
    la funcion es CUPON(x,y) calcula el y% de x. No se admiten valores de y mayores a 100.
    Retorna el valor del cupon.
    '''
    x=int(x)
    if y == None:
        cupon = x*(y/100)
    elif (int(y)<=100):
        y=int(y)
        cupon = x*(y/100)
    else: #(int(y) > 100):
        cupon = -1
    return int(cupon)

def cupon_eval(match):
    '''
    ***
    *match : Match
    ***
    Desde un string encontrado desde un elemento Match de forma 'CUPON(x)' o 'CUPON(x,y)' extrae los valores de x e y para
    llamar a la funcion cupon_eval, para calcular el valor de CUPON.

    Retorna el valor de cupon.
    '''
    x = match.group(2).strip(' ')
    x = re.sub(r'\s', '', x)

    if match.group(4) == ')':
        resultado = CUPON(x)
    else:
        y = match.group(4).strip(')')
        y = re.sub(r'\s', '', y)
        y = re.sub('\,', '', y)
        resultado = CUPON(x,y)
    return resultado


def multdiv(match):
    '''
    ***
    *match : Match
    ***
    Extrae desde el match de tipo x*y o x//y los valores de x e y, para calcular
    su respectivo valor. Imita el comportamiento de la funcion eval()
    Retorna el valor de la operacion binaria.
    '''
    total = 0
    x= int(match.group(1))
    y= int(match.group(4))
    

    if match.group(3)=='*':
        total = x*y
    elif match.group(3)=='//':
        if y==0:
            total = 'a'
        else:
            total = x//y
    
    return total

def sumrest(match):
    '''
    ***
    *match : Match
    ***
    Extrae desde el match de tipo x+y o x-y los valores de x e y, para calcular
    el valor de la operacion binaria respectiva. Imita el comportamiento de la funcion eval()
    Retorna el valor de la operacion binaria.
    '''
    total = 0
    x= int(match.group(1))
    y= int(match.group(4))
    if match.group(3)=='+':
        total = x+y
    elif match.group(3)=='-':
        total = x-y
    if total < 0:
        return 0
    return total

def replace_cupon_in_string(string):
    '''
    ***
    *string : string
    ***
    Reemplaza en string todas las coincidencias de tipo 'cupon' con su respectivo
    valor numerico.
    Retorna el string con todas las coincidencias reemplazadas.
    '''
    while True:
        match = re.search(cupon, string)
        if match is None:
            break
        matched_cupon = match.group(0)
        value_matched_cupon = cupon_eval(match)
        string = string.replace(matched_cupon, str(value_matched_cupon))
    return string


def calcular(string):
    '''
    ***
    *string : string
    ***
    El string tiene la forma de la expresion regular 'sentencia', por lo que para operarlo como si fueran una 
    seguidilla de operaciones binarias se reemplazan las coincidencias de multiplicacion y division con su 
    respectivo valor numerico llamando a la funcion multdiv con tal de crear una precedencia de calculo, 
    para que cuando se hayan reemplazado todas, se realiza lo mismo con las coincidencias de suma y resta llamando
    a la funcion sumrest.
    
    Retorna el valor numerico de la operacion completa, pero si ocurre el caso de division por cero retorna 'False'.
    '''
    string.strip()
    string = re.sub(espacio, '', string)
    operacion_final = string
    
    ###### Revisa el string, y a medida que encuentra la operacion multiplicacion o division la calcula y reemplaza su resultado 
    #           en el lugar de la operacion   
    c=1
    while re.search(binmult_div,operacion_final) != None:
        if multdiv(re.search(binmult_div,operacion_final)) == 'a':
            operacion_final='a'
            resultado = 'a'
        else:
            operacion_final = re.sub(binmult_div, str(multdiv(re.search(binmult_div,operacion_final))), operacion_final)
        c+=1
    #__________________________________________________________________________________________________________________________
    
    ###### Revisa el string, el cual ahora solo se compone de enteros y sumas o restas, y a medida que encuentra la operacion 
    #           la calcula y reemplaza su resultado en el lugar de la operacion                                                     
    c=1
    while re.search(binsum_rest,operacion_final) != None:
        operacion_final = re.sub(binsum_rest, str(sumrest(re.search(binsum_rest,operacion_final))), operacion_final)
        c+=1
    #__________________________________________________________________________________________________________________________
    
    if operacion_final !='a':
        resultado = int(operacion_final)

        if resultado <0:
            resultado = 0
        return resultado
    else:
        return 'False'

def issyntaxparentesis(string,i):
    '''
    ***
    *string : string
    *i : int
    ***
    Verifica la correcta syntaxis de parentesis en el string de manera recursiva, asi como que el balance de estos sea
    correcto y que cumpla con el patron '\(sentencia\)'.
    En caso de que alguna de las condiciones para una correcta sintaxis no se cumpla, retorna False.
    De lo contrario, retorna True
    '''
    match = re.search(sentencia_parentesis, string)
    match_error = re.search(wrong_sentencia_parentesis,string)
    if has_parentesis(string):
        if check_balance_parent(string) and (match_error==None):
                if match != None:
                    i+=1
                    string = re.sub(sentencia_parentesis, str(calcular(str(match.group(1)))),string)
                    issyntaxparentesis(string,i)
                    return True
        else:
            return False
    if issyntax(string):
        return True
            
def calcular_parentesis(string,i):
    '''
    ***
    *string : i
    ***
    Al igual que la funcion calcular, imita la funcion de eval(), por lo que resuelve de manera recursiva
    estos.
    Retorna un string con todas las operaciones reducidas a su minima expresion, con tal de cumplir la
    sintaxis de 'sentencia'.
    '''
    match = re.search(sentencia_parentesis, string)

    if has_parentesis(string):
        if match != None:
            i+=1
            string = re.sub(sentencia_parentesis, str(calcular(str(match.group(1)))),string)
            calcular_parentesis(string,i)

            return string
    else:
        return string


archivo = open("problemas.txt",'r')
lineas = archivo.readlines()
problema = True


f=open('desarrollos.txt', "w")

i=0     #Contador lineas
p=0     #Contador problemas
ANS = 0
list_problema=[]   #Lista problemas
list_index_error=[] #Lista indice operacion error en list_problema
error = False
list_resultados=[]  #Lista de lineas a escribir en el archivo desarrollos.txt,linea = resultado, linea= sin evaluar o linea= error.

for l in lineas:
        
    line = l.strip()
    text = line
    problema = True
    
    if l =='\n': 
        
        if(error):
            index=0
            for k in list_problema:
                if (index not in list_index_error) and index<len(list_problema):
                    list_problema[index] = k + ' = Sin resolver'
                    index+=1
                elif index in list_index_error:
                    index+=1

            for k in list_problema:
                f.write(k+'\n')                
                        
        else:
            for j in list_resultados:
                f.write(j+'\n')


        f.write('\n')
        error = False
        p=0
        list_problema = []
        list_index_error = []
        list_resultados=[]
        ANS=0
        problema = False

        
    text_ans = text.replace('ANS', str(ANS))
    text = replace_cupon_in_string(text_ans)
        
    if(problema):
        p+=1
        list_problema.append(text)
        list_resultados.append(text)
        if has_parentesis(text):
           
            if (issyntaxparentesis(text,0)): 
                text=calcular_parentesis(text,0)
                
                if has_parentesis(text):
                    text = re.sub(parentesis, '', text)
                
                if issyntax(text):
                    error=False
                    t = re.sub(espacio, '', text)
                    resultado = calcular(text)

                    if resultado == 'False':
                        ANS = 0
                        error = True
                        list_problema[p-1] = line + ' = ' + 'error'
                        list_index_error.append(p-1)
                        
                    ANS = resultado
                    list_resultados[p-1]=line + ' = ' + str(resultado)

                else:
                    error = True
                    list_problema[p-1] = line + ' = ' + 'error'
                    list_index_error.append(p-1)
                    ANS = 0

            else:
                error = True
                list_problema[p-1] = line + ' = ' + 'error' #' Mala syntax de parentesis'
                list_index_error.append(p-1)
                ANS = 0

        
        elif has_parentesis(text)==False:
            if  issyntax(text):
                        error=False
                        t = re.sub(espacio, '', text)
                        resultado = calcular(text)
                        if resultado == 'False':
                            error = True
                            list_problema[p-1] = line + ' = ' + 'error' # 'div cero'
                            list_index_error.append(p-1)
                            ANS = 0
                        ANS = resultado
                        list_resultados[p-1]=line + ' = ' + str(resultado)
            else:
                error = True
                list_problema[p-1] = line + ' = ' + 'error'     # 'Mala syntax'
                list_index_error.append(p-1)

    if l==lineas[-1]:   #Caso de llegar al final del archivo problemas.txt
        if(error):
            index=0
            for k in list_problema:
                if (index not in list_index_error) and index<len(list_problema):
                    list_problema[index] = k + ' = Sin resolver'
                    index+=1
                elif index in list_index_error:
                    index+=1

            for k in list_problema:
                f.write(k+'\n')                
                        
        else:
            for j in list_resultados:
                f.write(j+'\n')
    i+=1

archivo.close()
f.close()