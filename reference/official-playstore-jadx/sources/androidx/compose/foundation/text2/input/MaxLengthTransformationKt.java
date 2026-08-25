package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.InputTransformation;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"maxLengthInChars", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Landroidx/compose/foundation/text2/input/InputTransformation$Companion;", "maxLength", "", "maxLengthInCodepoints", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MaxLengthTransformationKt {
    public static final InputTransformation maxLengthInChars(InputTransformation.Companion companion, int i10) {
        return new MaxLengthFilter(i10, false);
    }

    public static final InputTransformation maxLengthInCodepoints(InputTransformation.Companion companion, int i10) {
        return new MaxLengthFilter(i10, true);
    }
}
