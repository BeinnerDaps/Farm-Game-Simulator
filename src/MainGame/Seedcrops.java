package MainGame;

import java.util.Random;

import MainGame.Tiles.Managetiles;

import java.util.ArrayList;

public class Seedcrops {

    private String seedname1;
    private String seedname2;
    private String seedname3;
    private String seedname4;
    private String seedname5;
    private String seedname6;
    private String seedname7;
    private String seedname8;

    private String seedType1;
    private String seedType2;
    private String seedType3;

    private int harvesttime1;
    private int harvesttime2;
    private int harvesttime3;
    private int harvesttime4;
    private int harvesttime5;
    private int harvesttime6;
    private int harvesttime7;
    private int harvesttime8;
    
    private int waterneed1;
    private int waterneed2;
    private int waterneed3;
    private int waterneed4;
    private int waterneed5;
    private int waterneed6;
    private int waterneed7;
    private int waterneed8;
    
    private int fertilizerneeds1;
    private int fertilizerneeds2;
    private int fertilizerneeds3;
    private int fertilizerneeds4;
    private int fertilizerneeds5;
    private int fertilizerneeds6;
    private int fertilizerneeds7;
    private int fertilizerneeds8;

    private int productmin1;
    private int productmax1;
    private int productyield1;

    private int productmin2;
    private int productmax2;
    private int productyield2;

    private int productmin3;
    private int productmax3;
    private int productyield3;

    private int productyield4;
    private int productyield5;
    private int productyield6;

    private int productmin7;
    private int productmax7;
    private int productyield7;

    private int productmin8;
    private int productmax8;
    private int productyield8;

    private int seedcost1;
    private int seedcost2;
    private int seedcost3;
    private int seedcost4;
    private int seedcost5;
    private int seedcost6;
    private int seedcost7;
    private int seedcost8;

    private int basesellingprice1;
    private int basesellingprice2;
    private int basesellingprice3;
    private int basesellingprice4;
    private int basesellingprice5;
    private int basesellingprice6;
    private int basesellingprice7;
    private int basesellingprice8;

    private int expyield1;
    private double expyield2;
    private double expyield3;
    private double expyield4;
    private int expyield5;
    private double expyield6;
    private int expyield7;
    private int expyield8;

    private Random random = new Random();
    private ArrayList<String> seedtotal = new ArrayList<String>();

