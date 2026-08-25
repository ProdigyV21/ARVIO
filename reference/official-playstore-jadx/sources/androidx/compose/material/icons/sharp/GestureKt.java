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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gesture", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Gesture", "Landroidx/compose/material/icons/Icons$Sharp;", "getGesture", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GestureKt {
    private static ImageVector _gesture;

    public static final ImageVector getGesture(Icons.Sharp sharp) {
        ImageVector imageVector = _gesture;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Gesture", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.59f, 6.89f);
        pathBuilderA.curveToRelative(0.7f, -0.71f, 1.4f, -1.35f, 1.71f, -1.22f);
        pathBuilderA.curveToRelative(0.5f, 0.2f, 0.0f, 1.03f, -0.3f, 1.52f);
        pathBuilderA.curveToRelative(-0.25f, 0.42f, -2.86f, 3.89f, -2.86f, 6.31f);
        pathBuilderA.curveToRelative(0.0f, 1.28f, 0.48f, 2.34f, 1.34f, 2.98f);
        pathBuilderA.curveToRelative(0.75f, 0.56f, 1.74f, 0.73f, 2.64f, 0.46f);
        pathBuilderA.curveToRelative(1.07f, -0.31f, 1.95f, -1.4f, 3.06f, -2.77f);
        pathBuilderA.curveToRelative(1.21f, -1.49f, 2.83f, -3.44f, 4.08f, -3.44f);
        pathBuilderA.curveToRelative(1.63f, 0.0f, 1.65f, 1.01f, 1.76f, 1.79f);
        pathBuilderA.curveToRelative(-3.78f, 0.64f, -5.38f, 3.67f, -5.38f, 5.37f);
        pathBuilderA.curveToRelative(0.0f, 1.7f, 1.44f, 3.09f, 3.21f, 3.09f);
        pathBuilderA.curveToRelative(1.63f, 0.0f, 4.29f, -1.33f, 4.69f, -6.1f);
        a.h(pathBuilderA, 21.0f, 14.88f, -2.5f, -2.47f);
        pathBuilderA.curveToRelative(-0.15f, -1.65f, -1.09f, -4.2f, -4.03f, -4.2f);
        pathBuilderA.curveToRelative(-2.25f, 0.0f, -4.18f, 1.91f, -4.94f, 2.84f);
        pathBuilderA.curveToRelative(-0.58f, 0.73f, -2.06f, 2.48f, -2.29f, 2.72f);
        pathBuilderA.curveToRelative(-0.25f, 0.3f, -0.68f, 0.84f, -1.11f, 0.84f);
        pathBuilderA.curveToRelative(-0.45f, 0.0f, -0.72f, -0.83f, -0.36f, -1.92f);
        pathBuilderA.curveToRelative(0.35f, -1.09f, 1.4f, -2.86f, 1.85f, -3.52f);
        pathBuilderA.curveToRelative(0.78f, -1.14f, 1.3f, -1.92f, 1.3f, -3.28f);
        pathBuilderA.curveTo(8.95f, 3.69f, 7.31f, 3.0f, 6.44f, 3.0f);
        pathBuilderA.curveTo(5.12f, 3.0f, 3.97f, 4.0f, 3.72f, 4.25f);
        pathBuilderA.curveToRelative(-0.36f, 0.36f, -0.66f, 0.66f, -0.88f, 0.93f);
        b.C(pathBuilderA, 1.75f, 1.71f, 13.88f, 18.55f);
        pathBuilderA.curveToRelative(-0.31f, 0.0f, -0.74f, -0.26f, -0.74f, -0.72f);
        pathBuilderA.curveToRelative(0.0f, -0.6f, 0.73f, -2.2f, 2.87f, -2.76f);
        pathBuilderA.curveToRelative(-0.3f, 2.69f, -1.43f, 3.48f, -2.13f, 3.48f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gesture = imageVectorBuild;
        return imageVectorBuild;
    }
}
