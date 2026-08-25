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
import v.d;
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_simCardDownload", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SimCardDownload", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSimCardDownload", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SimCardDownloadKt {
    private static ImageVector _simCardDownload;

    public static final ImageVector getSimCardDownload(Icons.TwoTone twoTone) {
        ImageVector imageVector = _simCardDownload;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SimCardDownload", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderM = a.m(6.0f, 8.83f, 20.0f, 12.0f, 4.0f);
        d.q(pathBuilderM, -7.17f, 6.0f, 8.83f);
        pathBuilderM.moveTo(11.0f, 9.02f);
        v.a.h(pathBuilderM, 13.0f, 9.0f, 4.0f, 3.0f);
        pathBuilderM.lineToRelative(-4.0f, 4.0f);
        pathBuilderM.lineToRelative(-4.0f, -4.0f);
        pathBuilderM.horizontalLineToRelative(3.0f);
        pathBuilderM.verticalLineTo(9.02f);
        pathBuilderM.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderM.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(18.0f, 2.0f, -8.0f, 4.0f, 8.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.verticalLineTo(4.0f);
        pathBuilderI.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        f.o(pathBuilderI, 18.0f, 20.0f, 6.0f, 8.83f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.e(pathBuilderI, 10.83f, 4.0f, 18.0f, 20.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(12.0f, 17.0f, 4.0f, -4.0f);
        pathBuilderR.lineToRelative(-3.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -4.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.02f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.0f, 3.98f, -3.0f, 0.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _simCardDownload = imageVectorBuild;
        return imageVectorBuild;
    }
}
