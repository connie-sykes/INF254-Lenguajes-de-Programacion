public abstract class Pikinim {
    protected int ataque,capacidad,cantidad = 10;

    public int getATK(){
    	/*
	Obtiene el ataque

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer ataque
	*/
        return ataque;
    }
    public void setATK(int atk){
    	/*
	Asigna el ataque

	_Parametros recibidos_
	integer atk

	_Parametros entregados_
	Ninguno
	*/
        ataque = atk;
    }

    public int getCapacidad(){
    	/*
	Obtiene la capacidad

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer capacidad
	*/
        return capacidad;
    }
    public void setCapacidad(int cap){
    	/*
	Asigna la capacidad

	_Parametros recibidos_
	integer cap

	_Parametros entregados_
	Ninguno
	*/
        capacidad = cap;
    }

    public int getCantidad(){
    	/*
	Obtiene la cantidad

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer	cantidad
	*/
        return cantidad;
    }
    public void setCantidad(int cant){
    	/*
	Asigna la cantidad

	_Parametros recibidos_
	integer cant

	_Parametros entregados_
	Ninguno
	*/
        cantidad = cant;
    }

    abstract void disminuir(int capacidad);
    abstract void multiplicar(int capacidad);
    
}
