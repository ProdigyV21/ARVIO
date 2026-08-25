package androidx.compose.material.icons.rounded;

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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_findInPage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FindInPage", "Landroidx/compose/material/icons/Icons$Rounded;", "getFindInPage", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FindInPageKt {
    private static ImageVector _findInPage;

    public static final ImageVector getFindInPage(Icons.Rounded rounded) {
        ImageVector imageVector = _findInPage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FindInPage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(20.0f, 19.59f, 8.83f);
        pathBuilderA.curveToRelative(0.0f, -0.53f, -0.21f, -1.04f, -0.59f, -1.41f);
        pathBuilderA.lineToRelative(-4.83f, -4.83f);
        pathBuilderA.curveToRelative(-0.37f, -0.38f, -0.88f, -0.59f, -1.41f, -0.59f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderA.lineTo(4.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 1.99f, 2.0f);
        pathBuilderA.horizontalLineTo(18.0f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.85f, -0.15f, 1.19f, -0.4f);
        pathBuilderA.lineToRelative(-4.43f, -4.43f);
        pathBuilderA.curveToRelative(-0.86f, 0.56f, -1.89f, 0.88f, -3.0f, 0.82f);
        pathBuilderA.curveToRelative(-2.37f, -0.11f, -4.4f, -1.96f, -4.72f, -4.31f);
        pathBuilderA.curveToRelative(-0.44f, -3.35f, 2.45f, -6.18f, 5.83f, -5.61f);
        pathBuilderA.curveToRelative(1.95f, 0.33f, 3.57f, 1.85f, 4.0f, 3.78f);
        pathBuilderA.curveToRelative(0.33f, 1.46f, 0.01f, 2.82f, -0.7f, 3.9f);
        c.z(pathBuilderA, 20.0f, 19.59f, 9.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -3.0f, 1.34f, -3.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _findInPage = imageVectorBuild;
        return imageVectorBuild;
    }
}
