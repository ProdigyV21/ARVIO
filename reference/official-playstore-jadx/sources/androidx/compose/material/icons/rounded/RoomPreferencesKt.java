package androidx.compose.material.icons.rounded;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_roomPreferences", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RoomPreferences", "Landroidx/compose/material/icons/Icons$Rounded;", "getRoomPreferences", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoomPreferencesKt {
    private static ImageVector _roomPreferences;

    public static final ImageVector getRoomPreferences(Icons.Rounded rounded) {
        ImageVector imageVector = _roomPreferences;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RoomPreferences", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.75f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, -0.22f, -0.03f, -0.42f, -0.06f, -0.63f);
        pathBuilderA.lineToRelative(0.84f, -0.73f);
        pathBuilderA.curveToRelative(0.18f, -0.16f, 0.22f, -0.42f, 0.1f, -0.63f);
        pathBuilderA.lineToRelative(-0.59f, -1.02f);
        pathBuilderA.curveToRelative(-0.12f, -0.21f, -0.37f, -0.3f, -0.59f, -0.22f);
        pathBuilderA.lineToRelative(-1.06f, 0.36f);
        pathBuilderA.curveToRelative(-0.32f, -0.27f, -0.68f, -0.48f, -1.08f, -0.63f);
        pathBuilderA.lineToRelative(-0.22f, -1.09f);
        pathBuilderA.curveToRelative(-0.05f, -0.23f, -0.25f, -0.4f, -0.49f, -0.4f);
        pathBuilderA.horizontalLineToRelative(-1.18f);
        pathBuilderA.curveToRelative(-0.24f, 0.0f, -0.44f, 0.17f, -0.49f, 0.4f);
        pathBuilderA.lineToRelative(-0.22f, 1.09f);
        pathBuilderA.curveToRelative(-0.4f, 0.15f, -0.76f, 0.36f, -1.08f, 0.63f);
        pathBuilderA.lineToRelative(-1.06f, -0.36f);
        pathBuilderA.curveToRelative(-0.23f, -0.08f, -0.47f, 0.02f, -0.59f, 0.22f);
        pathBuilderA.lineToRelative(-0.59f, 1.02f);
        pathBuilderA.curveToRelative(-0.12f, 0.21f, -0.08f, 0.47f, 0.1f, 0.63f);
        pathBuilderA.lineToRelative(0.84f, 0.73f);
        pathBuilderA.curveToRelative(-0.03f, 0.21f, -0.06f, 0.41f, -0.06f, 0.63f);
        pathBuilderA.reflectiveCurveToRelative(0.03f, 0.42f, 0.06f, 0.63f);
        pathBuilderA.lineToRelative(-0.84f, 0.73f);
        pathBuilderA.curveToRelative(-0.18f, 0.16f, -0.22f, 0.42f, -0.1f, 0.63f);
        pathBuilderA.lineToRelative(0.59f, 1.02f);
        pathBuilderA.curveToRelative(0.12f, 0.21f, 0.37f, 0.3f, 0.59f, 0.22f);
        pathBuilderA.lineToRelative(1.06f, -0.36f);
        pathBuilderA.curveToRelative(0.32f, 0.27f, 0.68f, 0.48f, 1.08f, 0.63f);
        pathBuilderA.lineToRelative(0.22f, 1.09f);
        pathBuilderA.curveToRelative(0.05f, 0.23f, 0.25f, 0.4f, 0.49f, 0.4f);
        pathBuilderA.horizontalLineToRelative(1.18f);
        pathBuilderA.curveToRelative(0.24f, 0.0f, 0.44f, -0.17f, 0.49f, -0.4f);
        pathBuilderA.lineToRelative(0.22f, -1.09f);
        pathBuilderA.curveToRelative(0.4f, -0.15f, 0.76f, -0.36f, 1.08f, -0.63f);
        pathBuilderA.lineToRelative(1.06f, 0.36f);
        pathBuilderA.curveToRelative(0.23f, 0.08f, 0.47f, -0.02f, 0.59f, -0.22f);
        pathBuilderA.lineToRelative(0.59f, -1.02f);
        pathBuilderA.curveToRelative(0.12f, -0.21f, 0.08f, -0.47f, -0.1f, -0.63f);
        pathBuilderA.lineToRelative(-0.84f, -0.73f);
        pathBuilderA.curveTo(21.72f, 17.42f, 21.75f, 17.22f, 21.75f, 17.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 19.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(19.1f, 19.0f, 18.0f, 19.0f);
        b.t(pathBuilderA, 14.0f, 11.26f, 6.0f, 3.0f);
        f.t(pathBuilderA, 4.0f, 2.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(5.45f, 3.0f, 5.0f, 3.45f, 5.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(15.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(8.26f);
        pathBuilderA.curveTo(11.47f, 19.87f, 11.0f, 18.49f, 11.0f, 17.0f);
        pathBuilderA.curveTo(11.0f, 14.62f, 12.19f, 12.53f, 14.0f, 11.26f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 10.0f, 12.55f, 10.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _roomPreferences = imageVectorBuild;
        return imageVectorBuild;
    }
}
