package sf;

import javax.swing.JFrame;
import x.XApp;
import x.XLogMgr;
import x.XScenarioMgr;

public class SF extends XApp {
    private XScenarioMgr mScenarioMgr = null;
    @Override
    public XScenarioMgr getScenarioMgr() {
        return this.mScenarioMgr;
    }
    
    private XLogMgr mLogMgr = null;
    @Override
    public XLogMgr getLogMgr() {
        return this.mLogMgr;
    }
    
    // fields
    private JFrame mFrame = null;
    private SFCanvas2D mCanvas2D = null;
    public SFCanvas2D getCanvas2D() {
        return this.mCanvas2D;
    }
    
    private SFPenMarkMgr mSFPenMarkMgr = null;
    public SFPenMarkMgr getSFPenMarkMgr() {
        return this.mSFPenMarkMgr;
    }

    
    private SFXform mXform = null;
    public SFXform getXform() {
        return this.mXform;
    }
    
    private SFColorChooser mColorChooser = null;
    public SFColorChooser getColorChooser() {
        return this.mColorChooser;
    }
    

    
    private SFEventListener mEventListener = null;
    private SFPtCurveMgr mPtCurveMgr = null;
    public SFPtCurveMgr getSFPtCurveMgr() {
        return this.mPtCurveMgr;
    }
    
    // constructor
    public SF() {
        this.mFrame = new JFrame("SnowFlake");
        this.mCanvas2D = new SFCanvas2D(this);
        this.mXform = new SFXform();
        this.mColorChooser = new SFColorChooser();
        this.mEventListener = new SFEventListener(this);
        this.mSFPenMarkMgr = new SFPenMarkMgr();
        this.mPtCurveMgr = new SFPtCurveMgr(this);
        this.mScenarioMgr = new SFScenarioMgr(this);
        this.mLogMgr = new XLogMgr();
        this.mLogMgr.setPrintOn(true);
        
        // connect event listeners
        this.mCanvas2D.addMouseListener(this.mEventListener);
        this.mCanvas2D.addMouseMotionListener(this.mEventListener);
        this.mCanvas2D.setFocusable(true);
        this.mCanvas2D.addKeyListener(this.mEventListener);
        
        // build and show visual components
        this.mFrame.add(this.mCanvas2D);
        this.mFrame.setVisible(true);
        this.mFrame.setSize(800, 600);
        this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
         // create a SF instance
         new SF();
    } 
}