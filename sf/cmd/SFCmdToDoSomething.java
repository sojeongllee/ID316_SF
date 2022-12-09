package sf.cmd;

import java.awt.Point;
import sf.SF;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToDoSomething extends XLoggableCmd {
    // fields
    //...
    
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToDoSomething(XApp app) {
        super(app);
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app) {
        SFCmdToDoSomething cmd = new SFCmdToDoSomething(app);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        //...
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
