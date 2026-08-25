package androidx.compose.material.icons.twotone;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_leakRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LeakRemove", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLeakRemove", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LeakRemoveKt {
    private static ImageVector _leakRemove;

    public static final ImageVector getLeakRemove(Icons.TwoTone twoTone) {
        ImageVector imageVector = _leakRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LeakRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(14.0f, 3.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.35f, -0.31f, 2.63f, -0.84f, 3.77f);
        pathBuilderA.lineToRelative(1.49f, 1.49f);
        pathBuilderA.curveTo(13.51f, 6.7f, 14.0f, 4.91f, 14.0f, 3.0f);
        d.k(pathBuilderA, 21.0f, 12.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.91f, 0.0f, -3.7f, 0.49f, -5.27f, 1.35f);
        pathBuilderA.lineToRelative(1.49f, 1.49f);
        pathBuilderA.curveToRelative(1.15f, -0.53f, 2.43f, -0.84f, 3.78f, -0.84f);
        d.k(pathBuilderA, 21.0f, 16.0f, -2.0f);
        pathBuilderA.curveToRelative(-0.79f, 0.0f, -1.54f, 0.13f, -2.24f, 0.37f);
        pathBuilderA.lineToRelative(1.68f, 1.68f);
        pathBuilderA.curveToRelative(0.19f, -0.01f, 0.37f, -0.05f, 0.56f, -0.05f);
        a.p(pathBuilderA, 10.0f, 3.0f, 8.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.19f, -0.04f, 0.37f, -0.06f, 0.56f);
        pathBuilderA.lineToRelative(1.68f, 1.68f);
        pathBuilderA.curveToRelative(0.25f, -0.7f, 0.38f, -1.46f, 0.38f, -2.24f);
        a.p(pathBuilderA, 4.41f, 2.86f, 3.0f, 4.27f);
        pathBuilderA.lineToRelative(2.84f, 2.84f);
        pathBuilderA.curveTo(5.03f, 7.67f, 4.06f, 8.0f, 3.0f, 8.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(1.61f, 0.0f, 3.09f, -0.55f, 4.27f, -1.46f);
        pathBuilderA.lineTo(8.7f, 9.97f);
        pathBuilderA.curveTo(7.14f, 11.24f, 5.16f, 12.0f, 3.0f, 12.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(2.72f, 0.0f, 5.2f, -0.99f, 7.11f, -2.62f);
        pathBuilderA.lineToRelative(2.51f, 2.51f);
        pathBuilderA.curveTo(10.99f, 15.81f, 10.0f, 18.29f, 10.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.16f, 0.76f, -4.14f, 2.03f, -5.7f);
        pathBuilderA.lineToRelative(1.43f, 1.43f);
        pathBuilderA.curveTo(14.55f, 17.91f, 14.0f, 19.39f, 14.0f, 21.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.06f, 0.33f, -2.03f, 0.89f, -2.84f);
        pathBuilderA.lineTo(19.73f, 21.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.41f, -1.41f, 4.41f, 2.86f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _leakRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
