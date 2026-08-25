package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.Modifier;
import d7.d;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a<\u0010\b\u001a\u00020\u0000*\u00020\u00002'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function2;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "block", "dragAndDropSource", "(Landroidx/compose/ui/Modifier;Lr7/p;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidDragAndDropSource_androidKt {
    public static final Modifier dragAndDropSource(Modifier modifier, p<? super DragAndDropSourceScope, ? super d<? super t0>, ? extends Object> pVar) {
        return modifier.then(new DragAndDropSourceWithDefaultShadowElement(pVar));
    }
}