    public Seedcrops (String seedType1, String seedType2, String seedType3,
                  String seedname1, int harvesttime1, int waterneed1, int fertilizerneeds1, int seedcost1, int basesellingprice1, int expyield1, int productmin1, int productmax1,
                  String seedname2, int harvesttime2, int waterneed2, int fertilizerneeds2, int seedcost2, int basesellingprice2, double expyield2, int productmin2, int productmax2,
                  String seedname3, int harvesttime3, int waterneed3, int fertilizerneeds3, int seedcost3, int basesellingprice3, double expyield3, int productmin3, int productmax3,
                  String seedname4, int harvesttime4, int waterneed4, int fertilizerneeds4, int seedcost4, int basesellingprice4, double expyield4, int productyield4,
                  String seedname5, int harvesttime5, int waterneed5, int fertilizerneeds5, int seedcost5, int basesellingprice5, int expyield5, int productyield5,
                  String seedname6, int harvesttime6, int waterneed6, int fertilizerneeds6, int seedcost6, int basesellingprice6, double expyield6, int productyield6,
                  String seedname7, int harvesttime7, int waterneed7, int fertilizerneeds7, int seedcost7, int basesellingprice7, int expyield7, int productmin7, int productmax7,
                  String seedname8, int harvesttime8, int waterneed8, int fertilizerneeds8, int seedcost8, int basesellingprice8, int expyield8, int productmin8, int productmax8)
    {   
        //Total amount of seeds
        seedtotal.add(new String(seedname1));
        seedtotal.add(new String(seedname2));
        seedtotal.add(new String(seedname3));
        seedtotal.add(new String(seedname4));
        seedtotal.add(new String(seedname5));
        seedtotal.add(new String(seedname6));
        seedtotal.add(new String(seedname7));
        seedtotal.add(new String(seedname8));

        //seed type
        this.seedType1 = seedType1;
        this.seedType2 = seedType2;
        this.seedType3 = seedType3;

        //seed1
        this.seedname1 = seedname1;
        this.harvesttime1 = harvesttime1;
        this.waterneed1 = waterneed1;
        this.fertilizerneeds1 = fertilizerneeds1;
        this.seedcost1 = seedcost1;
        this.basesellingprice1 = basesellingprice1;
        this.expyield1 = expyield1;
        this.productmin1 = productmin1;
        this.productmax1 = productmax1;

        //seed2
        this.seedname2 = seedname2;
        this.harvesttime2 = harvesttime2;
        this.waterneed2 = waterneed2;
        this.fertilizerneeds2 = fertilizerneeds2;
        this.seedcost2 = seedcost2;
        this.basesellingprice2 = basesellingprice2;
        this.expyield2 = expyield2;
        this.productmin2 = productmin2;
        this.productmax2 = productmax2;
        
        //seed3
        this.seedname3 = seedname3;
        this.harvesttime3 = harvesttime3;
        this.waterneed3 = waterneed3;
        this.fertilizerneeds3 = fertilizerneeds3;
        this.seedcost3 = seedcost3;
        this.basesellingprice3 = basesellingprice3;
        this.expyield3 = expyield3;
        this.productmin3 = productmin3;
        this.productmax3 = productmax3;
        
        //seed4
        this.seedname4 = seedname4;
        this.harvesttime4 = harvesttime4;
        this.waterneed4 = waterneed4;
        this.fertilizerneeds4 = fertilizerneeds4;
        this.seedcost4 = seedcost4;
        this.basesellingprice4 = basesellingprice4;
        this.expyield4 = expyield4;
        this.productyield4 = productyield4;
        
        //seed5
        this.seedname5 = seedname5;
        this.harvesttime5 = harvesttime5;
        this.waterneed5 = waterneed5;
        this.fertilizerneeds5 = fertilizerneeds5;
        this.seedcost5 = seedcost5;
        this.basesellingprice5 = basesellingprice5;
        this.expyield5 = expyield5;
        this.productyield5 = productyield5;
        
        //seed6
        this.seedname6 = seedname6;
        this.harvesttime6 = harvesttime6;
        this.waterneed6 = waterneed6;
        this.fertilizerneeds6 = fertilizerneeds6;
        this.seedcost6 = seedcost6;
        this.basesellingprice6 = basesellingprice6;
        this.expyield6 = expyield6;
        this.productyield6 = productyield6;
        
        //seed7
        this.seedname7 = seedname7;
        this.harvesttime7 = harvesttime7;
        this.waterneed7 = waterneed7;
        this.fertilizerneeds7 = fertilizerneeds7;
        this.seedcost7 = seedcost7;
        this.basesellingprice7 = basesellingprice7;
        this.expyield7 = expyield7;
        this.productmin7 = productmin7;
        this.productmax7 = productmax7;
        
        //seed8
        this.seedname8 = seedname8;
        this.harvesttime8 = harvesttime8;
        this.waterneed8 = waterneed8;
        this.fertilizerneeds8 = fertilizerneeds8;
        this.seedcost8 = seedcost8;
        this.basesellingprice8 = basesellingprice8;
        this.expyield8 = expyield8;
        this.productmin8 = productmin8;
        this.productmax8 = productmax8;
    }

    public Seedcrops(gamePanel gamepanel, Managetiles managetiles, Player player) {
	}

	public String getSeedname1() {
        return seedname1;
    }

    public void setSeedname1(String seedname1) {
        this.seedname1 = seedname1;
    }

    public String getSeedname2() {
        return seedname2;
    }

    public void setSeedname2(String seedname2) {
        this.seedname2 = seedname2;
    }

    public String getSeedname3() {
        return seedname3;
    }

    public void setSeedname3(String seedname3) {
        this.seedname3 = seedname3;
    }

    public String getSeedname4() {
        return seedname4;
    }

    public void setSeedname4(String seedname4) {
        this.seedname4 = seedname4;
    }

    public String getSeedname5() {
        return seedname5;
    }

    public void setSeedname5(String seedname5) {
        this.seedname5 = seedname5;
    }

    public String getSeedname6() {
        return seedname6;
    }

    public void setSeedname6(String seedname6) {
        this.seedname6 = seedname6;
    }

    public String getSeedname7() {
        return seedname7;
    }

    public void setSeedname7(String seedname7) {
        this.seedname7 = seedname7;
    }

