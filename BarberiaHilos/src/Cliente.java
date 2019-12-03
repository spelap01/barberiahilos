import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;
//no atiende a los sentados potr lo que no los elimina no se por que mirar mañana 
public class Cliente implements Runnable {
	int idCliente;
	static long  tiempoEsperatotal=0;
	boolean atendido;
	boolean sentado;
	ArrayList<Cliente> clientesSentados=new ArrayList<Cliente>();
	public Cliente(int i) {
		this.idCliente=i;
		atendido=false;
		System.out.println("El cliente "+idCliente+" se ha creado.");
	}
	public static NormalDistribution distribucionNormal;
	public static Barberia barberia;
	@Override
	public void run() {



		//long tiempoEspera=(long)distribucionNormal.sample();
		//while(tiempoEspera<0)tiempoEspera=(long)distribucionNormal.sample();
		try {
			long tiempoEspera=(long)distribucionNormal.sample();
			while(tiempoEspera<0)tiempoEspera=(long)distribucionNormal.sample();
			Thread.sleep(tiempoEsperatotal+tiempoEspera);
			System.out.println("El cliente "+idCliente+" llega a la barbería.");
			Thread.sleep(1);

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while(true) {
			try {

				//estan libres??
				//hayCola??
				Thread.sleep(1);
				int auxBarbero=barberia.barbersoDisponibles();
				if(barberia.colaDeEspera.isEmpty()) {
					if(auxBarbero!=-1&&atendido==false) {
						if(sentado==false) {
							atendido=true;
							System.out.println("El barbero "+Barbero.calculaLetra(auxBarbero)+" atiende al cliente "+idCliente+".");
							serAtendido(auxBarbero);
						}
					}else {
						sentarse();
					}
				}else {
					while(!barberia.colaDeEspera.isEmpty()) {
						auxBarbero=barberia.barbersoDisponibles();
						try {
							if(barberia.colaDeEspera.get(0).idCliente==idCliente&&auxBarbero!=-1) {
								barberia.sillasDesocupadas++;
								atendido=true;
								System.out.println("El barbero "+Barbero.calculaLetra(auxBarbero)+" atiende al cliente "+idCliente+".");
								barberia.colaDeEspera.remove(0);
								serAtendidoSentado(auxBarbero);
								if(!barberia.colaDeEspera.isEmpty()) {
									barberia.hayCola=false;
								}

							}
						}catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.print("");
							System.exit(0);
							
						}
					}
				}
				/*if(auxBarbero!=-1&&atendido==false) {
					if(sentado==false) {
						atendido=true;
						System.out.println("El barbero "+Barbero.calculaLetra(auxBarbero)+" atiende al cliente "+idCliente+".");
						serAtendido(auxBarbero);
					}else {
						if(barberia.colaDeEspera.get(0).idCliente==idCliente) {
							barberia.sillasDesocupadas++;
							atendido=true;
							System.out.println("El barbero "+Barbero.calculaLetra(auxBarbero)+" atiende al cliente "+idCliente+".");
							barberia.colaDeEspera.remove(0);
							serAtendidoSentado(auxBarbero);
							if(!barberia.colaDeEspera.isEmpty()) {
								barberia.hayCola=false;
							}
						}
					}
					//ser atendidos 
					////////////////////////////////////chapuza
				}/*else if(auxBarbero!=-1&&atendido==false&&sentado==true&&barberia.hayCola==true&&barberia.colaDeEspera.get(0).idCliente==idCliente) {

					barberia.sillasDesocupadas++;
					atendido=true;
					System.out.println("el cliente sentado: "+idCliente+" es atendido por el barbero: "+auxBarbero+" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					barberia.colaDeEspera.remove(0);
					serAtendidoSentado(auxBarbero);
					if(!barberia.colaDeEspera.isEmpty()) {
						barberia.hayCola=false;
					}
					//serAtendidoSentado(auxBarbero);

				}*//*else {
					sentarse();

				}*/
				//sino sentarse 
				//cortarPelo
				//sentars
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("El cliente "+idCliente+" ha sido destruido.");
				System.exit(0);
			}
		}
		//barberia.clienteDejaUnaSilla();
		// TODO Auto-generated method stub

	}



	private synchronized void serAtendidoSentado(int auxBarbero) throws InterruptedException {
		barberia.getbarbersoDisponibles(auxBarbero).cortarElPeloSentado(idCliente);
		wait();

	}



	public synchronized void serAtendido(int barbero) throws InterruptedException {
		barberia.getbarbersoDisponibles(barbero).cortarElPelo(idCliente);
		wait();

	}



	public synchronized void sentarse() throws InterruptedException {

		if(barberia.sillasDesocupadas>0&&sentado==false&&atendido==false) {
			barberia.clienteOcupaUnaSilla(idCliente);
			this.sentado=true;
			barberia.hayCola=true;
			barberia.colaDeEspera.add(this);
		}else {
			if(atendido==false&&sentado==false) {
				System.out.println("El cliente "+idCliente+" se marcha sin ser atendido.");
				this.atendido=true;
			}

		}

	}


}
