package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.mygdx.juego.Gameplay_Screen.FRE_DISPARO;
import static com.mygdx.juego.Gameplay_Screen.PLAYER_VEL;
import java.util.ArrayList;
import java.util.Random;

public class Nivel2 extends Screen_Base {
Principal main;
    Player player;
    Texture jugador_tex;
    String id;
    Jefe jefe;
    Random random= new Random();
        Texture bala_text;
       // Enemigo enemigo;
        ArrayList<Enemigo> enemies= new ArrayList<>();
        Bala bala;
        int push;//variable mamalona
        Texture enemigo_tex;
        Texture jefe_tex;
        ArrayList<Bala> bullets = new ArrayList<>();
        long last_shot=0;
        static final int PLAYER_VEL=300;
        static final int FRE_DISPARO=500;
        BitmapFont font;
        int score;
    public Nivel2(Principal main, BitmapFont font,int score) {
        this.main=main;
       this.font = font;
      jugador_tex = new Texture("Player.png");
                bala_text= new Texture("Bullet.png");
                enemigo_tex = new Texture("Alien.png");
                player = new Player(new Vector2(300,15),jugador_tex,PLAYER_VEL );
                enemies.add( new Enemigo(new Vector2(300,600),enemigo_tex,PLAYER_VEL));
                enemies.add( new Enemigo(new Vector2(200,400),enemigo_tex,PLAYER_VEL));
             enemies.add( new Enemigo(new Vector2(500,600),enemigo_tex,PLAYER_VEL));
             this.score=score;
    }

    @Override
    public void render(float delta) {
      float deltaTime= Gdx.graphics.getDeltaTime();
                gameLogic(deltaTime);
		ScreenUtils.clear(0, 0, 0, 1);
		main.dibujar.begin();
		player.draw(main.dibujar);
                    for(Bala bala:bullets)
                    {
                        bala.draw(main.dibujar);
                    }
                if( !enemies.isEmpty()){
                    for(int i=0;i< enemies.size();i++)
                    {
                        enemies.get(i).draw(main.dibujar);
                    }
                }
                font.draw(main.dibujar, "Puntaje "+score, 50, 680);
                font.draw(main.dibujar, "Nivel 2", 350, 680);
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
            if(!enemies.isEmpty()){
             enemyLogic(deltaTime);
            }

        }
    

    public void hide() {}
    public void show() {}

     public void playerLogic(Float deltaTime){
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
            if(!enemies.isEmpty()){
                for(int i=0;i< enemies.size();i++){
                    for(int j=0;j<bullets.size();j++){
                         if(bullets.get(j).isCollision(enemies.get(i))){
                        enemies.remove(i);
                        bullets.remove(j);
                        score+=100;
                        main.cliente.enviar(main.id+";jefe");
                         }
                }
                }
            }     
        }
     public void enemyLogic(float deltaTime)
        {
            if(enemies.isEmpty()){
            return;   
            }
               for(int i=0;i< enemies.size();i++)
               {
                   enemies.get(i).update(deltaTime);
               }
        }
}

