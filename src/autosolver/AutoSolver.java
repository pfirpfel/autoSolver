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
public class AutoSolver {

    private boolean laeuft, ende;
    int lastaction;
    Robot robot;
    int zustand[][] = new int[4][4];
    int anzahl[] = new int[11];

    public AutoSolver() throws AWTException {
        laeuft = true;
        ende = false;
        lastaction = KeyEvent.VK_UP;
        robot = new Robot();
    }

    public void trysolve() {
        auszaehlen();
        //mache etwas mit den Positionen der grösstne Zahlen vergleiche sie 
        //wenn sie unmöglich zusammenzubringen sind in den nächsten zwie Zügen
        //soll er zum nächstne Paar übergehen un dausrechnen was er amachen muss.
        for (int nr = 1; nr < 11; nr++) {
            int gross = groesstesPaarNr(1);
            int positionen[][] = new int[anzahl[gross]][2];
            int zaehler = 0;
            for (int index = 0; index < 4; index++) {
                for (int i = 0; i < 4; i++) {
                    positionen[zaehler][1] = index;
                    positionen[zaehler][1] = i;
                    zaehler++;
                    if (zaehler < anzahl[gross]) {
                        break;
                    }
                }
                if (zaehler < anzahl[gross]) {
                    break;
                }
            }
            boolean ausgelöst = false;
            for (int index = 0; index < anzahl[gross]; index++) {
                if (positionen[index][1] == positionen[index + 1][1] + 1 || positionen[index][1] == positionen[index + 1][1] - 1) {
                    robot.keyPress(KeyEvent.VK_UP);
                    ausgelöst = true;
                    break;
                } else if (positionen[index][2] == positionen[index + 1][2] + 1 || positionen[index][2] == positionen[index + 1][2] - 1) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    ausgelöst = true;
                    break;
                }
            }
            if (ausgelöst == true) {
                break;
            }
        }

    }

    public void auszaehlen() {
        //zählt aus wieviele Nummern es von jedem Typ hat.
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                switch (zustand[index][i]) {
                    case 2048: {
                        anzahl[10]++;
                    }
                    case 1024: {
                        anzahl[9]++;
                    }
                    case 512: {
                        anzahl[8]++;
                    }
                    case 256: {
                        anzahl[7]++;
                    }
                    case 128: {
                        anzahl[6]++;
                    }
                    case 64: {
                        anzahl[5]++;
                    }
                    case 32: {
                        anzahl[4]++;
                    }
                    case 16: {
                        anzahl[3]++;
                    }
                    case 8: {
                        anzahl[2]++;
                    }
                    case 4: {
                        anzahl[1]++;
                    }
                    case 2: {
                        anzahl[0]++;
                    }
                }
            }
        }
    }

    public int groesstesPaarNr(int nr) {
        //gibt aus von weelches Paar das grösste ist
        int index;
        for (index = 10; index > 1; index--) {
            if (anzahl[index] > 1) {
                nr--;
                if (nr == 0) {
                    break;
                }
            }
        }
        return index;
    }

    public void auslesenZustand() {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                zustand[index][i] = 0;
            }
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
            for (int index = 0; index < 30; index++) {
                for (int i = 0; i < 30; i++) {
                    robot.keyPress(KeyEvent.VK_UP);
                }
                robot.keyPress(KeyEvent.VK_RIGHT);
            }
            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyPress(KeyEvent.VK_RIGHT);
            //example 2
//            for (int index = 0; index < 30; index++) {
//                    robot.keyPress(KeyEvent.VK_UP);
//                    robot.keyPress(KeyEvent.VK_RIGHT);
//                }
//            robot.keyPress(KeyEvent.VK_LEFT)
            //example 3
//            robot.keyPress(KeyEvent.VK_LEFT);
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyPress(KeyEvent.VK_RIGHT);
//            robot.keyPress(KeyEvent.VK_UP);
            try {
                Thread.sleep(100);
                robot.delay(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
