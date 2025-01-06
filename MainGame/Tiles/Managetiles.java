package MainGame.Tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import MainGame.Stats;
import MainGame.gamePanel;
import MainGame.Player;

import java.text.DecimalFormat;



public class Managetiles {
    private gamePanel gamepanel;
    private Player player;
    private Stats stats;
    private Tiles[] tile;
    private int maptile1[][][];
    private int cropmap[][];
    private int i, j;
    private int totaltiles = 200;
    private int count = 0;
    private int toolbar[];
    private int toolbarequipped[];
    private int equipped;
    
    public Managetiles(gamePanel gamepanel, Player player, Stats stats){
        this.gamepanel = gamepanel;
        this.player = player;
        this.stats = stats;
        this.tile = new Tiles[totaltiles];
        this.maptile1 = new int[gamepanel.getMaxmaps()][gamepanel.getMaxscreencolumns()][gamepanel.getMaxscreenrows()];
        this.cropmap = new int[stats.getCropwidth()][stats.getCropheight()];
        this.toolbar = new int[16];
        this.toolbarequipped = new int[5];
        this.equipped = player.getToolequipped();

        tileimage();
        loadmap("/maps/house.txt", 1);
        loadmap("/maps/barn.txt", 2);
        loadmap("/maps/shop.txt", 3);
        loadmap("/maps/field.txt", 4);
        loadmap("/maps/mainlobby.txt", 0);
    }

    private DecimalFormat df = new DecimalFormat("000");

    public void tileimage()
    {
        try {
            for (i = 0; i < totaltiles; i++) {
                tile[i] = new Tiles();
            } 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/dirt.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/stone.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));

            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/unplowed.png"));
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/plowedfield.png"));
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/bigrock.png"));
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/bush.png"));

            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/fencevertright.png"));
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/fencevertleft.png"));
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/fenceright.png"));
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/fenceleft.png"));

            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfCarrot.png"));
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfRose.png"));
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfSunflower.png"));
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfTurnipFlower.png"));
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfTurnipVeggie.png"));

            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestableCarrot.png"));
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestableRose.png"));
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestableSunflower.png"));
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestableTurnipFlower.png"));
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestableTurnipVeggie.png"));

            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/DeadCarrot.png"));
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/deadrose.png"));
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/deadsunflower.png"));
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/deadturnipflwr.png"));
            tile[134].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/deadturnipveg.png"));

            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/TurnipVeggieicon.png"));
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Carroticon.png"));
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Potatoicon.png"));
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Roseicon.png"));
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/TurnipFlowericon.png"));
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Sunflowericon.png"));
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Mangoicon.png"));
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/icons/Appleicon.png"));
            

            for (i = 0; i < 36; i++) { //last number 68
                tile[i+33].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/House/image_part_" + df.format(i+1) + ".png"));
            }

            for (i = 0; i < 64; i++) { //last number 132
                tile[i+69].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Barn/image_part_" + df.format(i+1) + ".png"));
            }

            tile[133].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/withseeds.png"));
            //134 deadturnipveg
            tile[135].image = ImageIO.read(getClass().getResourceAsStream("/tools/Plow.png"));
            tile[136].image = ImageIO.read(getClass().getResourceAsStream("/tools/Waterpot.png"));
            tile[137].image = ImageIO.read(getClass().getResourceAsStream("/tools/Fertilizer.png"));
            tile[138].image = ImageIO.read(getClass().getResourceAsStream("/tools/Pickaxe.png"));
            tile[139].image = ImageIO.read(getClass().getResourceAsStream("/tools/Shovel.png"));
            
            tile[140].image = ImageIO.read(getClass().getResourceAsStream("/tools/Plowequip.png"));
            tile[141].image = ImageIO.read(getClass().getResourceAsStream("/tools/Waterpotequip.png"));
            tile[142].image = ImageIO.read(getClass().getResourceAsStream("/tools/Fertilizerequip.png"));
            tile[143].image = ImageIO.read(getClass().getResourceAsStream("/tools/Pickaxeequip.png"));
            tile[144].image = ImageIO.read(getClass().getResourceAsStream("/tools/shovelequip.png"));
            
