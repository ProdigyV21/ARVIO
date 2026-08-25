package androidx.compose.ui.text;

import androidx.compose.ui.text.android.selection.WordBoundary;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/android/selection/WordBoundary;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AndroidParagraph$wordBoundary$2 extends r implements r7.a<WordBoundary> {
    final /* synthetic */ AndroidParagraph this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidParagraph$wordBoundary$2(AndroidParagraph androidParagraph) {
        super(0);
        this.this$0 = androidParagraph;
    }

    @Override // r7.a
    public final WordBoundary invoke() {
        return new WordBoundary(this.this$0.getTextLocale$ui_text_release(), this.this$0.layout.getText());
    }
}
