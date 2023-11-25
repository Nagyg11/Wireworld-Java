public class StatusChanger {

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
                    isChanged=!(status==permanent.getXY(x,y));
                }

                wwDataActual.setXY(x,y,status);
            }
        }
        return isChanged;

    }

    public int nextStatus(int x, int y, WWData permanent){
        int headNum=0;
        switch (permanent.getXY(x,y)){
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                for(int deltaX=-1; deltaX<=1;deltaX++){
                    for (int deltaY=-1; deltaY<=1;deltaY++){
                        if(!(deltaX==0 && deltaY==0) && (0<=x+deltaX && x+deltaX < permanent.getColumnNum()) && (0<=y+deltaY && y+deltaY < permanent.getRowNum())){
                            //System.out.println(x+deltaX+" "+y+deltaY);
                            if(permanent.getXY(x+deltaX,y+deltaY)==1){
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
