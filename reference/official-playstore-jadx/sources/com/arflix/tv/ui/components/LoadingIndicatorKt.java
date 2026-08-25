package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a7\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\b\u0010\t\u001a-\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u0012²\u0006\f\u0010\u000f\u001a\u00020\u000e8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0010\u001a\u00020\u000e8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0011\u001a\u00020\u000e8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/unit/Dp;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "strokeWidth", "Lx6/t0;", "LoadingIndicator-5_Ve2oQ", "(Landroidx/compose/ui/Modifier;FJFLandroidx/compose/runtime/Composer;II)V", "LoadingIndicator", "PulsingLoadingIndicator-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "PulsingLoadingIndicator", "", "rotation", "scale", "alpha", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LoadingIndicatorKt {
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: LoadingIndicator-5_Ve2oQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6162LoadingIndicator5_Ve2oQ(androidx.compose.ui.Modifier r25, float r26, long r27, float r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(androidx.compose.ui.Modifier, float, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float LoadingIndicator_5_Ve2oQ$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LoadingIndicator_5_Ve2oQ$lambda$1$0(float f10, long j10, State state, DrawScope drawScope) {
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(f10);
        long jSize = SizeKt.Size(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) - fMo285toPx0680j_4, Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) - fMo285toPx0680j_4);
        long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(j10, 0.2f, 0.0f, 0.0f, 0.0f, 14, null);
        float f11 = fMo285toPx0680j_4 / 2;
        long jOffset = OffsetKt.Offset(f11, f11);
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, jM3471copywmQWz5c$default, 0.0f, 360.0f, false, jOffset, jSize, 0.0f, new Stroke(fMo285toPx0680j_4, 0.0f, companion.m3804getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, j10, LoadingIndicator_5_Ve2oQ$lambda$0(state), 90.0f, false, OffsetKt.Offset(f11, f11), jSize, 0.0f, new Stroke(fMo285toPx0680j_4, 0.0f, companion.m3804getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LoadingIndicator_5_Ve2oQ$lambda$2(Modifier modifier, float f10, long j10, float f11, int i10, int i11, Composer composer, int i12) {
        m6162LoadingIndicator5_Ve2oQ(modifier, f10, j10, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: PulsingLoadingIndicator-9IZ8Weo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6163PulsingLoadingIndicator9IZ8Weo(androidx.compose.ui.Modifier r25, float r26, long r27, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.LoadingIndicatorKt.m6163PulsingLoadingIndicator9IZ8Weo(androidx.compose.ui.Modifier, float, long, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float PulsingLoadingIndicator_9IZ8Weo$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PulsingLoadingIndicator_9IZ8Weo$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLoadingIndicator_9IZ8Weo$lambda$2$0(long j10, State state, State state2, DrawScope drawScope) {
        float fM3304getMinDimensionimpl = (Size.m3304getMinDimensionimpl(drawScope.mo3916getSizeNHjbRc()) / 2) * PulsingLoadingIndicator_9IZ8Weo$lambda$0(state);
        androidx.compose.ui.graphics.drawscope.c.x(drawScope, Color.m3471copywmQWz5c$default(j10, PulsingLoadingIndicator_9IZ8Weo$lambda$1(state2) * 0.3f, 0.0f, 0.0f, 0.0f, 14, null), fM3304getMinDimensionimpl, 0L, 0.0f, null, null, 0, 124, null);
        androidx.compose.ui.graphics.drawscope.c.x(drawScope, Color.m3471copywmQWz5c$default(j10, PulsingLoadingIndicator_9IZ8Weo$lambda$1(state2), 0.0f, 0.0f, 0.0f, 14, null), fM3304getMinDimensionimpl * 0.6f, 0L, 0.0f, null, null, 0, 124, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLoadingIndicator_9IZ8Weo$lambda$3(Modifier modifier, float f10, long j10, int i10, int i11, Composer composer, int i12) {
        m6163PulsingLoadingIndicator9IZ8Weo(modifier, f10, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