    public String getSeedname8() {
        return seedname8;
    }

    public void setSeedname8(String seedname8) {
        this.seedname8 = seedname8;
    }

    public String getSeedType1() {
        return seedType1;
    }

    public void setSeedType1(String seedType1) {
        this.seedType1 = seedType1;
    }

    public String getSeedType2() {
        return seedType2;
    }

    public void setSeedType2(String seedType2) {
        this.seedType2 = seedType2;
    }

    public String getSeedType3() {
        return seedType3;
    }

    public void setSeedType3(String seedType3) {
        this.seedType3 = seedType3;
    }

    public int getHarvesttime1() {
        return harvesttime1;
    }

    public void setHarvesttime1(int harvesttime1) {
        this.harvesttime1 = harvesttime1;
    }

    public int getHarvesttime2() {
        return harvesttime2;
    }

    public void setHarvesttime2(int harvesttime2) {
        this.harvesttime2 = harvesttime2;
    }

    public int getHarvesttime3() {
        return harvesttime3;
    }

    public void setHarvesttime3(int harvesttime3) {
        this.harvesttime3 = harvesttime3;
    }

    public int getHarvesttime4() {
        return harvesttime4;
    }

    public void setHarvesttime4(int harvesttime4) {
        this.harvesttime4 = harvesttime4;
    }

    public int getHarvesttime5() {
        return harvesttime5;
    }

    public void setHarvesttime5(int harvesttime5) {
        this.harvesttime5 = harvesttime5;
    }

    public int getHarvesttime6() {
        return harvesttime6;
    }

    public void setHarvesttime6(int harvesttime6) {
        this.harvesttime6 = harvesttime6;
    }

    public int getHarvesttime7() {
        return harvesttime7;
    }

    public void setHarvesttime7(int harvesttime7) {
        this.harvesttime7 = harvesttime7;
    }

    public int getHarvesttime8() {
        return harvesttime8;
    }

    public void setHarvesttime8(int harvesttime8) {
        this.harvesttime8 = harvesttime8;
    }

    public int getWaterneed1() {
        return waterneed1;
    }

    public void setWaterneed1(int waterneed1) {
        this.waterneed1 = waterneed1;
    }

    public int getWaterneed2() {
        return waterneed2;
    }

    public void setWaterneed2(int waterneed2) {
        this.waterneed2 = waterneed2;
    }

    public int getWaterneed3() {
        return waterneed3;
    }

    public void setWaterneed3(int waterneed3) {
        this.waterneed3 = waterneed3;
    }

    public int getWaterneed4() {
        return waterneed4;
    }

    public void setWaterneed4(int waterneed4) {
        this.waterneed4 = waterneed4;
    }

    public int getWaterneed5() {
        return waterneed5;
    }

    public void setWaterneed5(int waterneed5) {
        this.waterneed5 = waterneed5;
    }

    public int getWaterneed6() {
        return waterneed6;
    }

    public void setWaterneed6(int waterneed6) {
        this.waterneed6 = waterneed6;
    }

    public int getWaterneed7() {
        return waterneed7;
    }

    public void setWaterneed7(int waterneed7) {
        this.waterneed7 = waterneed7;
    }

    public int getWaterneed8() {
        return waterneed8;
    }

    public void setWaterneed8(int waterneed8) {
        this.waterneed8 = waterneed8;
    }

    public int getFertilizerneeds1() {
        return fertilizerneeds1;
    }

    public void setFertilizerneeds1(int fertilizerneeds1) {
        this.fertilizerneeds1 = fertilizerneeds1;
    }

    public int getFertilizerneeds2() {
        return fertilizerneeds2;
    }

    public void setFertilizerneeds2(int fertilizerneeds2) {
        this.fertilizerneeds2 = fertilizerneeds2;
    }

    public int getFertilizerneeds3() {
        return fertilizerneeds3;
    }

    public void setFertilizerneeds3(int fertilizerneeds3) {
        this.fertilizerneeds3 = fertilizerneeds3;
    }

    public int getFertilizerneeds4() {
        return fertilizerneeds4;
    }

    public void setFertilizerneeds4(int fertilizerneeds4) {
        this.fertilizerneeds4 = fertilizerneeds4;
    }

    public int getFertilizerneeds5() {
        return fertilizerneeds5;
    }

