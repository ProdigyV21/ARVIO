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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_labelImportant", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LabelImportant", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLabelImportant$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getLabelImportant", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LabelImportantKt {
    private static ImageVector _labelImportant;

    public static final ImageVector getLabelImportant(Icons.TwoTone twoTone) {
        ImageVector imageVector = _labelImportant;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LabelImportant", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = b.b(15.0f, 7.0f, 7.89f, 3.57f, 5.0f);
        pathBuilderB.lineToRelative(-3.57f, 5.0f);
        pathBuilderB.horizontalLineTo(15.0f);
        pathBuilderB.lineToRelative(3.55f, -5.0f);
        pathBuilderB.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.63f, 5.84f);
        pathBuilderA.curveTo(16.27f, 5.33f, 15.67f, 5.0f, 15.0f, 5.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.lineToRelative(5.0f, 7.0f);
        pathBuilderA.lineToRelative(-5.0f, 6.99f);
        pathBuilderA.horizontalLineToRelative(11.0f);
        pathBuilderA.curveToRelative(0.67f, 0.0f, 1.27f, -0.32f, 1.63f, -0.83f);
        d.l(pathBuilderA, 21.0f, 12.0f, -4.37f, -6.16f);
        pathBuilderA.moveTo(15.0f, 17.0f);
        pathBuilderA.horizontalLineTo(7.89f);
        pathBuilderA.lineToRelative(3.57f, -5.0f);
        pathBuilderA.lineToRelative(-3.57f, -5.0f);
        pathBuilderA.horizontalLineTo(15.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 3.55f, 5.0f, 15.0f, 17.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _labelImportant = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getLabelImportant$annotations(Icons.TwoTone twoTone) {
    }
}
