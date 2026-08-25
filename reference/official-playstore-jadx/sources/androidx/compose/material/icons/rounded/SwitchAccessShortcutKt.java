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
import v.a;
import v.b;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_switchAccessShortcut", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwitchAccessShortcut", "Landroidx/compose/material/icons/Icons$Rounded;", "getSwitchAccessShortcut", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchAccessShortcutKt {
    private static ImageVector _switchAccessShortcut;

    public static final ImageVector getSwitchAccessShortcut(Icons.Rounded rounded) {
        ImageVector imageVector = _switchAccessShortcut;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SwitchAccessShortcut", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(7.06f, 8.94f, 5.0f, 8.0f);
        pathBuilderJ.lineToRelative(2.06f, -0.94f);
        pathBuilderJ.lineTo(8.0f, 5.0f);
        pathBuilderJ.lineToRelative(0.94f, 2.06f);
        pathBuilderJ.lineTo(11.0f, 8.0f);
        pathBuilderJ.lineTo(8.94f, 8.94f);
        b.y(pathBuilderJ, 8.0f, 11.0f, 7.06f, 8.94f);
        pathBuilderJ.moveTo(8.0f, 21.0f);
        pathBuilderJ.lineToRelative(0.94f, -2.06f);
        pathBuilderJ.lineTo(11.0f, 18.0f);
        pathBuilderJ.lineToRelative(-2.06f, -0.94f);
        pathBuilderJ.lineTo(8.0f, 15.0f);
        pathBuilderJ.lineToRelative(-0.94f, 2.06f);
        pathBuilderJ.lineTo(5.0f, 18.0f);
        d.C(pathBuilderJ, 2.06f, 0.94f, 8.0f, 21.0f);
        pathBuilderJ.moveTo(4.37f, 12.37f);
        pathBuilderJ.lineTo(3.0f, 13.0f);
        pathBuilderJ.lineToRelative(1.37f, 0.63f);
        pathBuilderJ.lineTo(5.0f, 15.0f);
        pathBuilderJ.lineToRelative(0.63f, -1.37f);
        pathBuilderJ.lineTo(7.0f, 13.0f);
        pathBuilderJ.lineToRelative(-1.37f, -0.63f);
        b.y(pathBuilderJ, 5.0f, 11.0f, 4.37f, 12.37f);
        pathBuilderJ.moveTo(19.0f, 20.41f);
        pathBuilderJ.curveToRelative(0.0f, 0.78f, -0.84f, 1.25f, -1.51f, 0.86f);
        pathBuilderJ.curveTo(14.21f, 19.36f, 12.0f, 15.79f, 12.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.73f, 1.08f, -5.27f, 2.75f, -7.25f);
        pathBuilderJ.lineToRelative(-1.9f, -1.9f);
        pathBuilderJ.curveTo(12.54f, 2.54f, 12.76f, 2.0f, 13.21f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(5.29f);
        pathBuilderJ.curveTo(18.78f, 2.0f, 19.0f, 2.22f, 19.0f, 2.5f);
        pathBuilderJ.verticalLineToRelative(5.29f);
        pathBuilderJ.curveToRelative(0.0f, 0.45f, -0.54f, 0.67f, -0.85f, 0.35f);
        pathBuilderJ.lineToRelative(-1.97f, -1.97f);
        pathBuilderJ.curveTo(14.84f, 7.82f, 14.0f, 9.88f, 14.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 3.13f, 1.86f, 6.01f, 4.51f, 7.55f);
        pathBuilderJ.curveTo(18.81f, 19.73f, 19.0f, 20.06f, 19.0f, 20.41f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _switchAccessShortcut = imageVectorBuild;
        return imageVectorBuild;
    }
}
