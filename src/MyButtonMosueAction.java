import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButtonMosueAction extends MouseAdapter {

    boolean pressLeft=false;
    boolean pressRight=false;

    @Override
    public void mousePressed(MouseEvent me) {
        MyButton btn = (MyButton) me.getSource();

        if(me.getButton()==MouseEvent.BUTTON1){
            pressLeft=true;
            btn.changeBetweenStatus();
        }else if(me.getButton()==MouseEvent.BUTTON3){
            pressRight=true;
            btn.setClearStatus();
        }

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        pressLeft=false;
        pressRight=false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        MyButton btn = (MyButton) me.getSource();
        if(pressLeft) {
            btn.setHoverStatus();
        }else if(pressRight){
            btn.setClearStatus();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

}
