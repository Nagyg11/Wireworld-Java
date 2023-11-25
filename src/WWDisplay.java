import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class WWDisplay extends JFrame {

    private WWControl wwc;

    public WWDisplay(WWControl wwCont){
        super("Wireworld");
        wwc=wwCont;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(wwc.getLogo().getImage());
    }

    public void newWWMap(int c, int r){
        JPanel jpWWMap=new JPanel();
        jpWWMap.setName("wireWorldMap");
        jpWWMap.setLayout(new GridSquare(r, c));
        MouseAdapter mA=new MyButtonMosueAction();

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

        ActionListener mbal=new ManageButtonActionListener(wwc,this);
        JMenuItem saveMenuItem=new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");
        saveMenuItem.addActionListener(mbal);
        JMenuItem newMenuItem=new JMenuItem("New");
        newMenuItem.setActionCommand("New");
        newMenuItem.addActionListener(mbal);
        JMenuItem loadMenItem=new JMenuItem("Load");
        loadMenItem.setActionCommand("Load");
        loadMenItem.addActionListener(mbal);


        JMenuItem startMenuItem=new JMenuItem("Start");
        startMenuItem.setActionCommand("Start");
        startMenuItem.addActionListener(mbal);

        JMenuItem stopMenuItem=new JMenuItem("Stop");
        stopMenuItem.setActionCommand("Stop");
        stopMenuItem.addActionListener(mbal);

        JMenuItem speedMenuItem=new JMenuItem("Speed");
        speedMenuItem.setActionCommand("Speed");
        speedMenuItem.addActionListener(mbal);

        JMenuItem resetMenuItem=new JMenuItem("Reset");
        resetMenuItem.setActionCommand("Reset");
        resetMenuItem.addActionListener(mbal);

        JMenuItem clearMenuItem=new JMenuItem("Clear");
        clearMenuItem.setActionCommand("Clear");
        clearMenuItem.addActionListener(mbal);




        JMenu jMenuFile=new JMenu("File");
        JMenu jMenuRunning=new JMenu("Running");
        JMenu jMenuEdit=new JMenu("Edit");

        jMenuFile.add(saveMenuItem);
        jMenuFile.add(newMenuItem);
        jMenuFile.add(loadMenItem);
        jMenuRunning.add(startMenuItem);
        jMenuRunning.add(stopMenuItem);
        jMenuRunning.add(speedMenuItem);
        jMenuEdit.add(resetMenuItem);
        jMenuEdit.add(clearMenuItem);


        JMenuBar jmb=new JMenuBar();
        jmb.add(jMenuFile);
        jmb.add(jMenuRunning);
        jmb.add(jMenuEdit);
        setJMenuBar(jmb);

    }
}
