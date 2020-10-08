package JUnit_Tests;

import Interfaces.ICommand;
import Klassen.CommandStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class CommandStackTest {

    CommandStack commandStack;

    @BeforeEach
    void setup() {
        commandStack = new CommandStack();

    }

    @Test
    void removeCommand() {
//        assertThrows(EmptyStackException.class,()->{commandStack.removeCommand();});
        assertDoesNotThrow(() -> {
            commandStack.removeCommand();
        });
    }
}