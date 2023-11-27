package WireWorldDisplay;

import Control.SelectorDialog;
import Control.WWControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* A WireWorldDisplay.ManageButtonActionListener osztály arra szolgál, hogy a Wire World map-ot közvetlen vezérlő menü gombok lenyomása esetén a megfelelő az adott gombhoz tartozó tevékenységek végrehajtódjanak.
* */
public class ManageButtonActionListener implements ActionListener {

    /**
     * Az adatok és megjelnítésének vezérléséhez szükséges tagváltozó.
     * */
    private WWControl wwc;

    /**
     * A Control.SelectorDialog osztály fügvényeihez szükséges szülő elem tárolásához szükséges tagváltozó.
     * */
    private Component cmpnt;

    /**
     * Ezen tagváltozó a felugró ablakok megjelnításáre szolgáló osztály példánya. Célja, hogy megjeleníthetők legyenek a különböző felugró ablakok.
     * */
    private SelectorDialog sd;
    public ManageButtonActionListener(WWControl wwc, Component cmpnt) {
        this.wwc=wwc;
        this.cmpnt=cmpnt;
        sd=new SelectorDialog(this.cmpnt);
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

                case "New":
                        wwc.stop();
                        if(!sd.openNew()){
                            return;
                        }
                        wwc.setVisible(false);
                        wwc=new WWControl();
                        wwc.newWW(sd.getColumn(), sd.getRow());

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
