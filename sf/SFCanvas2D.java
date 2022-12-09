package sf;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SFCanvas2D extends JPanel {
    //constants
    private static final Color COLOR_CUR_PT_CURVE = Color.BLACK;
    private static final Color COLOR_PT_CURVES = Color.GRAY;
    private static final Color COLOR_PT_CURVE_DEFAULT = new Color(0, 0, 0, 192);
    private static final Color COLOR_SELECTED_PT_CURVES = Color.ORANGE;
    public static final Color COLOR_SELECTION_BOX = new Color(255, 0, 0, 64);
    private static final Color COLOR_INFO = new Color(255, 0, 0, 128);
    public static final Color COLOR_CROSS_HAIR = new Color(255, 0, 0, 64);
    
    //private static final Stroke STROKE_CUR_PT_CURVE = new BasicStroke(5f);
    //private static final Stroke STROKE_PT_CURVES = new BasicStroke(5f);
    private static final Stroke STROKE_PT_CURVE_DEFAULT = new BasicStroke(
        5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    private static final Stroke STROKE_SELECTED_PT_CURVES = new BasicStroke(5f);
    public static final Stroke STROKE_SELECTION_BOX = new BasicStroke(5f);
    public static final Stroke STROKE_CROSS_HAIR = new BasicStroke(5f);
    private static final float PEN_TIP_OFFSET = 30;
    
    private static final Font FONT_INFO = 
        new Font ("Monospaced", Font.PLAIN, 24);
    
    private static final int INFO_TOP_ALIGNMENT_X = 20;
    private static final int INFO_TOP_ALIGNMENT_Y = 30;
    public static final double ZOOM_ROTATE_CROSS_HAIR_RADIUS = 30;
    public static final float STROKE_WIDTH_INCREMENT = 1f;
    public static final float STROKE_MIN_WIDTH = 1f;
    
    
    
    //fields
    private SF mSF = null;
    //private SFPtCurveMgr mSFPtCurveMgr = null;
    private Stroke mCurStrokeForPtCurve = null;
    public Stroke getCurStrokeForPtCurve() {
        return this.mCurStrokeForPtCurve;
    }
    
    public Color mCurColorForPtCurve = null;
    public Color getCurColorForPtCurve() {
        return this.mCurColorForPtCurve;
    }
    
    public void setCurColorForPtCurve(Color c) {
        this.mCurColorForPtCurve = c;
    }
    
    //contructor
    public SFCanvas2D(SF sf) {
        this.mSF = sf;
        //this.mSFPtCurveMgr = PtCurveMgr;
        this.mCurStrokeForPtCurve = SFCanvas2D.STROKE_PT_CURVE_DEFAULT;
        this.mCurColorForPtCurve = SFCanvas2D.COLOR_PT_CURVE_DEFAULT;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
    // turn on anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        // world objects
        // transform the coordinate system from screen to world
        g2.transform(this.mSF.getXform().getCurXformFromWorldToScreen()); 
        
        //render common world objects.
        this.drawPtCurves(g2);
        this.drawSelectedPtCurves(g2);
//        this.drawCurPtCurve(g2);
        
        //render the current scene's world objects
        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
        curScene.renderWorldObjects(g2);
        
        // screen objects
        // transform the coordinate system from world to screen
        g2.transform(this.mSF.getXform().getCurXformFromScreenToWorld()); 
        
        //render common screen object
        //this.drawSelectionBox(g2);
//        this.drawCrossHair(g2);
        this.drawColorChooser(g2);
        this.drawPenTip(g2);
        this.drawInfo(g2);
        
        //render the current scene's screen objects
        curScene.renderScreenObjects(g2);
    }
        
//         you can set the color and stroke of g2
    private void setColorAndStroke(Graphics2D g2, Color c, Stroke s) {
        g2.setColor(c);
        g2.setStroke(s);
    }
    
    private void drawPtCurves(Graphics2D g2) {
        for (SFPtCurve ptCurve : this.mSF.getSFPtCurveMgr().getPtCurves()) {
            this.drawPtCurve(g2, ptCurve, ptCurve.getColor(), ptCurve.getStroke());
        }
    }
    
//    private void drawCurPtCurve(Graphics2D g2) {
//        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
//        if (curScene.getScenario() == SFDrawScenario.getSingleton()) {
//            SFPtCurve ptCurve = this.mSF.getSFPtCurveMgr().getCurPtCurve();
//            if (ptCurve != null) {
//                this.drawPtCurve(g2, ptCurve, ptCurve.getColor(), 
//                    ptCurve.getStroke());
//            }
//        }
//    }
    
    
    private void drawPtCurve(Graphics2D g2,  SFPtCurve ptCurve, Color c, Stroke s) {
        Path2D.Double path = new Path2D.Double();
        ArrayList<Point2D.Double> pts = ptCurve.getPts();
        if (pts.size() < 2) {
            return;
        }
        Point2D.Double pt0 = pts.get(0);
        path.moveTo(pt0.x, pt0.y);
        for (int i = 0; i < pts.size(); i++) {
            Point2D.Double pt = pts.get(i);
            path.lineTo(pt.x, pt.y);
        }
        g2.setColor(c);
        g2.setStroke(s);
        g2.draw(path);
        
    }
    
    private void drawSelectedPtCurves(Graphics2D g2) {
        for (SFPtCurve ptCurve : this.mSF.getSFPtCurveMgr().getSelectedPtCurves()) {
            this.drawPtCurve(g2, ptCurve, SFCanvas2D.COLOR_SELECTED_PT_CURVES,
                ptCurve.getStroke());
        }
    }
    
//    public void drawSelectionBox(Graphics2D g2) {
//        if (this.mSF.getSelectionBox() != null) {
//            this.setColorAndStroke(g2, SFCanvas2D.COLOR_SELECTION_BOX, 
//                SFCanvas2D.STROKE_SELECTION_BOX);
//            g2.draw(this.mSF.getSelectionBox());
//        }
//    }
    
//    private void drawCrossHair(Graphics2D g2) {
////        if (this.mSF.getMode() == SF.Mode.ZOOM_ROTATE) {
//        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
//        //// if (curScene.getScenario() == SFNavigateScenario.getSingleton()) {
//        if (false) { //// need to fix when SFNavigateScenario  is introduced
//        double r = SFCanvas2D.CROSS_HAIR_RADIUS;
//            Point ctr = SFXform.PIVOT_PT;
//            Line2D hline = new Line2D.Double(
//                ctr.x - r, ctr.y, ctr.x + r, ctr.y);
//            Line2D vline = new Line2D.Double(
//                ctr.x, ctr.y - r, ctr.x, ctr.y + r);
//            this.setColorAndStroke(g2, SFCanvas2D.COLOR_CROSS_HAIR, STROKE_CROSS_HAIR);
//            g2.draw(hline);
//            g2.draw(vline);
//        }
//    }
    
    public void increaseStrokeWidthForCurPtCurve(float f) {
        System.out.println("incrase");
        BasicStroke bs = (BasicStroke) this.mCurStrokeForPtCurve;
        float w = bs.getLineWidth();
        w += f;
        if (w < 1f) {
            w = 1f;
        }
        this.mCurStrokeForPtCurve = 
            new BasicStroke(w, bs.getEndCap(), bs.getLineJoin());
    }
    
    private void drawPenTip(Graphics2D g2) {

        BasicStroke bs = (BasicStroke) this.mCurStrokeForPtCurve;
        Point2D.Double worldPt0 = new Point2D.Double(0.0, 0.0);
        Point2D.Double worldPt1 = new Point2D.Double(bs.getLineWidth(), 0.0);
        Point screenPt0 = this.mSF.getXform().calcPtFromWorldToScreen(worldPt0);
        Point screenPt1 = this.mSF.getXform().calcPtFromWorldToScreen(worldPt1);
        double d = screenPt0.distance(screenPt1);
        double r = d / 2.0;

        Point2D.Double ctr = new Point2D.Double(
            this.getWidth() - SFCanvas2D.PEN_TIP_OFFSET, SFCanvas2D.PEN_TIP_OFFSET);
        Ellipse2D.Double e = new Ellipse2D.Double(
            ctr.x - r, ctr.y - r, 2*r, 2*r);
        g2.setColor(this.mCurColorForPtCurve);
        g2.fill(e);
    }
    
    private void drawInfo(Graphics2D g2) {
//      String str = String.valueOf(this.mSF.getMode());
        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
        String str = curScene.getClass().getSimpleName();
        g2.setColor(SFCanvas2D.COLOR_INFO);
        g2.setFont(SFCanvas2D.FONT_INFO);
        g2.drawString(str, SFCanvas2D.INFO_TOP_ALIGNMENT_X, 
            SFCanvas2D.INFO_TOP_ALIGNMENT_Y);
    }
    
    private void drawColorChooser(Graphics2D g2) {
//        if (this.mSF.getMode() == SF.Mode.COLOR) {
        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
        ////if (curScene.getScenario() == SFColorScenario.getSingleton()) {
        if (false) { //// need to fix when SFColorScenario is introduced
            this.mSF.getColorChooser().drawCells(g2, this.getWidth(),
                this.getHeight());
        }
    }
}
