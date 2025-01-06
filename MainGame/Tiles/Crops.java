package MainGame.Tiles;

import MainGame.Seedcrops;
import MainGame.Shop;
import MainGame.Stats;
import MainGame.gamePanel;
import MainGame.Equipment;
import MainGame.Player;
import java.util.Random;

interface interfacecrops
{
    void errorreset(); // resets error messages
    void croplocation(); //shows where player is currently located in crop
    void rocks(); //places rocks in 
    void rocksremove(); // removes rocks
    void plowfield(); //plows fields
    void waterplants(); // waters plants
    void fertilizerplants(); // fertilizes plants
    void waterandfertstatus(); // tells if the plant is watered or fertilized
    void shovelling(); // uses shovel and removes crops
    void harvesting(); // harvests the crop 
    void fruittreecropremove(); // used for removing the bigger crops that need 3x3 spaces
    void seed1plant(); // plants seed1
    void seed2plant(); // plants seed2
    void seed3plant(); // plants seed3
    void seed4plant(); // plants seed4
    void seed5plant(); // plants seed5
    void seed6plant(); // plants seed6
    void seed7plant(); // plants seed7
    void seed8plant(); // plants seed8
    void cropoccupied(); // used for occupying the bigger crops that need 3x3 spaces
    void mainplot(); // all of the methods used above is used in this function call
}

public class Crops extends Managetiles{
    
    private gamePanel gamepanel;
    private Managetiles managetiles;
    private Player player;
    private Stats stats;
    private Seedcrops seedcrops;
    private Equipment equipment;
    private Shop shop;

    private int i, j;
    private int playertilex, playertiley;
    private String error = "";
    private boolean errorindicator = false;

    // Main layer
    private int baselayer[][];

    //rocks
    private int rockrand;
    private int rockrand2;
    private Random random = new Random();
    private int[][] rockstatus;

    // plotstatus
    private int plotstatus[][];
    
    // cropstatus
    private int cropstatus[][];

    // dayplanted
    private int dayplanted[][];
    private boolean daycheck[][];

    // watering pot
    private int water[][];

    // Fertilizer
    private int fertilizer[][];

    //harvest yield
    private int harvest[];
    
