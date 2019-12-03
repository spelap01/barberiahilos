import org.apache.commons.math3.distribution.ExponentialDistribution;

public class Barbero extends Thread{ 
	boolean barberoOcupado=false;

	int idBarbero;//esto tiene que ser una letra
	boolean[] sillasClientes=barberia.inicializamosSillas();
	public Barbero(int i) {
		this.idBarbero=i;
		System.out.println("El barbero "+calculaLetra(i)+" se ha creado.");

		// TODO Auto-generated constructor stub
	}
	public static ExponentialDistribution distribucionExponencial;
	public static Barberia barberia;


	public void run() {
		System.out.println("El barbero "+ calculaLetra(idBarbero)+" se pone a dormir.");
		while(true) {
			try {
				//si hay clientes sin atender??;
				dormir();
				sleep(1);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("El barbero "+ calculaLetra(idBarbero)+" ha sido destruido.");

			}
		}

	}
	public synchronized void dormir() throws InterruptedException {
		
		if(barberoOcupado==false&&barberia.hayCola==false) {
			wait();
			System.out.println("El barbero "+ calculaLetra(idBarbero)+" se pone a dormir.");
		}
		
	}

	public synchronized void cortarElPelo(int idCliente) throws InterruptedException {
		
		long tiempoEspera=(long)distribucionExponencial.sample();
		Thread.sleep(tiempoEspera);
		System.out.println("El barbero "+calculaLetra(idBarbero)+" ha cortado el pelo al cliente "+idCliente+".");
		//System.out.println("El barbero "+idBarbero+" se pone a dormir.");
		barberoOcupado=false;
		notifyAll();
	}
	
	public synchronized boolean genteEnLaSilla(){
		if(barberia.getSillasDesocupadas()!=barberia.numSillas) {
			return true;
		}
		return false;
	}
	public synchronized void cortarElPeloSentado(int idCliente) throws InterruptedException {
		
		long tiempoEspera=(long)distribucionExponencial.sample();
		Thread.sleep(tiempoEspera);
		System.out.println("El barbero "+calculaLetra(idBarbero)+" ha cortado el pelo al cliente "+idCliente+".");
		//barberia.barberosArray[idBarbero-1].barberoOcupado=false;
		barberoOcupado=false;
		notifyAll();
		
	}
	public static String calculaLetra(int i) {
		switch(i) {
		case 1:
			return "A";
		case 2:
			return "B";
		case 3:
			return "C";
		case 4:
			return "D";
		case 5:
			return "E";
		case 6:
			return "F";
		case 7:
			return "G";
		case 8:
			return "H";
		case 9:
			return "I";
		case 10:
			return "J";
		case 11:
			return "K";
		case 12:
			return "L";
		case 13:
			return "M";
		case 14:
			return "N";
		case 15:
			return "O";
		case 16:
			return "P";
		case 17:
			return "Q";
		case 18:
			return "R";
		case 19:
			return "S";
		case 20:
			return "T";
		case 21:
			return "U";
		case 22:
			return "V";	 
		case 23:
			return "W";
		case 24:
			return "X";
		case 25:
			return "Y";
		case 26:
			return "Z";
		
		}
		return null;
	}



}
