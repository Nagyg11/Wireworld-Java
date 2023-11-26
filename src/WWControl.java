import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Ezen osztály az adattároló és a megjelenítést összekötő függvényeket valósít meg az adatok és megjelenítés manipulációjához.
 * */
public class WWControl {

    /**
     * Ezen tagváltozó a futtatás előttti állapotot tárolja az adattárolóból.
     * */
    private WWData wwDataBeforeRun;

    /**
     * Ezen tagváltozó az mindig aktuális állapotát tárolja a wire world mapnek, az adattárolóban
     * */
    private WWDataActual wwDataActual;

    /**
     * Ezen tagváltozó tárolja a megjelenítés elemeit.
     * */
    private WWDisplay wwDsp;

    /**
     * Ezen tagváltozó futtatás létrehozás szükséges.
     * */
    private Runner runner;

    /**
     * Ezen statikus tagváltozó a logo elérési útvonalát tárolja.
     * */
    private static ImageIcon logo =new ImageIcon(System.getProperty("user.dir")+ File.separator+"img"+File.separator+"wireworld.png");

    /**
     * Ezen statikus tagváltozó a mentési hely eléri útvonalát adja meg.
     * */
    private static File savePlace=new File(System.getProperty("user.dir")+File.separator+"maps");

    /**
     * Ezen tagváltozó tárolja hány sorból van a tárol wire world map
     * */
    private int row;

    /**
     * Ezen tagváltozó tárolja hány oszlopból van a tárol wire world map
     * */
    private int column;

    public WWControl(){
        wwDsp=new WWDisplay(this);
        wwDataActual=new WWDataActual();
        runner= new Runner(this);
    }

    /**
     * Ezen függvény egy új wire world map megjelenítését végzi és létrehozza a megfelelő adattárolót.
     * */
    public void newWW(int column, int row){
        this.column=column;
        this.row=row;
        wwDataActual=new WWDataActual(column,row);


        wwDsp.newWWMap(column,row);
        wwDsp.setVisible(true);
    }

    /**
     * Ezen függvény betölti a paraméterben megadottt mentett map-et megjelenítését és az adattárolóba is.
     * */
    public void loadWW(String fileName){
        wwDataActual=new WWDataActual();
        wwDataActual.loadWWDataActuals(savePlace+File.separator+fileName);
        column=wwDataActual.getColumnNum();
        row=wwDataActual.getRowNum();
        wwDsp.newWWMap(column,row);

        updateWWMap();
        wwDsp.setVisible(true);
    }

    /**
     * Ezen függvény frissíti az adattárolót a megjelnített mezők alapján.
     * */
    public void updateWireWorldMatrix(){

        for (Component bt:getWWMapPanel().getComponents()){
            MapButton mbtn=(MapButton) bt;
            wwDataActual.setXY(mbtn.getIdX(),mbtn.getIdY(),mbtn.getStatus());
        }

    }

    public void saveWW(String fileName){
        stop();
        wwDataActual.saveWWDataActual(savePlace+File.separator+fileName);
    }


    /**
     * Ezen függvény frissíti a megjelnítést az adatárolónak megfelelően.
     * */
    public void updateWWMap(){
        for (Component bt : getWWMapPanel().getComponents()) {
            MapButton mbtn = (MapButton) bt;
            mbtn.setStatus(wwDataActual.getXY(mbtn.getIdX(),mbtn.getIdY()));
        }
    }

    /**
     * Ezen függvény visszaállítja az aktuális adattárolót és a megjelnítést a futtatás előtti állapotba.
     * */
    public void resetToBeforeRunMatrix(){
        stop();
        if(wwDataBeforeRun==null){
            return;
        }
        wwDataActual.copy(wwDataBeforeRun);
        updateWWMap();
    }


    /**
     * @return a MapButton-ket tároló JPanel-t adja vissza.
     * */
    private JPanel getWWMapPanel() {
        for(Component cmpnt:wwDsp.getContentPane().getComponents()){
            if(cmpnt.getName().equals("wireWorldMap")){
                return (JPanel)cmpnt;
            }
        }
        return null;
    }

    /**
     * Ezen függvény elmenti a futtás előtti álapoto a megfelelő tagváltozóba, a visszaállítás érdekében.
     * */
    public void saveBeforeRunMatrix(){
        updateWireWorldMatrix();
        wwDataBeforeRun=new WWData(column,row);
        wwDataBeforeRun.copy(wwDataActual);
    }

    /**
     * Ezen függvény futtatja wire world szabályainak megfelelően az adattárolót és megjelenítést.
     * */
    public void run(){
        if(!runner.getWWLoop()){
            saveBeforeRunMatrix();
            runner=new Runner(this);
            runner.setWWLoop(true);
            runner.start();
        }
    }

    /**
     * Ezen függvény leállítja a futtatást.
     * */
    public void stop(){
        runner.setWWLoop(false);
    }

    /**
     * Ezen függvény üressé állítja az összes mezőt.
     * */
    public void clearMap(){
        stop();
        wwDataActual.resetWireWorldMatrix();
        updateWWMap();
    }

    public void setVisible(boolean value){
        stop();
        wwDsp.setVisible(value);
    }

    public void setWaitTime(double value){
        stop();
        runner.setWaitTime(value);
    }

    public boolean oneStep(){
        StatusChanger statusChanger=new StatusChanger();
        return statusChanger.oneStep(wwDataActual);
    }

    public double getWaitTime(){return runner.getWaitTime();}

    public File getSavePlace(){return savePlace;}

    public ImageIcon getLogo() {
        return logo;
    }

}
