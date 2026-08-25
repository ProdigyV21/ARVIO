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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localSee", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalSee", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalSee", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalSeeKt {
    private static ImageVector _localSee;

    public static final ImageVector getLocalSee(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localSee;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalSee", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -4.05f, -0.59f, -0.65f);
        pathBuilderG.lineTo(14.12f, 4.0f);
        pathBuilderG.lineTo(9.88f, 4.0f);
        pathBuilderG.lineTo(8.65f, 5.35f);
        pathBuilderG.lineToRelative(-0.6f, 0.65f);
        a.h(pathBuilderG, 4.0f, 6.0f, 12.0f, 16.0f);
        c.z(pathBuilderG, 20.0f, 6.0f, 12.0f, 17.0f);
        pathBuilderG.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderG.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderG.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderG, -2.24f, 5.0f, -5.0f, 5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(4.0f, 20.0f, 16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineTo(22.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-3.17f);
        pathBuilderA.lineTo(15.0f, 2.0f);
        pathBuilderA.lineTo(9.0f, 2.0f);
        pathBuilderA.lineTo(7.17f, 4.0f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a.g(pathBuilderA, 4.0f, 6.0f, 4.05f);
        pathBuilderA.lineToRelative(0.59f, -0.65f);
        pathBuilderA.lineTo(9.88f, 4.0f);
        pathBuilderA.horizontalLineToRelative(4.24f);
        pathBuilderA.lineToRelative(1.24f, 1.35f);
        pathBuilderA.lineToRelative(0.59f, 0.65f);
        pathBuilderA.lineTo(20.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        b.y(pathBuilderA, 4.0f, 18.0f, 4.0f, 6.0f);
        pathBuilderA.moveTo(12.0f, 7.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 15.2f);
        pathBuilderA.curveToRelative(-1.77f, 0.0f, -3.2f, -1.43f, -3.2f, -3.2f);
        pathBuilderA.curveToRelative(0.0f, -1.77f, 1.43f, -3.2f, 3.2f, -3.2f);
        pathBuilderA.reflectiveCurveToRelative(3.2f, 1.43f, 3.2f, 3.2f);
        pathBuilderA.curveToRelative(0.0f, 1.77f, -1.43f, 3.2f, -3.2f, 3.2f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localSee = imageVectorBuild;
        return imageVectorBuild;
    }
}
