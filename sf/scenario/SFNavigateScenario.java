package sf.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import sf.SF;
import sf.SFScene;
import sf.cmd.SFCmdToSetStartingScreenPt;
import sf.cmd.SFCmdToTranslateTo;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class SFNavigateScenario extends XScenario {
    // singleton pattern
    private static SFNavigateScenario mSingleton = null;
    public static SFNavigateScenario createSingleton(XApp app) {
        assert (SFNavigateScenario.mSingleton == null);
        SFNavigateScenario.mSingleton = new SFNavigateScenario(app);
        return SFNavigateScenario.mSingleton;
    }
    
    public static SFNavigateScenario getSingleton() {
        assert (SFNavigateScenario.mSingleton != null);
        return SFNavigateScenario.mSingleton;
    }
    
    private SFNavigateScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFNavigateScenario.PanReadyScene.createSingleton(this));
        this.addScene(SFNavigateScenario.ZoomAndRotateScene.createSingleton(this));
        this.addScene(SFNavigateScenario.PanScene.createSingleton(this));
    }

   
    public static class ZoomAndRotateScene extends SFScene {
        //singleton pattern
        private static ZoomAndRotateScene mSingleton = null;
        public static ZoomAndRotateScene createSingleton(XScenario scenario) {
            assert (ZoomAndRotateScene.mSingleton == null);
            ZoomAndRotateScene.mSingleton = new ZoomAndRotateScene(scenario);
            return ZoomAndRotateScene.mSingleton;
        }
        public static ZoomAndRotateScene getSingleton() {
            assert (ZoomAndRotateScene.mSingleton != null);
            return ZoomAndRotateScene.mSingleton;
        }
        private ZoomAndRotateScene(XScenario scenario) {
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

        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    SFCmdToZoomIn.execute(sf);
                    break;
                case KeyEvent.VK_DOWN:
                    SFCmdToZoomOut.execute(sf);
                    break;
                case KeyEvent.VK_LEFT:
                    SFCmdToRotatePaperCW45(sf);
                    break;
                case KeyEvent.VK_RIGHT:
                    SFCmdToRotatePaperCCW45(sf);
                    break;
                case KeyEvent.VK_SHIFT:
                    if(IsModeFold){
                        XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
                    } else {
                        XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
                    }
                    break;
            }
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
    
    public static class PanReadyScene extends SFScene {
        //singleton pattern
        private static PanReadyScene mSingleton = null;
        public static PanReadyScene createSingleton(XScenario scenario) {
            assert (PanReadyScene.mSingleton == null);
            PanReadyScene.mSingleton = new PanReadyScene(scenario);
            return PanReadyScene.mSingleton;
        }
        public static PanReadyScene getSingleton() {
            assert (PanReadyScene.mSingleton != null);
            return PanReadyScene.mSingleton;
        }
        private PanReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            SFCmdToSetStartingScreenPt.execute(sf, pt);
            XCmdToChangeScene.execute(sf, 
                SFNavigateScenario.PanScene.getSingleton(), 
                this.mReturnScene);
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {

        }

        @Override
        public void handleMouseRelease(MouseEvent e) {

        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_ALT:
                    if(IsModeFold){
                        XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
                    } else {
                        XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
                    }
                    break;
            }
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
    
    public static class PanScene extends SFScene {
        //singleton pattern
        private static PanScene mSingleton = null;
        public static PanScene createSingleton(XScenario scenario) {
            assert (PanScene.mSingleton == null);
            PanScene.mSingleton = new PanScene(scenario);
            return PanScene.mSingleton;
        }
        public static PanScene getSingleton() {
            assert (PanScene.mSingleton != null);
            return PanScene.mSingleton;
        }
        private PanScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            SFCmdToTranslateTo.execute(sf, pt);
//            sf.getXform().translateTo(pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            XCmdToChangeScene.execute(sf, SFNavigateScenario.PanReadyScene.getSingleton(), this.mReturnScene);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {

        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_ALT:
                    if(IsModeFold){
                        XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
                    } else {
                        XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
                    }
                    break;
            }
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
