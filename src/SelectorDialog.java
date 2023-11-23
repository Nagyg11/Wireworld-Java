import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class SelectorDialog {
    JComboBox cb;

    Component cmpnt;
    int row;
    int column;
    String loadFileName;
    String saveName;

    public SelectorDialog(Component cmpnt){
        this.cmpnt=cmpnt;
    }

    public int getRow(){return row;}
    public int getColumn(){return column;}
    public String getLoadFileName(){return loadFileName;}

    public String getSaveName(){return saveName;}

    public boolean openNew(){
        String selction[]={"100 X 50","150 X 70","90 X 40","80 X 35","55 X 27","10 X 5"};
        cb=new JComboBox(selction);
        int out=JOptionPane.showConfirmDialog(cmpnt,cb,"Tábla méret választás.",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            column=Integer.parseInt(cb.getSelectedItem().toString().split(" X ")[0]);
            row=Integer.parseInt(cb.getSelectedItem().toString().split(" X ")[1]);
            return true;
        }else{
            return false;
        }
    }

    public boolean openLoad(File savePlace){
        ArrayList<String> filesName=new ArrayList<>();
        for(int i=0; i<savePlace.listFiles().length; i++){
            filesName.add(savePlace.listFiles()[i].getName());
        }

        cb=new JComboBox(filesName.toArray());
        int out=JOptionPane.showConfirmDialog(cmpnt,cb,"Mentett tábla választás.",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            loadFileName=cb.getSelectedItem().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean selectSaveName(){
        JTextField jtx=new JTextField();
        int out=JOptionPane.showConfirmDialog(cmpnt,jtx,"Milyen néven mentődjön a map?",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            saveName= jtx.getText();
            return true;
        }else{
            return false;
        }
    }

}
