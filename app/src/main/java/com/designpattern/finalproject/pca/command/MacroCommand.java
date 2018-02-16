package com.designpattern.finalproject.pca.command;

import com.designpattern.finalproject.pca.theme.StylableWidget;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus-pc on 2018/01/21.
 */

public class MacroCommand extends Command {
    private ArrayList<Command> commandList;

    public MacroCommand() {
        commandList = new ArrayList<Command>();
    }

    public void addCommand(Command command) {
        commandList.add(command);
    }


    @Override
    public void execute() {
        for (Iterator<Command> it = commandList.iterator(); it.hasNext(); ) {
            it.next().execute();
        }
    }
}