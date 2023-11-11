/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

/**
 *
 * @author rober
 */
public class Gameplay_Screen extends Screen_Base{
    Principal main;
    Player player;
    Texture jugador_tex;
	
        Texture bala_text;
        Enemigo enemigo;
        Bala bala;
        int push;//variable mamalona
        Texture enemigo_tex;
        ArrayList<Bala> bullets = new ArrayList<>();
        long last_shot=0;
        static final int PLAYER_VEL=300;
        static final int FRE_DISPARO=500;
        BitmapFont font;
   
        public  Gameplay_Screen(Principal main,BitmapFont font)
   {
       this.main=main;
       this.font = font;
      jugador_tex = new Texture("Player.png");
                bala_text= new Texture("Bullet.png");
                enemigo_tex = new Texture("Alien.png");
                player = new Player(new Vector2(300,15),jugador_tex,PLAYER_VEL );
                enemigo = new Enemigo(new Vector2(300,600),enemigo_tex,PLAYER_VEL);
             
   }
    @Override
    public void render(float delta)
    {float deltaTime= Gdx.graphics.getDeltaTime();
                gameLogic(deltaTime);
		ScreenUtils.clear(0, 0, 0, 1);
		main.dibujar.begin();
              font.draw(main.dibujar,"sasasa",100,100);
		player.draw(main.dibujar);
               
                    for(Bala bala:bullets)
                    {
                        bala.draw(main.dibujar);
                    }
                
                if(enemigo !=null){
                 enemigo.draw(main.dibujar);   
                }
		main.dibujar.end();
                
	}
       
           public void gameLogic(float deltaTime)
        {
            if(Gdx.input.isKeyPressed(Input.Keys.A))
            {
              player.moveleft(deltaTime);
            }
              if(Gdx.input.isKeyPressed(Input.Keys.D))
            {
              player.moveRight(deltaTime);
            }
              
           if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                  long time=System.currentTimeMillis();
                  
                  if((time-last_shot)>=FRE_DISPARO){
                bullets.add(new Bala(new Vector2(player.getPosition().x+10,
                        player.getPosition().y+10),
                        bala_text,1000));
                
                                last_shot=System.currentTimeMillis();

                  }
                
                 
              }
            balaLogic(deltaTime);
            playerLogic(deltaTime);
            if(enemigo !=null){
             enemyLogic(deltaTime);
               
            }
        }
       
	
       
	
    @Override
    public void hide()    
    {
        
    }
    @Override
    public void show()
    {
        
    }
    public void playerLogic(Float deltaTime)
        {
            player.update(deltaTime);
        }
	
        public void balaLogic(float daltaTime)
        {
            if(bullets.isEmpty()){
                return;
            }
            for(int x=0;x<bullets.size();x++)
            {
                bullets.get(x).update(daltaTime);
                if(bullets.get(x).position.y>Gdx.graphics.getHeight())
                {
                    bullets.remove(x);
                    System.out.println("removido");
                }
            }
            if(enemigo!=null){
                for(Bala bala : bullets){
                         if(bala.isCollision(enemigo)){
                enemigo = null;
                bala = null;
            }    
                }
     
            }
      
            
        }
        public void enemyLogic(float deltaTime)
        {
            if(enemigo !=null){
             enemigo.update(deltaTime);   
            }
        }
    
}
