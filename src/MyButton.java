import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class MyButton extends JButton {
    int idX;
    int idY;
    int type;

    public MyButton(){}

    public MyButton(String s){
        super(s);
        type=0;
    }

    public void setType(int t){
        this.type=t;
        switch(t){
            case 0:
                setBackground(Color.BLACK);
                break;
            case 1:
                setBackground(Color.BLUE);
                break;
            case 2:
                setBackground(Color.RED);
                break;
            case 3:
                setBackground(Color.YELLOW);
                break;
        }
    }
    public int getType(){return type;}

    public void setIdX(int id){this.idX=idX;}
    public int getIdX(){return idX;}

    public void setIdY(int id){this.idY=idY;}
    public int getIdY(){return idY;}


}
