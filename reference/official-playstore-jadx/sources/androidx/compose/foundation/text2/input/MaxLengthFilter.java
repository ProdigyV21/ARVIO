package androidx.compose.foundation.text2.input;

import a0.c;
import androidx.compose.foundation.text.KeyboardOptions;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÂ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\tJ\u001a\u0010\u001b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/text2/input/MaxLengthFilter;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "", "maxLength", "", "inCodepoints", "<init>", "(IZ)V", "component1", "()I", "component2", "()Z", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "valueWithChanges", "Lx6/t0;", "transformInput", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "", "toString", "()Ljava/lang/String;", "copy", "(IZ)Landroidx/compose/foundation/text2/input/MaxLengthFilter;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class MaxLengthFilter implements InputTransformation {
    private final boolean inCodepoints;
    private final int maxLength;

    public MaxLengthFilter(int i10, boolean z) {
        this.maxLength = i10;
        this.inCodepoints = z;
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "maxLength must be at least zero, was ").toString());
        }
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final int getMaxLength() {
        return this.maxLength;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getInCodepoints() {
        return this.inCodepoints;
    }

    public static /* synthetic */ MaxLengthFilter copy$default(MaxLengthFilter maxLengthFilter, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = maxLengthFilter.maxLength;
        }
        if ((i11 & 2) != 0) {
            z = maxLengthFilter.inCodepoints;
        }
        return maxLengthFilter.copy(i10, z);
    }

    public final MaxLengthFilter copy(int maxLength, boolean inCodepoints) {
        return new MaxLengthFilter(maxLength, inCodepoints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MaxLengthFilter)) {
            return false;
        }
        MaxLengthFilter maxLengthFilter = (MaxLengthFilter) other;
        return this.maxLength == maxLengthFilter.maxLength && this.inCodepoints == maxLengthFilter.inCodepoints;
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public final /* synthetic */ KeyboardOptions getKeyboardOptions() {
        return a.a(this);
    }

    public int hashCode() {
        return (this.maxLength * 31) + (this.inCodepoints ? 1231 : 1237);
    }

    public String toString() {
        return c.o(c.t("InputTransformation.", this.inCodepoints ? "maxLengthInCodepoints" : "maxLengthInChars", "(maxLength="), this.maxLength, ')');
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        if ((this.inCodepoints ? valueWithChanges.getCodepointLength() : valueWithChanges.getLength()) > this.maxLength) {
            valueWithChanges.revertAllChanges();
        }
    }
}
