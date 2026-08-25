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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_repartition", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Repartition", "Landroidx/compose/material/icons/Icons$Rounded;", "getRepartition", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RepartitionKt {
    private static ImageVector _repartition;

    public static final ImageVector getRepartition(Icons.Rounded rounded) {
        ImageVector imageVector = _repartition;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Repartition", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(4.5f, 21.0f, 15.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(-15.0f);
        pathBuilderA.curveTo(3.67f, 15.0f, 3.0f, 15.67f, 3.0f, 16.5f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveTo(3.0f, 20.33f, 3.67f, 21.0f, 4.5f, 21.0f);
        c.y(pathBuilderA, 10.33f, 19.0f, -2.0f, 3.33f);
        e.y(pathBuilderA, 2.0f, 10.33f, 19.0f, 19.0f);
        d.z(pathBuilderA, -3.33f, -2.0f, 19.0f, 19.0f);
        a.C(pathBuilderA, 5.0f, 17.0f, 3.33f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderA, 5.0f, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.71f, 9.29f, 6.71f, 9.29f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.42f);
        pathBuilderJ.lineTo(5.83f, 7.0f);
        pathBuilderJ.lineToRelative(11.06f, 0.0f);
        pathBuilderJ.curveToRelative(1.0f, 0.0f, 1.92f, 0.68f, 2.08f, 1.66f);
        pathBuilderJ.curveTo(19.18f, 9.91f, 18.21f, 11.0f, 17.0f, 11.0f);
        pathBuilderJ.horizontalLineTo(4.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.lineToRelative(12.82f, 0.0f);
        pathBuilderJ.curveToRelative(2.09f, 0.0f, 3.96f, -1.52f, 4.16f, -3.6f);
        pathBuilderJ.curveTo(21.21f, 7.02f, 19.34f, 5.0f, 17.0f, 5.0f);
        pathBuilderJ.horizontalLineTo(5.83f);
        pathBuilderJ.lineToRelative(0.88f, -0.88f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.42f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineTo(2.71f, 5.29f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(2.59f, 2.59f);
        pathBuilderJ.curveTo(5.68f, 9.68f, 6.32f, 9.68f, 6.71f, 9.29f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _repartition = imageVectorBuild;
        return imageVectorBuild;
    }
}
