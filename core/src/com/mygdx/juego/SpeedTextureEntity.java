package com.mygdx.juego;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;



public class SpeedTextureEntity extends Entity {

    private Texture texture;
    private Vector2 position;
    private float speed;

    public SpeedTextureEntity(Vector2 position, Texture texture, float speed) {
        super(position, texture, speed);
        this.position = position;
        this.texture = texture;
        this.speed = speed;
    }

    @Override
    public void update(float deltaTime) {
        position.y -= deltaTime * speed;

        if (position.y < 0) {
            position.y = Gdx.graphics.getHeight();
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getSpeed() {
        return speed;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }

}