            for (i = 0; i < 4; i++) { //last number 148
                tile[i+145].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Well/image_part_" + df.format(i+1) + ".png"));
            }
            tile[149].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sign.png"));
            tile[150].image = ImageIO.read(getClass().getResourceAsStream("/tools/coin.png"));
            tile[151].image = ImageIO.read(getClass().getResourceAsStream("/tools/exp.png"));
            tile[152].image = ImageIO.read(getClass().getResourceAsStream("/tools/Graybox.png"));

            for (i = 0; i < 9; i++) { //last number 161
                tile[i+153].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/appletree/image_part_" + df.format(i+1) + ".png"));
            }
            for (i = 0; i < 9; i++) { //last number 170
                tile[i+162].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/mangotree/image_part_" + df.format(i+1) + ".png"));
            }
            for (i = 0; i < 9; i++) { //last number 179
                tile[i+171].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/halftree/image_part_" + df.format(i+1) + ".png"));
            }
            for (i = 0; i < 9; i++) { //last number 188
                tile[i+180].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/deadtree/image_part_" + df.format(i+1) + ".png"));
            }

            tile[189].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HalfPotato.png"));
            tile[190].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/HarvestablePotato.png"));
            tile[191].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/DeadPotato.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void itemoverlay(Graphics2D graphics2d) {

        toolbar[5] = 150;
        toolbar[6] = 151;
        toolbar[7] = 152;
        for (i = 0; i < 5; i++) {
            toolbar[i] = 135+i;
            toolbarequipped[i] = 140+i;
        }
        for (i = 8; i < 16; i++) {
            toolbar[i] = 25+(i-8);
        }
        for (i = 0; i < 5; i++) {
            if (i == player.getToolequipped()-1) {
                toolbar[i] = toolbarequipped[i];
            }
        }
        for (i = 0; i < 16; i++) {
            graphics2d.drawImage(tile[toolbar[i]].image, gamepanel.getTilesize()*i, gamepanel.getTilesize()*gamepanel.getMaxscreenrows() - gamepanel.getTilesize(), gamepanel.getTilesize(), gamepanel.getTilesize(), null);
        }
    }

    public void loadmap(String filepath, int mapnum)
    {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row, col;

            for (row = 0; row < gamepanel.getMaxscreenrows(); row++) {
                String line = br.readLine();
                for (col = 0; col < gamepanel.getMaxscreencolumns(); col++) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    maptile1[mapnum][col][row] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            
        }
    }

    public void drawtile(Graphics2D graphics2d)
    {
        int col, row, x, y = 0;

        for (row = 0; row < gamepanel.getMaxscreenrows(); row++){
            x = 0;
            for (col = 0; col < gamepanel.getMaxscreencolumns(); col++){
                int tilechosen = maptile1[player.getCurrentmap()][col][row];
                graphics2d.drawImage(tile[tilechosen].image, x, y, gamepanel.getTilesize(), gamepanel.getTilesize(), null);
                x += gamepanel.getTilesize();
            }
            y += gamepanel.getTilesize();            
        }
    }

    public void overlay(Graphics2D graphics2d)
    {
        int col, row, x, y = gamepanel.getTilesize()*3;
        cropoverlay();
        for (row = 4; row < gamepanel.getMaxscreenrows()-4; row++){
            x = gamepanel.getTilesize()*3;
            for (col = 4; col < gamepanel.getMaxscreencolumns()-2; col++){
                int cropper = cropmap[col-4][row-4];
                graphics2d.drawImage(tile[cropper].image, x, y, gamepanel.getTilesize(), gamepanel.getTilesize(), null);
                x += gamepanel.getTilesize();
            }
            y += gamepanel.getTilesize();            
        }
    }

    public void cropoverlay()
    {   

        gamepanel.croppers.rocks();
        count = 1;
        gamepanel.croppers.rocks();
        gamepanel.croppers.mainplot();
        for(i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                cropmap[j][i] = gamepanel.croppers.getBaselayer()[j][i];
            }
        }
    }

    public int[][][] getMaptile1() {
        return maptile1;
    }

    public void setMaptile1(int[][][] maptile1) {
        this.maptile1 = maptile1;
    }

    public Tiles[] getTile() {
        return tile;
    }

    public void setTile(Tiles[] tile) {
        this.tile = tile;
    }

    public int[][] getCropmap() {
        return cropmap;
    }

    public void setCropmap(int[][] cropmap) {
        this.cropmap = cropmap;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    

    
}
