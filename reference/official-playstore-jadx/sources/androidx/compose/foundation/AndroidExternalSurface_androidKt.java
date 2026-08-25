package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import com.arflix.tv.data.repository.g;
import d7.k;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u0001\u0010\u0002\u001a]\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u000f\u0010\u0015\u001a\u00020\u0014H\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001aU\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/AndroidExternalSurfaceState;", "rememberAndroidExternalSurfaceState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidExternalSurfaceState;", "Landroidx/compose/ui/Modifier;", "modifier", "", "isOpaque", "Landroidx/compose/ui/unit/IntSize;", "surfaceSize", "Landroidx/compose/foundation/AndroidExternalSurfaceZOrder;", "zOrder", "isSecure", "Lkotlin/Function1;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Lx6/t0;", "Lx6/n;", "onInit", "AndroidExternalSurface-58FFMhA", "(Landroidx/compose/ui/Modifier;ZJIZLr7/l;Landroidx/compose/runtime/Composer;II)V", "AndroidExternalSurface", "Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "rememberAndroidEmbeddedExternalSurfaceState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "Landroidx/compose/ui/graphics/Matrix;", "transform", "AndroidEmbeddedExternalSurface-sv6N_fY", "(Landroidx/compose/ui/Modifier;ZJ[FLr7/l;Landroidx/compose/runtime/Composer;II)V", "AndroidEmbeddedExternalSurface", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: AndroidEmbeddedExternalSurface-sv6N_fY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m174AndroidEmbeddedExternalSurfacesv6N_fY(androidx.compose.ui.Modifier r22, boolean r23, long r24, float[] r26, r7.l<? super androidx.compose.foundation.AndroidExternalSurfaceScope, x6.t0> r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidExternalSurface_androidKt.m174AndroidEmbeddedExternalSurfacesv6N_fY(androidx.compose.ui.Modifier, boolean, long, float[], r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011d  */
    /* JADX INFO: renamed from: AndroidExternalSurface-58FFMhA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m175AndroidExternalSurface58FFMhA(androidx.compose.ui.Modifier r20, boolean r21, long r22, int r24, boolean r25, r7.l<? super androidx.compose.foundation.AndroidExternalSurfaceScope, x6.t0> r26, androidx.compose.runtime.Composer r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidExternalSurface_androidKt.m175AndroidExternalSurface58FFMhA(androidx.compose.ui.Modifier, boolean, long, int, boolean, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final AndroidEmbeddedExternalSurfaceState rememberAndroidEmbeddedExternalSurfaceState(Composer composer, int i10) {
        composer.startReplaceableGroup(-1057437053);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1057437053, i10, -1, "androidx.compose.foundation.rememberAndroidEmbeddedExternalSurfaceState (AndroidExternalSurface.android.kt:383)");
        }
        Object objG = a0.c.g(773894976, composer, -492369756);
        Composer.Companion companion = Composer.INSTANCE;
        if (objG == companion.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new AndroidEmbeddedExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = (AndroidEmbeddedExternalSurfaceState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return androidEmbeddedExternalSurfaceState;
    }

    private static final AndroidExternalSurfaceState rememberAndroidExternalSurfaceState(Composer composer, int i10) {
        composer.startReplaceableGroup(-873615933);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-873615933, i10, -1, "androidx.compose.foundation.rememberAndroidExternalSurfaceState (AndroidExternalSurface.android.kt:189)");
        }
        Object objG = a0.c.g(773894976, composer, -492369756);
        Composer.Companion companion = Composer.INSTANCE;
        if (objG == companion.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new AndroidExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        AndroidExternalSurfaceState androidExternalSurfaceState = (AndroidExternalSurfaceState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return androidExternalSurfaceState;
    }
}
