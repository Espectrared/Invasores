/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.juego;

import com.badlogic.gdx.ScreenAdapter;

/**
 *
 * @author rober
 */
public abstract class Screen_Base extends ScreenAdapter {
    
    @Override
    abstract public void render(float delta);
   
    @Override
   abstract public void hide();    
   
    @Override
   abstract public void show();
   
}
