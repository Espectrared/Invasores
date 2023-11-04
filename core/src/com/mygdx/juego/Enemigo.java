/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author rober
 */
public class Enemigo extends Entity{

    public Enemigo(Vector2 position, Texture img, float speed) {
        super(position, img, speed);
    }

    @Override
    public void update(float deltaTime) {
                       // position.x+=deltaTime*speed;

        sprite.setPosition(position.x, position.y);
    }
    
}
