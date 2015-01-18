package kuvaldis.food.shell.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.core.ExecutionProcessor;
import org.springframework.shell.event.ParseResult;

@Slf4j
public abstract class AbstractShellCommandMarker implements ExecutionProcessor {
    @Override
    public ParseResult beforeInvocation(ParseResult invocationContext) {
        return invocationContext;
    }

    @Override
    public void afterReturningInvocation(ParseResult invocationContext, Object result) {

    }

    @Override
    public void afterThrowingInvocation(ParseResult invocationContext, Throwable thrown) {
        log.error("Error executing command", thrown);
    }
}
