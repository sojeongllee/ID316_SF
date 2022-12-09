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

public class SFPaperStatusScenario extends XScenario {
    // singleton pattern
    private static SFPaperStatusScenario mSingleton = null;
    public static SFPaperStatusScenario createSingleton(XApp app) {
        assert (SFPaperStatusScenario.mSingleton == null);
        SFPaperStatusScenario.mSingleton = new SFPaperStatusScenario(app);
        return SFPaperStatusScenario.mSingleton;
    }
    
    public static SFPaperStatusScenario getSingleton() {
        assert (SFPaperStatusScenario.mSingleton != null);
        return SFPaperStatusScenario.mSingleton;
    }
    
    private SFPaperStatusScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFPaperStatusScenario.PaperStatusReadyScene.createSingleton(this));
        this.addScene(SFPaperStatusScenario.HistoryScene.createSingleton(this));
    }

    public static class PaperStatusReadyScene extends SFScene {
        //singleton pattern
        private static PaperStatusReadyScene mSingleton = null;
        public static PaperStatusReadyScene createSingleton(XScenario scenario) {
            assert (PaperStatusReadyScene.mSingleton == null);
            PaperStatusReadyScene.mSingleton = new PaperStatusReadyScene(scenario);
            return PaperStatusReadyScene.mSingleton;
        }
        public static PaperStatusReadyScene getSingleton() {
            assert (PaperStatusReadyScene.mSingleton != null);
            return PaperStatusReadyScene.mSingleton;
        }
        private PaperStatusReadyScene(XScenario scenario) {
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
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_S:
                    SFCmdToSave(sf);
                    break;
                case KeyEvent.VK_Z: ////이게되나?
                    if(code == KeyEvent.VK_SHIFT){
                        SFCmdToRedo(sf);
                    } else {
                        SFCmdToUndo(sf);
                    }
                    break;
                case KeyEvent.VK_C:
                    XCmdToChangeScene.execute(sf, SFColorScenario.ColorReadyScene.getSingleton(), this);
                    break;
            }
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_N:
                    SFCmdToSave(sf);
                    SFCmdToMakeNewSF(sf);
                    break;
                case KeyEvent.VK_H: 
                    SFCmdToOpenSFHistoryWindow(sf);
                    XCmdToChangeScene.execute(sf, SFPaperStatusScenario.HistoryScene.getSingleton(), this.mReturnScene);
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
    
    public static class HistoryScene extends SFScene {
        //singleton pattern
        private static HistoryScene mSingleton = null;
        public static HistoryScene createSingleton(XScenario scenario) {
            assert (HistoryScene.mSingleton == null);
            HistoryScene.mSingleton = new HistoryScene(scenario);
            return HistoryScene.mSingleton;
        }
        public static HistoryScene getSingleton() {
            assert (HistoryScene.mSingleton != null);
            return HistoryScene.mSingleton;
        }
        private HistoryScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            Point pt = e.getPoint();
            if(e.getClickCount()>1){
                SFCmdToOpenHistorySF(sf, pt);
            }
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
