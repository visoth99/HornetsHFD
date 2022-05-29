package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Stroke;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class Helipad extends Fixed implements Drawable {

    public Helipad(){
        setWH(118,118);
    }
    public void draw(Graphics g,Point containOrigin) {
        g.setColor(ColorUtil.GRAY);
        int interX=(Display.getInstance().getDisplayWidth()/2)-59
                +containOrigin.getX() ;
        int interX2=(int)getW();
        int interY=(Display.getInstance().getDisplayHeight()/2)+335
                +containOrigin.getY();
        int interY2=(int)getH();
        g.drawRect(interX-18, interY-17,interX2+36,interY2+36,5);
        GeneralPath path = new GeneralPath();
        Stroke stroke = new Stroke(2.5f, Stroke.CAP_ROUND,
                Stroke.JOIN_ROUND, 4);
        path.arc(interX, interY,interX2,interY2,0, 360);
        g.drawShape(path, stroke);
        setXY(interX,interY);
    }

    public float getX(){
        return (float) location.getX();
    }
    public float getY(){
        return (float) location.getY();
    }
    public void setXY(float x,float y){
        location=new Point2D(x,y);
    }

    public float getW(){
        return (float) dimension.getWidth();
    }
    public float getH(){
        return (float) dimension.getHeight();
    }
    public void setWH(int x,int y){
        dimension=new Dimension(x,y);
    }
}
