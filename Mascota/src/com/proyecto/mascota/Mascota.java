package com.proyecto.mascota;

/*
 Clase "Mascota",la cual incluye metodos escenciales para que la Mascota viva.
 */

/*
 Modificaciones:
 ~26 Marzo,2012~
 1)Se agrego una temporal de mascota
 2)En el metodo ver muerto,en la condicion se tenia "if(vida <= 0 || hambre >= 50 || felicidad <= 0)",se modifico el hambre >= 50 por el getter del tope del hambre;debiado a que el tope varia cada nivel,por tanto,si se llegaba al tope del hambre antes la mascota no moria

 ~27 Marzo,20120~
 1)Se agrego atributo "stamina"
 2)Se agrego la inicializacion del stamina en el metodo crearMascota
 3)Se agrego el tope de "stamina"
 4)Setters y getters para los atributos de stamina [stamina y topStamina]
 5)Que cuando la mascota se suicide,su stamina tambien regrese a 0
 6)Se agrego metodo de modStaminaHilo,el cual es con el que se sube el stamina a traves del hilo
 7)En modAtrNivel,se agrego que el tope de stamina aumente conforme avanzas de nivel

 11 Abril,2012:
 1)Se elimnaron todas las referencias a la clase Imprimir,y todas las impresiones ya que no son necesarias
 */

import java.util.*;

public class Mascota {

	/* Random utilizado para que el juego sea mas interesante */
	Random genRand = new Random();

	/* Atributos de la mascota */
	private int vida;
	private int hambre;
	private int felic;
	private int stamina;
	private int topStamina;
	private int topVida;
	private int topHambre;
	private int topFelic;
	/* Atributos que son objetos */
	private String nombre;
	private General tempGeneral;

	private Mascota tempMascota;

	/* ~~Metodos de la mascota~~ */

	/*
	 * Metodo encargado de dar "vida" a la mascota,inicializar los
	 * atributos;recibe como parametros el nombre de la mascota asignado en la
	 * clase Principal y un objeto de tipo Niveles para manejar los niveles de
	 * la mascota ?Deberia de modificar los topes a que tambien sean random
	 */
	public void crearMascota(String asignarNombre, Niveles tempNivel) {
		this.nombre = asignarNombre;
		this.vida = (genRand.nextInt(10000) % 25) + 1;
		this.hambre = (genRand.nextInt(10000) % 25) + 1;
		this.felic = (genRand.nextInt(10000) % 25) + 1;
		this.stamina = (genRand.nextInt(10000) % 50) + 1;
		this.topVida = 25;
		this.topHambre = 25;
		this.topFelic = 25;
		this.topStamina = 50;
		tempNivel.setNivel(this);
	}

	/* Getters */
	public String getNombre() {
		return this.nombre;
	}

	public int getVida() {
		return this.vida;
	}

	public int getHambre() {
		return this.hambre;
	}

	public int getFelicidad() {
		return this.felic;
	}

	public int getStamina() {
		return this.stamina;
	}

	public int getTopVida() {
		return this.topVida;
	}

	public int getTopHambre() {
		return this.topHambre;
	}

	public int getTopFelic() {
		return this.topFelic;
	}

	public int getTopStamina() {
		return this.topStamina;
	}

	/* Setters */
	public void setMascota(General tempGeneral, Mascota tempMascota) {
		this.tempGeneral = tempGeneral;

		this.tempMascota = tempMascota;
	}

	public void setVida(int temp) {
		this.vida = temp;
	}

	public void setFelicidad(int temp) {
		this.felic = temp;
	}

	public void setHambre(int temp) {
		this.hambre = temp;
	}

	public void setStamina(int temp) {
		this.stamina = temp;
	}

	/* Metodo que modifica los atributos a traves del hilo de ejecucion */
	public void modHilo() {
		this.vida -= ((genRand.nextInt(10000) % 3) + 1);
		this.hambre += ((genRand.nextInt(10000) % 3) + 1);
		this.felic -= ((genRand.nextInt(10000) % 3) + 1);
		this.vida = tempGeneral.validaMin(this.vida);
		this.hambre = tempGeneral.validaTope(this.hambre, 2);
		this.felic = tempGeneral.validaMin(this.felic);
		/*
		 * Es necesario ver que si despues de que se modifiquen los atributos
		 * las mascota continua viva
		 */
		verMuerto(this.vida, this.hambre, this.felic);
	}

	/* Metodo que aumenta el stamina gracias al hilo de ejecucion */
	public void modStaminaHilo() {
		this.stamina += 2;
		this.stamina = tempGeneral.validaTope(this.stamina, 4);
	}

	/*
	 * Metodo que modifica el tope de los atributos,aumentando cada que el nivel
	 * avanza
	 */
	public void modAtrNivel() {
		this.topVida += ((genRand.nextInt(10000) % 3) + 1);
		this.topHambre += ((genRand.nextInt(10000) % 3) + 1);
		this.topFelic += ((genRand.nextInt(10000) % 3) + 1);
		this.topStamina += ((genRand.nextInt(10000) % 3) + 1);
	}

	/* Metodo para ver si la mascota esta vida o no */
	public void verMuerto(int vida, int hambre, int felicidad) {
		if (vida <= 0 || hambre >= tempMascota.getTopHambre() || felicidad <= 0) {
			System.out.println("Su mascota ha muerto :'(");
			//System.exit(0);
		}
	}

	/*
	 * Metodo de suicidio de la mascota. ?Implementarlo en el hilo,solo que
	 * cuando por el hilo baje toda su felicidad la mascota no muera,si no que
	 * se suicide
	 * ~12 Abril: No se utiliza,pero se puede utilizar
	 */
	public void suicidio() {
		this.vida = 0;
		this.hambre = 0;
		this.felic = 0;
		this.stamina = 0;
		/*
		 * imprEstad recibe "0" como parametro solo para como se imprimira,ir a
		 * metodo para mejor explicacion
		 */
	}

}