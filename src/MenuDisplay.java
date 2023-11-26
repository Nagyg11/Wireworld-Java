import javax.swing.*;
import java.awt.*;

/**
 * A menü megjelenítésére szolgáló osztály, amely a JFrame osztály leszármazottja hisz ezt egy új ablakon teszi meg.
 * */
public class MenuDisplay extends JFrame {

    /**
     * Az adatok és megjelnítésének vezérléséhez szükséges tagváltozó.
     * */
    private WWControl wwc;

    /**
     * Ezen konstruktor beállítja a szükséges dolgokat a menü megjeleneítéséhez.
     * */
    public MenuDisplay(){
        super("Wireworld menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        wwc=new WWControl();
        setIconImage(wwc.getLogo().getImage());
        setLayout(new GridLayout(3,0));
        add(new JLabel());
        createMenuButtons();
    }

    /**
     * Ezen függvény a menü gomobokat inicializálja.
     * */
    private void createMenuButtons(){
        JPanel jp=new JPanel();
        JButton newBtn=new JButton("Start new");
        JButton loadBtn=new JButton("Load map");

        newBtn.setActionCommand("NewMenu");
        loadBtn.setActionCommand("LoadMenu");

        MenuButtonActionListener mbal=new MenuButtonActionListener(wwc,this);
        newBtn.addActionListener(mbal);
        loadBtn.addActionListener(mbal);

        jp.add(newBtn);
        jp.add(loadBtn);
        add(jp);


    }
}
