public class Magenta extends Pikinim {
    public Magenta(){ //Constructor
        /*
	Constructor de clase

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	Ninguno
	*/
        setATK(2);
        setCapacidad(1);;
    }

    void multiplicar(int cantidad){
    	/*
	Multiplica los pikinim asignandoles una nueva cantidad

	_Parametros recibidos_
	integer cantidad

	_Parametros entregados_
	Ninguno
	*/
        setCantidad(cantidad*getATK());
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
