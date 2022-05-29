package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

import java.util.Random;

import static com.codename1.ui.CN.SIZE_MEDIUM;

public class Fire extends Fixed implements Drawable {
    private double interX2,interY2,x,y,oriX,oriY;
    private static final int FIRE_UNIT=80;
    public void start(int x,int y){
        this.x=x;
        this.y=y;
        int random = new Random().nextInt(FIRE_UNIT)+10;
        setWH(random, random);
        interX2=getW();
        interY2=getH();
    }
    public void draw(Graphics g, Point containOrigin) {
        oriX=containOrigin.getX();
        oriY=containOrigin.getY();
        int interX = (int) (x+oriX);
        int interY = (int) (y+oriY);
        Font font = Font.createSystemFont(Font.FACE_SYSTEM,
                Font.STYLE_PLAIN, SIZE_MEDIUM);
        g.setFont(font);
        g.setColor(ColorUtil.MAGENTA);
        if (interX2 > 0 && interY2 >0){
            g.fillArc(interX, interY, (int)interX2, (int)interY2, 0, 360);
        g.drawString(String.valueOf((int)interX2), (interX + (int)interX2),
                (interY + (int)interY2));
        }
        setXY((float) (x+oriX), (float) (y+oriY));
    }
    public void grow(){
        if (interX2 > 0 && interY2 >0) {
            float random = new Random().nextFloat()/2;
            interY2 += random;
            interX2 += random;
            x-=random/2;
            y-=random/2;
            setWH((int) interX2, (int) interY2);
            setXY((float) (x+oriX), (float) (y+oriY));
        } else{interX2=0;interY2=0;}
    }
    public void shrink(int water){
        water=(water*200)/1000;
        interX2-=water;
        interY2-=water;
        x+=(float)water/2;
        y+=(float)water/2;
        setWH((int) interX2,(int)interY2);
        setXY((float) (x+oriX), (float) (y+oriY));
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
