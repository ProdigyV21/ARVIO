package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m5351updateRangeAfterDeletepWDy79M(long j10, long j11) {
        int iM5188getLengthimpl;
        int iM5190getMinimpl = TextRange.m5190getMinimpl(j10);
        int iM5189getMaximpl = TextRange.m5189getMaximpl(j10);
        if (TextRange.m5194intersects5zctL8(j11, j10)) {
            if (TextRange.m5182contains5zctL8(j11, j10)) {
                iM5190getMinimpl = TextRange.m5190getMinimpl(j11);
                iM5189getMaximpl = iM5190getMinimpl;
            } else {
                if (TextRange.m5182contains5zctL8(j10, j11)) {
                    iM5188getLengthimpl = TextRange.m5188getLengthimpl(j11);
                } else if (TextRange.m5183containsimpl(j11, iM5190getMinimpl)) {
                    iM5190getMinimpl = TextRange.m5190getMinimpl(j11);
                    iM5188getLengthimpl = TextRange.m5188getLengthimpl(j11);
                } else {
                    iM5189getMaximpl = TextRange.m5190getMinimpl(j11);
                }
                iM5189getMaximpl -= iM5188getLengthimpl;
            }
        } else if (iM5189getMaximpl > TextRange.m5190getMinimpl(j11)) {
            iM5190getMinimpl -= TextRange.m5188getLengthimpl(j11);
            iM5188getLengthimpl = TextRange.m5188getLengthimpl(j11);
            iM5189getMaximpl -= iM5188getLengthimpl;
        }
        return TextRangeKt.TextRange(iM5190getMinimpl, iM5189getMaximpl);
    }
}
