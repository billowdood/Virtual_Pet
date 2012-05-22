package com.proyecto.mascota;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class PantallaTabs extends TabActivity {

	String getNombre;
	Intent intent;
	int hacer_cambio_pokemon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabslayout);
		// Get nombre de la pantalla principal
		Bundle obtenNombre = getIntent().getExtras();
		//getNombre = obtenNombre.getString("nombre");
		getNombre = obtenNombre.getString("nuevoNombre");
		
		hacer_cambio_pokemon = obtenNombre.getInt("seleccion_pokemon");
		
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Intent intent; // Reusable Intent for each tab

		Bundle cambiar_pokemon = new Bundle();
		cambiar_pokemon.putInt("seleccion_pokemon", hacer_cambio_pokemon);
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, AnimacionGif.class);
		intent.putExtras(cambiar_pokemon);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				//status
				.newTabSpec(getNombre)
				.setIndicator(getNombre,
						res.getDrawable(R.drawable.ic_tab_animacion))
				.setContent(intent);
		tabHost.addTab(spec);

		

		// Nuevo bundle para pasar el string a la actividad PantallaDos
		Bundle nuevoNombre = new Bundle();
		nuevoNombre.putString("nuevoNombre", getNombre);
		// Do the same for the other tabs
		intent = new Intent(this, PantallaDos.class);
		intent.putExtras(nuevoNombre);
		spec = tabHost
				//acciones
				.newTabSpec("acciones")
				.setIndicator("Acciones",
						res.getDrawable(R.drawable.ic_tab_stats))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(2);
	}
}
