package sf.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import sf.SF;
import sf.SFFoldLine;
import sf.SFPtCurve;
import sf.SFScene;
import sf.cmd.SFCmdToFoldPaper;
import sf.cmd.SFCmdToUpdateCurPtCurve;
import sf.cmd.SFCmdToUpdateFoldLine;
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
            SFCmdToUpdateCurPtCurve.execute(sf, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            Point pt = e.getPoint();
//            if(SFCmdToTestValidLine.execute(sf)){
//                XCmdToChangeScene.execute(sf, SFFoldScenario.SelectUpperFaceScene.getSingleton(), this.mReturnScene);
//            } else {
//                SFCmdToDeleteFoldingline.execute(sf);
                SFCmdToUpdateFoldLine.execute(sf, pt);
                SFFoldLine foldline = SFDefaultScenario.getSingleton().getFoldLine();
                foldline.setEndPt(pt);
                SFCmdToFoldPaper.execute(sf, foldline);
                XCmdToChangeScene.execute(sf, SFDefaultScenario.ReadyScene.getSingleton(), null);
//            }
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
            SF sf = (SF) this.mScenario.getApp();
            SFFoldScenario scenario = (SFFoldScenario) this.mScenario;
            scenario.drawFoldLine(g2);
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
            SF sf = (SF) this.mScenario.getApp();
            
            
        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
    public void drawFoldLine(Graphics2D g2) {
        SF sf = (SF) super.getApp();
        if(SFDefaultScenario.getSingleton().getFoldLine() != null) {
            Path2D.Double path = new Path2D.Double();
            if(sf.getSFPenMarkMgr().getLastPenMark() != null){
            Point firstpt = sf.getSFPenMarkMgr().getLastPenMark().getFirstPt();
            Point lastpt = sf.getSFPenMarkMgr().getLastPenMark().getLastPt();
            ArrayList<Point2D> pts = new ArrayList<>();
            pts.add(firstpt);
            pts.add(lastpt);
            Point2D pt0 = pts.get(0);
            path.moveTo(pt0.getX(), pt0.getY());
            pt0 = pts.get(1);
            path.lineTo(pt0.getX(), pt0.getY());
            g2.setColor(Color.black);
            g2.draw(path);
            }
        }
    }
}
