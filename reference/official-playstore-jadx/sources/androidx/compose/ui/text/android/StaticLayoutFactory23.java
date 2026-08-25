package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.StaticLayout;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory23;", "Landroidx/compose/ui/text/android/StaticLayoutFactoryImpl;", "()V", "create", "Landroid/text/StaticLayout;", "params", "Landroidx/compose/ui/text/android/StaticLayoutParams;", "isFallbackLineSpacingEnabled", "", TtmlNode.TAG_LAYOUT, "useFallbackLineSpacing", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StaticLayoutFactory23 implements StaticLayoutFactoryImpl {
    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public StaticLayout create(StaticLayoutParams params) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(params.getText(), params.getStart(), params.getEnd(), params.getPaint(), params.getWidth());
        builderObtain.setTextDirection(params.getTextDir());
        builderObtain.setAlignment(params.getAlignment());
        builderObtain.setMaxLines(params.getMaxLines());
        builderObtain.setEllipsize(params.getEllipsize());
        builderObtain.setEllipsizedWidth(params.getEllipsizedWidth());
        builderObtain.setLineSpacing(params.getLineSpacingExtra(), params.getLineSpacingMultiplier());
        builderObtain.setIncludePad(params.getIncludePadding());
        builderObtain.setBreakStrategy(params.getBreakStrategy());
        builderObtain.setHyphenationFrequency(params.getHyphenationFrequency());
        builderObtain.setIndents(params.getLeftIndents(), params.getRightIndents());
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 26) {
            StaticLayoutFactory26.setJustificationMode(builderObtain, params.getJustificationMode());
        }
        if (i10 >= 28) {
            StaticLayoutFactory28.setUseLineSpacingFromFallbacks(builderObtain, params.getUseFallbackLineSpacing());
        }
        if (i10 >= 33) {
            StaticLayoutFactory33.setLineBreakConfig(builderObtain, params.getLineBreakStyle(), params.getLineBreakWordStyle());
        }
        return builderObtain.build();
    }

    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public boolean isFallbackLineSpacingEnabled(StaticLayout layout, boolean useFallbackLineSpacing) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            return StaticLayoutFactory33.isFallbackLineSpacingEnabled(layout);
        }
        if (i10 >= 28) {
            return useFallbackLineSpacing;
        }
        return false;
    }
}
