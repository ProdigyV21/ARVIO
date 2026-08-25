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
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public final class SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ float $alpha;
    final /* synthetic */ ColorFilter $colorFilter;
    final /* synthetic */ String $contentDescription;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Error, Composer, Integer, t0> $error;
    final /* synthetic */ int $filterQuality;
    final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Loading, Composer, Integer, t0> $loading;
    final /* synthetic */ Object $model;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<AsyncImagePainter.State.Error, t0> $onError;
    final /* synthetic */ l<AsyncImagePainter.State.Loading, t0> $onLoading;
    final /* synthetic */ l<AsyncImagePainter.State.Success, t0> $onSuccess;
    final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Success, Composer, Integer, t0> $success;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$1(Object obj, String str, Modifier modifier, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar2, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, int i11, int i12, int i13) {
        super(2);
        this.$model = obj;
        this.$contentDescription = str;
        this.$modifier = modifier;
        this.$loading = rVar;
        this.$success = rVar2;
        this.$error = rVar3;
        this.$onLoading = lVar;
        this.$onSuccess = lVar2;
        this.$onError = lVar3;
        this.$alignment = alignment;
        this.$contentScale = contentScale;
        this.$alpha = f10;
        this.$colorFilter = colorFilter;
        this.$filterQuality = i10;
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
        SingletonSubcomposeAsyncImageKt.m6058SubcomposeAsyncImageylYTKUw(this.$model, this.$contentDescription, this.$modifier, this.$loading, this.$success, this.$error, this.$onLoading, this.$onSuccess, this.$onError, this.$alignment, this.$contentScale, this.$alpha, this.$colorFilter, this.$filterQuality, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
