package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_scoreboard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Scoreboard", "Landroidx/compose/material/icons/Icons$TwoTone;", "getScoreboard", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScoreboardKt {
    private static ImageVector _scoreboard;

    public static final ImageVector getScoreboard(Icons.TwoTone twoTone) {
        ImageVector imageVector = _scoreboard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Scoreboard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(17.5f, 13.5f, 16.0f, -3.0f, 1.5f);
        a.k(pathBuilderH, 13.5f, 12.75f, 6.0f, 1.5f);
        a.B(pathBuilderH, -1.5f, 6.0f, 4.0f, 12.0f);
        a0.a.j(pathBuilderH, 7.25f, -1.5f, 1.5f, 18.0f);
        f.f(pathBuilderH, 20.0f, 6.0f, 12.75f);
        pathBuilderH.moveTo(9.5f, 11.5f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a0.a.j(pathBuilderH, -2.0f, 1.0f, 3.0f, 15.0f);
        pathBuilderH.horizontalLineTo(5.0f);
        pathBuilderH.verticalLineToRelative(-2.5f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        d.y(pathBuilderH, 2.0f, -1.0f, 5.0f, 9.0f);
        pathBuilderH.horizontalLineToRelative(3.5f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        v.a.D(pathBuilderH, 11.5f, 12.75f, 14.5f, -1.5f);
        f.k(pathBuilderH, 13.0f, 1.5f, 14.5f);
        e.m(pathBuilderH, 12.75f, 11.0f, -1.5f, 9.5f);
        c.D(pathBuilderH, 1.5f, 11.0f, 19.0f, 14.0f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderH.horizontalLineToRelative(-2.5f);
        pathBuilderH.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderH.verticalLineToRelative(-4.0f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.horizontalLineTo(18.0f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderH, 14.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = v.a.a(18.0f, 9.0f, -2.5f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveTo(19.0f, 9.45f, 18.55f, 9.0f, 18.0f, 9.0f);
        c.C(pathBuilderA, 17.5f, 13.5f, 16.0f, -3.0f);
        c.D(pathBuilderA, 1.5f, 13.5f, 9.5f, 15.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.verticalLineToRelative(-2.5f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        d.y(pathBuilderA, 2.0f, -1.0f, 5.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(3.5f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.5f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        f.w(pathBuilderA, -2.0f, 1.0f, 3.0f, 15.0f);
        e.m(pathBuilderA, 12.75f, 11.0f, -1.5f, 9.5f);
        c.D(pathBuilderA, 1.5f, 11.0f, 12.75f, 14.5f);
        f.A(pathBuilderA, -1.5f, 13.0f, 1.5f, 14.5f);
        pathBuilderA.moveTo(22.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        a.C(pathBuilderA, 3.0f, 2.0f, 2.0f, 2.0f);
        a.C(pathBuilderA, 6.0f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveTo(21.1f, 4.0f, 22.0f, 4.9f, 22.0f, 6.0f);
        a0.b.t(pathBuilderA, 20.0f, 18.0f, 6.0f, -7.25f);
        c.p(pathBuilderA, 1.5f, -1.5f, 6.0f, 4.0f);
        b.z(pathBuilderA, 12.0f, 7.25f, -1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.c(pathBuilderA, 18.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scoreboard = imageVectorBuild;
        return imageVectorBuild;
    }
}
