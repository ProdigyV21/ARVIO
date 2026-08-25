package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_driveFileRenameOutline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DriveFileRenameOutline", "Landroidx/compose/material/icons/Icons$Filled;", "getDriveFileRenameOutline", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DriveFileRenameOutlineKt {
    private static ImageVector _driveFileRenameOutline;

    public static final ImageVector getDriveFileRenameOutline(Icons.Filled filled) {
        ImageVector imageVector = _driveFileRenameOutline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DriveFileRenameOutline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.41f, 5.8f, 17.2f, 4.59f);
        pathBuilderJ.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderJ.lineToRelative(-2.68f, 2.68f);
        b.n(pathBuilderJ, 3.0f, 15.96f, 20.0f, 4.04f);
        pathBuilderJ.lineToRelative(8.74f, -8.74f);
        pathBuilderJ.lineToRelative(2.63f, -2.63f);
        pathBuilderJ.curveToRelative(0.79f, -0.78f, 0.79f, -2.05f, 0.0f, -2.83f);
        c.C(pathBuilderJ, 6.21f, 18.0f, 5.0f, -1.21f);
        pathBuilderJ.lineToRelative(8.66f, -8.66f);
        d.C(pathBuilderJ, 1.21f, 1.21f, 6.21f, 18.0f);
        pathBuilderJ.moveTo(11.0f, 20.0f);
        a0.a.m(pathBuilderJ, 4.0f, -4.0f, 6.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderJ, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _driveFileRenameOutline = imageVectorBuild;
        return imageVectorBuild;
    }
}
