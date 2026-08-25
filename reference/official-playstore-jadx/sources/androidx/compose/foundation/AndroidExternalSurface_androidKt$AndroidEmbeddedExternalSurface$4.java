package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ boolean $isOpaque;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<AndroidExternalSurfaceScope, t0> $onInit;
    final /* synthetic */ long $surfaceSize;
    final /* synthetic */ float[] $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AndroidExternalSurface_androidKt$AndroidEmbeddedExternalSurface$4(Modifier modifier, boolean z, long j10, float[] fArr, l<? super AndroidExternalSurfaceScope, t0> lVar, int i10, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$isOpaque = z;
        this.$surfaceSize = j10;
        this.$transform = fArr;
        this.$onInit = lVar;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        AndroidExternalSurface_androidKt.m174AndroidEmbeddedExternalSurfacesv6N_fY(this.$modifier, this.$isOpaque, this.$surfaceSize, this.$transform, this.$onInit, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
