import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonActionListener implements ActionListener {

    /**
     * Az adatok és megjelnítésének vezérléséhez szükséges tagváltozó.
     * */
    WWControl wwc;

    /**
     * Ezen tagváltozó a szülő elem, ahol a hivó gomb szerepel.
     * */
    Component parent;

    /**
     * @param wwCont adatok és megjelnítésének vezérléséhez szükséges.
     * @param parent a hivó gomb szülő eleme.
     * */
    public MenuButtonActionListener(WWControl wwCont, Component parent){
        this.wwc=wwCont;
        this.parent=parent;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        SelectorDialog selector=new SelectorDialog(parent);
        switch (ae.getActionCommand()){
            case "NewMenu":
                if(!selector.openNew()){
                    return;
                }

                parent.setVisible(false);
                wwc.newWW(selector.getColumn(),selector.getRow());
                break;

            case "LoadMenu":
                if(!selector.openLoad(wwc.getSavePlace())){
                    return;
                }

                wwc.loadWW(selector.getLoadFileName());
                parent.setVisible(false);
                break;
        }
    }
}
