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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPolice", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPolice", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalPolice", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPoliceKt {
    private static ImageVector _localPolice;

    public static final ImageVector getLocalPolice(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localPolice;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalPolice", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(12.0f, 3.19f, 5.0f, 6.3f, 11.0f);
        pathBuilderN.curveToRelative(0.0f, 4.52f, 2.98f, 8.69f, 7.0f, 9.93f);
        pathBuilderN.curveToRelative(4.02f, -1.23f, 7.0f, -5.41f, 7.0f, -9.93f);
        c.x(pathBuilderN, 6.3f, 12.0f, 3.19f);
        pathBuilderN.moveTo(14.5f, 12.59f);
        pathBuilderN.lineToRelative(0.9f, 3.88f);
        pathBuilderN.lineTo(12.0f, 14.42f);
        pathBuilderN.lineToRelative(-3.4f, 2.05f);
        pathBuilderN.lineToRelative(0.9f, -3.87f);
        pathBuilderN.lineToRelative(-3.0f, -2.59f);
        pathBuilderN.lineToRelative(3.96f, -0.34f);
        pathBuilderN.lineTo(12.0f, 6.02f);
        pathBuilderN.lineToRelative(1.54f, 3.64f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderN, 17.5f, 10.0f, 14.5f, 12.59f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(14.5f, 12.59f, 0.9f, 3.88f);
        pathBuilderR.lineTo(12.0f, 14.42f);
        pathBuilderR.lineToRelative(-3.4f, 2.05f);
        pathBuilderR.lineToRelative(0.9f, -3.87f);
        pathBuilderR.lineToRelative(-3.0f, -2.59f);
        pathBuilderR.lineToRelative(3.96f, -0.34f);
        pathBuilderR.lineTo(12.0f, 6.02f);
        pathBuilderR.lineToRelative(1.54f, 3.64f);
        b.y(pathBuilderR, 17.5f, 10.0f, 14.5f, 12.59f);
        pathBuilderR.moveTo(12.0f, 3.19f);
        pathBuilderR.lineToRelative(7.0f, 3.11f);
        pathBuilderR.verticalLineTo(11.0f);
        pathBuilderR.curveToRelative(0.0f, 4.52f, -2.98f, 8.69f, -7.0f, 9.93f);
        pathBuilderR.curveTo(7.98f, 19.69f, 5.0f, 15.52f, 5.0f, 11.0f);
        pathBuilderR.verticalLineTo(6.3f);
        pathBuilderR.lineTo(12.0f, 3.19f);
        pathBuilderR.moveTo(12.0f, 1.0f);
        pathBuilderR.lineTo(3.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(6.0f);
        pathBuilderR.curveToRelative(0.0f, 5.55f, 3.84f, 10.74f, 9.0f, 12.0f);
        pathBuilderR.curveToRelative(5.16f, -1.26f, 9.0f, -6.45f, 9.0f, -12.0f);
        pathBuilderR.verticalLineTo(5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.f(pathBuilderR, 12.0f, 1.0f, 12.0f, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localPolice = imageVectorBuild;
        return imageVectorBuild;
    }
}
