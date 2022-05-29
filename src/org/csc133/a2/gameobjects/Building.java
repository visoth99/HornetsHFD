package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Building extends Fixed implements Drawable {
    private static final int VALUE_UNIT=1000000;
    private int damage;
    private final CopyOnWriteArrayList<Fire> fires;
    public Building(int x,int y,int w, int h) {
        setXY(x,y);
        setWH(w,h);
        fires=new CopyOnWriteArrayList<>();
    }
    public void draw(Graphics g, Point containOrigin){
        g.setColor(ColorUtil.rgb(255,0,0));
        int interX=(int)getX()+containOrigin.getX();
        int interX2=(int)getW();
        int interY=(int)getY()+containOrigin.getY();
        int interY2=(int)getH();
        g.drawRect(interX,interY,interX2,interY2,5);
        g.drawString("V: $"+ VALUE_UNIT,interX+interX2,
                interY+interY2);
        if(damage>1000)damage=1000;
        g.drawString("D: "+ (damage * 100) / 1000 +"%",interX+interX2,
                interY+interY2+30);
        damage=0;
        for(Fire fire:fires){
            fire.draw(g, new Point(containOrigin.getX(), containOrigin.getY()));
            damage+=fire.getW();
            }
    }
    public int returnDamage(){
        if(damage>1000)damage=1000;
        return damage;
    }
    public void setFiresBuilding(Fire fire){
        int x= (int) (new Random().nextInt(((int) getW()-100))+getX());
        int y= (int) (new Random().nextInt(((int) getH()-100))+getY());
        fire.start(x,y);
        fires.add(fire);
    }
    public CopyOnWriteArrayList<Fire> getFiresBuilding(){
        return fires;
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
