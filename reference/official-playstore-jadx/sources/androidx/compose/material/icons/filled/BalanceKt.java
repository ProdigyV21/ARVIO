package androidx.compose.material.icons.filled;

import a0.a;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_balance", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Balance", "Landroidx/compose/material/icons/Icons$Filled;", "getBalance", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BalanceKt {
    private static ImageVector _balance;

    public static final ImageVector getBalance(Icons.Filled filled) {
        ImageVector imageVector = _balance;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Balance", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.0f, 7.83f);
        pathBuilderA.curveToRelative(0.85f, -0.3f, 1.53f, -0.98f, 1.83f, -1.83f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.lineToRelative(-3.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.57f, 3.0f, 3.5f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.5f, -1.34f, 3.5f, -3.0f);
        a.p(pathBuilderA, -3.0f, -7.0f, 2.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-6.17f);
        pathBuilderA.curveTo(14.42f, 2.83f, 13.31f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(9.58f, 2.83f, 9.17f, 4.0f);
        v.a.h(pathBuilderA, 3.0f, 4.0f, 2.0f, 2.0f);
        pathBuilderA.lineToRelative(-3.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.57f, 3.0f, 3.5f, 3.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 14.66f, 9.0f, 13.0f);
        pathBuilderA.lineTo(6.0f, 6.0f);
        pathBuilderA.horizontalLineToRelative(3.17f);
        pathBuilderA.curveToRelative(0.3f, 0.85f, 0.98f, 1.53f, 1.83f, 1.83f);
        c.o(pathBuilderA, 19.0f, 2.0f, 2.0f, 20.0f);
        v.a.t(pathBuilderA, -2.0f, -9.0f, 7.83f);
        pathBuilderA.moveTo(20.37f, 13.0f);
        pathBuilderA.horizontalLineToRelative(-3.74f);
        d.C(pathBuilderA, 1.87f, -4.36f, 20.37f, 13.0f);
        pathBuilderA.moveTo(7.37f, 13.0f);
        pathBuilderA.horizontalLineTo(3.63f);
        b.y(pathBuilderA, 5.5f, 8.64f, 7.37f, 13.0f);
        pathBuilderA.moveTo(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveTo(13.0f, 5.55f, 12.55f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _balance = imageVectorBuild;
        return imageVectorBuild;
    }
}
