package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.FontVariation;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001aB\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0001H\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"}, d2 = {"Font", "Landroidx/compose/ui/text/font/Font;", "resId", "", "weight", "Landroidx/compose/ui/text/font/FontWeight;", "style", "Landroidx/compose/ui/text/font/FontStyle;", "Font-RetOiIg", "(ILandroidx/compose/ui/text/font/FontWeight;I)Landroidx/compose/ui/text/font/Font;", "loadingStrategy", "Landroidx/compose/ui/text/font/FontLoadingStrategy;", "Font-YpTlLL0", "(ILandroidx/compose/ui/text/font/FontWeight;II)Landroidx/compose/ui/text/font/Font;", "variationSettings", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "Font-F3nL8kk", "(ILandroidx/compose/ui/text/font/FontWeight;IILandroidx/compose/ui/text/font/FontVariation$Settings;)Landroidx/compose/ui/text/font/Font;", "toFontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontKt {
    /* JADX INFO: renamed from: Font-F3nL8kk, reason: not valid java name */
    public static final Font m5280FontF3nL8kk(int i10, FontWeight fontWeight, int i11, int i12, FontVariation.Settings settings) {
        return new ResourceFont(i10, fontWeight, i11, settings, i12, null);
    }

    /* JADX INFO: renamed from: Font-F3nL8kk$default, reason: not valid java name */
    public static /* synthetic */ Font m5281FontF3nL8kk$default(int i10, FontWeight fontWeight, int i11, int i12, FontVariation.Settings settings, int i13, Object obj) {
        if ((i13 & 2) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i13 & 4) != 0) {
            i11 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        if ((i13 & 8) != 0) {
            i12 = FontLoadingStrategy.INSTANCE.m5294getBlockingPKNRLFQ();
        }
        if ((i13 & 16) != 0) {
            settings = FontVariation.INSTANCE.m5324Settings6EWAqTQ(fontWeight, i11, new FontVariation.Setting[0]);
        }
        return m5280FontF3nL8kk(i10, fontWeight, i11, i12, settings);
    }

    @e
    /* JADX INFO: renamed from: Font-RetOiIg, reason: not valid java name */
    public static final /* synthetic */ Font m5282FontRetOiIg(int i10, FontWeight fontWeight, int i11) {
        return new ResourceFont(i10, fontWeight, i11, null, FontLoadingStrategy.INSTANCE.m5294getBlockingPKNRLFQ(), 8, null);
    }

    /* JADX INFO: renamed from: Font-RetOiIg$default, reason: not valid java name */
    public static /* synthetic */ Font m5283FontRetOiIg$default(int i10, FontWeight fontWeight, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i12 & 4) != 0) {
            i11 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        return m5282FontRetOiIg(i10, fontWeight, i11);
    }

    /* JADX INFO: renamed from: Font-YpTlLL0, reason: not valid java name */
    public static final Font m5284FontYpTlLL0(int i10, FontWeight fontWeight, int i11, int i12) {
        return new ResourceFont(i10, fontWeight, i11, new FontVariation.Settings(new FontVariation.Setting[0]), i12, null);
    }

    /* JADX INFO: renamed from: Font-YpTlLL0$default, reason: not valid java name */
    public static /* synthetic */ Font m5285FontYpTlLL0$default(int i10, FontWeight fontWeight, int i11, int i12, int i13, Object obj) {
        if ((i13 & 2) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i13 & 4) != 0) {
            i11 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        if ((i13 & 8) != 0) {
            i12 = FontLoadingStrategy.INSTANCE.m5294getBlockingPKNRLFQ();
        }
        return m5284FontYpTlLL0(i10, fontWeight, i11, i12);
    }

    public static final FontFamily toFontFamily(Font font) {
        return FontFamilyKt.FontFamily(font);
    }
}
