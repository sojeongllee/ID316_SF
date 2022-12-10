package sf.cmd;

import java.awt.Point;
import java.awt.geom.Path2D;
import sf.SF;
import sf.scenario.SFDefaultScenario;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToUpdateFoldLine extends XLoggableCmd {
    // fields
    //...
    Point mPt = null;
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToUpdateFoldLine(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app, Point pt) {
        SFCmdToUpdateFoldLine cmd = new SFCmdToUpdateFoldLine(app, pt);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        SFDefaultScenario.getSingleton().getFoldLine().update(mPt);
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
