package org.csc133.a2;

import com.codename1.ui.*;
import org.csc133.a2.gameobjects.*;

import java.util.ArrayList;

public class GameWorld {
    private River river;
    private Helipad helipad;
    private Helicopter helicopter;
    private ArrayList<GameObject> collection;
    private ArrayList<Fire> fires;
    private double acc=0;
    private static final int FUEL_INIT=25000;
    private int fuel=FUEL_INIT;
    private int water=0;
    private int fireSize=0;
    private int financialLoss=0;
    private int damageTotal=0;
    private int damagePerRecord;
    private long financialLossRecord;
    private String s1="Game Over", s2="You ran out of fuel:(";
    public GameWorld(){
        init();
    }
    public void init(){
        river = new River();
        helipad = new Helipad();
        helicopter=new Helicopter();
        int[] xArr=new int[]{585,350,1350,};
        int[] yArr=new int[]{85,540,540};
        int[] wArr=new int[]{1000,350,350};
        int[] hArr=new int[]{200,500,500};
        collection= new ArrayList<>();
        fires= new ArrayList<>();
        collection.add(river);
        collection.add(helipad);
        for(int i=0;i<3;i++){
            Building building = new Building(xArr[i], yArr[i], wArr[i], hArr[i]);
            collection.add(building);
            for(int j= 0; j<2; j++){
                Fire fire = new Fire();
                fires.add(fire);
                building.setFiresBuilding(fire);
            }
        }
        collection.add(helicopter);
    }
    public ArrayList<GameObject> getGameObjectCollection(){
        return collection;
    }
    public void dialog(){
        Dialog d = new Dialog(s1);
        Button btn2=new Button("Some Other Time");
        Button btn1=new Button("Heck Yeah");
        Label label = new Label(s2);
        label.getAllStyles().setFgColor(0x0);
        btn1.addActionListener((e) -> new Game());
        btn2.addActionListener((e)->quit());
        d.add(label);
        d.add(new Label("Play Again?"));
        d.add(btn1).add(btn2);
        d.show();
    }
    public void hitScreen(){
        Boolean checkScreen=helicopter.contains(50,
                157, Display.getInstance().getDisplayWidth()-50,
                Display.getInstance().getDisplayHeight()-157);
        //if the helicopter is out of the edge
        if (!checkScreen){
            s2="You crushed it";
            dialog();
        }
    }
    public void allBuildingBurn(){
        s2="The buildings are destroyed";
        dialog();
    }
    public void hasLanded(){
        Boolean heliCheck=helicopter.contains(helipad.getX()+20,
                helipad.getY()+20, helipad.getX()+helipad.getW()-20,
                helipad.getH()+helipad.getY()-20);
        //check if the fires are extinguished, and land the helipad
        if (fires.size()==0 && heliCheck && acc==0) {
            s1 = "You Won!";
            s2 = "Score: " +(100-damagePerRecord) +"% of Unburnt Buildings";
            dialog();
        }
    }
    public int getFuel(){
        return fuel;
    }
    public int getSpeed(){return (int) acc;}
    public int getHeading(){return helicopter.returnHeading();}
    public int getFireTotal(){return fires.size();}
    public int getFireSize() {
        return fireSize;
    }
    public long getFinancialLoss(){
        long loss=((long)financialLoss*3000000)/3000;
        long notOverUnit;
        if (financialLoss>=3000){
            notOverUnit=loss;
            return notOverUnit;
        }
        if(financialLossRecord<loss)financialLossRecord=loss;
        return financialLossRecord;
    }
    public int getPercentageDamage(){
        int returnPer=((damageTotal*100)/1000)/3;
        if (damagePerRecord<returnPer){
            damagePerRecord=returnPer;
        }
        return damagePerRecord;
    }
    public void drink(){
        Boolean riverCheck=helicopter.contains(river.getX(),
                river.getY(), river.getX()+river.getW(),
                river.getY()+river.getH());
        //check if the helicopter is on the river, and speed is 2
        if (riverCheck && (acc<=2) && water<1000){
            water+=100;
        }
    }
    public void removerFireCollection(Fire fire){
        for (GameObject gameObject : collection) {
            if (gameObject instanceof Building) {
                Building building = (Building) gameObject;
                for (Fire fire1 : building.getFiresBuilding()) {
                    if (fire1.getX() == fire.getX()) {
                        building.getFiresBuilding().remove(fire1);
                        break;
                    }
                }
            }
        }
    }
    public void fight() {
       for (int i = fires.size() - 1; i >= 0; i--) {
            Boolean fireCheck =
                    helicopter.contains(fires.get(i).getX(), fires.get(i).getY(),
                            fires.get(i).getW() + fires.get(i).getX(),
                            fires.get(i).getH() + fires.get(i).getY());
            //check if the helicopter closes to the fires, and it has water
            if (fireCheck && water>0) {
                fires.get(i).shrink(water);
                if(fires.get(i).getW()<0) {
                    removerFireCollection(fires.get(i));
                    fires.remove(i);
                    break;
                }
            }
        }
        water=0;
    }
    public void turnLeft(){
        helicopter.steerLeft();
    }
    public void turnRight(){
        helicopter.steerRight();
    }
    public void increaseSpeed(){
        if (acc<10){acc++;}
    }
    public void decreaseSpeed(){
        if (acc>0){acc--;}
    }
    public void getBuildingDamage(){
        damageTotal=0;
        financialLoss=0;
        for(GameObject gameObject:collection){
            if (gameObject instanceof Building){
                Building building=(Building) gameObject;
                damageTotal+=building.returnDamage();
                financialLoss+=building.returnDamage();
            }
        }
    }
    public void tick(){
        fireSize=0;
        hasLanded();
        hitScreen();
        getBuildingDamage();
        fuel-= (int)(Math.pow(acc,2))+5;
        helicopter.move(acc,fuel,water);
        for(Fire fire:fires){
            fire.grow();
            fireSize+=fire.getW();
        }
        if(damageTotal>=3000)allBuildingBurn();
        if (fuel<=0){dialog();}
    }
    public void quit(){
        Display.getInstance().exitApplication();
    }
}
