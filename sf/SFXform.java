package sf;


import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;


public class SFXform {
    //constants
    public static final Point PIVOT_PT = new Point(100, 100);
    public static final Double MIN_START_ARM_LENGTH_FOR_SCALING = 100.0;
    
    //fields
    //world->screen (inverse of mCurXformFromScreenToWorld)
    private AffineTransform mCurXformFromWorldToScreen = null; 
    public AffineTransform getCurXformFromWorldToScreen() {
        return this.mCurXformFromWorldToScreen;
    }
    //screen->world (inverse of mCurXformFromWorldToScreen)
    private AffineTransform mCurXformFromScreenToWorld = null; 
    public AffineTransform getCurXformFromScreenToWorld() {
        return this.mCurXformFromScreenToWorld;
    }
    
    private AffineTransform mStartXformFromWorldToScreen = null;
    
    private Point mStartScreenPt = null;
    public void setStartScreenPt(Point pt) {
        this.mStartScreenPt = pt;
        this.mStartXformFromWorldToScreen.
                setTransform(this.mCurXformFromWorldToScreen);
    }
    
    //constructor
    public SFXform() {
        this.mCurXformFromWorldToScreen = new AffineTransform();
        this.mCurXformFromScreenToWorld = new AffineTransform();
        this.mStartXformFromWorldToScreen = new AffineTransform();
    }
    
    //call whenever mCurXformFromWorldToScreen changes to have its
    //corresponding mCurXformFromScreenToWorld
    public void updateCurXformFromScreenToWorld() {
        try {
            this.mCurXformFromScreenToWorld
                    = this.mCurXformFromWorldToScreen.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
        }
    }
    
    public Point calcPtFromWorldToScreen (Point2D.Double worldPt) {
        Point screenPt = new Point();
        this.mCurXformFromWorldToScreen.transform(worldPt, screenPt);
        return screenPt;
    }
    
    public Point2D.Double calcPtFromScreenToWorld(Point screenPt) {
        Point2D.Double worldPt = new Point2D.Double();
        this.mCurXformFromScreenToWorld.transform(screenPt, worldPt);
        return worldPt;
    }
    
    public void translateTo(Point pt) {
        if (this.mStartScreenPt != null) {
            this.mCurXformFromWorldToScreen.
                    setTransform(this.mStartXformFromWorldToScreen);
            // call whenever mCurXformFromWorldToScreen changes
            this.updateCurXformFromScreenToWorld();

            Point2D.Double worldPt0 = 
                this.calcPtFromScreenToWorld(this.mStartScreenPt);
            Point2D.Double worldPt1 = this.calcPtFromScreenToWorld(pt);
            double dx = worldPt1.x - worldPt0.x;
            double dy = worldPt1.y - worldPt0.y;

            this.mCurXformFromWorldToScreen.translate(dx, dy);
            this.updateCurXformFromScreenToWorld();
        } 
    }

    public void rotateTo(Point pt) {
        if (this.mStartScreenPt != null) {
            this.mCurXformFromWorldToScreen.
                    setTransform(this.mStartXformFromWorldToScreen);
            // call whenever mCurXformFromWorldToScreen changes
            this.updateCurXformFromScreenToWorld();

            // for rotation
            double ang0 = StrictMath.atan2(
                this.mStartScreenPt.y - SFXform.PIVOT_PT.y,
                this.mStartScreenPt.x - SFXform.PIVOT_PT.x);
            double ang1 = StrictMath.atan2(pt.y - SFXform.PIVOT_PT.y,
                    pt.x - SFXform.PIVOT_PT.x);
            double ang = ang1 - ang0;

            Point2D.Double worldPivotPt = 
                this.calcPtFromScreenToWorld(SFXform.PIVOT_PT);
            this.mCurXformFromWorldToScreen.translate(worldPivotPt.x, 
                    worldPivotPt.y);
            this.mCurXformFromWorldToScreen.rotate(ang);
            this.mCurXformFromWorldToScreen.translate(-worldPivotPt.x, 
                    -worldPivotPt.y);
            this.updateCurXformFromScreenToWorld();
        } 
    }

    public boolean zoomRotateTo(Point pt) {
        if (this.mStartScreenPt != null) {
            this.mCurXformFromWorldToScreen.
                    setTransform(this.mStartXformFromWorldToScreen);
            // call whenever mCurXformFromWorldToScreen changes
            this.updateCurXformFromScreenToWorld();

            // for zoom and rotation
            double d0 = SFXform.PIVOT_PT.distance(this.mStartScreenPt);
            if(d0 < SFXform.MIN_START_ARM_LENGTH_FOR_SCALING) {
                return false;
            }
            double d1 = SFXform.PIVOT_PT.distance(pt);
            double s = d1 / d0;
            
            double ang0 = StrictMath.atan2(
                this.mStartScreenPt.y - SFXform.PIVOT_PT.y,
                this.mStartScreenPt.x - SFXform.PIVOT_PT.x);
            double ang1 = StrictMath.atan2(pt.y - SFXform.PIVOT_PT.y,
                    pt.x - SFXform.PIVOT_PT.x);
            double ang = ang1 - ang0;

            // step 1 : translate the canvas by worldPivotPt.
            Point2D.Double worldPivotPt = 
                this.calcPtFromScreenToWorld(SFXform.PIVOT_PT);
            this.mCurXformFromWorldToScreen.translate(worldPivotPt.x, 
                    worldPivotPt.y);
            
            // step 2 : rotate the canvas by ang.
            this.mCurXformFromWorldToScreen.rotate(ang);
            
            // step 3 : scale the canvas by s.
            this.mCurXformFromWorldToScreen.scale(s, s);
            
            // step 4 : translate the canvas by -worldPivotPt.
            this.mCurXformFromWorldToScreen.translate(-worldPivotPt.x, 
                    -worldPivotPt.y);
            this.updateCurXformFromScreenToWorld();
        } 
        return true;
    }
    
    public void home() {
        this.mCurXformFromWorldToScreen.setToIdentity();
        this.updateCurXformFromScreenToWorld();
    }
}
