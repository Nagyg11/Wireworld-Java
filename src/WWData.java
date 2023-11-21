import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class WWData {
    ArrayList<ArrayList<Integer>> wireWorldMatrix= new ArrayList<>();

    public WWData(){}

    public WWData(int column, int row){
        for(int r=0;r<row;r++){
            wireWorldMatrix.add(new ArrayList<>());
            for (int c=0;c<column;c++){
                wireWorldMatrix.get(r).add(0);
            }
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

    public ArrayList<ArrayList<Integer>> getWireWorldMatrix(){
        return wireWorldMatrix;
    }


}
