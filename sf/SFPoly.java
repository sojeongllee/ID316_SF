package sf;

import java.awt.Polygon;


public class SFPoly extends Polygon {
    //fields
    private boolean mFace = true;
    public boolean getIsFace() {
        return mFace;
    }
    
    //constructor
    public SFPoly() {
        
    }
    
    //methods
    public void changeFace() {
        this.mFace = !this.mFace;
    }
}
