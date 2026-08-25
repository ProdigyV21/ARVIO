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
import v.b;
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forest", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Forest", "Landroidx/compose/material/icons/Icons$Outlined;", "getForest", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForestKt {
    private static ImageVector _forest;

    public static final ImageVector getForest(Icons.Outlined outlined) {
        ImageVector imageVector = _forest;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Forest", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(24.0f, 18.0f, -3.86f, -6.0f, 22.0f);
        pathBuilderB.lineTo(15.0f, 2.0f);
        pathBuilderB.lineToRelative(-3.0f, 4.29f);
        pathBuilderB.lineTo(9.0f, 2.0f);
        pathBuilderB.lineTo(2.0f, 12.0f);
        c.A(pathBuilderB, 1.86f, 0.0f, 18.0f, 7.0f);
        b.z(pathBuilderB, 4.0f, 4.0f, -4.0f, 2.0f);
        a0.b.f(pathBuilderB, 4.0f, 4.0f, -4.0f, 24.0f);
        pathBuilderB.moveTo(15.0f, 5.49f);
        pathBuilderB.lineTo(18.16f, 10.0f);
        e.C(pathBuilderB, -1.68f, 3.86f, 6.0f, -3.62f);
        pathBuilderB.lineToRelative(-2.57f, -4.0f);
        pathBuilderB.horizontalLineTo(16.0f);
        d.C(pathBuilderB, -2.78f, -3.97f, 15.0f, 5.49f);
        pathBuilderB.moveTo(3.66f, 16.0f);
        pathBuilderB.lineToRelative(3.86f, -6.0f);
        pathBuilderB.horizontalLineTo(5.84f);
        pathBuilderB.lineTo(9.0f, 5.49f);
        pathBuilderB.lineTo(12.16f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.r(pathBuilderB, -1.68f, 3.86f, 6.0f, 3.66f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _forest = imageVectorBuild;
        return imageVectorBuild;
    }
}
