/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.AWTException;
import java.awt.Robot;

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
        robot.keyPress(key);
    }
}
