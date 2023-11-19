package com.mygdx.juego;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver_Screen extends Screen_Base {
    Principal main;
    BitmapFont font;
    int score;
    public GameOver_Screen(Principal main,BitmapFont font, int score){
        this.font = font;
        this.font=font;
        this.score = score;
        this.main = main;
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        main.dibujar.begin();
        font.draw(main.dibujar, "Game Over", 100, 400);
        font.draw(main.dibujar, "Score: " + score, 100, 500);
        main.dibujar.end();
    }

    @Override
    public void hide() {
       
    }

    @Override
    public void show() {
        
    }
    
}
