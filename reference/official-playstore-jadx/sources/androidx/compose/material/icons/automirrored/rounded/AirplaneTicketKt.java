package androidx.compose.material.icons.automirrored.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airplaneTicket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirplaneTicket", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getAirplaneTicket", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirplaneTicketKt {
    private static ImageVector _airplaneTicket;

    public static final ImageVector getAirplaneTicket(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _airplaneTicket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.AirplaneTicket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.19f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.01f, 4.9f, 2.01f, 6.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.curveTo(3.11f, 10.0f, 4.0f, 10.9f, 4.0f, 12.0f);
        pathBuilderQ.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.19f, 4.0f, 20.19f, 4.0f);
        a.z(pathBuilderQ, 17.73f, 13.3f, -8.49f, 2.26f);
        pathBuilderQ.curveToRelative(-0.22f, 0.06f, -0.45f, -0.04f, -0.56f, -0.23f);
        pathBuilderQ.lineToRelative(-1.12f, -1.95f);
        pathBuilderQ.curveToRelative(-0.18f, -0.3f, -0.01f, -0.69f, 0.32f, -0.78f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.16f, -0.04f, 0.34f, -0.01f, 0.47f, 0.1f);
        pathBuilderQ.lineToRelative(1.05f, 0.82f);
        pathBuilderQ.lineToRelative(2.39f, -0.64f);
        pathBuilderQ.lineTo(9.9f, 9.6f);
        pathBuilderQ.curveToRelative(-0.26f, -0.44f, -0.02f, -1.01f, 0.47f, -1.15f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(0.26f, -0.07f, 0.54f, 0.0f, 0.74f, 0.18f);
        pathBuilderQ.lineToRelative(3.69f, 3.44f);
        pathBuilderQ.lineToRelative(2.44f, -0.65f);
        pathBuilderQ.curveToRelative(0.51f, -0.14f, 1.04f, 0.17f, 1.18f, 0.68f);
        pathBuilderQ.curveTo(18.55f, 12.62f, 18.25f, 13.15f, 17.73f, 13.3f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airplaneTicket = imageVectorBuild;
        return imageVectorBuild;
    }
}
