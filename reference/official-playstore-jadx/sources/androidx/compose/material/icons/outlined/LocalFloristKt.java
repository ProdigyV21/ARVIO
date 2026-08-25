package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFlorist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFlorist", "Landroidx/compose/material/icons/Icons$Outlined;", "getLocalFlorist", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFloristKt {
    private static ImageVector _localFlorist;

    public static final ImageVector getLocalFlorist(Icons.Outlined outlined) {
        ImageVector imageVector = _localFlorist;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.LocalFlorist", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.66f, 13.07f);
        pathBuilderA.curveToRelative(0.15f, 0.0f, 0.29f, -0.01f, 0.43f, -0.03f);
        pathBuilderA.curveTo(9.56f, 14.19f, 10.69f, 15.0f, 12.0f, 15.0f);
        pathBuilderA.reflectiveCurveToRelative(2.44f, -0.81f, 2.91f, -1.96f);
        pathBuilderA.curveToRelative(0.14f, 0.02f, 0.29f, 0.03f, 0.43f, 0.03f);
        pathBuilderA.curveToRelative(1.73f, 0.0f, 3.14f, -1.41f, 3.14f, -3.14f);
        pathBuilderA.curveToRelative(0.0f, -0.71f, -0.25f, -1.39f, -0.67f, -1.93f);
        pathBuilderA.curveToRelative(0.43f, -0.54f, 0.67f, -1.22f, 0.67f, -1.93f);
        pathBuilderA.curveToRelative(0.0f, -1.73f, -1.41f, -3.14f, -3.14f, -3.14f);
        pathBuilderA.curveToRelative(-0.15f, 0.0f, -0.29f, 0.01f, -0.43f, 0.03f);
        pathBuilderA.curveTo(14.44f, 1.81f, 13.31f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.44f, 0.81f, -2.91f, 1.96f);
        pathBuilderA.curveToRelative(-0.14f, -0.02f, -0.29f, -0.03f, -0.43f, -0.03f);
        pathBuilderA.curveToRelative(-1.73f, 0.0f, -3.14f, 1.41f, -3.14f, 3.14f);
        pathBuilderA.curveToRelative(0.0f, 0.71f, 0.25f, 1.39f, 0.67f, 1.93f);
        pathBuilderA.curveToRelative(-0.43f, 0.54f, -0.68f, 1.22f, -0.68f, 1.93f);
        pathBuilderA.curveToRelative(0.0f, 1.73f, 1.41f, 3.14f, 3.15f, 3.14f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 13.0f);
        pathBuilderA.curveToRelative(-0.62f, 0.0f, -1.12f, -0.49f, -1.14f, -1.1f);
        pathBuilderA.lineToRelative(0.12f, -1.09f);
        pathBuilderA.curveToRelative(0.32f, 0.12f, 0.66f, 0.19f, 1.02f, 0.19f);
        pathBuilderA.reflectiveCurveToRelative(0.71f, -0.07f, 1.03f, -0.19f);
        pathBuilderA.lineToRelative(0.11f, 1.09f);
        pathBuilderA.curveToRelative(-0.02f, 0.61f, -0.52f, 1.1f, -1.14f, 1.1f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.34f, 11.07f);
        pathBuilderA.curveToRelative(-0.24f, 0.0f, -0.46f, -0.07f, -0.64f, -0.2f);
        pathBuilderA.lineToRelative(-0.81f, -0.57f);
        pathBuilderA.curveToRelative(0.55f, -0.45f, 0.94f, -1.09f, 1.06f, -1.83f);
        pathBuilderA.lineToRelative(0.88f, 0.42f);
        pathBuilderA.curveToRelative(0.4f, 0.19f, 0.66f, 0.59f, 0.66f, 1.03f);
        pathBuilderA.curveToRelative(0.0f, 0.64f, -0.52f, 1.15f, -1.15f, 1.15f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.69f, 5.13f);
        pathBuilderA.curveToRelative(0.2f, -0.13f, 0.42f, -0.2f, 0.65f, -0.2f);
        pathBuilderA.curveToRelative(0.63f, 0.0f, 1.14f, 0.51f, 1.14f, 1.14f);
        pathBuilderA.curveToRelative(0.0f, 0.44f, -0.25f, 0.83f, -0.66f, 1.03f);
        pathBuilderA.lineToRelative(-0.88f, 0.42f);
        pathBuilderA.curveToRelative(-0.12f, -0.74f, -0.51f, -1.38f, -1.07f, -1.83f);
        b.C(pathBuilderA, 0.82f, -0.56f, 12.0f, 3.0f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.12f, 0.49f, 1.14f, 1.1f);
        pathBuilderA.lineToRelative(-0.11f, 1.09f);
        pathBuilderA.curveTo(12.71f, 5.07f, 12.36f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.7f, 0.07f, -1.02f, 0.19f);
        pathBuilderA.lineToRelative(-0.12f, -1.09f);
        pathBuilderA.curveToRelative(0.02f, -0.61f, 0.52f, -1.1f, 1.14f, -1.1f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.66f, 4.93f);
        pathBuilderA.curveToRelative(0.24f, 0.0f, 0.46f, 0.07f, 0.64f, 0.2f);
        pathBuilderA.lineToRelative(0.81f, 0.56f);
        pathBuilderA.curveToRelative(-0.55f, 0.45f, -0.94f, 1.09f, -1.06f, 1.83f);
        pathBuilderA.lineToRelative(-0.88f, -0.42f);
        pathBuilderA.curveToRelative(-0.4f, -0.2f, -0.66f, -0.59f, -0.66f, -1.03f);
        pathBuilderA.curveToRelative(0.0f, -0.63f, 0.52f, -1.14f, 1.15f, -1.14f);
        a.z(pathBuilderA, 8.17f, 8.9f, 0.88f, -0.42f);
        pathBuilderA.curveToRelative(0.12f, 0.74f, 0.51f, 1.38f, 1.07f, 1.83f);
        pathBuilderA.lineToRelative(-0.81f, 0.55f);
        pathBuilderA.curveToRelative(-0.2f, 0.13f, -0.42f, 0.2f, -0.65f, 0.2f);
        pathBuilderA.curveToRelative(-0.63f, 0.0f, -1.14f, -0.51f, -1.14f, -1.14f);
        pathBuilderA.curveToRelative(-0.01f, -0.43f, 0.25f, -0.82f, 0.65f, -1.02f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 22.0f);
        pathBuilderA.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderA.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.44f, 19.56f);
        pathBuilderA.curveToRelative(0.71f, -1.9f, 2.22f, -3.42f, 4.12f, -4.12f);
        pathBuilderA.curveToRelative(-0.71f, 1.9f, -2.22f, 3.41f, -4.12f, 4.12f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.44f, 15.44f);
        pathBuilderA.curveToRelative(1.9f, 0.71f, 3.42f, 2.22f, 4.12f, 4.12f);
        pathBuilderA.curveToRelative(-1.9f, -0.71f, -3.41f, -2.22f, -4.12f, -4.12f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localFlorist = imageVectorBuild;
        return imageVectorBuild;
    }
}
