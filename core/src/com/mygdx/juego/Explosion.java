/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion {	
	private ParticleEffect effect;

	public Explosion(float x, float y) {				
		
		effect = new ParticleEffect();
                // Cargamos el efecto, el primero par�metro es cual es el fichero de la part�cula,
                // el segundo par�metro es la carpeta donde se encuentra la imagen usada
		effect.load(Gdx.files.internal("explosion.p"), Gdx.files.internal(""));
                startEffect(x,y);
	}
		
	public void draw(SpriteBatch batch, float delta) {
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
                // Dibujamos el efecto
		effect.draw(batch, delta);		
	}

	public void startEffect(float x, float y) {
                // Situamos el efecto en donde se ha hecho click
		effect.setPosition(x, y);
                // Iniciamos el efecto
		effect.start();
	}
	
	public void dispose() {		
		effect.dispose();
	}

	
}