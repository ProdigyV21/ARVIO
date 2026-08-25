package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "<anonymous>", "(Lka/k0;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.ScrollableKt$NoOpOnDragStarted$1", f = "Scrollable.kt", l = {}, m = "invokeSuspend")
public final class ScrollableKt$NoOpOnDragStarted$1 extends j implements q<k0, Offset, d7.d<? super t0>, Object> {
    int label;

    public ScrollableKt$NoOpOnDragStarted$1(d7.d<? super ScrollableKt$NoOpOnDragStarted$1> dVar) {
        super(3, dVar);
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return m367invoked4ec7I((k0) obj, ((Offset) obj2).getPackedValue(), (d7.d) obj3);
    }

    /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
    public final Object m367invoked4ec7I(k0 k0Var, long j10, d7.d<? super t0> dVar) {
        return new ScrollableKt$NoOpOnDragStarted$1(dVar).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return t0.f22605a;
    }
}
