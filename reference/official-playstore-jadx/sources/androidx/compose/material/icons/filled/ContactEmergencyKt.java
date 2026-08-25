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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactEmergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactEmergency", "Landroidx/compose/material/icons/Icons$Filled;", "getContactEmergency", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactEmergencyKt {
    private static ImageVector _contactEmergency;

    public static final ImageVector getContactEmergency(Icons.Filled filled) {
        ImageVector imageVector = _contactEmergency;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ContactEmergency", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        a.x(pathBuilderQ, 2.08f, 20.97f, 9.85f);
        pathBuilderQ.lineToRelative(-0.75f, 1.3f);
        b.o(pathBuilderQ, -1.47f, -0.85f, 12.0f, -1.5f);
        pathBuilderQ.verticalLineToRelative(-1.7f);
        pathBuilderQ.lineToRelative(-1.47f, 0.85f);
        pathBuilderQ.lineToRelative(-0.75f, -1.3f);
        pathBuilderQ.lineTo(16.5f, 9.0f);
        pathBuilderQ.lineToRelative(-1.47f, -0.85f);
        pathBuilderQ.lineToRelative(0.75f, -1.3f);
        b.o(pathBuilderQ, 1.47f, 0.85f, 6.0f, 1.5f);
        pathBuilderQ.verticalLineToRelative(1.7f);
        pathBuilderQ.lineToRelative(1.47f, -0.85f);
        pathBuilderQ.lineToRelative(0.75f, 1.3f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderQ, 19.5f, 9.0f, 20.97f, 9.85f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactEmergency = imageVectorBuild;
        return imageVectorBuild;
    }
}
