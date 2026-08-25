package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/model/Addon;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$addCustomAddon$2", f = "StreamRepository.kt", l = {805, 806, 810}, m = "invokeSuspend", v = 2)
public final class StreamRepository$addCustomAddon$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $customName;
    final /* synthetic */ String $url;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$addCustomAddon$2(StreamRepository streamRepository, String str, String str2, d7.d<? super StreamRepository$addCustomAddon$2> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$url = str;
        this.$customName = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(Addon addon, Addon addon2) {
        return kotlin.jvm.internal.p.a(addon2.getId(), addon.getId());
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$addCustomAddon$2(this.this$0, this.$url, this.$customName, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
    
        if (com.arflix.tv.data.repository.StreamRepository.saveAddons$default(r5, r6, false, r12, 2, null) != r4) goto L32;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Exception {
        /*
            r12 = this;
            int r0 = r12.label
            r1 = 3
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L33
            if (r0 == r3) goto L2f
            if (r0 == r2) goto L27
            if (r0 != r1) goto L1f
            java.lang.Object r0 = r12.L$1
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r12.L$0
            com.arflix.tv.data.model.Addon r0 = (com.arflix.tv.data.model.Addon) r0
            k2.c.G(r13)     // Catch: java.lang.Exception -> L1c
            goto L8b
        L1c:
            r0 = move-exception
            r13 = r0
            goto L82
        L1f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L27:
            java.lang.Object r0 = r12.L$0
            com.arflix.tv.data.model.Addon r0 = (com.arflix.tv.data.model.Addon) r0
            k2.c.G(r13)     // Catch: java.lang.Exception -> L1c
            goto L5b
        L2f:
            k2.c.G(r13)     // Catch: java.lang.Exception -> L1c
            goto L45
        L33:
            k2.c.G(r13)
            com.arflix.tv.data.repository.StreamRepository r13 = r12.this$0     // Catch: java.lang.Exception -> L1c
            java.lang.String r0 = r12.$url     // Catch: java.lang.Exception -> L1c
            java.lang.String r5 = r12.$customName     // Catch: java.lang.Exception -> L1c
            r12.label = r3     // Catch: java.lang.Exception -> L1c
            java.lang.Object r13 = com.arflix.tv.data.repository.StreamRepository.access$hydrateCustomAddon(r13, r0, r5, r12)     // Catch: java.lang.Exception -> L1c
            if (r13 != r4) goto L45
            goto L81
        L45:
            com.arflix.tv.data.model.Addon r13 = (com.arflix.tv.data.model.Addon) r13     // Catch: java.lang.Exception -> L1c
            com.arflix.tv.data.repository.StreamRepository r0 = r12.this$0     // Catch: java.lang.Exception -> L1c
            na.j r0 = r0.getInstalledAddons()     // Catch: java.lang.Exception -> L1c
            r12.L$0 = r13     // Catch: java.lang.Exception -> L1c
            r12.label = r2     // Catch: java.lang.Exception -> L1c
            java.lang.Object r0 = na.y0.l(r0, r12)     // Catch: java.lang.Exception -> L1c
            if (r0 != r4) goto L58
            goto L81
        L58:
            r11 = r0
            r0 = r13
            r13 = r11
        L5b:
            java.util.Collection r13 = (java.util.Collection) r13     // Catch: java.lang.Exception -> L1c
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch: java.lang.Exception -> L1c
            r6.<init>(r13)     // Catch: java.lang.Exception -> L1c
            com.arflix.tv.data.repository.d0 r13 = new com.arflix.tv.data.repository.d0     // Catch: java.lang.Exception -> L1c
            r2 = 0
            r13.<init>(r0, r2)     // Catch: java.lang.Exception -> L1c
            kotlin.collections.x.M0(r6, r13)     // Catch: java.lang.Exception -> L1c
            r6.add(r0)     // Catch: java.lang.Exception -> L1c
            com.arflix.tv.data.repository.StreamRepository r5 = r12.this$0     // Catch: java.lang.Exception -> L1c
            r12.L$0 = r0     // Catch: java.lang.Exception -> L1c
            r13 = 0
            r12.L$1 = r13     // Catch: java.lang.Exception -> L1c
            r12.label = r1     // Catch: java.lang.Exception -> L1c
            r7 = 0
            r9 = 2
            r10 = 0
            r8 = r12
            java.lang.Object r13 = com.arflix.tv.data.repository.StreamRepository.saveAddons$default(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L1c
            if (r13 != r4) goto L8b
        L81:
            return r4
        L82:
            boolean r0 = r13 instanceof java.util.concurrent.CancellationException
            if (r0 != 0) goto L91
            x6.c0 r0 = new x6.c0
            r0.<init>(r13)
        L8b:
            x6.d0 r13 = new x6.d0
            r13.<init>(r0)
            return r13
        L91:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$addCustomAddon$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((StreamRepository$addCustomAddon$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
