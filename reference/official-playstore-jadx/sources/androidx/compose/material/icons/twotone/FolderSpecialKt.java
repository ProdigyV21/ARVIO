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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderSpecial", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderSpecial", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFolderSpecial", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderSpecialKt {
    private static ImageVector _folderSpecial;

    public static final ImageVector getFolderSpecial(Icons.TwoTone twoTone) {
        ImageVector imageVector = _folderSpecial;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FolderSpecial", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(11.17f, 8.0f, -2.0f, -2.0f, 4.0f);
        pathBuilderB.verticalLineToRelative(12.0f);
        pathBuilderB.horizontalLineToRelative(16.0f);
        pathBuilderB.verticalLineTo(8.0f);
        pathBuilderB.horizontalLineToRelative(-8.83f);
        pathBuilderB.close();
        pathBuilderB.moveTo(15.0f, 9.0f);
        pathBuilderB.lineToRelative(1.19f, 2.79f);
        pathBuilderB.lineToRelative(3.03f, 0.26f);
        pathBuilderB.lineToRelative(-2.3f, 1.99f);
        pathBuilderB.lineToRelative(0.69f, 2.96f);
        pathBuilderB.lineTo(15.0f, 15.47f);
        pathBuilderB.lineTo(12.39f, 17.0f);
        pathBuilderB.lineToRelative(0.69f, -2.96f);
        pathBuilderB.lineToRelative(-2.3f, -1.99f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderB, 3.03f, -0.26f, 15.0f, 9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -2.0f, -2.0f);
        pathBuilderG.lineTo(4.0f, 4.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(22.0f, 8.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        v.a.p(pathBuilderG, 20.0f, 18.0f, 4.0f, 18.0f);
        pathBuilderG.lineTo(4.0f, 6.0f);
        pathBuilderG.horizontalLineToRelative(5.17f);
        pathBuilderG.lineToRelative(2.0f, 2.0f);
        d.f(pathBuilderG, 20.0f, 8.0f, 10.0f);
        pathBuilderG.moveTo(13.08f, 14.04f);
        pathBuilderG.lineTo(12.39f, 17.0f);
        pathBuilderG.lineTo(15.0f, 15.47f);
        pathBuilderG.lineTo(17.61f, 17.0f);
        pathBuilderG.lineToRelative(-0.69f, -2.96f);
        pathBuilderG.lineToRelative(2.3f, -1.99f);
        pathBuilderG.lineToRelative(-3.03f, -0.26f);
        pathBuilderG.lineTo(15.0f, 9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderG, -1.19f, 2.79f, -3.03f, 0.26f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _folderSpecial = imageVectorBuild;
        return imageVectorBuild;
    }
}
