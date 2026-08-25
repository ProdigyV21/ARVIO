package io.ktor.server.config;

import io.ktor.server.config.ConfigLoader;
import java.util.List;
import kotlin.Metadata;
import l6.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a!\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ll6/a;", "", "path", "tryGetString", "(Ll6/a;Ljava/lang/String;)Ljava/lang/String;", "", "tryGetStringList", "(Ll6/a;Ljava/lang/String;)Ljava/util/List;", "configPath", "Lio/ktor/server/config/ApplicationConfig;", "ApplicationConfig", "(Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfig;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HoconApplicationConfigKt {
    public static final ApplicationConfig ApplicationConfig(String str) {
        ConfigLoader.Companion companion = ConfigLoader.INSTANCE;
        return companion.load(companion, str);
    }

    public static final String tryGetString(a aVar, String str) {
        if (aVar.q(str)) {
            return aVar.g(str);
        }
        return null;
    }

    public static final List<String> tryGetStringList(a aVar, String str) {
        if (aVar.q(str)) {
            return aVar.e(str);
        }
        return null;
    }
}
