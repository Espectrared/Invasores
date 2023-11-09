package com.mygdx.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
//comentario push
public class Principal extends Game {
	SpriteBatch dibujar;
	
	@Override
        
	public void create () {
		dibujar = new SpriteBatch();
		setScreen(new Screen_MM(this));
                        

	}
	public void dispose () {
		dibujar.dispose();
		
	}
        
}
