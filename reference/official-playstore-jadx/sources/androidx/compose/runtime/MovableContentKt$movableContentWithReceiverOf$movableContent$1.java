package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class MovableContentKt$movableContentWithReceiverOf$movableContent$1<R> extends r implements q<R, Composer, Integer, t0> {
    final /* synthetic */ q<R, Composer, Integer, t0> $content;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MovableContentKt$movableContentWithReceiverOf$movableContent$1(q<? super R, ? super Composer, ? super Integer, t0> qVar) {
        super(3);
        this.$content = qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(R r4, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= (i10 & 8) == 0 ? composer.changed(r4) : composer.changedInstance(r4) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(250838178, i10, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:170)");
        }
        this.$content.invoke(r4, composer, Integer.valueOf(i10 & 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
