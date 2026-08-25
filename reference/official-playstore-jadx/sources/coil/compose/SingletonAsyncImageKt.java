package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aÓ\u0001\u0010\u001e\u001a\u00020\f2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\n2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\f\u0018\u00010\n2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\f\u0018\u00010\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0095\u0001\u0010\u001e\u001a\u00020\f2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f0\n2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\f\u0018\u00010\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006$"}, d2 = {"", "model", "", "contentDescription", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/painter/Painter;", "placeholder", "error", "fallback", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Success;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Error;", "onError", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "AsyncImage-ylYTKUw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "AsyncImage", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "AsyncImage-3HmZ8SU", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;II)V", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SingletonAsyncImageKt {
    /* JADX INFO: renamed from: AsyncImage-3HmZ8SU, reason: not valid java name */
    public static final void m6053AsyncImage3HmZ8SU(Object obj, String str, Modifier modifier, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-941517612);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform = (i12 & 8) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : lVar;
        l<? super AsyncImagePainter.State, t0> lVar3 = (i12 & 16) != 0 ? null : lVar2;
        Alignment center = (i12 & 32) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i12 & 64) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i12 & 128) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i12 & 256) == 0 ? colorFilter : null;
        int iM3947getDefaultFilterQualityfv9h1I = (i12 & 512) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-941517612, i11, -1, "coil.compose.AsyncImage (SingletonAsyncImage.kt:113)");
        }
        int i13 = i11 << 3;
        float f12 = f11;
        ColorFilter colorFilter3 = colorFilter2;
        AsyncImageKt.m6026AsyncImageMvsnxeU(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composer, 6), modifier2, defaultTransform, lVar3, center, fit, f12, colorFilter3, iM3947getDefaultFilterQualityfv9h1I, composer, (i13 & 1879048192) | (i11 & 112) | 520 | (i13 & 7168) | (57344 & i13) | (458752 & i13) | (3670016 & i13) | (29360128 & i13) | (234881024 & i13), (i11 >> 27) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: renamed from: AsyncImage-ylYTKUw, reason: not valid java name */
    public static final void m6054AsyncImageylYTKUw(Object obj, String str, Modifier modifier, Painter painter, Painter painter2, Painter painter3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12, int i13) {
        composer.startReplaceableGroup(2027616330);
        Modifier modifier2 = (i13 & 4) != 0 ? Modifier.INSTANCE : modifier;
        Painter painter4 = (i13 & 8) != 0 ? null : painter;
        Painter painter5 = (i13 & 16) != 0 ? null : painter2;
        Painter painter6 = (i13 & 32) != 0 ? painter5 : painter3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i13 & 64) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i13 & 128) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i13 & 256) != 0 ? null : lVar3;
        Alignment center = (i13 & 512) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 1024) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 2048) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 4096) != 0 ? null : colorFilter;
        int iM3947getDefaultFilterQualityfv9h1I = (i13 & 8192) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2027616330, i11, i12, "coil.compose.AsyncImage (SingletonAsyncImage.kt:60)");
        }
        int i14 = i11 << 3;
        int i15 = i12 << 3;
        Modifier modifier3 = modifier2;
        Painter painter7 = painter4;
        Painter painter8 = painter5;
        l<? super AsyncImagePainter.State.Loading, t0> lVar7 = lVar4;
        l<? super AsyncImagePainter.State.Error, t0> lVar8 = lVar6;
        ContentScale contentScale2 = fit;
        ColorFilter colorFilter3 = colorFilter2;
        AsyncImageKt.m6027AsyncImageQ4Kwu38(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composer, 6), modifier3, painter7, painter8, painter6, lVar7, lVar5, lVar8, center, contentScale2, f11, colorFilter3, iM3947getDefaultFilterQualityfv9h1I, composer, (i14 & 7168) | (i11 & 112) | 2392584 | (29360128 & i14) | (234881024 & i14) | (i14 & 1879048192), ((i11 >> 27) & 14) | (i15 & 112) | (i15 & 896) | (i15 & 7168) | (i15 & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }
}
