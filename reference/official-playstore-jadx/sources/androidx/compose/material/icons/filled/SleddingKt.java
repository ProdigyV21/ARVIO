package androidx.compose.material.icons.filled;

import a0.b;
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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sledding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sledding", "Landroidx/compose/material/icons/Icons$Filled;", "getSledding", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SleddingKt {
    private static ImageVector _sledding;

    public static final ImageVector getSledding(Icons.Filled filled) {
        ImageVector imageVector = _sledding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Sledding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.0f, 4.5f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 3.4f, 14.0f, 4.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.8f, 20.24f);
        pathBuilderA.curveToRelative(-0.68f, 2.1f, -2.94f, 3.25f, -5.04f, 2.57f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.lineTo(1.0f, 17.36f);
        pathBuilderA.lineToRelative(0.46f, -1.43f);
        pathBuilderA.lineToRelative(3.93f, 1.28f);
        pathBuilderA.lineToRelative(0.46f, -1.43f);
        pathBuilderA.lineTo(1.93f, 14.5f);
        pathBuilderA.lineToRelative(0.46f, -1.43f);
        pathBuilderA.lineTo(4.0f, 13.6f);
        pathBuilderA.verticalLineTo(9.5f);
        pathBuilderA.lineToRelative(5.47f, -2.35f);
        pathBuilderA.curveToRelative(0.39f, -0.17f, 0.84f, -0.21f, 1.28f, -0.07f);
        pathBuilderA.curveToRelative(0.95f, 0.31f, 1.46f, 1.32f, 1.16f, 2.27f);
        pathBuilderA.lineToRelative(-1.05f, 3.24f);
        pathBuilderA.lineTo(13.0f, 12.25f);
        pathBuilderA.curveToRelative(0.89f, -0.15f, 1.76f, 0.32f, 2.14f, 1.14f);
        pathBuilderA.lineToRelative(2.08f, 4.51f);
        pathBuilderA.lineToRelative(1.93f, 0.63f);
        pathBuilderA.lineToRelative(-0.46f, 1.43f);
        pathBuilderA.lineToRelative(-3.32f, -1.08f);
        pathBuilderA.lineTo(14.9f, 20.3f);
        pathBuilderA.lineToRelative(3.32f, 1.08f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(1.31f, 0.43f, 2.72f, -0.29f, 3.15f, -1.61f);
        pathBuilderA.curveToRelative(0.43f, -1.31f, -0.29f, -2.72f, -1.61f, -3.15f);
        pathBuilderA.lineToRelative(0.46f, -1.43f);
        pathBuilderA.curveTo(22.33f, 15.88f, 23.49f, 18.14f, 22.8f, 20.24f);
        a.z(pathBuilderA, 6.0f, 14.25f, 1.01f, 0.33f);
        pathBuilderA.curveToRelative(-0.22f, -0.42f, -0.28f, -0.92f, -0.12f, -1.4f);
        pathBuilderA.lineTo(7.92f, 10.0f);
        b.s(pathBuilderA, 6.0f, 10.82f, 14.25f);
        pathBuilderA.moveTo(13.94f, 18.41f);
        pathBuilderA.lineToRelative(-6.66f, -2.16f);
        pathBuilderA.lineToRelative(-0.46f, 1.43f);
        d.C(pathBuilderA, 6.66f, 2.16f, 13.94f, 18.41f);
        pathBuilderA.moveTo(14.63f, 17.05f);
        pathBuilderA.lineToRelative(-1.18f, -2.56f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderA, -3.97f, 0.89f, 14.63f, 17.05f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sledding = imageVectorBuild;
        return imageVectorBuild;
    }
}
