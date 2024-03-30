import java.util.*;
public class Enemigo extends Zona implements Ilevantable {
    private Integer vida;
    private Integer peso;
    private Integer ataque;
    private boolean derrotado = false;
    public Integer getVida(){
   	/*
	Obtiene la cantidad de piezas

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer vida
	*/
        return vida;
    }
    public Integer getPeso(){
    	/*
	Obtiene el peso

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer peso
	*/
        return peso;
    }
    public Integer getAtaque(){
    	/*
	Obtiene el ataque

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	integer ataque
	*/
        return ataque;
    }

    public void setVida(Integer v){
    /*
	Asigna la vida

	_Parametros recibidos_
	integer v

	_Parametros entregados_
	Ninguno
	*/
        vida = v;
    }
    public void setPeso(Integer p){
    /*
	Asigna el peso

	_Parametros recibidos_
	integer p

	_Parametros entregados_
	Ninguno
	*/
        peso = p;
    }
    public void setAtaque(Integer a){
        /*
	Asigna el ataque

	_Parametros recibidos_
	integer p

	_Parametros entregados_
	Ninguno
	*/
        ataque = a;
    }
    public void setDerrotado(boolean d){
        /*
	Asigna booleano a derrotado

	_Parametros recibidos_
	boolean d

	_Parametros entregados_
	Ninguno
	*/
        derrotado=d;
    }
    public boolean getDerrotado(){
        /*
	Obtiene el estado de derrotado

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	boolean derrotado
	*/
        return derrotado;
    }

    public Enemigo(Integer vida, Integer peso, Integer atk){ //Constructor
        /*
	Constructor de clase

	_Parametros recibidos_
	integer vida, peso, atk

	_Parametros entregados_
	Ninguno
	*/
        setVida(vida);
        setPeso(peso);
        setAtaque(atk);
    }

    public void Interactuar(Pikinim c, Pikinim m, Pikinim a){
        /*
	Interactua con el enemigo, si la zona fue completada imprime el texto predefinido, si el enemigo no fue derrotado 
	llama a Pelear(), y si lo fue llama a Levantar()

	_Parametros recibidos_
	Pikinim c
	Pikinim m
	Pikinim a

	_Parametros entregados_
	Ninguno
	*/
        if(super.getCompletada()){
            super.Interactuar(c, m, a);
        }else{
            if(!getDerrotado()){    //Si no ha sido derrotado, pelea
                Pelear(c,m,a);
            }
            else{   //Si fue derrotado, lo intenta levantar
                System.out.println("Sólo queda el cuerpo del enemigo...");
                Levantar(c,m,a);
            }
        }
        
    }
    public boolean Pelear(Pikinim c, Pikinim m, Pikinim a){
        System.out.println("Se aparece un enemigo en frente!");
        int suma_cantidad = c.getCantidad() + m.getCantidad() + a.getCantidad();
        int suma_ataque = c.getATK() + m.getATK() + a.getATK();
        System.out.println("Pikinims atacan! Enemigo -"+(suma_cantidad * suma_ataque)+"hp");
        setVida(getVida() - (suma_cantidad * suma_ataque)); //Ataque pikinims
        Random random = new Random();
        Integer rand_num= random.nextInt(4 - 1) + 1;
        switch(rand_num){ //Ataque enemigo a los pikinims
            case 1:
                c.setCantidad(a.getCantidad()-getAtaque());
                if(c.getCantidad()<0){  //ATACA AL CYAN
                    c.setCantidad(0);
                }
                System.out.println("Enemigo ataca a los Pikinim Cyan! "+(c.getCantidad()-getAtaque())+" pikinims");
                break;
            case 2:
                m.setCantidad(m.getCantidad()-getAtaque());
                if(m.getCantidad()<0){ //ATACA AL MAGENTA
                    m.setCantidad(0);
                }
                System.out.println("Enemigo ataca a los Pikinim Magenta! "+(m.getCantidad()-getAtaque())+" pikinims");
                break;
            case 3:
                a.setCantidad(a.getCantidad()-getAtaque());
                if(a.getCantidad()<0){ //ATACA AL AMARILLO
                    a.setCantidad(0);
                }
                
                System.out.println("Enemigo ataca a los Pikinim Amarillos! "+(a.getCantidad()-getAtaque())+" pikinims");
                break;
        }

        if (getVida() == 0 || getVida()<0){ //Si la vida del enemigo llega a 0
            System.out.println("El enemigo fue derrotado!");
            setDerrotado(true); //Fue derrotado
            Levantar(c, m, a); //Intenta levantar
            return getDerrotado();  
        } else {
            return getDerrotado(); //Su vida no llegó a 0, no fue derrotado.
        }
         
    }

    public void Levantar(Pikinim c, Pikinim m, Pikinim a){
        int suma_cantidad = c.getCantidad() + m.getCantidad() + a.getCantidad();
        int suma_capacidad =  c.getCapacidad() + m.getCapacidad() + a.getCapacidad();
        
        if (suma_cantidad * suma_capacidad >= getPeso()){ //Si lo pueden levantar, se marca como completado
            System.out.println("Los pikinim levantan el cuerpo del enemigo y se lo llevan");
            super.setCompletada(true);
        }
        else{
            System.out.println("Faltan pikinim para levantar al enemigo");
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
        System.out.println("~Enemigo~   Vida: "+getVida()+" Peso: "+getPeso()+" Ataque: "+getAtaque());
        }
    }
    
}
