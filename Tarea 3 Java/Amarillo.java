public class Amarillo extends Pikinim {
    public Amarillo(){  //Constructor
        /*
	Constructor de clase

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	Ninguno
	*/
        setATK(1);
        setCapacidad(3);;
    }
    
    void multiplicar(int cantidad){
    	/*
	Multiplica los pikinim asignandoles una nueva cantidad

	_Parametros recibidos_
	integer cantidad

	_Parametros entregados_
	Ninguno
	*/
        setCantidad((int)Math.ceil(cantidad*1.5));
    }
    void disminuir(int cantidad){
    	/*
	Disminuye los pikinim asignandoles una nueva cantidad

	_Parametros recibidos_
	integer cantidad

	_Parametros entregados_
	Ninguno
	*/
        setCantidad(getCantidad()-cantidad);
    }
}
