package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text2.input.TextFieldBuffer;
import androidx.compose.ui.text.StringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.intl.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text2/input/AllCapsTransformation;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Landroidx/compose/ui/text/intl/Locale;", "locale", "<init>", "(Landroidx/compose/ui/text/intl/Locale;)V", "component1", "()Landroidx/compose/ui/text/intl/Locale;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "valueWithChanges", "Lx6/t0;", "transformInput", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/TextFieldBuffer;)V", "", "toString", "()Ljava/lang/String;", "copy", "(Landroidx/compose/ui/text/intl/Locale;)Landroidx/compose/foundation/text2/input/AllCapsTransformation;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/text/intl/Locale;", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class AllCapsTransformation implements InputTransformation {
    private final KeyboardOptions keyboardOptions = new KeyboardOptions(KeyboardCapitalization.INSTANCE.m5395getCharactersIUNYP9k(), false, 0, 0, null, 30, null);
    private final Locale locale;

    public AllCapsTransformation(Locale locale) {
        this.locale = locale;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Locale getLocale() {
        return this.locale;
    }

    public static /* synthetic */ AllCapsTransformation copy$default(AllCapsTransformation allCapsTransformation, Locale locale, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            locale = allCapsTransformation.locale;
        }
        return allCapsTransformation.copy(locale);
    }

    public final AllCapsTransformation copy(Locale locale) {
        return new AllCapsTransformation(locale);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AllCapsTransformation) && p.a(this.locale, ((AllCapsTransformation) other).locale);
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    public int hashCode() {
        return this.locale.hashCode();
    }

    public String toString() {
        return "InputTransformation.allCaps(locale=" + this.locale + ')';
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        TextFieldBuffer.ChangeList changes = valueWithChanges.getChanges();
        for (int i10 = 0; i10 < changes.getChangeCount(); i10++) {
            long jMo1054getRangejx7JFs = changes.mo1054getRangejx7JFs(i10);
            changes.mo1053getOriginalRangejx7JFs(i10);
            if (!TextRange.m5186getCollapsedimpl(jMo1054getRangejx7JFs)) {
                valueWithChanges.replace(TextRange.m5190getMinimpl(jMo1054getRangejx7JFs), TextRange.m5189getMaximpl(jMo1054getRangejx7JFs), StringKt.toUpperCase(TextRangeKt.m5199substringFDrldGo(valueWithChanges.asCharSequence(), jMo1054getRangejx7JFs), this.locale));
            }
        }
    }
}
