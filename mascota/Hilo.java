package com.proyecto.mascota;

/*Clase del hilo de ejecucion*/
/*Marzo 27,2012:
 1)Se agrego que cada 30 segundos recuperes 2 puntos de stamina
 */

import java.util.*;

public class Hilo implements Runnable {

	/* Atributos de la clase,solo se necesitan dos objetos */
	private Mascota tempMascota;

	/* Referenciar los atributos */
	public void setHilo(Mascota tempMascota) {
		this.tempMascota = tempMascota;

	}

	// Metodo que usa el hilo para existir,llamado en main
	/*
	 * La logica seguida fue: ~Decidi que cada 5 minutos,se haga un "refresh" de
	 * los estados de la mascota,es decir,que baje su vida,suba su hambre,etc.
	 * ~Esto se logro a partir de tomar la hora del sistema y en un ciclo
	 * infinito ir haciendo diferencias entre otra hora del sistema,asi que
	 * cuando la diferencia sea 5 minutos (300000 milisegundos) se modificaran
	 * los atributos.
	 */
	public void run() {
		long startGeneral = System.currentTimeMillis();
		long startStamina = System.currentTimeMillis();
		while (true) {
			long end = System.currentTimeMillis();
			long diff1 = end - startStamina;
			long diff2 = end - startGeneral;
			if (diff1 >= 30000) {
				tempMascota.modStaminaHilo();
				System.out.println("\n\n~~He recuperado energia!~~\n\n");

				startStamina = System.currentTimeMillis();
			}
			if (diff2 >= 300000) {
				tempMascota.modHilo();
				System.out.print("\n\n~~Pasaron 5 minutos~~\n\n");

				startGeneral = System.currentTimeMillis();
			}
		}
	}

}
