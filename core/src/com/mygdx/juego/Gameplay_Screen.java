package com.mygdx.juego;

import com.badlogic.gdx.Gdx;

import java.util.Random;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


import java.util.ArrayList;

import com.badlogic.gdx.utils.Timer;

public class Gameplay_Screen extends Screen_Base {
    Random rng = new Random();
    Texture fondo;

    PowerUp vel;
    Principal main;
    Player player;
    Texture jugador_tex;
    String id;
    Jefe jefe;
    int contCastigos;
    public int score;
    int scoreCastigo = 0;
    private Timer.Task veloz;
    Random random = new Random();
    private boolean permisoDisparo = false;
    private float tiempoInicio = 0;
    private Timer.Task grande;
    Texture bala_text;
    Texture velocidad;

    Texture bala_enemigo;
    Texture caracol;
    ArrayList<Enemigo> enemies = new ArrayList<>();
    ArrayList<Explosion> explosions = new ArrayList<>();
    Bala bala;
    SpeedTextureEntity speedTextureEntity, velbaja;

    float vida;
    private ShapeRenderer shapeRenderer;

    Texture enemigo_tex;
    Texture jefe_tex;
    ArrayList<Bala> bullets = new ArrayList<>();
    ArrayList<BalaEnemigo> EnemyBullets = new ArrayList<>();
    long last_shot = 0;
    long last_shot2 = 0;

    static final int PLAYER_VEL = 300;
    static final int FRE_DISPARO = 500;
    BitmapFont font;
    boolean boss = false;
    private float timer;

    Music sonido;
    Music muerte;
    Music musicon;

    public Gameplay_Screen(Principal main, BitmapFont font) {
        shapeRenderer = new ShapeRenderer();
        vida = 1.0f;
        this.main = main;
        this.font = font;
        fondo = new Texture(Gdx.files.internal("fondoahorasi.jpg"));
        jugador_tex = new Texture("Player.png");
        bala_text = new Texture("Bullet.png");
        enemigo_tex = new Texture("Alien.png");
        velocidad = new Texture("velocidad.png");
        caracol = new Texture("caracol.png");
        bala_enemigo = new Texture("balaenemigo.png");
        sonido = Gdx.audio.newMusic(Gdx.files.internal("pistola.mp3"));
        muerte = Gdx.audio.newMusic(Gdx.files.internal("muerte.mp3"));
        musicon = Gdx.audio.newMusic(Gdx.files.internal("musicadefondo.mp3"));

        player = new Player(new Vector2(300, 15), jugador_tex, PLAYER_VEL);
        enemies.add(new Enemigo(new Vector2(300, 600), enemigo_tex, PLAYER_VEL));
        enemies.add(new Enemigo(new Vector2(200, 400), enemigo_tex, PLAYER_VEL));
        enemies.add(new Enemigo(new Vector2(500, 600), enemigo_tex, PLAYER_VEL));
        vel = new PowerUp(new Vector2(500, 700), velocidad, 300);

        //jfe = new Jefe(new Vector2(500, 600), enemigo_tex, PLAYER_VEL);

        //velbaja= new SpeedTextureEntity(new Vector2(600,200),caracol,100);

        musicon.setLooping(true);
        musicon.play();
    }

    @Override
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

        if (!EnemyBullets.isEmpty()) {
            for (int i = 0; i < EnemyBullets.size(); i++) {
                EnemyBullets.get(i).draw(main.dibujar);
            }
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

        if(vel != null){
            vel.draw(main.dibujar);
        }
        font.draw(main.dibujar, "Puntaje " + score, 50, 680);
        main.dibujar.end();
    }


    //Game logic
    public void gameLogic(float deltaTime) {
        timer += deltaTime;

        if (timer >= 5f) {
            masenemigod();
            timer = 0f;
        }

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
            if(vel != null){
                vel.update(deltaTime);
            }

            balaLogic(deltaTime);
            playerLogic(deltaTime);
            if (!enemies.isEmpty()) {
                enemyLogic(deltaTime);
            }


            balaEnemigoLogic(deltaTime);
            if (score >= 1000) {
                this.hide();
                main.setScreen(new Nivel2(main, font, score));
            }

        }

    }

    public void hide() {
    }

    public void show() {
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
        if(vel != null){
            if(player.isCollision(vel)){
                vel = null;
                powerupvelocivad();
            }
        }


    }

    ///Bala logic
    public void balaLogic(float daltaTime) {
        if (bullets.isEmpty()) {
            return;
        }
        for (int x = 0; x < bullets.size(); x++) {
            bullets.get(x).update(daltaTime);
            if (bullets.get(x).position.y > Gdx.graphics.getHeight()) {
                bullets.remove(x);
            }
        }
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                for (int j = 0; j < bullets.size(); j++) {
                    if (bullets.get(j).isCollision(enemies.get(i))) {
                        explosions.add(new Explosion(enemies.get(i).position.x, enemies.get(i).position.y));
                        enemies.remove(i);
                        bullets.remove(j);
                        muerte.play();
                        score += 500;
                        if (score > scoreCastigo) {
                            scoreCastigo = scoreCastigo + score + 200;
                            main.cliente.enviar(main.id + ";castigo");
                        }
                    }
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
            if(player != null){
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
    }

    public void generateBoss() {
        int x = new Random().nextInt(400 - 10) + 10;
        int y = new Random().nextInt(400 - 10) + 10;
        enemies.add(new Enemigo(new Vector2(x, y), enemigo_tex, PLAYER_VEL));
    }

    public void enemyLogic(float deltaTime) {
        if (enemies.isEmpty()) {
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemigo enemy = enemies.get(i);
            if (enemy.detectarJugador(player)) {
                long time2 = System.currentTimeMillis();
                if ((time2 - last_shot2) >= FRE_DISPARO) {
                    EnemyBullets.add(new BalaEnemigo(new Vector2(enemy.position.x, enemy.position.y + 10), bala_enemigo, 500));
                    last_shot2 = System.currentTimeMillis();
                }
            }
            enemy.update(deltaTime);
        }
    }

    public void powerupvelocivad() {
        player.aumenentovel(1500);

        veloz = new Timer.Task() {
            @Override
            public void run() {
                player.aumenentovel(PLAYER_VEL);
            }
        };
        Timer.schedule(veloz, 2);
    }

    public void generateCastigo() {
       int castigo = rng.nextInt(3)+1;
        switch (castigo) {
            case 1:
                break;

            case 2:
                grande = new Timer.Task() {
                    @Override
                    public void run() {
                        player.setScale(2.0f, 2.0f);
                    }
                };
                Timer.schedule(grande, 2);
                break;

            case 3:
            player.aumenentovel(100);
        veloz = new Timer.Task() {
            @Override
            public void run() {
                player.aumenentovel(PLAYER_VEL);

            }
        };
        Timer.schedule(veloz, 2);
        break;

        }
    }


    public void masenemigod()
    {
        float pos= player.position.x;
        int y = new Random().nextInt(200 - 10) ;
        enemies.add(new Enemigo(new Vector2(pos, 500-y), enemigo_tex, PLAYER_VEL));
        System.out.println("se creo enemigo");
    }

    }

