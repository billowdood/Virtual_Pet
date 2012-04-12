package com.proyecto.mascota;

/*Clase con metodos generales de la mascota*/
/*Cambios:
 26 Marzo,2012:
 1)Al momento de entrenar,que aumente su hambre

 27 Marzo,2012:
 1)Se agrego que se reduzca stamina en el metodo  de entrenar
 2)Se agrego que baje su stamina cuando juega
 3)Se agrego el validatope de stamina
 *Quitar de los metodos de alimentar y jugar el parametro Mascota,no tiene caso recibirlo de parametro si ya se asigna con el setGeneral[DONE]*

 11 Abril,2012:
 1)Se elimnaron todas las referencias a la clase Imprimir,y todas las impresiones ya que no son necesarias
 */
import java.util.*;

public class General {

	/* Random para hacer las cosas mas interesantes */
	Random genRand = new Random();

	/* Atributos de la clase,son objetos que se referenciaran */
	private Mascota tempMascota;
	private Niveles tempNivel;

	/* Referenciar los objetos de tipo mascota y de tipo niveles */
	public void setGeneral(Niveles tempNivel, Mascota tempMascota) {
		this.tempNivel = tempNivel;
		this.tempMascota = tempMascota;
	}

	/*
	 * Metodo que alimenta a la mascota,se valida que no este en el tope y hace
	 * las sumas y restas necesarias Recibe como parametros a la Mascota junto
	 * con su vida,hambre y felicidad
	 */
	public void alimentar(int subVida, int bajHambre, int subFelicidad) {
		int validVida, validHambre, validFelicidad;
		/*
		 * If que compara si el valor que entra de vida es menor a lo maximo que
		 * se puede tener,para poder aumentar su vida,si no,le deja el valor del
		 * maximo
		 */
		if (subVida < tempMascota.getTopVida()) {
			validVida = tempMascota.getVida()
					+ ((genRand.nextInt(10000) % 5) + 1);
			validVida = validaTope(validVida, 1);
			tempMascota.setVida(validVida);
		}
		/*
		 * If que realiza lo mismo que el If para la vida,pero con el atributo
		 * de felicidad
		 */
		if (subFelicidad < tempMascota.getTopFelic()) {
			validFelicidad = tempMascota.getFelicidad()
					+ ((genRand.nextInt(10000) % 5) + 1);
			validFelicidad = validaTope(validFelicidad, 3);
			tempMascota.setFelicidad(validFelicidad);
		}
		/*
		 * If que verifica que el hambre sea mayor a 0 para poder reducir el
		 * atributo
		 */
		if (bajHambre > 0) {
			validHambre = tempMascota.getHambre()
					- ((genRand.nextInt(10000) % 5) + 1);
			validHambre = validaMin(validHambre);
			tempMascota.setHambre(validHambre);
		}
	}

	/*
	 * Metodo que simula que la mascota juegue,se valida que los atributos no
	 * esten en su tope y hace las sumas y restas necesarias
	 */
	public void jugar(int bajVida, int subHambre, int subFelicidad,
			int bajStamina) {
		int validVida, validHambre, validFelicidad, validStamina;
		/*
		 * Los if siguen la misma logica que con el metodo de alimentar Se pone
		 * un if de stamina == 0,para validar que tenga stamina para jugar
		 */
		if (bajStamina == 0) {
			return;
		}

		if (bajStamina > 0) {
			validStamina = tempMascota.getStamina()
					- ((genRand.nextInt(10000) % 2) + 1);
			validStamina = validaMin(validStamina);
			tempMascota.setStamina(validStamina);
		}

		if (bajVida > 0) {
			validVida = tempMascota.getVida()
					- ((genRand.nextInt(10000) % 5) + 1);
			validVida = validaMin(validVida);
			tempMascota.setVida(validVida);
		}
		if (subHambre < tempMascota.getTopHambre()) {
			validHambre = tempMascota.getHambre()
					+ ((genRand.nextInt(10000) % 5) + 1);
			validHambre = validaTope(validHambre, 2);
			tempMascota.setHambre(validHambre);
		}
		if (subFelicidad < tempMascota.getTopFelic()) {
			validFelicidad = tempMascota.getFelicidad()
					+ ((genRand.nextInt(10000) % 5) + 1);
			validFelicidad = validaTope(validFelicidad, 3);
			tempMascota.setFelicidad(validFelicidad);
		}
	}

	/* Se agrego subHambre para que aumente cuando entrene */
	public void entrenar(int subHambre, int bajStamina) {
		int validHambre, validStamina;
		/*
		 * Si stamina esta en 0 significa que no puede entrenar, y se muestra en
		 * pantalla que ya no puedes entrenar mas,que esperes a que recuper e la
		 * mascota algo de stamina Se puso al principio para que si no puede
		 * entrenar,no haga nada ya que si se quedaba al final como
		 * originalmente se tenia, aunque ya no pudiera subir de nivel y bajar
		 * stamina(por ser 0) como quiera le daba hambre mientras la condicion
		 * de hambre se cumpliera
		 */
		if (bajStamina == 0) {
			return;
		}
		if (bajStamina > 0) {
			validStamina = tempMascota.getStamina()
					- ((genRand.nextInt(10000) % 3) + 1);
			validStamina = validaMin(validStamina);
			tempMascota.setStamina(validStamina);
			tempNivel.subNivel();
		}
		if (subHambre < tempMascota.getTopHambre()) {
			validHambre = tempMascota.getHambre()
					+ ((genRand.nextInt(10000) % 5) + 1);
			validHambre = validaTope(validHambre, 2);
			tempMascota.setHambre(validHambre);
		}
	}

	/*
	 * Metodos de validacion,tanto tope maximo como tope minimo Se utiliza como
	 * parametros: temp -> valor del atributo con el cual se accese al metodo
	 * cambia -> se utiliza en un switch para poder manejar por separado el tope
	 * del atributo con el cual se accese al metodo donde: 1->TopVida
	 * 2->TopHambre 3->TopFelicidad 4->TopStamina
	 */
	public int validaTope(int temp, int cambia) {
		/*
		 * ~Para cada caso: si el resultado de la adicion es mayor al
		 * tope,regresara el valor del tope;si no,regresara el valor de la
		 * adicion*Adicion realizada en los metodos de alimentar o jugar*
		 */
		switch (cambia) {

		case 1:
			if (temp > tempMascota.getTopVida()) {
				return tempMascota.getTopVida();
			} else {
				return temp;
			}

		case 2:
			if (temp > tempMascota.getTopHambre()) {
				return tempMascota.getTopHambre();
			} else {
				return temp;
			}

		case 3:
			if (temp > tempMascota.getTopFelic()) {
				return tempMascota.getTopFelic();
			} else {
				return temp;
			}

		case 4:
			if (temp > tempMascota.getTopStamina()) {
				return tempMascota.getTopStamina();
			} else {
				return temp;
			}
		}
		return temp;/* dunno if necessary */
	}

	/* Metodo de validacion para ver que no bajen de "0" los atributos */
	public int validaMin(int temp) {
		if (temp < 0) {
			return 0;
		}
		return temp;
	}
}