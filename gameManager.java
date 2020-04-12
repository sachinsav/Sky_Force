//gameManager class
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class gameManager implements KeyListener{
private Player player;
public static ArrayList<Bullet> bullet;
private ArrayList<Enemy> enemies;
private long current ;
private long delay;
private int health;
private int score;
private boolean start;
private int no;
Score sc;
int highscore;

public gameManager(){
}
public void init(){
Display.frame.addKeyListener(this);
player = new Player((gameSetUp.gameWidth/2)+50,(gameSetUp.gameHeight-60)+50);
player.init();
bullet = new ArrayList<Bullet>();
enemies = new ArrayList<Enemy>();
current = System.nanoTime();
delay = 2000;
health = player.getHealth();
score = 0;
no=2;
}
public void tick(){
if(start){
player.tick();
for(int i = 0;i<bullet.size();i++){
bullet.get(i).tick();
}
long breaks = (System.nanoTime()-current)/1000000;
if(breaks>delay){
	if(score>200)
		no=3;
	if(score>300)
		no=4;
	if(score>500)
		no=5;
	if(score>600)
		no=6;
	if(score>800)
		{no=8;
		Enemy.speed=2;}
	if(score>1500)
		Enemy.speed=3;
	if(score>2500)
		Enemy.speed=4;
for(int i= 0;i<no;i++){
Random rand = new Random();
int randX = rand.nextInt(400);
int randY = rand.nextInt(400);
if(health> 0){
	if(randX>50)
enemies.add(new Enemy(randX,-randY));
}
}
current = System.nanoTime();
}

//enemies
for(int i = 0;i<enemies.size();i++){
if(enemies.get(i).getY()>= 450 -50)
{
	for(int k=0;k<10;k++)
		enemies.get(i).tick();
}
	enemies.get(i).tick();
}
}
}

public void render(Graphics g){
if(start){player.render(g);
// bullet
for(int i=0;i<bullet.size(); i++){
bullet.get(i).render(g);
}
for(int i = 0;i< bullet.size();i++){
if(bullet.get(i).getY()<=50){
bullet.remove(i);
i--;

}}
//enemies
for(int i = 0;i<enemies.size();i++){
if(enemies.get(i).getY()>= 50&&enemies.get(i).getY()<400){
enemies.get(i).render(g);
}
}
//enemies & player collision
for(int i= 0;i<enemies.size();i++){
	int ex=enemies.get(i).getX();
int ey = enemies.get(i).getY();
int px = player.getX();
int py = player.getY();
if(px < (ex + 50) && (px + 60) > ex &&
py < (ey + 50) && (py + 60) > ey){
enemies.remove(i);
i--;
health--;
System.out.println(health);
if(health<=0){
	try {
		sc=new Score(score);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	highscore=Score.getK();
enemies.removeAll(enemies);
player.setHealth(0);
start = false;
}
}
g.setFont(new Font("arial",Font.BOLD,25));
g.setColor(Color.blue);
if(health<3)
	g.setColor(Color.RED);
g.drawString("Health : "+health, 70, 530);
// bullets  && enemy collision
for(int j = 0 ;j<bullet.size() ;j++){
int bx=bullet.get(j).getX();
int by=bullet.get(j).getY();
if(ex<bx+ 6 && ex + 50 >
bx && ey<by + 6
&& ey + 50> by ){
enemies.remove(i);
i--;
bullet.remove(j);
j--;
score= score + 5;
}
}
g.setColor(Color.blue);
g.setFont(new Font("arial",Font.BOLD, 40));
g.drawString("Score : "+score, 70,500);
}
}
else{g.setFont(new Font("arial",Font.PLAIN,33));
g.drawString("Press enter to start", 100, (gameSetUp.gameHeight/2)+50);
try {
	Score.filein();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
highscore=Score.getK();
g.setFont(new Font("arial",Font.BOLD,30));
g.setColor(Color.MAGENTA);
g.drawString("HighScore : "+highscore, 120, 300);
g.setColor(Color.blue);
g.setFont(new Font("arial",Font.BOLD,18));
g.drawString("Made By : Abhishekh , Amit , Sachin .", 5,595 );
}
}
public void keyPressed(KeyEvent e){
int source=e.getKeyCode();
if(source == KeyEvent.VK_ENTER){
start = true;
init();}
}

@Override
public void keyReleased(KeyEvent arg0){
}

@Override
public void keyTyped(KeyEvent arg0){
// TODO Auto-generated method stub
}
}