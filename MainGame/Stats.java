package MainGame;

interface interfacestats
{
    void notifycheck();
    void coindeduct1();
    void coindeduct2();
    void coindeduct3();
    void coindeduct4();
    void coindeduct5();
    void coindeduct6();
    void coindeduct7();
    void coindeduct8();
    void coindeductrocks(); 
    void harvest1();
    void harvest2();
    void harvest3();
    void harvest4();
    void harvest5();
    void harvest6();
    void harvest7();
    void harvest8();
    void watercost();
    void fertilizercost();
    void explevel();
    void plowcostexp();    
    void useshovel();
    void expincreaserocks();
}

public class Stats {

    private Seedcrops seedcrops;
    private Equipment tools;
    private String playername;

    private int objectcoins;
    private int farmerlevel;
    private double exp;
    private String farmerstatus;
    private double[] totalharvest;

    private int cropheight;
    private int cropwidth;
   
    //Landmarks
    private String landmark1;
    private String landmark2;
    private String landmark3;
    private String landmark4;

    //Farmer Type
    private String farmertype1;
    private String farmertype2;
    private String farmertype3;
    private String farmertype4;

    private int levelreq1;
    private int levelreq2;
    private int levelreq3;
    private int levelreq4;
    
    private int bonusearning;
    private int bonusearning2;
    private int bonusearning3;
    private int bonusearning4;

    private int seedcostreduct;
    private int seedcostreduct2;
    private int seedcostreduct3;
    private int seedcostreduct4;

    private int waterbonuslim;
    private int waterbonuslim2;
    private int waterbonuslim3;
    private int waterbonuslim4;

    private int fertbonuslim;
    private int fertbonuslim2;
    private int fertbonuslim3;
    private int fertbonuslim4;

    private int registrationfee1;
    private int registrationfee2;
    private int registrationfee3;
    private int registrationfee4;

    //Timeskip
    private double timeskip;
    private int day = 1;

    private String notify;
    private boolean notifycheck;
    

    public Stats (Seedcrops seedcrops, Equipment tools, String playername, int cropwidth, int cropheight, int objectcoins, 
                        int farmerlevel, String landmark1, String landmark2, String landmark3, String landmark4, double timeskip,
                        String farmertype1, String farmertype2, String farmertype3, String farmertype4, 
                        int levelreq1, int levelreq2, int levelreq3, int levelreq4,
                        int bonusearning, int bonusearning2, int bonusearning3, int bonusearning4, 
                        int seedcostreduct, int seedcostreduct2, int seedcostreduct3, int seedcostreduct4,
                        int waterbonuslim, int waterbonuslim2, int waterbonuslim3, int waterbonuslim4, 
                        int fertbonuslim, int fertbonuslim2, int fertbonuslim3, int fertbonuslim4,
                        int registrationfee1, int registrationfee2, int registrationfee3, int registrationfee4) 
    {
        this.seedcrops = seedcrops;
        this.tools = tools;
        this.playername = playername;
        this.cropwidth = cropwidth;
        this.cropheight = cropheight;
        this.objectcoins = objectcoins;
        this.farmerlevel = farmerlevel;
        this.landmark1 = landmark1;
        this.landmark2 = landmark2;
        this.landmark3 = landmark3;
        this.landmark4 = landmark4;
        this.farmertype1 = farmertype1;
        this.farmertype2 = farmertype2;
        this.farmertype3 = farmertype3;
        this.farmertype4 = farmertype4;
        this.levelreq1 = levelreq1;
        this.levelreq2 = levelreq2;
        this.levelreq3 = levelreq3;
        this.levelreq4 = levelreq4;
        this.bonusearning = bonusearning;
        this.bonusearning2 = bonusearning2;
        this.bonusearning3 = bonusearning3;
        this.bonusearning4 = bonusearning4;
        this.seedcostreduct = seedcostreduct;
        this.seedcostreduct2 = seedcostreduct2;
        this.seedcostreduct3 = seedcostreduct3;
        this.seedcostreduct4 = seedcostreduct4;
        this.waterbonuslim = waterbonuslim;
        this.waterbonuslim2 = waterbonuslim2;
        this.waterbonuslim3 = waterbonuslim3;
        this.waterbonuslim4 = waterbonuslim4;
        this.fertbonuslim = fertbonuslim;
        this.fertbonuslim2 = fertbonuslim2;
        this.fertbonuslim3 = fertbonuslim3;
        this.fertbonuslim4 = fertbonuslim4;
        this.registrationfee1 = registrationfee1;
        this.registrationfee2 = registrationfee2;
        this.registrationfee3 = registrationfee3;
        this.registrationfee4 = registrationfee4;
        this.timeskip = timeskip;
        this.farmerstatus = getFarmertype1();
        this.totalharvest = new double[seedcrops.getSeedtotal().size()];
        for (int i = 0; i < seedcrops.getSeedtotal().size(); i++) {
            totalharvest[i] = 0;
        }
    }

