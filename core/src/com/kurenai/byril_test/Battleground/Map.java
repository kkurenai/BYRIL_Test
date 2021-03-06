package com.kurenai.byril_test.Battleground;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kurenai.byril_test.Auxiliary.Constants;
import com.kurenai.byril_test.Auxiliary.Direction;
import com.kurenai.byril_test.Auxiliary.Position;
import com.kurenai.byril_test.Auxiliary.ShipsProvider;
import static com.kurenai.byril_test.Auxiliary.Constants.*;
import java.util.ArrayList;

public class Map extends Actor {

    private TextureRegion mapBGTexture;
    private int mapX, mapY;
    private boolean[][] placedShipsPositions;
    private ArrayList<Ship> ships;
    private com.kurenai.byril_test.Auxiliary.ShipsProvider shipsProvider;
    private SpriteBatch batch;

    public Map(TextureRegion mapBGTexture, SpriteBatch batch, int mapX, int mapY){
        this.mapBGTexture = mapBGTexture;
        this.batch = batch;
        this.mapX = mapX;
        this.mapY = mapY;
        placedShipsPositions = new boolean[MAP_SIZE_ROWS][MAP_SIZE_COLUMNS];
        shipsProvider = new ShipsProvider();
        ships = new ArrayList<>();
        placeShips();
    }

    @Override
    public void act(float delta) {
        drawMap();
    }

    private void drawMap(){
        batch.begin();

        batch.draw(mapBGTexture, mapX, mapY);
        drawShips(batch);

        batch.end();
    }

    private void drawShips(SpriteBatch batch){
        for(Ship ship: ships){
            if(ship.getDirection() == Direction.VERTICAL)
                batch.draw(ship, ship.getX(), ship.getY());
            else
                // map cell size added because ship will be moved after rotating
                batch.draw(ship,ship.getX() ,ship.getY() + MAP_CELL_SIZE,0,0,ship.getRegionWidth(),ship.getRegionHeight(),1,1,-90);
        }
    }

    private void placeShipRandomly(int decksNumber){

        ArrayList<Position> availableShipPositions = getAvailableShipPositions(decksNumber);

        if (availableShipPositions.size() != 0) {
            Position shipPosition = availableShipPositions.get(MathUtils.random(availableShipPositions.size() - 1));
            int cellColumn = shipPosition.getColumn();
            int cellRow = shipPosition.getRow();

            // placing ship position to placed ships positions array
            if (shipPosition.getDirection() == Direction.HORIZONTAL)
                for (int i = cellColumn; i < cellColumn + decksNumber; i++)
                    placedShipsPositions[cellRow][i] = true;
            else
                for (int i = cellRow; i < cellRow + decksNumber; i++)
                    placedShipsPositions[i][cellColumn] = true;

            // creating & adding new ship to ships array
            Ship ship = shipsProvider.getShip(decksNumber);
            ship.setY(getMapX() + MAP_CELL_SIZE * cellRow);
            ship.setX(getMapY() + MAP_CELL_SIZE * (cellColumn + MAP_HOOD_SIZE));
            ship.setDirection(shipPosition.getDirection());
            ships.add(ship);
        }
    }

    private void placeShips(){

        // so that everything fits, begin to place ships with the largest ones

        placeShipRandomly(4);

        for (int i = 0 ; i < 2 ; i++){
            placeShipRandomly(3);
        }

        for (int i = 0 ; i < 3 ; i++){
            placeShipRandomly(2);
        }

        for (int i = 0 ; i < 4 ; i++){
            placeShipRandomly(1);
        }

    }

    private ArrayList<Position> getAvailableShipPositions(int decksNumber){

        ArrayList<Position> availablePositions = new ArrayList<>();
        ArrayList<Direction> directions;

        for (int i = 0; i < MAP_SIZE_ROWS; i++)
            for (int j = 0; j < MAP_SIZE_COLUMNS; j++) {

                directions = canPlaceShip(i, j, decksNumber);
                if (directions != null)
                    for (Direction direction: directions)
                        availablePositions.add(new Position(i, j, direction));

            }

        return availablePositions;
    }

    // checks if ship could be placed on given cell & row, return available directions
    private ArrayList<Direction> canPlaceShip(int cellRow, int cellColumn, int decksNumber){
        boolean canPlaceShip;
        ArrayList<Direction> directions = new ArrayList<>();

        int minCellRow = cellRow - 1;
        int minCellColumn = cellColumn - 1;
        int maxCellRow;
        int maxCellColumn;

        if (!(cellRow + decksNumber > MAP_SIZE_ROWS)) { // checking if the ship fits map vertically
            canPlaceShip = true;
            maxCellRow = cellRow + decksNumber;
            maxCellColumn = cellColumn + 1;
            // checking if there is no other ships near
            outerloop:
            for ( int x = minCellRow; x <= maxCellRow; x++ ) {
                for ( int y = minCellColumn; y <= maxCellColumn; y++ ) {
                    if (x < MAP_SIZE_ROWS && y < MAP_SIZE_COLUMNS && x >= 0 && y>=0)
                        if (placedShipsPositions[x][y]) {
                            canPlaceShip = false;
                            break outerloop;
                        }
                }
            }
            if (canPlaceShip)
                directions.add(Direction.VERTICAL);
        }

        if (cellColumn + decksNumber > MAP_SIZE_COLUMNS) // checking if the ship fits map horizontally
            return null;
        else {
            canPlaceShip = true;
            maxCellRow = cellRow + 1;
            maxCellColumn = cellColumn + decksNumber;
            outerloop:
            for (int x = minCellRow; x <= maxCellRow; x++) {
                for (int y = minCellColumn; y <= maxCellColumn; y++) {
                    if (x < MAP_SIZE_ROWS && y < MAP_SIZE_COLUMNS && x >= 0 && y >= 0)
                        if (placedShipsPositions[x][y]) {
                            canPlaceShip = false;
                            break outerloop;
                        }
                }
            }
            if (canPlaceShip)
                directions.add(Direction.HORIZONTAL);
        }

        return directions;
    }

    public float getWidth(){
        return mapBGTexture.getRegionWidth();
    }

    public float getHeight(){
        return mapBGTexture.getRegionHeight();
    }

    public int getMapX(){
        return mapX;
    }

    public int getMapY(){
        return mapY;
    }

    public void replaceShips(){
        ships.clear();
        for (int i = 0; i < MAP_SIZE_ROWS; i++)
            for (int j = 0; j < MAP_SIZE_COLUMNS; j++)
                placedShipsPositions[i][j] = false;

        placeShips();
    }
}