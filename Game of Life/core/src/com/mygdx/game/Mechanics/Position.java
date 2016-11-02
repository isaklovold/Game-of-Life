package com.mygdx.game.Mechanics;

/**
 * Created by isakl on 30/10/2016.
 */
public class Position {

    private int positionX, positionY;

    /**
     * Class constructor that initialise the instance variables
     * @param posX X-coordinate of the position
     * @param posY Y-coordinate of the position
     */
    public Position(int posX, int posY){
        positionX = posX;
        positionY = posY;
    }

    /**
     * This is a getter for the X-coordinate of the position
     * @return returns the X-coordinate of the position
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * This is a getter for the Y-coordinate of the position
     * @return returns the Y-coordinate of the position
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * This is a setter of the X-coordinate of the position
     * @param positionX sets the X-coordinate of the position
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * This is a setter of the Y-coordinate of the position
     * @param positionY sets the Y-coordinate of the position
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Prints out information about the instance variables
     */
    @Override
    public String toString() {
        return "Position [positionX=" + positionX + ", positionY=" + positionY
                + "]";
    }

}
