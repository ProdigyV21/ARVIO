package io.ktor.server.config;

import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.collections.x;
import t7.a;
import z6.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"CONFIG_PATH", "", "", "getCONFIG_PATH", "()Ljava/util/List;", "configLoaders", "Lio/ktor/server/config/ConfigLoader;", "getConfigLoaders", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConfigLoadersJvmKt {
    private static final List<ConfigLoader> configLoaders = x.c1(ServiceLoader.load(ConfigLoader.class, ConfigLoader.class.getClassLoader()));

    public static final List<String> getCONFIG_PATH() {
        c cVarN = a.n();
        String property = System.getProperty("config.file");
        if (property != null) {
            cVarN.add(property);
        }
        String property2 = System.getProperty("config.resource");
        if (property2 != null) {
            cVarN.add(property2);
        }
        String property3 = System.getProperty("config.url");
        if (property3 != null) {
            cVarN.add(property3);
        }
        return a.e(cVarN);
    }

    public static final List<ConfigLoader> getConfigLoaders() {
        return configLoaders;
    }
}
