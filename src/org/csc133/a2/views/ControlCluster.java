package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.*;

public class ControlCluster extends Container {
    public ControlCluster(GameWorld gw) {
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_NONE);
        this.getAllStyles().setBgTransparency(255);
        this.setLayout(new BorderLayout());
        ((BorderLayout)this.getLayout()).
                setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        Button right = new Button("Right");
        right.getAllStyles().setBgTransparency(255);
        right.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        right.addActionListener((e) ->new RightCommand(gw));
        Button left = new Button("Left");
        left.getAllStyles().setBgTransparency(255);
        left.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        left.addActionListener((e) -> new LeftCommand(gw));
        Button fight = new Button("Fight");
        fight.getAllStyles().setBgTransparency(255);
        fight.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        fight.addActionListener((e) -> new FightCommand(gw));
        Button exit = new Button("Exit");
        exit.getAllStyles().setBgTransparency(255);
        exit.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        exit.addActionListener((e) -> new QuitCommand(gw));
        Button drink = new Button("Drink");
        drink.getAllStyles().setBgTransparency(255);
        drink.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        drink.addActionListener((e) -> new DrinkCommand(gw));
        Button brake = new Button("Brake");
        brake.getAllStyles().setBgTransparency(255);
        brake.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        brake.addActionListener((e) -> new DecreaseAccelerate(gw));
        Button accel = new Button("Accel");
        accel.getAllStyles().setBgTransparency(255);
        accel.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        accel.addActionListener((e) -> new AccelerateCommand(gw));
        Container left1 = new Container();
        left1.add(drink);
        left1.add(brake);
        left1.add(accel);
        Container right1 = new Container();
        right1.add(right);
        right1.add(left);
        right1.add(fight);
        this.add(BorderLayout.WEST, right1);
        this.add(BorderLayout.EAST, left1);
        this.add(BorderLayout.CENTER, exit);
    }
}
