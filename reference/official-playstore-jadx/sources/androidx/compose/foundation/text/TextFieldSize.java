package androidx.compose.foundation.text;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\rH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ5\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u0012\u0010\fR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\n\u001a\u00020\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R*\u0010-\u001a\u00020\r2\u0006\u0010,\u001a\u00020\r8\u0006@BX\u0086\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\u000f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"Landroidx/compose/foundation/text/TextFieldSize;", "", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Landroidx/compose/ui/text/TextStyle;", "resolvedStyle", "typeface", "<init>", "(Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/text/TextStyle;Ljava/lang/Object;)V", "Landroidx/compose/ui/unit/IntSize;", "computeMinSize-YbymL2g", "()J", "computeMinSize", "Lx6/t0;", "update", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "Landroidx/compose/ui/text/TextStyle;", "getResolvedStyle", "()Landroidx/compose/ui/text/TextStyle;", "setResolvedStyle", "(Landroidx/compose/ui/text/TextStyle;)V", "Ljava/lang/Object;", "getTypeface", "()Ljava/lang/Object;", "setTypeface", "(Ljava/lang/Object;)V", "<set-?>", "minSize", "J", "getMinSize-YbymL2g", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldSize {
    private Density density;
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection layoutDirection;
    private long minSize = m899computeMinSizeYbymL2g();
    private TextStyle resolvedStyle;
    private Object typeface;

    public TextFieldSize(LayoutDirection layoutDirection, Density density, FontFamily.Resolver resolver, TextStyle textStyle, Object obj) {
        this.layoutDirection = layoutDirection;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.resolvedStyle = textStyle;
        this.typeface = obj;
    }

    /* JADX INFO: renamed from: computeMinSize-YbymL2g, reason: not valid java name */
    private final long m899computeMinSizeYbymL2g() {
        return TextFieldDelegateKt.computeSizeForDefaultText$default(this.resolvedStyle, this.density, this.fontFamilyResolver, null, 0, 24, null);
    }

    public final Density getDensity() {
        return this.density;
    }

    public final FontFamily.Resolver getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    /* JADX INFO: renamed from: getMinSize-YbymL2g, reason: not valid java name and from getter */
    public final long getMinSize() {
        return this.minSize;
    }

    public final TextStyle getResolvedStyle() {
        return this.resolvedStyle;
    }

    public final Object getTypeface() {
        return this.typeface;
    }

    public final void setDensity(Density density) {
        this.density = density;
    }

    public final void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver = resolver;
    }

    public final void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    public final void setResolvedStyle(TextStyle textStyle) {
        this.resolvedStyle = textStyle;
    }

    public final void setTypeface(Object obj) {
        this.typeface = obj;
    }

    public final void update(LayoutDirection layoutDirection, Density density, FontFamily.Resolver fontFamilyResolver, TextStyle resolvedStyle, Object typeface) {
        if (layoutDirection == this.layoutDirection && p.a(density, this.density) && p.a(fontFamilyResolver, this.fontFamilyResolver) && p.a(resolvedStyle, this.resolvedStyle) && p.a(typeface, this.typeface)) {
            return;
        }
        this.layoutDirection = layoutDirection;
        this.density = density;
        this.fontFamilyResolver = fontFamilyResolver;
        this.resolvedStyle = resolvedStyle;
        this.typeface = typeface;
        this.minSize = m899computeMinSizeYbymL2g();
    }
}
