package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Stroke;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;
import org.csc133.a2.interfaces.Steerable;

import static com.codename1.ui.CN.*;

public class Helicopter extends Movable implements Drawable,Steerable {
    private double y;
    private double x;
    private double cosX;
    private double sinY;
    private double heliX;
    private double heliY;
    private double angle=-90;
    private int fuel,water;
    public Helicopter(){
        setXY((float)Display.getInstance().getDisplayWidth()/2,
                (float)Display.getInstance().getDisplayHeight()/2);
    }
    @Override
    public void draw(Graphics g, Point containOrigin) {
        double centerX=getX()+containOrigin.getX();
        double centerY=getY()+containOrigin.getY();
        double rY=centerY+530-containOrigin.getY();
        double interX = centerX + x;
        double interY = rY + y;
        double interX2=centerX+x+60*cosX;
        double interY2=(rY+y)+60*sinY;
        heliX=centerX+x+15*cosX;
        heliY=(rY+y)+15*sinY;
        Font font = Font.createSystemFont(FACE_SYSTEM,
                STYLE_BOLD, SIZE_MEDIUM);
        g.setFont(font);
        g.setColor(ColorUtil.YELLOW);
        g.fillArc((int)(centerX+x-15), (int)(rY+y-15), 31, 31,
                0,360);
        Stroke stroke = new Stroke(2.5f, Stroke.CAP_ROUND,
                Stroke.JOIN_ROUND, 4);
        GeneralPath path =new GeneralPath();
        path.moveTo(interX, interY);
        path.lineTo(interX2,interY2);
        g.drawShape(path,stroke);
        //Fuel and water
        g.drawString("F  : "+fuel,(int)(centerX+x),(int)(rY+y+65));
        g.drawString("W  : "+water,(int)(centerX+x), (int)(rY+y+92));
    }
    public int returnHeading(){
        int head=(int) angle+90;
        if(head ==360){
            angle=-90;
        }
        else if (head <0){
            head=head*(-1);
            if(head == 360)angle=-90;
        }
        return head;
    }
    @Override
    public void steerLeft() {
        angle=angle-15;
    }
    @Override
    public void steerRight() {
        angle=angle+15;
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

    @Override
    public void move(double acc,int fuel,int water) {
        this.fuel=fuel;
        if (this.fuel<0)this.fuel=0;
        this.water=water;
        super.heading=angle;
        double deG= Math.toRadians(angle);
        sinY = Math.sin(deG);
        cosX = Math.cos(deG);
        if (acc>0) {
            y += (super.speed+3 + acc) * sinY;
            x += (super.speed+3 + acc) * cosX;
        }
    }
    public Boolean contains(double x, double y, double w, double h){
        return (heliX >= x && heliX <= w) && (heliY >= y && heliY <= h);
    }
}
