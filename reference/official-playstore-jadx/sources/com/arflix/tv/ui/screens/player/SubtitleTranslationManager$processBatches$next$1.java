package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.SubtitleTranslationManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager$PendingItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager$PendingItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationManager$processBatches$next$1", f = "SubtitleTranslationManager.kt", l = {72}, m = "invokeSuspend", v = 2)
public final class SubtitleTranslationManager$processBatches$next$1 extends f7.j implements r7.p<ka.k0, d7.d<? super SubtitleTranslationManager.PendingItem>, Object> {
    int label;
    final /* synthetic */ SubtitleTranslationManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleTranslationManager$processBatches$next$1(SubtitleTranslationManager subtitleTranslationManager, d7.d<? super SubtitleTranslationManager$processBatches$next$1> dVar) {
        super(2, dVar);
        this.this$0 = subtitleTranslationManager;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SubtitleTranslationManager$processBatches$next$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        ma.j jVar = this.this$0.queue;
        this.label = 1;
        Object objReceive = jVar.receive(this);
        e7.a aVar = e7.a.f15033i;
        return objReceive == aVar ? aVar : objReceive;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super SubtitleTranslationManager.PendingItem> dVar) {
        return ((SubtitleTranslationManager$processBatches$next$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
