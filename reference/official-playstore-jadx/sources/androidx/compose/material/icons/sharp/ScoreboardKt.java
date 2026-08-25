package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_scoreboard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Scoreboard", "Landroidx/compose/material/icons/Icons$Sharp;", "getScoreboard", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScoreboardKt {
    private static ImageVector _scoreboard;

    public static final ImageVector getScoreboard(Icons.Sharp sharp) {
        ImageVector imageVector = _scoreboard;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Scoreboard", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(17.5f, 13.5f, 16.0f, -3.0f, 1.5f);
        a.D(pathBuilderH, 13.5f, 22.0f, 4.0f, -5.0f);
        e.x(pathBuilderH, 2.0f, -2.0f, 2.0f, 9.0f);
        pathBuilderH.verticalLineTo(2.0f);
        pathBuilderH.horizontalLineTo(7.0f);
        pathBuilderH.verticalLineToRelative(2.0f);
        pathBuilderH.horizontalLineTo(2.0f);
        a.t(pathBuilderH, 16.0f, 20.0f, 4.0f);
        a.C(pathBuilderH, 9.5f, 12.5f, -3.0f, 1.0f);
        w.a.B(pathBuilderH, 3.0f, 15.0f, 5.0f, -3.5f);
        d.y(pathBuilderH, 3.0f, -1.0f, 5.0f, 9.0f);
        c.D(pathBuilderH, 4.5f, 12.5f, 12.75f, 18.0f);
        f.w(pathBuilderH, -1.5f, -1.5f, 1.5f, 18.0f);
        e.m(pathBuilderH, 12.75f, 14.5f, -1.5f, 13.0f);
        c.D(pathBuilderH, 1.5f, 14.5f, 12.75f, 11.0f);
        f.A(pathBuilderH, -1.5f, 9.5f, 1.5f, 11.0f);
        e.m(pathBuilderH, 12.75f, 7.5f, -1.5f, 6.0f);
        c.D(pathBuilderH, 1.5f, 7.5f, 19.0f, 9.0f);
        pathBuilderH.verticalLineToRelative(6.0f);
        pathBuilderH.horizontalLineToRelative(-4.5f);
        pathBuilderH.verticalLineTo(9.0f);
        pathBuilderH.horizontalLineTo(19.0f);
        pathBuilderH.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderH.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scoreboard = imageVectorBuild;
        return imageVectorBuild;
    }
}
