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
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderShared", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderShared", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFolderShared", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderSharedKt {
    private static ImageVector _folderShared;

    public static final ImageVector getFolderShared(Icons.TwoTone twoTone) {
        ImageVector imageVector = _folderShared;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FolderShared", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.17f, 8.0f, -0.59f, -0.59f);
        pathBuilderR.lineTo(9.17f, 6.0f);
        a.h(pathBuilderR, 4.0f, 6.0f, 12.0f, 16.0f);
        f.n(pathBuilderR, 20.0f, 8.0f, -8.83f);
        c.m(pathBuilderR, 19.0f, 16.0f, 1.0f, -8.0f);
        pathBuilderR.verticalLineToRelative(-1.0f);
        pathBuilderR.curveToRelative(0.0f, -1.33f, 2.67f, -2.0f, 4.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(4.0f, 0.67f, 4.0f, 2.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.0f, 9.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, 0.9f, -2.0f, 2.0f, -2.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -2.0f, -2.0f);
        pathBuilderG.lineTo(4.0f, 4.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderG.lineTo(2.0f, 18.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(22.0f, 8.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderG, 20.0f, 18.0f, 4.0f, 18.0f);
        pathBuilderG.lineTo(4.0f, 6.0f);
        pathBuilderG.horizontalLineToRelative(5.17f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        d.f(pathBuilderG, 20.0f, 8.0f, 10.0f);
        pathBuilderG.moveTo(15.0f, 13.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        c.y(pathBuilderG, 11.0f, 16.0f, 1.0f, 8.0f);
        pathBuilderG.verticalLineToRelative(-1.0f);
        pathBuilderG.curveToRelative(0.0f, -1.33f, -2.67f, -2.0f, -4.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderG, -4.0f, 0.67f, -4.0f, 2.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _folderShared = imageVectorBuild;
        return imageVectorBuild;
    }
}
