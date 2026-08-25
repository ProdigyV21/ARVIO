package androidx.compose.material.icons.outlined;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_category", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Category", "Landroidx/compose/material/icons/Icons$Outlined;", "getCategory", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CategoryKt {
    private static ImageVector _category;

    public static final ImageVector getCategory(Icons.Outlined outlined) {
        ImageVector imageVector = _category;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Category", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
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
        b.w(pathBuilderC, 3.0f, 21.5f, 8.0f, -8.0f);
        d.f(pathBuilderC, 3.0f, 13.5f, 8.0f);
        a.C(pathBuilderC, 5.0f, 15.5f, 4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.d(pathBuilderC, 5.0f, 19.5f, -4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _category = imageVectorBuild;
        return imageVectorBuild;
    }
}
