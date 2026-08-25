package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0016\u0010\u0003\u001a\u00020\u0000*\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\u00020\u00058\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/TextRange;", "reverse-5zc-tL8", "(J)J", "reverse", "Lkotlin/Function0;", "", "text", "Lx6/t0;", "logDebug", "(Lr7/a;)V", "", "DEBUG", "Z", "DEBUG_TAG", "Ljava/lang/String;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionStateKt {
    private static final boolean DEBUG = false;
    private static final String DEBUG_TAG = "TextFieldSelectionState";

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reverse-5zc-tL8, reason: not valid java name */
    public static final long m1193reverse5zctL8(long j10) {
        return TextRangeKt.TextRange(TextRange.m5187getEndimpl(j10), TextRange.m5192getStartimpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logDebug(a<String> aVar) {
    }
}
