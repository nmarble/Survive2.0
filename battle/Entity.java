/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Entity {
    Color color;
    Coords coords;
    double size;
    boolean toBeDeleted;
    
    public Entity(Coords coords, double size) {
        this.coords = coords;
        this.size = size;
        color = Color.BLACK;
    }
    public Entity(Coords coords, double size, Color color) {
        this.coords = coords;
        this.size = size;
        this.color = color;
    }
    public void changeColor(Color newColor) {this.color = newColor;}
    
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(coords.getX() - (size / 2), coords.getY()- (size / 2), size, size);
    }
    
    public boolean intersects(Entity s)
    {
        return coords.getDistanceTo(s.getCoords()) < (size / 2 + s.getSize() / 2);
    }
    public Coords getCoords() {return coords;}
    public void changeCoords(Coords other) {
        coords = coords.add(other);
    }
    public double getSize() {return size;}
    public void move(Direction direct) {
        coords = coords.move(direct);
    }
    
    public boolean toBeDelete() {return toBeDeleted;}
    public void toBeDelete(boolean status) {toBeDeleted = status;}
    
}
