package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class AlertDialogKt$AlertDialogContent$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $buttonContentColor;
    final /* synthetic */ p<Composer, Integer, t0> $buttons;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ p<Composer, Integer, t0> $icon;
    final /* synthetic */ long $iconContentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ p<Composer, Integer, t0> $text;
    final /* synthetic */ long $textContentColor;
    final /* synthetic */ p<Composer, Integer, t0> $title;
    final /* synthetic */ long $titleContentColor;
    final /* synthetic */ float $tonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AlertDialogKt$AlertDialogContent$2(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, Shape shape, long j10, float f10, long j11, long j12, long j13, long j14, int i10, int i11, int i12) {
        super(2);
        this.$buttons = pVar;
        this.$modifier = modifier;
        this.$icon = pVar2;
        this.$title = pVar3;
        this.$text = pVar4;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$tonalElevation = f10;
        this.$buttonContentColor = j11;
        this.$iconContentColor = j12;
        this.$titleContentColor = j13;
        this.$textContentColor = j14;
        this.$$changed = i10;
        this.$$changed1 = i11;
        this.$$default = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        AlertDialogKt.m1215AlertDialogContent4hvqGtA(this.$buttons, this.$modifier, this.$icon, this.$title, this.$text, this.$shape, this.$containerColor, this.$tonalElevation, this.$buttonContentColor, this.$iconContentColor, this.$titleContentColor, this.$textContentColor, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
