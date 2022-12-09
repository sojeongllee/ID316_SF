package sf;

import sf.scenario.SFCutScenario;
import sf.scenario.SFDefaultScenario;
//import sf.scenario.SFDrawScenario;
import sf.scenario.SFNavigateScenario;
import sf.scenario.SFPaperStatusScenario;
//import sf.scenario.SFSelectScenario;
import x.XScenarioMgr;


public class SFScenarioMgr extends XScenarioMgr {
    // constructor
    public SFScenarioMgr(SF sf) {
        super(sf);
    }
    @Override
    protected void addScenarios() {
        this.addScenarios(SFDefaultScenario.createSingleton(this.mApp));
//        this.addScenarios(SFDrawScenario.createSingleton(this.mApp));
//        this.addScenarios(SFSelectScenario.createSingleton(this.mApp));
        this.addScenarios(SFNavigateScenario.createSingleton(mApp));
        this.addScenarios(SFPaperStatusScenario.createSingleton(mApp));
        this.addScenarios(SFCutScenario.createSingleton(mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(SFDefaultScenario.ReadyScene.getSingleton());
    }
    
}
