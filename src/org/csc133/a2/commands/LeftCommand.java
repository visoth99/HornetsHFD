package org.csc133.a2.commands;

import org.csc133.a2.GameWorld;

public class LeftCommand {
    public LeftCommand(GameWorld gw){
        gw.turnLeft();
    }
}
