package com.mygdx.game.Beings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Mechanics.Position;

import java.util.ArrayList;

/**
 * Created by isakl on 30/10/2016.
 */
public class Cells implements Cell {

    private ArrayList<Cells> cells;

    private Position position;
    private boolean isAlive;
    private String name;
    private Texture body;

    public Cells(){

    }

    public Cells(Position pos, boolean isAlive, String nm, Texture tex){
        position = pos;
        this.isAlive = isAlive;
        name = nm;
        body = tex;

        cells = new ArrayList<Cells>();
    }

    @Override
    public void act() {
        if(getNeighbours() == 0 || getNeighbours() == 1 || getNeighbours() >= 4){
            this.setAlive(false);
        }

    }

    public int getNeighbours(){
        int neighbourCount = 0;
        for(int i = 0; i < cells.size(); i++){
            if(this.getLocation() != cells.get(i).getLocation()){
                if(     (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() + 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY())
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() - 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY())
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX()
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() + 20)
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX()
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() -20)
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() + 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() + 20)
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() + 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() - 20)
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() - 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() + 20)
                    ||  (this.getLocation().getPositionX() == cells.get(i).getLocation().getPositionX() - 20
                    &&  this.getLocation().getPositionY() == cells.get(i).getLocation().getPositionY() - 20)
                ){
                    neighbourCount++;
                }
            }
        }

        return neighbourCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getLocation() {
        return position;
    }

    @Override
    public void setLocation(Position position) {
        this.position = position;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public Texture getTexture() {
        return body;
    }

    public void setBody(Texture body) {
        this.body = body;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setCells(ArrayList<Cells> cells){
        this.cells = cells;
    }

    public ArrayList<Cells> getCells(){
        return cells;
    }
}
