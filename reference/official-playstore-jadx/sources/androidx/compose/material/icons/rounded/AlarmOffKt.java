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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarmOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AlarmOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getAlarmOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmOffKt {
    private static ImageVector _alarmOff;

    public static final ImageVector getAlarmOff(Icons.Rounded rounded) {
        ImageVector imageVector = _alarmOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AlarmOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.04f, 6.29f);
        pathBuilderA.curveTo(10.66f, 6.11f, 11.32f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(3.86f, 0.0f, 7.0f, 3.14f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.68f, -0.11f, 1.34f, -0.29f, 1.96f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.curveToRelative(0.47f, -1.08f, 0.73f, -2.27f, 0.73f, -3.52f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.curveToRelative(-1.25f, 0.0f, -2.44f, 0.26f, -3.53f, 0.72f);
        b.C(pathBuilderA, 1.57f, 1.57f, 3.71f, 2.79f);
        pathBuilderA.curveToRelative(-0.38f, -0.38f, -1.0f, -0.38f, -1.39f, 0.0f);
        pathBuilderA.lineToRelative(-0.02f, 0.03f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.01f, 0.0f, 1.39f);
        pathBuilderA.lineToRelative(0.68f, 0.68f);
        pathBuilderA.lineToRelative(-0.17f, 0.14f);
        pathBuilderA.curveToRelative(-0.42f, 0.34f, -0.47f, 0.96f, -0.13f, 1.38f);
        pathBuilderA.lineToRelative(0.03f, 0.03f);
        pathBuilderA.curveToRelative(0.35f, 0.42f, 0.96f, 0.47f, 1.38f, 0.12f);
        pathBuilderA.lineToRelative(0.31f, -0.25f);
        pathBuilderA.lineToRelative(0.8f, 0.8f);
        pathBuilderA.curveTo(3.83f, 8.69f, 3.0f, 10.75f, 3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(2.25f, 0.0f, 4.31f, -0.83f, 5.89f, -2.2f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.curveToRelative(0.38f, 0.38f, 1.0f, 0.38f, 1.39f, 0.0f);
        pathBuilderA.lineToRelative(0.03f, -0.03f);
        pathBuilderA.curveToRelative(0.38f, -0.38f, 0.38f, -1.0f, 0.0f, -1.39f);
        b.C(pathBuilderA, -17.01f, -17.0f, 12.0f, 20.0f);
        pathBuilderA.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.7f, 0.61f, -3.26f, 1.62f, -4.47f);
        pathBuilderA.lineToRelative(9.85f, 9.85f);
        pathBuilderA.curveTo(15.26f, 19.39f, 13.7f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.91f, 6.56f);
        pathBuilderA.curveToRelative(0.42f, 0.35f, 1.03f, 0.29f, 1.38f, -0.12f);
        pathBuilderA.lineToRelative(0.03f, -0.03f);
        pathBuilderA.curveToRelative(0.35f, -0.42f, 0.29f, -1.03f, -0.12f, -1.38f);
        pathBuilderA.lineToRelative(-3.1f, -2.59f);
        pathBuilderA.curveToRelative(-0.42f, -0.35f, -1.03f, -0.29f, -1.38f, 0.12f);
        pathBuilderA.lineToRelative(-0.03f, 0.03f);
        pathBuilderA.curveToRelative(-0.35f, 0.42f, -0.29f, 1.03f, 0.12f, 1.38f);
        b.C(pathBuilderA, 3.1f, 2.59f, 7.43f, 3.68f);
        pathBuilderA.curveToRelative(0.18f, -0.34f, 0.15f, -0.77f, -0.11f, -1.09f);
        pathBuilderA.lineToRelative(-0.03f, -0.03f);
        pathBuilderA.curveToRelative(-0.3f, -0.36f, -0.8f, -0.43f, -1.2f, -0.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, 1.34f, 1.34f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarmOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
