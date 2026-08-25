package androidx.compose.ui.text.font;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÖ\u0001J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/text/font/AndroidFontResolveInterceptor;", "Landroidx/compose/ui/text/font/PlatformResolveInterceptor;", "fontWeightAdjustment", "", "(I)V", "component1", "copy", "equals", "", "other", "", "hashCode", "interceptFontWeight", "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "toString", "", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class AndroidFontResolveInterceptor implements PlatformResolveInterceptor {
    public static final int $stable = 0;
    private final int fontWeightAdjustment;

    public AndroidFontResolveInterceptor(int i10) {
        this.fontWeightAdjustment = i10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final int getFontWeightAdjustment() {
        return this.fontWeightAdjustment;
    }

    public static /* synthetic */ AndroidFontResolveInterceptor copy$default(AndroidFontResolveInterceptor androidFontResolveInterceptor, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = androidFontResolveInterceptor.fontWeightAdjustment;
        }
        return androidFontResolveInterceptor.copy(i10);
    }

    public final AndroidFontResolveInterceptor copy(int fontWeightAdjustment) {
        return new AndroidFontResolveInterceptor(fontWeightAdjustment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AndroidFontResolveInterceptor) && this.fontWeightAdjustment == ((AndroidFontResolveInterceptor) other).fontWeightAdjustment;
    }

    public int hashCode() {
        return this.fontWeightAdjustment;
    }

    @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
    public final /* synthetic */ FontFamily interceptFontFamily(FontFamily fontFamily) {
        return c.a(this, fontFamily);
    }

    @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
    /* JADX INFO: renamed from: interceptFontStyle-T2F_aPo, reason: not valid java name */
    public final /* synthetic */ int mo5255interceptFontStyleT2F_aPo(int i10) {
        return c.b(this, i10);
    }

    @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
    /* JADX INFO: renamed from: interceptFontSynthesis-Mscr08Y, reason: not valid java name */
    public final /* synthetic */ int mo5256interceptFontSynthesisMscr08Y(int i10) {
        return c.c(this, i10);
    }

    @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
    public FontWeight interceptFontWeight(FontWeight fontWeight) {
        int i10 = this.fontWeightAdjustment;
        return (i10 == 0 || i10 == Integer.MAX_VALUE) ? fontWeight : new FontWeight(d.n(fontWeight.getWeight() + this.fontWeightAdjustment, 1, 1000));
    }

    public String toString() {
        return a0.c.o(new StringBuilder("AndroidFontResolveInterceptor(fontWeightAdjustment="), this.fontWeightAdjustment, ')');
    }
}
