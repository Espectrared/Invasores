package com.mygdx.juego;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BalaEnemigo extends Bala{
    public BalaEnemigo(Vector2 position, Texture img, float speed) {
        super(position, img, speed);
    }

    public void update(float deltaTime)
    {
        position.y-=deltaTime*speed;

        sprite.setPosition(position.x, position.y);

    }
}