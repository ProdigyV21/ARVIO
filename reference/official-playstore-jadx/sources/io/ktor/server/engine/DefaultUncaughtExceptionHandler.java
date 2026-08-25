package io.ktor.server.engine;

import d7.h;
import d7.i;
import d7.j;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import ka.g0;
import ka.j0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlinx.coroutines.CoroutineExceptionHandler;
import r7.a;
import r7.p;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007B\u0015\b\u0016\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0006\u0010\bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0018\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/ktor/server/engine/DefaultUncaughtExceptionHandler;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlin/Function0;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "logger", "<init>", "(Lr7/a;)V", "(Lvc/b;)V", "Ld7/j;", "context", "", "exception", "Lx6/t0;", "handleException", "(Ld7/j;Ljava/lang/Throwable;)V", "Lr7/a;", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultUncaughtExceptionHandler implements CoroutineExceptionHandler {
    private final a<b> logger;

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultUncaughtExceptionHandler$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lvc/b;", "Lio/ktor/util/logging/Logger;", "invoke", "()Lvc/b;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements a<b> {
        final /* synthetic */ b $logger;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(b bVar) {
            super(0);
            this.$logger = bVar;
        }

        @Override // r7.a
        public final b invoke() {
            return this.$logger;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultUncaughtExceptionHandler(a<? extends b> aVar) {
        this.logger = aVar;
    }

    @Override // d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) pVar.invoke(r4, this);
    }

    @Override // d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) i.a(this, bVar);
    }

    @Override // d7.j.a
    public j.b<?> getKey() {
        return g0.f19591i;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(j context, Throwable exception) {
        if ((exception instanceof CancellationException) || (exception instanceof IOException)) {
            return;
        }
        Object string = (j0) context.get(j0.f19600l);
        if (string == null) {
            string = context.toString();
        }
        ((b) this.logger.invoke()).c("Unhandled exception caught for " + string, exception);
    }

    @Override // d7.j
    public j minusKey(j.b<?> bVar) {
        return i.b(this, bVar);
    }

    @Override // d7.j
    public j plus(j jVar) {
        return h.a(this, jVar);
    }

    public DefaultUncaughtExceptionHandler(b bVar) {
        this(new AnonymousClass1(bVar));
    }
}
