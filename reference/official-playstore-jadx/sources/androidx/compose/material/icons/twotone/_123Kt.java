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
import v.a;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__123", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_123", "Landroidx/compose/material/icons/Icons$TwoTone;", "get_123", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _123Kt {
    private static ImageVector __123;

    public static final ImageVector get_123(Icons.TwoTone twoTone) {
        ImageVector imageVector = __123;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone._123", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = b.b(7.0f, 15.0f, 5.5f, -4.5f, 4.0f);
        f.k(pathBuilderB, 9.0f, 3.0f, 15.0f);
        a.C(pathBuilderB, 13.5f, 13.5f, -3.0f, -1.0f);
        pathBuilderB.horizontalLineToRelative(2.0f);
        pathBuilderB.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderB.verticalLineTo(10.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        v.b.q(pathBuilderB, 9.0f, 1.5f, 3.0f, 1.0f);
        pathBuilderB.horizontalLineToRelative(-2.0f);
        pathBuilderB.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        f.k(pathBuilderB, 15.0f, 4.5f, 13.5f);
        pathBuilderB.moveTo(19.5f, 14.0f);
        pathBuilderB.verticalLineToRelative(-4.0f);
        pathBuilderB.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        v.b.q(pathBuilderB, 15.0f, 1.5f, 3.0f, 1.0f);
        a.o(pathBuilderB, -2.0f, 1.0f, 2.0f, 1.0f);
        a0.a.d(pathBuilderB, -3.0f, 15.0f, 3.5f);
        pathBuilderB.curveTo(19.05f, 15.0f, 19.5f, 14.55f, 19.5f, 14.0f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __123 = imageVectorBuild;
        return imageVectorBuild;
    }
}
