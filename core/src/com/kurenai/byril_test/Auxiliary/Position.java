package com.kurenai.byril_test.Auxiliary;

public class Position {

    private int row;
    private int column;
    private Direction direction;

    public Position(int row, int column, Direction direction){
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }

}
