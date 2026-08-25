package androidx.compose.material.icons.filled;

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
import v.a;
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_scoreboard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Scoreboard", "Landroidx/compose/material/icons/Icons$Filled;", "getScoreboard", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScoreboardKt {
    private static ImageVector _scoreboard;

    public static final ImageVector getScoreboard(Icons.Filled filled) {
        ImageVector imageVector = _scoreboard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Scoreboard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(17.5f, 13.5f, 16.0f, -3.0f, 1.5f);
        a.D(pathBuilderH, 13.5f, 20.0f, 4.0f, -3.0f);
        e.x(pathBuilderH, 2.0f, -2.0f, 2.0f, 9.0f);
        pathBuilderH.verticalLineTo(2.0f);
        pathBuilderH.horizontalLineTo(7.0f);
        pathBuilderH.verticalLineToRelative(2.0f);
        pathBuilderH.horizontalLineTo(4.0f);
        pathBuilderH.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderH.verticalLineToRelative(12.0f);
        pathBuilderH.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderH.horizontalLineToRelative(16.0f);
        pathBuilderH.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderH.verticalLineTo(6.0f);
        pathBuilderH.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        pathBuilderH.close();
        pathBuilderH.moveTo(9.5f, 11.5f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a0.a.j(pathBuilderH, -2.0f, 1.0f, 3.0f, 15.0f);
        pathBuilderH.horizontalLineTo(5.0f);
        pathBuilderH.verticalLineToRelative(-2.5f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        d.y(pathBuilderH, 2.0f, -1.0f, 5.0f, 9.0f);
        pathBuilderH.horizontalLineToRelative(3.5f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        a.D(pathBuilderH, 11.5f, 12.75f, 18.0f, -1.5f);
        a.t(pathBuilderH, -1.5f, 1.5f, 18.0f);
        e.m(pathBuilderH, 12.75f, 14.5f, -1.5f, 13.0f);
        c.D(pathBuilderH, 1.5f, 14.5f, 12.75f, 11.0f);
        f.A(pathBuilderH, -1.5f, 9.5f, 1.5f, 11.0f);
        e.m(pathBuilderH, 12.75f, 7.5f, -1.5f, 6.0f);
        c.D(pathBuilderH, 1.5f, 7.5f, 19.0f, 14.0f);
        pathBuilderH.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderH.horizontalLineToRelative(-2.5f);
        pathBuilderH.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderH.verticalLineToRelative(-4.0f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.horizontalLineTo(18.0f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderH, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scoreboard = imageVectorBuild;
        return imageVectorBuild;
    }
}
