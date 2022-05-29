package org.csc133.a2.commands;

import org.csc133.a2.GameWorld;

public class AccelerateCommand {

    public AccelerateCommand(GameWorld gw){
        gw.increaseSpeed();
    }
}
