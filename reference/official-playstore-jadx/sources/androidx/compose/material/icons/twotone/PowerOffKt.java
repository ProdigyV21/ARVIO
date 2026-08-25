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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_powerOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PowerOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPowerOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PowerOffKt {
    private static ImageVector _powerOff;

    public static final ImageVector getPowerOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _powerOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PowerOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(12.12f, 9.0f, 16.0f, 12.88f);
        c.z(pathBuilderJ, 16.0f, 9.0f, 11.5f, 17.17f);
        b.A(pathBuilderJ, 11.5f, 19.0f, 1.0f, -1.83f);
        pathBuilderJ.lineToRelative(1.07f, -1.06f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderJ, 8.0f, 10.54f, 3.11f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(10.0f, 3.0f, 8.0f, 3.0f, 1.88f);
        b.C(pathBuilderS, 2.0f, 2.0f, 16.0f, 9.0f);
        pathBuilderS.verticalLineToRelative(3.88f);
        pathBuilderS.lineToRelative(1.8f, 1.8f);
        pathBuilderS.lineToRelative(0.2f, -0.2f);
        pathBuilderS.lineTo(18.0f, 9.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        b.A(pathBuilderS, 16.0f, 3.0f, -2.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(-3.88f);
        d.C(pathBuilderS, 2.0f, 2.0f, 16.0f, 9.0f);
        pathBuilderS.moveTo(4.12f, 3.84f);
        pathBuilderS.lineTo(2.71f, 5.25f);
        pathBuilderS.lineTo(6.0f, 8.54f);
        d.r(pathBuilderS, 5.96f, 9.5f, 18.0f, 3.0f);
        f.y(pathBuilderS, 5.0f, -3.0f, 0.48f, -0.48f);
        pathBuilderS.lineToRelative(4.47f, 4.47f);
        d.C(pathBuilderS, 1.41f, -1.41f, 4.12f, 3.84f);
        pathBuilderS.moveTo(12.5f, 17.17f);
        b.A(pathBuilderS, 12.5f, 19.0f, -1.0f, -1.83f);
        pathBuilderS.lineTo(8.0f, 13.65f);
        pathBuilderS.verticalLineToRelative(-3.11f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 5.57f, 5.57f, -1.07f, 1.06f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _powerOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
