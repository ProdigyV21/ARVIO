package io.ktor.server.application;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.ConfigKeys;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"host", "", "Lio/ktor/server/config/ApplicationConfig;", "getHost", "(Lio/ktor/server/config/ApplicationConfig;)Ljava/lang/String;", "port", "", "getPort", "(Lio/ktor/server/config/ApplicationConfig;)I", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationConfigExtensionsKt {
    public static final String getHost(ApplicationConfig applicationConfig) {
        String string;
        ApplicationConfigValue applicationConfigValuePropertyOrNull = applicationConfig.propertyOrNull(ConfigKeys.hostConfigPath);
        return (applicationConfigValuePropertyOrNull == null || (string = applicationConfigValuePropertyOrNull.getString()) == null) ? "0.0.0.0" : string;
    }

    public static final int getPort(ApplicationConfig applicationConfig) {
        String string;
        ApplicationConfigValue applicationConfigValuePropertyOrNull = applicationConfig.propertyOrNull(ConfigKeys.hostPortPath);
        if (applicationConfigValuePropertyOrNull == null || (string = applicationConfigValuePropertyOrNull.getString()) == null) {
            return 8080;
        }
        return Integer.parseInt(string);
    }
}
