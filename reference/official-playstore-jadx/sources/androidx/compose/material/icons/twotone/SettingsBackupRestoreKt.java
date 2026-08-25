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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsBackupRestore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsBackupRestore", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSettingsBackupRestore", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsBackupRestoreKt {
    private static ImageVector _settingsBackupRestore;

    public static final ImageVector getSettingsBackupRestore(Icons.TwoTone twoTone) {
        ImageVector imageVector = _settingsBackupRestore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SettingsBackupRestore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 3.0f);
        pathBuilderA.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderA.lineTo(0.0f, 12.0f);
        pathBuilderA.lineToRelative(4.0f, 4.0f);
        pathBuilderA.lineToRelative(4.0f, -4.0f);
        pathBuilderA.lineTo(5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -3.87f, 3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.13f, 7.0f, -7.0f, 7.0f);
        pathBuilderA.curveToRelative(-1.51f, 0.0f, -2.91f, -0.49f, -4.06f, -1.3f);
        pathBuilderA.lineToRelative(-1.42f, 1.44f);
        pathBuilderA.curveTo(8.04f, 20.3f, 9.94f, 21.0f, 12.0f, 21.0f);
        pathBuilderA.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -4.03f, -9.0f, -9.0f, -9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsBackupRestore = imageVectorBuild;
        return imageVectorBuild;
    }
}
