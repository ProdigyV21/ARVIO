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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactEmergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactEmergency", "Landroidx/compose/material/icons/Icons$Rounded;", "getContactEmergency", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactEmergencyKt {
    private static ImageVector _contactEmergency;

    public static final ImageVector getContactEmergency(Icons.Rounded rounded) {
        ImageVector imageVector = _contactEmergency;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ContactEmergency", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(22.0f, 3.0f, 2.0f);
        pathBuilderQ.curveTo(0.9f, 3.0f, 0.0f, 3.9f, 0.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(20.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        pathBuilderQ.lineTo(24.0f, 5.0f);
        pathBuilderQ.curveTo(24.0f, 3.9f, 23.1f, 3.0f, 22.0f, 3.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(9.0f, 8.0f);
        pathBuilderQ.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderQ.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderQ.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderQ.reflectiveCurveTo(7.35f, 8.0f, 9.0f, 8.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(2.08f, 19.0f);
        pathBuilderQ.curveToRelative(1.38f, -2.39f, 3.96f, -4.0f, 6.92f, -4.0f);
        pathBuilderQ.reflectiveCurveToRelative(5.54f, 1.61f, 6.92f, 4.0f);
        a.x(pathBuilderQ, 2.08f, 20.6f, 10.5f);
        pathBuilderQ.lineTo(20.6f, 10.5f);
        pathBuilderQ.curveToRelative(-0.21f, 0.36f, -0.67f, 0.48f, -1.02f, 0.27f);
        pathBuilderQ.lineToRelative(-0.82f, -0.48f);
        pathBuilderQ.verticalLineToRelative(0.95f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderQ.verticalLineTo(10.3f);
        pathBuilderQ.lineToRelative(-0.82f, 0.48f);
        pathBuilderQ.curveToRelative(-0.36f, 0.21f, -0.82f, 0.08f, -1.02f, -0.27f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(-0.21f, -0.36f, -0.08f, -0.82f, 0.27f, -1.02f);
        pathBuilderQ.lineTo(16.5f, 9.0f);
        pathBuilderQ.lineToRelative(-0.82f, -0.48f);
        pathBuilderQ.curveToRelative(-0.36f, -0.21f, -0.48f, -0.67f, -0.27f, -1.02f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.21f, -0.36f, 0.67f, -0.48f, 1.02f, -0.27f);
        pathBuilderQ.lineToRelative(0.82f, 0.48f);
        pathBuilderQ.verticalLineTo(6.75f);
        pathBuilderQ.curveTo(17.25f, 6.34f, 17.59f, 6.0f, 18.0f, 6.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderQ.verticalLineTo(7.7f);
        pathBuilderQ.lineToRelative(0.82f, -0.48f);
        pathBuilderQ.curveToRelative(0.36f, -0.21f, 0.82f, -0.08f, 1.02f, 0.27f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.21f, 0.36f, 0.08f, 0.82f, -0.27f, 1.02f);
        pathBuilderQ.lineTo(19.5f, 9.0f);
        pathBuilderQ.lineToRelative(0.82f, 0.48f);
        pathBuilderQ.curveTo(20.68f, 9.68f, 20.81f, 10.14f, 20.6f, 10.5f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactEmergency = imageVectorBuild;
        return imageVectorBuild;
    }
}
