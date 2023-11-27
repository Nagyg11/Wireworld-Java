package Data;

import java.util.ArrayList;

/**
 * Ezen osztály a wire world map állapotaival kompatibilsen, az adott állapotok mezők szerinti tárolására szolgál, és ezen adattároláshoz és maipulációhoz szükséges müveletek függvényeit tartalmazza.
 * */
public class WWData {

    /**
     * Ezen tagváltozó egy 2 dimenziós lista, amely arra szolgál, hogy a wire world egyes mezőinek eltárolja állapotát(0-3) a wire world map-el "komatibilis" módon.
     * */
    protected ArrayList<ArrayList<Integer>> wireWorldMatrix= new ArrayList<>();

    /**
     * Ezen tagváltozó azt tárolja hogy az adott listán belüli listák mekkora méretüek, azaz reprezentáció szerint hány oszlopból áll a mátrix.
     * */
    protected int column;

    /**
     * Ezen tagváltozó azt tárolja hogy az adott listákat tároló lista mekkora méretü, azaz reprezentáció szerint hány sorból áll a mátrix.
     * */
    protected int row;

    public WWData(){
    }


    /**
     * Ezen konstruktor a paraméterben kapott oszlop és sor számú 2 dimenziós listát hoz létre.
     * @param column oszlopk számát adja meg. Azaz hány elmeből állnak a listában szereplő listák.
     * @param row sorok számát adja meg. Hány listából áll a listákat tároló lista.
     * */
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

    /**
     * Ez a függvény a paraméterben kapott adattároló értékeit másolja az adattárolóba ami, meghívta. Lényege hogy ne referncia szeint legyen érték át adva.
     * @param wwData azon adattároló amelynek adatait másolni szeretnénk.
     * */
    public void copy(WWData wwData){
        for(int r=0;r<wwData.row;r++) {
            for (int c = 0; c < wwData.column; c++) {
                setXY(c, r, wwData.getXY(c, r));
            }
        }
    }

    /**
     * @return adattárolóban milyen érték szerepel a paraméterben kapott x,y koordinátákon.
     * */
    public int getXY(int x, int y){return wireWorldMatrix.get(y).get(x);}

    /**
     * @param x az x koordinátája, azon értéknek amelyet módosítani szeretnénk.
     * @param y az y koordinátája, azon értéknek amelyet módosítani szeretnénk.
     * @param value az érték amelyre módosítani szeretnénk a az adattárolóban szereplő értéket
     * */
    public void setXY(int x, int y,int value){wireWorldMatrix.get(y).set(x,value);}

    /**
     * @return hány oszlopból áll az adattároló.(azaz hány elmeből állnak a listában szereplő listák.)
     * */
    public int getColumnNum(){return column;}
    /**
     * @return hány sorból áll az adattároló.(azaz hány elemből áll a listákat tároló lista.)
     * */
    public int getRowNum(){return row;}
}
