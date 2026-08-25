package io.ktor.server.engine;

import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.engine.internal.ApplicationUtilsJvmKt;
import io.ktor.util.InternalAPI;
import k2.c;
import ka.k0;
import ka.m0;
import ka.n1;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x0;
import ka.x1;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a%\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a4\u0010\r\u001a\u00020\u0004*\u00020\u00072\u001c\u0010\f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "", "gracePeriodMillis", "timeoutMillis", "Lka/t;", "stopServerOnCancellation", "(Lio/ktor/server/engine/ApplicationEngine;JJ)Lka/t;", "Lka/v1;", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "launchOnCancellation", "(Lka/v1;Lr7/l;)Lka/t;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EngineContextCancellationHelperKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.EngineContextCancellationHelperKt$launchOnCancellation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.EngineContextCancellationHelperKt$launchOnCancellation$1", f = "EngineContextCancellationHelper.kt", l = {TsExtractor.TS_STREAM_TYPE_H265, AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ l<d<? super t0>, Object> $block;
        final /* synthetic */ t $deferred;
        int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(t tVar, l<? super d<? super t0>, ? extends Object> lVar, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$deferred = tVar;
            this.$block = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$deferred, this.$block, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        
            if (r5.join(r4) == r3) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
        
            if (r5.invoke(r4) == r3) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0044, code lost:
        
            return r3;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1f
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r5)
                goto L45
            L10:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L18:
                int r0 = r4.I$0
                k2.c.G(r5)     // Catch: java.lang.Throwable -> L30
            L1d:
                r2 = r0
                goto L30
            L1f:
                k2.c.G(r5)
                ka.t r5 = r4.$deferred     // Catch: java.lang.Throwable -> L30
                r0 = 0
                r4.I$0 = r0     // Catch: java.lang.Throwable -> L30
                r4.label = r2     // Catch: java.lang.Throwable -> L30
                java.lang.Object r5 = r5.join(r4)     // Catch: java.lang.Throwable -> L30
                if (r5 != r3) goto L1d
                goto L44
            L30:
                if (r2 != 0) goto L3a
                ka.t r5 = r4.$deferred
                boolean r5 = r5.isCancelled()
                if (r5 == 0) goto L45
            L3a:
                r7.l<d7.d<? super x6.t0>, java.lang.Object> r5 = r4.$block
                r4.label = r1
                java.lang.Object r5 = r5.invoke(r4)
                if (r5 != r3) goto L45
            L44:
                return r3
            L45:
                x6.t0 r5 = x6.t0.f22605a
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.EngineContextCancellationHelperKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1", f = "EngineContextCancellationHelper.kt", l = {}, m = "invokeSuspend")
    public static final class C18831 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ long $gracePeriodMillis;
        final /* synthetic */ ApplicationEngine $this_stopServerOnCancellation;
        final /* synthetic */ long $timeoutMillis;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18831(ApplicationEngine applicationEngine, long j10, long j11, d<? super C18831> dVar) {
            super(1, dVar);
            this.$this_stopServerOnCancellation = applicationEngine;
            this.$gracePeriodMillis = j10;
            this.$timeoutMillis = j11;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new C18831(this.$this_stopServerOnCancellation, this.$gracePeriodMillis, this.$timeoutMillis, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((C18831) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            this.$this_stopServerOnCancellation.stop(this.$gracePeriodMillis, this.$timeoutMillis);
            return t0.f22605a;
        }
    }

    @InternalAPI
    public static final t launchOnCancellation(v1 v1Var, l<? super d<? super t0>, ? extends Object> lVar) {
        x1 x1Var = new x1(v1Var);
        m0.p(n1.f19624i, v1Var.plus(ApplicationUtilsJvmKt.getIOBridge(x0.f19652a)), 0, new AnonymousClass1(x1Var, lVar, null), 2);
        return x1Var;
    }

    public static final t stopServerOnCancellation(ApplicationEngine applicationEngine, long j10, long j11) {
        t tVarLaunchOnCancellation;
        v1 v1Var = (v1) applicationEngine.getEnvironment().getParentCoroutineContext().get(u1.f19642i);
        return (v1Var == null || (tVarLaunchOnCancellation = launchOnCancellation(v1Var, new C18831(applicationEngine, j10, j11, null))) == null) ? m0.c() : tVarLaunchOnCancellation;
    }

    public static /* synthetic */ t stopServerOnCancellation$default(ApplicationEngine applicationEngine, long j10, long j11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = 50;
        }
        if ((i10 & 2) != 0) {
            j11 = 5000;
        }
        return stopServerOnCancellation(applicationEngine, j10, j11);
    }
}
