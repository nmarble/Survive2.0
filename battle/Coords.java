
package battle;


public class Coords {
     
    private double x,y;
    
    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Coords(Coords coords) {
        this.x = coords.getX();
        this.y = coords.getY();
        
    }
    public double getX(){return x;}
    public double getY(){return y;}
    
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    
    public Coords move(Direction direct) {
        x += direct.getX();
        y += direct.getY();
        return this;
    }
    
    public double getDistanceTo(Coords other) {
        return Math.sqrt(Math.pow((x - other.getX()), 2) + Math.pow((y - other.getY()), 2));
    }
    
    public Coords getDirVert(Coords to) {
        return new Coords((to.getX() - x) / getDistanceTo(to), (to.getY() - y) / getDistanceTo(to));
    }
    
    @Override
    public String toString() {
        return "X: " + Double.toString(x) + ", Y: " + Double.toString(y);
    }
    
    public Coords add(Coords toAdd) {
        return new Coords(x + toAdd.getX(), y + toAdd.getY());
    }
    
    public Coords sub(Coords toAdd) {
        return new Coords(x - toAdd.getX(), y - toAdd.getY());
    }
    public Coords multi(double multiplier) {
        return new Coords(x * multiplier, y * multiplier);
    }
    public void changeX (double x) {
        this.x += x;
    }
    
    public void changeY (double y) {
        this.y += y;
    }
}
