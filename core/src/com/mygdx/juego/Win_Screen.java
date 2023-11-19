/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author Be
 */
public class Win_Screen extends Screen_Base{

     Principal main;
    BitmapFont font;
    int score;
    public Win_Screen(Principal main,BitmapFont font, int score){
        this.font = font;
        this.font=font;
        this.score = score;
        this.main = main;
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        main.dibujar.begin();
        font.draw(main.dibujar, "Ganaste!", 100, 400);
        main.dibujar.end();
    }


    @Override
    public void hide() {
    }

    @Override
    public void show() {
        
    }
    
}
