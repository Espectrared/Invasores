package com.mygdx.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Principal extends ApplicationAdapter {
	SpriteBatch dibujar;
	Texture jugador_tex;
	Player player;
        Texture bala_text;
        Enemigo enemigo;
        Bala bala;
        Texture enemigo_tex;
        static final int PLAYER_VEL=300;
	@Override
	public void create () {
		dibujar = new SpriteBatch();
		jugador_tex = new Texture("Player.png");
                bala_text= new Texture("Bullet.png");
                enemigo_tex = new Texture("Alien.png");
                player = new Player(new Vector2(350,15),jugador_tex,PLAYER_VEL );
                enemigo = new Enemigo(new Vector2(300,600),enemigo_tex,PLAYER_VEL);
	}

	@Override
	public void render () {
                float deltaTime= Gdx.graphics.getDeltaTime();
                gameLogic(deltaTime);
		ScreenUtils.clear(0, 0, 0, 1);
		dibujar.begin();
                if(bala != null){
                bala.draw(dibujar);
                }
		player.draw(dibujar);
                enemigo.draw(dibujar);
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
              
              if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
                bala = new Bala(new Vector2(player.getPosition().x+10,player.getPosition().y+10),bala_text,1000);
               
              }
            balaLogic(deltaTime);
            playerLogic(deltaTime);
            enemyLogic(deltaTime);
            
        }
        public void playerLogic(Float deltaTime)
        {
            player.update(deltaTime);
        }
	
        public void balaLogic(float daltaTime)
        {
            if(bala  == null){
                return;
            }
            bala.update(daltaTime);
            
        }
        public void enemyLogic(float deltaTime)
        {
            enemigo.update(deltaTime);
        }
	@Override
	public void dispose () {
		dibujar.dispose();
		jugador_tex.dispose();
                bala_text.dispose();
                enemigo_tex.dispose();
	}
        
}
