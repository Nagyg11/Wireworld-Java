import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class MyButton extends JButton {
    int idX;
    int idY;
    int status;

    public MyButton(){}

    public MyButton(String s){
        super(s);
        status=0;
    }

    public void setStatus(int t){
        this.status=t;
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
            default:
                throw new IndexOutOfBoundsException("Nem letezo statuszt valasztott a mezonek.");
        }
    }

    public int getStatus(){return status;}

    public void setClearStatus(){
        setStatus(0);
    }

    public void changeBetweenStatus(){
        if(status==0){
            setStatus(3);
        }else{
            setStatus((status%3)+1);
        }
    }

    public void setHoverStatus(){
        setStatus(3);
    }

    public void setIdX(int id){this.idX=id;}
    public int getIdX(){return idX;}

    public void setIdY(int id){this.idY=id;}
    public int getIdY(){return idY;}


}
