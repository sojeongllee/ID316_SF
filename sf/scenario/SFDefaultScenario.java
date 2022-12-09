package sf.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import sf.SF;
import sf.SFCanvas2D;
import sf.SFPoly;
import sf.SFScene;
import sf.cmd.SFCmdToAddCurPtCurveToPtCurves;
import sf.cmd.SFCmdToCreateCurPtCurve;
import sf.cmd.SFCmdToInitializePaper;
import sf.cmd.SFCmdToUpdateCurPtCurve;
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
//            SFCmdToCreateFoldingline.execute(sf, pt);
//            XCmdToChangeScene.execute(sf, 
//                SFFoldScenario.FoldReadyScene.getSingleton(), 
//                this);
            SFCmdToCreateCurPtCurve.execute(sf, pt);
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {
//            SF sf = (SF) this.mScenario.getApp();
//            Point pt = e.getPoint();
//            SFCmdToUpdateCurPtCurve.execute(sf, pt);
        }

        @Override
        public void handleMouseRelease(MouseEvent e) {
            SF sf = (SF) this.mScenario.getApp();
            SFCmdToAddCurPtCurveToPtCurves.execute(sf);
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
//                    XCmdToFlipPaper();
                    break;
                case KeyEvent.VK_K:
                    SFCmdToInitializePaper.execute(sf);
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
            SFDefaultScenario scenario = (SFDefaultScenario) this.mScenario;
            Path2D.Double foldline = (Path2D.Double) scenario.makefoldline(g2);
        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
    public Path2D makefoldline(Graphics2D g2){
        SF sf = (SF) super.getApp();
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
        return path;
    }
    
//    public Point2D.Double intersect(Point2D.Double poly_1, Point2D.Double poly_2, 
//        Point2D.Double line_1, Point2D.Double line_2) {
//        double poly_a, poly_b, poly_c, line_a, line_b, line_c = NaN;
//        if(poly_1.x != poly_2.x) {
//            poly_a =(poly_2.y - poly_1.y)/(poly_2.x-poly_1.x);
//            poly_b = poly_1.y - poly_a*poly_1.x;
//        } else {
//            poly_c = poly_1.x;
//        }
//        
//        if(line_1.x != line_2.x) {
//            line_a =(line_2.y - line_1.y)/(line_2.x-line_1.x);
//            line_b = line_1.y - line_a*line_1.x;
//        } else{
//            line_c = line_1.x;
//        }
//        
//        if(poly_c != NaN && line_c != NaN) {
//            
//        }
//        
//    }
    
    public void foldpaper(Graphics2D g2, Path2D.Double foldline){
        // 만들어진 foldline은 해당 step에 저장
        // 각 poly마다 겹치는 두 점을 찾고 foldline의 기울기가 양수면 -> 그 점보다 왼쪽 위 -> poly_1, 왼쪽 아래->poly_2 
        // 기울기가 음수면 -> 그 점보다 오른쪽 위 이런식으로 나눔
        // 각 poly마다 겹치게 되는 두 점 추가해서 polygon만든 다음에 next step으로 주기
        // 지금 문제가 겹치는 포인트를 어케 찾을지임 시발...
        SF sf = (SF) super.getApp();
        ArrayList<SFPoly> lastpolys = sf.getOriStepMgr().getLastOriStep().getPolygons();
        ArrayList<Point2D.Double> intersectpt = new ArrayList<>();
        Point firstpt = sf.getSFPenMarkMgr().getLastPenMark().getFirstPt();
        Point lastpt = sf.getSFPenMarkMgr().getLastPenMark().getLastPt();
        int numpoly = lastpolys.size();
        for(int i = 0; i < numpoly ; i++){
            SFPoly poly = lastpolys.get(i);
            int numpts = poly.npoints;
            for(int j = 0; j < numpts ; j++) {
                
            }
        }
        
    }
    
}
