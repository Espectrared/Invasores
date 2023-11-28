package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Random;

public class Nivel2 extends Screen_Base {
Principal main;
    Texture fondo;
    PowerUp vel;
    Player player;
    Texture jugador_tex;
    String id;
    Jefe jefe;

    Music sonido;
    Music muerte;
    Music musicon;
    Texture velocidad;
    private boolean permisoDisparo = false;
    private float tiempoInicio = 0;
    int contJefe = 0;
    ArrayList<BalaEnemigo> EnemyBullets = new ArrayList<>();
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
    Jefe jfe;
    ArrayList<Explosion> explosions = new ArrayList<>();
    public Nivel2(Principal main, BitmapFont font,int score) {
        fondo = new Texture(Gdx.files.internal("fondoahorasi.jpg"));
        this.main=main;
       this.font = font;
      jugador_tex = new Texture("Player.png");
        sonido = Gdx.audio.newMusic(Gdx.files.internal("pistola.mp3"));
        muerte = Gdx.audio.newMusic(Gdx.files.internal("muerte.mp3"));
        musicon = Gdx.audio.newMusic(Gdx.files.internal("musicadefondo.mp3"));
                bala_text= new Texture("Bullet.png");
        velocidad = new Texture("velocidad.png");
                enemigo_tex = new Texture("Alien.png");
        vel = new PowerUp(new Vector2(500, 700), velocidad, 300);
                player = new Player(new Vector2(300,15),jugador_tex,PLAYER_VEL );
                enemies.add( new Enemigo(new Vector2(300,600),enemigo_tex,PLAYER_VEL));
                enemies.add( new Enemigo(new Vector2(200,400),enemigo_tex,PLAYER_VEL));
                enemies.add( new Enemigo(new Vector2(500,600),enemigo_tex,PLAYER_VEL));
                jfe = new Jefe(new Vector2(500, 600), enemigo_tex, PLAYER_VEL);
                jfe.setScale(2.0f,2.0f);
             this.score=score;
    }
    public void render(float delta) {

        float deltaTime = Gdx.graphics.getDeltaTime();
        if (!permisoDisparo) {
            tiempoInicio += deltaTime;
            if (tiempoInicio >= 0.5f) {
                permisoDisparo = true;
            }
        }

        gameLogic(deltaTime);
        ScreenUtils.clear(0, 0, 0, 1);
        main.dibujar.begin();
        main.dibujar.draw(fondo,0,0,700,700);
        if (player != null) {
            player.draw(main.dibujar);
        }

        for (Bala bala : bullets) {
            bala.draw(main.dibujar);
        }


        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).draw(main.dibujar);
            }
        }
        if (!explosions.isEmpty()) {
            for (int i = 0; i < explosions.size(); i++) {
                explosions.get(i).draw(main.dibujar, delta);
            }
        }
        if (jfe != null) {
            jfe.draw(main.dibujar);
        }

        vel.draw(main.dibujar);
        font.draw(main.dibujar, "Puntaje " + score, 50, 680);
        main.dibujar.end();
    }
    

    public void hide() {}
    public void show() {}

     public void balaLogic(float daltaTime) {
         if (bullets.isEmpty()) {
             return;
         }
         for (int x = 0; x < bullets.size(); x++) {
             bullets.get(x).update(daltaTime);
             if (bullets.get(x).position.y > Gdx.graphics.getHeight()) {
                 bullets.remove(x);
                 System.out.println("removido");
             }
         }
         if (!enemies.isEmpty()) {
             for (int i = 0; i < enemies.size(); i++) {
                 for (int j = 0; j < bullets.size(); j++) {
                     if (bullets.get(j).isCollision(enemies.get(i))) {
                         enemies.remove(i);
                         bullets.remove(j);
                         score += 100;
                         main.cliente.enviar(main.id + ";jefe");
                     }
                 }
             }
         }

         if (jfe != null) {
             for (int j = 0; j < bullets.size(); j++) {
                 if (bullets.get(j).isCollision(jfe)) {
                     contJefe++;
                     if (contJefe == 3) {
                         jfe = null;
                         main.cliente.enviar(main.id + ";gano");
                     }
                 }

             }
         }
     }
    public void gameLogic(float deltaTime) {

        if (player != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.moveleft(deltaTime);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.moveRight(deltaTime);
            }

            if (player != null) {
                if (permisoDisparo && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    long time = System.currentTimeMillis();
                    if ((time - last_shot) >= FRE_DISPARO) {
                        bullets.add(new Bala(new Vector2(player.getPosition().x + 10,
                                player.getPosition().y + 10),
                                bala_text, 1000));
                        last_shot = System.currentTimeMillis();
                        sonido.play();
                    }
                }
            }

            vel.update(deltaTime);
            if(jfe !=null){
                jfe.update(deltaTime);
            }

            balaLogic(deltaTime);
            playerLogic(deltaTime);
            if (!enemies.isEmpty()) {
                enemyLogic(deltaTime);
            }


            balaEnemigoLogic(deltaTime);


        }
    }
        public void playerLogic(Float deltaTime) {
            if (player != null) {
                player.update(deltaTime);
            }
            if (!enemies.isEmpty()) {
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isCollision(player)) {
                        player = null;
                        this.hide();
                        main.setScreen(new GameOver_Screen(main, font, score));
                        main.cliente.enviar(main.id + ";gano");
                        System.out.println("El player murio");
                    }
                }

            }


        }
    public void balaEnemigoLogic(float daltaTime) {
        if (EnemyBullets.isEmpty()) {
            return;
        }
        for (int x = 0; x < EnemyBullets.size(); x++) {
            EnemyBullets.get(x).update(daltaTime);
            if(EnemyBullets.get(x).isCollision(player)){
                player = null;
                this.hide();
                main.setScreen(new GameOver_Screen(main, font, score));
                main.cliente.enviar(main.id + ";gano");
            }
            if (EnemyBullets.get(x).position.y > Gdx.graphics.getHeight()) {
                EnemyBullets.remove(x);
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

