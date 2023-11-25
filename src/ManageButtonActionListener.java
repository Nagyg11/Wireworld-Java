import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageButtonActionListener implements ActionListener {
    WWControl wwc;
    Component cmpnt;
    SelectorDialog sd=new SelectorDialog(cmpnt);
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
                    wwc.stop();
                    break;

                case "Reset":
                        wwc.resetToBeforeRunMatrix();
                    break;

                case "Clear":
                        wwc.clearMap();
                    break;
                case "Save":
                        wwc.stop();
                        if(!sd.selectSaveName()){
                            return;
                        }
                        wwc.updateWireWorldMatrix();
                        wwc.saveWW(sd.getSaveName());
                    break;

                case "Load":
                    wwc.stop();
                    if(!sd.openLoad(wwc.getSavePlace())){
                        return;
                    }
                    wwc.setVisible(false);
                    wwc=new WWControl();
                    wwc.loadWW(sd.getLoadFileName());
                    break;
                case "Speed":
                    wwc.stop();
                    if(!sd.selectSpeed(wwc)){
                        return;
                    }
                    wwc.setWaitTime(sd.getSpeed());
                    break;
            }
    }
}
