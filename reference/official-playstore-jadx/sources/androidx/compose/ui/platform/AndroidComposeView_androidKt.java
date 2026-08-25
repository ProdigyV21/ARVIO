package androidx.compose.ui.platform;

import android.content.res.Configuration;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.text.input.PlatformTextInputService;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\n\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a.\u0010\u0011\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a2\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\".\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u00198\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \"\u0018\u0010$\u001a\u00020\u0002*\u00020!8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirectionFromInt", "(I)Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/graphics/Matrix;", "other", "Lx6/t0;", "preTransform-JiSxe2E", "([F[F)V", "preTransform", "", "x", "y", "tmpMatrix", "preTranslate-cG2Xzmc", "([FFF[F)V", "preTranslate", "m1", "row", "m2", "column", "dot-p89u6pk", "([FI[FI)F", TtmlNode.TEXT_EMPHASIS_MARK_DOT, "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/PlatformTextInputService;", "platformTextInputServiceInterceptor", "Lr7/l;", "getPlatformTextInputServiceInterceptor", "()Lr7/l;", "setPlatformTextInputServiceInterceptor", "(Lr7/l;)V", "Landroid/content/res/Configuration;", "getLocaleLayoutDirection", "(Landroid/content/res/Configuration;)Landroidx/compose/ui/unit/LayoutDirection;", "localeLayoutDirection", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeView_androidKt {
    private static r7.l<? super PlatformTextInputService, ? extends PlatformTextInputService> platformTextInputServiceInterceptor = AndroidComposeView_androidKt$platformTextInputServiceInterceptor$1.INSTANCE;

    /* JADX INFO: renamed from: dot-p89u6pk, reason: not valid java name */
    private static final float m4938dotp89u6pk(float[] fArr, int i10, float[] fArr2, int i11) {
        int i12 = i10 * 4;
        return (fArr[i12 + 3] * fArr2[12 + i11]) + (fArr[i12 + 2] * fArr2[8 + i11]) + (fArr[i12 + 1] * fArr2[4 + i11]) + (fArr[i12] * fArr2[i11]);
    }

    public static final LayoutDirection getLocaleLayoutDirection(Configuration configuration) {
        return layoutDirectionFromInt(configuration.getLayoutDirection());
    }

    public static final r7.l<PlatformTextInputService, PlatformTextInputService> getPlatformTextInputServiceInterceptor() {
        return platformTextInputServiceInterceptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutDirection layoutDirectionFromInt(int i10) {
        return i10 != 0 ? i10 != 1 ? LayoutDirection.Ltr : LayoutDirection.Rtl : LayoutDirection.Ltr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: preTransform-JiSxe2E, reason: not valid java name */
    public static final void m4939preTransformJiSxe2E(float[] fArr, float[] fArr2) {
        float fM4938dotp89u6pk = m4938dotp89u6pk(fArr2, 0, fArr, 0);
        float fM4938dotp89u6pk2 = m4938dotp89u6pk(fArr2, 0, fArr, 1);
        float fM4938dotp89u6pk3 = m4938dotp89u6pk(fArr2, 0, fArr, 2);
        float fM4938dotp89u6pk4 = m4938dotp89u6pk(fArr2, 0, fArr, 3);
        float fM4938dotp89u6pk5 = m4938dotp89u6pk(fArr2, 1, fArr, 0);
        float fM4938dotp89u6pk6 = m4938dotp89u6pk(fArr2, 1, fArr, 1);
        float fM4938dotp89u6pk7 = m4938dotp89u6pk(fArr2, 1, fArr, 2);
        float fM4938dotp89u6pk8 = m4938dotp89u6pk(fArr2, 1, fArr, 3);
        float fM4938dotp89u6pk9 = m4938dotp89u6pk(fArr2, 2, fArr, 0);
        float fM4938dotp89u6pk10 = m4938dotp89u6pk(fArr2, 2, fArr, 1);
        float fM4938dotp89u6pk11 = m4938dotp89u6pk(fArr2, 2, fArr, 2);
        float fM4938dotp89u6pk12 = m4938dotp89u6pk(fArr2, 2, fArr, 3);
        float fM4938dotp89u6pk13 = m4938dotp89u6pk(fArr2, 3, fArr, 0);
        float fM4938dotp89u6pk14 = m4938dotp89u6pk(fArr2, 3, fArr, 1);
        float fM4938dotp89u6pk15 = m4938dotp89u6pk(fArr2, 3, fArr, 2);
        float fM4938dotp89u6pk16 = m4938dotp89u6pk(fArr2, 3, fArr, 3);
        fArr[0] = fM4938dotp89u6pk;
        fArr[1] = fM4938dotp89u6pk2;
        fArr[2] = fM4938dotp89u6pk3;
        fArr[3] = fM4938dotp89u6pk4;
        fArr[4] = fM4938dotp89u6pk5;
        fArr[5] = fM4938dotp89u6pk6;
        fArr[6] = fM4938dotp89u6pk7;
        fArr[7] = fM4938dotp89u6pk8;
        fArr[8] = fM4938dotp89u6pk9;
        fArr[9] = fM4938dotp89u6pk10;
        fArr[10] = fM4938dotp89u6pk11;
        fArr[11] = fM4938dotp89u6pk12;
        fArr[12] = fM4938dotp89u6pk13;
        fArr[13] = fM4938dotp89u6pk14;
        fArr[14] = fM4938dotp89u6pk15;
        fArr[15] = fM4938dotp89u6pk16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: preTranslate-cG2Xzmc, reason: not valid java name */
    public static final void m4940preTranslatecG2Xzmc(float[] fArr, float f10, float f11, float[] fArr2) {
        Matrix.m3693resetimpl(fArr2);
        Matrix.m3704translateimpl$default(fArr2, f10, f11, 0.0f, 4, null);
        m4939preTransformJiSxe2E(fArr, fArr2);
    }

    public static final void setPlatformTextInputServiceInterceptor(r7.l<? super PlatformTextInputService, ? extends PlatformTextInputService> lVar) {
        platformTextInputServiceInterceptor = lVar;
    }
}
