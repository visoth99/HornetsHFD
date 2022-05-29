package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.layouts.BorderLayout;
import org.csc133.a2.commands.*;
import com.codename1.ui.Graphics;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Form;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;


public class Game extends Form implements Runnable {
    private final GameWorld gw;
    private final GlassCockpit gc;

    public Game(){
        gw= new GameWorld();
        gc=new GlassCockpit(gw);
        MapView mapView = new MapView(gw);
        ControlCluster cc = new ControlCluster(gw);
        //Control Keys
        addKeyListener('Q', (evt) -> new QuitCommand(gw));
        addKeyListener(-91, (evt) -> new AccelerateCommand(gw));
        addKeyListener(-92, (evt) -> new DecreaseAccelerate(gw));
        addKeyListener(-93, (evt) -> new LeftCommand(gw));
        addKeyListener(-94, (evt) -> new RightCommand(gw));
        addKeyListener('f', (evt) -> new FightCommand(gw));
        addKeyListener('d', (evt) -> new DrinkCommand(gw));
        //
        this.getStyle().setBgColor(ColorUtil.BLACK);
        UITimer timer = new UITimer(this);
        timer.schedule(100,true,this);
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH,gc);
        this.add(BorderLayout.SOUTH, cc);
        this.add(BorderLayout.CENTER, mapView);
        this.show();
    }
    public void paint(Graphics g)
    {
        super.paint(g);
    }
    @Override
    public void run() {
        gw.tick();
        gc.update();
        repaint();
    }
}
