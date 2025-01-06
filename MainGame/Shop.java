package MainGame;

interface interfaceshop 
{
    void buyseed1();
    void buyseed2();
    void buyseed3();
    void buyseed4();
    void buyseed5();
    void buyseed6();
    void buyseed7();
    void buyseed8();
    void seedsdeduct1();
    void seedsdeduct2();
    void seedsdeduct3();
    void seedsdeduct4(); 
    void seedsdeduct5();
    void seedsdeduct6();    
    void seedsdeduct7();
    void seedsdeduct8();
}

public class Shop {

    private Stats stats;
    private int seedamount1;
    private int seedamount2;
    private int seedamount3;
    private int seedamount4;
    private int seedamount5;
    private int seedamount6;
    private int seedamount7;
    private int seedamount8;

    public Shop(Stats stats, int seedamount1, int seedamount2, int seedamount3, int seedamount4, 
                int seedamount5, int seedamount6, int seedamount7, int seedamount8) {
        this.stats = stats;
        this.seedamount1 = seedamount1;
        this.seedamount2 = seedamount2;
        this.seedamount3 = seedamount3;
        this.seedamount4 = seedamount4;
        this.seedamount5 = seedamount5;
        this.seedamount6 = seedamount6;
        this.seedamount7 = seedamount7;
        this.seedamount8 = seedamount8;
    }

    public void buyseed1()
    {
        seedamount1 += 1;
        stats.coindeduct1();
    }

    public void buyseed2()
    {
        seedamount2 += 1;
        stats.coindeduct2();
    }

    public void buyseed3()
    {
        seedamount3 += 1;
        stats.coindeduct3();
    }

    public void buyseed4()
    {
        seedamount4 += 1;  
        stats.coindeduct4();
    }

    public void buyseed5()
    {
        seedamount5 += 1;
        stats.coindeduct5();
    }

    public void buyseed6()
    {
        seedamount6 += 1;
        stats.coindeduct6();
    }

    public void buyseed7()
    {
        seedamount7 += 1;
        stats.coindeduct7();
    }

    public void buyseed8()
    {
        seedamount8 += 1;
        stats.coindeduct8();
    }

    public void seedsdeduct1()
    {
        seedamount1 -= 1;
    }

    public void seedsdeduct2()
    {
        seedamount2 -= 1;
    }
    
    public void seedsdeduct3()
    {
        seedamount3 -= 1;
    }

    public void seedsdeduct4()
    {
        seedamount4 -= 1;
    }

    public void seedsdeduct5()
    {
        seedamount5 -= 1;
    }

    public void seedsdeduct6()
    {
        seedamount6 -= 1;
    }
    
    public void seedsdeduct7()
    {
        seedamount7 -= 1;
    }

    public void seedsdeduct8()
    {
        seedamount8 -= 1;
    }

    public int getSeedamount1() {
        return seedamount1;
    }

    public void setSeedamount1(int seedamount1) {
        this.seedamount1 = seedamount1;
    }

    public int getSeedamount2() {
        return seedamount2;
    }

    public void setSeedamount2(int seedamount2) {
        this.seedamount2 = seedamount2;
    }

    public int getSeedamount3() {
        return seedamount3;
    }

    public void setSeedamount3(int seedamount3) {
        this.seedamount3 = seedamount3;
    }

    public int getSeedamount4() {
        return seedamount4;
    }

    public void setSeedamount4(int seedamount4) {
        this.seedamount4 = seedamount4;
    }

    public int getSeedamount5() {
        return seedamount5;
    }

    public void setSeedamount5(int seedamount5) {
        this.seedamount5 = seedamount5;
    }

    public int getSeedamount6() {
        return seedamount6;
    }

    public void setSeedamount6(int seedamount6) {
        this.seedamount6 = seedamount6;
    }

    public int getSeedamount7() {
        return seedamount7;
    }

    public void setSeedamount7(int seedamount7) {
        this.seedamount7 = seedamount7;
    }

    public int getSeedamount8() {
        return seedamount8;
    }

    public void setSeedamount8(int seedamount8) {
        this.seedamount8 = seedamount8;
    }
}
