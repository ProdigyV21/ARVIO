package androidx.compose.material.icons.sharp;

import a0.a;
import a0.b;
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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_connectingAirports", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ConnectingAirports", "Landroidx/compose/material/icons/Icons$Sharp;", "getConnectingAirports", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConnectingAirportsKt {
    private static ImageVector _connectingAirports;

    public static final ImageVector getConnectingAirports(Icons.Sharp sharp) {
        ImageVector imageVector = _connectingAirports;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.ConnectingAirports", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(15.4f, 17.0f, 1.3f, 4.4f, -1.1f);
        pathBuilderC.lineTo(13.0f, 17.0f);
        pathBuilderC.horizontalLineToRelative(-3.0f);
        pathBuilderC.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderC.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        e.C(pathBuilderC, 3.0f, 2.6f, -4.4f, 1.1f);
        pathBuilderC.lineTo(15.4f, 15.0f);
        c.A(pathBuilderC, 2.85f, 19.0f, 14.0f, 1.0f);
        pathBuilderC.lineToRelative(-0.6f, 2.0f);
        pathBuilderC.lineToRelative(0.6f, 2.0f);
        b.B(pathBuilderC, -1.0f, -0.75f, -1.0f, 15.4f);
        pathBuilderC.moveTo(5.75f, 7.0f);
        pathBuilderC.lineTo(5.0f, 6.0f);
        pathBuilderC.horizontalLineTo(4.0f);
        pathBuilderC.lineToRelative(0.6f, 2.0f);
        pathBuilderC.lineTo(4.0f, 10.0f);
        a.n(pathBuilderC, 1.0f, 0.75f, -1.0f, 8.6f);
        pathBuilderC.lineToRelative(-1.3f, 4.4f);
        c.A(pathBuilderC, 1.1f, 11.0f, 9.0f, 3.0f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderC.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        w.a.f(pathBuilderC, -3.0f, 8.4f, 2.6f, 7.3f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.w(pathBuilderC, 8.6f, 7.0f, 5.75f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _connectingAirports = imageVectorBuild;
        return imageVectorBuild;
    }
}
