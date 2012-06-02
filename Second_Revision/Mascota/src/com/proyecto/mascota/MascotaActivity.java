package com.proyecto.mascota;

import com.proyecto.mascota.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MascotaActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	/* Creamos los campos de nuestro layout para despues referenciarlos */
	EditText etNombre;
	Button enviarNombre;
	boolean pasar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * El layout de nuestra actividad sera el archivo xml "main",localizado
		 * en la carpeta de resources[res]
		 */
		setContentView(R.layout.main);
		refLayouts();

	}

	/* Metodo que forma parte de nuestro ciclo de vida de la actividad */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	/*
	 * Metodo que sera llamado al darse click sobre nuestro boton,es
	 * implementado ya que imlementamos OnClickListener en la clase
	 */
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String nombre;
		nombre = etNombre.getText().toString();
		/*
		 * Comparar la cadena con un null,para obligar al usuario a darle un
		 * nombre
		 */

		if (stringNull(nombre))
			confirmaNombre(nombre);
		/*
		 * if (pasar) { Bundle tempBund = new Bundle();
		 * tempBund.putString("nombre", nombre); Intent tempInt = new
		 * Intent(this, PantallaTabs.class); tempInt.putExtras(tempBund);
		 * startActivity(tempInt); }
		 */

	}


	private void confirmaNombre(final String confirmaNombre) {
		// Create the dialog box

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Set the message to display
		alertbox.setMessage("Confirma el nombre de: " + confirmaNombre);
		// Set a positive/yes button and create a listener
		alertbox.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			// Click listener
			public void onClick(DialogInterface arg0, int arg1) {
				pasarNombre(confirmaNombre);
			}
		});
		// Set a negative/no button and create a listener
		alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
			// Click listener
			public void onClick(DialogInterface arg0, int arg1) {
			}
		});
		// display box
		alertbox.show();

	}

	private void pasarNombre(String confNombre) {
		Bundle tempBund = new Bundle();
		tempBund.putString("nombre", confNombre);
		// Intent tempInt = new Intent(this, PantallaTabs.class);
		Intent tempInt = new Intent(this, SelecPoke.class);
		// Intent tempInt = new Intent(this,AnimacionGif.class);
		tempInt.putExtras(tempBund);
		startActivity(tempInt);
	}

	private boolean stringNull(String nombreIsNull) {
		if ("".equals(nombreIsNull)) {
			Context context = getApplicationContext();
			CharSequence text = "Ingrese un nombre para su mascota";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			return false;
		} else
			return true;
	}

	/*
	 * Metodo con el cual referenciamos nuestros campos en java con los campos
	 * en el xml
	 */
	private void refLayouts() {
		// TODO Auto-generated method stub
		etNombre = (EditText) findViewById(R.id.etNombre);
		enviarNombre = (Button) findViewById(R.id.bSend);
		enviarNombre.setOnClickListener(this);
	}

}