package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class p0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6283i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6284l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f6285m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6286n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6287o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6288p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6289q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ x6.o f6290r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f6291s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(boolean z, x6.o oVar, Modifier modifier, boolean z5, Object obj, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
        super(2);
        this.f6283i = i12;
        this.f6284l = z;
        this.f6290r = oVar;
        this.f6285m = modifier;
        this.f6286n = z5;
        this.f6291s = obj;
        this.f6287o = mutableInteractionSource;
        this.f6288p = i10;
        this.f6289q = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6283i) {
            case 0:
                ((Number) obj2).intValue();
                r7.l lVar = (r7.l) this.f6290r;
                CheckboxColors checkboxColors = (CheckboxColors) this.f6291s;
                CheckboxKt.Checkbox(this.f6284l, lVar, this.f6285m, this.f6286n, checkboxColors, this.f6287o, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6288p | 1), this.f6289q);
                break;
            default:
                ((Number) obj2).intValue();
                r7.a aVar = (r7.a) this.f6290r;
                RadioButtonColors radioButtonColors = (RadioButtonColors) this.f6291s;
                RadioButtonKt.RadioButton(this.f6284l, aVar, this.f6285m, this.f6286n, radioButtonColors, this.f6287o, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6288p | 1), this.f6289q);
                break;
        }
        return x6.t0.f22605a;
    }
}
