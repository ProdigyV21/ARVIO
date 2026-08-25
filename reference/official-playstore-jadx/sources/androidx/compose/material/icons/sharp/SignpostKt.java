package androidx.compose.material.icons.sharp;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signpost", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Signpost", "Landroidx/compose/material/icons/Icons$Sharp;", "getSignpost", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignpostKt {
    private static ImageVector _signpost;

    public static final ImageVector getSignpost(Icons.Sharp sharp) {
        ImageVector imageVector = _signpost;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Signpost", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.horizontalLineTo(5.5f);
        pathBuilderA.curveTo(4.67f, 4.0f, 4.0f, 4.67f, 4.0f, 5.5f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveTo(4.0f, 9.33f, 4.67f, 10.0f, 5.5f, 10.0f);
        a.w(pathBuilderA, 11.0f, 2.0f, 6.62f);
        pathBuilderA.curveToRelative(-0.4f, 0.0f, -0.78f, 0.16f, -1.06f, 0.44f);
        pathBuilderA.lineToRelative(-1.5f, 1.5f);
        pathBuilderA.curveToRelative(-0.59f, 0.59f, -0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.curveTo(5.84f, 17.84f, 6.22f, 18.0f, 6.62f, 18.0f);
        pathBuilderA.horizontalLineTo(11.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.horizontalLineToRelative(5.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        v.a.n(pathBuilderA, 13.0f, -2.0f, 4.38f);
        pathBuilderA.curveToRelative(0.4f, 0.0f, 0.78f, -0.16f, 1.06f, -0.44f);
        pathBuilderA.lineToRelative(1.5f, -1.5f);
        pathBuilderA.curveToRelative(0.59f, -0.59f, 0.59f, -1.54f, 0.0f, -2.12f);
        pathBuilderA.lineToRelative(-1.5f, -1.5f);
        pathBuilderA.curveTo(18.16f, 4.16f, 17.78f, 4.0f, 17.38f, 4.0f);
        pathBuilderA.horizontalLineTo(13.0f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.curveTo(13.0f, 2.45f, 12.55f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _signpost = imageVectorBuild;
        return imageVectorBuild;
    }
}
