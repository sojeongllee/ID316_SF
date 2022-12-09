package sf.scenario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import sf.SFScene;
import x.XApp;
import x.XScenario;

public class SFEmptyScenario extends XScenario {
    // singleton pattern
    private static SFEmptyScenario mSingleton = null;
    public static SFEmptyScenario createSingleton(XApp app) {
        assert (SFEmptyScenario.mSingleton == null);
        SFEmptyScenario.mSingleton = new SFEmptyScenario(app);
        return SFEmptyScenario.mSingleton;
    }
    
    public static SFEmptyScenario getSingleton() {
        assert (SFEmptyScenario.mSingleton != null);
        return SFEmptyScenario.mSingleton;
    }
    
    private SFEmptyScenario(XApp app) {
        super(app);
    }
    
    @Override
    protected void addScenes() {
        this.addScene(SFEmptyScenario.EmptyScene.createSingleton(this));
    }

    public static class EmptyScene extends SFScene {
        //singleton pattern
        private static EmptyScene mSingleton = null;
        public static EmptyScene createSingleton(XScenario scenario) {
            assert (EmptyScene.mSingleton == null);
            EmptyScene.mSingleton = new EmptyScene(scenario);
            return EmptyScene.mSingleton;
        }
        public static EmptyScene getSingleton() {
            assert (EmptyScene.mSingleton != null);
            return EmptyScene.mSingleton;
        }
        private EmptyScene(XScenario scenario) {
            super(scenario);
        }

        @Override
        public void handleMousePress(MouseEvent e) {
            
        }

        @Override
        public void handleMouseDrag(MouseEvent e) {

        }

        @Override
        public void handleMouseRelease(MouseEvent e) {

        }

        @Override
        public void handleKeyDown(KeyEvent e) {
            
        }

        @Override
        public void handleKeyUp(KeyEvent e) {
            
        }

        @Override
        public void updateSupportObjects() {

        }

        @Override
        public void renderWorldObjects(Graphics2D g2) {

        }

        @Override
        public void renderScreenObjects(Graphics2D g2) {

        }

        @Override
        public void getReady() {

        }

        @Override
        public void wrapUp() {

        }
    }
    
}
