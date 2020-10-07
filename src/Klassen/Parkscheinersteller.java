package Klassen;

import Interfaces.ICommand;

public class Parkscheinersteller {

    private ICommand command;

    public void saveCommand(ICommand command){
        this.command = command;
    }

    public void activate(){
        command.execute();
    }
    public ICommand getCommand(){
        return command;
    }

}
