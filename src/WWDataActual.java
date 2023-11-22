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

    public void loadWWDataActuals(String fileName){
        try {
            FileInputStream f = new FileInputStream(savePlace+File.separator+fileName);
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

    public void saveWWDataActual(String fileName)  {
        try {
            FileOutputStream f = new FileOutputStream(savePlace+File.separator+fileName);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(wireWorldMatrix);
            out.close();
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }

    public void oneStep(){
        WWData permanent=new WWData(this.column,this.row);
        permanent.copy(this);
        int rowNum=permanent.row;
        int columnNum=permanent.column;

        for(int x=0;x<columnNum;x++){
            for (int y=0;y<rowNum;y++){
                if(permanent.getXY(x,y)==1){
                    for(int deltaX=-1; deltaX<=1;deltaX++){
                        for (int deltaY=-1; deltaY<=1;deltaY++){
                            if(!(deltaX==0 && deltaY==0) && (0<=x+deltaX && x+deltaX < columnNum) && (0<=y+deltaY && y+deltaY < rowNum)){
                                //System.out.println(x+deltaX+" "+y+deltaY);
                                if(permanent.getXY(x+deltaX,y+deltaY)==3){
                                    setXY(x+deltaX,y+deltaY,1);
                                }
                            }
                        }
                    }
                    setXY(x,y,2);
                }else if(permanent.getXY(x,y)==2){
                    setXY(x,y,3);

                }
            }
        }

    }


}
