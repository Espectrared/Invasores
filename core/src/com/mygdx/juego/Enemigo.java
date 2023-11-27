package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemigo extends Entity{

    private float limiteIzquierdo;
    private float limiteDerecho;
    private float tiempoTranscurrido = 0;
    private float intervaloDeTiempo = 3;

    public Enemigo(Vector2 position, Texture img, float speed) {
        super(position, img, speed);
        this.limiteIzquierdo = 0;
        this.limiteDerecho = Gdx.graphics.getWidth() - sprite.getWidth();
    }

    @Override
    public void update(float deltaTime) {
        if (position.x < limiteIzquierdo || position.x > limiteDerecho) {
            speed = -speed;
        }
        position.x += deltaTime * speed;

        tiempoTranscurrido += deltaTime;
        if (tiempoTranscurrido > intervaloDeTiempo) {
            position.y -= sprite.getHeight() * 0.20;
            tiempoTranscurrido = 0;
        }

        sprite.setPosition(position.x, position.y);
    }

}
