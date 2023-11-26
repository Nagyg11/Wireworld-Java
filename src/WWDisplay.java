import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * A wire world map megjelenítésére szolgáló osztály, amely a JFrame osztály leszármazottja hisz ezt egy új ablakon teszi meg.
 * */
public class WWDisplay extends JFrame {

    /**
     * Az adatok és megjelnítésének vezérléséhez szükséges tagváltozó. (főként csak apraméterben tovább adás céljából szerepel ebben az osztályban)
     * */
    private WWControl wwc;

    /**
     * A vezérlő gombok listener osztályának példánya.
     * */
    private ActionListener mbal;


    /**
     * @param wwCont az adatok és megjelenítéshez szükséges példány
     * */
    public WWDisplay(WWControl wwCont){
        super("Wireworld");
        wwc=wwCont;
        mbal=new ManageButtonActionListener(wwc,this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(wwc.getLogo().getImage());
    }


    /**
     * @param column megadja hány oszlopból álljon a mezőkből álló wire world map
     * @param row megadja hány oszlopból álljon a mezőkből álló wire worldmap
     * Ezen függvény inicializálja a mezőket a mapre és a vezérlő gomobokat a fejléc menübe.
     * */
    public void newWWMap(int column, int row){
        createMapButtons(column,row);
        createMenuBar();
    }

    /**
     * Ezen függvény inicializálja a mező gombokat a wire world map-re.
     * */
    private void createMapButtons(int column, int row){
        JPanel jpWWMap=new JPanel();
        jpWWMap.setName("wireWorldMap");
        jpWWMap.setLayout(new GridSquare(row, column));
        MouseAdapter mA=new MapButtonMouseAction();

        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {
                MapButton btn=new MapButton();
                btn.setIdX(j);
                btn.setIdY(i);
                btn.setClearStatus();
                btn.addMouseListener(mA);
                jpWWMap.add(btn);
            }
        }
        add(jpWWMap,BorderLayout.CENTER);
    }


    /**
     * Ezen függvény inicializálja a fejléc menüsort.
     * */
    private void createMenuBar(){
        JMenuBar jmb=new JMenuBar();
        createFileMenu(jmb);
        createRunningMenu(jmb);
        createEditMenu(jmb);
        setJMenuBar(jmb);
    }

    /**
     * Ezen függvény inicializálja a fejléc menüsor File menü menüpontjait.
     * */
    private void createFileMenu(JMenuBar jmb){
        JMenuItem saveMenuItem=new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");
        saveMenuItem.addActionListener(mbal);

        JMenuItem newMenuItem=new JMenuItem("New");
        newMenuItem.setActionCommand("New");
        newMenuItem.addActionListener(mbal);

        JMenuItem loadMenItem=new JMenuItem("Load");
        loadMenItem.setActionCommand("Load");
        loadMenItem.addActionListener(mbal);

        JMenu jMenuFile=new JMenu("File");
        jMenuFile.add(saveMenuItem);
        jMenuFile.add(newMenuItem);
        jMenuFile.add(loadMenItem);

        jmb.add(jMenuFile);
    }

    /**
     * Ezen függvény inicializálja a fejléc menüsor Running menü menüpontjait.
     * */
    private void createRunningMenu(JMenuBar jmb){
        JMenuItem startMenuItem=new JMenuItem("Start");
        startMenuItem.setActionCommand("Start");
        startMenuItem.addActionListener(mbal);

        JMenuItem stopMenuItem=new JMenuItem("Stop");
        stopMenuItem.setActionCommand("Stop");
        stopMenuItem.addActionListener(mbal);

        JMenuItem speedMenuItem=new JMenuItem("Speed");
        speedMenuItem.setActionCommand("Speed");
        speedMenuItem.addActionListener(mbal);

        JMenu jMenuRunning=new JMenu("Running");
        jMenuRunning.add(startMenuItem);
        jMenuRunning.add(stopMenuItem);
        jMenuRunning.add(speedMenuItem);

        jmb.add(jMenuRunning);
    }

    /**
     * Ezen függvény inicializálja a fejléc menüsor Edit menü menüpontjait.
     * */
    private void createEditMenu(JMenuBar jmb){

        JMenuItem resetMenuItem=new JMenuItem("Reset");
        resetMenuItem.setActionCommand("Reset");
        resetMenuItem.addActionListener(mbal);

        JMenuItem clearMenuItem=new JMenuItem("Clear");
        clearMenuItem.setActionCommand("Clear");
        clearMenuItem.addActionListener(mbal);
        JMenu jMenuEdit=new JMenu("Edit");
        jMenuEdit.add(resetMenuItem);
        jMenuEdit.add(clearMenuItem);

        jmb.add(jMenuEdit);
    }
}