    public void notifyreset() {
        notify = "";
        notifycheck = false;
    }

    // public void farmerstat(char yesorno)
    // {
    //     if (yesorno == 'y' && getFarmerlevel() >= getLevelreq2() && farmerstatus == getFarmertype1() && getObjectcoins() >= getRegistrationfee2()){
    //         farmerstatus = getFarmertype2();
    //         objectcoins -= getRegistrationfee2();
    //         bonusearning = getBonusearning2();
    //         seedcostreduct = getSeedcostreduct2();
    //         waterbonuslim = getWaterbonuslim2();
    //         fertbonuslim = getFertbonuslim2();
    //         notifycheck = true; notify = ("==============================");
    //         notifycheck = true; notify = ("Leveled up to rank: " + getFarmerstatus());
    //         notifycheck = true; notify = ("==============================");
    //     } else if (yesorno == 'y' && getFarmerlevel() < getLevelreq2() && farmerstatus == getFarmertype1()){
    //        notifycheck = true; notify = ("Sorry, not enough levels to unlock " + getFarmertype2());
    //     } else if (yesorno == 'y' && getObjectcoins() < getRegistrationfee2() && farmerstatus == getFarmertype1()){
    //         notifycheck = true; notify = ("Sorry, not enough coins to unlock " + getFarmertype2());
    //     } else if (yesorno == 'y' && getFarmerlevel() >= getLevelreq3() && farmerstatus == getFarmertype2() && getObjectcoins() >= getRegistrationfee3()){
    //         farmerstatus = getFarmertype3();
    //         objectcoins -= getRegistrationfee3();
    //         bonusearning = getBonusearning3();
    //         seedcostreduct = getSeedcostreduct3();
    //         waterbonuslim = getWaterbonuslim3();
    //         fertbonuslim = getFertbonuslim3();
    //         notifycheck = true; notify = ("==============================");
    //         notifycheck = true; notify = ("Leveled up to rank: " + getFarmerstatus());
    //         notifycheck = true; notify = ("==============================");
    //     } else if (yesorno == 'y' && getFarmerlevel() < getLevelreq3() && farmerstatus == getFarmertype2()){
    //         notifycheck = true; notify = ("Sorry, not enough levels to unlock " + getFarmertype3());
    //     } else if (yesorno == 'y' && getObjectcoins() < getRegistrationfee3() && farmerstatus == getFarmertype2()){
    //          notifycheck = true; notify = ("Sorry, not enough coins to unlock " + getFarmertype3());
    //     } else if (yesorno == 'y' && getFarmerlevel() >= getLevelreq4() && farmerstatus == getFarmertype3() && getObjectcoins() >= getRegistrationfee4()){
    //         farmerstatus = getFarmertype4();
    //         objectcoins -= getRegistrationfee3();
    //         bonusearning = getBonusearning3();
    //         seedcostreduct = getSeedcostreduct3();
    //         waterbonuslim = getWaterbonuslim3();
    //         fertbonuslim = getFertbonuslim3();
    //         notifycheck = true; notify = ("==============================");
    //         notifycheck = true; notify = ("Leveled up to rank: " + getFarmerstatus());
    //         notifycheck = true; notify = ("==============================");
    //     } else if (yesorno == 'y' && getFarmerlevel() < getLevelreq4() && farmerstatus == getFarmertype3()){
    //         notifycheck = true; notify = ("Sorry, not enough levels to unlock " + getFarmertype4());
    //      } else if (yesorno == 'y' && getObjectcoins() < getRegistrationfee4() && farmerstatus == getFarmertype3()){
    //          notifycheck = true; notify = ("Sorry, not enough coins to unlock " + getFarmertype4());
    //     } else if (yesorno == 'n') {
    //         System.out.print("\n");
    //     } 
    // }

