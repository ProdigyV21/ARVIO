package androidx.compose.material.icons.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_importContacts", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImportContacts", "Landroidx/compose/material/icons/Icons$Rounded;", "getImportContacts", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImportContactsKt {
    private static ImageVector _importContacts;

    public static final ImageVector getImportContacts(Icons.Rounded rounded) {
        ImageVector imageVector = _importContacts;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ImportContacts", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.5f, 4.5f);
        pathBuilderA.curveToRelative(-1.95f, 0.0f, -4.05f, 0.4f, -5.5f, 1.5f);
        pathBuilderA.curveToRelative(-1.45f, -1.1f, -3.55f, -1.5f, -5.5f, -1.5f);
        pathBuilderA.curveToRelative(-1.45f, 0.0f, -2.99f, 0.22f, -4.28f, 0.79f);
        pathBuilderA.curveTo(1.49f, 5.62f, 1.0f, 6.33f, 1.0f, 7.14f);
        pathBuilderA.verticalLineToRelative(11.28f);
        pathBuilderA.curveToRelative(0.0f, 1.3f, 1.22f, 2.26f, 2.48f, 1.94f);
        pathBuilderA.curveToRelative(0.98f, -0.25f, 2.02f, -0.36f, 3.02f, -0.36f);
        pathBuilderA.curveToRelative(1.56f, 0.0f, 3.22f, 0.26f, 4.56f, 0.92f);
        pathBuilderA.curveToRelative(0.6f, 0.3f, 1.28f, 0.3f, 1.87f, 0.0f);
        pathBuilderA.curveToRelative(1.34f, -0.67f, 3.0f, -0.92f, 4.56f, -0.92f);
        pathBuilderA.curveToRelative(1.0f, 0.0f, 2.04f, 0.11f, 3.02f, 0.36f);
        pathBuilderA.curveToRelative(1.26f, 0.33f, 2.48f, -0.63f, 2.48f, -1.94f);
        pathBuilderA.verticalLineTo(7.14f);
        pathBuilderA.curveToRelative(0.0f, -0.81f, -0.49f, -1.52f, -1.22f, -1.85f);
        pathBuilderA.curveToRelative(-1.28f, -0.57f, -2.82f, -0.79f, -4.27f, -0.79f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.0f, 17.23f);
        pathBuilderA.curveToRelative(0.0f, 0.63f, -0.58f, 1.09f, -1.2f, 0.98f);
        pathBuilderA.curveToRelative(-0.75f, -0.14f, -1.53f, -0.2f, -2.3f, -0.2f);
        pathBuilderA.curveToRelative(-1.7f, 0.0f, -4.15f, 0.65f, -5.5f, 1.5f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveToRelative(1.35f, -0.85f, 3.8f, -1.5f, 5.5f, -1.5f);
        pathBuilderA.curveToRelative(0.92f, 0.0f, 1.83f, 0.09f, 2.7f, 0.28f);
        pathBuilderA.curveToRelative(0.46f, 0.1f, 0.8f, 0.51f, 0.8f, 0.98f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderA, 9.47f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _importContacts = imageVectorBuild;
        return imageVectorBuild;
    }
}
