package GameStates;

import Entity.View.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelStateModel {
    public static final int MAX_HEARTS = 3;
    public static int MAX_BULLETS;
    public static int MAX_COINS;
    private static String lvl;
    private static int curCoins;
    private static int cameraX;
    private static ArrayList<Wall> floors = new ArrayList<>();
    private static ArrayList<Spike> spikes = new ArrayList<>();
    private static ArrayList<Coin> coins = new ArrayList<>();
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    Player player;
    private Tent tent;
    private static int endX;
    private static int endY;
    private long timer;
    private long lastTime;
    private static int startX;
    private static int startY;

    public static int getCurCoins() {
        return curCoins;
    }


    public static void addCoin() {
        curCoins++;
    }

    public static void setCameraX(int c) {
        cameraX += c;
    }

    public static ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public static ArrayList<Coin> getCoins() {
        return coins;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static void setLvl(String name) {
        lvl = name;
    }

    public static ArrayList<Wall> getFloors() {
        return floors;
    }
    public void addFloor(int x, int y){
        floors.add(new Wall(x,y,1));
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tent getTent() {
        return tent;
    }

    public void setTent(Tent tent) {
        this.tent = tent;
    }

    public void init() {
        cameraX = 0;
        lastTime = System.currentTimeMillis();
        coins.clear();
        floors.clear();
        spikes.clear();
        enemies.clear();
        MAX_COINS = 0;
        makeLevel();
        player = new Player(startX, startY);
        reset();
        tent = new Tent(endX, endY);
    }


    public static void makeLevel() {
        try {
            Scanner scanner;
            if (lvl == null) {
                scanner = new Scanner(new File("levels/first_level.txt"));
            } else
                scanner = new Scanner(new File(lvl));
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] wall_pos = line.split(" ");
                int length = Integer.parseInt(wall_pos[2]);
                startX = Integer.parseInt(wall_pos[0]) + (length * 32) / 2 - 16;
                startY = Integer.parseInt(wall_pos[1]) - 70;
                for (int i = 1; i < wall_pos.length - 1; i += 3) {
                    int x_pos = Integer.parseInt(wall_pos[i - 1]);
                    int y_pos = Integer.parseInt(wall_pos[i]);
                    int num = Integer.parseInt(wall_pos[i + 1]);
                    floors.add(new Wall(x_pos, y_pos, num));
                }
                endX = floors.get(floors.size() - 1).getEndX();
                endY = floors.get(floors.size() - 1).getEndY();
            }
            line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] spike_pos = line.split(" ");
                for (int i = 1; i < spike_pos.length - 1; i += 3) {
                    int x_pos = Integer.parseInt(spike_pos[i - 1]);
                    int y_pos = Integer.parseInt(spike_pos[i]);
                    int num = Integer.parseInt(spike_pos[i + 1]);
                    for (int j = 0; j < num; j++) {
                        spikes.add(new Spike(x_pos, y_pos));
                        x_pos += 32;
                    }
                }
            }
            line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] coin_pos = line.split(" ");
                for (int i = 1; i < coin_pos.length; i += 2) {
                    coins.add(new Coin(Integer.parseInt(coin_pos[i - 1]), Integer.parseInt(coin_pos[i])));
                }
                MAX_COINS = coins.size();
            }
            if (scanner.hasNext()) {
                line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] enemy_pos = line.split(" ");
                    for (int i = 1; i < enemy_pos.length; i += 2) {
                        enemies.add(new Enemy(Integer.parseInt(enemy_pos[i - 1]), Integer.parseInt(enemy_pos[i])));
                    }
                    MAX_BULLETS = enemies.size() * 3 + 5;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void reset() {
        cameraX = 0;
        for (Enemy enemy : enemies) {
            enemy.reset();
        }
        for (Coin coin : coins) {
            coin.setVisible(true);
        }
        curCoins = 0;
        player.reset();
    }


    public void update() {
        for (Wall floor : floors) {
            floor.set(cameraX);
        }
        for (Spike spike : spikes) {
            spike.set(cameraX);
        }
        for (Enemy enemy : enemies) {
            enemy.update();
            enemy.set(player.getSpeed());
            enemy.checkAttack(player);
        }

        for (Coin coin : coins) {
            coin.set(cameraX);
            coin.update();
        }
        tent.set(cameraX);
        player.update();
        player.checkAttacks(enemies);

    }

    public boolean isEnd() {
        return player.getHitBox().intersects(tent.getHitBox());
    }

    public void keyPressed(int e) {
        if (e == KeyEvent.VK_A || e == KeyEvent.VK_LEFT) {
            player.setKeyLeft(true);
        }
        if (e == KeyEvent.VK_D || e == KeyEvent.VK_RIGHT) {
            player.setKeyRight(true);
        }
        if (e == KeyEvent.VK_W || e == KeyEvent.VK_UP)
            player.setKeyUP(true);


    }

    public void keyReleased(int e) {

        if (e == KeyEvent.VK_A || e == KeyEvent.VK_LEFT)
            player.setKeyLeft(false);
        if (e == KeyEvent.VK_D || e == KeyEvent.VK_RIGHT)
            player.setKeyRight(false);
        if (e == KeyEvent.VK_W || e == KeyEvent.VK_UP)
            player.setKeyUP(false);
    }

    public void keyTyped(char e) {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (e == 'e') {
            if (timer > 300 && !player.isDead() && player.isStay()) {
                player.shoot();
                timer = 0;
            }
        }
    }

}
