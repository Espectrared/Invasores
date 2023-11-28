/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author rober
 */
public class Jefe extends Enemigo{
    private float limiteIzquierdo;
    private float limiteDerecho;
    private float tiempoTranscurrido = 0;
    private float intervaloDeTiempo = 3;

    int hp = 1000;
    
    public Jefe(Vector2 position, Texture img, float speed) {
        super(position, img, speed);
        this.limiteIzquierdo = 0;
        this.limiteDerecho = Gdx.graphics.getWidth() - sprite.getWidth();
    }
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

    public void setScale(float scaleX, float scaleY) {
        this.sprite.setScale(scaleX, scaleY);
    }

    public boolean detectarJugador(Player player) {
        float rangoDeteccion = 10.0f;
        float posicionRedondeada = Math.round(this.position.x);
        return Math.abs(posicionRedondeada - player.position.x) < rangoDeteccion;
    }
    
}
