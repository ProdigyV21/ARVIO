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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_markChatRead", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MarkChatRead", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMarkChatRead", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MarkChatReadKt {
    private static ImageVector _markChatRead;

    public static final ImageVector getMarkChatRead(Icons.TwoTone twoTone) {
        ImageVector imageVector = _markChatRead;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MarkChatRead", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.0f, 10.0f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.67f, 0.03f, 1.0f, 0.08f);
        c.o(pathBuilderA, 4.0f, 4.0f, 12.0f, 8.08f);
        pathBuilderA.curveTo(12.57f, 12.61f, 15.47f, 10.0f, 19.0f, 10.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.34f, 20.0f, -3.54f, -3.54f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.lineToRelative(2.12f, 2.12f);
        pathBuilderR.lineToRelative(4.24f, -4.24f);
        b.y(pathBuilderR, 23.0f, 14.34f, 17.34f, 20.0f);
        c.B(pathBuilderR, 12.08f, 16.0f, 4.0f, 4.0f);
        pathBuilderR.horizontalLineToRelative(16.0f);
        pathBuilderR.verticalLineToRelative(6.08f);
        pathBuilderR.curveToRelative(0.71f, 0.1f, 1.38f, 0.31f, 2.0f, 0.6f);
        pathBuilderR.verticalLineTo(4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        f.q(pathBuilderR, 18.0f, 4.0f, -4.0f, 6.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.14f, 0.02f, -0.27f, 0.03f, -0.4f);
        pathBuilderR.curveTo(12.01f, 17.4f, 12.0f, 17.2f, 12.0f, 17.0f);
        pathBuilderR.curveTo(12.0f, 16.66f, 12.03f, 16.33f, 12.08f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _markChatRead = imageVectorBuild;
        return imageVectorBuild;
    }
}
