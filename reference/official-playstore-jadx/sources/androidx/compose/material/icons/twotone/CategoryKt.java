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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_category", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Category", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCategory", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CategoryKt {
    private static ImageVector _category;

    public static final ImageVector getCategory(Icons.TwoTone twoTone) {
        ImageVector imageVector = _category;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Category", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(17.5f, 17.5f, -2.5f, 0.0f);
        pathBuilderB.arcToRelative(2.5f, 2.5f, 0.0f, true, true, 5.0f, 0.0f);
        pathBuilderB.arcToRelative(2.5f, 2.5f, 0.0f, true, true, -5.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = d.a(5.0f, 15.5f, 4.0f, 4.0f);
        c.z(pathBuilderA, 5.0f, 19.5f, 12.0f, 5.84f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderA, 10.07f, 9.0f, 3.86f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = e.c(12.0f, 2.0f, -5.5f, 9.0f, 11.0f);
        c.z(pathBuilderC, 12.0f, 2.0f, 12.0f, 5.84f);
        pathBuilderC.lineTo(13.93f, 9.0f);
        d.q(pathBuilderC, -3.87f, 12.0f, 5.84f);
        pathBuilderC.moveTo(17.5f, 13.0f);
        pathBuilderC.curveToRelative(-2.49f, 0.0f, -4.5f, 2.01f, -4.5f, 4.5f);
        pathBuilderC.reflectiveCurveToRelative(2.01f, 4.5f, 4.5f, 4.5f);
        pathBuilderC.reflectiveCurveToRelative(4.5f, -2.01f, 4.5f, -4.5f);
        pathBuilderC.reflectiveCurveToRelative(-2.01f, -4.5f, -4.5f, -4.5f);
        pathBuilderC.close();
        pathBuilderC.moveTo(17.5f, 20.0f);
        pathBuilderC.curveToRelative(-1.38f, 0.0f, -2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderC.reflectiveCurveToRelative(1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderC.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderC.reflectiveCurveToRelative(-1.12f, 2.5f, -2.5f, 2.5f);
        a.p(pathBuilderC, 11.0f, 13.5f, 3.0f, 13.5f);
        b.l(pathBuilderC, 8.0f, 8.0f, -8.0f);
        pathBuilderC.moveTo(9.0f, 19.5f);
        a.h(pathBuilderC, 5.0f, 19.5f, -4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderC, 4.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _category = imageVectorBuild;
        return imageVectorBuild;
    }
}
