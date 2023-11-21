import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class Display extends JFrame {

    int r=50;
    int c=100;

    public Display(){
        super("Wireworld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
    }

    public void newWWMap(){
        JPanel jpWWMap=new JPanel();
        jpWWMap.setName("wireWorldMap");
        jpWWMap.setLayout(new GridSquare(r, c));
        MouseAdapter mA=new MyButtonMosueAction();
        //setLayout(new GridLayout(2,0));

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                MyButton btn=new MyButton();
                btn.setIdX(i);
                btn.setIdY(j);
                btn.setClearStatus();
                btn.addMouseListener(mA);
                jpWWMap.add(btn);
            }
        }
        add(jpWWMap,BorderLayout.CENTER);
        JPanel jpn=new JPanel();
        JButton btnStart=new JButton("Start");
        BtnStartAction bsa=new BtnStartAction();
        btnStart.addActionListener(bsa);
        JTextField jtx=new JTextField(10);
        jpn.add(btnStart);
        jpn.add(jtx);
        add(jpn, BorderLayout.SOUTH);
    }

    public void loadWWMap(){

    }

    public void setColumn(int c){this.c=c;}
    public void setRow(int r){this.r=r;}

}
