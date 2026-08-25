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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_slowMotionVideo", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SlowMotionVideo", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSlowMotionVideo", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SlowMotionVideoKt {
    private static ImageVector _slowMotionVideo;

    public static final ImageVector getSlowMotionVideo(Icons.TwoTone twoTone) {
        ImageVector imageVector = _slowMotionVideo;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SlowMotionVideo", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.26f, 18.32f, 1.43f, -1.43f);
        pathBuilderR.curveToRelative(-0.86f, -1.1f, -1.44f, -2.43f, -1.62f, -3.89f);
        pathBuilderR.lineTo(2.05f, 13.0f);
        pathBuilderR.curveToRelative(0.2f, 2.01f, 1.0f, 3.84f, 2.21f, 5.32f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.1f, 5.69f);
        pathBuilderR.curveToRelative(1.11f, -0.86f, 2.44f, -1.44f, 3.9f, -1.62f);
        pathBuilderR.lineTo(11.0f, 2.05f);
        pathBuilderR.curveToRelative(-2.01f, 0.2f, -3.84f, 1.0f, -5.32f, 2.21f);
        c.z(pathBuilderR, 7.1f, 5.69f, 2.05f, 11.0f);
        pathBuilderR.horizontalLineToRelative(2.02f);
        pathBuilderR.curveToRelative(0.18f, -1.46f, 0.76f, -2.79f, 1.62f, -3.9f);
        pathBuilderR.lineTo(4.26f, 5.68f);
        pathBuilderR.curveTo(3.05f, 7.16f, 2.25f, 8.99f, 2.05f, 11.0f);
        d.k(pathBuilderR, 13.05f, 2.05f, 2.02f);
        pathBuilderR.curveTo(16.97f, 4.59f, 20.0f, 7.95f, 20.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.03f, 7.41f, -6.95f, 7.93f);
        pathBuilderR.verticalLineToRelative(2.02f);
        pathBuilderR.curveTo(18.08f, 21.42f, 22.0f, 17.16f, 22.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -5.16f, -3.92f, -9.42f, -8.95f, -9.95f);
        a.z(pathBuilderR, 16.0f, 12.0f, -2.95f, -2.21f);
        pathBuilderR.lineTo(10.0f, 7.5f);
        c.t(pathBuilderR, 9.0f, 3.05f, -2.29f);
        pathBuilderR.moveTo(5.68f, 19.74f);
        pathBuilderR.curveTo(7.16f, 20.95f, 9.0f, 21.75f, 11.0f, 21.95f);
        pathBuilderR.verticalLineToRelative(-2.02f);
        pathBuilderR.curveToRelative(-1.46f, -0.18f, -2.79f, -0.76f, -3.9f, -1.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderR, -1.42f, 1.43f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _slowMotionVideo = imageVectorBuild;
        return imageVectorBuild;
    }
}
