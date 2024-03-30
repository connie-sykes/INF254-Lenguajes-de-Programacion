public class Zona {
    private boolean completada;
    
    
    public boolean getCompletada(){
    	/*
	Obtiene el estado de completada

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	boolean completada
	*/
        return completada;
    }
    public void setCompletada(boolean b){
        /*
	Asigna booleano a completada

	_Parametros recibidos_
	boolean b

	_Parametros entregados_
	Ninguno
	*/
        completada = b;
    }



    public void Interactuar(Pikinim c, Pikinim m, Pikinim a){
    	/*
	Interactua con la zona, siendo la clase padre, si la zona fue completada imprime "No queda nada aqui"

	_Parametros recibidos_
	integer peso

	_Parametros entregados_
	Ninguno
	*/
        if (getCompletada()==true){
            System.out.println("No queda nada aquí...");
        }
    }

    public void QueHay(){
    	/*
	Imprime que hay en la zona, en caso de no haber nada despliega el mensaje "No queda nada"

	_Parametros recibidos_
	NInguno

	_Parametros entregados_
	Ninguno
	*/
        if(getCompletada()==true){
            System.out.println("No queda nada aquí...");
        }
 
    }
    public Zona(){
    	/*
	Constructor de clase

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	Ninguno
	*/
        setCompletada(false);
    }
}
