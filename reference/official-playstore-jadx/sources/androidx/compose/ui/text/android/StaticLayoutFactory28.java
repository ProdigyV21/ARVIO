package androidx.compose.ui.text.android;

import android.text.StaticLayout;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory28;", "", "<init>", "()V", "Landroid/text/StaticLayout$Builder;", "builder", "", "useFallbackLineSpacing", "Lx6/t0;", "setUseLineSpacingFromFallbacks", "(Landroid/text/StaticLayout$Builder;Z)V", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StaticLayoutFactory28 {
    public static final StaticLayoutFactory28 INSTANCE = new StaticLayoutFactory28();

    private StaticLayoutFactory28() {
    }

    @n
    public static final void setUseLineSpacingFromFallbacks(StaticLayout.Builder builder, boolean useFallbackLineSpacing) {
        builder.setUseLineSpacingFromFallbacks(useFallbackLineSpacing);
    }
}
