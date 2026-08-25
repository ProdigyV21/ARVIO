package io.ktor.server.config;

import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lio/ktor/server/config/ConfigLoader;", "", "load", "Lio/ktor/server/config/ApplicationConfig;", "path", "", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ConfigLoader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u0016\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/config/ConfigLoader$Companion;", "", "()V", "loadDefault", "Lio/ktor/server/config/ApplicationConfig;", "load", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public static /* synthetic */ ApplicationConfig load$default(Companion companion, Companion companion2, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = null;
            }
            return companion.load(companion2, str);
        }

        private final ApplicationConfig loadDefault() {
            for (String str : ConfigLoadersJvmKt.getCONFIG_PATH()) {
                Iterator<ConfigLoader> it = ConfigLoadersJvmKt.getConfigLoaders().iterator();
                while (it.hasNext()) {
                    ApplicationConfig applicationConfigLoad = it.next().load(str);
                    if (applicationConfigLoad != null) {
                        return applicationConfigLoad;
                    }
                }
            }
            return null;
        }

        public final ApplicationConfig load(Companion companion, String str) {
            ApplicationConfig applicationConfigLoadDefault;
            if (str == null && (applicationConfigLoadDefault = companion.loadDefault()) != null) {
                return applicationConfigLoadDefault;
            }
            Iterator<ConfigLoader> it = ConfigLoadersJvmKt.getConfigLoaders().iterator();
            while (it.hasNext()) {
                ApplicationConfig applicationConfigLoad = it.next().load(str);
                if (applicationConfigLoad != null) {
                    return applicationConfigLoad;
                }
            }
            return new MapApplicationConfig();
        }
    }

    ApplicationConfig load(String path);
}
