package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import kotlin.Metadata;
import r7.l;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a§\u0002\u0010!\u001a\u00020\t2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042&\b\u0002\u0010\f\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2&\b\u0002\u0010\u000e\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2&\b\u0002\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001a³\u0001\u0010!\u001a\u00020\t2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"0\u00112\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u001c\u0010%\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006("}, d2 = {"", "model", "", "contentDescription", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function2;", "Lcoil/compose/SubcomposeAsyncImageScope;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "loading", "Lcoil/compose/AsyncImagePainter$State$Success;", "success", "Lcoil/compose/AsyncImagePainter$State$Error;", "error", "Lkotlin/Function1;", "onLoading", "onSuccess", "onError", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "SubcomposeAsyncImage-ylYTKUw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lr7/r;Lr7/r;Lr7/r;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "SubcomposeAsyncImage", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "content", "SubcomposeAsyncImage-10Xjiaw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILr7/q;Landroidx/compose/runtime/Composer;III)V", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SingletonSubcomposeAsyncImageKt {
    /* JADX INFO: renamed from: SubcomposeAsyncImage-10Xjiaw, reason: not valid java name */
    public static final void m6057SubcomposeAsyncImage10Xjiaw(Object obj, String str, Modifier modifier, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, q<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i11, int i12, int i13) {
        int i14;
        l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform;
        int i15;
        int iM3947getDefaultFilterQualityfv9h1I;
        Composer composerStartRestartGroup = composer.startRestartGroup(1571662781);
        Modifier modifier2 = (i13 & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i13 & 8) != 0) {
            i14 = i11;
            i15 = i14 & (-7169);
            defaultTransform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        } else {
            i14 = i11;
            defaultTransform = lVar;
            i15 = i14;
        }
        l<? super AsyncImagePainter.State, t0> lVar3 = (i13 & 16) != 0 ? null : lVar2;
        Alignment center = (i13 & 32) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 64) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 128) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 256) != 0 ? null : colorFilter;
        if ((i13 & 512) != 0) {
            i15 &= -1879048193;
            iM3947getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        } else {
            iM3947getDefaultFilterQualityfv9h1I = i10;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1571662781, i15, i12, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:112)");
        }
        int i16 = i15 << 3;
        SubcomposeAsyncImageKt.m6060SubcomposeAsyncImagesKDTAoQ(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composerStartRestartGroup, 6), modifier2, defaultTransform, lVar3, center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, qVar, composerStartRestartGroup, (i15 & 112) | 520 | (i16 & 7168) | (57344 & i16) | (458752 & i16) | (3670016 & i16) | (29360128 & i16) | (234881024 & i16) | (i16 & 1879048192), ((i15 >> 27) & 14) | ((i12 << 3) & 112), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$2(obj, str, modifier2, defaultTransform, lVar3, center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, qVar, i14, i12, i13));
    }

    /* JADX INFO: renamed from: SubcomposeAsyncImage-ylYTKUw, reason: not valid java name */
    public static final void m6058SubcomposeAsyncImageylYTKUw(Object obj, String str, Modifier modifier, r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar, r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar2, r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Composer composer, int i11, int i12, int i13) {
        int i14;
        int iM3947getDefaultFilterQualityfv9h1I;
        int i15;
        Composer composerStartRestartGroup = composer.startRestartGroup(1047090393);
        Modifier modifier2 = (i13 & 4) != 0 ? Modifier.INSTANCE : modifier;
        r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, t0> rVar4 = (i13 & 8) != 0 ? null : rVar;
        r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, t0> rVar5 = (i13 & 16) != 0 ? null : rVar2;
        r<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, t0> rVar6 = (i13 & 32) != 0 ? null : rVar3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i13 & 64) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i13 & 128) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i13 & 256) != 0 ? null : lVar3;
        Alignment center = (i13 & 512) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i13 & 1024) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f11 = (i13 & 2048) != 0 ? 1.0f : f10;
        ColorFilter colorFilter2 = (i13 & 4096) != 0 ? null : colorFilter;
        if ((i13 & 8192) != 0) {
            i14 = i12;
            i15 = i14 & (-7169);
            iM3947getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        } else {
            i14 = i12;
            iM3947getDefaultFilterQualityfv9h1I = i10;
            i15 = i14;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1047090393, i11, i15, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:58)");
        }
        int i16 = i11 << 3;
        int i17 = (i11 & 112) | 520 | (i16 & 7168) | (i16 & 57344) | (458752 & i16) | (3670016 & i16) | (29360128 & i16) | (234881024 & i16) | (i16 & 1879048192);
        int i18 = i15 << 3;
        SubcomposeAsyncImageKt.m6059SubcomposeAsyncImageQ4Kwu38(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composerStartRestartGroup, 6), modifier2, rVar4, rVar5, rVar6, lVar4, lVar5, lVar6, center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, composerStartRestartGroup, i17, ((i11 >> 27) & 14) | (i18 & 112) | (i18 & 896) | (i18 & 7168) | (i18 & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$1(obj, str, modifier2, rVar4, rVar5, rVar6, lVar4, lVar5, lVar6, center, fit, f11, colorFilter2, iM3947getDefaultFilterQualityfv9h1I, i11, i14, i13));
    }
}
