package androidx.compose.material.icons.twotone;

import a0.a;
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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_euroSymbol", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EuroSymbol", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEuroSymbol", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EuroSymbolKt {
    private static ImageVector _euroSymbol;

    public static final ImageVector getEuroSymbol(Icons.TwoTone twoTone) {
        ImageVector imageVector = _euroSymbol;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EuroSymbol", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 18.5f);
        pathBuilderA.curveToRelative(-2.51f, 0.0f, -4.68f, -1.42f, -5.76f, -3.5f);
        a.w(pathBuilderA, 15.0f, -2.0f, 8.58f);
        pathBuilderA.curveToRelative(-0.05f, -0.33f, -0.08f, -0.66f, -0.08f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.03f, -0.67f, 0.08f, -1.0f);
        b.i(pathBuilderA, 15.0f, 9.0f, 9.24f);
        pathBuilderA.curveTo(10.32f, 6.92f, 12.5f, 5.5f, 15.0f, 5.5f);
        pathBuilderA.curveToRelative(1.61f, 0.0f, 3.09f, 0.59f, 4.23f, 1.57f);
        pathBuilderA.lineTo(21.0f, 5.3f);
        pathBuilderA.curveTo(19.41f, 3.87f, 17.3f, 3.0f, 15.0f, 3.0f);
        pathBuilderA.curveToRelative(-3.92f, 0.0f, -7.24f, 2.51f, -8.48f, 6.0f);
        v.a.n(pathBuilderA, 3.0f, 2.0f, 3.06f);
        pathBuilderA.curveToRelative(-0.04f, 0.33f, -0.06f, 0.66f, -0.06f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.02f, 0.67f, 0.06f, 1.0f);
        v.a.n(pathBuilderA, 3.0f, 2.0f, 3.52f);
        pathBuilderA.curveToRelative(1.24f, 3.49f, 4.56f, 6.0f, 8.48f, 6.0f);
        pathBuilderA.curveToRelative(2.31f, 0.0f, 4.41f, -0.87f, 6.0f, -2.3f);
        pathBuilderA.lineToRelative(-1.78f, -1.77f);
        pathBuilderA.curveToRelative(-1.13f, 0.98f, -2.6f, 1.57f, -4.22f, 1.57f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _euroSymbol = imageVectorBuild;
        return imageVectorBuild;
    }
}
