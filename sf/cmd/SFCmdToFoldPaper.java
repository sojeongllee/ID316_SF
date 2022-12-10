package sf.cmd;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import sf.SF;
import sf.SFFoldLine;
import sf.SFPoly;
import sf.scenario.SFDefaultScenario;
import x.XApp;
import x.XLoggableCmd;

public class SFCmdToFoldPaper extends XLoggableCmd {
    // fields
    //...
    
    //private constructor
    //private SFCmdToDoSomething(XApp app, ...) {
    private SFCmdToFoldPaper(XApp app, Path2D.Double foldline) {
        super(app);
    }
    
    // SFCmdToCreateCurptCurve.execute(sf, ...);
    public static boolean execute(XApp app, Path2D.Double foldline) {
        SFCmdToFoldPaper cmd = new SFCmdToFoldPaper(app, foldline);
        return cmd.execute();
        
    }
    
    @Override
    protected boolean defineCmd() {
        SF sf = (SF) this.mApp;
        if(sf.getOriStepMgr().getLastOriStep() != null) {
            ArrayList<SFPoly> lastpolys = sf.getOriStepMgr().getLastOriStep().getPolygons();
            
            if(SFDefaultScenario.getSingleton().getFoldLine() != null){
                SFFoldLine foldline = SFDefaultScenario.getSingleton().getFoldLine();
                Point firstpt = foldline.getStartPt();
                Point lastpt = foldline.getEndPt();
                int numpoly = lastpolys.size();

                // find intersectPts(startPt, endPt)
                ArrayList<Point2D.Double> intersectPts = new ArrayList<>();
                for(int i = 0; i < numpoly ; i++){
                    SFPoly poly = lastpolys.get(i);
                    int numpts = poly.npoints;
//                    System.out.println(numpts);

                    for(int j = 0; j < numpts ; j++) {
                        Point2D.Double pt1 = new Point2D.Double(poly.xpoints[j], poly.ypoints[j]);
                        Point2D.Double pt2 = null;
                        if (j != numpts-1){
                            pt2 = new Point2D.Double(poly.xpoints[j+1], poly.ypoints[j+1]);
                        } else {
                            pt2 = new Point2D.Double(poly.xpoints[0], poly.ypoints[0]);   
                        }
                        

                        Point2D.Double interpt = intersect(firstpt, lastpt, pt1, pt2);
//                        
                        if(interpt != null) {
                            System.out.println("interpt: " + interpt.toString());
                                    
                            intersectPts.add(interpt);
                        }
                    }
                }
//                System.out.println(intersectPts);
            }
            
        }
        return true;
    }
    
    public Point2D.Double intersect(Point firstpt, Point lastpt, 
        Point2D.Double pt1, Point2D.Double pt2) {
        double x1 = firstpt.x, y1 = firstpt.y, x2 = lastpt.x, y2 = lastpt.y, x3 = pt1.x, y3 = pt2.y,
                x4 = pt2.x, y4 = pt2.y;
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        System.out.println("Pt1 :"+pt1.toString());
        System.out.println("Pt2 :"+pt2.toString());
        System.out.println("firstpt :"+firstpt.toString());
        System.out.println("lastpt :"+lastpt.toString());
        System.out.println(d);
        if (d == 0) {
            return null; //parallel
        }
        double xi = ((x1*y2 - y1*x2)*(x3-x4) - (x1 - x2)*(x3*y4-y3*x4)) / d;
        double yi = ((x1*y2 - y1*x2)*(y3-y4) - (y1 - y2)*(x3*y4-y3*x4)) / d;
        Point2D.Double intersectpt = new Point2D.Double(xi, yi);
        
        System.out.println("x: " + xi);
        System.out.println("y: " + yi);
        Path2D.Double edge = new Path2D.Double();
        edge.moveTo(pt1.x, pt1.y);
        edge.moveTo(pt2.x, pt2.y);
        
//        System.out.println(xi);
        if(edge.contains(intersectpt.x, intersectpt.y)) {
            System.out.println("intersectpt: " + intersectpt.toString());
            return intersectpt;
        } else{
            return null;
        }
        
    }
    
    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        //...
        return sb.toString();
    }
    
}
