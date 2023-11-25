import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WWControl {
    private WWData wwDataBeforeRun;
    private WWDataActual wwDataActual;
    private WWDisplay wwDsp;
    private Runner runner= new Runner(this, wwDataActual);

    private static ImageIcon logo =new ImageIcon(System.getProperty("user.dir")+ File.separator+"img"+File.separator+"wireworld.png");

    private static File savePlace=new File(System.getProperty("user.dir")+File.separator+"maps");

    private int row;
    private int column;

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
        wwDataActual.loadWWDataActuals(savePlace+File.separator+fileName);
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
        stop();
        wwDataActual.saveWWDataActual(savePlace+File.separator+fileName);
    }

    public void updateWWMap(){
        for (Component bt : getWWMapPanel().getComponents()) {
            MyButton mbtn = (MyButton) bt;
            mbtn.setStatus(wwDataActual.getXY(mbtn.getIdX(),mbtn.getIdY()));
        }
    }

    public void stepWWDataActual(){
    }

    public void resetToBeforeRunMatrix(){
        stop();
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
            runner=new Runner(this, wwDataActual);
            runner.setWWLoop(true);
            runner.start();
        }
    }

    public void stop(){
        runner.setWWLoop(false);
    }

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

    public double getWaitTime(){return runner.getWaitTime();}

    public File getSavePlace(){return savePlace;}

    public ImageIcon getLogo() {
        return logo;
    }

    public void setRow(int r){row=r;}
    public void setColumn(int c){column=c;}

    public int getRow(){return row;}
    public int getColumn(){return column;}

}
