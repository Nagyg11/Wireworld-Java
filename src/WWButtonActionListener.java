import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WWButtonActionListener implements ActionListener {
    WWControl wwc;
    Component cmpnt;
    public WWButtonActionListener(WWControl wwc, Component cmpnt) {
        this.wwc=wwc;
        this.cmpnt=cmpnt;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()){
                case "Start":

                    break;
                case "Stop":

                    break;

                    case "Reset":

                    break;

                    case "Clear":

                    break;

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
