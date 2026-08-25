package io.ktor.network.util;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.j0;
import ka.k0;
import ka.m0;
import ka.s0;
import ka.v1;
import kotlin.Metadata;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001BN\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0015\u0010\u0014J\r\u0010\u0016\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0019R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001aR-\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n8\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\r\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/network/util/Timeout;", "", "", ContentDisposition.Parameters.Name, "", "timeoutMs", "Lkotlin/Function0;", "clock", "Lka/k0;", "scope", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "onTimeout", "<init>", "(Ljava/lang/String;JLr7/a;Lka/k0;Lr7/l;)V", "Lka/v1;", "initTimeoutJob", "()Lka/v1;", TtmlNode.START, "()V", "stop", "finish", "Ljava/lang/String;", "J", "Lr7/a;", "Lka/k0;", "Lr7/l;", "workerJob", "Lka/v1;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Timeout {
    private final a<Long> clock;
    private final String name;
    private final l<d<? super t0>, Object> onTimeout;
    private final k0 scope;
    private final long timeoutMs;
    volatile /* synthetic */ long lastActivityTime = 0;
    volatile /* synthetic */ int isStarted = 0;
    private v1 workerJob = initTimeoutJob();

    /* JADX INFO: renamed from: io.ktor.network.util.Timeout$initTimeoutJob$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.network.util.Timeout$initTimeoutJob$1", f = "Utils.kt", l = {57, 59, 60}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return Timeout.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            e7.a aVar = e7.a.f15033i;
            int i10 = this.label;
            if (i10 != 0 && i10 != 1) {
                if (i10 != 2) {
                    if (i10 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                    return t0.f22605a;
                }
                c.G(obj);
                l lVar = Timeout.this.onTimeout;
                this.label = 3;
                if (lVar.invoke(this) == aVar) {
                    return aVar;
                }
                return t0.f22605a;
            }
            c.G(obj);
            while (true) {
                if (Timeout.this.isStarted == 0) {
                    Timeout timeout = Timeout.this;
                    timeout.lastActivityTime = ((Number) timeout.clock.invoke()).longValue();
                }
                long jLongValue = (Timeout.this.lastActivityTime + Timeout.this.timeoutMs) - ((Number) Timeout.this.clock.invoke()).longValue();
                if (jLongValue > 0 || Timeout.this.isStarted == 0) {
                    this.label = 1;
                    if (s0.a(jLongValue, this) == aVar) {
                        break;
                    }
                } else {
                    this.label = 2;
                    if (m0.B(this) == aVar) {
                    }
                }
            }
            return aVar;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Timeout(String str, long j10, a<Long> aVar, k0 k0Var, l<? super d<? super t0>, ? extends Object> lVar) {
        this.name = str;
        this.timeoutMs = j10;
        this.clock = aVar;
        this.scope = k0Var;
        this.onTimeout = lVar;
    }

    private final v1 initTimeoutJob() {
        if (this.timeoutMs == Long.MAX_VALUE) {
            return null;
        }
        k0 k0Var = this.scope;
        return m0.p(k0Var, k0Var.getCoroutineContext().plus(new j0("Timeout " + this.name)), 0, new AnonymousClass1(null), 2);
    }

    public final void finish() {
        v1 v1Var = this.workerJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
    }

    public final void start() {
        this.lastActivityTime = ((Number) this.clock.invoke()).longValue();
        this.isStarted = 1;
    }

    public final void stop() {
        this.isStarted = 0;
    }
}
