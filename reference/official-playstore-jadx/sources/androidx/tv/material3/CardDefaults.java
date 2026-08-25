package androidx.tv.material3;

import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001JN\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJN\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000bJ-\u0010\u0010\u001a\u00020\u00132\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"Landroidx/tv/material3/CardDefaults;", "", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "focusedContainerColor", "focusedContentColor", "pressedContainerColor", "pressedContentColor", "Landroidx/tv/material3/n;", "colors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/tv/material3/n;", "colors", "compactCardColors-5tl4gsc", "compactCardColors", "Landroidx/tv/material3/d;", "border", "focusedBorder", "pressedBorder", "Landroidx/tv/material3/m;", "(Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/tv/material3/d;Landroidx/compose/runtime/Composer;II)Landroidx/tv/material3/m;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CardDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CardDefaults f5542a = new CardDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Alignment f5543b = Alignment.INSTANCE.getCenter();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final RoundedCornerShape f5544c = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(8));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Brush f5545d = Brush.Companion.m3435verticalGradient8A3gB4$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(ColorKt.Color(28, 27, 31, 0)), Color.m3462boximpl(ColorKt.Color(28, 27, 31, ComposerKt.providerMapsKey))), 0.0f, 0.0f, 0, 14, (Object) null);

    public static r a() {
        s1 s1Var = s1.f6376c;
        return new r(s1Var, s1Var, s1Var);
    }

    public static w b() {
        RoundedCornerShape roundedCornerShape = f5544c;
        return new w(roundedCornerShape, roundedCornerShape, roundedCornerShape);
    }

    public final m border(d dVar, d dVar2, d dVar3, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            dVar = d.f5889d;
        }
        if ((i11 & 2) != 0) {
            dVar2 = new d(BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(3), MaterialTheme.f5670a.getColorScheme(composer, 6).b()), f5544c, 2);
        }
        if ((i11 & 4) != 0) {
            dVar3 = dVar2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(946758005, i10, -1, "androidx.tv.material3.CardDefaults.border (Card.kt:492)");
        }
        m mVar = new m(dVar, dVar2, dVar3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mVar;
    }

    /* JADX INFO: renamed from: colors-5tl4gsc, reason: not valid java name */
    public final n m5979colors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        long jY = (i11 & 1) != 0 ? MaterialTheme.f5670a.getColorScheme(composer, 6).y() : j10;
        long jM5987contentColorForek8zF_U = (i11 & 2) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(jY, composer, i10 & 14) : j11;
        long j16 = (i11 & 4) != 0 ? jY : j12;
        long jM5987contentColorForek8zF_U2 = (i11 & 8) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(j16, composer, (i10 >> 6) & 14) : j13;
        long j17 = (i11 & 16) != 0 ? j16 : j14;
        long jM5987contentColorForek8zF_U3 = (i11 & 32) != 0 ? ColorSchemeKt.m5987contentColorForek8zF_U(j17, composer, (i10 >> 12) & 14) : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-527018073, i10, -1, "androidx.tv.material3.CardDefaults.colors (Card.kt:421)");
        }
        n nVar = new n(jY, jM5987contentColorForek8zF_U, j16, jM5987contentColorForek8zF_U2, j17, jM5987contentColorForek8zF_U3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return nVar;
    }

    /* JADX INFO: renamed from: compactCardColors-5tl4gsc, reason: not valid java name */
    public final n m5980compactCardColors5tl4gsc(long j10, long j11, long j12, long j13, long j14, long j15, Composer composer, int i10, int i11) {
        long jY = (i11 & 1) != 0 ? MaterialTheme.f5670a.getColorScheme(composer, 6).y() : j10;
        long jM3509getWhite0d7_KjU = (i11 & 2) != 0 ? Color.INSTANCE.m3509getWhite0d7_KjU() : j11;
        long j16 = (i11 & 4) != 0 ? jY : j12;
        long j17 = (i11 & 8) != 0 ? jM3509getWhite0d7_KjU : j13;
        long j18 = (i11 & 16) != 0 ? j16 : j14;
        long j19 = (i11 & 32) != 0 ? j17 : j15;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1369890896, i10, -1, "androidx.tv.material3.CardDefaults.compactCardColors (Card.kt:450)");
        }
        n nVar = new n(jY, jM3509getWhite0d7_KjU, j16, j17, j18, j19);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return nVar;
    }
}
