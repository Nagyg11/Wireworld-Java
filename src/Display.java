import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class Display extends JFrame {

    int r=50;
    int c=100;

    public Display(){
        super("Wireworld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridSquare(r, c));
        MouseAdapter mhv=new MosueHover();

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                MyButton btn=new MyButton();
                btn.setIdX(i);
                btn.setIdY(j);
                btn.setType(0);
                btn.addMouseListener(mhv);
                add(btn);
            }
        }
        setSize(800,600);
    }

}
