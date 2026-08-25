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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_groupOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GroupOff", "Landroidx/compose/material/icons/Icons$Filled;", "getGroupOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GroupOffKt {
    private static ImageVector _groupOff;

    public static final ImageVector getGroupOff(Icons.Filled filled) {
        ImageVector imageVector = _groupOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.GroupOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.42f, -0.5f, -2.73f, -1.33f, -3.76f);
        pathBuilderA.curveTo(14.09f, 4.1f, 14.53f, 4.0f, 15.0f, 4.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderA.curveToRelative(-0.06f, 0.0f, -0.12f, 0.0f, -0.18f, 0.0f);
        pathBuilderA.lineToRelative(-0.77f, -0.77f);
        pathBuilderA.curveTo(14.65f, 10.29f, 15.0f, 9.18f, 15.0f, 8.0f);
        c.C(pathBuilderA, 22.83f, 20.0f, 23.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -2.18f, -3.58f, -3.47f, -6.34f, -3.87f);
        pathBuilderA.curveToRelative(1.1f, 0.75f, 1.95f, 1.71f, 2.23f, 2.94f);
        c.z(pathBuilderA, 22.83f, 20.0f, 7.24f, 4.41f);
        pathBuilderA.curveTo(7.77f, 4.15f, 8.37f, 4.0f, 9.0f, 4.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.63f, -0.15f, 1.23f, -0.41f, 1.76f);
        c.z(pathBuilderA, 7.24f, 4.41f, 9.17f, 12.0f);
        pathBuilderA.curveTo(9.11f, 12.0f, 9.06f, 12.0f, 9.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.06f, 0.0f, -0.11f, 0.0f, -0.17f);
        pathBuilderA.lineTo(0.69f, 3.51f);
        pathBuilderA.lineTo(2.1f, 2.1f);
        pathBuilderA.lineToRelative(19.8f, 19.8f);
        pathBuilderA.lineToRelative(-1.41f, 1.41f);
        c.f(pathBuilderA, 17.0f, 19.83f, 20.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -2.66f, 5.33f, -4.0f, 8.0f, -4.0f);
        pathBuilderA.curveToRelative(0.37f, 0.0f, 0.8f, 0.03f, 1.25f, 0.08f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 9.17f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _groupOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
