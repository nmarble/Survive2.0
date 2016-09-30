/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

/**
 *
 * @author Nick
 */
public class Bullet extends Entity {
    private Coords vel = new Coords(0,0);
    private int lifeSpan = 100;
    private double speed = 20;
    
    public Bullet(Coords coords, double size) {
         super(coords, size);
    }
    
    public void setVel(Coords newVel) {
        vel = newVel.multi(speed);
    }
    
    public void changeVel(Coords change) {
        vel.add(change);
    }
    
    public void update() {
        coords = coords.add(vel);
        lifeSpan--;
        if (lifeSpan <= 0) {
            this.toBeDeleted = true;
        }
    }
    
}
