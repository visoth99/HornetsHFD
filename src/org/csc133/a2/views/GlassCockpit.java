package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import org.csc133.a2.GameWorld;


public class GlassCockpit extends Container {
    private final GameWorld gw;
    private final Label fuel ;
    private final Label speed ;
    private final Label angle ;
    private final Label fireTotal;
    private final Label fireSize;
    private final Label financialLoss;
    private final Label damageTotal;
    public GlassCockpit(GameWorld gw) {
        this.gw=gw;
        fuel =new Label("0");
        speed =new Label("0");
        angle =new Label("0");
        fireTotal =new Label("0");
        fireSize =new Label("0");
        financialLoss =new Label("0");
        damageTotal =new Label("0");
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_NONE);
        this.getAllStyles().setBgTransparency(255);
        this.setLayout(new GridLayout(2,7));
        this.add("HEADING");this.add("SPEED");this.add("FUEL");
        this.add("FIRES");this.add("FIRE SIZE");this.add("% OF DAMAGE");
        this.add("FINANCIAL LOSS");
        this.add(angle);
        this.add(speed);
        this.add(fuel);
        this.add(fireTotal);
        this.add(fireSize);
        this.add(damageTotal);
        this.add(financialLoss);
    }
    public void update() {
        this.fuel.setText(String.valueOf(gw.getFuel()));
        this.speed.setText(String.valueOf(gw.getSpeed()));
        this.angle.setText(String.valueOf(gw.getHeading()));
        this.fireTotal.setText(String.valueOf(gw.getFireTotal()));
        this.fireSize.setText(String.valueOf(gw.getFireSize()));
        this.financialLoss.setText("$: "+ gw.getFinancialLoss());
        this.damageTotal.setText(String.valueOf(gw.getPercentageDamage()));
    }

}
