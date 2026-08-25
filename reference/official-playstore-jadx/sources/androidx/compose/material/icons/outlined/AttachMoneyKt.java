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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attachMoney", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AttachMoney", "Landroidx/compose/material/icons/Icons$Outlined;", "getAttachMoney", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttachMoneyKt {
    private static ImageVector _attachMoney;

    public static final ImageVector getAttachMoney(Icons.Outlined outlined) {
        ImageVector imageVector = _attachMoney;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AttachMoney", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.8f, 10.9f);
        pathBuilderA.curveToRelative(-2.27f, -0.59f, -3.0f, -1.2f, -3.0f, -2.15f);
        pathBuilderA.curveToRelative(0.0f, -1.09f, 1.01f, -1.85f, 2.7f, -1.85f);
        pathBuilderA.curveToRelative(1.78f, 0.0f, 2.44f, 0.85f, 2.5f, 2.1f);
        pathBuilderA.horizontalLineToRelative(2.21f);
        pathBuilderA.curveToRelative(-0.07f, -1.72f, -1.12f, -3.3f, -3.21f, -3.81f);
        a.j(pathBuilderA, 3.0f, -3.0f, 2.16f);
        pathBuilderA.curveToRelative(-1.94f, 0.42f, -3.5f, 1.68f, -3.5f, 3.61f);
        pathBuilderA.curveToRelative(0.0f, 2.31f, 1.91f, 3.46f, 4.7f, 4.13f);
        pathBuilderA.curveToRelative(2.5f, 0.6f, 3.0f, 1.48f, 3.0f, 2.41f);
        pathBuilderA.curveToRelative(0.0f, 0.69f, -0.49f, 1.79f, -2.7f, 1.79f);
        pathBuilderA.curveToRelative(-2.06f, 0.0f, -2.87f, -0.92f, -2.98f, -2.1f);
        pathBuilderA.horizontalLineToRelative(-2.2f);
        pathBuilderA.curveToRelative(0.12f, 2.19f, 1.76f, 3.42f, 3.68f, 3.83f);
        a.j(pathBuilderA, 21.0f, 3.0f, -2.15f);
        pathBuilderA.curveToRelative(1.95f, -0.37f, 3.5f, -1.5f, 3.5f, -3.55f);
        pathBuilderA.curveToRelative(0.0f, -2.84f, -2.43f, -3.81f, -4.7f, -4.4f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attachMoney = imageVectorBuild;
        return imageVectorBuild;
    }
}
