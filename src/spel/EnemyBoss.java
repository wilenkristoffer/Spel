package spel;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends  GameObject{

    private Handler handler;

    private int timer = 80;

    private int timer2 = 50;

    Random r = new Random();
    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;


        velX = 0;
        velY = 2;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y, 96, 96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;
        if(timer2 <= 0){
            if(velX == 0) velX = 2;
            velX += (int) (0.01f*Math.signum(velX));
            int spawn = r.nextInt(10);
            if(spawn == 0) handler.addObject(new EnemyBossBullet(x+48, y+48, ID.BasicEnemy, handler));

        }


      //  if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
      if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

       // handler.addObject(new Trail(x,y, ID.Trail, Color.red, 64, 64, 0.02f, handler));
    }

    @Override
    public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, 96, 96);
    }
}