    public Crops(gamePanel gamepanel, Managetiles managetiles, Player player, Stats stats, Seedcrops seedcrops, Shop shop, Equipment equipment)
    {
        super(gamepanel, player, stats);
        this.gamepanel = gamepanel;
        this.managetiles = managetiles;
        this.player = player;
        this.stats = stats;
        this.seedcrops = seedcrops;
        this.shop = shop;
        this.equipment = equipment;
        this.baselayer = new int[stats.getCropwidth()][stats.getCropheight()];
        this.rockstatus = new int[stats.getCropwidth()][stats.getCropheight()];
        this.plotstatus = new int[stats.getCropwidth()][stats.getCropheight()];
        this.cropstatus = new int[stats.getCropwidth()][stats.getCropheight()];
        this.dayplanted = new int[stats.getCropwidth()][stats.getCropheight()];
        this.daycheck = new boolean[stats.getCropwidth()][stats.getCropheight()];
        this.water = new int[stats.getCropwidth()][stats.getCropheight()];
        this.fertilizer = new int[stats.getCropwidth()][stats.getCropheight()];
        this.harvest = new int[seedcrops.size()];

        for (int i = 0; i < seedcrops.size(); i++) {
            harvest[i] = 0;
        }

        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    baselayer[j][i] = 6;
            }
        }
        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    rockstatus[j][i] = 0;
            }
        }
        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    plotstatus[j][i] = 0;
            }
        }
        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    cropstatus[j][i] = 0;
            }
        }
        
        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    dayplanted[j][i] = 0;
                    daycheck[playertilex][playertiley] = false;
            }
        }

        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    daycheck[j][i] = false;
            }
        }

        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    water[j][i] = 0;
            }
        }

        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                    fertilizer[j][i] = 0;
            }
        }

    }

    public void errorreset() {
        error = "";
        errorindicator = false;
    }

    //Player location
    public void croplocation()
    {
        if (player.getY() <= gamepanel.getScreenheight() + gamepanel.getTilesize()*9 && player.getY() >= gamepanel.getScreenheight() + gamepanel.getTilesize()*4 &&
            player.getX() >= (gamepanel.getTilesize()*3) && player.getX() <= gamepanel.getScreenwidth() - gamepanel.getTilesize()*3) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                if (player.getX() >= (gamepanel.getTilesize()*3 + gamepanel.getTilesize()*j) && player.getX() <= (gamepanel.getTilesize()*3 + gamepanel.getTilesize()*(j+1))) {
                    playertilex = j;       
                }
            } 
            for (i = 0; i < stats.getCropheight(); i++){
                if (player.getY() >= (gamepanel.getScreenheight() + gamepanel.getTilesize()*4 + gamepanel.getTilesize()*i) && player.getY() <= (gamepanel.getScreenheight() + gamepanel.getTilesize()*4 + gamepanel.getTilesize()*(i+1))) {
                    playertiley = i;
                }
            }
        } else {
            playertilex = -1;
            playertiley = -1;
        }
    }

    private int randomnumber()
    {
        return this.random.nextInt(stats.getCropwidth());
    }

    // Rocks
    public void rocks()
    {
        if (managetiles.getCount() == 0) {
            for (i = 0; i < stats.getCropheight(); i++) {
                do
                {
                    rockrand = randomnumber();
                    rockrand2 = randomnumber();
                } while (rockrand == rockrand2);
                rockstatus[rockrand][i] = 1;
                rockstatus[rockrand2][i] = 1;
            }
        }
    }

    //Removes the rocks
    public void rocksremove()
    {
        if (stats.getObjectcoins() >= equipment.getTool4Cost()) {
            if (rockstatus[playertilex][playertiley] == 0) {
                errorindicator = true; error = ("ERROR: No rocks were found. Coins still deducted");
            } else if (rockstatus[playertilex][playertiley] == 1) {
                rockstatus[playertilex][playertiley] = 0;
                stats.expincreaserocks();
            }
        } else {
            errorindicator = true; error = ("ERROR: Insufficent coins");
        }
        stats.coindeductrocks();
    }
    //Plows the field
    public void plowfield ()
    {
        if (rockstatus[playertilex][playertiley] == 1){
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot already plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0){
            stats.plowcostexp();
            plotstatus[playertilex][playertiley] = 1;
        }
    }

    public void waterplants()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot unoccupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            water[playertilex][playertiley] += 1;
            if (cropstatus[playertilex][playertiley] == 1 && water[playertilex][playertiley] <= (seedcrops.getWaterneed1() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed1()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 2 && water[playertilex][playertiley] <= (seedcrops.getWaterneed2() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed2()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 3 && water[playertilex][playertiley] <= (seedcrops.getWaterneed3() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed3()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 4 && water[playertilex][playertiley] <= (seedcrops.getWaterneed4() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed4()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 5 && water[playertilex][playertiley] <= (seedcrops.getWaterneed5() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed5()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 6 && water[playertilex][playertiley] <= (seedcrops.getWaterneed6() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed6()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 7 && water[playertilex][playertiley] <= (seedcrops.getWaterneed7() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed7()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
           } else if (cropstatus[playertilex][playertiley] == 8 && water[playertilex][playertiley] <= (seedcrops.getWaterneed8() + stats.getWaterbonuslim())){
                if (water[playertilex][playertiley] < seedcrops.getWaterneed8()) {
                    stats.watercost();
                } else {
                    errorindicator = true; error = ("Water requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 9) {
                errorindicator = true; error = ("ERROR: Crop must be watered from the center");
            }
        }
    }

    public void fertilizerplants()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot unoccupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            fertilizer[playertilex][playertiley] += 1;
            if (cropstatus[playertilex][playertiley] == 1 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds1()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 2 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds2()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 3 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds3()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 4 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds4()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 5 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds5()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 6 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds6()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 7 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds7()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 8 && fertilizer[playertilex][playertiley] <= (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim())){
                if (fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds8()){
                    stats.fertilizercost();
                } else {
                    errorindicator = true; error = ("Fertilizer requirement met");
                }
            } else if (cropstatus[playertilex][playertiley] == 9) {
                errorindicator = true; error = ("ERROR: Crop must be fertilized from the center");
            }
        }
    }

    public void waterandfertstatus()
    {
        if (cropstatus[playertilex][playertiley] == 1) {
            // errorindicator = true; error = (seedcrops.getSeedname1());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed1() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed1() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds1()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds1() && water[playertilex][playertiley] < seedcrops.getWaterneed1()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds1() && water[playertilex][playertiley] >= seedcrops.getWaterneed1()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 2) {
            // errorindicator = true; error = (seedcrops.getSeedname2());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed2() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed2() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds2()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds2() && water[playertilex][playertiley] < seedcrops.getWaterneed2()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds2() && water[playertilex][playertiley] >= seedcrops.getWaterneed2()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 3) {
            // errorindicator = true; error = (seedcrops.getSeedname3());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed3() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed3() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds3()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds3() && water[playertilex][playertiley] < seedcrops.getWaterneed3()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds3() && water[playertilex][playertiley] >= seedcrops.getWaterneed3()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 4) {
            // errorindicator = true; error = (seedcrops.getSeedname4());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed4() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed4() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds4()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds4() && water[playertilex][playertiley] < seedcrops.getWaterneed4()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds4() && water[playertilex][playertiley] >= seedcrops.getWaterneed4()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 5) {
            // errorindicator = true; error = (seedcrops.getSeedname5());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed5() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed5() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds5()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds5() && water[playertilex][playertiley] < seedcrops.getWaterneed5()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds5() && water[playertilex][playertiley] >= seedcrops.getWaterneed5()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 6) {
            // errorindicator = true; error = (seedcrops.getSeedname6());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed6() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed6() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds6()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds6() && water[playertilex][playertiley] < seedcrops.getWaterneed6()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds6() && water[playertilex][playertiley] >= seedcrops.getWaterneed6()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 7) {
            // errorindicator = true; error = (seedcrops.getSeedname7());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed7() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed7() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds7()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds7() && water[playertilex][playertiley] < seedcrops.getWaterneed7()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds7() && water[playertilex][playertiley] >= seedcrops.getWaterneed7()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } else  if (cropstatus[playertilex][playertiley] == 8) {
            // errorindicator = true; error = (seedcrops.getSeedname8());
            // errorindicator = true; error = ("Fertilizer requirement: " + fertilizer[playertilex][playertiley] + "/" + (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()));
            // errorindicator = true; error = ("Water requirement:      " + water[playertilex][playertiley] + "/" + (seedcrops.getWaterneed8() + stats.getWaterbonuslim()));
            if (water[playertilex][playertiley] >= seedcrops.getWaterneed8() && fertilizer[playertilex][playertiley] < seedcrops.getFertilizerneeds8()) {
                errorindicator = true; error = ("Water requirement met");
            } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds8() && water[playertilex][playertiley] < seedcrops.getWaterneed8()) {
                errorindicator = true; error = ("Fertilizer requirement met");
            } } else if (fertilizer[playertilex][playertiley] >= seedcrops.getFertilizerneeds8() && water[playertilex][playertiley] >= seedcrops.getWaterneed8()) {
                errorindicator = true; error = ("Fertilizer requirement met");
        } 
    }

    public void harvesting()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot unoccupied");

        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 1 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime1()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname1() + " not yet ready to harvest");     
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 2 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime2()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname2() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 3 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime3()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname3() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 4 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime4()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname4() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 5 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime5()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname5() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 6 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime6()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname6() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 7 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime7()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname7() + " not yet ready to harvest");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 8 && gamepanel.getMinutes() < dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime8()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname8() + " not yet ready to harvest");

        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 1 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime1()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname1() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 2 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime2()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname2() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 3 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime3()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname3() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 4 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime4()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname4() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 5 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime5()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname5() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 6 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime6()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname6() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 7 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime7()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname7() + " has withered");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 8 && gamepanel.getMinutes() > dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime8()) {
            errorindicator = true; error = ("ERROR: " + seedcrops.getSeedname8() + " has withered");
        
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 1 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime1() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed1() + stats.getWaterbonuslim())) {
            stats.harvest1();
            harvest[0] += seedcrops.getProductyield1();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield1() + " " + seedcrops.getSeedname1() + " Harvested. " + harvest[0] + " total harvests.");     
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 2 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime2() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed2() + stats.getWaterbonuslim())) {
            stats.harvest2();
            harvest[1] += seedcrops.getProductyield2();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield2() + " " + seedcrops.getSeedname2() + " Harvested. " + harvest[1] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 3 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime3() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed3() + stats.getWaterbonuslim())) {
            stats.harvest3();
            harvest[2] += seedcrops.getProductyield3();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield3() + " " + seedcrops.getSeedname3() + " Harvested. " + harvest[2] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 4 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime4() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed4() + stats.getWaterbonuslim())) {
            stats.harvest4();
            harvest[3] += seedcrops.getProductyield4();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield4() + " " + seedcrops.getSeedname4() + " Harvested. " + harvest[3] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 5 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime5() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed5() + stats.getWaterbonuslim())) {
            stats.harvest5();
            harvest[4] += seedcrops.getProductyield5();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield5() + " " + seedcrops.getSeedname5() + " (" + seedcrops.getSeedType2() + ") Harvested. " + harvest[4] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 6 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime6() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed6() + stats.getWaterbonuslim())) {
            stats.harvest6();
            harvest[5] += seedcrops.getProductyield6();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            errorindicator = true; error = (seedcrops.getProductyield6() + " " + seedcrops.getSeedname6() + " Harvested. " + harvest[5] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 7 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime7() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed7() + stats.getWaterbonuslim())) {
            stats.harvest7();
            harvest[6] += seedcrops.getProductyield7();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            fruittreecropremove();
            errorindicator = true; error = (seedcrops.getProductyield7() + " " + seedcrops.getSeedname7() + " Harvested. " + harvest[6] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 8 && gamepanel.getMinutes() == dayplanted[playertilex][playertiley]+seedcrops.getHarvesttime8() && fertilizer[playertilex][playertiley] >= (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()) && water[playertilex][playertiley] >= (seedcrops.getWaterneed8() + stats.getWaterbonuslim())) {
            stats.harvest8();
            harvest[7] += seedcrops.getProductyield8();
            plotstatus[playertilex][playertiley] = 0;
            cropstatus[playertilex][playertiley] = 0;
            dayplanted[playertilex][playertiley] = 0;
            daycheck[playertilex][playertiley] = false;
            fertilizer[playertilex][playertiley] = 0;
            water[playertilex][playertiley] = 0;
            fruittreecropremove();
            errorindicator = true; error = (seedcrops.getProductyield8() + " " + seedcrops.getSeedname8() + " Harvested. " + harvest[7] + " total harvests.");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 9) {
            errorindicator = true; error = ("ERROR: Crop must be harvested from the center");
        } else {
            errorindicator = true; error = ("Crop has not been watered/fertilized and has withered");
        }
    }

    public void shovelling()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot already unplowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            plotstatus[playertilex][playertiley] = 0;
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 9) {
            errorindicator = true; error = ("ERROR: Crop must be shovelled in the center");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            if (cropstatus[playertilex][playertiley] == 7 || cropstatus[playertilex][playertiley] == 8){
                fruittreecropremove();
            }
            cropstatus[playertilex][playertiley] = 0;
            plotstatus[playertilex][playertiley] = 0;
            
            stats.useshovel();
        }
    }

    private void fruittreecropremove()
    {
        plotstatus[playertilex+1][playertiley] = 0; // Right
        plotstatus[playertilex-1][playertiley] = 0; // Left
        plotstatus[playertilex][playertiley+1] = 0; // Down
        plotstatus[playertilex][playertiley-1] = 0; // Up
        //corners
        plotstatus[playertilex+1][playertiley+1] = 0; //Bottom Right
        plotstatus[playertilex+1][playertiley-1] = 0; //Top Right
        plotstatus[playertilex-1][playertiley+1] = 0; //Bottom Left
        plotstatus[playertilex-1][playertiley-1] = 0; // Top Left

        cropstatus[playertilex+1][playertiley] = 0; // Right
        cropstatus[playertilex-1][playertiley] = 0; // Left
        cropstatus[playertilex][playertiley+1] = 0; // Down
        cropstatus[playertilex][playertiley-1] = 0; // Up
        //corners
        cropstatus[playertilex+1][playertiley+1] = 0; //Bottom Right
        cropstatus[playertilex+1][playertiley-1] = 0; //Top Right
        cropstatus[playertilex-1][playertiley+1] = 0; //Bottom Left
        cropstatus[playertilex-1][playertiley-1] = 0; // Top Left

    } 

    //Turnips
    public void seed1plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] == 0) {
            if (shop.getSeedamount1() > 0) {
                shop.seedsdeduct1();
                cropstatus[playertilex][playertiley] = 1;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount1() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname1() + "s in inventory");
            }
        }
    }

    //Carrots
    public void seed2plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount2() > 0) {
                shop.seedsdeduct2();
                cropstatus[playertilex][playertiley] = 2;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount2() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname2() + "s in inventory");
            }
        }
    }

    //Potatoes
    public void seed3plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount3() > 0) {
                shop.seedsdeduct3();
                cropstatus[playertilex][playertiley] = 3;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount3() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname3() + "es in inventory");
            }
        }
    }
    //Roses
    public void seed4plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount4() > 0) {
                shop.seedsdeduct4();
                cropstatus[playertilex][playertiley] = 4;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount4() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname4() + "s in inventory");
            }
        }
    }
    //turnips
    public void seed5plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount5() > 0) {
                shop.seedsdeduct5();
                cropstatus[playertilex][playertiley] = 5;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount5() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname5() + "s (" + seedcrops.getSeedType2() + ") in inventory");
            }
        }
    }
    //Sunflowers
    public void seed6plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount6() > 0) {
                shop.seedsdeduct6();
                cropstatus[playertilex][playertiley] = 6;
                if (daycheck[playertilex][playertiley] == false) {
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount6() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname6() + "s in inventory");
            }
        }
    }
    //Mangoes
    public void seed7plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (playertilex == 0 || playertilex == (stats.getCropwidth()-1) || playertiley == 0 || playertiley == stats.getCropheight()-1) {
            errorindicator = true; error = ("ERROR: Not enough space, needs 3x3 area");
        } else if (rockstatus[playertilex][playertiley] == 1 || rockstatus[playertilex+1][playertiley] == 1 ||
                   rockstatus[playertilex-1][playertiley] == 1 || rockstatus[playertilex][playertiley+1] == 1 ||
                   rockstatus[playertilex][playertiley-1] == 1 || rockstatus[playertilex+1][playertiley+1] == 1 ||
                   rockstatus[playertilex+1][playertiley-1] == 1 || rockstatus[playertilex-1][playertiley+1] == 1 ||
                   rockstatus[playertilex-1][playertiley-1] == 1 || cropstatus[playertilex][playertiley] != 0 ||   
                   cropstatus[playertilex+1][playertiley] != 0 || cropstatus[playertilex-1][playertiley] != 0 || 
                   cropstatus[playertilex][playertiley+1] != 0 || cropstatus[playertilex][playertiley-1] != 0 || 
                   cropstatus[playertilex+1][playertiley+1] != 0 || cropstatus[playertilex+1][playertiley-1] != 0 || 
                   cropstatus[playertilex-1][playertiley+1] != 0 || cropstatus[playertilex-1][playertiley-1] != 0 || 
                   rockstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Surrounding space occupied, needs 3x3 area");
        } else if (plotstatus[playertilex][playertiley] == 0 || plotstatus[playertilex+1][playertiley] == 0 ||
                   plotstatus[playertilex-1][playertiley] == 0 || plotstatus[playertilex][playertiley+1] == 0 ||
                   plotstatus[playertilex][playertiley-1] == 0 || plotstatus[playertilex+1][playertiley+1] == 0 ||
                   plotstatus[playertilex+1][playertiley-1] == 0 || plotstatus[playertilex-1][playertiley+1] == 0 ||
                   plotstatus[playertilex-1][playertiley-1] == 0) {
            errorindicator = true; error = ("ERROR: Surrounding space not plowed, needs 3x3 plowed area");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount7() > 0) {
                shop.seedsdeduct7();
                cropstatus[playertilex][playertiley] = 7; //Middle
                if (daycheck[playertilex][playertiley] == false) {
                    cropoccupied();
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount7() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname7() + "es in inventory");
            }
        }
    }
    //Apples
    public void seed8plant()
    {
        if (rockstatus[playertilex][playertiley] == 1) {
            errorindicator = true; error = ("ERROR: Rocks in the way");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 0) {
            errorindicator = true; error = ("ERROR: Plot not plowed");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Plot occupied");
        } else if (playertilex == 0 || playertilex == (stats.getCropwidth()-1) || playertiley == 0 || playertiley == stats.getCropheight()-1) {
            errorindicator = true; error = ("ERROR: Out of bounds, needs 3x3 area");
        } else if (rockstatus[playertilex][playertiley] == 1 || rockstatus[playertilex+1][playertiley] == 1 ||
                   rockstatus[playertilex-1][playertiley] == 1 || rockstatus[playertilex][playertiley+1] == 1 ||
                   rockstatus[playertilex][playertiley-1] == 1 || rockstatus[playertilex+1][playertiley+1] == 1 ||
                   rockstatus[playertilex+1][playertiley-1] == 1 || rockstatus[playertilex-1][playertiley+1] == 1 ||
                   rockstatus[playertilex-1][playertiley-1] == 1 || cropstatus[playertilex][playertiley] != 0 ||   
                   cropstatus[playertilex+1][playertiley] != 0 || cropstatus[playertilex-1][playertiley] != 0 || 
                   cropstatus[playertilex][playertiley+1] != 0 || cropstatus[playertilex][playertiley-1] != 0 || 
                   cropstatus[playertilex+1][playertiley+1] != 0 || cropstatus[playertilex+1][playertiley-1] != 0 || 
                   cropstatus[playertilex-1][playertiley+1] != 0 || cropstatus[playertilex-1][playertiley-1] != 0 || 
                   rockstatus[playertilex][playertiley] == 1 && cropstatus[playertilex][playertiley] != 0) {
            errorindicator = true; error = ("ERROR: Surrounding space occupied, needs 3x3 area");
        } else if (plotstatus[playertilex][playertiley] == 0 || plotstatus[playertilex+1][playertiley] == 0 ||
                   plotstatus[playertilex-1][playertiley] == 0 || plotstatus[playertilex][playertiley+1] == 0 ||
                   plotstatus[playertilex][playertiley-1] == 0 || plotstatus[playertilex+1][playertiley+1] == 0 ||
                   plotstatus[playertilex+1][playertiley-1] == 0 || plotstatus[playertilex-1][playertiley+1] == 0 ||
                   plotstatus[playertilex-1][playertiley-1] == 0) {
            errorindicator = true; error = ("ERROR: Surrounding space not plowed, needs 3x3 plowed area");
        } else if (rockstatus[playertilex][playertiley] == 0 && plotstatus[playertilex][playertiley] == 1) {
            if (shop.getSeedamount8() > 0) {
                shop.seedsdeduct8();
                cropstatus[playertilex][playertiley] = 8; //Middle
                 if (daycheck[playertilex][playertiley] == false) {
                    cropoccupied();
                    dayplanted[playertilex][playertiley] = gamepanel.getMinutes();
                    daycheck[playertilex][playertiley] = true;
                }
            } else if (shop.getSeedamount8() <= 0) {
                errorindicator = true; error = ("No " + seedcrops.getSeedname8() + "s in inventory");
            }
        }
    }

    private void cropoccupied()
    {   
        cropstatus[playertilex+1][playertiley] = 9; // Right
        cropstatus[playertilex-1][playertiley] = 9; // Left
        cropstatus[playertilex][playertiley+1] = 9; // Down
        cropstatus[playertilex][playertiley-1] = 9; // Up
        //corners
        cropstatus[playertilex+1][playertiley+1] = 9; //Bottom Right
        cropstatus[playertilex+1][playertiley-1] = 9; //Top Right
        cropstatus[playertilex-1][playertiley+1] = 9; //Bottom Left
        cropstatus[playertilex-1][playertiley-1] = 9; // Top Left
    }

    public void mainplot() {
        gamepanel.croppers.rocks();
        for (i = 0; i < stats.getCropheight(); i++) {
            for (j = 0; j < stats.getCropwidth(); j++) {
                //1 - Turnips Veg
                if (plotstatus[j][i] == 0 && rockstatus[j][i] == 1) {
                    baselayer[j][i] = 5; 
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 1){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime1()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime1()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime1())) {
                        baselayer[j][i] = 15; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime1() && fertilizer[j][i] < (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed1() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 134;//Death to no fertilizer
                        fertilizer[j][i] = 0; 
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime1() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed1() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 134;//Death to no water
                        fertilizer[j][i] = 0; 
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime1() && fertilizer[j][i] < (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed1() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 134;//Death to no water and fertilizer
                        fertilizer[j][i] = 0; 
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime1() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds1() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed1() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 20; // Ready to harvest
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime1()) {
                        baselayer[j][i] = 134; //Passed harvest date
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //2 - Carrots
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 2){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime2()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime2()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime2())) {
                        baselayer[j][i] = 11; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime2() && fertilizer[j][i] < (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed2() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 21; //Death to no fertilizer
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime2() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed2() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 21; //Death to no water
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime2() && fertilizer[j][i] < (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed2() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 21; //Death to no fertilizer
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime2() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds2() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed2() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 16; // Ready to harvest
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime2()) {
                        baselayer[j][i] = 21; //Passed harvest date
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //3 - Potatoes
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 3){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime3()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime3()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime3())) {
                        baselayer[j][i] = 189; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime3() && fertilizer[j][i] < (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed3() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 191;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime3() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed3() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 191;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime3() && fertilizer[j][i] < (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed3() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 191;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime3() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds3() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed3() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 190;
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime3()) {
                        baselayer[j][i] = 191;
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //4 - Roses
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 4){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime4()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime4()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime4())) {
                        baselayer[j][i] = 12; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime4() && fertilizer[j][i] < (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed4() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 22;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime4() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed4() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 22;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime4() && fertilizer[j][i] < (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed4() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 22;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime4() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds4() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed4() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 17;
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime4()) {
                        baselayer[j][i] = 22;
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //5 - Turnips Flower
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 5){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime5()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime5()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime5())) {
                        baselayer[j][i] = 14; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime5() && fertilizer[j][i] < (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed5() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 24;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime5() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed5() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 24;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime5() && fertilizer[j][i] < (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed5() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 24;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime5() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds5() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed5() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 19;
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime5()) {
                        baselayer[j][i] = 24;
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //6 - Sunflowers
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 6){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime6()/2))) {
                        baselayer[j][i] = 133; //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime6()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime6())) {
                        baselayer[j][i] = 13; // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime6() && fertilizer[j][i] < (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed6() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 23;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime6() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed6() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 23;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime6() && fertilizer[j][i] < (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed6() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 23;
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime6() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds6() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed6() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 18;
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime6()) {
                        baselayer[j][i] = 23;
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //7 - Mango
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 7){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime7()/2))) {
                        baselayer[j][i] = 133; // Middle
                        baselayer[j+1][i] = 133; // Right
                        baselayer[j-1][i] = 133; // Left
                        baselayer[j][i+1] = 133; // Down
                        baselayer[j][i-1] = 133; // Up
                        //corners
                        baselayer[j+1][i+1] = 133; //Bottom Right
                        baselayer[j+1][i-1] = 133; //Top Right
                        baselayer[j-1][i+1] = 133; //Bottom Left
                        baselayer[j-1][i-1] = 133; // Top Left //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime7()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime7())) {
                        baselayer[j][i] = 175; // Middle
                        baselayer[j+1][i] = 176; // Right
                        baselayer[j-1][i] = 174; // Left
                        baselayer[j][i+1] = 178; // Down
                        baselayer[j][i-1] = 172; // Up
                        //corners
                        baselayer[j+1][i+1] = 179; //Bottom Right
                        baselayer[j+1][i-1] = 173; //Top Right
                        baselayer[j-1][i+1] = 177; //Bottom Left
                        baselayer[j-1][i-1] = 171; // Top Left // Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime7() && fertilizer[j][i] < (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed7() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime7() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed7() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime7() && fertilizer[j][i] < (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed7() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime7() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds7() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed7() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 157; //Middle
                        baselayer[j+1][i] = 158; // Right
                        baselayer[j-1][i] = 156; // Left
                        baselayer[j][i+1] = 160; // Down
                        baselayer[j][i-1] = 154; // Up
                        //corners
                        baselayer[j+1][i+1] = 161; //Bottom Right
                        baselayer[j+1][i-1] = 155; //Top Right
                        baselayer[j-1][i+1] = 159; //Bottom Left
                        baselayer[j-1][i-1] = 153; // Top Left
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime7()) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                //8 - Apple
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 8){
                    if (gamepanel.getMinutes() >= dayplanted[j][i] && gamepanel.getMinutes() < (dayplanted[j][i]+(seedcrops.getHarvesttime8()/2))) {
                        baselayer[j][i] = 133; // Middle
                        baselayer[j+1][i] = 133; // Right
                        baselayer[j-1][i] = 133; // Left
                        baselayer[j][i+1] = 133; // Down
                        baselayer[j][i-1] = 133; // Up
                        //corners
                        baselayer[j+1][i+1] = 133; //Bottom Right
                        baselayer[j+1][i-1] = 133; //Top Right
                        baselayer[j-1][i+1] = 133; //Bottom Left
                        baselayer[j-1][i-1] = 133; // Top Left //Seeds //Seeds
                    } else if (gamepanel.getMinutes() >= (dayplanted[j][i]+(seedcrops.getHarvesttime8()/2)) && gamepanel.getMinutes() < (dayplanted[j][i]+seedcrops.getHarvesttime8())) {
                        baselayer[j][i] = 175; // Middle
                        baselayer[j+1][i] = 176; // Right
                        baselayer[j-1][i] = 174; // Left
                        baselayer[j][i+1] = 178; // Down
                        baselayer[j][i-1] = 172; // Up
                        //corners
                        baselayer[j+1][i+1] = 179; //Bottom Right
                        baselayer[j+1][i-1] = 173; //Top Right
                        baselayer[j-1][i+1] = 177; //Bottom Left
                        baselayer[j-1][i-1] = 171; // Top Left // Halfwaystate// Halfwaystate
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime8() && fertilizer[j][i] < (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed8() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime8() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed8() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime8() && fertilizer[j][i] < (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()) && water[j][i] < (seedcrops.getWaterneed8() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        fertilizer[j][i] = 0;
                        water[j][i] = 0;
                    } else if (gamepanel.getMinutes() == dayplanted[j][i]+seedcrops.getHarvesttime8() && fertilizer[j][i] >= (seedcrops.getFertilizerneeds8() + stats.getFertbonuslim()) && water[j][i] >= (seedcrops.getWaterneed8() + stats.getWaterbonuslim())) {
                        baselayer[j][i] = 166; //Middle
                        baselayer[j+1][i] = 167; // Right
                        baselayer[j-1][i] = 165; // Left
                        baselayer[j][i+1] = 169; // Down
                        baselayer[j][i-1] = 163; // Up
                        //corners
                        baselayer[j+1][i+1] = 170; //Bottom Right
                        baselayer[j+1][i-1] = 164; //Top Right
                        baselayer[j-1][i+1] = 168; //Bottom Left
                        baselayer[j-1][i-1] = 162; // Top Left;
                    } else if (gamepanel.getMinutes() > dayplanted[j][i]+seedcrops.getHarvesttime8()) {
                        baselayer[j][i] = 183; // Middle
                        baselayer[j+1][i] = 184; // Right
                        baselayer[j-1][i] = 182; // Left
                        baselayer[j][i+1] = 186; // Down
                        baselayer[j][i-1] = 181; // Up
                        //corners
                        baselayer[j+1][i+1] = 187; //Bottom Right
                        baselayer[j+1][i-1] = 182; //Top Right
                        baselayer[j-1][i+1] = 185; //Bottom Left
                        baselayer[j-1][i-1] = 180; // Top Left
                        dayplanted[j][i] = 0;
                        daycheck[j][i] = false;
                    }
                } else if (plotstatus[j][i] == 1 && rockstatus[j][i] == 0 && cropstatus[j][i] == 0){
                    baselayer[j][i] = 4;
                } else if (plotstatus[j][i] == 0 && rockstatus[j][i] == 0 && cropstatus[j][i] == 0){
                    baselayer[j][i] = 3;
                }
            }
        }
    }


    public int[][] getBaselayer() {
        return baselayer;
    }

    public void setBaselayer(int[][] baselayer) {
        this.baselayer = baselayer;
    }

    public int getPlayertilex() {
        return playertilex;
    }

    public void setPlayertilex(int playertilex) {
        this.playertilex = playertilex;
    }

    public int getPlayertiley() {
        return playertiley;
    }

    public void setPlayertiley(int playertiley) {
        this.playertiley = playertiley;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isErrorindicator() {
        return errorindicator;
    }

    public void setErrorindicator(boolean errorindicator) {
        this.errorindicator = errorindicator;
    }

    
    
}
