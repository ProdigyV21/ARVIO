package androidx.compose.material.icons.outlined;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsVoice", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsVoice", "Landroidx/compose/material/icons/Icons$Outlined;", "getSettingsVoice", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsVoiceKt {
    private static ImageVector _settingsVoice;

    public static final ImageVector getSettingsVoice(Icons.Outlined outlined) {
        ImageVector imageVector = _settingsVoice;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SettingsVoice", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(7.0f, 22.0f, 2.0f, 2.0f);
        c.z(pathBuilderA, 7.0f, 24.0f, 12.0f, 13.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.lineTo(15.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 2.34f, 9.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.curveToRelative(0.0f, 0.56f, -0.44f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        c.z(pathBuilderA, 11.0f, 4.0f, 11.0f, 22.0f);
        d.x(pathBuilderA, 2.0f, 2.0f, -2.0f);
        a.C(pathBuilderA, 15.0f, 22.0f, 2.0f, 2.0f);
        e.r(pathBuilderA, -2.0f, 19.0f, 10.0f, -1.7f);
        pathBuilderA.curveToRelative(0.0f, 3.0f, -2.54f, 5.1f, -5.3f, 5.1f);
        pathBuilderA.reflectiveCurveTo(6.7f, 13.0f, 6.7f, 10.0f);
        pathBuilderA.lineTo(5.0f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, 3.41f, 2.72f, 6.23f, 6.0f, 6.72f);
        b.A(pathBuilderA, 11.0f, 20.0f, 2.0f, -3.28f);
        pathBuilderA.curveToRelative(3.28f, -0.49f, 6.0f, -3.31f, 6.0f, -6.72f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settingsVoice = imageVectorBuild;
        return imageVectorBuild;
    }
}
