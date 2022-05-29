package org.csc133.a2.commands;

import org.csc133.a2.GameWorld;

public class QuitCommand {
    public QuitCommand(GameWorld gw){
        gw.quit();
    }
}
