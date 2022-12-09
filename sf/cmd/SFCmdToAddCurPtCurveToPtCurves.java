package sf.cmd;

import sf.SF;
import sf.SFPtCurve;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToAddCurPtCurveToPtCurves extends XLoggableCmd {
    // fields
    private int mNumOfPtCurvesBefore = Integer.MIN_VALUE;
    private int mNumOfPtCurvesAfter = Integer.MIN_VALUE;
    
    // private contructor
    // private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToAddCurPtCurveToPtCurves(XApp app) {
        super(app);
    }
    
    // SFCmdToIncreaseStrokeWidthForCurPtCurve.execute(sf, ...)
    // public static boolean execute(XApp app, ...) {
    public static boolean execute(XApp app) {
        SFCmdToAddCurPtCurveToPtCurves cmd = 
            new SFCmdToAddCurPtCurveToPtCurves(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        SFPtCurve curPtCurve = sf.getSFPtCurveMgr().getCurPtCurve();
        if (curPtCurve.getPts().size() >= 2) {
            this.mNumOfPtCurvesBefore = 
                sf.getSFPtCurveMgr().getPtCurves().size();
            sf.getSFPtCurveMgr().getPtCurves().add(curPtCurve);
            this.mNumOfPtCurvesAfter =
                sf.getSFPtCurveMgr().getPtCurves().size();
//            sf.getPtCurveMgr().setCurPtCurve(null);
            return true;
        } else {
//            sf.getPtCurveMgr().setCurPtCurve(null);
            return false;
        }
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mNumOfPtCurvesBefore).append("\t");
        sb.append(this.mNumOfPtCurvesAfter);
        return sb.toString();
    }
    
}
