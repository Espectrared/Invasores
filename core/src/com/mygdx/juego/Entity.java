
package com.mygdx.juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author rober
 */
public abstract class Entity {
    protected Vector2 position;
    protected Vector2 position_initiial;
    protected Sprite sprite;
    protected Boolean isAlive;
    protected float speed;

    public Entity(Vector2 position,  Texture img,  float speed) {
        this.position = position;
        position_initiial = position;
        sprite= new Sprite(img);
        sprite.setScale(1);
        isAlive = true;
        this.speed = speed;
    }
    public void draw(SpriteBatch dibujar)
    {
     sprite.draw(dibujar);
 
    }
   public abstract  void update(float deltaTime);
   public boolean isCollision(Entity cosa){
    Rectangle rec1 = sprite.getBoundingRectangle();
    Rectangle rec2 = cosa.sprite.getBoundingRectangle();
    return rec1.overlaps(rec2);
   }
}
