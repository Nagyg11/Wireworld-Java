import java.io.*;
import java.util.ArrayList;

/**
 * Ezen osztály a WWData osztály leszármazottja, mivel ebben is mezők állapotait lehet tárlni, viszont specifikusan mindig az aktuális állapotot, amelye állaptokon különböző extra funkciókat is el kellhet végezni.
 * */
public class WWDataActual extends WWData{
    public WWDataActual(){
        super();
    }

    /**
     * @param column oszlopk számát adja meg. Azaz hány elmeből álljanak a listában szereplő listák.
     * @param row sorok számát adja meg. Hány listából álljon a listákat tároló lista.
     * Ezen konstruktor a paraméterben kapott oszlop és sor számot adja tovább az ős osztály konstruktorának.
     * */
    public WWDataActual(int column, int row){
        super(column,row);
    }

    /**
     * @param filePath a megadott fájl elrési útból szerializált adatokat olvas be az adatárolóba.
     * */
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


    /**
     * @param filePath a megadott fájl elrési útu fájlba szerializáltan ír ki adatokat az adattárolóból.(2d-s lista)
     * */
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


    /**
     * Az adattárolóban lévő összes állapot értéket 0-ra(üresre) állítja.
     * */
    public void resetWireWorldMatrix() {
        for (int x=0;x<column;x++){
            for (int y=0;y<row;y++){
                setXY(x,y,0);
            }
        }
    }
}
