package com.proyecto.mascota;

import java.util.*;

public class Niveles {

	/* Random para hacer las cosas mas interesantes */
	Random rand = new Random();

	/* Atributos de la clase */
	private int nivel;
	private int sigNivel;
	private int exp;
	private Mascota tempMascota;

	/* Inicializar los atributos */
	public void setNivel(Mascota tempMascota) {
		this.tempMascota = tempMascota;
		this.nivel = 1;
		this.sigNivel = 10;
		this.exp = 0;
	}

	/* Getters */
	public int getNivel() {
		return this.nivel;
	}

	public int getSigNivel() {
		return this.sigNivel;
	}

	public int getExperiencia() {
		return this.exp;
	}

	/* Metodo para subir de nivel cuando se requiera */
	public void subNivel() {
		this.exp += ((rand.nextInt(10000) % 5) + 1);
		if (this.exp >= this.sigNivel) {
			tempMascota.modAtrNivel();
			this.nivel++;
			formula();
		}
	}

	/*
	 * Formula que obtiene cuantos puntos de experiencia se necesitan para el
	 * siguiente nivel ?Implementar una mejor formula
	 */
	public void formula() {
		// Prueba formula 1:
		this.sigNivel *= 2;
		// Prueba formula 2:
		// this.sigNivel += (this.sigNivel * .2);
	}
}