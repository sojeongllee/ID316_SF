package sf.scenario;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import sf.SF;
import sf.SFCanvas2D;
import sf.SFScene;
import sf.cmd.SFCmdToCreateCurPtCurve;
import x.XApp;
import x.XCmdToChangeScene;
import x.XScenario;

public class SFDefaultScenario extends XScenario {
    // singleton pattern
    private static SFDefaultScenario mSingleton = null;
    public static SFDefaultScenario createSingleton(XApp app) {
        assert (SFDefaultScenario.mSingleton == null); // 개발자가 디버깅용으로 쓰는 코드임
        SFDefaultScenario.mSingleton = new SFDefaultScenario(app);
        return SFDefaultScenario.mSingleton;
    }
    
    public static SFDefaultScenario getSingleton() {
        assert (SFDefaultScenario.mSingleton != null);
        return SFDefaultScenario.mSingleton;
    }
    
    private SFDefaultScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFDefaultScenario.ReadyScene.createSingleton(this)); //inner class
    }

    public static class ReadyScene extends SFScene { //밖에서도 불러야함
        //singleton pattern
        private static ReadyScene mSingleton = null;
        public static ReadyScene createSingleton(XScenario scenario) {
            assert (ReadyScene.mSingleton == null);
            ReadyScene.mSingleton = new ReadyScene(scenario);
            return ReadyScene.mSingleton;
        }
        public static ReadyScene getSingleton() {
            assert (ReadyScene.mSingleton != null);
            return ReadyScene.mSingleton;
        }
        private ReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            SFCmdToCreateFoldingline.execute(sf, pt);
            XCmdToChangeScene.execute(sf, SFFoldScenario.FoldReadyScene.getSingleton(), this);
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
                case KeyEvent.VK_SPACE:
                    XCmdToFlipPaper();
                    break;
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this);
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
