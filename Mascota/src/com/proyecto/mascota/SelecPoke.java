package com.proyecto.mascota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SelecPoke extends Activity implements OnClickListener,OnCheckedChangeListener {

	RadioButton radio_eevee, radio_pikachu, radio_slowpoke;
	RadioGroup grupo_seleccion;
	Button aceptar;
	TextView nombre;
	String obtener_nombre;
	Intent intent;
	int selec_pokemon;// 1-pikachu,2-slowpoke,3-eevee

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selec_class);
		// Obtener nombre de la actividad principal
		Bundle obtenNombre = getIntent().getExtras();
		obtener_nombre = obtenNombre.getString("nombre");
		refLayouts();
	}

	private void refLayouts() {
		radio_eevee = (RadioButton) findViewById(R.id.rb_eevee);
		radio_pikachu = (RadioButton) findViewById(R.id.rb_pikachu);
		radio_slowpoke = (RadioButton) findViewById(R.id.rb_slowpoke);
		aceptar = (Button) findViewById(R.id.btn_selec_class);
		grupo_seleccion = (RadioGroup) findViewById(R.id.rg_seleccion);
		grupo_seleccion.setOnCheckedChangeListener(this);
		aceptar.setOnClickListener(this);
		radio_eevee.setOnClickListener(this);
		radio_pikachu.setOnClickListener(this);
		radio_slowpoke.setOnClickListener(this);
		nombre = (TextView) findViewById(R.id.tv_selec_mascota);
		// Modificar el encabezado
		nombre.setText("Que pokemon sera " + obtener_nombre);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.btn_selec_class:
			// Nuevo bundle para pasar el string
			Bundle nuevoNombre = new Bundle();
			nuevoNombre.putString("nuevoNombre", obtener_nombre);
			nuevoNombre.putInt("seleccion_pokemon", selec_pokemon);
			// Do the same for the other tabs
			intent = new Intent(this, PantallaTabs.class);
			intent.putExtras(nuevoNombre);
			startActivity(intent);
			break;
		/*
		 * case R.id.rb_pikachu: selec_pokemon = 1; break;
		 * 
		 * case R.id.rb_slowpoke: selec_pokemon = 2; break;
		 * 
		 * case R.id.rb_eevee: selec_pokemon = 3; break;
		 */
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rb_pikachu:
			selec_pokemon = 1;
			break;

		case R.id.rb_slowpoke:
			selec_pokemon = 2;
			break;

		case R.id.rb_eevee:
			selec_pokemon = 3;
			break;
		}
		
	}

}
