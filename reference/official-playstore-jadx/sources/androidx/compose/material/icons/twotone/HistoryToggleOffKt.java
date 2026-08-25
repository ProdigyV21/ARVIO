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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_historyToggleOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HistoryToggleOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHistoryToggleOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HistoryToggleOffKt {
    private static ImageVector _historyToggleOff;

    public static final ImageVector getHistoryToggleOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _historyToggleOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HistoryToggleOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.1f, 19.37f, 1.0f, 1.74f);
        pathBuilderR.curveToRelative(-0.96f, 0.44f, -2.01f, 0.73f, -3.1f, 0.84f);
        pathBuilderR.verticalLineToRelative(-2.02f);
        pathBuilderR.curveTo(13.74f, 19.84f, 14.44f, 19.65f, 15.1f, 19.37f);
        w.a.r(pathBuilderR, 4.07f, 13.0f, 2.05f);
        pathBuilderR.curveToRelative(0.11f, 1.1f, 0.4f, 2.14f, 0.84f, 3.1f);
        pathBuilderR.lineToRelative(1.74f, -1.0f);
        pathBuilderR.curveTo(4.35f, 14.44f, 4.16f, 13.74f, 4.07f, 13.0f);
        a.z(pathBuilderR, 15.1f, 4.63f, 1.0f, -1.74f);
        pathBuilderR.curveTo(15.14f, 2.45f, 14.1f, 2.16f, 13.0f, 2.05f);
        pathBuilderR.verticalLineToRelative(2.02f);
        pathBuilderR.curveTo(13.74f, 4.16f, 14.44f, 4.35f, 15.1f, 4.63f);
        a.g(pathBuilderR, 19.93f, 11.0f, 2.02f);
        pathBuilderR.curveToRelative(-0.11f, -1.1f, -0.4f, -2.14f, -0.84f, -3.1f);
        pathBuilderR.lineToRelative(-1.74f, 1.0f);
        pathBuilderR.curveTo(19.65f, 9.56f, 19.84f, 10.26f, 19.93f, 11.0f);
        a.z(pathBuilderR, 8.9f, 19.37f, -1.0f, 1.74f);
        pathBuilderR.curveToRelative(0.96f, 0.44f, 2.01f, 0.73f, 3.1f, 0.84f);
        pathBuilderR.verticalLineToRelative(-2.02f);
        pathBuilderR.curveTo(10.26f, 19.84f, 9.56f, 19.65f, 8.9f, 19.37f);
        w.a.u(pathBuilderR, 11.0f, 4.07f, 2.05f);
        pathBuilderR.curveToRelative(-1.1f, 0.11f, -2.14f, 0.4f, -3.1f, 0.84f);
        pathBuilderR.lineToRelative(1.0f, 1.74f);
        pathBuilderR.curveTo(9.56f, 4.35f, 10.26f, 4.16f, 11.0f, 4.07f);
        a.z(pathBuilderR, 18.36f, 7.17f, 1.74f, -1.01f);
        pathBuilderR.curveToRelative(-0.63f, -0.87f, -1.4f, -1.64f, -2.27f, -2.27f);
        pathBuilderR.lineToRelative(-1.01f, 1.74f);
        pathBuilderR.curveTo(17.41f, 6.08f, 17.92f, 6.59f, 18.36f, 7.17f);
        a.z(pathBuilderR, 4.63f, 8.9f, -1.74f, -1.0f);
        pathBuilderR.curveTo(2.45f, 8.86f, 2.16f, 9.9f, 2.05f, 11.0f);
        pathBuilderR.horizontalLineToRelative(2.02f);
        pathBuilderR.curveTo(4.16f, 10.26f, 4.35f, 9.56f, 4.63f, 8.9f);
        pathBuilderR.close();
        pathBuilderR.moveTo(19.93f, 13.0f);
        pathBuilderR.curveToRelative(-0.09f, 0.74f, -0.28f, 1.44f, -0.56f, 2.1f);
        pathBuilderR.lineToRelative(1.74f, 1.0f);
        pathBuilderR.curveToRelative(0.44f, -0.96f, 0.73f, -2.01f, 0.84f, -3.1f);
        a.x(pathBuilderR, 19.93f, 16.83f, 18.36f);
        pathBuilderR.lineToRelative(1.01f, 1.74f);
        pathBuilderR.curveToRelative(0.87f, -0.63f, 1.64f, -1.4f, 2.27f, -2.27f);
        pathBuilderR.lineToRelative(-1.74f, -1.01f);
        pathBuilderR.curveTo(17.92f, 17.41f, 17.41f, 17.92f, 16.83f, 18.36f);
        a.p(pathBuilderR, 7.17f, 5.64f, 6.17f, 3.89f);
        pathBuilderR.curveTo(5.29f, 4.53f, 4.53f, 5.29f, 3.9f, 6.17f);
        pathBuilderR.lineToRelative(1.74f, 1.01f);
        pathBuilderR.curveTo(6.08f, 6.59f, 6.59f, 6.08f, 7.17f, 5.64f);
        a.p(pathBuilderR, 5.64f, 16.83f, 3.9f, 17.83f);
        pathBuilderR.curveToRelative(0.63f, 0.87f, 1.4f, 1.64f, 2.27f, 2.27f);
        pathBuilderR.lineToRelative(1.01f, -1.74f);
        pathBuilderR.curveTo(6.59f, 17.92f, 6.08f, 17.41f, 5.64f, 16.83f);
        b.w(pathBuilderR, 13.0f, 7.0f, -2.0f, 5.41f);
        pathBuilderR.lineToRelative(4.29f, 4.29f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderR, 13.0f, 11.59f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _historyToggleOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
