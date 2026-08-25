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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_repeatOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RepeatOn", "Landroidx/compose/material/icons/Icons$Rounded;", "getRepeatOn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RepeatOnKt {
    private static ImageVector _repeatOn;

    public static final ImageVector getRepeatOn(Icons.Rounded rounded) {
        ImageVector imageVector = _repeatOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.RepeatOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(21.0f, 1.0f, 3.0f);
        pathBuilderQ.curveTo(1.9f, 1.0f, 1.0f, 1.9f, 1.0f, 3.0f);
        pathBuilderQ.verticalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(18.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(3.0f);
        pathBuilderQ.curveTo(23.0f, 1.9f, 22.1f, 1.0f, 21.0f, 1.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(19.0f, 18.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineTo(7.0f);
        pathBuilderQ.verticalLineToRelative(1.79f);
        pathBuilderQ.curveToRelative(0.0f, 0.45f, -0.54f, 0.67f, -0.85f, 0.36f);
        pathBuilderQ.lineToRelative(-2.79f, -2.79f);
        pathBuilderQ.curveToRelative(-0.2f, -0.2f, -0.2f, -0.51f, 0.0f, -0.71f);
        pathBuilderQ.lineToRelative(2.79f, -2.79f);
        pathBuilderQ.curveTo(6.46f, 14.54f, 7.0f, 14.76f, 7.0f, 15.21f);
        w.a.j(pathBuilderQ, 17.0f, 10.0f, -3.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderQ, 18.0f, 20.64f, 6.35f);
        pathBuilderQ.lineToRelative(-2.79f, 2.79f);
        pathBuilderQ.curveTo(17.54f, 9.46f, 17.0f, 9.24f, 17.0f, 8.79f);
        a0.a.s(pathBuilderQ, 7.0f, 7.0f, 3.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(11.0f);
        pathBuilderQ.verticalLineTo(3.21f);
        pathBuilderQ.curveToRelative(0.0f, -0.45f, 0.54f, -0.67f, 0.85f, -0.36f);
        pathBuilderQ.lineToRelative(2.79f, 2.79f);
        pathBuilderQ.curveTo(20.84f, 5.84f, 20.84f, 6.15f, 20.64f, 6.35f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _repeatOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
