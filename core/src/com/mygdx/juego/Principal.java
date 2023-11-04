package com.mygdx.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
//comentario push
public class Principal extends ApplicationAdapter {
	SpriteBatch dibujar;
	Texture jugador_tex;
	Player player;
        Texture bala_text;
        Enemigo enemigo;
        Bala bala;
        int push;//variable mamalona
        Texture enemigo_tex;
        ArrayList<Bala> bullets = new ArrayList<>();
        long last_shot=0;
        static final int PLAYER_VEL=300;
        static final int FRE_DISPARO=500;
	@Override
        
	public void create () {
		dibujar = new SpriteBatch();
		jugador_tex = new Texture("Player.png");
                bala_text= new Texture("Bullet.png");
                enemigo_tex = new Texture("Alien.png");
                player = new Player(new Vector2(300,15),jugador_tex,PLAYER_VEL );
                enemigo = new Enemigo(new Vector2(300,600),enemigo_tex,PLAYER_VEL);
	}

	@Override
	public void render () {
                float deltaTime= Gdx.graphics.getDeltaTime();
                gameLogic(deltaTime);
		ScreenUtils.clear(0, 0, 0, 1);
		dibujar.begin();
              
		player.draw(dibujar);
               
                    for(Bala bala:bullets)
                    {
                        bala.draw(dibujar);
                    }
                
                if(enemigo !=null){
                 enemigo.draw(dibujar);   
                }
		dibujar.end();
	}
        public void gameLogic(float deltaTime)
        {
            if(Gdx.input.isKeyPressed(Keys.A))
            {
              player.moveleft(deltaTime);
            }
              if(Gdx.input.isKeyPressed(Keys.D))
            {
              player.moveRight(deltaTime);
            }
              
           if(Gdx.input.isKeyPressed(Keys.SPACE)){
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
	@Override
	public void dispose () {
		dibujar.dispose();
		jugador_tex.dispose();
                bala_text.dispose();
                enemigo_tex.dispose();
	}
        
}
