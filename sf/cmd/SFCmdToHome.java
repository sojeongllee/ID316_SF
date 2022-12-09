package sf.cmd;

import java.awt.geom.AffineTransform;
import sf.SF;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToHome extends XLoggableCmd {
    // fields
    private AffineTransform mXformBefore = null;
    private AffineTransform mXformAfter = null;
    
    // private contructor
    // private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToHome(XApp app) {
        super(app);
    }
    
    // SFCmdToIncreaseStrokeWidthForCurPtCurve.execute(sf, ...)
    // public static boolean execute(XApp app, ...) {
    public static boolean execute(XApp app) {
        SFCmdToHome cmd = new SFCmdToHome(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        this.mXformBefore = new AffineTransform(
            sf.getXform().getCurXformFromWorldToScreen());
        sf.getXform().home();
        this.mXformAfter = sf.getXform().getCurXformFromWorldToScreen();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mXformBefore).append("\t");
        sb.append(this.mXformAfter);
        return sb.toString();
    }
    
}
