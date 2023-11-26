import javax.swing.*;
import java.awt.*;

/**
* A MapButton a JButton-ból leszármazott osztály. A célja, hogy a Wire World map-en a különböző állapotokat megjelenítse, és a gombra nyomással az állapotok között váltani lehsessen. Az osztályban tárolva van a gombok map-en található koordinátája és állapota.
* */
public class MapButton extends JButton {

    /**
     * Ezen tagváltozó tárolja az addot gomb wire world map-en betöltött x koordinátáját.
     * */
    private int idX;

    /**
     * Ezen tagváltozó tárolja az addot gomb wire world map-en betöltött y koordinátáját.
     * */
    private int idY;

    /**
     * Ezen tagváltozó tárolja az addot gomb állapotát(0-3).
     * */
    private int status;

    /**
    * A setStatus függvényel a gombokat a különböző állapotokra lehet állítani.
    * */
    public void setStatus(int t){
        this.status=t;
        switch(t){
            case 0:
                setBackground(Color.BLACK);
                break;
            case 1:
                setBackground(Color.BLUE);
                break;
            case 2:
                setBackground(Color.RED);
                break;
            case 3:
                setBackground(Color.YELLOW);
                break;
            default:
                throw new IndexOutOfBoundsException("Nem letezo statuszt valasztott a mezonek.");
        }
    }

    /**
     * @return a gomb aktuális állapotával.
     * */
    public int getStatus(){return status;}

    /**
     * A setClearStatus függvény segítségével az adott, gomb álapotát üresre állítjuk.(Azaz álapota 0)
     * */
    public void setClearStatus(){
        setStatus(0);
    }

    /**
     * A changeBetweenStatus függvény meghívásával a gomb állapota a nem üres állapotok között sorban változik körkörösen.
     * */
    public void changeBetweenStatus(){
        if(status==0){
            setStatus(3);
        }else{
            setStatus((status%3)+1);
        }
    }

    /**
     * A setHoverStatus függvény 3-as állapotúra állítja gombot. (Azaz vezetékké)
     * */
    public void setHoverStatus(){
        setStatus(3);
    }

    /**
     * A setIdX függvény segítségével a gomb Wire World map-en található vízszíntes koordinátáját lehet állítani.
     * @param value az új x koordináta.
     * */
    public void setIdX(int value){this.idX=value;}
    /**
     * @return a gomb Wire World map-en található vízszíntes(x) koordinátája.
     * */
    public int getIdX(){return idX;}


    /**
     * A setIdY függvény segítségével a gomb Wire World map-en található függőleges koordinátáját lehet állítani.
     * @param value az új y koordináta.
     * */
    public void setIdY(int value){this.idY=value;}

    /**
     * @return a gomb Wire World map-en található fűggőleges(y) koordinátája.
     * */
    public int getIdY(){return idY;}


}
