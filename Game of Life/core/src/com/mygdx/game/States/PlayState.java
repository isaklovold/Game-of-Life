package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Beings.Cells;
import com.mygdx.game.Mechanics.Grid;
import com.mygdx.game.Mechanics.Position;
import com.mygdx.game.main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by isakl on 30/10/2016.
 */
public class PlayState extends State {

    private boolean pause;

    // @@@@@@@@@@@@  GRID WORLD  @@@@@@@@@@@@
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 40;
    private Grid[][] grid = new Grid[GRID_HEIGHT][GRID_WIDTH];
    private int grid_x, grid_y;
    private Position grid_position;
    private Texture grid_texture;
    private Grid grid_object;

    // @@@@@@@@@@@@    @@@@@@@@@@@@
    private ArrayList<Cells> cells;
    private String cell_name;
    private boolean isCellAlive;
    private int cells_x, cells_y;
    private Position cells_position;
    private Texture cells_texture;
    private Cells cell;

    private Texture childrens_texture;
    private int child_x, child_y;
    private boolean isChildAlive;
    private Position child_position;
    private Cells child;
    private String child_name;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        pause = true;
        cam.setToOrtho(true, main.WIDTH, main.HEIGHT);

        // GRID
        grid_x = 0;
        grid_y = 0;
        grid_texture = new Texture("cell_normal.png");

        // CELLS
        cells = new ArrayList<Cells>();
        cells_x = 0;
        cells_y = 0;
        isCellAlive = true;
        cell_name = "";
        cells_texture = new Texture("cell_base.png");

        // CHILDREN
        child_x = 0;
        child_y = 0;
        isCellAlive = true;
        child_name = "";
        childrens_texture = new Texture("cell_base.png");


        createGrid();
        //spawnCells();

    }



    @Override
    protected void handleInput() {
        // START AND PAUSE GAME
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if(pause == true){
                pause = false;
            } else {
                pause = true;
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (pause == true) {
            addCells();
        } else {
            act();
            generateLife();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){}

            removeCells();



        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        for(int i = 0; i < 40; i++){
            for(int j = 0; j < 40; j++){
                sb.draw(grid[i][j].getTexture(), grid[i][j].getPosition().getPositionX(), grid[i][j].getPosition().getPositionY());
            }
        }
        for(int i = 0; i < cells.size(); i++){
            sb.draw(cells.get(i).getTexture(), cells.get(i).getLocation().getPositionX(), cells.get(i).getLocation().getPositionY());
        }
        sb.end();
    }

    public void createGrid(){
        for(int i = 0; i < GRID_HEIGHT; i++){
            if(i != 0){
                grid_x = 0;
                grid_y += 20;

            }
            for(int j = 0; j < GRID_WIDTH; j++){
                if(j != 0){
                    grid_x += 20;
                }

                grid_position = new Position(grid_x, grid_y);
                grid_object = new Grid(grid_position, grid_texture);
                grid[i][j] = grid_object;
            }
        }
    }

    public void addCells(){
        // ADDS CELLS TO GRID
        boolean gotCell = false;
        for(int i = 0; i < GRID_HEIGHT; i++){
            for(int j = 0; j < GRID_WIDTH; j++){
                if(Gdx.input.isButtonPressed(Input.Keys.LEFT)){
                    if(         (Gdx.input.getX() >= grid[i][j].getPosition().getPositionX() && Gdx.input.getX() <= grid[i][j].getPosition().getPositionX() + 20)
                            &&  (Gdx.input.getY() >= grid[i][j].getPosition().getPositionY() && Gdx.input.getY() <= grid[i][j].getPosition().getPositionY() + 20)){
                        for(int k = 0; k < cells.size(); k++){
                            if(         (cells.get(k).getLocation().getPositionX() == grid[i][j].getPosition().getPositionX())
                                    &&  (cells.get(k).getLocation().getPositionY() == grid[i][j].getPosition().getPositionY())){
                                gotCell = true;
                            }
                        }

                        if(gotCell == false) {
                            cells_position = grid[i][j].getPosition();
                            cell = new Cells(cells_position, isCellAlive, cell_name, cells_texture);
                            cells.add(cell);

                            grid[i][j].setDoContainCell(true);
                        }
                    }
                }
            }
        }
    }

    public void spawnCells(){
        Random rand = new Random();
        int x_pos;
        int y_pos;

        for(int i = 0; i < 100; i++){
            x_pos = rand.nextInt(GRID_WIDTH);
            y_pos = rand.nextInt(GRID_HEIGHT);

            cells_x = grid[x_pos][y_pos].getPosition().getPositionX();
            cells_y = grid[x_pos][y_pos].getPosition().getPositionY();
            cells_position = new Position(cells_x, cells_y);

            cell_name = "Cell nr." + i;
            cell = new Cells(cells_position, isCellAlive, cell_name, cells_texture);
            if(!cells.contains(cell)) {
                cells.add(cell);
            } else {
                i--;
            }
        }

        System.out.println(cells.size());
    }

    public void generateLife(){
        ArrayList<Cells> tempCells = new ArrayList<Cells>();
        int neighbourCount = 0;
        for(int i = 0; i < GRID_HEIGHT; i++){
            for(int j = 0; j < GRID_WIDTH; j++){
                for(int k = 0; k < cells.size(); k++){
                    if(!grid[i][j].containsCell()){
                        if((grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() + 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY())
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() - 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY())
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX()
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() + 20)
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX()
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() - 20)
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() + 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() + 20)
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() + 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() - 20)
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() - 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() - 20)
                        || (grid[i][j].getPosition().getPositionX() == cells.get(k).getLocation().getPositionX() - 20
                        && grid[i][j].getPosition().getPositionY() == cells.get(k).getLocation().getPositionY() + 20)
                        ){
                            neighbourCount++;
                        }
                    }
                }

                if((grid[i][j].containsCell() == false) && (neighbourCount == 3)){
                    child_position = grid[i][j].getPosition();
                    child = new Cells(child_position, isCellAlive, child_name, childrens_texture);
                    tempCells.add(child);
                    grid[i][j].setDoContainCell(true);
                }

                neighbourCount = 0;
            }
        }

        if(tempCells != null){
            for(int i = 0; i < tempCells.size(); i++){
                cells.add(tempCells.get(i));
            }
        }
    }

    public void removeCells(){
        int cellsCount = cells.size() - 1;
        for(int i = cellsCount; i >= 0; i--){
            if((cells.get(i).isAlive() == false)
            || (cells.get(i).getLocation().getPositionX() > main.WIDTH)
            || (cells.get(i).getLocation().getPositionY() > main.HEIGHT)
            || (cells.get(i).getLocation().getPositionX() < 0)
            ||  (cells.get(i).getLocation().getPositionY() < 0))
            {
                for(int k = 0; k < GRID_HEIGHT; k++){
                    for(int j = 0; j < GRID_WIDTH; j++){
                        if(grid[k][j].getPosition() == cells.get(i).getLocation()){
                            grid[k][j].setDoContainCell(false);
                        }
                    }
                }
                cells.remove(i);
            }
        }
    }

    public void act(){
        for(int i = 0; i < cells.size(); i++){
            cells.get(i).setCells(cells);
            cells.get(i).act();
        }
    }

    @Override
    public void dispose() {

    }
}
