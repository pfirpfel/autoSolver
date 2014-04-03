/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Elias
 */
public class KeyAction {

    Robot robot;

    public KeyAction() throws AWTException {
        this.robot = new Robot();
    }

    public void keyPress(int key) {
        System.out.println(key);
        System.out.println("UP"+KeyEvent.VK_UP);
        System.out.println("DOWN"+KeyEvent.VK_DOWN);
        System.out.println("RIGHT"+KeyEvent.VK_RIGHT);
        System.out.println("LEFT"+KeyEvent.VK_LEFT);
        //robot.keyPress(key);
    }
}
