package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactPhone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContactPhone", "Landroidx/compose/material/icons/Icons$Sharp;", "getContactPhone", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactPhoneKt {
    private static ImageVector _contactPhone;

    public static final ImageVector getContactPhone(Icons.Sharp sharp) {
        ImageVector imageVector = _contactPhone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ContactPhone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(23.99f, 3.0f, 0.0f, 3.0f, 18.0f);
        c.v(pathBuilderS, 24.0f, -0.01f, -18.0f);
        pathBuilderS.moveTo(8.0f, 6.0f);
        pathBuilderS.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderS.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderS.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        a.p(pathBuilderS, 14.0f, 18.0f, 2.0f, 18.0f);
        pathBuilderS.verticalLineToRelative(-1.0f);
        pathBuilderS.curveToRelative(0.0f, -2.0f, 4.0f, -3.1f, 6.0f, -3.1f);
        pathBuilderS.reflectiveCurveToRelative(6.0f, 1.1f, 6.0f, 3.1f);
        c.r(pathBuilderS, 1.0f, 17.85f, 14.0f, 1.64f);
        pathBuilderS.lineTo(21.0f, 16.0f);
        pathBuilderS.lineToRelative(-1.99f, 1.99f);
        pathBuilderS.curveToRelative(-1.31f, -0.98f, -2.28f, -2.38f, -2.73f, -3.99f);
        pathBuilderS.curveToRelative(-0.18f, -0.64f, -0.28f, -1.31f, -0.28f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(0.1f, -1.36f, 0.28f, -2.0f);
        pathBuilderS.curveToRelative(0.45f, -1.62f, 1.42f, -3.01f, 2.73f, -3.99f);
        pathBuilderS.lineTo(21.0f, 8.0f);
        pathBuilderS.lineToRelative(-1.51f, 2.0f);
        pathBuilderS.horizontalLineToRelative(-1.64f);
        pathBuilderS.curveToRelative(-0.22f, 0.63f, -0.35f, 1.3f, -0.35f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderS, 0.13f, 1.37f, 0.35f, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactPhone = imageVectorBuild;
        return imageVectorBuild;
    }
}
