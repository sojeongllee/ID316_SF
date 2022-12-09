
package sf;

import java.util.ArrayList;


public class SFOriStepMgr {
    
    //fields
    private ArrayList<SFOriStep> mOrigami = null;
    public ArrayList<SFOriStep> getOrigami() {
        return this.mOrigami;
    }
    
    //constuctor
    public SFOriStepMgr(){
        this.mOrigami = new ArrayList<>();
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
