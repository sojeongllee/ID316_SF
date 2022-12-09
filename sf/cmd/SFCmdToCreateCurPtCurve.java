package sf.cmd;

import java.awt.Point;
import java.awt.geom.Point2D;
import sf.SF;
import sf.SFPtCurve;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToCreateCurPtCurve extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point2D.Double mWorldPt = null;
    
    //private constructor
    private SFCmdToCreateCurPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        SFCmdToCreateCurPtCurve cmd = new SFCmdToCreateCurPtCurve(app, pt);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        this.mWorldPt = sf.getXform().calcPtFromScreenToWorld(this.mScreenPt);
        SFPtCurve curPtCurve = new SFPtCurve(this.mWorldPt, 
            sf.getCanvas2D().getCurColorForPtCurve(), 
                sf.getCanvas2D().getCurStrokeForPtCurve()); 
        sf.getSFPtCurveMgr().setCurPtCurves(curPtCurve);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mScreenPt).append("\t");
        sb.append(this.mWorldPt);
        return sb.toString();
    }
    
}
