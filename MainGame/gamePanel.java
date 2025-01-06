package MainGame;

import MainGame.Tiles.Crops;
import MainGame.Tiles.Managetiles;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.text.DecimalFormat;

public class gamePanel extends JPanel implements Runnable{

    // SCREEN RESOLUTION
    private final int ogtilesize = 16; //16x16 - 16-bit
    private final int scale = 3;
    private final int tilesize = ogtilesize * scale; //48x48 - scaled 16-bit
    private final int maxscreencolumns = 16;
    private final int maxscreenrows =  13;
    private final int screenwidth = maxscreencolumns * tilesize; // 960 pixels
    private final int screenheight = maxscreenrows * tilesize; // 720 pixels
    private DecimalFormat df = new DecimalFormat("00");

    // FPS - number of frames per second occuring
    private int FPS;

    // NUMBER OF MAPS
    private int maxmaps = 5;
    private double seconds = 0; 
    private int minutes = 0, now = 0;
    private boolean timecheck = false;
    
    // What runs the code
    Thread gameThread;

    //MOVE and ACTION class - allows movement
    Move keymove = new Move();

    // PLAYER class - player position, action, etc.
    Player player = new Player(
        this,
        keymove, 
        ((screenwidth/2) - (tilesize/2)), 
        ((screenheight/2) - (tilesize/2)), 
        4
    );

    //SEEDS class - contains all information about the seeds and their specifications
    Seedcrops seeds = new Seedcrops (
        "Root Crop",
        "Flower",
        "Fruit Tree",

        //seed1
        "Turnip", 
        2,
        1,
        0,
        5,
        6,
        5,
        1,
        2,

        //seed2
        "Carrot",
        3,
        1,
        0,
        10,
        9,
        7.5,
        1,
        2,

        //seed3
        "Potato",
        5, 
        3,
        1,
        20,
        3,
        12.5,
        1,
        10,

        //seed4
        "Rose",
        1,
        1,
        0,
        5,
        5,
        2.5,
        1,

        //seed5
        "turnip",
        2,
        2,
        0,
        10,
        9,
        5,
        1,

        //seed6
        "Sunflower",
        3,
        2,
        1,
        20,
        19,
        7.5,
        1,

        //seed7
        "Mango",
        10,
        7,
        4,
        100,
        8,
        25,
        5,
        15,

        //seed8
        "Apple",
        10,
        7,
        5,
        200,
        5,
        25,
        10,
        15
        );

    // TOOL class - holds all information about tools used
    Equipment tool = new Equipment(
        "Plow", 
        0, 
        0.5,
        "Watering Can", 
        0, 
        0.5,
        "Fertilizer", 
        10, 
        4,
        "Pickaxe", 
        50, 
        15,
        "Shovel", 
        7,
        2
        );

    // STATS class - contains info about player stats
    Stats stats = new Stats(
        seeds,
        tool,
        "player",
        10,
        5,
        500, 
        0,
        "Shop",
        "House", 
        "Field", 
        "Storage",
        0,
        "Farmer",
        "Registered Farmer",
        "Distinguished Farmer",
        "Legendary Farmer",
        0,
        5,
        10,
        15,
        0,
        1,
        2,
        4,
        0,
        1,
        2,
        3,
        0,
        0,
        1,
        2,
        0,
        0,
        0,
        1,
        0,
        200,
        300,
        400
        );

    //TILES class - contains the all sprites used to show graphics
    public Managetiles tilebg = new Managetiles(
        this, 
        player,
        stats
    );

    //SHOP class - allows player to buy more seeds
    public Shop shop = new Shop (
        stats,
        10,
        10,
        10,
        10,
        10,
        10,
        10,
        10
    );
    
    //CROPS class - plows field, plants seeds, harvest crops, etc. The main attraction 
    public Crops croppers = new Crops(
        this,
        tilebg,
        player,
        stats,
        seeds,
        shop,
        tool
    );


