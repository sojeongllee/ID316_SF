package sf.cmd;

import java.awt.Point;
import sf.SF;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToTranslateTo extends XLoggableCmd {
    // fields
    //...
    Point mPt = null;
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToTranslateTo(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app, Point pt) {
        SFCmdToTranslateTo cmd = new SFCmdToTranslateTo(app, pt);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        //...
        sf.getXform().translateTo(mPt);
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
