package sf.cmd;

import java.awt.Point;
import java.util.ArrayList;
import sf.SF;
import sf.SFOriStep;
import sf.SFPoly;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToInitializePaper extends XLoggableCmd {
    // fields
    //...
    
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToInitializePaper(XApp app) {
        super(app);
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app) {
        SFCmdToInitializePaper cmd = new SFCmdToInitializePaper(app);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        if(sf.getOriStepMgr().getOrigami().isEmpty()) {
            ArrayList<SFPoly> poly = new ArrayList<>();
            int[] xs = {0, 0, 400, 400};
            int[] ys = {0, 400, 400, 0};
            int numpts = xs.length;
            SFPoly firstpaper = new SFPoly(xs, ys, numpts, true);
            poly.add(firstpaper);
            SFOriStep firststep = new SFOriStep(0, poly);
            sf.getOriStepMgr().addOriStep(firststep);
            System.out.println(sf.getOriStepMgr().getLastOriStep());
            return true;
        } else{
            return false;
        }
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append("Initialize.");
        return sb.toString();
    }
    
}
