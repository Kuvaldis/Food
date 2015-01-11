package kuvaldis.food.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class TestCommand implements CommandMarker {

    @CliCommand(value = "test")
    public void test() {
        System.out.println("234");
    }
}
