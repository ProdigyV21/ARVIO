package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u009f\u0001\u0010\u0015\u001a\u00020\u00122\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001aa\u0010\u0015\u001a\u00020\u00122\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u00062\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"", "model", "Landroidx/compose/ui/graphics/painter/Painter;", "placeholder", "error", "fallback", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Success;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Error;", "onError", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter-MqR-F_0", "(Ljava/lang/Object;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "rememberAsyncImagePainter-19ie5dc", "(Ljava/lang/Object;Lr7/l;Lr7/l;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SingletonAsyncImagePainterKt {
    /* JADX INFO: renamed from: rememberAsyncImagePainter-19ie5dc, reason: not valid java name */
    public static final AsyncImagePainter m6055rememberAsyncImagePainter19ie5dc(Object obj, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, ContentScale contentScale, int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-1494234083);
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform = (i12 & 2) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : lVar;
        l<? super AsyncImagePainter.State, t0> lVar3 = (i12 & 4) != 0 ? null : lVar2;
        ContentScale fit = (i12 & 8) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        int iM3947getDefaultFilterQualityfv9h1I = (i12 & 16) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1494234083, i11, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:97)");
        }
        int i13 = i11 << 3;
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(obj, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composer, 6), defaultTransform, lVar3, fit, iM3947getDefaultFilterQualityfv9h1I, composer, (i13 & 896) | 72 | (i13 & 7168) | (57344 & i13) | (i13 & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    /* JADX INFO: renamed from: rememberAsyncImagePainter-MqR-F_0, reason: not valid java name */
    public static final AsyncImagePainter m6056rememberAsyncImagePainterMqRF_0(Object obj, Painter painter, Painter painter2, Painter painter3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, ContentScale contentScale, int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(533921043);
        if ((i12 & 2) != 0) {
            painter = null;
        }
        Painter painter4 = (i12 & 4) != 0 ? null : painter2;
        Painter painter5 = (i12 & 8) != 0 ? painter4 : painter3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i12 & 16) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i12 & 32) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i12 & 64) != 0 ? null : lVar3;
        ContentScale fit = (i12 & 128) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        int iM3947getDefaultFilterQualityfv9h1I = (i12 & 256) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(533921043, i11, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:54)");
        }
        int i13 = i11 << 3;
        AsyncImagePainter asyncImagePainterM6034rememberAsyncImagePainter3HmZ8SU = AsyncImagePainterKt.m6034rememberAsyncImagePainter3HmZ8SU(obj, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composer, 6), painter, painter4, painter5, lVar4, lVar5, lVar6, fit, iM3947getDefaultFilterQualityfv9h1I, composer, (458752 & i13) | 37448 | (3670016 & i13) | (29360128 & i13) | (234881024 & i13) | (i13 & 1879048192), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return asyncImagePainterM6034rememberAsyncImagePainter3HmZ8SU;
    }
}
