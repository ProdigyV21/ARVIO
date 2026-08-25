package io.ktor.server.plugins.callloging;

import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class CallLoggingKt$CallLogging$1 extends m implements a<CallLoggingConfig> {
    public static final CallLoggingKt$CallLogging$1 INSTANCE = new CallLoggingKt$CallLogging$1();

    public CallLoggingKt$CallLogging$1() {
        super(0, CallLoggingConfig.class, "<init>", "<init>()V", 0);
    }

    @Override // r7.a
    public final CallLoggingConfig invoke() {
        return new CallLoggingConfig();
    }
}
