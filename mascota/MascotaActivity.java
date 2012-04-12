package com.proyecto.mascota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MascotaActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	EditText etNombre;
	Button enviarNombre;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		refLayouts();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private void refLayouts() {
		// TODO Auto-generated method stub
		etNombre = (EditText) findViewById(R.id.etNombre);
		enviarNombre = (Button) findViewById(R.id.bSend);
		enviarNombre.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String nombre;
		nombre = etNombre.getText().toString();
		Bundle tempBund = new Bundle();
		tempBund.putString("nombre", nombre);
		Intent tempInt = new Intent(this, PantallaDos.class);
		tempInt.putExtras(tempBund);
		startActivity(tempInt);
	}

}