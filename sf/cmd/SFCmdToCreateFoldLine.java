package sf.cmd;

import java.awt.Point;
import sf.SF;
import sf.SFFoldLine;
import sf.scenario.SFDefaultScenario;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToCreateFoldLine extends XLoggableCmd {
    // fields
    //...
    
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    Point mPt = null;
    private SFCmdToCreateFoldLine(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app, Point pt) {
        SFCmdToCreateFoldLine cmd = new SFCmdToCreateFoldLine(app, pt);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        SFFoldLine foldline = new SFFoldLine(this.mPt);
        SFDefaultScenario.getSingleton().setFoldLine(foldline);
        
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        //...
        return sb.toString();
    }
    
}
