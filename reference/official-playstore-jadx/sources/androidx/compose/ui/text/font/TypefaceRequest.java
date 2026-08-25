package androidx.compose.ui.text.font;

import androidx.compose.material3.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0080\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0013\u001a\u00020\u0006HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0015\u001a\u00020\bHÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0012J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017JI\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u001eHÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0012J\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\"\u0010#R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b'\u0010\u0010R\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b)\u0010\u0012R\u001d\u0010\t\u001a\u00020\b8\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\t\u0010(\u001a\u0004\b*\u0010\u0012R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\n\u0010+\u001a\u0004\b,\u0010\u0017\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"Landroidx/compose/ui/text/font/TypefaceRequest;", "", "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_FAMILY, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontSynthesis;", "fontSynthesis", "resourceLoaderCacheKey", "<init>", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;IILjava/lang/Object;Lkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/ui/text/font/FontFamily;", "component2", "()Landroidx/compose/ui/text/font/FontWeight;", "component3-_-LCdwA", "()I", "component3", "component4-GVVA2EU", "component4", "component5", "()Ljava/lang/Object;", "copy-e1PVR60", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;IILjava/lang/Object;)Landroidx/compose/ui/text/font/TypefaceRequest;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/text/font/FontFamily;", "getFontFamily", "Landroidx/compose/ui/text/font/FontWeight;", "getFontWeight", "I", "getFontStyle-_-LCdwA", "getFontSynthesis-GVVA2EU", "Ljava/lang/Object;", "getResourceLoaderCacheKey", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TypefaceRequest {
    public static final int $stable = 8;
    private final FontFamily fontFamily;
    private final int fontStyle;
    private final int fontSynthesis;
    private final FontWeight fontWeight;
    private final Object resourceLoaderCacheKey;

    public /* synthetic */ TypefaceRequest(FontFamily fontFamily, FontWeight fontWeight, int i10, int i11, Object obj, h hVar) {
        this(fontFamily, fontWeight, i10, i11, obj);
    }

    /* JADX INFO: renamed from: copy-e1PVR60$default, reason: not valid java name */
    public static /* synthetic */ TypefaceRequest m5341copye1PVR60$default(TypefaceRequest typefaceRequest, FontFamily fontFamily, FontWeight fontWeight, int i10, int i11, Object obj, int i12, Object obj2) {
        if ((i12 & 1) != 0) {
            fontFamily = typefaceRequest.fontFamily;
        }
        if ((i12 & 2) != 0) {
            fontWeight = typefaceRequest.fontWeight;
        }
        if ((i12 & 4) != 0) {
            i10 = typefaceRequest.fontStyle;
        }
        if ((i12 & 8) != 0) {
            i11 = typefaceRequest.fontSynthesis;
        }
        if ((i12 & 16) != 0) {
            obj = typefaceRequest.resourceLoaderCacheKey;
        }
        Object obj3 = obj;
        int i13 = i10;
        return typefaceRequest.m5344copye1PVR60(fontFamily, fontWeight, i13, i11, obj3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FontFamily getFontFamily() {
        return this.fontFamily;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FontWeight getFontWeight() {
        return this.fontWeight;
    }

    /* JADX INFO: renamed from: component3-_-LCdwA, reason: not valid java name and from getter */
    public final int getFontStyle() {
        return this.fontStyle;
    }

    /* JADX INFO: renamed from: component4-GVVA2EU, reason: not valid java name and from getter */
    public final int getFontSynthesis() {
        return this.fontSynthesis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Object getResourceLoaderCacheKey() {
        return this.resourceLoaderCacheKey;
    }

    /* JADX INFO: renamed from: copy-e1PVR60, reason: not valid java name */
    public final TypefaceRequest m5344copye1PVR60(FontFamily fontFamily, FontWeight fontWeight, int fontStyle, int fontSynthesis, Object resourceLoaderCacheKey) {
        return new TypefaceRequest(fontFamily, fontWeight, fontStyle, fontSynthesis, resourceLoaderCacheKey, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypefaceRequest)) {
            return false;
        }
        TypefaceRequest typefaceRequest = (TypefaceRequest) other;
        return p.a(this.fontFamily, typefaceRequest.fontFamily) && p.a(this.fontWeight, typefaceRequest.fontWeight) && FontStyle.m5302equalsimpl0(this.fontStyle, typefaceRequest.fontStyle) && FontSynthesis.m5313equalsimpl0(this.fontSynthesis, typefaceRequest.fontSynthesis) && p.a(this.resourceLoaderCacheKey, typefaceRequest.resourceLoaderCacheKey);
    }

    public final FontFamily getFontFamily() {
        return this.fontFamily;
    }

    /* JADX INFO: renamed from: getFontStyle-_-LCdwA, reason: not valid java name */
    public final int m5345getFontStyle_LCdwA() {
        return this.fontStyle;
    }

    /* JADX INFO: renamed from: getFontSynthesis-GVVA2EU, reason: not valid java name */
    public final int m5346getFontSynthesisGVVA2EU() {
        return this.fontSynthesis;
    }

    public final FontWeight getFontWeight() {
        return this.fontWeight;
    }

    public final Object getResourceLoaderCacheKey() {
        return this.resourceLoaderCacheKey;
    }

    public int hashCode() {
        FontFamily fontFamily = this.fontFamily;
        int iM5314hashCodeimpl = (FontSynthesis.m5314hashCodeimpl(this.fontSynthesis) + ((FontStyle.m5303hashCodeimpl(this.fontStyle) + ((this.fontWeight.hashCode() + ((fontFamily == null ? 0 : fontFamily.hashCode()) * 31)) * 31)) * 31)) * 31;
        Object obj = this.resourceLoaderCacheKey;
        return iM5314hashCodeimpl + (obj != null ? obj.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TypefaceRequest(fontFamily=");
        sb2.append(this.fontFamily);
        sb2.append(", fontWeight=");
        sb2.append(this.fontWeight);
        sb2.append(", fontStyle=");
        sb2.append((Object) FontStyle.m5304toStringimpl(this.fontStyle));
        sb2.append(", fontSynthesis=");
        sb2.append((Object) FontSynthesis.m5317toStringimpl(this.fontSynthesis));
        sb2.append(", resourceLoaderCacheKey=");
        return d.p(sb2, this.resourceLoaderCacheKey, ')');
    }

    private TypefaceRequest(FontFamily fontFamily, FontWeight fontWeight, int i10, int i11, Object obj) {
        this.fontFamily = fontFamily;
        this.fontWeight = fontWeight;
        this.fontStyle = i10;
        this.fontSynthesis = i11;
        this.resourceLoaderCacheKey = obj;
    }
}
