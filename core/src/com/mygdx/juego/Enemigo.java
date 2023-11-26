package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemigo extends Entity{
    private float limiteIzquierdo;
    private float limiteDerecho;
    private float tiempoTranscurrido = 0; // Contador de tiempo
    private float intervaloDeTiempo = 1; // Intervalo de tiempo en segundos para que los enemigos bajen

    public Enemigo(Vector2 position, Texture img, float speed) {
        super(position, img, speed);
        this.limiteIzquierdo = 0; // El límite izquierdo es el borde izquierdo de la pantalla
        this.limiteDerecho = Gdx.graphics.getWidth() - sprite.getWidth(); // El límite derecho es el borde derecho de la pantalla menos el ancho del sprite
    }

    @Override
    public void update(float deltaTime) {
        if (position.x < limiteIzquierdo || position.x > limiteDerecho) {
            speed = -speed; // Cambia la dirección cuando alcanza un límite
        }
        position.x += deltaTime * speed;  // Ahora los enemigos se moverán en el eje x

        tiempoTranscurrido += deltaTime; // Actualiza el contador de tiempo
        if (tiempoTranscurrido > intervaloDeTiempo) {
            position.y -= sprite.getHeight() * 0.20; // Los enemigos bajan un 20% de su altura
            tiempoTranscurrido = 0; // Reinicia el contador de tiempo
        }

        sprite.setPosition(position.x, position.y);
    }

}
