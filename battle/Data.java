/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Data {
    public List<Enemy> enemies;
    public List<Player> players;
    public List<Bullet> bullets;
    public List<Entity> toBeDeleted;
    public int score = 0;
    public boolean dead = false;
    
    public Data() {
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        toBeDeleted = new ArrayList<>();
    }
    
    public void checkCollisions() {
        bullets.stream().forEach((bullet) -> {
            enemies.stream().forEach((enemy) -> {
                if (bullet.intersects(enemy)) {
                    enemy.toBeDelete(true);
                    bullet.toBeDelete(true);
                    score += 10;
                }
            });
        });
        
        enemies.stream().forEach((enemy) -> {
            enemies.stream().forEach((otherEnemy) -> {
                if(enemy != otherEnemy && enemy.intersects(otherEnemy)) {
                    enemy.setDir(enemy.getCoords().getDirVert(otherEnemy.getCoords()).multi(-1));
                } 
            });
        });
        
        players.stream().forEach((player) -> {
            enemies.stream().forEach((enemy) -> {
                if (player.intersects(enemy)) {
                    dead = true;
                }
            });
        });
    }
    
    public void removeToBeDeleted() {
        toBeDeleted.stream().forEach((entity) -> {
            enemies.remove(entity);
            bullets.remove(entity);
            addMoreEnemies();
        });
        toBeDeleted.clear();
    }
    
    public void renderAll(GraphicsContext gc) {
        //Render all players
        players.stream().forEach((player) -> {
            player.render(gc);
        });

        //Render all enimies
        enemies.stream().forEach((enemy) -> {
            enemy.render(gc);
        });
        
        //Render all bulletes
        bullets.stream().forEach((bullet) -> {
            bullet.render(gc);
        });
        
        gc.setStroke(Color.BLUE);
        gc.strokeText(Integer.toString(score), 720, 10);
    }
    
    public void updateAll() {
        checkCollisions();
        bullets.stream().forEach((bullet) -> {
            bullet.update();
            if (bullet.toBeDelete()) {
                toBeDeleted.add(bullet);
            }
        });
        
        enemies.stream().forEach((enemy) -> {
            enemy.update(players.get(0).getCoords());
            if (enemy.toBeDelete()) {
                toBeDeleted.add(enemy);
            }
        });
        removeToBeDeleted();
    }
    
    public void addMoreEnemies() {
        Random r = new Random();
        double newX = 0; 
        double newY = 0;
        while (newX >= 0 && newX <= 1440 && newY >=0 && newY <= 900) {
            newX = -100 + (1540 + 100) * r.nextDouble();
            newY = -100 + (1000 + 100) * r.nextDouble();
        }
        enemies.add(new Enemy(new Coords(newX,newY), 30, Color.RED));
    }
    public Data newData() {
        return new Data();
    }
}
