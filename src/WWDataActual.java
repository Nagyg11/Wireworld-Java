import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class WWDataActual extends WWData{
    public WWDataActual(){
        super();
    }

    public WWDataActual(int column, int row){
        super(column,row);
    }

    public void loadWWDataActuals(String filePath){
        try {
            FileInputStream f = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(f);
            wireWorldMatrix = (ArrayList<ArrayList<Integer>>) in.readObject();
            in.close();
            row=wireWorldMatrix.size();
            column=wireWorldMatrix.get(0).size();
        } catch(IOException ex) {
            ex.fillInStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.fillInStackTrace();
        }

    }

    public void saveWWDataActual(String filePath)  {
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(wireWorldMatrix);
            out.close();
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }


    public void resetWireWorldMatrix() {
        for (int x=0;x<column;x++){
            for (int y=0;y<row;y++){
                setXY(x,y,0);
            }
        }
    }
}
