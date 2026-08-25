package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ListItemKt$BaseListItem$1$1$2$1$1$1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.p f5666i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListItemKt$BaseListItem$1$1$2$1$1$1(r7.p pVar) {
        super(2);
        this.f5666i = pVar;
    }

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(676560428, i10, -1, "androidx.tv.material3.BaseListItem.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ListItem.kt:323)");
        }
        TextKt.ProvideTextStyle(MaterialTheme.f5670a.getTypography(composer, 6).f6312o, this.f5666i, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
