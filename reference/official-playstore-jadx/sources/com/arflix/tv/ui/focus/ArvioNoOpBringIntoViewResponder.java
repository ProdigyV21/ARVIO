package com.arflix.tv.ui.focus;

import androidx.compose.foundation.relocation.BringIntoViewResponder;
import androidx.compose.ui.geometry.Rect;
import d7.d;
import kotlin.Metadata;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\n\u001a\u00020\t2\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\bH\u0096@¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/focus/ArvioNoOpBringIntoViewResponder;", "Landroidx/compose/foundation/relocation/BringIntoViewResponder;", "<init>", "()V", "Landroidx/compose/ui/geometry/Rect;", "localRect", "calculateRectForParent", "(Landroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "Lkotlin/Function0;", "Lx6/t0;", "bringChildIntoView", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class ArvioNoOpBringIntoViewResponder implements BringIntoViewResponder {
    public static final ArvioNoOpBringIntoViewResponder INSTANCE = new ArvioNoOpBringIntoViewResponder();

    private ArvioNoOpBringIntoViewResponder() {
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Object bringChildIntoView(a<Rect> aVar, d<? super t0> dVar) {
        return t0.f22605a;
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Rect calculateRectForParent(Rect localRect) {
        return localRect;
    }
}
