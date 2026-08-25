package coil.compose;

import a0.c;
import android.view.SizeResolver;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.media3.exoplayer.RendererCapabilities;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a¯\u0002\u0010#\u001a\u00020\u000b2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062&\b\u0002\u0010\u000e\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\r2&\b\u0002\u0010\u0010\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\r2&\b\u0002\u0010\u0012\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00132\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00132\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"\u001a»\u0001\u0010#\u001a\u00020\u000b2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0014\b\u0002\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$0\u00132\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010 \u001a\u00020\u001f2\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\f¢\u0006\u0002\b\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a]\u0010,\u001a\u00020\u000b*\u00020\t2\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010+\u001a\u00020*2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0007¢\u0006\u0004\b,\u0010-\u001a\u0097\u0001\u0010.\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b0\u0013¢\u0006\u0002\b\f¢\u0006\u0002\b\r2$\u0010\u000e\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\r2$\u0010\u0010\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\r2$\u0010\u0012\u001a \u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\b\f¢\u0006\u0002\b\rH\u0003¢\u0006\u0004\b.\u0010/\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00060"}, d2 = {"", "model", "", "contentDescription", "Lcoil/ImageLoader;", "imageLoader", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function2;", "Lcoil/compose/SubcomposeAsyncImageScope;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "loading", "Lcoil/compose/AsyncImagePainter$State$Success;", "success", "Lcoil/compose/AsyncImagePainter$State$Error;", "error", "Lkotlin/Function1;", "onLoading", "onSuccess", "onError", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "SubcomposeAsyncImage-Q4Kwu38", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lr7/r;Lr7/r;Lr7/r;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "SubcomposeAsyncImage", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "content", "SubcomposeAsyncImage-sKDTAoQ", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILr7/q;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/painter/Painter;", "painter", "SubcomposeAsyncImageContent", "(Lcoil/compose/SubcomposeAsyncImageScope;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;II)V", "contentOf", "(Lr7/r;Lr7/r;Lr7/r;)Lr7/q;", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SubcomposeAsyncImageKt {

    /* JADX INFO: renamed from: coil.compose.SubcomposeAsyncImageKt$SubcomposeAsyncImageContent$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Alignment $alignment;
        final /* synthetic */ float $alpha;
        final /* synthetic */ ColorFilter $colorFilter;
        final /* synthetic */ String $contentDescription;
        final /* synthetic */ ContentScale $contentScale;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Painter $painter;
        final /* synthetic */ SubcomposeAsyncImageScope $this_SubcomposeAsyncImageContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SubcomposeAsyncImageScope subcomposeAsyncImageScope, Modifier modifier, Painter painter, String str, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, int i11) {
            super(2);
            this.$this_SubcomposeAsyncImageContent = subcomposeAsyncImageScope;
            this.$modifier = modifier;
            this.$painter = painter;
            this.$contentDescription = str;
            this.$alignment = alignment;
            this.$contentScale = contentScale;
            this.$alpha = f10;
            this.$colorFilter = colorFilter;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SubcomposeAsyncImageKt.SubcomposeAsyncImageContent(this.$this_SubcomposeAsyncImageContent, this.$modifier, this.$painter, this.$contentDescription, this.$alignment, this.$contentScale, this.$alpha, this.$colorFilter, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: coil.compose.SubcomposeAsyncImageKt$contentOf$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcoil/compose/SubcomposeAsyncImageScope;", "Lx6/t0;", "invoke", "(Lcoil/compose/SubcomposeAsyncImageScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C06421 extends r implements q<SubcomposeAsyncImageScope, Composer, Integer, t0> {
        final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Error, Composer, Integer, t0> $error;
        final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Loading, Composer, Integer, t0> $loading;
        final /* synthetic */ r7.r<SubcomposeAsyncImageScope, AsyncImagePainter.State.Success, Composer, Integer, t0> $success;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06421(r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar2, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar3) {
            super(3);
            this.$loading = rVar;
            this.$success = rVar2;
            this.$error = rVar3;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SubcomposeAsyncImageScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        /* JADX WARN: Removed duplicated region for block: B:47:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(coil.compose.SubcomposeAsyncImageScope r12, androidx.compose.runtime.Composer r13, int r14) {
            /*
                Method dump skipped, instruction units count: 203
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.compose.SubcomposeAsyncImageKt.C06421.invoke(coil.compose.SubcomposeAsyncImageScope, androidx.compose.runtime.Composer, int):void");
        }
    }

    /* JADX INFO: renamed from: SubcomposeAsyncImage-Q4Kwu38, reason: not valid java name */
    public static final void m6059SubcomposeAsyncImageQ4Kwu38(Object obj, String str, ImageLoader imageLoader, Modifier modifier, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar2, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12, int i13) {
        int iM3947getDefaultFilterQualityfv9h1I;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(934816934);
        Modifier modifier2 = (i13 & 8) != 0 ? Modifier.INSTANCE : modifier;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar4 = (i13 & 16) != 0 ? null : rVar;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar5 = (i13 & 32) != 0 ? null : rVar2;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar6 = (i13 & 64) != 0 ? null : rVar3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i13 & 128) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i13 & 256) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i13 & 512) != 0 ? null : lVar3;
        Alignment center = (i13 & 1024) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 2048) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 4096) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 8192) != 0 ? null : colorFilter;
        if ((i13 & 16384) != 0) {
            iM3947getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
            i14 = i12 & (-57345);
        } else {
            iM3947getDefaultFilterQualityfv9h1I = i10;
            i14 = i12;
        }
        Modifier modifier3 = modifier2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(934816934, i11, i14, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:65)");
        }
        l<? super AsyncImagePainter.State.Loading, t0> lVar7 = lVar4;
        l<AsyncImagePainter.State, t0> lVarOnStateOf = UtilsKt.onStateOf(lVar7, lVar5, lVar6);
        l<? super AsyncImagePainter.State.Error, t0> lVar8 = lVar6;
        ContentScale contentScale2 = fit;
        ColorFilter colorFilter3 = colorFilter2;
        int i15 = i14;
        int i16 = i15 << 18;
        int i17 = (i11 & 112) | 520 | (i11 & 7168) | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024) | (i16 & 1879048192);
        int i18 = (i15 >> 12) & 14;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar7 = rVar6;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar8 = rVar5;
        l<? super AsyncImagePainter.State.Success, t0> lVar9 = lVar5;
        Alignment alignment2 = center;
        float f12 = f11;
        r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar9 = rVar4;
        int i19 = iM3947getDefaultFilterQualityfv9h1I;
        m6060SubcomposeAsyncImagesKDTAoQ(obj, str, imageLoader, modifier3, null, lVarOnStateOf, alignment2, contentScale2, f12, colorFilter3, i19, contentOf(rVar4, rVar5, rVar6), composerStartRestartGroup, i17, i18, 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new SubcomposeAsyncImageKt$SubcomposeAsyncImage$1(obj, str, imageLoader, modifier3, rVar9, rVar8, rVar7, lVar7, lVar9, lVar8, alignment2, contentScale2, f12, colorFilter3, i19, i11, i12, i13));
    }

    /* JADX INFO: renamed from: SubcomposeAsyncImage-sKDTAoQ, reason: not valid java name */
    public static final void m6060SubcomposeAsyncImagesKDTAoQ(Object obj, String str, ImageLoader imageLoader, Modifier modifier, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, q<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i11, int i12, int i13) {
        int iM3947getDefaultFilterQualityfv9h1I;
        int i14;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar3;
        Alignment alignment2;
        int i15;
        l<? super AsyncImagePainter.State, t0> lVar4;
        Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(10937794);
        Modifier modifier3 = (i13 & 8) != 0 ? Modifier.INSTANCE : modifier;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform = (i13 & 16) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : lVar;
        l<? super AsyncImagePainter.State, t0> lVar5 = (i13 & 32) != 0 ? null : lVar2;
        Alignment center = (i13 & 64) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 128) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 256) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 512) != 0 ? null : colorFilter;
        if ((i13 & 1024) != 0) {
            iM3947getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
            i14 = i12 & (-15);
        } else {
            iM3947getDefaultFilterQualityfv9h1I = i10;
            i14 = i12;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(10937794, i11, i14, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:117)");
        }
        ImageRequest imageRequestUpdateRequest = AsyncImageKt.updateRequest(UtilsKt.requestOf(obj, composerStartRestartGroup, 8), fit, composerStartRestartGroup, 8 | ((i11 >> 18) & 112));
        int i16 = i11 >> 6;
        int i17 = i11 >> 9;
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(imageRequestUpdateRequest, imageLoader, defaultTransform, lVar5, fit, iM3947getDefaultFilterQualityfv9h1I, composerStartRestartGroup, (i16 & 7168) | (i16 & 896) | 72 | (i17 & 57344) | ((i14 << 15) & 458752), 0);
        SizeResolver sizeResolver = imageRequestUpdateRequest.getSizeResolver();
        if (sizeResolver instanceof ConstraintsSizeResolver) {
            lVar3 = defaultTransform;
            alignment2 = center;
            composerStartRestartGroup.startReplaceableGroup(-247978567);
            Modifier modifier4 = modifier3;
            BoxWithConstraintsKt.BoxWithConstraints(modifier4, alignment2, true, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1964284792, true, new SubcomposeAsyncImageKt$SubcomposeAsyncImage$3(sizeResolver, qVar, asyncImagePainterM6035rememberAsyncImagePainter5jETZwI, str, alignment2, fit, f11, colorFilter2, i14)), composerStartRestartGroup, (i17 & 14) | 3456 | ((i11 >> 15) & 112), 0);
            i15 = iM3947getDefaultFilterQualityfv9h1I;
            lVar4 = lVar5;
            modifier2 = modifier4;
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.startReplaceableGroup(-247979203);
            int i18 = (i17 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK | ((i11 >> 15) & 112);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, true, composerStartRestartGroup, ((i18 >> 3) & 14) | 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            a<ComposeUiNode> constructor = companion.getConstructor();
            lVar3 = defaultTransform;
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier3);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU = c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            Alignment alignment3 = center;
            alignment2 = alignment3;
            qVar.invoke(new RealSubcomposeAsyncImageScope(BoxScopeInstance.INSTANCE, asyncImagePainterM6035rememberAsyncImagePainter5jETZwI, str, alignment3, fit, f11, colorFilter2), composerStartRestartGroup, Integer.valueOf(i14 & 112));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            i15 = iM3947getDefaultFilterQualityfv9h1I;
            lVar4 = lVar5;
            modifier2 = modifier3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new SubcomposeAsyncImageKt$SubcomposeAsyncImage$4(obj, str, imageLoader, modifier2, lVar3, lVar4, alignment2, fit, f11, colorFilter2, i15, qVar, i11, i12, i13));
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01e3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SubcomposeAsyncImageContent(coil.compose.SubcomposeAsyncImageScope r20, androidx.compose.ui.Modifier r21, androidx.compose.ui.graphics.painter.Painter r22, java.lang.String r23, androidx.compose.ui.Alignment r24, androidx.compose.ui.layout.ContentScale r25, float r26, androidx.compose.ui.graphics.ColorFilter r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.compose.SubcomposeAsyncImageKt.SubcomposeAsyncImageContent(coil.compose.SubcomposeAsyncImageScope, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.painter.Painter, java.lang.String, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, float, androidx.compose.ui.graphics.ColorFilter, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final q<SubcomposeAsyncImageScope, Composer, Integer, t0> contentOf(r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar2, r7.r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar3) {
        return (rVar == null && rVar2 == null && rVar3 == null) ? ComposableSingletons$SubcomposeAsyncImageKt.INSTANCE.m6038getLambda1$coil_compose_base_release() : ComposableLambdaKt.composableLambdaInstance(-1302781228, true, new C06421(rVar, rVar2, rVar3));
    }
}
