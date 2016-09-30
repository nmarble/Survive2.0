/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Sprite {
    private List<Image> images = new ArrayList<>();
    private double width, height;
    private Coords coords;
    public Sprite(List<String> imageLocations, Coords coords) {
        for (String location : imageLocations) {
            images.add(new Image(location));
            
        }
        width = images.get(0).getWidth();
        height = images.get(0).getHeight();
        this.coords = coords;
    }
    
    public Image getImage(){
        return images.get(0);
    }
    
    public void render(GraphicsContext gc) {
        gc.drawImage(images.get(0), coords.getX(), coords.getY());
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(coords.getX(),coords.getY(),width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public Coords getCoords() {return coords;}
    public void move(Direction direct) {
        coords = coords.move(direct);
    }
   
}
