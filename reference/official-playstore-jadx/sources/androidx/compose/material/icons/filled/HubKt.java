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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hub", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hub", "Landroidx/compose/material/icons/Icons$Filled;", "getHub", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HubKt {
    private static ImageVector _hub;

    public static final ImageVector getHub(Icons.Filled filled) {
        ImageVector imageVector = _hub;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Hub", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.4f, 18.2f);
        pathBuilderA.curveTo(8.78f, 18.7f, 9.0f, 19.32f, 9.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.44f, 0.0f, 0.85f, 0.09f, 1.23f, 0.26f);
        pathBuilderA.lineToRelative(1.41f, -1.77f);
        pathBuilderA.curveToRelative(-0.92f, -1.03f, -1.29f, -2.39f, -1.09f, -3.69f);
        pathBuilderA.lineToRelative(-2.03f, -0.68f);
        pathBuilderA.curveTo(4.98f, 11.95f, 4.06f, 12.5f, 3.0f, 12.5f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.07f, 0.0f, 0.14f, -0.01f, 0.21f);
        pathBuilderA.lineToRelative(2.03f, 0.68f);
        pathBuilderA.curveToRelative(0.64f, -1.21f, 1.82f, -2.09f, 3.22f, -2.32f);
        pathBuilderA.lineToRelative(0.0f, -2.16f);
        pathBuilderA.curveTo(9.96f, 5.57f, 9.0f, 4.4f, 9.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, 1.4f, -0.96f, 2.57f, -2.25f, 2.91f);
        pathBuilderA.verticalLineToRelative(2.16f);
        pathBuilderA.curveToRelative(1.4f, 0.23f, 2.58f, 1.11f, 3.22f, 2.32f);
        pathBuilderA.lineToRelative(2.03f, -0.68f);
        pathBuilderA.curveTo(18.0f, 9.64f, 18.0f, 9.57f, 18.0f, 9.5f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.curveToRelative(-1.06f, 0.0f, -1.98f, -0.55f, -2.52f, -1.37f);
        pathBuilderA.lineToRelative(-2.03f, 0.68f);
        pathBuilderA.curveToRelative(0.2f, 1.29f, -0.16f, 2.65f, -1.09f, 3.69f);
        pathBuilderA.lineToRelative(1.41f, 1.77f);
        pathBuilderA.curveTo(17.15f, 17.09f, 17.56f, 17.0f, 18.0f, 17.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.68f, 0.22f, -1.3f, 0.6f, -1.8f);
        pathBuilderA.lineToRelative(-1.41f, -1.77f);
        pathBuilderA.curveToRelative(-1.35f, 0.75f, -3.01f, 0.76f, -4.37f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 8.4f, 18.2f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hub = imageVectorBuild;
        return imageVectorBuild;
    }
}
