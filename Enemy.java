//Enemy class

import java.awt.Graphics;
public class Enemy {
private int x;
private int y;
public static int speed=1;
public Enemy(int x,int y){
this.x = x;
this.y =y;
}
public void tick(){
y += speed;
}
public void render(Graphics g){
g.drawImage(loadImage.enemy,x,y,50,50,null);
}
public int getX(){
return x;
}
public int getY() {
return y;
}
}
