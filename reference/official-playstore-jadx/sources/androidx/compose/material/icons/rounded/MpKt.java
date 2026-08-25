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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Mp", "Landroidx/compose/material/icons/Icons$Rounded;", "getMp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MpKt {
    private static ImageVector _mp;

    public static final ImageVector getMp(Icons.Rounded rounded) {
        ImageVector imageVector = _mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        a.g(pathBuilderQ, 7.0f, 9.0f, 4.5f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(4.25f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveTo(11.34f, 15.0f, 11.0f, 14.66f, 11.0f, 14.25f);
        w.a.j(pathBuilderQ, 10.5f, -1.0f, 2.25f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(-0.41f, 0.0f, -0.75f, -0.34f, -0.75f, -0.75f);
        w.a.j(pathBuilderQ, 10.5f, -1.0f, 3.75f);
        pathBuilderQ.curveTo(7.5f, 14.66f, 7.16f, 15.0f, 6.75f, 15.0f);
        pathBuilderQ.horizontalLineToRelative(0.0f);
        pathBuilderQ.curveTo(6.34f, 15.0f, 6.0f, 14.66f, 6.0f, 14.25f);
        pathBuilderQ.verticalLineTo(10.0f);
        pathBuilderQ.curveTo(6.0f, 9.45f, 6.45f, 9.0f, 7.0f, 9.0f);
        a.p(pathBuilderQ, 14.25f, 15.0f, 14.25f, 15.0f);
        pathBuilderQ.curveToRelative(-0.41f, 0.0f, -0.75f, -0.33f, -0.75f, -0.75f);
        pathBuilderQ.verticalLineTo(10.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineTo(17.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(2.5f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.verticalLineToRelative(0.75f);
        pathBuilderQ.curveTo(15.0f, 14.66f, 14.66f, 15.0f, 14.25f, 15.0f);
        b.w(pathBuilderQ, 15.0f, 12.0f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderQ, 15.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mp = imageVectorBuild;
        return imageVectorBuild;
    }
}