    public void setFertilizerneeds5(int fertilizerneeds5) {
        this.fertilizerneeds5 = fertilizerneeds5;
    }

    public int getFertilizerneeds6() {
        return fertilizerneeds6;
    }

    public void setFertilizerneeds6(int fertilizerneeds6) {
        this.fertilizerneeds6 = fertilizerneeds6;
    }

    public int getFertilizerneeds7() {
        return fertilizerneeds7;
    }

    public void setFertilizerneeds7(int fertilizerneeds7) {
        this.fertilizerneeds7 = fertilizerneeds7;
    }

    public int getFertilizerneeds8() {
        return fertilizerneeds8;
    }

    public void setFertilizerneeds8(int fertilizerneeds8) {
        this.fertilizerneeds8 = fertilizerneeds8;
    }

    public int getProductmin1() {
        return productmin1;
    }

    public void setProductmin1(int productmin1) {
        this.productmin1 = productmin1;
    }

    public int getProductmax1() {
        return productmax1;
    }

    public void setProductmax1(int productmax1) {
        this.productmax1 = productmax1;
    }

    public int getProductyield1() {
        return productyield1;
    }

    public void setProductyield1(int productyield1) {
        this.productyield1 = productyield1;
    }

    public int getProductmin2() {
        return productmin2;
    }

    public void setProductmin2(int productmin2) {
        this.productmin2 = productmin2;
    }

    public int getProductmax2() {
        return productmax2;
    }

    public void setProductmax2(int productmax2) {
        this.productmax2 = productmax2;
    }

    public int getProductyield2() {
        return productyield2;
    }

    public void setProductyield2(int productyield2) {
        this.productyield2 = productyield2;
    }

    public int getProductmin3() {
        return productmin3;
    }

    public void setProductmin3(int productmin3) {
        this.productmin3 = productmin3;
    }

    public int getProductmax3() {
        return productmax3;
    }

    public void setProductmax3(int productmax3) {
        this.productmax3 = productmax3;
    }

    public int getProductyield3() {
        return productyield3;
    }

    public void setProductyield3(int productyield3) {
        this.productyield3 = productyield3;
    }

    public int getProductyield4() {
        return productyield4;
    }

    public void setProductyield4(int productyield4) {
        this.productyield4 = productyield4;
    }

    public int getProductyield5() {
        return productyield5;
    }

    public void setProductyield5(int productyield5) {
        this.productyield5 = productyield5;
    }

    public int getProductyield6() {
        return productyield6;
    }

    public void setProductyield6(int productyield6) {
        this.productyield6 = productyield6;
    }

    public int getProductmin7() {
        return productmin7;
    }

    public void setProductmin7(int productmin7) {
        this.productmin7 = productmin7;
    }

    public int getProductmax7() {
        return productmax7;
    }

    public void setProductmax7(int productmax7) {
        this.productmax7 = productmax7;
    }

    public int getProductyield7() {
        return productyield7;
    }

    public void setProductyield7(int productyield7) {
        this.productyield7 = productyield7;
    }

    public int getProductmin8() {
        return productmin8;
    }

    public void setProductmin8(int productmin8) {
        this.productmin8 = productmin8;
    }

    public int getProductmax8() {
        return productmax8;
    }

    public void setProductmax8(int productmax8) {
        this.productmax8 = productmax8;
    }

    public int getProductyield8() {
        return productyield8;
    }

    public void setProductyield8(int productyield8) {
        this.productyield8 = productyield8;
    }

    public int getSeedcost1() {
        return seedcost1;
    }

    public void setSeedcost1(int seedcost1) {
        this.seedcost1 = seedcost1;
    }

    public int getSeedcost2() {
        return seedcost2;
    }

    public void setSeedcost2(int seedcost2) {
        this.seedcost2 = seedcost2;
    }

    public int getSeedcost3() {
        return seedcost3;
    }

    public void setSeedcost3(int seedcost3) {
        this.seedcost3 = seedcost3;
    }

    public int getSeedcost4() {
        return seedcost4;
    }

    public void setSeedcost4(int seedcost4) {
        this.seedcost4 = seedcost4;
    }

    public int getSeedcost5() {
        return seedcost5;
    }

    public void setSeedcost5(int seedcost5) {
        this.seedcost5 = seedcost5;
    }

    public int getSeedcost6() {
        return seedcost6;
    }

