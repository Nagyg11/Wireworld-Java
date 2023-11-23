import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class WWControl {
    WWData wwDataBeforeRun;
    WWDataActual wwDataActual;
    WWDisplay wwDsp;
    Runner runner= new Runner(this);

    int row;
    int column;

    public WWControl(){
        wwDsp=new WWDisplay(this);
        wwDataActual=new WWDataActual();
    }

    public void newWW(int c, int r){
        column=c;
        row=r;
        wwDataActual=new WWDataActual(column,row);


        wwDsp.newWWMap(column,row);
        wwDsp.setVisible(true);
    }

    public void loadWW(String fileName){
        wwDataActual=new WWDataActual();
        wwDataActual.loadWWDataActuals(fileName);
        column=wwDataActual.getColumnNum();
        row=wwDataActual.getRowNum();
        wwDsp.newWWMap(column,row);

        updateWWMap();
        wwDsp.setVisible(true);
    }

    public void updateWireWorldMatrix(){

        for (Component bt:getWWMapPanel().getComponents()){
            MyButton mbtn=(MyButton) bt;
            wwDataActual.setXY(mbtn.getIdX(),mbtn.getIdY(),mbtn.getStatus());
        }

    }

    public void saveWW(String fileName){
        wwDataActual.saveWWDataActual(fileName);
    }

    public void updateWWMap(){
        for (Component bt : getWWMapPanel().getComponents()) {
            MyButton mbtn = (MyButton) bt;
            mbtn.setStatus(wwDataActual.getXY(mbtn.getIdX(),mbtn.getIdY()));
        }
    }

    public void stepWWDataActual(){
    }

    public void resetToBeforeDunMatrix(){
        if(wwDataBeforeRun==null){
            return;
        }
        wwDataActual.copy(wwDataBeforeRun);
        updateWWMap();
    }


    private JPanel getWWMapPanel() {
        for(Component cmpnt:wwDsp.getContentPane().getComponents()){
            if(cmpnt.getName().equals("wireWorldMap")){
                return (JPanel)cmpnt;
            }
        }
        return null;
    }

    public void saveBeforeRunMatrix(){
        updateWireWorldMatrix();
        wwDataBeforeRun=new WWData(column,row);
        wwDataBeforeRun.copy(wwDataActual);
    }

    public void run(){
        if(!runner.getWWLoop()){
            saveBeforeRunMatrix();
            runner=new Runner(this);
            runner.setWWLoop(true);
            runner.start();
        }
    }

    public void stop(){
        runner.setWWLoop(false);
    }

    public void clearMap(){
        wwDataActual.resetWireWorldMatrix();
        updateWWMap();
    }

    public File getSavePlace(){return wwDataActual.getSavePlace();}



    public void setRow(int r){row=r;}
    public void setColumn(int c){column=c;}

    public int getRow(){return row;}
    public int getColumn(){return column;}

}
