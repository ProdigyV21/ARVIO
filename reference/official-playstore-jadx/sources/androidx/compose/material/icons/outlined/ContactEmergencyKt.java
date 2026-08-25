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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactEmergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactEmergency", "Landroidx/compose/material/icons/Icons$Outlined;", "getContactEmergency", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactEmergencyKt {
    private static ImageVector _contactEmergency;

    public static final ImageVector getContactEmergency(Icons.Outlined outlined) {
        ImageVector imageVector = _contactEmergency;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ContactEmergency", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 14.0f);
        pathBuilderA.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveTo(7.35f, 14.0f, 9.0f, 14.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 10.0f);
        pathBuilderA.curveToRelative(0.54f, 0.0f, 1.0f, 0.46f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.46f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.46f, -1.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 8.46f, 10.0f, 9.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(22.0f, 3.0f, 2.0f);
        pathBuilderQ.curveTo(0.9f, 3.0f, 0.0f, 3.9f, 0.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(20.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 1.99f, -0.9f, 1.99f, -2.0f);
        pathBuilderQ.lineTo(24.0f, 5.0f);
        pathBuilderQ.curveTo(24.0f, 3.9f, 23.1f, 3.0f, 22.0f, 3.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(4.54f, 19.0f);
        pathBuilderQ.curveToRelative(1.1f, -1.22f, 2.69f, -2.0f, 4.46f, -2.0f);
        pathBuilderQ.reflectiveCurveToRelative(3.36f, 0.78f, 4.46f, 2.0f);
        w.a.v(pathBuilderQ, 4.54f, 22.0f, 19.0f, -6.08f);
        pathBuilderQ.curveToRelative(-1.38f, -2.39f, -3.96f, -4.0f, -6.92f, -4.0f);
        pathBuilderQ.reflectiveCurveToRelative(-5.54f, 1.61f, -6.92f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.i(pathBuilderQ, 2.0f, 5.0f, 20.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.78f, 11.15f, 1.47f, -0.85f);
        pathBuilderR.lineToRelative(0.0f, 1.7f);
        pathBuilderR.lineToRelative(1.5f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -1.7f);
        pathBuilderR.lineToRelative(1.47f, 0.85f);
        pathBuilderR.lineToRelative(0.75f, -1.3f);
        pathBuilderR.lineToRelative(-1.47f, -0.85f);
        pathBuilderR.lineToRelative(1.47f, -0.85f);
        pathBuilderR.lineToRelative(-0.75f, -1.3f);
        pathBuilderR.lineToRelative(-1.47f, 0.85f);
        pathBuilderR.lineToRelative(0.0f, -1.7f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 1.7f);
        pathBuilderR.lineToRelative(-1.47f, -0.85f);
        pathBuilderR.lineToRelative(-0.75f, 1.3f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 1.47f, 0.85f, -1.47f, 0.85f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactEmergency = imageVectorBuild;
        return imageVectorBuild;
    }
}
