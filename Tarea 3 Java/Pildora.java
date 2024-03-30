import java.util.Scanner;

public class Pildora extends Zona {
    private Integer cantidad;
    Scanner read = new Scanner(System.in);
    public Integer getCantidad(){
   	/*
	Obtiene la cantidad a multiplicar

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer cantidad
	*/
        return cantidad;
    }
    public void setCantidad(Integer q){
    	/*
	Asigna la cantidad a multiplicar

	_Parametros recibidos_
	integer l

	_Parametros entregados_
	Ninguno
	*/
        cantidad = q;
    }

    public Pildora(Integer quantity){
        /*
	Constructor de clase

	_Parametros recibidos_
	integer quantity

	_Parametros entregados_
	Ninguno
	*/
        setCantidad(quantity);
    }


    public void Interactuar(Pikinim c, Pikinim m, Pikinim a){
        /*
	Interactua con la pildora, si la zona fue completada imprime el texto predefinido, sino consume la pildora

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
            System.out.println("Pillaste una pila wacho, a quien se la das?");
            System.out.println("\t 1. Pikinim Cyan - cantidad: "+c.getCantidad());
            System.out.println("\t 2. Pikinim Magenta - cantidad: "+m.getCantidad());
            System.out.println("\t 3. Pikinim Amarillo - cantidad: "+a.getCantidad());
            System.out.print("Elección: ");
            Integer in = read.nextInt();
            System.out.println();
            switch(in){
                case 1:
                    c.multiplicar(getCantidad());
                    System.out.println("Los Pikinims Cyan se multiplican!");
                    break;
                case 2:
                    m.multiplicar(getCantidad());
                    System.out.println("Los Pikinims Magenta se multiplican!");
                    break;
                case 3:
                    a.multiplicar(getCantidad());
                    System.out.println("Los Pikinims Amarillo se multiplican!");
                    break;
            }
            super.setCompletada(true);        
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
        System.out.println("~Pildora~   Cantidad: "+getCantidad());
        }
    }
}
