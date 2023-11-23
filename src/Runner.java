import java.sql.Time;

public class Runner extends Thread {

    WWControl wwc;
    boolean wwLoop=false;
    long startTime=System.currentTimeMillis();

    public Runner(WWControl wwControl){
        this.wwc=wwControl;
    }
    public void run(){
        wwc.updateWireWorldMatrix();
        long deltaTime=600000000;
        long currentTime;
        while(wwLoop){
            currentTime=System.currentTimeMillis();
            deltaTime+=(currentTime-startTime);
            if(deltaTime/1000>1000000) {
                oneStep(wwc.wwDataActual);
                wwc.updateWWMap();
                startTime=currentTime;
                deltaTime=0;
            }
        }
    }

    public void oneStep(WWDataActual wwDataActual){
        WWData permanent=new WWData(wwDataActual.getColumnNum(),wwDataActual.getRowNum());
        permanent.copy(wwDataActual);
        int rowNum=permanent.row;
        int columnNum=permanent.column;

        for(int x=0;x<columnNum;x++){
            for (int y=0;y<rowNum;y++){
                wwDataActual.setXY(x,y,nextStatus(x,y,permanent));
            }
        }

    }

    public int nextStatus(int x, int y, WWData permanent){
        int headNum=0;

        if(permanent.getXY(x,y)==0){
            return 0;
        } else if(permanent.getXY(x,y)==3){
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
        }else if(permanent.getXY(x,y)==2){
           return 3;

        }else if(permanent.getXY(x,y)==1){
            return 2;
        }
        return 0;
    }

    public void setWWLoop(boolean value){
        wwLoop=value;
    }

    public boolean getWWLoop(){
        return wwLoop;
    }

}
