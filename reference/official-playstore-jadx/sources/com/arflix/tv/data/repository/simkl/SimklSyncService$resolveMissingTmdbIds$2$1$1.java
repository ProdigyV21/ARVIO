package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.repository.simkl.SimklSyncService;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import ua.f;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$resolveMissingTmdbIds$2$1$1", f = "SimklSyncService.kt", l = {863, 470}, m = "invokeSuspend", v = 2)
public final class SimklSyncService$resolveMissingTmdbIds$2$1$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ SimklSyncService.TmdbResolutionCandidate $candidate;
    final /* synthetic */ f $permits;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ SimklSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimklSyncService$resolveMissingTmdbIds$2$1$1(f fVar, SimklSyncService simklSyncService, SimklSyncService.TmdbResolutionCandidate tmdbResolutionCandidate, d<? super SimklSyncService$resolveMissingTmdbIds$2$1$1> dVar) {
        super(2, dVar);
        this.$permits = fVar;
        this.this$0 = simklSyncService;
        this.$candidate = tmdbResolutionCandidate;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SimklSyncService$resolveMissingTmdbIds$2$1$1(this.$permits, this.this$0, this.$candidate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008b A[Catch: all -> 0x001e, TRY_LEAVE, TryCatch #1 {all -> 0x001e, blocks: (B:7:0x0019, B:25:0x0087, B:27:0x008b), top: B:37:0x0019 }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
        /*
            r10 = this;
            int r0 = r10.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L3c
            if (r0 == r3) goto L29
            if (r0 != r2) goto L21
            java.lang.Object r0 = r10.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r10.L$1
            com.arflix.tv.data.repository.simkl.SimklSyncService r1 = (com.arflix.tv.data.repository.simkl.SimklSyncService) r1
            java.lang.Object r2 = r10.L$0
            ua.f r2 = (ua.f) r2
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L1e
            goto L87
        L1e:
            r11 = move-exception
            goto L9c
        L21:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L29:
            int r0 = r10.I$0
            java.lang.Object r3 = r10.L$2
            com.arflix.tv.data.repository.simkl.SimklSyncService$TmdbResolutionCandidate r3 = (com.arflix.tv.data.repository.simkl.SimklSyncService.TmdbResolutionCandidate) r3
            java.lang.Object r5 = r10.L$1
            com.arflix.tv.data.repository.simkl.SimklSyncService r5 = (com.arflix.tv.data.repository.simkl.SimklSyncService) r5
            java.lang.Object r6 = r10.L$0
            ua.f r6 = (ua.f) r6
            k2.c.G(r11)
            r11 = r6
            goto L59
        L3c:
            k2.c.G(r11)
            ua.f r11 = r10.$permits
            com.arflix.tv.data.repository.simkl.SimklSyncService r0 = r10.this$0
            com.arflix.tv.data.repository.simkl.SimklSyncService$TmdbResolutionCandidate r5 = r10.$candidate
            r10.L$0 = r11
            r10.L$1 = r0
            r10.L$2 = r5
            r10.I$0 = r1
            r10.label = r3
            java.lang.Object r3 = r11.a(r10)
            if (r3 != r4) goto L56
            goto L82
        L56:
            r3 = r5
            r5 = r0
            r0 = r1
        L59:
            com.arflix.tv.data.api.SimklIds r6 = r3.getIds()     // Catch: java.lang.Throwable -> L99
            com.arflix.tv.data.model.MediaType r7 = r3.getMediaType()     // Catch: java.lang.Throwable -> L99
            java.lang.String r8 = r3.getTitle()     // Catch: java.lang.Throwable -> L99
            java.lang.Integer r9 = r3.getYear()     // Catch: java.lang.Throwable -> L99
            java.lang.String r6 = com.arflix.tv.data.repository.simkl.SimklSyncService.access$resolutionKey(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L99
            if (r6 != 0) goto L70
            goto L93
        L70:
            r10.L$0 = r11     // Catch: java.lang.Throwable -> L99
            r10.L$1 = r5     // Catch: java.lang.Throwable -> L99
            r10.L$2 = r6     // Catch: java.lang.Throwable -> L99
            r10.I$0 = r0     // Catch: java.lang.Throwable -> L99
            r10.I$1 = r1     // Catch: java.lang.Throwable -> L99
            r10.label = r2     // Catch: java.lang.Throwable -> L99
            java.lang.Object r0 = com.arflix.tv.data.repository.simkl.SimklSyncService.access$resolveCandidate(r5, r3, r10)     // Catch: java.lang.Throwable -> L99
            if (r0 != r4) goto L83
        L82:
            return r4
        L83:
            r2 = r11
            r11 = r0
            r1 = r5
            r0 = r6
        L87:
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch: java.lang.Throwable -> L1e
            if (r11 == 0) goto L92
            j$.util.concurrent.ConcurrentHashMap r1 = com.arflix.tv.data.repository.simkl.SimklSyncService.access$getResolvedExternalIds$p(r1)     // Catch: java.lang.Throwable -> L1e
            r1.put(r0, r11)     // Catch: java.lang.Throwable -> L1e
        L92:
            r11 = r2
        L93:
            r11.release()
            x6.t0 r11 = x6.t0.f22605a
            return r11
        L99:
            r0 = move-exception
            r2 = r11
            r11 = r0
        L9c:
            r2.release()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService$resolveMissingTmdbIds$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((SimklSyncService$resolveMissingTmdbIds$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
