package androidx.compose.ui.platform;

import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/ClipboardManager;", "", "Landroidx/compose/ui/text/AnnotatedString;", "annotatedString", "Lx6/t0;", "setText", "(Landroidx/compose/ui/text/AnnotatedString;)V", "getText", "()Landroidx/compose/ui/text/AnnotatedString;", "", "hasText", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ClipboardManager {
    AnnotatedString getText();

    boolean hasText();

    void setText(AnnotatedString annotatedString);
}