    public void explevel()
    {
        if (exp >= 100.0){
            farmerlevel += 1;
            exp = 0.0;
        }
    }

    public void coindeduct1()
    {
        if (objectcoins >= seedcrops.getSeedcost1()) {
        objectcoins -= (seedcrops.getSeedcost1() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname1() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost1() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct2()
    {
        if (objectcoins >= seedcrops.getSeedcost2()) {
        objectcoins -= (seedcrops.getSeedcost2() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname2() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost2() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct3()
    {
        if (objectcoins >= seedcrops.getSeedcost3()) {
        objectcoins -= (seedcrops.getSeedcost3() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname3() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost3() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct4()
    {
        if (objectcoins >= seedcrops.getSeedcost4()) {
        objectcoins -= (seedcrops.getSeedcost4() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname4() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost4() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct5()
    {
        if (objectcoins >= seedcrops.getSeedcost5()) {
        objectcoins -= (seedcrops.getSeedcost5() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname5() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost5() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct6()
    {
        if (objectcoins >= seedcrops.getSeedcost6()) {
        objectcoins -= (seedcrops.getSeedcost6() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname6() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost6() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct7()
    {
        if (objectcoins >= seedcrops.getSeedcost7()) {
        objectcoins -= (seedcrops.getSeedcost7() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname7() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost7() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeduct8()
    {
        if (objectcoins >= seedcrops.getSeedcost8()) {
        objectcoins -= (seedcrops.getSeedcost8() - getSeedcostreduct());
        notifycheck = true; notify = ("Bought "+ seedcrops.getSeedname8() + ", Coins: " + getObjectcoins() + " (-" + seedcrops.getSeedcost8() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins for purchase");
        }
    }

    public void coindeductrocks()
    {
        if (objectcoins >= tools.getTool4Cost()) {
            objectcoins -= tools.getTool4Cost();
            notifycheck = true; notify = ("Coins: " + getObjectcoins() + " (-" + tools.getTool4Cost() + ")");
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins to use " + tools.getTool4());
        }
    }

    public void expincreaserocks()
    {   
        if (objectcoins >= tools.getTool4Cost()){
            exp += tools.getTool4XP();
        }
    }

    public void useshovel()
    {
        if (objectcoins >= tools.getTool5Cost()) {
            objectcoins -= tools.getTool5Cost();
            notifycheck = true; notify = ("Coins: " + getObjectcoins() + " (-" + tools.getTool5Cost() + ")");
            exp += tools.getTool5XP();
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins to use " + tools.getTool5());
        }
    }

    public void harvest1()
    {
        exp += seedcrops.getExpyield1();
        objectcoins += ((seedcrops.getBasesellingprice1() + getBonusearning()) * seedcrops.getProductyield1());
        totalharvest[0] += (((seedcrops.getBasesellingprice1() + getBonusearning()) * seedcrops.getProductyield1()) + (((seedcrops.getBasesellingprice1() + getBonusearning()) * seedcrops.getProductyield1()) * 0.2 * seedcrops.getFertilizerneeds1()) +  (((seedcrops.getBasesellingprice1() + getBonusearning()) * seedcrops.getProductyield1()) * 0.5 * (seedcrops.getWaterneed1()) - 1));
    }

    public void harvest2()
    {
        exp += seedcrops.getExpyield2();
        objectcoins += ((seedcrops.getBasesellingprice2() + getBonusearning()) * seedcrops.getProductyield2());
        totalharvest[1] += (((seedcrops.getBasesellingprice2() + getBonusearning()) * seedcrops.getProductyield2()) + (((seedcrops.getBasesellingprice2() + getBonusearning()) * seedcrops.getProductyield2()) * 0.2 * seedcrops.getFertilizerneeds2()) +  (((seedcrops.getBasesellingprice2() + getBonusearning()) * seedcrops.getProductyield2()) * 0.5 * (seedcrops.getWaterneed2()) - 1));
    }

    public void harvest3()
    {
        exp += seedcrops.getExpyield3();
        objectcoins += ((seedcrops.getBasesellingprice3() + getBonusearning()) * seedcrops.getProductyield3());
        totalharvest[2] += (((seedcrops.getBasesellingprice3() + getBonusearning()) * seedcrops.getProductyield3()) + (((seedcrops.getBasesellingprice3() + getBonusearning()) * seedcrops.getProductyield3()) * 0.2 * seedcrops.getFertilizerneeds3()) +  (((seedcrops.getBasesellingprice3() + getBonusearning()) * seedcrops.getProductyield3()) * 0.5 * (seedcrops.getWaterneed3()) - 1));
    }

    public void harvest4()
    {
        exp += seedcrops.getExpyield4();
        objectcoins += ((seedcrops.getBasesellingprice4() + getBonusearning())*seedcrops.getProductyield4());
        totalharvest[3] += (((seedcrops.getBasesellingprice4() + getBonusearning()) * seedcrops.getProductyield4()) + (((seedcrops.getBasesellingprice4() + getBonusearning()) * seedcrops.getProductyield4()) * 0.2 * seedcrops.getFertilizerneeds4()) +  (((seedcrops.getBasesellingprice4() + getBonusearning()) * seedcrops.getProductyield4()) * 0.5 * (seedcrops.getWaterneed4()) - 1));
    }

    public void harvest5()
    {
        exp += seedcrops.getExpyield5();
        objectcoins += ((seedcrops.getBasesellingprice5() + getBonusearning())*seedcrops.getProductyield5());
        totalharvest[4] += (((seedcrops.getBasesellingprice5() + getBonusearning()) * seedcrops.getProductyield5()) + (((seedcrops.getBasesellingprice5() + getBonusearning()) * seedcrops.getProductyield5()) * 0.2 * seedcrops.getFertilizerneeds5()) +  (((seedcrops.getBasesellingprice5() + getBonusearning()) * seedcrops.getProductyield5()) * 0.5 * (seedcrops.getWaterneed5()) - 1));
    }

    public void harvest6()
    {
        exp += seedcrops.getExpyield6();
        objectcoins += ((seedcrops.getBasesellingprice6() + getBonusearning())*seedcrops.getProductyield6());
        totalharvest[5] += (((seedcrops.getBasesellingprice6() + getBonusearning()) * seedcrops.getProductyield6()) + (((seedcrops.getBasesellingprice6() + getBonusearning()) * seedcrops.getProductyield6()) * 0.2 * seedcrops.getFertilizerneeds6()) +  (((seedcrops.getBasesellingprice6() + getBonusearning()) * seedcrops.getProductyield6()) * 0.5 * (seedcrops.getWaterneed6()) - 1));
    }

    public void harvest7()
    {
        exp += seedcrops.getExpyield7();
        objectcoins += ((seedcrops.getBasesellingprice7() + getBonusearning())*seedcrops.getProductyield7());
        totalharvest[6] += (((seedcrops.getBasesellingprice7() + getBonusearning()) * seedcrops.getProductyield7()) + (((seedcrops.getBasesellingprice7() + getBonusearning()) * seedcrops.getProductyield7()) * 0.2 * seedcrops.getFertilizerneeds7()) +  (((seedcrops.getBasesellingprice7() + getBonusearning()) * seedcrops.getProductyield7()) * 0.5 * (seedcrops.getWaterneed7()) - 1));
    }

    public void harvest8()
    {
        exp += seedcrops.getExpyield8();
        objectcoins += ((seedcrops.getBasesellingprice8() + getBonusearning())*seedcrops.getProductyield8());
        totalharvest[7] += (((seedcrops.getBasesellingprice8() + getBonusearning()) * seedcrops.getProductyield8()) + (((seedcrops.getBasesellingprice8() + getBonusearning()) * seedcrops.getProductyield8()) * 0.2 * seedcrops.getFertilizerneeds8()) +  (((seedcrops.getBasesellingprice8() + getBonusearning()) * seedcrops.getProductyield8()) * 0.5 * (seedcrops.getWaterneed8()) - 1));
    }

    public void watercost()
    {
        if (objectcoins >= tools.getTool2Cost()) {
            objectcoins -= tools.getTool2Cost();
            notifycheck = true; notify = ("Coins: " + getObjectcoins() + " (-" + tools.getTool2Cost() + ")");
            exp += tools.getTool2XP();
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins to use " + tools.getTool2());
        }
    }

    public void fertilizercost()
    {
        if (objectcoins >= tools.getTool3Cost()) {
            objectcoins -= tools.getTool3Cost();
            notifycheck = true; notify = ("Coins: " + getObjectcoins() + " (-" + tools.getTool3Cost() + ")");
            exp += tools.getTool3XP();
        } else {
            notifycheck = true; notify = ("Sorry. Not enough coins to use " + tools.getTool3());
        }
    }

    public void plowcostexp()
    {
        objectcoins -= tools.getTool1Cost();
        notifycheck = true; notify = ("Coins: " + getObjectcoins() + " (-" + tools.getTool1Cost() + ")");
        exp += tools.getTool1XP();
    }

    public Seedcrops getSeedcrops() {
        return seedcrops;
    }

    public void setSeedcrops(Seedcrops seedcrops) {
        this.seedcrops = seedcrops;
    }

    public Equipment getTools() {
        return tools;
    }

    public void setTools(Equipment tools) {
        this.tools = tools;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getObjectcoins() {
        return objectcoins;
    }

    public void setObjectcoins(int objectcoins) {
        this.objectcoins = objectcoins;
    }

    public int getFarmerlevel() {
        return farmerlevel;
    }

    public void setFarmerlevel(int farmerlevel) {
        this.farmerlevel = farmerlevel;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public String getFarmerstatus() {
        return farmerstatus;
    }

    public void setFarmerstatus(String farmerstatus) {
        this.farmerstatus = farmerstatus;
    }

    public double[] getTotalharvest() {
        return totalharvest;
    }

    public void setTotalharvest(double[] totalharvest) {
        this.totalharvest = totalharvest;
    }

    public String getLandmark1() {
        return landmark1;
    }

    public void setLandmark1(String landmark1) {
        this.landmark1 = landmark1;
    }

    public String getLandmark2() {
        return landmark2;
    }

    public void setLandmark2(String landmark2) {
        this.landmark2 = landmark2;
    }

    public String getLandmark3() {
        return landmark3;
    }

    public void setLandmark3(String landmark3) {
        this.landmark3 = landmark3;
    }

    public String getLandmark4() {
        return landmark4;
    }

    public void setLandmark4(String landmark4) {
        this.landmark4 = landmark4;
    }

    public String getFarmertype1() {
        return farmertype1;
    }

    public void setFarmertype1(String farmertype1) {
        this.farmertype1 = farmertype1;
    }

    public String getFarmertype2() {
        return farmertype2;
    }

    public void setFarmertype2(String farmertype2) {
        this.farmertype2 = farmertype2;
    }

    public String getFarmertype3() {
        return farmertype3;
    }

    public void setFarmertype3(String farmertype3) {
        this.farmertype3 = farmertype3;
    }

    public String getFarmertype4() {
        return farmertype4;
    }

    public void setFarmertype4(String farmertype4) {
        this.farmertype4 = farmertype4;
    }

    public int getLevelreq1() {
        return levelreq1;
    }

    public void setLevelreq1(int levelreq1) {
        this.levelreq1 = levelreq1;
    }

    public int getLevelreq2() {
        return levelreq2;
    }

    public void setLevelreq2(int levelreq2) {
        this.levelreq2 = levelreq2;
    }

    public int getLevelreq3() {
        return levelreq3;
    }

    public void setLevelreq3(int levelreq3) {
        this.levelreq3 = levelreq3;
    }

    public int getLevelreq4() {
        return levelreq4;
    }

    public void setLevelreq4(int levelreq4) {
        this.levelreq4 = levelreq4;
    }

    public int getBonusearning() {
        return bonusearning;
    }

    public void setBonusearning(int bonusearning) {
        this.bonusearning = bonusearning;
    }

    public int getBonusearning2() {
        return bonusearning2;
    }

    public void setBonusearning2(int bonusearning2) {
        this.bonusearning2 = bonusearning2;
    }

    public int getBonusearning3() {
        return bonusearning3;
    }

    public void setBonusearning3(int bonusearning3) {
        this.bonusearning3 = bonusearning3;
    }

    public int getBonusearning4() {
        return bonusearning4;
    }

    public void setBonusearning4(int bonusearning4) {
        this.bonusearning4 = bonusearning4;
    }

    public int getSeedcostreduct() {
        return seedcostreduct;
    }

    public void setSeedcostreduct(int seedcostreduct) {
        this.seedcostreduct = seedcostreduct;
    }

    public int getSeedcostreduct2() {
        return seedcostreduct2;
    }

    public void setSeedcostreduct2(int seedcostreduct2) {
        this.seedcostreduct2 = seedcostreduct2;
    }

    public int getSeedcostreduct3() {
        return seedcostreduct3;
    }

    public void setSeedcostreduct3(int seedcostreduct3) {
        this.seedcostreduct3 = seedcostreduct3;
    }

    public int getSeedcostreduct4() {
        return seedcostreduct4;
    }

    public void setSeedcostreduct4(int seedcostreduct4) {
        this.seedcostreduct4 = seedcostreduct4;
    }

    public int getWaterbonuslim() {
        return waterbonuslim;
    }

    public void setWaterbonuslim(int waterbonuslim) {
        this.waterbonuslim = waterbonuslim;
    }

    public int getWaterbonuslim2() {
        return waterbonuslim2;
    }

    public void setWaterbonuslim2(int waterbonuslim2) {
        this.waterbonuslim2 = waterbonuslim2;
    }

    public int getWaterbonuslim3() {
        return waterbonuslim3;
    }

    public void setWaterbonuslim3(int waterbonuslim3) {
        this.waterbonuslim3 = waterbonuslim3;
    }

    public int getWaterbonuslim4() {
        return waterbonuslim4;
    }

    public void setWaterbonuslim4(int waterbonuslim4) {
        this.waterbonuslim4 = waterbonuslim4;
    }

    public int getFertbonuslim() {
        return fertbonuslim;
    }

    public void setFertbonuslim(int fertbonuslim) {
        this.fertbonuslim = fertbonuslim;
    }

    public int getFertbonuslim2() {
        return fertbonuslim2;
    }

    public void setFertbonuslim2(int fertbonuslim2) {
        this.fertbonuslim2 = fertbonuslim2;
    }

    public int getFertbonuslim3() {
        return fertbonuslim3;
    }

    public void setFertbonuslim3(int fertbonuslim3) {
        this.fertbonuslim3 = fertbonuslim3;
    }

    public int getFertbonuslim4() {
        return fertbonuslim4;
    }

    public void setFertbonuslim4(int fertbonuslim4) {
        this.fertbonuslim4 = fertbonuslim4;
    }

    public int getRegistrationfee1() {
        return registrationfee1;
    }

    public void setRegistrationfee1(int registrationfee1) {
        this.registrationfee1 = registrationfee1;
    }

    public int getRegistrationfee2() {
        return registrationfee2;
    }

    public void setRegistrationfee2(int registrationfee2) {
        this.registrationfee2 = registrationfee2;
    }

    public int getRegistrationfee3() {
        return registrationfee3;
    }

    public void setRegistrationfee3(int registrationfee3) {
        this.registrationfee3 = registrationfee3;
    }

    public int getRegistrationfee4() {
        return registrationfee4;
    }

    public void setRegistrationfee4(int registrationfee4) {
        this.registrationfee4 = registrationfee4;
    }

    public double getTimeskip() {
        return timeskip;
    }

    public void setTimeskip(double timeskip) {
        this.timeskip = timeskip;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCropheight() {
        return cropheight;
    }

    public void setCropheight(int cropheight) {
        this.cropheight = cropheight;
    }

    public int getCropwidth() {
        return cropwidth;
    }

    public void setCropwidth(int cropwidth) {
        this.cropwidth = cropwidth;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public boolean isNotifycheck() {
        return notifycheck;
    }

    public void setNotifycheck(boolean notifycheck) {
        this.notifycheck = notifycheck;
    }

    
}
