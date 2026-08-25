package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import d7.d;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\b\u001a\u00020\u00072\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H§@¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewResponder;", "", "Landroidx/compose/ui/geometry/Rect;", "localRect", "calculateRectForParent", "(Landroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "Lkotlin/Function0;", "Lx6/t0;", "bringChildIntoView", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface BringIntoViewResponder {
    Object bringChildIntoView(r7.a<Rect> aVar, d<? super t0> dVar);

    Rect calculateRectForParent(Rect localRect);
}
