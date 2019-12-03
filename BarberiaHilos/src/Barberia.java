import java.util.ArrayList;

public class Barberia {
	//la barberia es la mesa de los filosofos 

	public int numSillas;
	public static Barbero[] barberosArray;
	public static boolean[] sillasLibres;
	public static int sillasDesocupadas;
	static boolean hayCola=false;
	public ArrayList<Cliente> colaDeEspera = new ArrayList<Cliente>();


	public static Barberia getBarberia() {
		Barberia barberia = new Barberia();
		return barberia;
	}

	public void setNumeroSillas(int nextInt) {
		this.numSillas = nextInt;
		sillasDesocupadas=nextInt;
	}

	public boolean[] inicializamosSillas() {
		sillasLibres= new boolean[numSillas];
		for(int i=0;i<numSillas;i++) {
			sillasLibres[i]=true;
		}
		return sillasLibres;
	}

	public int getSillasDesocupadas() {
		return sillasDesocupadas;
	}


	public synchronized int barbersoDisponibles() {
		for(int i=0;i< barberosArray.length;i++) {
			if(barberosArray[i].barberoOcupado==false) {
				barberosArray[i].barberoOcupado=true;
				notifyAll();
				return barberosArray[i].idBarbero;
			}
		}
		return -1;
	}
	public synchronized Barbero getbarbersoDisponibles(int i) {
		return barberosArray[i-1];
	}


	public synchronized void clienteOcupaUnaSilla(int idCliente) throws InterruptedException {


		System.out.println("El cliente "+idCliente+" se sienta en una silla de espera.");
		sillasDesocupadas--;



	}
	public synchronized void clienteDejaUnaSilla() {
		for(int i=0;i<numSillas;i++) {
			if(sillasLibres[i]==false) {
				sillasLibres[i]=true;
				sillasDesocupadas++;
				break;
			}
		}
	}

	//public cliente espera
	//cliente se sienta o cliente se marcha 

	public void setBarberos(Barbero[] barberos) {
		this.barberosArray=barberos;		
	}





}
