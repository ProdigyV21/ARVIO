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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_linkOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LinkOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLinkOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LinkOffKt {
    private static ImageVector _linkOff;

    public static final ImageVector getLinkOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _linkOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LinkOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.39f, 11.0f);
        pathBuilder.lineTo(16.0f, 12.61f);
        pathBuilder.verticalLineTo(11.0f);
        pathBuilder.close();
        a.C(pathBuilder, 17.0f, 7.0f, -4.0f, 1.9f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.curveToRelative(1.71f, 0.0f, 3.1f, 1.39f, 3.1f, 3.1f);
        pathBuilder.curveToRelative(0.0f, 1.27f, -0.77f, 2.37f, -1.87f, 2.84f);
        pathBuilder.lineToRelative(1.4f, 1.4f);
        pathBuilder.curveTo(21.05f, 15.36f, 22.0f, 13.79f, 22.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        a.z(pathBuilder, 2.0f, 4.27f, 3.11f, 3.11f);
        pathBuilder.curveTo(3.29f, 8.12f, 2.0f, 9.91f, 2.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        a0.a.i(pathBuilder, 4.0f, -1.9f, 7.0f);
        pathBuilder.curveToRelative(-1.71f, 0.0f, -3.1f, -1.39f, -3.1f, -3.1f);
        pathBuilder.curveToRelative(0.0f, -1.59f, 1.21f, -2.9f, 2.76f, -3.07f);
        b.m(pathBuilder, 8.73f, 11.0f, 8.0f, 2.0f);
        a0.b.x(pathBuilder, 2.73f, 13.0f, 15.27f, 17.0f);
        pathBuilder.horizontalLineToRelative(1.73f);
        pathBuilder.lineToRelative(4.01f, 4.01f);
        pathBuilder.lineToRelative(1.41f, -1.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilder, 3.41f, 2.86f, 2.0f, 4.27f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _linkOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
