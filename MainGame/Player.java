package MainGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

interface interfaceplayer
{
    void Playersprite();
    void interact();
    void update();
    void drawplayer();    
}

public class Player {

    private gamePanel gamepanel;
    private Move keymove;
    private int playerxpos, playerypos;
    private int playerspeed;
    private BufferedImage idleleft1, idleleft2, idleright1, idleright2;
    private BufferedImage wleft1, wleft2, wleft3, wleft4, wleft5, wleft6, wleft7, wleft8;
    private BufferedImage wright1, wright2, wright3, wright4, wright5, wright6, wright7, wright8;
    private String direction;
    private int spritecount;
    private int spritenum;
    private boolean i;
    private int currentmap;
    private int x, y;
    private int toolequipped;

    public Player(gamePanel gamepanel, Move keymove, int playerxpos, int playerypos, int playerspeed)
    {
        this.gamepanel = gamepanel;
        this.keymove = keymove;
        this.playerxpos = playerxpos;
        this.playerypos = playerypos;
        this.x = playerxpos + gamepanel.getTilesize()/2;
        this.y = playerypos + gamepanel.getTilesize()/2;
        this.playerspeed = playerspeed;
        this.direction = "down";
        this.spritecount = 0;
        this.spritenum = 1;
        this.i = true; 
        this.currentmap = 0;
        Playersprite();
    }

