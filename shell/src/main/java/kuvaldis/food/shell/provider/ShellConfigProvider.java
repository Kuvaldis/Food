package kuvaldis.food.shell.provider;

import kuvaldis.food.shell.utils.BuildInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.BannerProvider;
import org.springframework.shell.plugin.HistoryFileNameProvider;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.shell.support.util.FileUtils;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.shell.support.util.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

// todo move some to properties
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShellConfigProvider implements BannerProvider, PromptProvider, HistoryFileNameProvider {

    @Autowired
    private BuildInfoUtils buildInfoUtils;

    @Override
    public String getBanner() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("banner.txt").toURI()))));
            sb.append(getVersion()).append(OsUtils.LINE_SEPARATOR);
            return sb.toString();
        } catch (URISyntaxException | IOException e) {
            log.error("Banner init exception");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getVersion() {
        return buildInfoUtils.getBuildVersion();
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to " + getProviderName();
    }

    @Override
    public String getProviderName() {
        return "Food Manager";
    }

    @Override
    public String getPrompt() {
        return "fm>";
    }

    @Override
    public String getHistoryFileName() {
        return "food-manager.log";
    }
}