    // gamePanel() - Supposed "Main" function
    public gamePanel(int FPS)
    {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keymove);
        this.setFocusable(true);
        this.FPS = FPS;
    }
    
    // startThread() - Starts Gameplay
    public void startThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // keeps track of Time loops
    public void run() 
    {
        double drawinterval = 1000000000/FPS;
        double lasttime = System.nanoTime();
        double delta = 0;
        long currenttime, timer = 0, drawcount = 0;
        while (gameThread != null){
            currenttime = System.nanoTime();
            delta += (currenttime - lasttime)/drawinterval;
            timer += (currenttime - lasttime);
            lasttime = currenttime;
            seconds += 0.000000025;
            if (seconds >= 60) {
                minutes += 1;
                seconds = 0;
            } if (minutes >= 100) {
                minutes = 0;
            }
        
            if (delta >= 1){
                action();
                update();
                repaint();
                delta--;
                drawcount++;
            }
            //System.out.println(df.format((int)(days)) + ":" + df.format((int)(minutes)) + ":" +df.format((int)(seconds/10000)));
            if (timer >= 1000000000){
                
                //System.out.println("FPS: " + drawcount);
                drawcount = 0;
                timer = 0;
            }
        }
    }
    // update() - calls player updated positions
    public void update()
    {
        player.update();
    }

    // action() - calls when the player interacts with items
    public void action()
    {
        player.interact();
    }
    
    // paintComponent() - illustrates graphics and put it on the screen
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D)graphics;

        tilebg.drawtile(graphics2d);
        if (player.getCurrentmap() == 4) {
            croppers.croplocation();
            tilebg.overlay(graphics2d);
        }
        player.drawplayer(graphics2d);
        tilebg.itemoverlay(graphics2d);
        paintLabel(graphics2d);
        timeprint(graphics2d);
        if (croppers.isErrorindicator()) {
            stats.notifyreset();
            printerror(graphics2d);
            if (timecheck == false){
                now = (int)(seconds);
                timecheck = true; 
                //System.out.println(minutes);
            } 
        } 
        if (stats.isNotifycheck()) {
            croppers.errorreset();
            printnotify(graphics2d);
            if (timecheck == false){
                now = (int)(seconds);
                timecheck = true; 
            } 
        }
        if (seconds > now+2) {
            croppers.errorreset();
            stats.notifyreset();
            timecheck = false;
        }     
        player.interact();
        graphics2d.dispose();
    }
    
    // paintLabel() - printsout text in GUI as indicators of amount/time/level/etc.
    public void paintLabel(Graphics toolbar){
        // LABEL
        toolbar.setFont(new Font("SansSerif", Font.BOLD, 15));
        toolbar.setColor(Color.white);
        toolbar.drawString(stats.getObjectcoins()+"", tilesize*5 + tilesize/3 - 5, screenheight - (int)(tilesize) );
        toolbar.drawString(stats.getExp()+"", tilesize*6 + tilesize/3 -3, screenheight - (int)(tilesize));
        toolbar.drawString("Lvl " + stats.getFarmerlevel(), tilesize*7 + tilesize/3 -8, screenheight - (int)(tilesize/2.5));
        toolbar.drawString(""+shop.getSeedamount1(), tilesize*8 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount2(), tilesize*9 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount3(), tilesize*10 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount4(), tilesize*11 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount5(), tilesize*12 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount6(), tilesize*13 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount7(), tilesize*14 + tilesize/3 -8, screenheight - (int)(tilesize));
        toolbar.drawString(""+shop.getSeedamount8(), tilesize*15 + tilesize/3 -8, screenheight - (int)(tilesize));
        
    }

    // timeprint() - prints the time
    public void timeprint(Graphics timewatch) {
        timewatch.setFont(new Font("SansSerif", Font.BOLD, 30));  
        timewatch.setColor(Color.white);
        timewatch.drawString("Day:" + df.format((int)(minutes)) + "." +df.format((int)(seconds)), 5, tilesize/2);
    }
    // printerror() - prints errors
    public void printerror(Graphics printerror) {
        printerror.setFont(new Font("SansSerif", Font.BOLD, 20)); 
        printerror.setColor(Color.white); 
        printerror.drawString(croppers.getError(), tilesize, screenheight - (tilesize*2 + tilesize/2));
    }

    //  printnotify() - prints messages
    public void printnotify(Graphics printerror) {
        printerror.setFont(new Font("SansSerif", Font.BOLD, 20)); 
        printerror.setColor(Color.white); 
        printerror.drawString(stats.getNotify(), tilesize, screenheight - (tilesize*2 + tilesize/2));
    }

    public int getTilesize() {
        return tilesize;
    }

    public int getMaxscreencolumns() {
        return maxscreencolumns;
    }

    public int getMaxscreenrows() {
        return maxscreenrows;
    }

    public int getMaxmaps() {
        return maxmaps;
    }

    public void setMaxmaps(int maxmaps) {
        this.maxmaps = maxmaps;
    }

    public int getScreenwidth() {
        return screenwidth;
    }

    public int getScreenheight() {
        return screenheight;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
