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

public class SFFoldScenario extends XScenario {
    // singleton pattern
    private static SFFoldScenario mSingleton = null;
    public static SFFoldScenario createSingleton(XApp app) {
        assert (SFFoldScenario.mSingleton == null);
        SFFoldScenario.mSingleton = new SFFoldScenario(app);
        return SFFoldScenario.mSingleton;
    }
    
    public static SFFoldScenario getSingleton() {
        assert (SFFoldScenario.mSingleton != null);
        return SFFoldScenario.mSingleton;
    }
    
    private SFFoldScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFFoldScenario.FoldReadyScene.createSingleton(this));
        this.addScene(SFFoldScenario.SelectUpperFaceScene.createSingleton(this));
    }

    public static class FoldReadyScene extends SFScene {
        //singleton pattern
        private static FoldReadyScene mSingleton = null;
        public static FoldReadyScene createSingleton(XScenario scenario) {
            assert (FoldReadyScene.mSingleton == null);
            FoldReadyScene.mSingleton = new FoldReadyScene(scenario);
            return FoldReadyScene.mSingleton;
        }
        public static FoldReadyScene getSingleton() {
            assert (FoldReadyScene.mSingleton != null);
            return FoldReadyScene.mSingleton;
        }
        private FoldReadyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            SFCmdToUpdateFoldingline.execute(sf, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            if(SFCmdToTestValidLine.execute(sf)){
                XCmdToChangeScene.execute(sf, SFFoldScenario.SelectUpperFaceScene.getSingleton(), this.mReturnScene);
            } else {
                XCmdToDeleteFoldingline.execute(sf);
                XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
            }
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
    
    public static class SelectUpperFaceScene extends SFScene {
        //singleton pattern
        private static SelectUpperFaceScene mSingleton = null;
        public static SelectUpperFaceScene createSingleton(XScenario scenario) {
            assert (SelectUpperFaceScene.mSingleton == null);
            SelectUpperFaceScene.mSingleton = new SelectUpperFaceScene(scenario);
            return SelectUpperFaceScene.mSingleton;
        }
        public static SelectUpperFaceScene getSingleton() {
            assert (SelectUpperFaceScene.mSingleton != null);
            return SelectUpperFaceScene.mSingleton;
        }
        private SelectUpperFaceScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
            if(SFCmdToTestMouseInpaper.execute(sf, pt)){
                SFCmdToFoldUpperFace.execute(sf, pt);
                XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
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
