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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_syncAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SyncAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getSyncAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SyncAltKt {
    private static ImageVector _syncAlt;

    public static final ImageVector getSyncAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _syncAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SyncAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.65f, 7.65f, -2.79f, -2.79f);
        pathBuilderR.curveTo(18.54f, 4.54f, 18.0f, 4.76f, 18.0f, 5.21f);
        pathBuilderR.verticalLineTo(7.0f);
        pathBuilderR.horizontalLineTo(4.0f);
        pathBuilderR.curveTo(3.45f, 7.0f, 3.0f, 7.45f, 3.0f, 8.0f);
        pathBuilderR.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(14.0f);
        pathBuilderR.verticalLineToRelative(1.79f);
        pathBuilderR.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderR.lineToRelative(2.79f, -2.79f);
        pathBuilderR.curveTo(21.84f, 8.16f, 21.84f, 7.84f, 21.65f, 7.65f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(20.0f, 15.0f, 6.0f, -1.79f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderA.lineToRelative(-2.79f, 2.79f);
        pathBuilderA.curveToRelative(-0.2f, 0.19f, -0.2f, 0.51f, -0.01f, 0.7f);
        pathBuilderA.lineToRelative(2.79f, 2.79f);
        pathBuilderA.curveTo(5.46f, 19.46f, 6.0f, 19.24f, 6.0f, 18.79f);
        pathBuilderA.verticalLineTo(17.0f);
        pathBuilderA.horizontalLineToRelative(14.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 20.55f, 15.0f, 20.0f, 15.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _syncAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
