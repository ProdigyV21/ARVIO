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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_peopleOutline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PeopleOutline", "Landroidx/compose/material/icons/Icons$Filled;", "getPeopleOutline", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PeopleOutlineKt {
    private static ImageVector _peopleOutline;

    public static final ImageVector getPeopleOutline(Icons.Filled filled) {
        ImageVector imageVector = _peopleOutline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PeopleOutline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 13.0f);
        pathBuilderA.curveToRelative(-1.2f, 0.0f, -3.07f, 0.34f, -4.5f, 1.0f);
        pathBuilderA.curveToRelative(-1.43f, -0.67f, -3.3f, -1.0f, -4.5f, -1.0f);
        pathBuilderA.curveTo(5.33f, 13.0f, 1.0f, 14.08f, 1.0f, 16.25f);
        b.A(pathBuilderA, 1.0f, 19.0f, 22.0f, -2.75f);
        pathBuilderA.curveToRelative(0.0f, -2.17f, -4.33f, -3.25f, -6.5f, -3.25f);
        b.w(pathBuilderA, 12.5f, 17.5f, -10.0f, -1.25f);
        pathBuilderA.curveToRelative(0.0f, -0.54f, 2.56f, -1.75f, 5.0f, -1.75f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, 1.21f, 5.0f, 1.75f);
        b.f(pathBuilderA, 1.25f, 21.5f, 17.5f);
        pathBuilderA.lineTo(14.0f, 17.5f);
        pathBuilderA.verticalLineToRelative(-1.25f);
        pathBuilderA.curveToRelative(0.0f, -0.46f, -0.2f, -0.86f, -0.52f, -1.22f);
        pathBuilderA.curveToRelative(0.88f, -0.3f, 1.96f, -0.53f, 3.02f, -0.53f);
        pathBuilderA.curveToRelative(2.44f, 0.0f, 5.0f, 1.21f, 5.0f, 1.75f);
        b.f(pathBuilderA, 1.25f, 7.5f, 12.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(9.43f, 5.0f, 7.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(4.0f, 6.57f, 4.0f, 8.5f);
        pathBuilderA.reflectiveCurveTo(5.57f, 12.0f, 7.5f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.5f, 6.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 12.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(18.43f, 5.0f, 16.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(13.0f, 6.57f, 13.0f, 8.5f);
        pathBuilderA.reflectiveCurveToRelative(1.57f, 3.5f, 3.5f, 3.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 6.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, 0.9f, -2.0f, 2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _peopleOutline = imageVectorBuild;
        return imageVectorBuild;
    }
}
