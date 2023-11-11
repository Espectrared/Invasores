/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author rober
 */
public class Screen_MM extends Screen_Base {
Principal main;
Texture fondo_text;

    Screen_MM(Principal main)
    {
        this.main=main;
        fondo_text= new Texture("fondo.jpg");
        
    }
    @Override
    public void render(float delta) {

		main.dibujar.begin();
                main.dibujar.draw(fondo_text, 0, 0);
               	main.dibujar.end();
                
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void show() {
    }
    
    
}
