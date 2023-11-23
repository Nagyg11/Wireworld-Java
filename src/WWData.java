import java.io.File;
import java.util.ArrayList;

public class WWData {
    ArrayList<ArrayList<Integer>> wireWorldMatrix= new ArrayList<>();
    static File savePlace=new File(System.getProperty("user.dir")+File.separator+"maps");
    int column;
    int row;

    public WWData(){
    }

    public WWData(int column, int row){

        this.column=column;
        this.row=row;
        for(int r=0;r<row;r++){
            wireWorldMatrix.add(new ArrayList<>());
            for (int c=0;c<column;c++){
                wireWorldMatrix.get(r).add(0);
            }
        }
    }

    public void copy(WWData wwData){
        //System.out.println(this.column+" "+this.row);
        //System.out.println(wwData.column+" "+wwData.row);
        for(int r=0;r<wwData.row;r++) {
            for (int c = 0; c < wwData.column; c++) {
                setXY(c, r, wwData.getXY(c, r));
            }
        }
    }

    protected int getXY(int x, int y){return wireWorldMatrix.get(y).get(x);}
    protected void setXY(int x, int y,int value){wireWorldMatrix.get(y).set(x,value);}

    public int getColumnNum(){return column;}
    public int getRowNum(){return row;}

    /*public ArrayList<ArrayList<Integer>> getWireWorldMatrix(){
        return wireWorldMatrix;
    }*/
    public File getSavePlace(){return savePlace;}
}
