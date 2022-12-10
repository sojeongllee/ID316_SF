package sf;

import java.awt.Polygon;


public class SFPoly extends Polygon {
    //fields
    private boolean mFace = true;
    public boolean getFace() {
        return mFace;
    }
    
    //constructor
    public SFPoly(int[] xpts, int[] ypts, int num, boolean face) {
        super(xpts, ypts, num);
        this.mFace = face;
    }
    
    //methods
    public void changeFace() {
        this.mFace = !this.mFace;
    }
    
}
