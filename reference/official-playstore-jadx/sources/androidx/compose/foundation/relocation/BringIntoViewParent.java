package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import d7.d;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bà\u0080\u0001\u0018\u00002\u00020\u0001J(\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H¦@¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewParent;", "", "Landroidx/compose/ui/layout/LayoutCoordinates;", "childCoordinates", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Rect;", "boundsProvider", "Lx6/t0;", "bringChildIntoView", "(Landroidx/compose/ui/layout/LayoutCoordinates;Lr7/a;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface BringIntoViewParent {
    Object bringChildIntoView(LayoutCoordinates layoutCoordinates, r7.a<Rect> aVar, d<? super t0> dVar);
}
