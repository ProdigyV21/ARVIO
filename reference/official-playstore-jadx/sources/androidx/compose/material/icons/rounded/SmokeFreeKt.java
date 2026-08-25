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
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_smokeFree", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SmokeFree", "Landroidx/compose/material/icons/Icons$Rounded;", "getSmokeFree", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SmokeFreeKt {
    private static ImageVector _smokeFree;

    public static final ImageVector getSmokeFree(Icons.Rounded rounded) {
        ImageVector imageVector = _smokeFree;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SmokeFree", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(20.5f, 13.0f, 22.0f, 13.0f, 3.0f);
        e.r(pathBuilderS, -1.5f, 18.0f, 13.0f, 1.5f);
        a.n(pathBuilderS, 3.0f, 18.0f, 16.0f);
        pathBuilderS.moveTo(17.0f, 14.5f);
        pathBuilderS.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderS.horizontalLineToRelative(-0.84f);
        pathBuilderS.lineToRelative(2.18f, 2.18f);
        pathBuilderS.curveToRelative(0.1f, -0.21f, 0.16f, -0.44f, 0.16f, -0.68f);
        v.a.p(pathBuilderS, 18.96f, 2.35f, 19.0f, 2.35f);
        f.x(pathBuilderS, -0.04f, 18.85f, 4.73f);
        pathBuilderS.curveToRelative(0.38f, -0.38f, 0.67f, -0.84f, 0.84f, -1.35f);
        pathBuilderS.curveToRelative(0.16f, -0.5f, -0.19f, -1.01f, -0.71f, -1.02f);
        pathBuilderS.curveToRelative(-0.34f, 0.01f, -0.61f, 0.25f, -0.72f, 0.58f);
        pathBuilderS.curveToRelative(-0.18f, 0.55f, -0.62f, 0.99f, -1.17f, 1.17f);
        pathBuilderS.curveToRelative(-0.34f, 0.11f, -0.59f, 0.39f, -0.59f, 0.74f);
        pathBuilderS.lineTo(16.5f, 5.0f);
        pathBuilderS.curveToRelative(0.0f, 0.37f, 0.27f, 0.69f, 0.64f, 0.75f);
        pathBuilderS.curveToRelative(1.93f, 0.31f, 3.36f, 2.0f, 3.36f, 4.02f);
        pathBuilderS.verticalLineToRelative(1.48f);
        pathBuilderS.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderS.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderS.lineTo(22.0f, 9.76f);
        pathBuilderS.curveToRelative(0.0f, -2.22f, -1.28f, -4.14f, -3.15f, -5.03f);
        v.a.g(pathBuilderS, 14.61f, 8.65f, 1.42f);
        pathBuilderS.curveToRelative(1.05f, 0.0f, 1.97f, 0.74f, 1.97f, 2.05f);
        pathBuilderS.verticalLineToRelative(0.55f);
        pathBuilderS.curveToRelative(0.0f, 0.41f, 0.33f, 0.75f, 0.75f, 0.75f);
        pathBuilderS.horizontalLineToRelative(0.01f);
        pathBuilderS.curveToRelative(0.41f, 0.0f, 0.75f, -0.33f, 0.75f, -0.75f);
        pathBuilderS.verticalLineToRelative(-0.89f);
        pathBuilderS.curveToRelative(0.0f, -1.81f, -1.6f, -3.16f, -3.47f, -3.16f);
        pathBuilderS.horizontalLineToRelative(-1.3f);
        pathBuilderS.curveToRelative(-1.02f, 0.0f, -1.94f, -0.73f, -2.07f, -1.75f);
        pathBuilderS.curveToRelative(-0.12f, -0.95f, 0.46f, -1.7f, 1.3f, -1.93f);
        pathBuilderS.curveToRelative(0.32f, -0.09f, 0.54f, -0.38f, 0.54f, -0.72f);
        pathBuilderS.curveToRelative(0.0f, -0.49f, -0.46f, -0.86f, -0.93f, -0.72f);
        pathBuilderS.curveToRelative(-1.42f, 0.41f, -2.45f, 1.73f, -2.42f, 3.28f);
        pathBuilderS.curveToRelative(0.02f, 1.85f, 1.61f, 3.29f, 3.45f, 3.29f);
        pathBuilderS.close();
        pathBuilderS.moveTo(4.12f, 5.29f);
        pathBuilderS.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderS.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderS.lineTo(9.0f, 13.0f);
        pathBuilderS.lineTo(3.5f, 13.0f);
        pathBuilderS.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderS.reflectiveCurveTo(2.67f, 16.0f, 3.5f, 16.0f);
        pathBuilderS.lineTo(12.0f, 16.0f);
        pathBuilderS.lineToRelative(6.29f, 6.29f);
        pathBuilderS.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderS.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 4.12f, 5.29f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _smokeFree = imageVectorBuild;
        return imageVectorBuild;
    }
}
