package com.proyecto.mascota;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaDos extends Activity implements OnClickListener {

	TextView nombre, vida, hambre, felicidad, nivel, exper, sig_nivel, stamina;
	Button handle, alimentar, jugar, entrenar, salir;
	General general;
	Mascota mascota;
	Niveles niveles;
	String camb_Nombre;

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
		camb_Nombre = tempBund.getString("nombre");
		// Cambiar nombre
		nombre.setText(camb_Nombre);
	}

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
		handle = (Button) findViewById(R.id.handle);
		alimentar = (Button) findViewById(R.id.bAlim);
		jugar = (Button) findViewById(R.id.bJug);
		entrenar = (Button) findViewById(R.id.bEntr);
		salir = (Button) findViewById(R.id.bSalir);
		// Hacer que los botones sean "escuchables"
		handle.setOnClickListener(this);
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
}
