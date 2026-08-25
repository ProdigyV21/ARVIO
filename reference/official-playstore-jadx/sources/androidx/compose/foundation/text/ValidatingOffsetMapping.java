package androidx.compose.foundation.text;

import a0.c;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/text/ValidatingOffsetMapping;", "Landroidx/compose/ui/text/input/OffsetMapping;", "delegate", "originalLength", "", "transformedLength", "(Landroidx/compose/ui/text/input/OffsetMapping;II)V", "originalToTransformed", "offset", "transformedToOriginal", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ValidatingOffsetMapping implements OffsetMapping {
    private final OffsetMapping delegate;
    private final int originalLength;
    private final int transformedLength;

    public ValidatingOffsetMapping(OffsetMapping offsetMapping, int i10, int i11) {
        this.delegate = offsetMapping;
        this.originalLength = i10;
        this.transformedLength = i11;
    }

    @Override // androidx.compose.ui.text.input.OffsetMapping
    public int originalToTransformed(int offset) {
        int iOriginalToTransformed = this.delegate.originalToTransformed(offset);
        if (offset < 0 || offset > this.originalLength) {
            return iOriginalToTransformed;
        }
        if (iOriginalToTransformed < 0 || iOriginalToTransformed > this.transformedLength) {
            throw new IllegalStateException(c.o(androidx.compose.foundation.c.v("OffsetMapping.originalToTransformed returned invalid mapping: ", offset, " -> ", iOriginalToTransformed, " is not in range of transformed text [0, "), this.transformedLength, ']').toString());
        }
        return iOriginalToTransformed;
    }

    @Override // androidx.compose.ui.text.input.OffsetMapping
    public int transformedToOriginal(int offset) {
        int iTransformedToOriginal = this.delegate.transformedToOriginal(offset);
        if (offset < 0 || offset > this.transformedLength) {
            return iTransformedToOriginal;
        }
        if (iTransformedToOriginal < 0 || iTransformedToOriginal > this.originalLength) {
            throw new IllegalStateException(c.o(androidx.compose.foundation.c.v("OffsetMapping.transformedToOriginal returned invalid mapping: ", offset, " -> ", iTransformedToOriginal, " is not in range of original text [0, "), this.originalLength, ']').toString());
        }
        return iTransformedToOriginal;
    }
}
