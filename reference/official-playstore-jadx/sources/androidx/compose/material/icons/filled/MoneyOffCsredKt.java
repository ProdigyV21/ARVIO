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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_moneyOffCsred", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MoneyOffCsred", "Landroidx/compose/material/icons/Icons$Filled;", "getMoneyOffCsred", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MoneyOffCsredKt {
    private static ImageVector _moneyOffCsred;

    public static final ImageVector getMoneyOffCsred(Icons.Filled filled) {
        ImageVector imageVector = _moneyOffCsred;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MoneyOffCsred", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.53f, 7.43f);
        pathBuilderA.curveToRelative(0.42f, -0.31f, 0.93f, -0.47f, 1.54f, -0.47f);
        pathBuilderA.reflectiveCurveToRelative(1.11f, 0.16f, 1.5f, 0.49f);
        pathBuilderA.curveToRelative(0.39f, 0.32f, 0.65f, 0.7f, 0.79f, 1.12f);
        pathBuilderA.lineToRelative(1.89f, -0.8f);
        pathBuilderA.curveToRelative(-0.24f, -0.71f, -0.71f, -1.35f, -1.4f, -1.92f);
        pathBuilderA.curveToRelative(-0.5f, -0.4f, -1.12f, -0.65f, -1.85f, -0.77f);
        a.j(pathBuilderA, 3.0f, -2.0f, 2.11f);
        pathBuilderA.curveToRelative(-0.41f, 0.08f, -0.79f, 0.21f, -1.14f, 0.39f);
        pathBuilderA.curveToRelative(-0.35f, 0.18f, -0.64f, 0.39f, -0.9f, 0.63f);
        pathBuilderA.lineToRelative(1.43f, 1.43f);
        pathBuilderA.curveTo(10.43f, 7.52f, 10.48f, 7.47f, 10.53f, 7.43f);
        v.a.p(pathBuilderA, 2.81f, 2.81f, 1.39f, 4.22f);
        pathBuilderA.lineToRelative(12.35f, 12.35f);
        pathBuilderA.curveTo(13.31f, 16.85f, 12.79f, 17.0f, 12.19f, 17.0f);
        pathBuilderA.curveToRelative(-0.71f, 0.0f, -1.32f, -0.23f, -1.83f, -0.7f);
        pathBuilderA.curveToRelative(-0.5f, -0.47f, -0.86f, -1.07f, -1.06f, -1.81f);
        pathBuilderA.lineToRelative(-1.98f, 0.8f);
        pathBuilderA.curveToRelative(0.34f, 1.17f, 0.95f, 2.08f, 1.83f, 2.73f);
        pathBuilderA.curveToRelative(0.57f, 0.42f, 1.19f, 0.68f, 1.85f, 0.83f);
        a.j(pathBuilderA, 21.0f, 2.0f, -2.08f);
        pathBuilderA.curveToRelative(0.44f, -0.07f, 0.87f, -0.17f, 1.29f, -0.35f);
        pathBuilderA.curveToRelative(0.34f, -0.14f, 0.64f, -0.32f, 0.92f, -0.53f);
        pathBuilderA.lineToRelative(4.57f, 4.57f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.41f, -1.41f, 2.81f, 2.81f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _moneyOffCsred = imageVectorBuild;
        return imageVectorBuild;
    }
}
