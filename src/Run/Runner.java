package Run;

import Control.WWControl;

/**
 * Ezen osztály a Thread osztály leszármazptja ezzel, elérve, hogy a megfelelő függvénye külön szálon fusson, mivel a futása közben frrsíteni kell a megjelenést és a háttérban müködő adatároló 2d-s listát is.
 * */
public class Runner extends Thread {

    /**
     * Az adatok és megjelnítésének vezérléséhez szükséges tagváltozó.
     * */
    private final WWControl wwc;


    /**
     * Ezen tagváltozó értéke szerint fut vagy nem fut a run() függvényben szereplő léptető rész.
     * */
    private boolean wwLoop=false;

    /**
     * Ezen statikus tagváltozó azt az időt tárolja másodpercben amennyi két wire world map lépés frissítés között várjon a végrehajtó.
     * */
    private static double waitTime=0.1;

    /**
     * Ezen tagváltozó az utolsó időpontot tárolja maikor lefutott teljesen a run()-ban található függvény. Az idő eltelésének mérésére szükséges.
     * */
    private long startTime=System.currentTimeMillis();

    public Runner(WWControl wwControl){
        this.wwc=wwControl;
    }

    @Override
    public void run(){
        wwc.updateWireWorldMatrix();
        long deltaTime=0;
        long currentTime;
        while(wwLoop){
            currentTime=System.currentTimeMillis();
            deltaTime+=(currentTime-startTime);
            if(((double)(deltaTime))/1000>waitTime) {
                if(!wwc.oneStep()){
                    wwLoop=false;
                }
                wwc.updateWWMap();
                deltaTime=0;
            }
            startTime=currentTime;
        }
    }


    /**
     * Ezen függvénnyel a frissítések közötti várakozási időt lehet állítani.
     * */
    public static void setWaitTime(double value){waitTime=value;}

    /**
     * @return wire world map lépés frissítések közötti várakozási idő.
     * */
    public double getWaitTime(){return waitTime;}

    /**
     * Ezen függvénnyel a wwLoop tagváltozó értéke állítható.
     * */
    public void setWWLoop(boolean value){
        wwLoop=value;
    }
    /**
     * @return éppen fut-e még a frissítő ciklus.
     * */
    public boolean getWWLoop(){
        return wwLoop;
    }

}
