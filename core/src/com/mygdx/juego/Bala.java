
package com.mygdx.juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author rober
 */
public class Bala extends Entity{
       public Bala(Vector2 position, Texture img, float speed)
    {
        super(position, img, speed);
        
        
          
    }

    @Override
    public void update(float deltaTime)
    {
        position.y+=deltaTime*speed;   
              
        sprite.setPosition(position.x, position.y);
        
    }
      
}
