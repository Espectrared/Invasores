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
public class Player extends Entity{

    public Player(Vector2 position, Texture img, float speed)
    {
        super(position, img, speed);
          
    }

    @Override
    public void update(float deltaTime)
    {
        if(position.x<0)
        {
            //position.x= sprite.getWidth()*sprite.getScaleX()/4;
            position.x=0;
        }
        if(position.x+(sprite.getWidth()*sprite.getScaleX())>= Gdx.graphics.getWidth())
        {
             position.x= (float) (Gdx.graphics.getWidth()- (sprite.getWidth()*sprite.getScaleX()));
             
        }
        sprite.setPosition(position.x, position.y);
        
    }
            public void moveleft(float deltaTime)
            {
                
                position.x-=deltaTime*speed;
            }
              public void moveRight(float deltaTime)
            {
                position.x+=deltaTime*speed;
            }
      
              public Vector2 getPosition(){
                  return position;
              }
    public void aumenentovel(float velocidad){
        this.speed = velocidad;
    }
}
