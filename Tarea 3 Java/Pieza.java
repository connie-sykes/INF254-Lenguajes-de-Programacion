public class Pieza extends Zona implements Ilevantable {
    private int Peso;
    private static Integer contador_piezas=0;

    public Integer getPiezas(){
    	/*
	Obtiene la cantidad de piezas

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer contador_piezas
	*/
        return contador_piezas;
    }
    public void addPiezas(){
    	/*
	Anade una unidad a contador_piezas

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	Ninguno
	*/
        contador_piezas+=1;
    }

    public Integer getPeso(){
    	/*
	Obtiene el peso

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer peso
	*/
        return Peso;
    }
    public void setPeso(Integer p){
        Peso =p;
    }

    public Pieza(Integer peso){ //Constructor
        /*
	Constructor de clase

	_Parametros recibidos_
	integer peso

	_Parametros entregados_
	Ninguno
	*/
        setPeso(peso);

    }
    public void Levantar(Pikinim c, Pikinim m, Pikinim a){

        System.out.println("Has encontrado una pieza");
        int suma_cantidad = c.getCantidad() + m.getCantidad() + a.getCantidad();
        int suma_capacidad =  c.getCapacidad() + m.getCapacidad() + a.getCapacidad();
        System.out.println("Los pikinims intentan levantar la pieza...");
        if (suma_cantidad * suma_capacidad >= getPeso()){
            System.out.println("Pieza levantada :D");
            super.setCompletada(true);
            addPiezas();
            //marcar tesoro//
        } else {
            System.out.println("Faltan pikinims para levantar la pieza :(");
            //creo que no es necesario especificar un else.
        }
    }
    public void Interactuar(Pikinim c, Pikinim m, Pikinim a){
    	/*
	Interactua con la pieza, si la zona fue completada imprime el texto predefinido, sino llama al metodo
	Levantar

	_Parametros recibidos_
	Pikinim c
	Pikinim m
	Pikinim a

	_Parametros entregados_
	Ninguno
	*/
        if(super.getCompletada()){
            super.Interactuar(c, m, a);
        } 
        else{
            Levantar(c,m,a);
        }
        
    }
    public void QueHay(){
    	/*
	Imprime el tipo de zona y sus caracterisitcas

	_Parametros recibidos_
	NInguno

	_Parametros entregados_
	Ninguno
	*/
	
        if(super.getCompletada()){
            super.QueHay();
        }else{
            System.out.println("~Pieza~     Peso:"+getPeso());
        }
    }
        
}
