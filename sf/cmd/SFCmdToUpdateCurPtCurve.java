package sf.cmd;

import java.awt.Point;
import java.awt.geom.Point2D;
import sf.SF;
import sf.SFPtCurve;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToUpdateCurPtCurve extends XLoggableCmd {
    // fields
    private Point mScreenPt = null;
    private Point2D.Double mWorldPt = null;
    
    // private contructor
    private SFCmdToUpdateCurPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    // SFCmdToCreateCurPtCurve.execute(sf, pt)
    public static boolean execute(XApp app, Point pt) {
        SFCmdToUpdateCurPtCurve cmd = new SFCmdToUpdateCurPtCurve(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        SFPtCurve curPtCurve = sf.getSFPtCurveMgr().getCurPtCurve();
        
        int size = curPtCurve.getPts().size();
        Point2D.Double lastWorldPt = curPtCurve.getPts().get(size - 1);
        Point lastScreenPt = sf.getXform().calcPtFromWorldToScreen(
            lastWorldPt);
        if (this.mScreenPt.distance(lastScreenPt) < 
            SFPtCurve.MIN_DIST_BTWN_PTS) {
            return false;
        }
        this.mWorldPt = sf.getXform().calcPtFromScreenToWorld(this.mScreenPt);
        curPtCurve.addPt(this.mWorldPt);
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
