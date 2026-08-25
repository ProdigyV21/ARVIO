package androidx.compose.material.icons.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_savedSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SavedSearch", "Landroidx/compose/material/icons/Icons$Sharp;", "getSavedSearch", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SavedSearchKt {
    private static ImageVector _savedSearch;

    public static final ImageVector getSavedSearch(Icons.Sharp sharp) {
        ImageVector imageVector = _savedSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SavedSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.73f, 13.31f);
        pathBuilderA.curveTo(15.52f, 12.24f, 16.0f, 10.93f, 16.0f, 9.5f);
        pathBuilderA.curveTo(16.0f, 5.91f, 13.09f, 3.0f, 9.5f, 3.0f);
        pathBuilderA.reflectiveCurveTo(3.0f, 5.91f, 3.0f, 9.5f);
        pathBuilderA.curveTo(3.0f, 13.09f, 5.91f, 16.0f, 9.5f, 16.0f);
        pathBuilderA.curveToRelative(1.43f, 0.0f, 2.74f, -0.48f, 3.81f, -1.27f);
        pathBuilderA.lineTo(19.59f, 21.0f);
        b.y(pathBuilderA, 21.0f, 19.59f, 14.73f, 13.31f);
        pathBuilderA.moveTo(9.5f, 14.0f);
        pathBuilderA.curveTo(7.01f, 14.0f, 5.0f, 11.99f, 5.0f, 9.5f);
        pathBuilderA.reflectiveCurveTo(7.01f, 5.0f, 9.5f, 5.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 7.01f, 14.0f, 9.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 11.99f, 14.0f, 9.5f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.29f, 8.44f, -0.79f, -2.44f);
        pathBuilderR.lineToRelative(-0.79f, 2.44f);
        pathBuilderR.lineToRelative(-2.46f, 0.0f);
        pathBuilderR.lineToRelative(2.01f, 1.59f);
        pathBuilderR.lineToRelative(-0.77f, 2.47f);
        pathBuilderR.lineToRelative(2.01f, -1.53f);
        pathBuilderR.lineToRelative(2.01f, 1.53f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -0.77f, -2.47f, 2.01f, -1.59f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _savedSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
