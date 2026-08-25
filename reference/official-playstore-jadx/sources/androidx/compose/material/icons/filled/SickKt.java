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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sick", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sick", "Landroidx/compose/material/icons/Icons$Filled;", "getSick", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SickKt {
    private static ImageVector _sick;

    public static final ImageVector getSick(Icons.Filled filled) {
        ImageVector imageVector = _sick;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Sick", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 9.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 2.0f, -4.0f, 2.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderA.curveTo(23.0f, 8.1f, 22.1f, 9.0f, 21.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.5f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, -0.73f, 0.41f, -1.71f, 0.92f, -2.66f);
        pathBuilderA.curveTo(16.68f, 2.88f, 14.44f, 2.0f, 11.99f, 2.0f);
        pathBuilderA.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.47f, 10.0f, 9.99f, 10.0f);
        pathBuilderA.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.06f, -1.09f, -0.14f, -1.62f);
        pathBuilderA.curveTo(21.58f, 10.45f, 21.3f, 10.5f, 21.0f, 10.5f);
        pathBuilderA.curveTo(19.07f, 10.5f, 17.5f, 8.93f, 17.5f, 7.0f);
        a.z(pathBuilderA, 15.62f, 7.38f, 1.06f, 1.06f);
        pathBuilderA.lineTo(15.62f, 9.5f);
        pathBuilderA.lineToRelative(1.06f, 1.06f);
        pathBuilderA.lineToRelative(-1.06f, 1.06f);
        b.y(pathBuilderA, 13.5f, 9.5f, 15.62f, 7.38f);
        pathBuilderA.moveTo(7.32f, 8.44f);
        pathBuilderA.lineToRelative(1.06f, -1.06f);
        pathBuilderA.lineTo(10.5f, 9.5f);
        pathBuilderA.lineToRelative(-2.12f, 2.12f);
        pathBuilderA.lineToRelative(-1.06f, -1.06f);
        b.y(pathBuilderA, 8.38f, 9.5f, 7.32f, 8.44f);
        pathBuilderA.moveTo(15.44f, 17.0f);
        pathBuilderA.curveToRelative(-0.69f, -1.19f, -1.97f, -2.0f, -3.44f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.75f, 0.81f, -3.44f, 2.0f);
        pathBuilderA.horizontalLineTo(6.88f);
        pathBuilderA.curveToRelative(0.3f, -0.76f, 0.76f, -1.43f, 1.34f, -1.99f);
        pathBuilderA.lineTo(5.24f, 13.3f);
        pathBuilderA.curveToRelative(-0.45f, 0.26f, -1.01f, 0.28f, -1.49f, 0.0f);
        pathBuilderA.curveToRelative(-0.72f, -0.41f, -0.96f, -1.33f, -0.55f, -2.05f);
        pathBuilderA.curveToRelative(0.41f, -0.72f, 1.33f, -0.96f, 2.05f, -0.55f);
        pathBuilderA.curveToRelative(0.48f, 0.28f, 0.74f, 0.78f, 0.74f, 1.29f);
        pathBuilderA.lineToRelative(3.58f, 2.07f);
        pathBuilderA.curveToRelative(0.73f, -0.36f, 1.55f, -0.56f, 2.43f, -0.56f);
        pathBuilderA.curveToRelative(2.33f, 0.0f, 4.32f, 1.45f, 5.12f, 3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 15.44f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sick = imageVectorBuild;
        return imageVectorBuild;
    }
}
