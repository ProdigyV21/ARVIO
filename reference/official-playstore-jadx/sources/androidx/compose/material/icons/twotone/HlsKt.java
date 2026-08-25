package androidx.compose.material.icons.twotone;

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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hls", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Hls", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHls", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HlsKt {
    private static ImageVector _hls;

    public static final ImageVector getHls(Icons.TwoTone twoTone) {
        ImageVector imageVector = _hls;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Hls", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = b.b(6.5f, 9.0f, 8.0f, 6.0f, 6.5f);
        c.p(pathBuilderB, -2.5f, -2.0f, 15.0f, 3.0f);
        v.b.r(pathBuilderB, 9.0f, 1.5f, 2.0f, 2.0f);
        a.D(pathBuilderB, 9.0f, 16.5f, 15.0f, 3.0f);
        pathBuilderB.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderB.verticalLineToRelative(-1.5f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a0.a.e(pathBuilderB, 17.0f, -1.0f, 2.0f, 11.0f);
        pathBuilderB.horizontalLineToRelative(1.5f);
        pathBuilderB.verticalLineToRelative(-1.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderB.horizontalLineToRelative(-3.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderB.verticalLineToRelative(1.5f);
        pathBuilderB.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a0.a.e(pathBuilderB, 19.0f, 1.0f, -2.0f, 13.0f);
        pathBuilderB.horizontalLineToRelative(-1.5f);
        pathBuilderB.verticalLineToRelative(1.0f);
        pathBuilderB.curveTo(15.5f, 14.55f, 15.95f, 15.0f, 16.5f, 15.0f);
        c.y(pathBuilderB, 14.0f, 15.0f, -1.5f, -2.5f);
        pathBuilderB.verticalLineTo(9.0f);
        pathBuilderB.horizontalLineTo(10.0f);
        pathBuilderB.verticalLineToRelative(6.0f);
        pathBuilderB.horizontalLineTo(14.0f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _hls = imageVectorBuild;
        return imageVectorBuild;
    }
}
