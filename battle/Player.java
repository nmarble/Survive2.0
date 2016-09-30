/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Nick
 */
public class Player extends Entity{

    private String name;
    private Coords pointer = new Coords(0,0);
    private Coords mouse = new Coords(0,0);
    private double speed = 5;
    
    public Player(Coords coords, double size) {
        super(coords, size);
        pointer = new Coords(coords.getX(), coords.getY());
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(coords.getX() - (size / 2), coords.getY() - (size / 2), size, size);
        changePointer();
        gc.setFill(Color.RED);
        gc.fillOval(pointer.getX(), pointer.getY(), 5, 5);
    }
    
    @Override
    public void move(Direction direct) {
        coords = coords.add(direct.getDir().multi(speed));
        pointer = pointer.add(direct.getDir().multi(speed));
    }
    
    public void setMouse(Coords newCoords) {mouse = newCoords;}
    public Coords getMouse() {return mouse;}
    public void changePointer() {
        Coords temp = new Coords(mouse.getX() - coords.getX(), mouse.getY() - coords.getY());
        double mag = Math.sqrt(temp.getX() * temp.getX() + temp.getY() * temp.getY());
        pointer.setX(coords.getX() - (2.5) + temp.getX() / mag * (size /2));
        pointer.setY(coords.getY() - (2.5) + temp.getY() / mag * (size /2));
    }
    
    public Coords getPointer() {return pointer;}
    public Coords getPointerLoc() {return new Coords(pointer.getX(), pointer.getY());}
    
    public Coords getFacing() {
        return pointer.getDirVert(mouse);
    }
}
