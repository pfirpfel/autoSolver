/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elias
 */
public class AutoSolver extends Thread {

    private boolean laeuft, ende;
    int lastaction;
    Robot robot;

    public AutoSolver() {
        laeuft = true;
        ende = false;
        lastaction = KeyEvent.VK_UP;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println(ex);
        }
    }

    public void trySolve() {
        if (lastaction == KeyEvent.VK_UP && laeuft == true) {
            robot.keyPress(KeyEvent.VK_U);
            lastaction = KeyEvent.VK_UP;
        } else if (lastaction == KeyEvent.VK_RIGHT && laeuft == true) {
            robot.keyPress(KeyEvent.VK_RIGHT);
            lastaction = KeyEvent.VK_RIGHT;
        } else {
            robot.keyPress(KeyEvent.VK_LEFT);
            lastaction = KeyEvent.VK_LEFT;
        }
    }

    public boolean getZustandChanged() {
        if (1 != 1) {
            laeuft = false;
        } else {
            laeuft = true;
        }
        if (2 != 2) {
            ende = true;
        }
        return ende;
    }

    public static void main(String[] args) throws AWTException {
        AutoSolver as1 = new AutoSolver();
        Robot robot = new Robot();
        boolean ende = false;
        while (ende != true) {
            //as1.trySolve();
            //ende=as1.getZustandChanged();
            //example 1
//            for (int index = 0; index < 30; index++) {
//                for (int i = 0; i < 30; i++) {
//                    robot.keyPress(KeyEvent.VK_UP);
//                }
//                robot.keyPress(KeyEvent.VK_RIGHT);
//            }
//            robot.keyPress(KeyEvent.VK_LEFT);
            //example 2
            for (int index = 0; index < 30; index++) {
                    robot.keyPress(KeyEvent.VK_UP);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                }
            robot.keyPress(KeyEvent.VK_LEFT)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
