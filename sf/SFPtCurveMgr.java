package sf;

import java.util.ArrayList;


public class SFPtCurveMgr {
    //fields
    private SF mSF = null; 
    
    private SFPtCurve mCurPtCurve = null;
    public SFPtCurve getCurPtCurve() {
        return this.mCurPtCurve;
    }
    public void setCurPtCurves(SFPtCurve curptcurve) {
        this.mCurPtCurve = curptcurve;
    }
    private ArrayList<SFPtCurve> mPtCurves = null;
    public ArrayList<SFPtCurve> getPtCurves() {
        return this.mPtCurves;
    }

    private ArrayList<SFPtCurve> mSelectedPtCurves = null;
    public ArrayList<SFPtCurve> getSelectedPtCurves() {
        return this.mSelectedPtCurves;
    }
    
    public SFPtCurveMgr(SF sf) {
    //constructor 
    this.mSF = sf;
    this.mPtCurves = new ArrayList<SFPtCurve>();
    this.mSelectedPtCurves = new ArrayList<SFPtCurve>();
    }
}
