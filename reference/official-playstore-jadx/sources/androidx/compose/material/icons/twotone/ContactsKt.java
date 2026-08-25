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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contacts", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Contacts", "Landroidx/compose/material/icons/Icons$TwoTone;", "getContacts", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactsKt {
    private static ImageVector _contacts;

    public static final ImageVector getContacts(Icons.TwoTone twoTone) {
        ImageVector imageVector = _contacts;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Contacts", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(20.0f, 6.0f, 4.0f, 6.0f, 12.0f);
        d.q(pathBuilderS, 16.0f, 20.0f, 6.0f);
        pathBuilderS.moveTo(12.0f, 7.0f);
        pathBuilderS.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderS.reflectiveCurveTo(13.38f, 12.0f, 12.0f, 12.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderS.reflectiveCurveTo(10.62f, 7.0f, 12.0f, 7.0f);
        a.p(pathBuilderS, 17.0f, 17.0f, 7.0f, 17.0f);
        pathBuilderS.verticalLineToRelative(-1.01f);
        pathBuilderS.curveTo(7.0f, 13.9f, 10.31f, 13.0f, 12.0f, 13.0f);
        pathBuilderS.reflectiveCurveToRelative(5.0f, 0.9f, 5.0f, 2.99f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderS, 17.0f, 17.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(4.0f, 20.0f, 16.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineTo(22.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.lineTo(4.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        b.w(pathBuilderA, 4.0f, 6.0f, 16.0f, 12.0f);
        b.y(pathBuilderA, 4.0f, 18.0f, 4.0f, 6.0f);
        a.C(pathBuilderA, 4.0f, 0.0f, 16.0f, 2.0f);
        c.z(pathBuilderA, 4.0f, 2.0f, 4.0f, 22.0f);
        d.m(pathBuilderA, 16.0f, 2.0f, 4.0f, 24.0f);
        pathBuilderA.moveTo(12.0f, 12.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveTo(13.38f, 7.0f, 12.0f, 7.0f);
        pathBuilderA.reflectiveCurveTo(9.5f, 8.12f, 9.5f, 9.5f);
        pathBuilderA.reflectiveCurveTo(10.62f, 12.0f, 12.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 8.5f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 13.0f);
        pathBuilderA.curveToRelative(-1.69f, 0.0f, -5.0f, 0.9f, -5.0f, 2.99f);
        b.A(pathBuilderA, 7.0f, 17.0f, 10.0f, -1.01f);
        pathBuilderA.curveTo(17.0f, 13.9f, 13.69f, 13.0f, 12.0f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.81f, 15.5f);
        pathBuilderA.curveToRelative(0.61f, -0.52f, 2.03f, -1.0f, 3.19f, -1.0f);
        pathBuilderA.curveToRelative(1.17f, 0.0f, 2.59f, 0.48f, 3.2f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 8.81f, 15.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contacts = imageVectorBuild;
        return imageVectorBuild;
    }
}
