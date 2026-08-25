package androidx.compose.material.icons.outlined;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_personAddAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PersonAddAlt", "Landroidx/compose/material/icons/Icons$Outlined;", "getPersonAddAlt", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersonAddAltKt {
    private static ImageVector _personAddAlt;

    public static final ImageVector getPersonAddAlt(Icons.Outlined outlined) {
        ImageVector imageVector = _personAddAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PersonAddAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(20.0f, 9.0f, 6.0f, -2.0f, 3.0f);
        v.a.o(pathBuilderA, -3.0f, 2.0f, 3.0f, 3.0f);
        a.j(pathBuilderA, 2.0f, -3.0f, 3.0f, 9.0f);
        v.a.x(pathBuilderA, 20.0f, 9.0f, 12.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveTo(5.0f, 5.79f, 5.0f, 8.0f);
        pathBuilderA.curveTo(5.0f, 10.21f, 6.79f, 12.0f, 9.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 6.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 9.1f, 7.0f, 8.0f);
        pathBuilderA.curveTo(7.0f, 6.9f, 7.9f, 6.0f, 9.0f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.39f, 14.56f);
        pathBuilderA.curveTo(13.71f, 13.7f, 11.53f, 13.0f, 9.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA.curveTo(1.61f, 15.07f, 1.0f, 16.1f, 1.0f, 17.22f);
        w.a.j(pathBuilderA, 20.0f, 16.0f, -2.78f);
        pathBuilderA.curveTo(17.0f, 16.1f, 16.39f, 15.07f, 15.39f, 14.56f);
        c.C(pathBuilderA, 15.0f, 18.0f, 3.0f, -0.78f);
        pathBuilderA.curveToRelative(0.0f, -0.38f, 0.2f, -0.72f, 0.52f, -0.88f);
        pathBuilderA.curveTo(4.71f, 15.73f, 6.63f, 15.0f, 9.0f, 15.0f);
        pathBuilderA.curveToRelative(2.37f, 0.0f, 4.29f, 0.73f, 5.48f, 1.34f);
        pathBuilderA.curveTo(14.8f, 16.5f, 15.0f, 16.84f, 15.0f, 17.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _personAddAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
