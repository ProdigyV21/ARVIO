package com.arflix.tv.data.repository.simkl;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler$submit$2$1", f = "SimklScrobbler.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 187, 123}, m = "invokeSuspend", v = 2)
public final class SimklScrobbler$submit$2$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ long $remaining;
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SimklScrobbler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimklScrobbler$submit$2$1(long j10, SimklScrobbler simklScrobbler, d<? super SimklScrobbler$submit$2$1> dVar) {
        super(2, dVar);
        this.$remaining = j10;
        this.this$0 = simklScrobbler;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SimklScrobbler$submit$2$1(this.$remaining, this.this$0, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
    
        if (r0.execute(r4, r10) == r5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            int r0 = r10.label
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r0 == 0) goto L35
            if (r0 == r4) goto L31
            if (r0 == r3) goto L25
            if (r0 != r2) goto L1d
            java.lang.Object r0 = r10.L$1
            com.arflix.tv.data.repository.simkl.SimklScrobbler$Command r0 = (com.arflix.tv.data.repository.simkl.SimklScrobbler.Command) r0
            java.lang.Object r0 = r10.L$0
            com.arflix.tv.data.repository.simkl.SimklScrobbler$Command r0 = (com.arflix.tv.data.repository.simkl.SimklScrobbler.Command) r0
            k2.c.G(r11)
            goto L97
        L1d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L25:
            java.lang.Object r0 = r10.L$1
            com.arflix.tv.data.repository.simkl.SimklScrobbler r0 = (com.arflix.tv.data.repository.simkl.SimklScrobbler) r0
            java.lang.Object r3 = r10.L$0
            ua.a r3 = (ua.a) r3
            k2.c.G(r11)
            goto L62
        L31:
            k2.c.G(r11)
            goto L4a
        L35:
            k2.c.G(r11)
            long r6 = r10.$remaining
            r8 = 1
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 >= 0) goto L41
            r6 = r8
        L41:
            r10.label = r4
            java.lang.Object r11 = ka.s0.a(r6, r10)
            if (r11 != r5) goto L4a
            goto L96
        L4a:
            com.arflix.tv.data.repository.simkl.SimklScrobbler r11 = r10.this$0
            ua.a r11 = com.arflix.tv.data.repository.simkl.SimklScrobbler.access$getQueueMutex$p(r11)
            com.arflix.tv.data.repository.simkl.SimklScrobbler r0 = r10.this$0
            r10.L$0 = r11
            r10.L$1 = r0
            r10.I$0 = r1
            r10.label = r3
            java.lang.Object r3 = r11.c(r10)
            if (r3 != r5) goto L61
            goto L96
        L61:
            r3 = r11
        L62:
            r11 = 0
            com.arflix.tv.data.repository.simkl.SimklScrobbler.access$setPendingJob$p(r0, r11)     // Catch: java.lang.Throwable -> L9a
            com.arflix.tv.data.repository.simkl.SimklScrobbler.access$setHasWritten$p(r0, r4)     // Catch: java.lang.Throwable -> L9a
            r7.a r4 = r0.getElapsedRealtimeMs$app_playRelease()     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r4 = r4.invoke()     // Catch: java.lang.Throwable -> L9a
            java.lang.Number r4 = (java.lang.Number) r4     // Catch: java.lang.Throwable -> L9a
            long r6 = r4.longValue()     // Catch: java.lang.Throwable -> L9a
            com.arflix.tv.data.repository.simkl.SimklScrobbler.access$setLastWriteAt$p(r0, r6)     // Catch: java.lang.Throwable -> L9a
            com.arflix.tv.data.repository.simkl.SimklScrobbler$Command r4 = com.arflix.tv.data.repository.simkl.SimklScrobbler.access$getPendingCommand$p(r0)     // Catch: java.lang.Throwable -> L9a
            com.arflix.tv.data.repository.simkl.SimklScrobbler.access$setPendingCommand$p(r0, r11)     // Catch: java.lang.Throwable -> L9a
            r3.b(r11)
            if (r4 == 0) goto L97
            com.arflix.tv.data.repository.simkl.SimklScrobbler r0 = r10.this$0
            r10.L$0 = r11
            r10.L$1 = r11
            r10.I$0 = r1
            r10.label = r2
            java.lang.Object r11 = com.arflix.tv.data.repository.simkl.SimklScrobbler.access$execute(r0, r4, r10)
            if (r11 != r5) goto L97
        L96:
            return r5
        L97:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        L9a:
            r0 = move-exception
            r3.b(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler$submit$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((SimklScrobbler$submit$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
