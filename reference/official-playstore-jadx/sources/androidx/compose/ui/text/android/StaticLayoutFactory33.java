package androidx.compose.ui.text.android;

import android.graphics.text.LineBreakConfig;
import android.text.StaticLayout;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory33;", "", "<init>", "()V", "Landroid/text/StaticLayout;", TtmlNode.TAG_LAYOUT, "", "isFallbackLineSpacingEnabled", "(Landroid/text/StaticLayout;)Z", "Landroid/text/StaticLayout$Builder;", "builder", "", "lineBreakStyle", "lineBreakWordStyle", "Lx6/t0;", "setLineBreakConfig", "(Landroid/text/StaticLayout$Builder;II)V", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StaticLayoutFactory33 {
    public static final StaticLayoutFactory33 INSTANCE = new StaticLayoutFactory33();

    private StaticLayoutFactory33() {
    }

    @n
    public static final boolean isFallbackLineSpacingEnabled(StaticLayout layout) {
        return layout.isFallbackLineSpacingEnabled();
    }

    @n
    public static final void setLineBreakConfig(StaticLayout.Builder builder, int lineBreakStyle, int lineBreakWordStyle) {
        builder.setLineBreakConfig(new LineBreakConfig.Builder().setLineBreakStyle(lineBreakStyle).setLineBreakWordStyle(lineBreakWordStyle).build());
    }
}
