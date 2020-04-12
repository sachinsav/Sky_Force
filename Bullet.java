//Bullet class

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

private int x;
private int y;
private int speed;
public Bullet(int x, int y)
{
this.x = x-5;
this.y = y;
speed = 10;
}
public void tick()
{
y -= speed;
}
public int getY(){
return y;
}
public int getX(){
return x;
}
public void render(Graphics g){
g.setColor(Color.red);
g.fillRect(x,y, 6, 10);
g.setColor(Color.BLACK);
}
}