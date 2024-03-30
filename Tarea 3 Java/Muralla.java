public class Muralla extends Zona {
    private Integer vida;
    public Integer getVida(){
        /*
	Obtiene la vida de la muralla

	_Parametros recibidos_
	Ninguno

	_Parametros entregados_
	Ninguno
	*/
        return vida;
    }
    public void setVida(Integer l){
        /*
	Asigna la vida de la muralla

	_Parametros recibidos_
	integer l

	_Parametros entregados_
	Ninguno
	*/
        vida = l;
    }
    public Muralla(Integer vida){
        /*
	Constructor de clase

	_Parametros recibidos_
	integer vida

	_Parametros entregados_
	Ninguno
	*/
        setVida(vida);
    }
    public void Interactuar(Pikinim c, Pikinim m, Pikinim a){
        /*
	Interactua con la muralla, si la zona fue completada imprime el texto predefinido, sino llama al metodo
	TryRomper

	_Parametros recibidos_
	Pikinim c
	Pikinim m
	Pikinim a

	_Parametros entregados_
	Ninguno
	*/
        System.out.println("Te has topado con una muralla!");
        if(super.getCompletada()){
            super.Interactuar(c, m, a);
        }
        else{
            TryRomper(c,m,a);
        }

    }
    public void TryRomper(Pikinim c, Pikinim m, Pikinim a){
        /*
	Intenta romper la muralla, bajandole una respectiva vida segun los pikinim entregados

	_Parametros recibidos_
	Pikinim c
	Pikinim m
	Pikinim a

	_Parametros entregados_
	Ninguno
	*/
	
        Integer suma_c = c.getCantidad() * c.getATK();
        Integer suma_m = m.getCantidad() * m.getATK();
        Integer suma_a = a.getCantidad() * a.getATK();
        System.out.println("Los pikinim se intentan abrir paso y golpean la muralla, de vida "+getVida());
        setVida(getVida() - (suma_c + suma_m + suma_a));
        System.out.println("Muralla -"+(suma_c + suma_m + suma_a)+"hp");
        if(getVida()<0){
            setVida(0);
        }
        System.out.println("Vida total muralla: "+getVida());
        if (getVida()==0){
            System.out.println("EL MURO HA CAIDO! ");
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
            System.out.println("~Muralla~   Vida: "+getVida());
        }

    }
}
