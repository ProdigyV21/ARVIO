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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactPhone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactPhone", "Landroidx/compose/material/icons/Icons$Rounded;", "getContactPhone", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactPhoneKt {
    private static ImageVector _contactPhone;

    public static final ImageVector getContactPhone(Icons.Rounded rounded) {
        ImageVector imageVector = _contactPhone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ContactPhone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        pathBuilderJ.close();
        pathBuilderJ.moveTo(8.0f, 6.0f);
        pathBuilderJ.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        a.p(pathBuilderJ, 14.0f, 18.0f, 2.0f, 18.0f);
        pathBuilderJ.verticalLineToRelative(-1.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.0f, 4.0f, -3.1f, 6.0f, -3.1f);
        pathBuilderJ.reflectiveCurveToRelative(6.0f, 1.1f, 6.0f, 3.1f);
        c.r(pathBuilderJ, 1.0f, 17.85f, 14.0f, 1.39f);
        pathBuilderJ.curveToRelative(0.16f, 0.0f, 0.3f, 0.07f, 0.4f, 0.2f);
        pathBuilderJ.lineToRelative(1.1f, 1.45f);
        pathBuilderJ.curveToRelative(0.15f, 0.2f, 0.13f, 0.48f, -0.05f, 0.65f);
        pathBuilderJ.lineToRelative(-1.36f, 1.36f);
        pathBuilderJ.curveToRelative(-0.18f, 0.18f, -0.48f, 0.2f, -0.67f, 0.04f);
        pathBuilderJ.curveToRelative(-1.13f, -0.96f, -1.97f, -2.25f, -2.38f, -3.71f);
        pathBuilderJ.curveToRelative(-0.18f, -0.63f, -0.28f, -1.3f, -0.28f, -1.99f);
        pathBuilderJ.reflectiveCurveToRelative(0.1f, -1.36f, 0.28f, -2.0f);
        pathBuilderJ.curveToRelative(0.41f, -1.47f, 1.25f, -2.75f, 2.38f, -3.71f);
        pathBuilderJ.curveToRelative(0.2f, -0.17f, 0.49f, -0.14f, 0.67f, 0.04f);
        pathBuilderJ.lineToRelative(1.36f, 1.36f);
        pathBuilderJ.curveToRelative(0.18f, 0.18f, 0.2f, 0.46f, 0.05f, 0.65f);
        pathBuilderJ.lineToRelative(-1.1f, 1.45f);
        pathBuilderJ.curveToRelative(-0.09f, 0.13f, -0.24f, 0.2f, -0.4f, 0.2f);
        pathBuilderJ.horizontalLineToRelative(-1.39f);
        pathBuilderJ.curveToRelative(-0.22f, 0.63f, -0.35f, 1.3f, -0.35f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, 0.13f, 1.38f, 0.35f, 2.01f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactPhone = imageVectorBuild;
        return imageVectorBuild;
    }
}
