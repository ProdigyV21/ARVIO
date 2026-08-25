package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ballot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Ballot", "Landroidx/compose/material/icons/Icons$Filled;", "getBallot", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BallotKt {
    private static ImageVector _ballot;

    public static final ImageVector getBallot(Icons.Filled filled) {
        ImageVector imageVector = _ballot;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Ballot", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int iM3733getEvenOddRgk1Os = PathFillType.INSTANCE.m3733getEvenOddRgk1Os();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(13.0f, 9.5f, 5.0f, -2.0f, -5.0f);
        a.D(pathBuilderK, 9.5f, 13.0f, 16.5f, 5.0f);
        a.t(pathBuilderK, -2.0f, -5.0f, 16.5f);
        pathBuilderK.moveTo(19.0f, 21.0f);
        pathBuilderK.horizontalLineTo(5.0f);
        pathBuilderK.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderK.verticalLineTo(5.0f);
        pathBuilderK.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderK.horizontalLineToRelative(14.0f);
        pathBuilderK.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderK.verticalLineToRelative(14.0f);
        pathBuilderK.curveTo(21.0f, 20.1f, 20.1f, 21.0f, 19.0f, 21.0f);
        a.u(pathBuilderK, 6.0f, 11.0f, 5.0f, 6.0f);
        c.l(pathBuilderK, 6.0f, 11.0f, 7.0f, 7.0f);
        d.z(pathBuilderK, 3.0f, 3.0f, 7.0f, 7.0f);
        a.C(pathBuilderK, 6.0f, 18.0f, 5.0f, -5.0f);
        c.l(pathBuilderK, 6.0f, 18.0f, 7.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderK, 3.0f, 3.0f, 7.0f, 14.0f), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ballot = imageVectorBuild;
        return imageVectorBuild;
    }
}
