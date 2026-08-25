package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderDelete", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderDelete", "Landroidx/compose/material/icons/Icons$Outlined;", "getFolderDelete", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderDeleteKt {
    private static ImageVector _folderDelete;

    public static final ImageVector getFolderDelete(Icons.Outlined outlined) {
        ImageVector imageVector = _folderDelete;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FolderDelete", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(16.5f, 10.0f, 9.0f, -2.0f, 1.0f);
        b.q(pathBuilderA, 12.0f, 1.5f, 1.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        a0.b.A(pathBuilderA, -4.0f, 1.0f, 10.0f, 16.5f);
        v.a.C(pathBuilderA, 16.5f, 15.5f, -2.0f, -4.0f);
        c.D(pathBuilderA, 2.0f, 15.5f, 20.0f, 6.0f);
        a.n(pathBuilderA, -8.0f, -2.0f, -2.0f, 4.0f);
        pathBuilderA.curveTo(2.89f, 4.0f, 2.01f, 4.89f, 2.01f, 6.0f);
        pathBuilderA.lineTo(2.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(16.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveTo(22.0f, 6.89f, 21.11f, 6.0f, 20.0f, 6.0f);
        f.o(pathBuilderA, 20.0f, 18.0f, 4.0f, 6.0f);
        a.n(pathBuilderA, 5.17f, 2.0f, 2.0f, 20.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _folderDelete = imageVectorBuild;
        return imageVectorBuild;
    }
}
