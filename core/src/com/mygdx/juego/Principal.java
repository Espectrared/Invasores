package com.mygdx.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
//comentario push
public class Principal extends Game {
	SpriteBatch dibujar;
	FreeTypeFontGenerator generator;
        FreeTypeFontParameter parameter;
        BitmapFont font;
        Client cliente;
        Thread cliente_t;
        String id;
        Gameplay_Screen gameplay;
	@Override
	public void create () {
                id =  Long.toString(System.currentTimeMillis())+Math.random();
                System.out.println("id: " + id);
		dibujar = new SpriteBatch();
                generator= new  FreeTypeFontGenerator (Gdx.files.internal("fuente.ttf"));
                parameter = new FreeTypeFontParameter();
                parameter.size=36;
                font= generator.generateFont(parameter);
                //setScreen(new Screen_MM(this,font));
                cliente = new Client(this);
                cliente_t = new Thread(cliente);
                cliente_t.start();
                gameplay = new Gameplay_Screen(this,font);
                setScreen(gameplay);
                

	}
	public void dispose () {
		dibujar.dispose();
		
	}
        
}
