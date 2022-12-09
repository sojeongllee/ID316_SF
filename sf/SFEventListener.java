package sf;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SFEventListener implements MouseListener, MouseMotionListener, 
        KeyListener {
    
    // fields
    private SF mSF = null;
    //private SFPtCurveMgr mSFPtCurveMgr = null;
    
    //constructor
    public SFEventListener (SF sf) {
        this.mSF = sf;
    }
    
    
     @Override
    public void mousePressed(MouseEvent e) {
        if (this.mSF.getSFPenMarkMgr().handleMousePress(e)) {
            SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
            curScene.handleMousePress(e);
            this.mSF.getCanvas2D().repaint();
        }
    }
    
    /*
    public void mousePressed(MouseEvent e) {
        SF sf = this.mSF;
        SFPtCurveMgr PtCurveMgr = this.mSFPtCurveMgr;
        SFPtCurve curPtCurve = null;
        Point pt = e.getPoint();
        Point2D.Double worldPt = 
            sf.getXform().calcPtFromScreenToWorld(pt);
        
        switch (sf.getMode()) {
            case DRAW:
                curPtCurve = new SFPtCurve(worldPt, 
                    sf.getCanvas2D().getCurColorForPtCurve(), 
                        sf.getCanvas2D().getCurStrokeForPtCurve()); 
                //sf.setCurPtCurves(curPtCurve);
                PtCurveMgr.setCurPtCurves(curPtCurve);
                
                break;
            case SELECT:
                SFSelectionBox selectionbox = new SFSelectionBox(pt);
                sf.setSelectionBox(selectionbox);
                break;
            case SELECTED:
                curPtCurve = 
                    new SFPtCurve(worldPt, 
                        sf.getCanvas2D().getCurColorForPtCurve(), 
                            sf.getCanvas2D().getCurStrokeForPtCurve()); 
                //sf.setCurPtCurves(curPtCurve);
                
                break;
            case PAN:
                sf.getXform().setStartScreenPt(pt);
                break;
            case ZOOM_ROTATE:
                sf.getXform().setStartScreenPt(pt);
            case COLOR:
                Color c = sf.getColorChooser().calcColor(pt, 
                    sf.getCanvas2D().getWidth(), sf.getCanvas2D().getHeight());
                if (c != null) {
                    if(PtCurveMgr.getSelectedPtCurves().isEmpty()) {
                        sf.getCanvas2D().setCurColorForPtCurve(c);
                    } else {
                        for (SFPtCurve ptCurve : PtCurveMgr.getSelectedPtCurves()) {
                            ptCurve.setColor(c);
                        }
                        PtCurveMgr.getPtCurves().addAll(PtCurveMgr.getSelectedPtCurves());
                        PtCurveMgr.getSelectedPtCurves().clear();
                    }
                    
                }
                break;
        }
        sf.getCanvas2D().repaint();
    }
    */
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.mSF.getSFPenMarkMgr().handleMouseRelease(e)) {
            SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
            curScene.handleMouseRelease(e);
            this.mSF.getCanvas2D().repaint();
        }
    }
    
    /*
    public void mouseReleased(MouseEvent e) {
        SF sf = this.mSF;
        SFPtCurveMgr PtCurveMgr = this.mSFPtCurveMgr;
        SFPtCurve curPtCurve = PtCurveMgr.getCurPtCurve();
        switch (sf.getMode()) {
            case DRAW:
                if (curPtCurve != null) {
                    if (curPtCurve.getPts().size() > 1) {
                        PtCurveMgr.getPtCurves().add(curPtCurve);
                    }
                    PtCurveMgr.setCurPtCurves(null);
                }  
                break;
            case SELECT:
                sf.setSelectionBox(null);
                break;
            case SELECTED:
                if (curPtCurve != null) {
                    if (curPtCurve.getPts().size() == 1) {
                        PtCurveMgr.getPtCurves().addAll(PtCurveMgr.getSelectedPtCurves());
                        PtCurveMgr.getSelectedPtCurves().clear();
                        sf.setMode(SF.Mode.DRAW);
                    } 
                    PtCurveMgr.setCurPtCurves(null);
                }  
                break;
            case PAN:
                sf.getXform().setStartScreenPt(null);
                break;
            case ZOOM_ROTATE:
                sf.getXform().setStartScreenPt(null);
                break;
            case COLOR:
                break;
        }
        sf.getCanvas2D().repaint(); 
    }
    */
    
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.mSF.getSFPenMarkMgr().handleMouseDrag(e)) {
            SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
            curScene.handleMouseDrag(e);
            this.mSF.getCanvas2D().repaint();
        }
    }
    
    /*
    public void mouseDragged(MouseEvent e) {
        SF sf = this.mSF;
        SFPtCurveMgr PtCurveMgr = this.mSFPtCurveMgr;
        SFPtCurve curPtCurve = PtCurveMgr.getCurPtCurve();
        Point pt = e.getPoint();
        switch (sf.getMode()) {
            case DRAW:
                if (curPtCurve != null) {
                    // check if the distance between the new screen point
                    // and the last screen point is greater than a certain
                    // tolerance.
                    int size = curPtCurve.getPts().size();
                    Point2D.Double lastWorldPoint = 
                        curPtCurve.getPts().get(size - 1);
                    Point lastScreenPoint = 
                        sf.getXform().calcPtFromWorldToScreen(lastWorldPoint);
                    if (pt.distance(lastScreenPoint) < 
                            SFPtCurve.MIN_DIST_BTWN_PTS) {
                            return;
                    }
                    Point2D.Double worldPt = 
                        sf.getXform().calcPtFromScreenToWorld(pt);
                    curPtCurve.addPt(worldPt);
                }
                break;
            case SELECT:
                if (sf.getSelectionBox() != null) {
                    //update the selection box with the anchor point and 
                    //a new mouse position 
                    sf.getSelectionBox() .update(pt);
                    PtCurveMgr.updateSelectedPtCurves();
                }
                break;
            case SELECTED:
                if (curPtCurve != null) {
                    Point2D.Double worldPt = 
                        sf.getXform().calcPtFromScreenToWorld(pt);
                    curPtCurve.addPt(worldPt);
                }
                break;
            case PAN:
                sf.getXform().translateTo(pt);
                break;
            case ZOOM_ROTATE:
                sf.getXform().zoomRotateTo(pt);
                break;
            case COLOR:
                break;
        }
        sf.getCanvas2D().repaint();
    }
    */
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
        curScene.handleKeyDown(e);
        this.mSF.getCanvas2D().repaint();   
    }
    /*
    public void keyPressed(KeyEvent e) {
        SF sf = this.mSF;
        SFPtCurveMgr PtCurveMgr = this.mSFPtCurveMgr;
        int code = e.getKeyCode();
        switch (sf.getMode()) {
            case DRAW:
                switch (code) {
                    case KeyEvent.VK_SHIFT:
                        sf.setMode(SF.Mode.SELECT);
                        break;
                    case KeyEvent.VK_CONTROL:
                        sf.setMode(SF.Mode.PAN);
                        break;
                    case KeyEvent.VK_ALT:
                        sf.setMode(SF.Mode.ZOOM_ROTATE);
                        break;
                    case KeyEvent.VK_C:
                        sf.setMode(SF.Mode.COLOR);
                        break;
                    case KeyEvent.VK_UP:
                        sf.getCanvas2D().increaseStrokeWidthForCurPtCurve(
                            SFCanvas2D.STROKE_WIDTH_INCREMENT);
                        break;
                    case KeyEvent.VK_DOWN:
                        sf.getCanvas2D().increaseStrokeWidthForCurPtCurve(
                            -SFCanvas2D.STROKE_WIDTH_INCREMENT);
                        break;
                }
                break;
            case SELECT:
                break;
            case SELECTED:
                switch (code) {
                    case KeyEvent.VK_SHIFT:
                        sf.setMode(SF.Mode.SELECT);
                        break;
                    case KeyEvent.VK_CONTROL:
                        sf.setMode(SF.Mode.PAN);
                        break;
                    case KeyEvent.VK_ALT:
                        sf.setMode(SF.Mode.ZOOM_ROTATE);
                        break;
                    case KeyEvent.VK_C:
                        sf.setMode(SF.Mode.COLOR);
                        break;
                    case KeyEvent.VK_UP:
                        for (SFPtCurve ptCurve : PtCurveMgr.getSelectedPtCurves()) {
                            ptCurve.increaseStrokeWidth(
                                SFCanvas2D.STROKE_WIDTH_INCREMENT);
                        }

                        break;
                    case KeyEvent.VK_DOWN:
                        for (SFPtCurve ptCurve : PtCurveMgr.getSelectedPtCurves()) {
                            ptCurve.increaseStrokeWidth(
                                -SFCanvas2D.STROKE_WIDTH_INCREMENT);
                        }
                        break;
                }
                break;
            case PAN:
                break;
            case ZOOM_ROTATE:
                break;
            case COLOR:
                break;       
        }
        sf.getCanvas2D().repaint();
    }
    */
    
     @Override
    public void keyReleased(KeyEvent e) {
        SFScene curScene = (SFScene) this.mSF.getScenarioMgr().getCurScene();
        curScene.handleKeyUp(e);
        this.mSF.getCanvas2D().repaint();     
    }
    
    /*
    public void keyReleased(KeyEvent e) {
        SF sf = this.mSF;
        SFPtCurveMgr PtCurveMgr = this.mSFPtCurveMgr;
        int code = e.getKeyCode();
        switch (sf.getMode()) {
            case DRAW:
                switch (code) {
                    case KeyEvent.VK_HOME:
                    sf.getXform().home();
                }
                break;
            case SELECT:
                switch (code) {
                    case KeyEvent.VK_SHIFT:
                        if(PtCurveMgr.getSelectedPtCurves().isEmpty()) {
                            sf.setMode(SF.Mode.DRAW);
                            sf.setSelectionBox(null);
                            break;
                        } else {
                            sf.setMode(SF.Mode.SELECTED);
                        }  
                }
                break;
            case SELECTED:
                switch (code) {
                    case KeyEvent.VK_ESCAPE: // cancel selected lines
                        PtCurveMgr.getPtCurves().addAll(PtCurveMgr.getSelectedPtCurves());
                        PtCurveMgr.getSelectedPtCurves().clear();
                        sf.setMode(SF.Mode.DRAW);
                        break;
                    case KeyEvent.VK_DELETE: // delete selected lines
                        PtCurveMgr.getSelectedPtCurves().clear();
                        sf.setMode(SF.Mode.DRAW);
                        break;
                    case KeyEvent.VK_HOME:
                        sf.getXform().home();
                        break;
                }
                break;
            case PAN:
                switch (code) {
                    case KeyEvent.VK_CONTROL:
                        if(PtCurveMgr.getSelectedPtCurves().isEmpty()) {
                            sf.setMode(SF.Mode.DRAW);
                            sf.setSelectionBox(null);
                            break;
                        } else {
                            sf.setMode(SF.Mode.SELECTED);
                        } 
                }
                break;
            case ZOOM_ROTATE:
                switch (code) {
                    case KeyEvent.VK_ALT:
                        if(PtCurveMgr.getSelectedPtCurves().isEmpty()) {
                            sf.setMode(SF.Mode.DRAW);
                            sf.setSelectionBox(null);
                            break;
                        } else {
                            sf.setMode(SF.Mode.SELECTED);
                        } 
                break;
                }
                
            case COLOR:
                switch (code) {
                    case KeyEvent.VK_C:
                        if(PtCurveMgr.getSelectedPtCurves().isEmpty()) {
                            sf.setMode(SF.Mode.DRAW);
                            sf.setSelectionBox(null);
                            break;
                        } else {
                            sf.setMode(SF.Mode.SELECTED);
                        } 
                break;
                }
                
                        
                        
        }
        sf.getCanvas2D().repaint();
    }
    */
    @Override
    public void keyTyped(KeyEvent e) { 
    }


    
}
