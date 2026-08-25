package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.PlayerViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$loadMedia$4$16", f = "PlayerViewModel.kt", l = {1051}, m = "emit", v = 2)
public final class PlayerViewModel$loadMedia$4$16$emit$1 extends f7.c {
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlayerViewModel.AnonymousClass4.AnonymousClass16<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerViewModel$loadMedia$4$16$emit$1(PlayerViewModel.AnonymousClass4.AnonymousClass16<? super T> anonymousClass16, d7.d<? super PlayerViewModel$loadMedia$4$16$emit$1> dVar) {
        super(dVar);
        this.this$0 = anonymousClass16;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type d7.d to com.arflix.tv.ui.screens.player.PlayerViewModel$loadMedia$4$16$emit$1 for r1v1 'this'  d7.d
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // f7.a
    public final java.lang.Object invokeSuspend(java.lang.Object r2) {
        /*
            r1 = this;
            r1.result = r2
            int r2 = r1.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 | r0
            r1.label = r2
            com.arflix.tv.ui.screens.player.PlayerViewModel$loadMedia$4$16<T> r2 = r1.this$0
            r0 = 0
            java.lang.Object r2 = r2.emit(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerViewModel$loadMedia$4$16$emit$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
