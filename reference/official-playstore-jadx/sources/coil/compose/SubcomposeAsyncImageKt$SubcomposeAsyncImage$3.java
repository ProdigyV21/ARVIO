package coil.compose;

import android.view.SizeResolver;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class SubcomposeAsyncImageKt$SubcomposeAsyncImage$3 extends r implements q<BoxWithConstraintsScope, Composer, Integer, t0> {
    final /* synthetic */ int $$dirty1;
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ float $alpha;
    final /* synthetic */ ColorFilter $colorFilter;
    final /* synthetic */ q<SubcomposeAsyncImageScope, Composer, Integer, t0> $content;
    final /* synthetic */ String $contentDescription;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ AsyncImagePainter $painter;
    final /* synthetic */ SizeResolver $sizeResolver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SubcomposeAsyncImageKt$SubcomposeAsyncImage$3(SizeResolver sizeResolver, q<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, t0> qVar, AsyncImagePainter asyncImagePainter, String str, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10) {
        super(3);
        this.$sizeResolver = sizeResolver;
        this.$content = qVar;
        this.$painter = asyncImagePainter;
        this.$contentDescription = str;
        this.$alignment = alignment;
        this.$contentScale = contentScale;
        this.$alpha = f10;
        this.$colorFilter = colorFilter;
        this.$$dirty1 = i10;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((BoxWithConstraintsScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i10) {
        if ((i10 & 14) == 0) {
            i10 |= composer.changed(boxWithConstraintsScope) ? 4 : 2;
        }
        if ((i10 & 91) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1964284792, i10, -1, "coil.compose.SubcomposeAsyncImage.<anonymous> (SubcomposeAsyncImage.kt:154)");
        }
        ((ConstraintsSizeResolver) this.$sizeResolver).m6039setConstraintsBRTryo0(boxWithConstraintsScope.mo465getConstraintsmsEJaDk());
        this.$content.invoke(new RealSubcomposeAsyncImageScope(boxWithConstraintsScope, this.$painter, this.$contentDescription, this.$alignment, this.$contentScale, this.$alpha, this.$colorFilter), composer, Integer.valueOf(this.$$dirty1 & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
