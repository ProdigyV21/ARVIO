package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text.KeyboardOptions;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u00002\u00020\u0001B?\u00126\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J@\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016JJ\u0010\u0017\u001a\u00020\u000028\b\u0002\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cHÖ\u0003¢\u0006\u0004\b\u001f\u0010 RG\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010\u0016¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text2/input/InputTransformationByValue;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "old", "proposed", "transformation", "<init>", "(Lr7/p;)V", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "valueWithChanges", "Lx6/t0;", "transformInput", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "", "toString", "()Ljava/lang/String;", "component1", "()Lr7/p;", "copy", "(Lr7/p;)Landroidx/compose/foundation/text2/input/InputTransformationByValue;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lr7/p;", "getTransformation", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class InputTransformationByValue implements InputTransformation {
    private final p<CharSequence, CharSequence, CharSequence> transformation;

    /* JADX WARN: Multi-variable type inference failed */
    public InputTransformationByValue(p<? super CharSequence, ? super CharSequence, ? extends CharSequence> pVar) {
        this.transformation = pVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ InputTransformationByValue copy$default(InputTransformationByValue inputTransformationByValue, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            pVar = inputTransformationByValue.transformation;
        }
        return inputTransformationByValue.copy(pVar);
    }

    public final p<CharSequence, CharSequence, CharSequence> component1() {
        return this.transformation;
    }

    public final InputTransformationByValue copy(p<? super CharSequence, ? super CharSequence, ? extends CharSequence> transformation) {
        return new InputTransformationByValue(transformation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof InputTransformationByValue) && kotlin.jvm.internal.p.a(this.transformation, ((InputTransformationByValue) other).transformation);
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public final /* synthetic */ KeyboardOptions getKeyboardOptions() {
        return a.a(this);
    }

    public final p<CharSequence, CharSequence, CharSequence> getTransformation() {
        return this.transformation;
    }

    public int hashCode() {
        return this.transformation.hashCode();
    }

    public String toString() {
        return "InputTransformation.byValue(transformation=" + this.transformation + ')';
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        TextFieldCharSequence textFieldCharSequenceM1058toTextFieldCharSequenceOEnZFl4$foundation_release$default = TextFieldBuffer.m1058toTextFieldCharSequenceOEnZFl4$foundation_release$default(valueWithChanges, null, 1, null);
        CharSequence charSequence = (CharSequence) this.transformation.invoke(originalValue, textFieldCharSequenceM1058toTextFieldCharSequenceOEnZFl4$foundation_release$default);
        if (charSequence == textFieldCharSequenceM1058toTextFieldCharSequenceOEnZFl4$foundation_release$default) {
            return;
        }
        if (charSequence == originalValue) {
            valueWithChanges.revertAllChanges();
        } else {
            valueWithChanges.setTextIfChanged$foundation_release(charSequence);
        }
    }
}
