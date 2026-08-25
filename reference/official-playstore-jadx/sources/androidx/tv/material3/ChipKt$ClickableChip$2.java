package androidx.tv.material3;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ChipKt$ClickableChip$2 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.p f5595i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TextStyle f5596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f5597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f5598n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ float f5599o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5600p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChipKt$ClickableChip$2(r7.p pVar, TextStyle textStyle, r7.p pVar2, r7.p pVar3, float f10, PaddingValues paddingValues) {
        super(3);
        this.f5595i = pVar;
        this.f5596l = textStyle;
        this.f5597m = pVar2;
        this.f5598n = pVar3;
        this.f5599o = f10;
        this.f5600p = paddingValues;
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
            ComposerKt.traceEventStart(278594973, i10, -1, "androidx.tv.material3.ClickableChip.<anonymous> (Chip.kt:372)");
        }
        ChipKt.m5983ChipContentHYR8e34(this.f5595i, this.f5596l, this.f5597m, null, this.f5598n, this.f5599o, this.f5600p, composer, 3072);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
