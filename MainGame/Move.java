package MainGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Move implements KeyListener {

    public boolean up, down, left, right, interact, harvest;
    public boolean tool1, tool2, tool3, tool4, tool5;
    public boolean seed1, seed2, seed3, seed4, seed5, seed6, seed7, seed8;

    @Override
    public void keyTyped(KeyEvent e) {

       
    }
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = true;
        } if (code == KeyEvent.VK_S) {
            down = true;
        } if (code == KeyEvent.VK_A) {
            left = true;
        } if (code == KeyEvent.VK_D) {
            right = true;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        } if (code == KeyEvent.VK_S) {
            down = false;
        } if (code == KeyEvent.VK_A) {
            left = false;
        } if (code == KeyEvent.VK_D) {
            right = false;
        } if (code == KeyEvent.VK_E) {
            interact = true;
        } if (code == KeyEvent.VK_Q) {
            harvest = true;
        }

        switch(code) {
            case KeyEvent.VK_Z: tool1 = true; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_X: tool1 = false; tool2 = true; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_C: tool1 = false; tool2 = false; tool3 = true; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_V: tool1 = false; tool2 = false; tool3 = false; tool4 = true; tool5 = false; break;
            case KeyEvent.VK_B: tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = true; break;
            case KeyEvent.VK_1: seed1 = true; seed2 = false; seed3 = false; seed4 = false; seed5 = false; seed6 = false; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_2: seed1 = false; seed2 = true; seed3 = false; seed4 = false; seed5 = false; seed6 = false; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_3: seed1 = false; seed2 = false; seed3 = true; seed4 = false; seed5 = false; seed6 = false; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_4: seed1 = false; seed2 = false; seed3 = false; seed4 = true; seed5 = false; seed6 = false; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_5: seed1 = false; seed2 = false; seed3 = false; seed4 = false; seed5 = true; seed6 = false; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_6: seed1 = false; seed2 = false; seed3 = false; seed4 = false; seed5 = false; seed6 = true; seed7 = false; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_7: seed1 = false; seed2 = false; seed3 = false; seed4 = false; seed5 = false; seed6 = false; seed7 = true; seed8 = false; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
            case KeyEvent.VK_8: seed1 = false; seed2 = false; seed3 = false; seed4 = false; seed5 = false; seed6 = false; seed7 = false; seed8 = true; tool1 = false; tool2 = false; tool3 = false; tool4 = false; tool5 = false; break;
        }   
    }
}
