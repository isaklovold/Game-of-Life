package com.mygdx.game.Beings;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Mechanics.Position;

import java.util.ArrayList;

/**
 * Created by isakl on 30/10/2016.
 */
public interface Cell {

    public String getName();

    public void act();

    public Position getLocation();

    public void setLocation(Position position);

    public boolean isAlive();

    public Texture getTexture();


}
