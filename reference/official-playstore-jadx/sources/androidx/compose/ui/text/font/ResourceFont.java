package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.FontVariation;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B9\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0003\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJD\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010!R \u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\"\u0010\u0019R\u0017\u0010\t\u001a\u00020\b8\u0007¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%R&\u0010\u000b\u001a\u00020\n8WX\u0097\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u000b\u0010\u001d\u0012\u0004\b'\u0010(\u001a\u0004\b&\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Landroidx/compose/ui/text/font/ResourceFont;", "Landroidx/compose/ui/text/font/Font;", "", "resId", "Landroidx/compose/ui/text/font/FontWeight;", "weight", "Landroidx/compose/ui/text/font/FontStyle;", "style", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "variationSettings", "Landroidx/compose/ui/text/font/FontLoadingStrategy;", "loadingStrategy", "<init>", "(ILandroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;ILkotlin/jvm/internal/h;)V", "copy-RetOiIg", "(ILandroidx/compose/ui/text/font/FontWeight;I)Landroidx/compose/ui/text/font/ResourceFont;", "copy", "copy-F3nL8kk", "(ILandroidx/compose/ui/text/font/FontWeight;IILandroidx/compose/ui/text/font/FontVariation$Settings;)Landroidx/compose/ui/text/font/ResourceFont;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "I", "getResId", "Landroidx/compose/ui/text/font/FontWeight;", "getWeight", "()Landroidx/compose/ui/text/font/FontWeight;", "getStyle-_-LCdwA", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "getVariationSettings", "()Landroidx/compose/ui/text/font/FontVariation$Settings;", "getLoadingStrategy-PKNRLFQ", "getLoadingStrategy-PKNRLFQ$annotations", "()V", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResourceFont implements Font {
    public static final int $stable = 0;
    private final int loadingStrategy;
    private final int resId;
    private final int style;
    private final FontVariation.Settings variationSettings;
    private final FontWeight weight;

    public /* synthetic */ ResourceFont(int i10, FontWeight fontWeight, int i11, FontVariation.Settings settings, int i12, h hVar) {
        this(i10, fontWeight, i11, settings, i12);
    }

    /* JADX INFO: renamed from: copy-F3nL8kk$default, reason: not valid java name */
    public static /* synthetic */ ResourceFont m5336copyF3nL8kk$default(ResourceFont resourceFont, int i10, FontWeight fontWeight, int i11, int i12, FontVariation.Settings settings, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = resourceFont.resId;
        }
        if ((i13 & 2) != 0) {
            fontWeight = resourceFont.getWeight();
        }
        if ((i13 & 4) != 0) {
            i11 = resourceFont.getStyle();
        }
        if ((i13 & 8) != 0) {
            i12 = resourceFont.getLoadingStrategy();
        }
        if ((i13 & 16) != 0) {
            settings = resourceFont.variationSettings;
        }
        FontVariation.Settings settings2 = settings;
        int i14 = i11;
        return resourceFont.m5339copyF3nL8kk(i10, fontWeight, i14, i12, settings2);
    }

    /* JADX INFO: renamed from: copy-RetOiIg$default, reason: not valid java name */
    public static /* synthetic */ ResourceFont m5337copyRetOiIg$default(ResourceFont resourceFont, int i10, FontWeight fontWeight, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = resourceFont.resId;
        }
        if ((i12 & 2) != 0) {
            fontWeight = resourceFont.getWeight();
        }
        if ((i12 & 4) != 0) {
            i11 = resourceFont.getStyle();
        }
        return resourceFont.m5340copyRetOiIg(i10, fontWeight, i11);
    }

    /* JADX INFO: renamed from: getLoadingStrategy-PKNRLFQ$annotations, reason: not valid java name */
    public static /* synthetic */ void m5338getLoadingStrategyPKNRLFQ$annotations() {
    }

    /* JADX INFO: renamed from: copy-F3nL8kk, reason: not valid java name */
    public final ResourceFont m5339copyF3nL8kk(int resId, FontWeight weight, int style, int loadingStrategy, FontVariation.Settings variationSettings) {
        return new ResourceFont(resId, weight, style, variationSettings, loadingStrategy, null);
    }

    /* JADX INFO: renamed from: copy-RetOiIg, reason: not valid java name */
    public final ResourceFont m5340copyRetOiIg(int resId, FontWeight weight, int style) {
        return m5336copyF3nL8kk$default(this, resId, weight, style, getLoadingStrategy(), null, 16, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResourceFont)) {
            return false;
        }
        ResourceFont resourceFont = (ResourceFont) other;
        return this.resId == resourceFont.resId && p.a(getWeight(), resourceFont.getWeight()) && FontStyle.m5302equalsimpl0(getStyle(), resourceFont.getStyle()) && p.a(this.variationSettings, resourceFont.variationSettings) && FontLoadingStrategy.m5289equalsimpl0(getLoadingStrategy(), resourceFont.getLoadingStrategy());
    }

    @Override // androidx.compose.ui.text.font.Font
    /* JADX INFO: renamed from: getLoadingStrategy-PKNRLFQ, reason: from getter */
    public int getLoadingStrategy() {
        return this.loadingStrategy;
    }

    public final int getResId() {
        return this.resId;
    }

    @Override // androidx.compose.ui.text.font.Font
    /* JADX INFO: renamed from: getStyle-_-LCdwA, reason: from getter */
    public int getStyle() {
        return this.style;
    }

    public final FontVariation.Settings getVariationSettings() {
        return this.variationSettings;
    }

    @Override // androidx.compose.ui.text.font.Font
    public FontWeight getWeight() {
        return this.weight;
    }

    public int hashCode() {
        return this.variationSettings.hashCode() + ((FontLoadingStrategy.m5290hashCodeimpl(getLoadingStrategy()) + ((FontStyle.m5303hashCodeimpl(getStyle()) + ((getWeight().hashCode() + (this.resId * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "ResourceFont(resId=" + this.resId + ", weight=" + getWeight() + ", style=" + ((Object) FontStyle.m5304toStringimpl(getStyle())) + ", loadingStrategy=" + ((Object) FontLoadingStrategy.m5291toStringimpl(getLoadingStrategy())) + ')';
    }

    private ResourceFont(int i10, FontWeight fontWeight, int i11, FontVariation.Settings settings, int i12) {
        this.resId = i10;
        this.weight = fontWeight;
        this.style = i11;
        this.variationSettings = settings;
        this.loadingStrategy = i12;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ResourceFont(int i10, FontWeight fontWeight, int i11, FontVariation.Settings settings, int i12, int i13, h hVar) {
        FontWeight normal = (i13 & 2) != 0 ? FontWeight.INSTANCE.getNormal() : fontWeight;
        int iM5309getNormal_LCdwA = (i13 & 4) != 0 ? FontStyle.INSTANCE.m5309getNormal_LCdwA() : i11;
        this(i10, normal, iM5309getNormal_LCdwA, (i13 & 8) != 0 ? FontVariation.INSTANCE.m5324Settings6EWAqTQ(normal, iM5309getNormal_LCdwA, new FontVariation.Setting[0]) : settings, (i13 & 16) != 0 ? FontLoadingStrategy.INSTANCE.m5293getAsyncPKNRLFQ() : i12, null);
    }
}
