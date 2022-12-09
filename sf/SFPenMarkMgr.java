package sf;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class SFPenMarkMgr {
    //constants
    private static final int MAX_NUM_PEN_MARKS = 10;
    
    //fields
    private ArrayList<SFPenMark> mPenMarks = null;
    public ArrayList<SFPenMark> getPenMarks() {
        return this.mPenMarks;
    }
    
    //constructor
    public SFPenMarkMgr() {
        this.mPenMarks = new ArrayList<SFPenMark>();
    }
    
    public void addPenMark(SFPenMark penMark) {
        this.mPenMarks.add(penMark);
        if(this.mPenMarks.size() > SFPenMarkMgr.MAX_NUM_PEN_MARKS) {
            this.mPenMarks.remove(0);
            assert (this.mPenMarks.size() <= SFPenMarkMgr.MAX_NUM_PEN_MARKS);
        }
    }
    
    public SFPenMark getLastPenMark() {
        int size = this.mPenMarks.size();
        if (size == 0) {
            return null;
        } else {
            return this.mPenMarks.get(size-1);
        }
    }
    
    public SFPenMark getRecentPenMark(int i) { //최근 i번째 값 return
        int size = this.mPenMarks.size();
        int index = size - 1 - i;
        if (index < 0 || index >= size) {
            return null;
        } else {
            return this.mPenMarks.get(index);
        }
    }
    
    public boolean handleMousePress(MouseEvent e) {
        Point pt = e.getPoint();
        SFPenMark penMark = new SFPenMark(pt);
        this.addPenMark(penMark);
        return true;
    }
    
    public boolean handleMouseDrag(MouseEvent e) {
        Point pt = e.getPoint();
        SFPenMark penMark = this.getLastPenMark();
        if (penMark != null) {
            penMark.addPt(pt);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean handleMouseRelease(MouseEvent e) {
        return true;
    }
}
