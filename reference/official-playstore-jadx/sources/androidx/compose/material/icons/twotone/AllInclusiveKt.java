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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_allInclusive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AllInclusive", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAllInclusive", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AllInclusiveKt {
    private static ImageVector _allInclusive;

    public static final ImageVector getAllInclusive(Icons.TwoTone twoTone) {
        ImageVector imageVector = _allInclusive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AllInclusive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.6f, 6.62f);
        pathBuilderA.curveToRelative(-1.44f, 0.0f, -2.8f, 0.56f, -3.77f, 1.53f);
        pathBuilderA.lineTo(7.8f, 14.39f);
        pathBuilderA.curveToRelative(-0.64f, 0.64f, -1.49f, 0.99f, -2.4f, 0.99f);
        pathBuilderA.curveToRelative(-1.87f, 0.0f, -3.39f, -1.51f, -3.39f, -3.38f);
        pathBuilderA.reflectiveCurveTo(3.53f, 8.62f, 5.4f, 8.62f);
        pathBuilderA.curveToRelative(0.91f, 0.0f, 1.76f, 0.35f, 2.44f, 1.03f);
        pathBuilderA.lineToRelative(1.13f, 1.0f);
        pathBuilderA.lineToRelative(1.51f, -1.34f);
        pathBuilderA.lineTo(9.22f, 8.2f);
        pathBuilderA.curveTo(8.2f, 7.18f, 6.84f, 6.62f, 5.4f, 6.62f);
        pathBuilderA.curveTo(2.42f, 6.62f, 0.0f, 9.04f, 0.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(2.42f, 5.38f, 5.4f, 5.38f);
        pathBuilderA.curveToRelative(1.44f, 0.0f, 2.8f, -0.56f, 3.77f, -1.53f);
        pathBuilderA.lineToRelative(7.03f, -6.24f);
        pathBuilderA.curveToRelative(0.64f, -0.64f, 1.49f, -0.99f, 2.4f, -0.99f);
        pathBuilderA.curveToRelative(1.87f, 0.0f, 3.39f, 1.51f, 3.39f, 3.38f);
        pathBuilderA.reflectiveCurveToRelative(-1.52f, 3.38f, -3.39f, 3.38f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.76f, -0.35f, -2.44f, -1.03f);
        pathBuilderA.lineToRelative(-1.14f, -1.01f);
        pathBuilderA.lineToRelative(-1.51f, 1.34f);
        pathBuilderA.lineToRelative(1.27f, 1.12f);
        pathBuilderA.curveToRelative(1.02f, 1.01f, 2.37f, 1.57f, 3.82f, 1.57f);
        pathBuilderA.curveToRelative(2.98f, 0.0f, 5.4f, -2.41f, 5.4f, -5.38f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.42f, -5.37f, -5.4f, -5.37f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _allInclusive = imageVectorBuild;
        return imageVectorBuild;
    }
}
