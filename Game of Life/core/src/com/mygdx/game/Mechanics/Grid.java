package com.mygdx.game.Mechanics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Beings.Cell;
import com.mygdx.game.Beings.Cells;

/**
 * Created by isakl on 30/10/2016.
 */
public class Grid {

    private Position position;
    private Texture texture;
    private boolean doContainCell;

    public Grid(Position pos, Texture tex){
        position = pos;
        texture = tex;
        doContainCell = false;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setDoContainCell(boolean cell){
        doContainCell = cell;
    }

    public boolean containsCell(){
        return doContainCell;
    }
}
