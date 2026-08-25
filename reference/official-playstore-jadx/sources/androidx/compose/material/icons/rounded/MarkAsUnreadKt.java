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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_markAsUnread", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MarkAsUnread", "Landroidx/compose/material/icons/Icons$Rounded;", "getMarkAsUnread", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MarkAsUnreadKt {
    private static ImageVector _markAsUnread;

    public static final ImageVector getMarkAsUnread(Icons.Rounded rounded) {
        ImageVector imageVector = _markAsUnread;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MarkAsUnread", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(16.23f, 7.0f, 2.6f);
        pathBuilderA.curveToRelative(-0.06f, -0.47f, -0.36f, -0.94f, -0.79f, -1.17f);
        pathBuilderA.lineTo(11.4f, 2.45f);
        pathBuilderA.curveToRelative(-0.56f, -0.29f, -1.23f, -0.29f, -1.8f, -0.01f);
        pathBuilderA.lineTo(2.8f, 5.83f);
        pathBuilderA.curveTo(2.32f, 6.09f, 2.0f, 6.64f, 2.0f, 7.17f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.verticalLineTo(7.4f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 10.5f, 4.0f, 16.23f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 8.0f, 7.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderQ.verticalLineToRelative(9.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(13.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineToRelative(-9.0f);
        pathBuilderQ.curveTo(22.0f, 8.9f, 21.1f, 8.0f, 20.0f, 8.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(20.0f, 11.46f);
        pathBuilderQ.curveToRelative(0.0f, 0.33f, -0.19f, 0.64f, -0.48f, 0.79f);
        pathBuilderQ.lineToRelative(-5.61f, 2.88f);
        pathBuilderQ.curveToRelative(-0.25f, 0.13f, -0.56f, 0.13f, -0.81f, 0.0f);
        pathBuilderQ.lineToRelative(-5.61f, -2.88f);
        pathBuilderQ.curveTo(7.19f, 12.1f, 7.0f, 11.79f, 7.0f, 11.46f);
        pathBuilderQ.verticalLineToRelative(0.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.67f, 0.7f, -1.1f, 1.3f, -0.79f);
        pathBuilderQ.lineToRelative(5.2f, 2.67f);
        pathBuilderQ.lineToRelative(5.2f, -2.67f);
        pathBuilderQ.curveTo(19.3f, 10.36f, 20.0f, 10.79f, 20.0f, 11.46f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderQ, 20.0f, 11.46f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _markAsUnread = imageVectorBuild;
        return imageVectorBuild;
    }
}
