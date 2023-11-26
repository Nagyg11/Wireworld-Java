
/**
 * Ezen osztály célja, hogy függvényei a wire world szabályainak megfelelően változtassa a paraméterben kapott adattárolóban található értékeket.
 * */
public class StatusChanger {

    /**
     * Ezen függvény eggyel lépteti a paraméterben kapott adattárolót a wire world szabályai szerint.
     * @param wwDataActual az aktuális értékeket tároló adattároló.
     * @return volt-e változtatás az adatárolón.
     * */
    public boolean oneStep(WWDataActual wwDataActual){
        boolean isChanged=false;

        WWData permanent=new WWData(wwDataActual.getColumnNum(),wwDataActual.getRowNum());
        permanent.copy(wwDataActual);
        int rowNum=permanent.row;
        int columnNum=permanent.column;
        int status=0;

        for(int x=0;x<columnNum;x++){
            for (int y=0;y<rowNum;y++){
                status=nextStatus(x,y,permanent);
                if(!isChanged){
                    isChanged=(!(status==permanent.getXY(x,y)));
                }

                wwDataActual.setXY(x,y,status);
            }
        }
        return isChanged;

    }

    /**
     * @param x a vizsgált mező x koordinátája az adattárolóban.
     * @param y a vizsgált mező y koordinátája az adattárolóban.
     * @param wwData azon adattároló, amely alapján az adott mező új értéke kiszámításra kerül.
     * @return a paraméterben kapott mezőnek mi lesz a frissítés utáni állapota.
     * */
    private int nextStatus(int x, int y, WWData wwData){
        int headNum=0;
        switch (wwData.getXY(x,y)){
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                for(int deltaX=-1; deltaX<=1;deltaX++){
                    for (int deltaY=-1; deltaY<=1;deltaY++){
                        if(!(deltaX==0 && deltaY==0) && (0<=x+deltaX && x+deltaX < wwData.getColumnNum()) && (0<=y+deltaY && y+deltaY < wwData.getRowNum())){

                            if(wwData.getXY(x+deltaX,y+deltaY)==1){
                                headNum++;
                            }

                        }
                    }
                }
                if(headNum>0 && headNum<=2){
                    return 1;
                }else{
                    return 3;
                }
            default:
                return 0;
        }
    }


}
