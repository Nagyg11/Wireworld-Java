import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageButtonActionListener implements ActionListener {
    WWControl wwc;
    Component cmpnt;
    public ManageButtonActionListener(WWControl wwc, Component cmpnt) {
        this.wwc=wwc;
        this.cmpnt=cmpnt;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()){
                case "Start":
                    wwc.run();

                    break;
                case "Stop":

                    break;

                case "Reset":
                        wwc.resetToBeforeDunMatrix();

                        break;

                        case "Clear": break;

                    case "Save":
                        SelectorDialog sd=new SelectorDialog(cmpnt);
                        if(!sd.selectSaveName()){
                            return;
                        }
                        wwc.updateWireWorldMatrix();
                        wwc.saveWW(sd.getSaveName());
                    break;
            }
    }
}
