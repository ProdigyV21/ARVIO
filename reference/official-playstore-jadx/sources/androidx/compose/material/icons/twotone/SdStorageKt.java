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
import v.d;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sdStorage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SdStorage", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSdStorage", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SdStorageKt {
    private static ImageVector _sdStorage;

    public static final ImageVector getSdStorage(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sdStorage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SdStorage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(6.0f, 8.83f, 6.0f, 20.0f, 12.0f);
        pathBuilderT.lineTo(18.0f, 4.0f);
        d.q(pathBuilderT, -7.17f, 6.0f, 8.83f);
        v.a.C(pathBuilderT, 15.0f, 7.0f, 2.0f, 4.0f);
        d.q(pathBuilderT, -2.0f, 15.0f, 7.0f);
        v.a.C(pathBuilderT, 12.0f, 7.0f, 2.0f, 4.0f);
        d.q(pathBuilderT, -2.0f, 12.0f, 7.0f);
        pathBuilderT.moveTo(11.0f, 11.0f);
        pathBuilderT.lineTo(9.0f, 11.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderT, 9.0f, 7.0f, 2.0f, 4.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(18.0f, 2.0f, -8.0f, 4.0f, 8.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(20.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        v.a.p(pathBuilderI, 18.0f, 20.0f, 6.0f, 20.0f);
        pathBuilderI.lineTo(6.0f, 8.83f);
        pathBuilderI.lineTo(10.83f, 4.0f);
        d.f(pathBuilderI, 18.0f, 4.0f, 16.0f);
        v.a.C(pathBuilderI, 9.0f, 7.0f, 2.0f, 4.0f);
        c.z(pathBuilderI, 9.0f, 11.0f, 12.0f, 7.0f);
        d.x(pathBuilderI, 2.0f, 4.0f, -2.0f);
        v.a.C(pathBuilderI, 15.0f, 7.0f, 2.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.d(pathBuilderI, -2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sdStorage = imageVectorBuild;
        return imageVectorBuild;
    }
}
