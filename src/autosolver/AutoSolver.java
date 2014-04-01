/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autosolver;

import java.awt.event.KeyEvent;

/**
 *
 * @author Elias
 */
public class AutoSolver {
    private boolean laeuft;
    public AutoSolver(){
        laeuft=true;
    }
    public void trySolve(){
        while(laeuft){
            int aktion=(int)((Math.random()*4)+1);
            if (aktion<4){
                System.out.println("W"+KeyEvent.VK_UP);                
            }else{
                System.out.println("W"+KeyEvent.VK_RIGHT);  
            }
        }
    }
    public void getZustand(){
        if(1!=1){
            laeuft=false;
        }else{
            laeuft=true;
        }
    }
    public static void main(String[] args) {
        
    }
    
    
}
