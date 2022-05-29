package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class River extends Fixed implements Drawable { ;
    public River(){
        setWH(Display.getInstance().getDisplayWidth(),192);
    }
    @Override
    public void draw(Graphics g, Point containOrigin) {
       int x =containOrigin.getX();
       int y=(Display.getInstance().getDisplayHeight()/6)+50
               +containOrigin.getY();
       int w=(int)getW();
       int h=(int)getH();
        g.setColor(ColorUtil.BLUE);
        g.drawRect(x,y,w,h,3);
        setXY(containOrigin.getX(),(Display.getInstance().getDisplayHeight()/6)
                +50+containOrigin.getY());
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
