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
import v.b;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getHdrOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrOffKt {
    private static ImageVector _hdrOff;

    public static final ImageVector getHdrOff(Icons.Rounded rounded) {
        ImageVector imageVector = _hdrOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HdrOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(17.5f, 14.25f, 17.5f, 13.0f, 1.1f);
        pathBuilderT.lineToRelative(0.72f, 1.59f);
        pathBuilderT.curveToRelative(0.11f, 0.25f, 0.36f, 0.41f, 0.63f, 0.41f);
        pathBuilderT.curveToRelative(0.5f, 0.0f, 0.83f, -0.51f, 0.64f, -0.96f);
        pathBuilderT.lineToRelative(-0.49f, -1.14f);
        pathBuilderT.curveToRelative(0.5f, -0.3f, 0.9f, -0.8f, 0.9f, -1.4f);
        pathBuilderT.verticalLineToRelative(-1.0f);
        pathBuilderT.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderT.lineTo(17.0f, 9.0f);
        pathBuilderT.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderT.verticalLineToRelative(3.9f);
        pathBuilderT.lineToRelative(1.04f, 1.04f);
        pathBuilderT.curveToRelative(0.27f, -0.11f, 0.46f, -0.38f, 0.46f, -0.69f);
        b.w(pathBuilderT, 17.5f, 10.5f, 2.0f, 1.0f);
        b.B(pathBuilderT, -2.0f, -1.0f, 13.0f, 10.5f);
        e.v(pathBuilderT, 0.4f, 1.5f, 1.5f, -1.9f);
        pathBuilderT.curveToRelative(0.0f, -0.82f, -0.68f, -1.5f, -1.5f, -1.5f);
        pathBuilderT.horizontalLineToRelative(-1.9f);
        pathBuilderT.lineToRelative(1.5f, 1.5f);
        pathBuilderT.horizontalLineToRelative(0.4f);
        pathBuilderT.close();
        pathBuilderT.moveTo(21.03f, 21.03f);
        pathBuilderT.lineToRelative(-18.0f, -18.0f);
        pathBuilderT.curveToRelative(-0.29f, -0.29f, -0.76f, -0.29f, -1.05f, 0.0f);
        pathBuilderT.curveToRelative(-0.29f, 0.29f, -0.29f, 0.76f, 0.0f, 1.05f);
        pathBuilderT.lineToRelative(4.98f, 4.98f);
        pathBuilderT.curveToRelative(-0.27f, 0.11f, -0.46f, 0.38f, -0.46f, 0.69f);
        pathBuilderT.lineTo(6.5f, 11.0f);
        pathBuilderT.horizontalLineToRelative(-2.0f);
        pathBuilderT.lineTo(4.5f, 9.75f);
        pathBuilderT.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderT.reflectiveCurveTo(3.0f, 9.34f, 3.0f, 9.75f);
        pathBuilderT.verticalLineToRelative(4.5f);
        pathBuilderT.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderT.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        b.A(pathBuilderT, 4.5f, 12.5f, 2.0f, 1.75f);
        pathBuilderT.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderT.reflectiveCurveToRelative(0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderT.lineTo(8.0f, 10.1f);
        pathBuilderT.lineToRelative(1.5f, 1.5f);
        pathBuilderT.verticalLineToRelative(2.9f);
        pathBuilderT.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderT.horizontalLineToRelative(2.5f);
        pathBuilderT.curveToRelative(0.12f, 0.0f, 0.24f, -0.01f, 0.36f, -0.04f);
        pathBuilderT.lineToRelative(7.11f, 7.11f);
        pathBuilderT.curveToRelative(0.29f, 0.29f, 0.76f, 0.29f, 1.05f, 0.0f);
        pathBuilderT.curveToRelative(0.29f, -0.28f, 0.29f, -0.75f, 0.01f, -1.04f);
        pathBuilderT.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderT.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hdrOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
