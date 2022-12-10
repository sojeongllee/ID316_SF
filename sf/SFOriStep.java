package sf;

import java.awt.geom.Point2D;
import java.util.ArrayList;


public class SFOriStep {
    //fields
    private int mStep = 0;
    public int getStep() {
        return this.mStep;
    }
    
    private ArrayList<SFPoly> mPolygons = null;
    public ArrayList<SFPoly> getPolygons() {
        return this.mPolygons;
    }
    
    
    
    
    
    private ArrayList<Point2D.Double> mFoldLine = null;
    public ArrayList<Point2D.Double> getFoldLine() {
        return this.mFoldLine;
    }
    public void setFoldLine(Point2D.Double firstpt, Point2D.Double lastpt) {
        if(firstpt != null && lastpt != null){
            this.mFoldLine.add(firstpt);
            this.mFoldLine.add(lastpt);
            int size = this.mFoldLine.size();
            assert(size>2);
        }
    }
    
    private ArrayList<Point2D.Double> mCuttingLine = null;
    public ArrayList<Point2D.Double> getCuttingLine() {
        return this.mCuttingLine;
    }
    public void setCuttingLine(ArrayList<Point2D.Double> cuttingline) {
        if(cuttingline != null){
            this.mCuttingLine = cuttingline;
        }
    }
    
    //constructor
    public SFOriStep(int step, ArrayList<SFPoly> poly) {
        this.mStep = step;
        this.mPolygons = poly;
        this.mFoldLine = new ArrayList<>();
        this.mCuttingLine = new ArrayList<>();
    }
    
    //methods
    public void addPoly(SFPoly poly) {
        if(poly != null) {
            this.mPolygons.add(poly);
        }
    }
    
}
