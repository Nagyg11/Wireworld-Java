import javax.swing.*;
import java.awt.*;

public class WWControl {
    public WWData wwData;
    WWDisplay wwDsp;

    int row;
    int column;

    public WWControl(){
        wwDsp=new WWDisplay(this);
    }

    public void newWW(int c, int r){
        column=c;
        row=r;
        wwData=new WWData(column,row);


        wwDsp.newWWMap(column,row);
        wwDsp.setVisible(true);
    }

    public void loadWW(String fileName){
        wwData=new WWData();
        wwData.loadWWDatas(fileName);
        column=wwData.getWireWorldMatrix().get(0).size();
        row=wwData.getWireWorldMatrix().size();
        wwDsp.newWWMap(column,row);

        updateWWMap();
        wwDsp.setVisible(true);
    }

    public void updateWireWorldMatrix(){

        for (Component bt:getWWMapPanel().getComponents()){
            MyButton mbtn=(MyButton) bt;
            wwData.getWireWorldMatrix().get(mbtn.getIdY()).set(mbtn.getIdX(),mbtn.getStatus());
        }

    }

    public void saveWW(String fileName){
        wwData.saveWWDatas(fileName);
    }

    private void updateWWMap(){
        for (Component bt : getWWMapPanel().getComponents()) {
            MyButton mbtn = (MyButton) bt;
            mbtn.setStatus(wwData.getWireWorldMatrix().get(mbtn.getIdY()).get(mbtn.getIdX()));
        }
    }


    private JPanel getWWMapPanel() {
        for(Component cmpnt:wwDsp.getContentPane().getComponents()){
            if(cmpnt.getName().equals("wireWorldMap")){
                return (JPanel)cmpnt;
            }
        }
        return null;
    }

    public void setRow(int r){row=r;}
    public void setColumn(int c){column=c;}

    public int getRow(){return row;}
    public int getColumn(){return column;}

}