    public void Playersprite()
    {
        try {
            idleleft1 = ImageIO.read(getClass().getResourceAsStream("/playersprite/idle_left_1.png"));
            idleleft2 = ImageIO.read(getClass().getResourceAsStream("/playersprite/idle_left_2.png"));
            idleright1 = ImageIO.read(getClass().getResourceAsStream("/playersprite/idle_right_1.png"));
            idleright2 = ImageIO.read(getClass().getResourceAsStream("/playersprite/idle_right_2.png"));


            wleft1 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_1.png"));
            wleft2 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_2.png"));
            wleft3 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_3.png"));
            wleft4 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_4.png"));
            wleft5 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_5.png"));
            wleft6 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_6.png"));
            wleft7 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_7.png"));
            wleft8 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_left_8.png"));

            wright1 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_8.png"));
            wright2 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_2.png"));
            wright3 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_3.png"));
            wright4 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_4.png"));
            wright5 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_5.png"));
            wright6 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_6.png"));
            wright7 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_7.png"));
            wright8 = ImageIO.read(getClass().getResourceAsStream("/playersprite/walk_right_8.png")); 
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interact() { 
    
        if (keymove.tool1) {
            toolequipped = 1; //Plow
        } else if (keymove.tool2) {
            toolequipped = 2; //Water
        } else if (keymove.tool3) {
            toolequipped = 3; //Fertilizer
        }  else if (keymove.tool4) {
            toolequipped = 4; //Pickaxe
        } else if (keymove.tool5) {
            toolequipped = 5; //Shovel
        } else if (keymove.seed1) {
            toolequipped = 6; // Turnip veg
        } else if (keymove.seed2) {
            toolequipped = 7; // Carrot
        } else if (keymove.seed3) {
            toolequipped = 8; //Potato
        } else if (keymove.seed4) {
            toolequipped = 9; //Rose
        } else if (keymove.seed5) {            toolequipped = 10; //Turnip flower
        } else if (keymove.seed6) {
            toolequipped = 11; //Sunflower
        } else if (keymove.seed7) {
            toolequipped = 12; //Mango
        } else if (keymove.seed8) {
            toolequipped = 13; //Apple
        }
        if (currentmap == 4) {
            if (getY() <= gamepanel.getScreenheight() + gamepanel.getTilesize()*9 && getY() >= gamepanel.getScreenheight() + gamepanel.getTilesize()*4 &&
                getX() >= (gamepanel.getTilesize()*3) && getX() <= gamepanel.getScreenwidth() - gamepanel.getTilesize()*3) { 
                if (keymove.harvest) {
                    gamepanel.croppers.harvesting();
                }
                if (keymove.interact) {
                    switch(toolequipped){
                        case 1: gamepanel.croppers.plowfield(); break;
                        case 2: gamepanel.croppers.waterplants(); break;
                        case 3: gamepanel.croppers.fertilizerplants(); break;
                        case 4: gamepanel.croppers.rocksremove(); break;
                        case 5: gamepanel.croppers.shovelling(); break;
                        case 6: gamepanel.croppers.seed1plant(); break;
                        case 7: gamepanel.croppers.seed2plant(); break;
                        case 8: gamepanel.croppers.seed3plant(); break;
                        case 9: gamepanel.croppers.seed4plant(); break;
                        case 10: gamepanel.croppers.seed5plant(); break;
                        case 11: gamepanel.croppers.seed6plant(); break;
                        case 12: gamepanel.croppers.seed7plant(); break;
                        case 13: gamepanel.croppers.seed8plant(); break;
                        default: System.out.println("emptyhand"); break;
                    }
                }
            } 
        } 
        
        if (currentmap == 3 && keymove.interact) {
                   if (getY() >= -(gamepanel.getScreenheight()) + gamepanel.getTilesize()*5 && getY() <= -(gamepanel.getTilesize()*6) && getX() <= (gamepanel.getTilesize()*3) && getX() >= gamepanel.getTilesize()*2){
                gamepanel.shop.buyseed1();
            } else if (getY() >= -(gamepanel.getScreenheight()) + gamepanel.getTilesize()*2 && getY() <= -(gamepanel.getTilesize()*9) && getX() <= (gamepanel.getTilesize()*3) && getX() >= gamepanel.getTilesize()*2){
                gamepanel.shop.buyseed2();
            } else if (getY() >= -(gamepanel.getScreenheight()) && getY() <= -(gamepanel.getTilesize()*11) && getX() <= (gamepanel.getTilesize()*5) && getX() >= gamepanel.getTilesize()*2){
                gamepanel.shop.buyseed3();
            } else if (getY() >= -(gamepanel.getScreenheight()) && getY() <= -(gamepanel.getTilesize()*11) && getX() <= (gamepanel.getTilesize()*8) && getX() >= gamepanel.getTilesize()*5){
                gamepanel.shop.buyseed4();
            } else if (getY() >= -(gamepanel.getScreenheight()) && getY() <= -(gamepanel.getTilesize()*11) && getX() <= (gamepanel.getTilesize()*12) && getX() >= gamepanel.getTilesize()*9){
                gamepanel.shop.buyseed5();
            } else if (getY() >= -(gamepanel.getScreenheight()) && getY() <= -(gamepanel.getTilesize()*11) && getX() <= (gamepanel.getTilesize()*15) && getX() >= gamepanel.getTilesize()*12){
                gamepanel.shop.buyseed6();
            } else if (getY() >= -(gamepanel.getScreenheight()) + gamepanel.getTilesize()*2 && getY() <= -(gamepanel.getTilesize()*9) && getX() <= (gamepanel.getTilesize()*14) && getX() >= gamepanel.getTilesize()*13){
                gamepanel.shop.buyseed7();
            } else if (getY() >= -(gamepanel.getScreenheight()) + gamepanel.getTilesize()*5 && getY() <= -(gamepanel.getTilesize()*6) && getX() <= (gamepanel.getTilesize()*14) && getX() >= gamepanel.getTilesize()*13){
                gamepanel.shop.buyseed8();
            }
        }
        keymove.interact = false;
        keymove.harvest = false;
    }

    public void update()
    {
        if (keymove.up == true) {
            direction = "up";
            // Shop
            if (currentmap == 3) {
                if (y >= -(gamepanel.getScreenheight() - gamepanel.getTilesize() - gamepanel.getTilesize()/2)) {
                    playerypos -= playerspeed; 
                    y -= playerspeed;
                } 
            } else if (currentmap == 1 || currentmap == 2) {
                if (y >= gamepanel.getTilesize()) {
                    playerypos -= playerspeed; 
                    y -= playerspeed;
                }
            } else {
                playerypos -= playerspeed; 
                y -= playerspeed;
            }
        } if (keymove.down == true) {
            // Field
            direction = "down"; 
            if (currentmap == 4) {
                if (y <= ((gamepanel.getScreenheight()*2) - (gamepanel.getTilesize()/2 + gamepanel.getTilesize()))) {
                    playerypos += playerspeed; 
                    y += playerspeed;
                }
            } else if (currentmap == 1 || currentmap == 2) {
                if (y <= (gamepanel.getScreenheight() - gamepanel.getTilesize()*3 + gamepanel.getTilesize()/2)) {
                    playerypos += playerspeed; 
                    y += playerspeed;
                }
            } else {
                playerypos += playerspeed; 
                y += playerspeed; 
            }
            //House
        } if (keymove.left == true) {
            direction = "left";
            i = true;
            if (currentmap == 1) { // House
                if (x >= -(gamepanel.getScreenwidth())) {
                    playerxpos -= playerspeed; 
                    x -= playerspeed;
                } 
            } else if (currentmap == 4) { // Field
                if (x >= gamepanel.getTilesize()){
                    playerxpos -= playerspeed; 
                    x -= playerspeed;
                }
            } else if (currentmap == 3) { //Shop
                if (x >= gamepanel.getTilesize()*2 + gamepanel.getTilesize()/2){
                    playerxpos -= playerspeed; 
                    x -= playerspeed;
                }
            } else { 
                playerxpos -= playerspeed; 
                x -= playerspeed;
            }
            //Barn
        } if (keymove.right == true) {
            direction = "right";
            i = false;
            if (currentmap == 2) {//Barn
                if (x <= (gamepanel.getScreenwidth()*2 - gamepanel.getTilesize() + gamepanel.getTilesize()/2)) {
                    playerxpos += playerspeed; 
                    x += playerspeed;
                }
            } else if (currentmap == 4) { //Field
                if (x <= gamepanel.getScreenwidth()- gamepanel.getTilesize()*2 + gamepanel.getTilesize()){
                    playerxpos += playerspeed; 
                    x += playerspeed;
                }
            } else if (currentmap == 3) { //Shop
                if (x <= gamepanel.getScreenwidth()- gamepanel.getTilesize()*3 + gamepanel.getTilesize()/2){
                    playerxpos += playerspeed; 
                    x += playerspeed;
                }
            } else {
                playerxpos += playerspeed; 
                x += playerspeed;
            }
        } if (keymove.left == false && keymove.right == false && keymove.up == false && keymove.down == false) {
            direction = "stay";  
        }

        if (x <= -(gamepanel.getTilesize())) {
            currentmap = 1;; // House
        } else if (x >= ((gamepanel.getScreenwidth()) - (gamepanel.getTilesize()/2)) + gamepanel.getTilesize()) {
            currentmap = 2;; // Barn
        } else if (y <= -(gamepanel.getTilesize())) {
            currentmap = 3;; // Shop
        } else if (y >= ((gamepanel.getScreenheight()) - (gamepanel.getTilesize()/2)) + gamepanel.getTilesize()) {
            currentmap = 4; // Field
        } else {
            currentmap = 0;
        }
 
        spritecount++;
        if (spritecount > 8){
            if (spritenum == 1) {
                spritenum = 2;
            } else if (spritenum == 2) {
                spritenum = 3;
            } else if (spritenum == 3) {
                spritenum = 4;
            } else if (spritenum == 4) {
                spritenum = 5;
            } else if (spritenum == 5) {
                spritenum = 6;
            } else if (spritenum == 6) {
                spritenum = 7;
            } else if (spritenum == 7) {
                spritenum = 8;
            } else if (spritenum == 8) {
                spritenum = 1;
            }
            spritecount = 0;
        }

        if (playerxpos < -(gamepanel.getTilesize())) {
            playerxpos = gamepanel.getTilesize()*gamepanel.getMaxscreencolumns();
        } else if (playerxpos > gamepanel.getTilesize()*gamepanel.getMaxscreencolumns()) {
            playerxpos = -(gamepanel.getTilesize());
        } else if (playerypos < -(gamepanel.getTilesize())) {
            playerypos = gamepanel.getTilesize()*gamepanel.getMaxscreenrows();
        } else if (playerypos > gamepanel.getTilesize()*gamepanel.getMaxscreenrows()) {
            playerypos = -(gamepanel.getTilesize());
        } 
    }

    public void drawplayer(Graphics2D graphics2d)
    {
        BufferedImage image = null;

        // movement animation
        switch (direction) {
            case "up":
                if (i == true) {
                    switch (spritenum) {
                        case 1: image = wleft1; break;
                        case 2: image = wleft2; break;
                        case 3: image = wleft3; break;
                        case 4: image = wleft4; break;
                        case 5: image = wleft5; break;
                        case 6: image = wleft6; break;
                        case 7: image = wleft7; break;
                        case 8: image = wleft8; break;
                    }
                } else if (i == false) {
                    switch (spritenum) {
                        case 1: image = wright1; break;
                        case 2: image = wright2; break;
                        case 3: image = wright3; break;
                        case 4: image = wright4; break;
                        case 5: image = wright5; break;
                        case 6: image = wright6; break;
                        case 7: image = wright7; break;
                        case 8: image = wright8; break;
                    }
                }
                break;
            case "down":
                if (i == true) {
                    switch (spritenum) {
                        case 1: image = wleft1; break;
                        case 2: image = wleft2; break;
                        case 3: image = wleft3; break;
                        case 4: image = wleft4; break;
                        case 5: image = wleft5; break;
                        case 6: image = wleft6; break;
                        case 7: image = wleft7; break;
                        case 8: image = wleft8; break;
                    }
                } else if (i == false) {
                    switch (spritenum) {
                        case 1: image = wright1; break;
                        case 2: image = wright2; break;
                        case 3: image = wright3; break;
                        case 4: image = wright4; break;
                        case 5: image = wright5; break;
                        case 6: image = wright6; break;
                        case 7: image = wright7; break;
                        case 8: image = wright8; break;
                    }
                }
                break;
            case "left":
                switch (spritenum) {
                    case 1: image = wleft1; break;
                    case 2: image = wleft2; break;
                    case 3: image = wleft3; break;
                    case 4: image = wleft4; break;
                    case 5: image = wleft5; break;
                    case 6: image = wleft6; break;
                    case 7: image = wleft7; break;
                    case 8: image = wleft8; break;
                } 
                break;
            case "right":
                switch (spritenum) {
                    case 1: image = wright1; break;
                    case 2: image = wright2; break;
                    case 3: image = wright3; break;
                    case 4: image = wright4; break;
                    case 5: image = wright5; break;
                    case 6: image = wright6; break;
                    case 7: image = wright7; break;
                    case 8: image = wright8; break;
                } 
                break;
            case "stay":   
                if (i == true) {
                    switch (spritenum) {
                        case 1: image = idleleft1; break;
                        case 2: image = idleleft1; break;
                        case 3: image = idleleft1; break;
                        case 4: image = idleleft1; break;
                        case 5: image = idleleft2; break;
                        case 6: image = idleleft2; break;
                        case 7: image = idleleft2; break;
                        case 8: image = idleleft2; break;
                    }
                } else if (i == false) {
                    switch (spritenum) {
                        case 1: image = idleright1; break;
                        case 2: image = idleright1; break;
                        case 3: image = idleright1; break;
                        case 4: image = idleright1; break;
                        case 5: image = idleright2; break;
                        case 6: image = idleright2; break;
                        case 7: image = idleright2; break;
                        case 8: image = idleright2; break;
                    }
                }
                break;
        }
        graphics2d.drawImage(image, playerxpos, playerypos, gamepanel.getTilesize(), gamepanel.getTilesize(), null);
    }


    public int getCurrentmap() {
        return currentmap;
    }

    public void setCurrentmap(int currentmap) {
        this.currentmap = currentmap;
    }

    public int getPlayerxpos() {
        return playerxpos;
    }

    public void setPlayerxpos(int playerxpos) {
        this.playerxpos = playerxpos;
    }

    public int getPlayerypos() {
        return playerypos;
    }

    public void setPlayerypos(int playerypos) {
        this.playerypos = playerypos;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPlayerspeed() {
        return playerspeed;
    }

    public void setPlayerspeed(int playerspeed) {
        this.playerspeed = playerspeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getToolequipped() {
        return toolequipped;
    }

    public void setToolequipped(int toolequipped) {
        this.toolequipped = toolequipped;
    }
}