package Control;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Ezen osztály a felugró ablalkok megvalósítását tartalmazza.
 * */
public class SelectorDialog {

    private JComboBox cb;

    /**
     * Ezen tagváltozó a hivó szülő elemét tárolja.
     * */
    private Component cmpnt;

    /**
     * Ezen tagváltozó azt tárolja, hogy hány soros legyen a felugró ablakban megadott adat.
     * */
    private int row;

    /**
     * Ezen tagváltozó azt tárolja, hogy hány oszlopos legyen a felugró ablakban megadott adat.
     * */
    private int column;

    /**
     * Ezen tagváltozó azt tárolja, hogy mely fájlból olvasson be fájlt, majd a controller.
     * */
    private String loadFileName;

    /**
     * Ezen tagváltozó azt tárolja, hogy mely fájlba mentse, majd a controller az adattárolót.
     * */
    private String saveName;

    /**
     * Ezen tagváltozó tárolja, hogy milyen sebességre állítsa a controller a sebesség értéket.
     * */
    private double speed;

    public SelectorDialog(Component cmpnt){
        this.cmpnt=cmpnt;
    }

    public int getRow(){return row;}
    public int getColumn(){return column;}
    public String getLoadFileName(){return loadFileName;}

    public String getSaveName(){return saveName;}
    public double getSpeed(){return speed;}

    /**
     * Új wire world map létrehozásához felugró ablakot jelnít meg, amelyben ki lehet választani a map adatait.
     * @return választott-e a felhasználó, az ok gomb lenyomásával?
     * */
    public boolean openNew(){
        String[] selction={"100 X 50","150 X 70","90 X 45","55 X 27","10 X 5"};
        cb=new JComboBox(selction);
        Object[] selObj={"Kérem válasszon map méretet!",cb};

        int out=JOptionPane.showConfirmDialog(cmpnt,selObj,"Új map",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            column=Integer.parseInt(cb.getSelectedItem().toString().split(" X ")[0]);
            row=Integer.parseInt(cb.getSelectedItem().toString().split(" X ")[1]);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Wire world map betöltéséhez felugró ablakot jelnít meg, amelyben ki lehet választani mely mentett mapot szeretnénk betölteni.
     * @return választott-e a felhasználó, az ok gomb lenyomásával?
     * */
    public boolean openLoad(File savePlace){
        ArrayList<String> filesName=new ArrayList<>();
        for(int i=0; i<savePlace.listFiles().length; i++){
            filesName.add(savePlace.listFiles()[i].getName());
        }

        cb=new JComboBox(filesName.toArray());
        Object[] selObj={"Válasszon a mentett map-ok közül!",cb};

        int out=JOptionPane.showConfirmDialog(cmpnt,selObj,"Map betöltés",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            loadFileName=cb.getSelectedItem().toString();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Wire world map mentéséhez felugró ablakot jelnít meg, amelyben ki lehet választani meilyen néven szeretnénk menteni az adattárolót.
     * @return választott-e a felhasználó, az ok gomb lenyomásával?
     * */
    public boolean selectSaveName(){
        JTextField jtx=new JTextField();
        Object[] selObj={"Milyen néven mentődjön a map?",jtx};

        int out=JOptionPane.showConfirmDialog(cmpnt,selObj,"Map mentés",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            saveName= jtx.getText();
            return true;
        }else{
            return false;
        }
    }

    /**
     * A frissítés sebességének megadaására szolgáló felugró ablakot jelenít meg.
     * @return választott-e megfelelő értéket a felhasználó?
     * */
    public boolean selectSpeed(WWControl wwc){
        JTextField jtx=new JTextField(20);
        Object[] selObj={"Jelenegleg beálított érték: "+wwc.getWaitTime()+" mp"+"\n"+"Sebbeség beállítása (másodpercben adható meg, tizedesjegyeket ponttal válassza el):",jtx};
        int out=JOptionPane.showConfirmDialog(cmpnt,selObj,"Sebesség",JOptionPane.OK_CANCEL_OPTION);
        if(out==JOptionPane.OK_OPTION){
            try {
                speed = Double.parseDouble(jtx.getText());
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(cmpnt,"NEM MEGFELELŐ ÉRTÉKET ADOTT MEG!"+"\n"+"Csak ponttal elválasztott egész/tört számot adhat meg!");
                return false;
            }
            if(speed<=0){
                JOptionPane.showMessageDialog(cmpnt,"NEM MEGFELELŐ ÉRTÉKET ADOTT MEG!"+"\n"+"0 vagy annál kisebb értéket kérem ne adjon meg mivel az nem értelmezhető időmérésnél.");
                return false;
            }
            return true;
        }else{
            return false;
        }
    }

}