    public void setSeedcost6(int seedcost6) {
        this.seedcost6 = seedcost6;
    }

    public int getSeedcost7() {
        return seedcost7;
    }

    public void setSeedcost7(int seedcost7) {
        this.seedcost7 = seedcost7;
    }

    public int getSeedcost8() {
        return seedcost8;
    }

    public void setSeedcost8(int seedcost8) {
        this.seedcost8 = seedcost8;
    }

    public int getBasesellingprice1() {
        return basesellingprice1;
    }

    public void setBasesellingprice1(int basesellingprice1) {
        this.basesellingprice1 = basesellingprice1;
    }

    public int getBasesellingprice2() {
        return basesellingprice2;
    }

    public void setBasesellingprice2(int basesellingprice2) {
        this.basesellingprice2 = basesellingprice2;
    }

    public int getBasesellingprice3() {
        return basesellingprice3;
    }

    public void setBasesellingprice3(int basesellingprice3) {
        this.basesellingprice3 = basesellingprice3;
    }

    public int getBasesellingprice4() {
        return basesellingprice4;
    }

    public void setBasesellingprice4(int basesellingprice4) {
        this.basesellingprice4 = basesellingprice4;
    }

    public int getBasesellingprice5() {
        return basesellingprice5;
    }

    public void setBasesellingprice5(int basesellingprice5) {
        this.basesellingprice5 = basesellingprice5;
    }

    public int getBasesellingprice6() {
        return basesellingprice6;
    }

    public void setBasesellingprice6(int basesellingprice6) {
        this.basesellingprice6 = basesellingprice6;
    }

    public int getBasesellingprice7() {
        return basesellingprice7;
    }

    public void setBasesellingprice7(int basesellingprice7) {
        this.basesellingprice7 = basesellingprice7;
    }

    public int getBasesellingprice8() {
        return basesellingprice8;
    }

    public void setBasesellingprice8(int basesellingprice8) {
        this.basesellingprice8 = basesellingprice8;
    }

    public int getExpyield1() {
        return expyield1;
    }

    public void setExpyield1(int expyield1) {
        this.expyield1 = expyield1;
    }

    public double getExpyield2() {
        return expyield2;
    }

    public void setExpyield2(double expyield2) {
        this.expyield2 = expyield2;
    }

    public double getExpyield3() {
        return expyield3;
    }

    public void setExpyield3(double expyield3) {
        this.expyield3 = expyield3;
    }

    public double getExpyield4() {
        return expyield4;
    }

    public void setExpyield4(double expyield4) {
        this.expyield4 = expyield4;
    }

    public int getExpyield5() {
        return expyield5;
    }

    public void setExpyield5(int expyield5) {
        this.expyield5 = expyield5;
    }

    public double getExpyield6() {
        return expyield6;
    }

    public void setExpyield6(double expyield6) {
        this.expyield6 = expyield6;
    }

    public int getExpyield7() {
        return expyield7;
    }

    public void setExpyield7(int expyield7) {
        this.expyield7 = expyield7;
    }

    public int getExpyield8() {
        return expyield8;
    }

    public void setExpyield8(int expyield8) {
        this.expyield8 = expyield8;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int size() {
        return 8;
    }

    private int seedproductyield1()
    {
        return this.random.nextInt((getProductmax1()+1) - getProductmin1());
    }

    private int seedproductyield2()
    {
        return this.random.nextInt((getProductmax2()+1) - getProductmin2());
    }

    private int seedproductyield3()
    {
        return this.random.nextInt((getProductmax3()+1)- getProductmin3());
    }

    private int seedproductyield7()
    {
        return this.random.nextInt((getProductmax7()+1) - getProductmin7());
    }

    private int seedproductyield8()
    {
        return this.random.nextInt((getProductmax8()+1) - getProductmin8());
        
    }

    public void productrandom()
    {
        productyield1 = getProductmin1() + seedproductyield1();
        productyield2 = getProductmin2() + seedproductyield2();
        productyield3 = getProductmin3() + seedproductyield3();
        productyield7 = getProductmin7() + seedproductyield7();
        productyield8 = getProductmin8() + seedproductyield8();
    }

    public ArrayList<String> getSeedtotal() {
        return seedtotal;
    }

    public void setSeedtotal(ArrayList<String> seedtotal) {
        this.seedtotal = seedtotal;
    }

}
