package com.proyecto.mascota;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

public class AnimacionGif extends Activity{

	WebView animacion;
	ImageView img;
	int hacer_cambio_animacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animacion_gif);
		animacion = (WebView) findViewById(R.id.animacion_gif_yei);
		//animacion.loadUrl("file:///android_res/drawable/p.gif");
		Bundle cambiar_animacion = getIntent().getExtras();
		hacer_cambio_animacion = cambiar_animacion.getInt("seleccion_pokemon");
		switch (hacer_cambio_animacion) {

		case 1:
			//img.setBackgroundResource(R.drawable.frame_animation);
			animacion.loadUrl("file:///android_res/drawable/p.gif");
			break;

		case 2:
			//img.setBackgroundResource(R.drawable.ic_animacion_slowpoke);
			animacion.loadUrl("file:///android_res/drawable/s.gif");
			break;

		case 3:
			//img.setBackgroundResource(R.drawable.ic_animacion_eevee);
			animacion.loadUrl("file:///android_res/drawable/e.gif");
			break;
		}
		// Get the background, which has been compiled to an AnimationDrawable
		// object.
		//frameAnimation = (AnimationDrawable) img.getBackground();
	}

	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus)
			// Start the animation (looped playback by default).
			frameAnimation.start();
	}

}
*/
}
