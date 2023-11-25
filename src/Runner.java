import java.sql.Time;

public class Runner extends Thread {

    private WWControl wwc;
    private WWDataActual wwDataActual;
    private boolean wwLoop=false;
    private StatusChanger statusChanger=new StatusChanger();

    private static double waitTime=0.1;
    private long startTime=System.currentTimeMillis();

    public Runner(WWControl wwControl, WWDataActual wwdatAct){
        this.wwc=wwControl;
        this.wwDataActual=wwdatAct;
    }
    public void run(){
        wwc.updateWireWorldMatrix();
        long deltaTime=0;
        long currentTime;
        while(wwLoop){
            currentTime=System.currentTimeMillis();
            deltaTime+=(currentTime-startTime);
            if(((double)(deltaTime))/1000>waitTime) {
                if(!statusChanger.oneStep(wwDataActual)){
                    wwLoop=false;
                }
                wwc.updateWWMap();
                deltaTime=0;
            }
            startTime=currentTime;
        }
    }


    public void setWaitTime(double value){waitTime=value;}

    public double getWaitTime(){return waitTime;}

    public void setWWLoop(boolean value){
        wwLoop=value;
    }

    public boolean getWWLoop(){
        return wwLoop;
    }

}
