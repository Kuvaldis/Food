package kuvaldis.food.shell.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        prepareLoggers();
    }

    private void prepareLoggers() {
        jul();
        logback();
    }

    private void jul() {
        log.debug("Prepare java util logger");
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private void logback() {
        log.debug("Prepare logback environment");
        final String environment = System.getenv("env");
        if (environment != null) {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            try {
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                context.reset();
                //noinspection ConstantConditions
                configurator.doConfigure(Loader.getResource(String.format("environment/logback-%s.xml", environment),
                        Loader.getClassLoaderOfObject(this)));
            } catch (JoranException ignore) {
            }
            StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        }
    }
}
