/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import javafx.scene.paint.Color;

/**
 *
 * @author Nick
 */
public class Enemy extends Entity {
    private Coords dir = new Coords(0,0);
    
    public Enemy(Coords coords, double size) {
        super(coords, size);
    }
    public Enemy(Coords coords, double size, Color color) {
        super(coords, size, color);
    }
    public void update(Coords playerPos) {
    coords = coords.add(dir);
        dir = coords.getDirVert(playerPos);
    }
    public Coords getDir() {return new Coords(dir);}
    public void setDir(Coords newCoords) {dir = newCoords;}
}
