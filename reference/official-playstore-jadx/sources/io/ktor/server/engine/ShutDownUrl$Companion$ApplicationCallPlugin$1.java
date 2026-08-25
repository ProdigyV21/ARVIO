package io.ktor.server.engine;

import io.ktor.server.engine.ShutDownUrl;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class ShutDownUrl$Companion$ApplicationCallPlugin$1 extends m implements a<ShutDownUrl.Config> {
    public static final ShutDownUrl$Companion$ApplicationCallPlugin$1 INSTANCE = new ShutDownUrl$Companion$ApplicationCallPlugin$1();

    public ShutDownUrl$Companion$ApplicationCallPlugin$1() {
        super(0, ShutDownUrl.Config.class, "<init>", "<init>()V", 0);
    }

    @Override // r7.a
    public final ShutDownUrl.Config invoke() {
        return new ShutDownUrl.Config();
    }
}
