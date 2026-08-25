package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text.KeyboardOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text2/input/FilterChain;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "first", "second", "<init>", "(Landroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/foundation/text2/input/InputTransformation;)V", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "valueWithChanges", "Lx6/t0;", "transformInput", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Landroidx/compose/foundation/text/KeyboardOptions;", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FilterChain implements InputTransformation {
    private final InputTransformation first;
    private final InputTransformation second;

    public FilterChain(InputTransformation inputTransformation, InputTransformation inputTransformation2) {
        this.first = inputTransformation;
        this.second = inputTransformation2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || FilterChain.class != other.getClass()) {
            return false;
        }
        FilterChain filterChain = (FilterChain) other;
        return p.a(this.first, filterChain.first) && p.a(this.second, filterChain.second) && p.a(getKeyboardOptions(), filterChain.getKeyboardOptions());
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public KeyboardOptions getKeyboardOptions() {
        KeyboardOptions keyboardOptions = this.second.getKeyboardOptions();
        return keyboardOptions == null ? this.first.getKeyboardOptions() : keyboardOptions;
    }

    public int hashCode() {
        int iHashCode = (this.second.hashCode() + (this.first.hashCode() * 31)) * 32;
        KeyboardOptions keyboardOptions = getKeyboardOptions();
        return iHashCode + (keyboardOptions != null ? keyboardOptions.hashCode() : 0);
    }

    public String toString() {
        return this.first + ".then(" + this.second + ')';
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        this.first.transformInput(originalValue, valueWithChanges);
        this.second.transformInput(originalValue, valueWithChanges);
    }
}
