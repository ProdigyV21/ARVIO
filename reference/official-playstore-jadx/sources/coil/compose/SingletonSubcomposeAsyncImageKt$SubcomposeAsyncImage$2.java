package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public final class SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ float $alpha;
    final /* synthetic */ ColorFilter $colorFilter;
    final /* synthetic */ q<SubcomposeAsyncImageScope, Composer, Integer, t0> $content;
    final /* synthetic */ String $contentDescription;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ int $filterQuality;
    final /* synthetic */ Object $model;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<AsyncImagePainter.State, t0> $onState;
    final /* synthetic */ l<AsyncImagePainter.State, AsyncImagePainter.State> $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$2(Object obj, String str, Modifier modifier, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, q<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, t0> qVar, int i11, int i12, int i13) {
        super(2);
        this.$model = obj;
        this.$contentDescription = str;
        this.$modifier = modifier;
        this.$transform = lVar;
        this.$onState = lVar2;
        this.$alignment = alignment;
        this.$contentScale = contentScale;
        this.$alpha = f10;
        this.$colorFilter = colorFilter;
        this.$filterQuality = i10;
        this.$content = qVar;
        this.$$changed = i11;
        this.$$changed1 = i12;
        this.$$default = i13;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SingletonSubcomposeAsyncImageKt.m6057SubcomposeAsyncImage10Xjiaw(this.$model, this.$contentDescription, this.$modifier, this.$transform, this.$onState, this.$alignment, this.$contentScale, this.$alpha, this.$colorFilter, this.$filterQuality, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
