package io.ktor.server.engine;

import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/nio/file/WatchService;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ApplicationEngineEnvironmentReloading$watcher$2 extends r implements a<WatchService> {
    public static final ApplicationEngineEnvironmentReloading$watcher$2 INSTANCE = new ApplicationEngineEnvironmentReloading$watcher$2();

    public ApplicationEngineEnvironmentReloading$watcher$2() {
        super(0);
    }

    @Override // r7.a
    public final WatchService invoke() {
        try {
            return FileSystems.getDefault().newWatchService();
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }
}
