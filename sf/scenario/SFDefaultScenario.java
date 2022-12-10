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
            SFDefaultScenario scenario = (SFDefaultScenario) this.mScenario;
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
            scenario.foldpaper(foldline);
        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
//    public Path2D.Double foldline = null;
    
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
    
    public Point2D.Double intersect(Point firstpt, Point lastpt, 
        Point2D.Double pt1, Point2D.Double pt2) {
        double x1 = firstpt.x, y1 = firstpt.y, x2 = lastpt.x, y2 = lastpt.y, x3 = pt1.x, y3 = pt2.y,
                x4 = pt2.x, y4 = pt2.y;
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return null;
        }

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        Point2D.Double intersectpt = new Point2D.Double(xi, yi);
        
        Path2D.Double edge = new Path2D.Double();
        edge.moveTo(pt1.x, pt1.y);
        edge.moveTo(pt2.x, pt2.y);
        
        if(edge.contains(intersectpt)) {
            return intersectpt;
        } else{
            return null;
        }
    }
    
    public void foldpaper(Path2D.Double foldline){
        // 만들어진 foldline은 해당 step에 저장
        // 각 poly마다 겹치는 두 점을 찾고 foldline의 기울기가 양수면 -> 그 점보다 왼쪽 위 -> poly_1, 왼쪽 아래->poly_2 
        // 기울기가 음수면 -> 그 점보다 오른쪽 위 이런식으로 나눔
        // 각 poly마다 겹치게 되는 두 점 추가해서 polygon만든 다음에 next step으로 주기
        // 지금 문제가 겹치는 포인트를 어케 찾을지임 시발...
        SF sf = (SF) super.getApp();
        ArrayList<SFPoly> lastpolys = sf.getOriStepMgr().getLastOriStep().getPolygons();
        
        Point firstpt = sf.getSFPenMarkMgr().getLastPenMark().getFirstPt();
        Point lastpt = sf.getSFPenMarkMgr().getLastPenMark().getLastPt();
        int numpoly = lastpolys.size();
        
        // find intersectPts(startPt, endPt)
        ArrayList<Point2D.Double> intersectPts = new ArrayList<>();
        for(int i = 0; i < numpoly ; i++){
            SFPoly poly = lastpolys.get(i);
            int numpts = poly.npoints;
            for(int j = 0; j < numpts ; j++) {
                Point2D.Double pt1 = new Point2D.Double(poly.xpoints[j], poly.ypoints[j]);
                Point2D.Double pt2 = new Point2D.Double(poly.xpoints[j+1], poly.ypoints[j+1]);
                Point2D.Double interpt = intersect(firstpt, lastpt, pt1, pt2);
                if(interpt != null) {
                    intersectPts.add(interpt);
                }
            }
        }
        System.out.println(intersectPts);
        
    }
    
}
