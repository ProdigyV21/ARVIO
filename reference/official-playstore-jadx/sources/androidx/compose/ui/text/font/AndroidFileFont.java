package androidx.compose.ui.text.font;

import android.content.Context;
import android.os.Build;
import androidx.compose.ui.text.font.FontVariation;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/text/font/AndroidFileFont;", "Landroidx/compose/ui/text/font/AndroidPreloadedFont;", "Ljava/io/File;", "file", "Landroidx/compose/ui/text/font/FontWeight;", "weight", "Landroidx/compose/ui/text/font/FontStyle;", "style", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "variationSettings", "<init>", "(Ljava/io/File;Landroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;Lkotlin/jvm/internal/h;)V", "Landroid/content/Context;", "context", "Landroid/graphics/Typeface;", "doLoad$ui_text_release", "(Landroid/content/Context;)Landroid/graphics/Typeface;", "doLoad", "", "toString", "()Ljava/lang/String;", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "cacheKey", "Ljava/lang/String;", "getCacheKey", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidFileFont extends AndroidPreloadedFont {
    public static final int $stable = 0;
    private final String cacheKey;
    private final File file;

    public /* synthetic */ AndroidFileFont(File file, FontWeight fontWeight, int i10, FontVariation.Settings settings, h hVar) {
        this(file, fontWeight, i10, settings);
    }

    @Override // androidx.compose.ui.text.font.AndroidPreloadedFont
    public android.graphics.Typeface doLoad$ui_text_release(Context context) {
        return Build.VERSION.SDK_INT >= 26 ? TypefaceBuilderCompat.INSTANCE.createFromFile(this.file, context, getVariationSettings()) : android.graphics.Typeface.createFromFile(this.file);
    }

    @Override // androidx.compose.ui.text.font.AndroidPreloadedFont
    public String getCacheKey() {
        return this.cacheKey;
    }

    public final File getFile() {
        return this.file;
    }

    public String toString() {
        return "Font(file=" + this.file + ", weight=" + getWeight() + ", style=" + ((Object) FontStyle.m5304toStringimpl(getStyle())) + ')';
    }

    public /* synthetic */ AndroidFileFont(File file, FontWeight fontWeight, int i10, FontVariation.Settings settings, int i11, h hVar) {
        this(file, (i11 & 2) != 0 ? FontWeight.INSTANCE.getNormal() : fontWeight, (i11 & 4) != 0 ? FontStyle.INSTANCE.m5309getNormal_LCdwA() : i10, settings, null);
    }

    private AndroidFileFont(File file, FontWeight fontWeight, int i10, FontVariation.Settings settings) {
        super(fontWeight, i10, settings, null);
        this.file = file;
        setTypeface$ui_text_release(doLoad$ui_text_release(null));
    }
}
