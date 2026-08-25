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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_people", "Landroidx/compose/ui/graphics/vector/ImageVector;", "People", "Landroidx/compose/material/icons/Icons$Outlined;", "getPeople", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PeopleKt {
    private static ImageVector _people;

    public static final ImageVector getPeople(Icons.Outlined outlined) {
        ImageVector imageVector = _people;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.People", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 13.75f);
        pathBuilderA.curveToRelative(-2.34f, 0.0f, -7.0f, 1.17f, -7.0f, 3.5f);
        b.A(pathBuilderA, 2.0f, 19.0f, 14.0f, -1.75f);
        pathBuilderA.curveToRelative(0.0f, -2.33f, -4.66f, -3.5f, -7.0f, -3.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.34f, 17.0f);
        pathBuilderA.curveToRelative(0.84f, -0.58f, 2.87f, -1.25f, 4.66f, -1.25f);
        pathBuilderA.reflectiveCurveToRelative(3.82f, 0.67f, 4.66f, 1.25f);
        c.z(pathBuilderA, 4.34f, 17.0f, 9.0f, 12.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(10.93f, 5.0f, 9.0f, 5.0f);
        pathBuilderA.reflectiveCurveTo(5.5f, 6.57f, 5.5f, 8.5f);
        pathBuilderA.reflectiveCurveTo(7.07f, 12.0f, 9.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 7.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveTo(9.83f, 10.0f, 9.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(8.17f, 7.0f, 9.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.04f, 13.81f);
        pathBuilderA.curveToRelative(1.16f, 0.84f, 1.96f, 1.96f, 1.96f, 3.44f);
        b.A(pathBuilderA, 18.0f, 19.0f, 4.0f, -1.75f);
        pathBuilderA.curveToRelative(0.0f, -2.02f, -3.5f, -3.17f, -5.96f, -3.44f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 12.0f);
        pathBuilderA.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(16.93f, 5.0f, 15.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.54f, 0.0f, -1.04f, 0.13f, -1.5f, 0.35f);
        pathBuilderA.curveToRelative(0.63f, 0.89f, 1.0f, 1.98f, 1.0f, 3.15f);
        pathBuilderA.reflectiveCurveToRelative(-0.37f, 2.26f, -1.0f, 3.15f);
        pathBuilderA.curveToRelative(0.46f, 0.22f, 0.96f, 0.35f, 1.5f, 0.35f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _people = imageVectorBuild;
        return imageVectorBuild;
    }
}
