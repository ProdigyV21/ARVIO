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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_leakRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LeakRemove", "Landroidx/compose/material/icons/Icons$Rounded;", "getLeakRemove", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LeakRemoveKt {
    private static ImageVector _leakRemove;

    public static final ImageVector getLeakRemove(Icons.Rounded rounded) {
        ImageVector imageVector = _leakRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LeakRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.12f, 12.04f);
        pathBuilderA.curveToRelative(0.5f, -0.05f, 0.88f, -0.48f, 0.88f, -0.99f);
        pathBuilderA.curveToRelative(0.0f, -0.59f, -0.51f, -1.06f, -1.1f, -1.0f);
        pathBuilderA.curveToRelative(-1.5f, 0.15f, -2.9f, 0.61f, -4.16f, 1.3f);
        pathBuilderA.lineToRelative(1.48f, 1.48f);
        pathBuilderA.curveToRelative(0.9f, -0.41f, 1.87f, -0.69f, 2.9f, -0.79f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.0f, 15.09f);
        pathBuilderA.curveToRelative(0.0f, -0.61f, -0.54f, -1.09f, -1.14f, -1.0f);
        pathBuilderA.curveToRelative(-0.38f, 0.06f, -0.75f, 0.16f, -1.11f, 0.28f);
        pathBuilderA.lineToRelative(1.62f, 1.62f);
        pathBuilderA.curveToRelative(0.37f, -0.15f, 0.63f, -0.49f, 0.63f, -0.9f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.97f, 4.14f);
        pathBuilderA.curveToRelative(0.06f, -0.59f, -0.4f, -1.11f, -1.0f, -1.11f);
        pathBuilderA.curveToRelative(-0.5f, 0.0f, -0.94f, 0.37f, -0.99f, 0.87f);
        pathBuilderA.curveToRelative(-0.1f, 1.03f, -0.38f, 2.01f, -0.79f, 2.91f);
        pathBuilderA.lineToRelative(1.48f, 1.48f);
        pathBuilderA.curveToRelative(0.69f, -1.26f, 1.15f, -2.66f, 1.3f, -4.15f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.93f, 4.16f);
        pathBuilderA.curveToRelative(0.1f, -0.6f, -0.39f, -1.14f, -1.0f, -1.14f);
        pathBuilderA.curveToRelative(-0.41f, 0.0f, -0.75f, 0.26f, -0.9f, 0.62f);
        pathBuilderA.lineToRelative(1.62f, 1.62f);
        pathBuilderA.curveToRelative(0.13f, -0.35f, 0.22f, -0.72f, 0.28f, -1.1f);
        a.p(pathBuilderA, 20.44f, 18.88f, 5.12f, 3.56f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(2.15f, 2.15f);
        pathBuilderA.curveToRelative(-0.59f, 0.41f, -1.26f, 0.7f, -1.99f, 0.82f);
        pathBuilderA.curveToRelative(-0.48f, 0.1f, -0.84f, 0.5f, -0.84f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.61f, 0.54f, 1.09f, 1.14f, 1.0f);
        pathBuilderA.curveToRelative(1.17f, -0.19f, 2.23f, -0.68f, 3.13f, -1.37f);
        pathBuilderA.lineTo(8.73f, 10.0f);
        pathBuilderA.curveToRelative(-1.34f, 1.1f, -3.0f, 1.82f, -4.81f, 1.99f);
        pathBuilderA.curveToRelative(-0.5f, 0.05f, -0.88f, 0.48f, -0.88f, 0.99f);
        pathBuilderA.curveToRelative(0.0f, 0.59f, 0.51f, 1.06f, 1.1f, 1.0f);
        pathBuilderA.curveToRelative(2.28f, -0.23f, 4.36f, -1.15f, 6.01f, -2.56f);
        pathBuilderA.lineToRelative(2.48f, 2.48f);
        pathBuilderA.curveToRelative(-1.4f, 1.65f, -2.33f, 3.72f, -2.56f, 6.0f);
        pathBuilderA.curveToRelative(-0.06f, 0.59f, 0.4f, 1.11f, 1.0f, 1.11f);
        pathBuilderA.curveToRelative(0.5f, 0.0f, 0.94f, -0.37f, 0.99f, -0.87f);
        pathBuilderA.curveToRelative(0.18f, -1.82f, 0.9f, -3.48f, 1.99f, -4.82f);
        pathBuilderA.lineToRelative(1.43f, 1.43f);
        pathBuilderA.curveToRelative(-0.69f, 0.9f, -1.18f, 1.96f, -1.37f, 3.13f);
        pathBuilderA.curveToRelative(-0.1f, 0.6f, 0.39f, 1.14f, 1.0f, 1.14f);
        pathBuilderA.curveToRelative(0.49f, 0.0f, 0.9f, -0.36f, 0.98f, -0.85f);
        pathBuilderA.curveToRelative(0.12f, -0.73f, 0.42f, -1.4f, 0.82f, -1.99f);
        pathBuilderA.lineToRelative(2.13f, 2.13f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.curveToRelative(0.38f, -0.41f, 0.38f, -1.04f, -0.01f, -1.43f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _leakRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
