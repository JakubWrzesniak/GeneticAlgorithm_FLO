package Models;

import java.awt.*;

public class Machine {
    final int number;
    final Point position;

    public Machine(int number, Point position) {
        this.number = number;
        this.position = position;
    }

    public Machine(int number, int xPos, int yPos) {
        this.number = number;
        this.position = new Point(xPos, yPos) {
        };
    }

    public int getNumber() {
        return number;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
}
