import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A MouseAdapter osztályból származó MapButtonMouseAction osztály a wire world map-en végrehajtott egér eseényekt figyeli és az adott események esetén végrehajtatja a megfelelő függvényeket.
 * */
public class MapButtonMouseAction extends MouseAdapter {


    /**
     * Ezen tagváltozó true értéket vesz fel ha a ball egér gomb le van nyomva a wire world map felett.
     * */
    private boolean pressLeft=false;
    /**
     * Ezen tagváltozó true értéket vesz fel ha a jobb egér gomb le van nyomva a wire world map felett.
     * */
    private boolean pressRight=false;


    /**
     * Ezen függvény beállítja a tagváltozókat annak megfelelően, melyik egér gomb van lenyomva.
     * */
    @Override
    public void mousePressed(MouseEvent me) {
        MapButton btn = (MapButton) me.getSource();

        if(me.getButton()==MouseEvent.BUTTON1){
            pressLeft=true;
            btn.changeBetweenStatus();
        }else if(me.getButton()==MouseEvent.BUTTON3){
            pressRight=true;
            btn.setClearStatus();
        }

    }


    /**
     * Ezen függvény arra szolgál, hogy ha egyik egér gomb sincs már lenyomva, akkor a tagváltozókat visszaállítsa false értékre.
     * */
    @Override
    public void mouseReleased(MouseEvent me) {
        pressLeft=false;
        pressRight=false;
    }


    /**
     * Ezen függvény akkor hajtódik végre, ha az adott a gomb felé került-e az egér. A függvényben viszgálva van, hogy lenyomott állapotban van-e az egér, mert csak ebben az esetben hajtódik végre a gomb állapot változattaása.
     * */
    @Override
    public void mouseEntered(MouseEvent me) {
        MapButton btn = (MapButton) me.getSource();
        if(pressLeft) {
            btn.setHoverStatus();
        }else if(pressRight){
            btn.setClearStatus();
        }
    }

}
