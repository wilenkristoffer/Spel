package spel;

import java.awt.*;

public class SmartEnemy extends  GameObject{

    private Handler handler;

    private GameObject player;
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

    }
    public Rectangle getBounds(){
        return new Rectangle(x,y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        double diffX = x - player.getX() - 16;
        double diffY = y - player.getY() - 16;
        double distance = Math.sqrt(diffX*diffX + diffY*diffY);

        velX = (int) (-1.5*diffX/distance);
        velY = (int) (-1.5*diffY/distance);


        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x,y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
    }

    @Override
    public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, 16, 16);
    }
}
