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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hearingDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HearingDisabled", "Landroidx/compose/material/icons/Icons$Sharp;", "getHearingDisabled", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HearingDisabledKt {
    private static ImageVector _hearingDisabled;

    public static final ImageVector getHearingDisabled(Icons.Sharp sharp) {
        ImageVector imageVector = _hearingDisabled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HearingDisabled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.03f, 3.2f);
        pathBuilderA.curveTo(7.15f, 2.44f, 8.51f, 2.0f, 10.0f, 2.0f);
        pathBuilderA.curveToRelative(3.93f, 0.0f, 7.0f, 3.07f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 1.26f, -0.38f, 2.65f, -1.07f, 3.9f);
        pathBuilderA.curveToRelative(-0.02f, 0.04f, -0.05f, 0.08f, -0.08f, 0.13f);
        pathBuilderA.lineToRelative(-1.48f, -1.48f);
        pathBuilderA.curveTo(14.77f, 10.69f, 15.0f, 9.8f, 15.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -2.8f, -2.2f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.curveTo(9.08f, 4.0f, 8.24f, 4.26f, 7.5f, 4.67f);
        c.z(pathBuilderA, 6.03f, 3.2f, 17.21f, 14.38f);
        pathBuilderA.lineToRelative(1.43f, 1.43f);
        pathBuilderA.curveTo(20.11f, 13.93f, 21.0f, 11.57f, 21.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -3.04f, -1.23f, -5.79f, -3.22f, -7.78f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(17.99f, 4.26f, 19.0f, 6.51f, 19.0f, 9.0f);
        pathBuilderA.curveTo(19.0f, 11.02f, 18.33f, 12.88f, 17.21f, 14.38f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.0f, 6.5f);
        pathBuilderA.curveToRelative(-0.21f, 0.0f, -0.4f, 0.03f, -0.59f, 0.08f);
        pathBuilderA.lineToRelative(3.01f, 3.01f);
        pathBuilderA.curveTo(12.47f, 9.4f, 12.5f, 9.21f, 12.5f, 9.0f);
        pathBuilderA.curveTo(12.5f, 7.62f, 11.38f, 6.5f, 10.0f, 6.5f);
        a.p(pathBuilderA, 21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderA.lineTo(1.39f, 4.22f);
        pathBuilderA.lineToRelative(2.13f, 2.13f);
        pathBuilderA.curveTo(3.19f, 7.16f, 3.0f, 8.05f, 3.0f, 9.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.36f, 0.05f, -0.71f, 0.12f, -1.05f);
        pathBuilderA.lineToRelative(6.61f, 6.61f);
        pathBuilderA.curveToRelative(-0.88f, 0.68f, -1.78f, 1.41f, -2.27f, 2.9f);
        pathBuilderA.curveToRelative(-0.5f, 1.5f, -1.0f, 2.01f, -1.71f, 2.38f);
        pathBuilderA.curveTo(7.56f, 19.94f, 7.29f, 20.0f, 7.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.57f, 0.0f, 1.13f, -0.12f, 1.64f, -0.35f);
        pathBuilderA.curveToRelative(1.36f, -0.71f, 2.13f, -1.73f, 2.73f, -3.55f);
        pathBuilderA.curveToRelative(0.32f, -0.98f, 0.9f, -1.43f, 1.71f, -2.05f);
        pathBuilderA.curveToRelative(0.03f, -0.02f, 0.05f, -0.04f, 0.08f, -0.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 6.62f, 6.62f, 21.19f, 21.19f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hearingDisabled = imageVectorBuild;
        return imageVectorBuild;
    }
}
