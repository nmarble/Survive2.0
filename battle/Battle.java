/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Battle extends Application {
    public Data initEntities () {
        Data data = new Data();
        data.players.add(new Player(new Coords(720,450), 40));
        return data;
    }
    public void startDead () {
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Hello, World! 2.0");
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
        Canvas canvas = new Canvas(1440,900);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        ArrayList<String> input = new ArrayList<String>();
        
        final Data data = initEntities();
        
        theScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            
            if ( !input.contains(code) )
                input.add(code);
        });
        
        theScene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
        
        theScene.setOnMouseClicked((MouseEvent e) -> {
            Bullet newBullet = new Bullet(data.players.get(0).getPointerLoc(), 5);
            newBullet.setVel(data.players.get(0).getFacing());
            data.bullets.add(newBullet);
            
        });
        
        theScene.setOnMouseMoved((MouseEvent e) -> {
            data.players.get(0).setMouse(new Coords(e.getX(), e.getY()));
        });
        
        
        final long startNanoTime = System.nanoTime();
        
        AnimationTimer deadTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                if (input.contains("SPACE")) {
                    System.exit(0);
                }         
            }
        };
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
                gc.clearRect(0, 0, 1440, 900);
                
                data.renderAll(gc);
                data.updateAll();
                       
                if (input.contains("A"))
                    data.players.get(0).move(Direction.Left);
                if (input.contains("D"))
                    data.players.get(0).move(Direction.Right);
                if (input.contains("W"))
                    data.players.get(0).move(Direction.Down);
                if (input.contains("S"))
                    data.players.get(0).move(Direction.Up);
                
                if(data.dead == true) {
                    gc.setStroke(Color.RED);
                    gc.setFont(Font.font(STYLESHEET_CASPIAN, 72));
                    gc.strokeText("DEAD", 400, 400);
                    this.stop();
                    deadTimer.start();
                }
             }
        };
        
        timer.start();
        
        theStage.show();
    }

}
