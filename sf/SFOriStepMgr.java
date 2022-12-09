
package sf;

import java.util.ArrayList;


public class SFOriStepMgr {
    
    //fields
    private int mStep = 0;
    public int getStep() {
        return this.mStep;
    }
    public void addStep() {
        this.mStep += 1;
    }
    
    private ArrayList<SFOriStep> mOrigami = null;
    public ArrayList<SFOriStep> getOrigami() {
        return this.mOrigami;
    }
    
    //constuctor
    public SFOriStepMgr(SF sf){
        this.mOrigami = new ArrayList<>();
        this.mStep = 0;
    }
    
    
    //methods
    public void addOriStep(SFOriStep newori) {
        this.mOrigami.add(newori);
    }
    
    public SFOriStep getLastOriStep() {
        int size = this.mOrigami.size();
        if (size == 0) {
            return null;
        } else {
            return this.mOrigami.get(size-1);
        }
    }
    
    public SFOriStep getRecentOriStep(int i) { //최근 i번째 값 return
        int size = this.mOrigami.size();
        int index = size - 1 - i;
        if (index < 0 || index >= size) {
            return null;
        } else {
            return this.mOrigami.get(index);
        }
    }
}
