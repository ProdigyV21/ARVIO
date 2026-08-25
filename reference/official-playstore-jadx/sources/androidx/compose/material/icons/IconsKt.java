package androidx.compose.material.icons;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a1\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a;\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0086\b¢\u0006\u0004\b\u0007\u0010\u000b\u001aN\u0010\u0016\u001a\u00020\u0003*\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0002¢\u0006\u0002\b\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\"\u001a\u0010\u0017\u001a\u00020\f8\u0000X\u0081T¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"", ContentDisposition.Parameters.Name, "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "Lx6/n;", "block", "Landroidx/compose/ui/graphics/vector/ImageVector;", "materialIcon", "(Ljava/lang/String;Lr7/l;)Landroidx/compose/ui/graphics/vector/ImageVector;", "", "autoMirror", "(Ljava/lang/String;ZLr7/l;)Landroidx/compose/ui/graphics/vector/ImageVector;", "", "fillAlpha", "strokeAlpha", "Landroidx/compose/ui/graphics/PathFillType;", "pathFillType", "Landroidx/compose/ui/graphics/vector/PathBuilder;", "Lx6/t0;", "pathBuilder", "materialPath-YwgOQQI", "(Landroidx/compose/ui/graphics/vector/ImageVector$Builder;FFILr7/l;)Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "materialPath", "MaterialIconDimension", "F", "getMaterialIconDimension$annotations", "()V", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IconsKt {
    public static final float MaterialIconDimension = 24.0f;

    public static /* synthetic */ void getMaterialIconDimension$annotations() {
    }

    public static final ImageVector materialIcon(String str, boolean z, l<? super ImageVector.Builder, ImageVector.Builder> lVar) {
        return ((ImageVector.Builder) lVar.invoke(new ImageVector.Builder(str, Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, z, 96, null))).build();
    }

    public static /* synthetic */ ImageVector materialIcon$default(String str, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return ((ImageVector.Builder) lVar.invoke(new ImageVector.Builder(str, Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, z, 96, null))).build();
    }

    /* JADX INFO: renamed from: materialPath-YwgOQQI, reason: not valid java name */
    public static final ImageVector.Builder m1196materialPathYwgOQQI(ImageVector.Builder builder, float f10, float f11, int i10, l<? super PathBuilder, t0> lVar) {
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        lVar.invoke(pathBuilder);
        return ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilder.getNodes(), i10, "", solidColor, f10, null, f11, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
    }

    /* JADX INFO: renamed from: materialPath-YwgOQQI$default, reason: not valid java name */
    public static /* synthetic */ ImageVector.Builder m1197materialPathYwgOQQI$default(ImageVector.Builder builder, float f10, float f11, int i10, l lVar, int i11, Object obj) {
        float f12 = (i11 & 1) != 0 ? 1.0f : f10;
        float f13 = (i11 & 2) != 0 ? 1.0f : f11;
        int defaultFillType = (i11 & 4) != 0 ? VectorKt.getDefaultFillType() : i10;
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        lVar.invoke(pathBuilder);
        return ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, f12, null, f13, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
    }
}
