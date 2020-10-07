package Klassen;

import Interfaces.ICommand;
import java.util.Stack;

public class CommandStack{

    public CommandStack(){}

    private Stack<ICommand> commands = new Stack<>();

    public void addCommand(ICommand command){
        commands.push(command);
    }

    public ICommand removeCommand(){
        return commands.pop();
    }

}
