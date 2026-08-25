package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class CallLoggingConfig$formatCall$1 extends m implements l<ApplicationCall, String> {
    public CallLoggingConfig$formatCall$1(Object obj) {
        super(1, 0, CallLoggingConfig.class, obj, "defaultFormat", "defaultFormat(Lio/ktor/server/application/ApplicationCall;)Ljava/lang/String;");
    }

    @Override // r7.l
    public final String invoke(ApplicationCall applicationCall) {
        return ((CallLoggingConfig) this.receiver).defaultFormat(applicationCall);
    }
}
