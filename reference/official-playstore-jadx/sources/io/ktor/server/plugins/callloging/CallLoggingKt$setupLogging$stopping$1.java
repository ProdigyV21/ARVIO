package io.ktor.server.plugins.callloging;

import io.ktor.server.application.Application;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CallLoggingKt$setupLogging$stopping$1 extends r implements l<Application, t0> {
    final /* synthetic */ l<String, t0> $log;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CallLoggingKt$setupLogging$stopping$1(l<? super String, t0> lVar) {
        super(1);
        this.$log = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Application) obj);
        return t0.f22605a;
    }

    public final void invoke(Application application) {
        this.$log.invoke("Application stopping: " + application);
    }
}
