package com.proyecto.mascota;

import com.proyecto.mascota.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaDos extends Activity implements OnClickListener {

	/* Campos en java que se refereciaran a nuestro layout xml */
	TextView nombre, vida, hambre, felicidad, nivel, exper, sig_nivel, stamina;
	Button handle, alimentar, jugar, entrenar, salir;
	/* Objetos que manejan la logica de la mascota */
	General general;
	Mascota mascota;
	Niveles niveles;
	/*
	 * Variable de tipo boolean que se usa para finalizar el proceso en
	 * background
	 */
	boolean requestToExit = false;
	/* String que recibe el nombre por medio del bundle */
	String camb_Nombre;
	Intent regresa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);
		inicializaLogicaObjetos();
		setViews();
		setButtons();
		cambiarValores();
		// Obtener nombre tecleado por usuario
		Bundle tempBund = getIntent().getExtras();
		camb_Nombre = tempBund.getString("nuevoNombre");
		// Cambiar nombre
		nombre.setText(camb_Nombre);
		AsyncTask();
		regresa = new Intent(this, MascotaActivity.class);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bAlim:
			general.alimentar(mascota.getVida(), mascota.getHambre(),
					mascota.getFelicidad());
			toastNotifications();
			cambiarValores();
			break;

		case R.id.bJug:
			general.jugar(mascota.getVida(), mascota.getHambre(),
					mascota.getFelicidad(), mascota.getStamina());
			cambiarValores();
			break;

		case R.id.bEntr:
			general.entrenar(mascota.getHambre(), mascota.getStamina());
			cambiarValores();
			break;

		case R.id.bSalir:
			finish();
			break;
		}
		validaVivo(camb_Nombre);
	}

	private void toastNotifications() {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		if (mascota.getVida() == mascota.getTopVida()) {
			CharSequence text = "Vida Completa!";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		if (mascota.getFelicidad() == mascota.getTopFelic()) {
			CharSequence text = "Mascota completamente feliz!";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		if (mascota.getStamina() == 0) {
			CharSequence text = "Stamina agotada!";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		if (mascota.getHambre() == mascota.getTopHambre()) {
			CharSequence text = "Mascota ha muerto!";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}// fin toastNotification

	public void inicializaLogicaObjetos() {
		general = new General();
		mascota = new Mascota();
		niveles = new Niveles();
		general.setGeneral(niveles, mascota);
		mascota.setMascota(general, mascota);
		mascota.crearMascota(null, niveles);
	}

	private void setViews() {
		nombre = (TextView) findViewById(R.id.tvNombre);
		vida = (TextView) findViewById(R.id.tvVida);
		hambre = (TextView) findViewById(R.id.tvHambre);
		felicidad = (TextView) findViewById(R.id.tvFelicidad);
		nivel = (TextView) findViewById(R.id.tvNivel);
		exper = (TextView) findViewById(R.id.tvExperiencia);
		sig_nivel = (TextView) findViewById(R.id.tvSigNiv);
		stamina = (TextView) findViewById(R.id.tvStam);
	}

	private void setButtons() {
		//handle = (Button) findViewById(R.id.handle);
		alimentar = (Button) findViewById(R.id.bAlim);
		jugar = (Button) findViewById(R.id.bJug);
		entrenar = (Button) findViewById(R.id.bEntr);
		salir = (Button) findViewById(R.id.bSalir);
		// Hacer que los botones sean "escuchables"
		//handle.setOnClickListener(this);
		alimentar.setOnClickListener(this);
		jugar.setOnClickListener(this);
		entrenar.setOnClickListener(this);
		salir.setOnClickListener(this);

	}

	private void cambiarValores() {
		vida.setText(mascota.getVida() + "/" + mascota.getTopVida());
		hambre.setText(mascota.getHambre() + "/" + mascota.getTopHambre());
		felicidad.setText(mascota.getFelicidad() + "/" + mascota.getTopFelic());
		nivel.setText("" + niveles.getNivel());
		exper.setText("" + niveles.getExperiencia());
		sig_nivel.setText("" + niveles.getSigNivel());
		stamina.setText(mascota.getStamina() + "/" + mascota.getTopStamina());
	}

	private void AsyncTask() {
		new AsyncTask<Void, Void, Integer>() {
			@Override
			protected Integer doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				long startGeneral = System.currentTimeMillis();
				long startStamina = System.currentTimeMillis();
				while (!requestToExit) {
					long end = System.currentTimeMillis();
					long diff1 = end - startStamina;
					long diff2 = end - startGeneral;
					if (diff1 >= 3000) {
						mascota.modStaminaHilo();
						publishProgress();
						startStamina = System.currentTimeMillis();
					}
					if (diff2 >= 30000) {
						mascota.modHilo();
						publishProgress();
						startGeneral = System.currentTimeMillis();
					}
				}
				return 0;
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
				cambiarValores();

			}

		}.execute();
	}

	private void validaVivo(String confirmaNombre) {
		if (mascota.getHambre() == mascota.getTopHambre()
				|| mascota.getVida() == 0 || mascota.getFelicidad() == 0) {
			// Create the dialog box

			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			// Set the message to display
			alertbox.setMessage("Su macota " + confirmaNombre
					+ " ha muerto,desea otra mascota?");
			// Set a positive/yes button and create a listener
			alertbox.setPositiveButton("Si",
					new DialogInterface.OnClickListener() {
						// Click listener
						public void onClick(DialogInterface arg0, int arg1) {
							startActivity(regresa);
							finish();
						}
					});
			// Set a negative/no button and create a listener
			alertbox.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						// Click listener
						public void onClick(DialogInterface arg0, int arg1) {
							finish();
						}
					});
			// display box
			alertbox.show();
		}
	}
}
