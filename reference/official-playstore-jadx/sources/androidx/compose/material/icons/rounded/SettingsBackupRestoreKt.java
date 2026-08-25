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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsBackupRestore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsBackupRestore", "Landroidx/compose/material/icons/Icons$Rounded;", "getSettingsBackupRestore", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsBackupRestoreKt {
    private static ImageVector _settingsBackupRestore;

    public static final ImageVector getSettingsBackupRestore(Icons.Rounded rounded) {
        ImageVector imageVector = _settingsBackupRestore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SettingsBackupRestore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.77f, 3.0f);
        pathBuilderA.curveToRelative(-2.65f, 0.07f, -5.0f, 1.28f, -6.6f, 3.16f);
        pathBuilderA.lineTo(3.85f, 4.85f);
        pathBuilderA.curveTo(3.54f, 4.54f, 3.0f, 4.76f, 3.0f, 5.21f);
        pathBuilderA.verticalLineTo(9.5f);
        pathBuilderA.curveTo(3.0f, 9.78f, 3.22f, 10.0f, 3.5f, 10.0f);
        pathBuilderA.horizontalLineToRelative(4.29f);
        pathBuilderA.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderA.lineTo(6.59f, 7.59f);
        pathBuilderA.curveTo(7.88f, 6.02f, 9.82f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(4.32f, 0.0f, 7.74f, 3.94f, 6.86f, 8.41f);
        pathBuilderA.curveToRelative(-0.54f, 2.77f, -2.81f, 4.98f, -5.58f, 5.47f);
        pathBuilderA.curveToRelative(-3.8f, 0.68f, -7.18f, -1.74f, -8.05f, -5.16f);
        pathBuilderA.curveTo(5.11f, 13.3f, 4.71f, 13.0f, 4.27f, 13.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.65f, 0.0f, -1.14f, 0.61f, -0.98f, 1.23f);
        pathBuilderA.curveTo(4.28f, 18.12f, 7.8f, 21.0f, 12.0f, 21.0f);
        pathBuilderA.curveToRelative(5.06f, 0.0f, 9.14f, -4.17f, 9.0f, -9.26f);
        pathBuilderA.curveTo(20.86f, 6.86f, 16.65f, 2.88f, 11.77f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 14.0f, 13.1f, 14.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsBackupRestore = imageVectorBuild;
        return imageVectorBuild;
    }
}
