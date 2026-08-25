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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_liveHelp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LiveHelp", "Landroidx/compose/material/icons/Icons$Sharp;", "getLiveHelp$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getLiveHelp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LiveHelpKt {
    private static ImageVector _liveHelp;

    public static final ImageVector getLiveHelp(Icons.Sharp sharp) {
        ImageVector imageVector = _liveHelp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.LiveHelp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(21.0f, 2.0f, 3.0f, 2.0f, 18.0f);
        pathBuilderS.horizontalLineToRelative(6.0f);
        pathBuilderS.lineToRelative(3.0f, 3.0f);
        pathBuilderS.lineToRelative(3.0f, -3.0f);
        d.q(pathBuilderS, 6.0f, 21.0f, 2.0f);
        a.C(pathBuilderS, 13.0f, 18.0f, -2.0f, -2.0f);
        b.B(pathBuilderS, 2.0f, 2.0f, 15.07f, 10.25f);
        pathBuilderS.lineToRelative(-0.9f, 0.92f);
        pathBuilderS.curveTo(13.45f, 11.9f, 13.0f, 12.5f, 13.0f, 14.0f);
        pathBuilderS.horizontalLineToRelative(-2.0f);
        pathBuilderS.verticalLineToRelative(-0.5f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, 0.45f, -2.1f, 1.17f, -2.83f);
        pathBuilderS.lineToRelative(1.24f, -1.26f);
        pathBuilderS.curveToRelative(0.37f, -0.36f, 0.59f, -0.86f, 0.59f, -1.41f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderS.lineTo(8.0f, 8.0f);
        pathBuilderS.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderS.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderS.curveToRelative(0.0f, 0.88f, -0.36f, 1.68f, -0.93f, 2.25f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _liveHelp = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getLiveHelp$annotations(Icons.Sharp sharp) {
    }
}
