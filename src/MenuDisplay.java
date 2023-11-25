import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuDisplay extends JFrame {

    private WWControl wwc=new WWControl();

    public MenuDisplay(){
        super("Wireworld menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setResizable(false);
        JButton newBtn=new JButton("Start new");
        JButton loadBtn=new JButton("Load map");
        JPanel jp=new JPanel();
        jp.add(newBtn);
        jp.add(loadBtn);
        setLayout(new GridLayout(3,0));
        setIconImage(wwc.getLogo().getImage());
        add(new JLabel(wwc.getLogo()));
        add(jp);

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
            if(!selector.openLoad(wwc.getSavePlace())){
                return;
            }

            wwc.loadWW(selector.getLoadFileName());
            this.setVisible(false);
        });


    }
}
