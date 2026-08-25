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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsScore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsScore", "Landroidx/compose/material/icons/Icons$Sharp;", "getSportsScore", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsScoreKt {
    private static ImageVector _sportsScore;

    public static final ImageVector getSportsScore(Icons.Sharp sharp) {
        ImageVector imageVector = _sportsScore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SportsScore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(11.0f, 6.0f, 9.0f, 4.0f, 2.0f);
        a.D(pathBuilderC, 6.0f, 15.0f, 4.0f, -2.0f);
        a.t(pathBuilderC, 2.0f, 2.0f, 4.0f);
        a.C(pathBuilderC, 9.0f, 14.0f, 2.0f, -2.0f);
        c.l(pathBuilderC, 9.0f, 14.0f, 19.0f, 10.0f);
        f.z(pathBuilderC, 8.0f, -2.0f, 2.0f, 19.0f);
        c.m(pathBuilderC, 19.0f, 14.0f, -2.0f, -2.0f);
        e.y(pathBuilderC, 2.0f, 19.0f, 13.0f, 14.0f);
        f.w(pathBuilderC, 2.0f, -2.0f, -2.0f, 14.0f);
        a.C(pathBuilderC, 19.0f, 4.0f, -2.0f, 2.0f);
        c.D(pathBuilderC, 2.0f, 4.0f, 13.0f, 8.0f);
        f.z(pathBuilderC, 6.0f, -2.0f, 2.0f, 13.0f);
        e.o(pathBuilderC, 7.0f, 10.0f, 8.0f, 2.0f);
        pathBuilderC.verticalLineTo(6.0f);
        pathBuilderC.horizontalLineTo(7.0f);
        pathBuilderC.verticalLineTo(4.0f);
        pathBuilderC.horizontalLineTo(5.0f);
        b.z(pathBuilderC, 16.0f, 2.0f, -8.0f, 2.0f);
        e.y(pathBuilderC, -2.0f, 7.0f, 15.0f, 12.0f);
        f.w(pathBuilderC, 2.0f, -2.0f, -2.0f, 12.0f);
        c.m(pathBuilderC, 11.0f, 10.0f, 2.0f, 2.0f);
        e.y(pathBuilderC, -2.0f, 11.0f, 9.0f, 8.0f);
        a0.b.A(pathBuilderC, 2.0f, 2.0f, 8.0f, 9.0f);
        e.m(pathBuilderC, 13.0f, 10.0f, 2.0f, 8.0f);
        c.D(pathBuilderC, -2.0f, 10.0f, 15.0f, 6.0f);
        pathBuilderC.verticalLineToRelative(2.0f);
        pathBuilderC.horizontalLineToRelative(2.0f);
        pathBuilderC.verticalLineTo(6.0f);
        pathBuilderC.horizontalLineTo(15.0f);
        pathBuilderC.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderC.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsScore = imageVectorBuild;
        return imageVectorBuild;
    }
}
