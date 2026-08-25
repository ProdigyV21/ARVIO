package io.ktor.server.cio;

import com.google.android.gms.cast.MediaTrack;
import io.ktor.server.cio.CIOApplicationEngine;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.CommandLineKt;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\u0007*\u00020\n2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/cio/EngineMain;", "", "<init>", "()V", "", "", "args", "Lx6/t0;", MediaTrack.ROLE_MAIN, "([Ljava/lang/String;)V", "Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "Lio/ktor/server/config/ApplicationConfig;", "config", "loadConfiguration", "(Lio/ktor/server/cio/CIOApplicationEngine$Configuration;Lio/ktor/server/config/ApplicationConfig;)V", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EngineMain {
    public static final EngineMain INSTANCE = new EngineMain();

    private EngineMain() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadConfiguration(CIOApplicationEngine.Configuration configuration, ApplicationConfig applicationConfig) {
        String string;
        ApplicationConfig applicationConfigConfig = applicationConfig.config("ktor.deployment");
        CommandLineKt.loadCommonConfiguration(configuration, applicationConfigConfig);
        ApplicationConfigValue applicationConfigValuePropertyOrNull = applicationConfigConfig.propertyOrNull("connectionIdleTimeoutSeconds");
        if (applicationConfigValuePropertyOrNull == null || (string = applicationConfigValuePropertyOrNull.getString()) == null) {
            return;
        }
        configuration.setConnectionIdleTimeoutSeconds(Integer.parseInt(string));
    }

    @n
    public static final void main(String[] args) throws Throwable {
        ApplicationEngineEnvironment applicationEngineEnvironmentCommandLineEnvironment = CommandLineKt.commandLineEnvironment(args);
        new CIOApplicationEngine(applicationEngineEnvironmentCommandLineEnvironment, new EngineMain$main$engine$1(applicationEngineEnvironmentCommandLineEnvironment)).start(true);
    }
}
