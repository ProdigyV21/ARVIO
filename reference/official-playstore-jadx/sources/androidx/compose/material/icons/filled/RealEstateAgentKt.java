package androidx.compose.material.icons.filled;

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
import v.c;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_realEstateAgent", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RealEstateAgent", "Landroidx/compose/material/icons/Icons$Filled;", "getRealEstateAgent", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RealEstateAgentKt {
    private static ImageVector _realEstateAgent;

    public static final ImageVector getRealEstateAgent(Icons.Filled filled) {
        ImageVector imageVector = _realEstateAgent;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.RealEstateAgent", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(1.0f, 22.0f, 4.0f, 11.0f, 1.0f);
        v.a.D(pathBuilderS, 22.0f, 20.0f, 17.0f, -7.0f);
        pathBuilderS.lineToRelative(-2.09f, -0.73f);
        pathBuilderS.lineToRelative(0.33f, -0.94f);
        pathBuilderS.lineTo(13.0f, 16.0f);
        pathBuilderS.horizontalLineToRelative(2.82f);
        pathBuilderS.curveToRelative(0.65f, 0.0f, 1.18f, -0.53f, 1.18f, -1.18f);
        pathBuilderS.lineToRelative(0.0f, 0.0f);
        pathBuilderS.curveToRelative(0.0f, -0.49f, -0.31f, -0.93f, -0.77f, -1.11f);
        b.m(pathBuilderS, 8.97f, 11.0f, 7.0f, 9.02f);
        pathBuilderS.lineTo(14.0f, 22.0f);
        pathBuilderS.lineToRelative(8.0f, -3.0f);
        pathBuilderS.lineToRelative(0.0f, 0.0f);
        pathBuilderS.curveTo(21.99f, 17.9f, 21.11f, 17.0f, 20.0f, 17.0f);
        v.a.z(pathBuilderS, 14.0f, 1.5f, -7.0f, 5.0f);
        a0.b.l(pathBuilderS, 9.0f, 2.0f, 8.14f, 3.26f);
        pathBuilderS.curveTo(18.26f, 12.71f, 19.0f, 13.79f, 19.0f, 15.0f);
        a0.b.z(pathBuilderS, 2.0f, 6.5f, 14.0f, 1.5f);
        e.m(pathBuilderS, 13.5f, 10.0f, -1.0f, 9.0f);
        c.D(pathBuilderS, 1.0f, 10.0f, 13.5f, 8.0f);
        f.A(pathBuilderS, -1.0f, 7.0f, 1.0f, 8.0f);
        e.m(pathBuilderS, 15.5f, 10.0f, -1.0f, 9.0f);
        c.D(pathBuilderS, 1.0f, 10.0f, 15.5f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.v(pathBuilderS, -1.0f, 7.0f, 1.0f, 8.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _realEstateAgent = imageVectorBuild;
        return imageVectorBuild;
    }
}
