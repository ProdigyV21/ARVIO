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
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dangerous", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Dangerous", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDangerous", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DangerousKt {
    private static ImageVector _dangerous;

    public static final ImageVector getDangerous(Icons.TwoTone twoTone) {
        ImageVector imageVector = _dangerous;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Dangerous", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(9.1f, 5.0f, 5.0f, 9.1f, 5.8f);
        pathBuilderS.lineTo(9.1f, 19.0f);
        e.n(pathBuilderS, 5.8f, 4.1f, -4.1f, 9.1f);
        a.e(pathBuilderS, 14.9f, 5.0f, 9.1f);
        pathBuilderS.moveTo(16.24f, 14.83f);
        pathBuilderS.lineToRelative(-1.41f, 1.41f);
        pathBuilderS.lineTo(12.0f, 13.41f);
        pathBuilderS.lineToRelative(-2.83f, 2.83f);
        pathBuilderS.lineToRelative(-1.41f, -1.41f);
        pathBuilderS.lineTo(10.59f, 12.0f);
        pathBuilderS.lineTo(7.76f, 9.17f);
        pathBuilderS.lineToRelative(1.41f, -1.41f);
        pathBuilderS.lineTo(12.0f, 10.59f);
        pathBuilderS.lineToRelative(2.83f, -2.83f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderS, 13.41f, 12.0f, 16.24f, 14.83f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderP = y.a.p(15.73f, 3.0f, 8.27f, 3.0f, 8.27f);
        b.k(pathBuilderP, 7.46f, 8.27f, 21.0f, 7.46f);
        pathBuilderP.lineTo(21.0f, 15.73f);
        c.x(pathBuilderP, 8.27f, 15.73f, 3.0f);
        pathBuilderP.moveTo(19.0f, 14.9f);
        pathBuilderP.lineTo(14.9f, 19.0f);
        pathBuilderP.horizontalLineTo(9.1f);
        pathBuilderP.lineTo(5.0f, 14.9f);
        pathBuilderP.verticalLineTo(9.1f);
        pathBuilderP.lineTo(9.1f, 5.0f);
        pathBuilderP.horizontalLineToRelative(5.8f);
        pathBuilderP.lineTo(19.0f, 9.1f);
        pathBuilderP.verticalLineTo(14.9f);
        pathBuilderP.close();
        pathBuilderP.moveTo(14.83f, 7.76f);
        pathBuilderP.lineTo(12.0f, 10.59f);
        pathBuilderP.lineTo(9.17f, 7.76f);
        pathBuilderP.lineTo(7.76f, 9.17f);
        pathBuilderP.lineTo(10.59f, 12.0f);
        pathBuilderP.lineToRelative(-2.83f, 2.83f);
        pathBuilderP.lineToRelative(1.41f, 1.41f);
        pathBuilderP.lineTo(12.0f, 13.41f);
        pathBuilderP.lineToRelative(2.83f, 2.83f);
        pathBuilderP.lineToRelative(1.41f, -1.41f);
        pathBuilderP.lineTo(13.41f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderP, 2.83f, -2.83f, 14.83f, 7.76f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _dangerous = imageVectorBuild;
        return imageVectorBuild;
    }
}
