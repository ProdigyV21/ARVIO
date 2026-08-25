package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text.StringHelpers_jvmKt;
import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.foundation.text2.input.internal.CodepointHelpers_jvmKt;
import androidx.compose.foundation.text2.input.internal.OffsetMappingCalculator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"mask", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "Landroidx/compose/foundation/text2/input/CodepointTransformation$Companion;", "character", "", "toVisualText", "", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "codepointTransformation", "offsetMappingCalculator", "Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CodepointTransformationKt {
    public static final CodepointTransformation mask(CodepointTransformation.Companion companion, char c10) {
        return new MaskCodepointTransformation(c10);
    }

    public static final CharSequence toVisualText(TextFieldCharSequence textFieldCharSequence, CodepointTransformation codepointTransformation, OffsetMappingCalculator offsetMappingCalculator) {
        StringBuilder sb2 = new StringBuilder();
        int i10 = 0;
        boolean z = false;
        int i11 = 0;
        while (i10 < textFieldCharSequence.length()) {
            int iCodePointAt = CodepointHelpers_jvmKt.codePointAt(textFieldCharSequence, i10);
            int iTransform = codepointTransformation.transform(i11, iCodePointAt);
            int iCharCount = CodepointHelpers_jvmKt.charCount(iCodePointAt);
            if (iTransform != iCodePointAt) {
                offsetMappingCalculator.recordEditOperation(sb2.length(), sb2.length() + iCharCount, CodepointHelpers_jvmKt.charCount(iTransform));
                z = true;
            }
            StringHelpers_jvmKt.appendCodePointX(sb2, iTransform);
            i10 += iCharCount;
            i11++;
        }
        return z ? sb2.toString() : textFieldCharSequence;
    }
}
