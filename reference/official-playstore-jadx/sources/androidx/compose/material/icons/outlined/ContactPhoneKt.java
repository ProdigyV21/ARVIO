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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactPhone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactPhone", "Landroidx/compose/material/icons/Icons$Outlined;", "getContactPhone", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactPhoneKt {
    private static ImageVector _contactPhone;

    public static final ImageVector getContactPhone(Icons.Outlined outlined) {
        ImageVector imageVector = _contactPhone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ContactPhone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 3.0f, 2.0f, 3.0f);
        pathBuilderJ.curveTo(0.9f, 3.0f, 0.0f, 3.9f, 0.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(20.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        pathBuilderJ.lineTo(24.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 22.0f, 19.0f, 2.0f, 19.0f);
        b.g(pathBuilderJ, 2.0f, 5.0f, 20.0f, 14.0f);
        pathBuilderJ.moveTo(19.01f, 17.99f);
        pathBuilderJ.lineTo(21.0f, 16.0f);
        pathBuilderJ.lineToRelative(-1.51f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(-1.64f);
        pathBuilderJ.curveToRelative(-0.22f, -0.63f, -0.35f, -1.3f, -0.35f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.13f, -1.37f, 0.35f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(1.64f);
        pathBuilderJ.lineTo(21.0f, 8.0f);
        pathBuilderJ.lineToRelative(-1.99f, -1.99f);
        pathBuilderJ.curveToRelative(-1.31f, 0.98f, -2.28f, 2.37f, -2.73f, 3.99f);
        pathBuilderJ.curveToRelative(-0.18f, 0.64f, -0.28f, 1.31f, -0.28f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.1f, 1.36f, 0.28f, 2.0f);
        pathBuilderJ.curveToRelative(0.45f, 1.61f, 1.42f, 3.01f, 2.73f, 3.99f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.0f, 12.0f);
        pathBuilderJ.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.0f, 8.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(15.0f, 16.59f);
        pathBuilderJ.curveToRelative(0.0f, -2.5f, -3.97f, -3.58f, -6.0f, -3.58f);
        pathBuilderJ.reflectiveCurveToRelative(-6.0f, 1.08f, -6.0f, 3.58f);
        b.g(pathBuilderJ, 3.0f, 18.0f, 12.0f, -1.41f);
        pathBuilderJ.moveTo(5.48f, 16.0f);
        pathBuilderJ.curveToRelative(0.74f, -0.5f, 2.22f, -1.0f, 3.52f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.77f, 0.49f, 3.52f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderJ, 5.48f, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactPhone = imageVectorBuild;
        return imageVectorBuild;
    }
}
