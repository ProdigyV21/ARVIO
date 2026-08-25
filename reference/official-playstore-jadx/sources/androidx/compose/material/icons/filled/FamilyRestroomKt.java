package androidx.compose.material.icons.filled;

import a0.a;
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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_familyRestroom", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FamilyRestroom", "Landroidx/compose/material/icons/Icons$Filled;", "getFamilyRestroom", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FamilyRestroomKt {
    private static ImageVector _familyRestroom;

    public static final ImageVector getFamilyRestroom(Icons.Filled filled) {
        ImageVector imageVector = _familyRestroom;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FamilyRestroom", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, 0.89f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(16.0f, 5.11f, 16.0f, 4.0f);
        c.y(pathBuilderA, 20.0f, 22.0f, -6.0f, 2.5f);
        pathBuilderA.lineToRelative(-2.54f, -7.63f);
        pathBuilderA.curveTo(19.68f, 7.55f, 18.92f, 7.0f, 18.06f, 7.0f);
        pathBuilderA.horizontalLineToRelative(-0.12f);
        pathBuilderA.curveToRelative(-0.86f, 0.0f, -1.63f, 0.55f, -1.9f, 1.37f);
        pathBuilderA.lineToRelative(-0.86f, 2.58f);
        pathBuilderA.curveTo(16.26f, 11.55f, 17.0f, 12.68f, 17.0f, 14.0f);
        e.y(pathBuilderA, 8.0f, 20.0f, 12.5f, 11.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(11.0f, 9.17f, 11.0f, 10.0f);
        pathBuilderA.reflectiveCurveTo(11.67f, 11.5f, 12.5f, 11.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.5f, 6.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(4.39f, 6.0f, 5.5f, 6.0f);
        a.z(pathBuilderA, 7.5f, 22.0f, -7.0f, 9.0f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(2.9f, 7.0f, 2.0f, 7.9f, 2.0f, 9.0f);
        b.f(pathBuilderA, 6.0f, 1.5f, 7.0f, 7.5f);
        c.m(pathBuilderA, 14.0f, 22.0f, -4.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.82f, -0.68f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.82f, 0.0f, -1.5f, 0.68f, -1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.d(pathBuilderA, 4.0f, 1.0f, 4.0f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _familyRestroom = imageVectorBuild;
        return imageVectorBuild;
    }
}
