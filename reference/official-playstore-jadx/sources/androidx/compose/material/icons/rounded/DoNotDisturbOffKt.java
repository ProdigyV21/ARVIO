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
import v.b;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doNotDisturbOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoNotDisturbOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getDoNotDisturbOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoNotDisturbOffKt {
    private static ImageVector _doNotDisturbOff;

    public static final ImageVector getDoNotDisturbOff(Icons.Rounded rounded) {
        ImageVector imageVector = _doNotDisturbOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DoNotDisturbOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(17.0f, 11.0f, 2.0f, -1.17f);
        pathBuilderA.lineToRelative(4.51f, 4.51f);
        pathBuilderA.curveTo(21.39f, 15.93f, 22.0f, 14.04f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -5.52f, -4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.curveTo(9.96f, 2.0f, 8.07f, 2.61f, 6.49f, 3.66f);
        a.e(pathBuilderA, 13.83f, 11.0f, 17.0f);
        pathBuilderA.moveTo(2.1f, 4.93f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.curveToRelative(-1.37f, 2.07f, -2.0f, 4.68f, -1.48f, 7.45f);
        pathBuilderA.curveToRelative(0.75f, 3.95f, 3.92f, 7.13f, 7.88f, 7.88f);
        pathBuilderA.curveToRelative(2.77f, 0.52f, 5.38f, -0.1f, 7.45f, -1.48f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineTo(3.51f, 3.51f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveTo(1.71f, 3.91f, 1.71f, 4.54f, 2.1f, 4.93f);
        v.a.g(pathBuilderA, 7.0f, 11.0f, 1.17f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.verticalLineTo(11.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doNotDisturbOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
