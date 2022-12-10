package sf;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class SFFoldLine extends Path2D.Double {
    //fields
    
    private Point mStartPt = null;
    public Point getStartPt() {
        return this.mStartPt;
    }
    public void setStartPt(Point pt) {
        this.mStartPt = pt;
    }
    
    private Point mEndPt = null;
    public Point getEndPt() {
        return this.mEndPt;
    }
    public void setEndPt(Point pt) {
        this.mEndPt = pt;
    }
    
    //constructor
    public SFFoldLine(Point pt) {
        this.mStartPt = pt;
    }
    
    //update
    public void update(Point pt){
        this.moveTo(pt.x, pt.y);
    }
}   
