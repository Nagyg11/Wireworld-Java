import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class systemData {
    ArrayList<ArrayList<Integer>> wireWorldMatrix= new ArrayList<>();

    public systemData(){
        for(int c=0;c<50;c++){
            wireWorldMatrix.add(new ArrayList<>());
            for (int r=0;r<100;r++){
                wireWorldMatrix.get(c).add(0);
            }
        }


    }

    public void updateWireWorldMatrix(Display dsp){

                for (Component bt:getWWMapPanel(dsp).getComponents()){
                    MyButton mbtn=(MyButton) bt;
                    wireWorldMatrix.get(mbtn.getIdX()).set(mbtn.getIdY(),mbtn.getStatus());
                }

    }

    public void updateWWMap(Display dsp){
            for (Component bt : getWWMapPanel(dsp).getComponents()) {
                MyButton mbtn = (MyButton) bt;
                mbtn.setStatus(wireWorldMatrix.get(mbtn.getIdX()).get(mbtn.getIdY()));
            }
    }

    public void loadWWDatas(String fileName){
        try {
            FileInputStream f = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(f);
            wireWorldMatrix = (ArrayList<ArrayList<Integer>>) in.readObject();
            in.close();
        } catch(IOException ex) {
            ex.fillInStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.fillInStackTrace();
        }

    }

    public void saveWWDatas(String fileName)  {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(wireWorldMatrix);
            out.close();
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }

    private JPanel getWWMapPanel(Display dsp) {
        for(Component cmpnt:dsp.getContentPane().getComponents()){
            if(cmpnt.getName().equals("wireWorldMap")){
                return (JPanel)cmpnt;
            }
        }
        return null;
    }


}
