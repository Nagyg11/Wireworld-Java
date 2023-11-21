public class Main {
    public static void main(String[] args) {
        Display dsp=new Display();
        systemData sD=new systemData();
        dsp.newWWMap();
        dsp.setVisible(true);
        //sD.loadWWDatas("test");
        sD.updateWWMap(dsp);
        //sD.saveWWDatas("test");
    }
}