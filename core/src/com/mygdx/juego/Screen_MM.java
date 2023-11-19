/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author rober
 */
public class Screen_MM extends Screen_Base {
Principal main;
Texture fondo_text;
long time,lastime=0;
BitmapFont font;
    Screen_MM(Principal main,BitmapFont font)
    {
        this.main=main;
        this.font=font;
        fondo_text= new Texture("fondo.jpg");
        
    }
    @Override
    public void render(float delta) {

		main.dibujar.begin();
                main.dibujar.draw(fondo_text, 0, 0);
                time=System.currentTimeMillis();
                if(time-lastime>1000)
                font.draw(main.dibujar, "Presiona espacio para continuar", 100, 400);
                lastime=System.currentTimeMillis();
                if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                    main.setScreen(new Gameplay_Screen(main,font));
                    
                }
               	main.dibujar.end();
                
                
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void show() {
    }
    
    
}
