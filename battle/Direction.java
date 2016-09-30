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
public enum Direction {
    Up(0,1), Down(0,-1), Left(-1,0), Right(1,0);
        private Coords coords;
        private Direction(double x, double y) {
            coords = new Coords(x,y);
        }

        public Coords getDir() {
            return coords;
        }
        public double getX() {return coords.getX();}
        public double getY() {return coords.getY();}
}
