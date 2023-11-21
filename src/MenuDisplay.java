import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuDisplay extends JFrame {

    WWControl wwc=new WWControl();

    public MenuDisplay(){
        super("Wireworld menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        JButton newBtn=new JButton("Start new");
        JButton loadBtn=new JButton("Load");
        add(newBtn,BorderLayout.NORTH);
        add(loadBtn,BorderLayout.SOUTH);

        newBtn.addActionListener((x) -> {
            SelectorDialog selector=new SelectorDialog(this);
            if(!selector.openNew()){
                return;
            }

            this.setVisible(false);
            wwc.newWW(selector.getColumn(),selector.getRow());
        });

        loadBtn.addActionListener((x)-> {
            SelectorDialog selector=new SelectorDialog(this);
            if(!selector.openLoad()){
                return;
            }

            wwc.loadWW(selector.getLoadFileName());
            this.setVisible(false);
        });


    }
}
