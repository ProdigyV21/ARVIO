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
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localAirport", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalAirport", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalAirport", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalAirportKt {
    private static ImageVector _localAirport;

    public static final ImageVector getLocalAirport(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localAirport;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalAirport", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(22.0f, 16.0f, -2.0f, -8.5f, -5.0f);
        pathBuilderG.verticalLineTo(3.5f);
        pathBuilderG.curveTo(13.5f, 2.67f, 12.83f, 2.0f, 12.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderG.verticalLineTo(9.0f);
        pathBuilderG.lineTo(2.0f, 14.0f);
        pathBuilderG.verticalLineToRelative(2.0f);
        pathBuilderG.lineToRelative(8.5f, -2.5f);
        pathBuilderG.verticalLineTo(19.0f);
        pathBuilderG.lineTo(8.0f, 20.5f);
        pathBuilderG.lineTo(8.0f, 22.0f);
        pathBuilderG.lineToRelative(4.0f, -1.0f);
        pathBuilderG.lineToRelative(4.0f, 1.0f);
        pathBuilderG.lineToRelative(0.0f, -1.5f);
        pathBuilderG.lineTo(13.5f, 19.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderG, -5.5f, 22.0f, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localAirport = imageVectorBuild;
        return imageVectorBuild;
    }
}
