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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_creditScore", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CreditScore", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCreditScore", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CreditScoreKt {
    private static ImageVector _creditScore;

    public static final ImageVector getCreditScore(Icons.TwoTone twoTone) {
        ImageVector imageVector = _creditScore;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CreditScore", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.89f, 4.0f, 2.01f, 4.89f, 2.01f, 6.0f);
        pathBuilderQ.lineTo(2.0f, 18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        a0.a.y(pathBuilderQ, 5.0f, -2.0f, 4.0f, -6.0f);
        pathBuilderQ.horizontalLineToRelative(18.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.89f, 21.11f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderQ, 20.0f, 8.0f, 4.0f, 6.0f);
        c.D(pathBuilderQ, 16.0f, 8.0f, 14.93f, 19.17f);
        pathBuilderQ.lineToRelative(-2.83f, -2.83f);
        pathBuilderQ.lineToRelative(-1.41f, 1.41f);
        pathBuilderQ.lineTo(14.93f, 22.0f);
        pathBuilderQ.lineTo(22.0f, 14.93f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ, -1.41f, -1.41f, 14.93f, 19.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _creditScore = imageVectorBuild;
        return imageVectorBuild;
    }
}
