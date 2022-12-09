package sf.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import sf.SF;
import sf.SFScene;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class SFCutScenario extends XScenario {
    // singleton pattern
    private static SFCutScenario mSingleton = null;
    public static SFCutScenario createSingleton(XApp app) {
        assert (SFCutScenario.mSingleton == null);
        SFCutScenario.mSingleton = new SFCutScenario(app);
        return SFCutScenario.mSingleton;
    }
    
    public static SFCutScenario getSingleton() {
        assert (SFCutScenario.mSingleton != null);
        return SFCutScenario.mSingleton;
    }
    
    private SFCutScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFCutScenario.CutReadyScene.createSingleton(this));
        this.addScene(SFCutScenario.CutScene.createSingleton(this));
        this.addScene(SFCutScenario.SelectRemainedFaceScene.createSingleton(this));
    }

    public static class CutReadyScene extends SFScene {
        //singleton pattern
        private static CutReadyScene mSingleton = null;
        public static CutReadyScene createSingleton(XScenario scenario) {
            assert (CutReadyScene.mSingleton == null);
            CutReadyScene.mSingleton = new CutReadyScene(scenario);
            return CutReadyScene.mSingleton;
        }
        public static CutReadyScene getSingleton() {
            assert (CutReadyScene.mSingleton != null);
            return CutReadyScene.mSingleton;
        }
        private CutReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            XCmdToChangeScene.execute(sf, SFCutScenario.CutScene.getSingleton(), this.mReturnScene);
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {

        }

        @Override
        public void handleMouseRelease(MouseEvent e) {

        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_SHIFT:
                    XCmdToChangeScene.execute(sf, 
                        SFNavigateScenario.ZoomAndRotateScene.getSingleton(), 
                        this);
                    break;
                case KeyEvent.VK_ALT:
                    XCmdToChangeScene.execute(sf, 
                        SFNavigateScenario.PanReadyScene.getSingleton(), 
                        this);
                    break;
                case KeyEvent.VK_CONTROL:
                    XCmdToChangeScene.execute(sf, SFPaperStatusScenario.PaperStatusReadyScene.getSingleton(), this);
                    break;
                case KeyEvent.VK_ENTER:
                    if(SFCmdToTestClosedLine){
                        XCmdToChangeScene.execute(sf, SFCutScenario.SelectRemainedFaceScene.getSingleton(), this.mReturnScene);
                    } else{
                        ////
                    }
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            
        }

        @Override
        public void updateSupportObjects() {

        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {

        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
    public static class CutScene extends SFScene {
        //singleton pattern
        private static CutScene mSingleton = null;
        public static CutScene createSingleton(XScenario scenario) {
            assert (CutScene.mSingleton == null);
            CutScene.mSingleton = new CutScene(scenario);
            return CutScene.mSingleton;
        }
        public static CutScene getSingleton() {
            assert (CutScene.mSingleton != null);
            return CutScene.mSingleton;
        }
        private CutScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            SFCmdToDrawCuttingLine(sf, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            
        }

        @Override
        public void updateSupportObjects() {

        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {

        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
    public static class SelectRemainedFaceScene extends SFScene {
        //singleton pattern
        private static SelectRemainedFaceScene mSingleton = null;
        public static SelectRemainedFaceScene createSingleton(XScenario scenario) {
            assert (SelectRemainedFaceScene.mSingleton == null);
            SelectRemainedFaceScene.mSingleton = new SelectRemainedFaceScene(scenario);
            return SelectRemainedFaceScene.mSingleton;
        }
        public static SelectRemainedFaceScene getSingleton() {
            assert (SelectRemainedFaceScene.mSingleton != null);
            return SelectRemainedFaceScene.mSingleton;
        }
        private SelectRemainedFaceScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {

        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            SFCmdToCutPaper(sf);
            XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            
        }

        @Override
        public void updateSupportObjects() {

        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {

        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
}
