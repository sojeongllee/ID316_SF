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

public class SFColorScenario extends XScenario {
    // singleton pattern
    private static SFColorScenario mSingleton = null;
    public static SFColorScenario createSingleton(XApp app) {
        assert (SFColorScenario.mSingleton == null);
        SFColorScenario.mSingleton = new SFColorScenario(app);
        return SFColorScenario.mSingleton;
    }
    
    public static SFColorScenario getSingleton() {
        assert (SFColorScenario.mSingleton != null);
        return SFColorScenario.mSingleton;
    }
    
    private SFColorScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFColorScenario.ColorReadyScene.createSingleton(this));
        this.addScene(SFColorScenario.ChangeColorScene.createSingleton(this));
    }

    public static class ColorReadyScene extends SFScene {
        //singleton pattern
        private static ColorReadyScene mSingleton = null;
        public static ColorReadyScene createSingleton(XScenario scenario) {
            assert (ColorReadyScene.mSingleton == null);
            ColorReadyScene.mSingleton = new ColorReadyScene(scenario);
            return ColorReadyScene.mSingleton;
        }
        public static ColorReadyScene getSingleton() {
            assert (ColorReadyScene.mSingleton != null);
            return ColorReadyScene.mSingleton;
        }
        private ColorReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            XCmdToChangeScene.execute(sf, SFColorScenario.ChangeColorScene.getSingleton(), mReturnScene);
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
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(sf, 
                        SFPaperStatusScenario.PaperStatusReadyScene.getSingleton(), 
                        this.mReturnScene);
                    break;
                case KeyEvent.VK_CONTROL:
                    if(IsModeFold){
                        XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
                    } else {
                        XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
                    }
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
    
    public static class ChangeColorScene extends SFScene {
        //singleton pattern
        private static ChangeColorScene mSingleton = null;
        public static ChangeColorScene createSingleton(XScenario scenario) {
            assert (ChangeColorScene.mSingleton == null);
            ChangeColorScene.mSingleton = new ChangeColorScene(scenario);
            return ChangeColorScene.mSingleton;
        }
        public static ChangeColorScene getSingleton() {
            assert (ChangeColorScene.mSingleton != null);
            return ChangeColorScene.mSingleton;
        }
        private ChangeColorScene(XScenario scenario) {
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
            Point pt = e.getPoint();
            SFCmdToChangePaperColor(sf, pt);
            XCmdToChangeScene.execute(sf, SFColorScenario.ColorReadyScene.getSingleton(), this.mReturnScene);
        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(sf, 
                        SFPaperStatusScenario.PaperStatusReadyScene.getSingleton(), 
                        this.mReturnScene);
                    break;
                case KeyEvent.VK_CONTROL:
                    if(IsModeFold){
                        XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
                    } else {
                        XCmdToChangeScene.execute(sf, SFCutScenario.CutReadyScene.getSingleton(), this.mReturnScene);
                    }
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
