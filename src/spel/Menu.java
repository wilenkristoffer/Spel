package spel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;

    private Handler handler;

    private Random r = new Random();

    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }

            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }

            //quit button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
            //back button for help
        }
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;

            }
        }
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));


            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;

    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.Menu) {


            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 40);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 260, 195);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 260, 295);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 260, 395);

        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 50);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Använd WASD tangenterna för att röra dig", 50, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 260, 395);

        } else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);

            g.setFont(fnt3);
            g.drawString("Du förlora, din poäng: " + hud.getScore(), 180, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 240, 395);
        }
    }
}
