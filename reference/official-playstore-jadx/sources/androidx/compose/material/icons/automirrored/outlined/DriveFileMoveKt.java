package androidx.compose.material.icons.automirrored.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_driveFileMove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DriveFileMove", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getDriveFileMove", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DriveFileMoveKt {
    private static ImageVector _driveFileMove;

    public static final ImageVector getDriveFileMove(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _driveFileMove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.DriveFileMove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 6.0f, -8.0f, -2.0f, -2.0f);
        pathBuilderG.horizontalLineTo(4.0f);
        pathBuilderG.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveTo(22.0f, 6.9f, 21.1f, 6.0f, 20.0f, 6.0f);
        f.o(pathBuilderG, 20.0f, 18.0f, 4.0f, 6.0f);
        pathBuilderG.horizontalLineToRelative(5.17f);
        pathBuilderG.lineToRelative(1.41f, 1.41f);
        pathBuilderG.lineTo(11.17f, 8.0f);
        pathBuilderG.horizontalLineTo(20.0f);
        pathBuilderG.verticalLineTo(18.0f);
        pathBuilderG.close();
        a.A(pathBuilderG, 12.16f, 12.0f, 8.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(4.16f);
        pathBuilderG.lineToRelative(-1.59f, 1.59f);
        pathBuilderG.lineTo(11.99f, 17.0f);
        pathBuilderG.lineTo(16.0f, 13.01f);
        pathBuilderG.lineTo(11.99f, 9.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderG, -1.41f, 1.41f, 12.16f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _driveFileMove = imageVectorBuild;
        return imageVectorBuild;
    }
}
