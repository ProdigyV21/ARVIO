package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import r7.t;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0004\b\u0003\u0010\u00032*\u0010\u0005\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00040\u0004H\u000b¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"P1", "P2", "P3", "P4", "Lx6/x;", "it", "Lx6/t0;", "invoke", "(Lx6/x;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class MovableContentKt$movableContentOf$movableContent$4 extends r implements q<x, Composer, Integer, t0> {
    final /* synthetic */ t<P1, P2, P3, P4, Composer, Integer, t0> $content;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MovableContentKt$movableContentOf$movableContent$4(t<? super P1, ? super P2, ? super P3, ? super P4, ? super Composer, ? super Integer, t0> tVar) {
        super(3);
        this.$content = tVar;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((x) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(x xVar, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= (i10 & 8) == 0 ? composer.changed(xVar) : composer.changedInstance(xVar) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1876318581, i10, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:144)");
        }
        t<P1, P2, P3, P4, Composer, Integer, t0> tVar = this.$content;
        Object obj = xVar.f22608i;
        Object obj2 = ((x) obj).f22608i;
        Object obj3 = ((x) obj).f22609l;
        Object obj4 = xVar.f22609l;
        tVar.invoke(obj2, obj3, ((x) obj4).f22608i, ((x) obj4).f22609l, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
