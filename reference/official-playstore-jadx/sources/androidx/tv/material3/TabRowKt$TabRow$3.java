package androidx.tv.material3;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TabRowKt$TabRow$3 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.q f5768i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.p f5769l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.r f5770m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f5771n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabRowKt$TabRow$3(MutableState mutableState, r7.p pVar, r7.q qVar, r7.r rVar) {
        super(3);
        this.f5768i = qVar;
        this.f5769l = pVar;
        this.f5770m = rVar;
        this.f5771n = mutableState;
    }

    @Override // r7.q
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((BoxScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(859340465, i10, -1, "androidx.tv.material3.TabRow.<anonymous> (TabRow.kt:121)");
        }
        composer.startReplaceableGroup(-1114371258);
        r7.q qVar = this.f5768i;
        boolean zChanged = composer.changed(qVar);
        r7.p pVar = this.f5769l;
        boolean zChanged2 = zChanged | composer.changed(pVar);
        r7.r rVar = this.f5770m;
        boolean zChanged3 = zChanged2 | composer.changed(rVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new j4(this.f5771n, pVar, qVar, rVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        SubcomposeLayoutKt.SubcomposeLayout(null, (r7.p) objRememberedValue, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
