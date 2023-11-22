import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class WWDisplay extends JFrame {

    WWControl wwc;

    public WWDisplay(WWControl wwCont){
        super("Wireworld");
        wwc=wwCont;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void newWWMap(int c, int r){
        JPanel jpWWMap=new JPanel();
        jpWWMap.setName("wireWorldMap");
        jpWWMap.setLayout(new GridSquare(r, c));
        MouseAdapter mA=new MyButtonMosueAction();
        //setLayout(new GridLayout(2,0));

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                MyButton btn=new MyButton();
                btn.setIdX(j);
                btn.setIdY(i);
                btn.setClearStatus();
                btn.addMouseListener(mA);
                jpWWMap.add(btn);
            }
        }
        add(jpWWMap,BorderLayout.CENTER);


        JPanel jpn=new JPanel();

        JButton btnStart=new JButton("Start");
        JButton btnStop=new JButton("Stop");
        JButton btnReset=new JButton("Reset");
        JButton btnClear=new JButton("Clear");
        JButton btnSave=new JButton("Save");

        ActionListener mbal=new ManageButtonActionListener(wwc,this);
        btnStart.addActionListener(mbal);
        btnStop.addActionListener(mbal);
        btnReset.addActionListener(mbal);
        btnClear.addActionListener(mbal);
        btnSave.addActionListener(mbal);

        btnStart.setActionCommand("Start");
        btnStop.setActionCommand("Stop");
        btnReset.setActionCommand("Reset");
        btnClear.setActionCommand("Clear");
        btnSave.setActionCommand("Save");


        //JTextField jtx=new JTextField(10);
        jpn.add(btnStart);
        jpn.add(btnStop);
        jpn.add(btnReset);
        jpn.add(btnClear);
        jpn.add(btnSave);
        //jpn.add(jtx);
        add(jpn, BorderLayout.SOUTH);
    }

    public void loadWWMap(){

    }

}
