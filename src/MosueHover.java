import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MosueHover extends MouseAdapter {

    boolean press=false;

    @Override
    public void mousePressed(MouseEvent me) {
        press=true;
        MyButton btn = (MyButton) me.getSource();
        if(btn.getType()==0){
            btn.setType(3);
        }else{
            btn.setType((btn.getType()%3)+1);

        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        press=false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if(press) {
            MyButton btn = (MyButton) me.getSource();
            btn.setType(3);
        }
    }
}
