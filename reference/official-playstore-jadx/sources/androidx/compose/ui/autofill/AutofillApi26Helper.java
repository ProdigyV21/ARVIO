package androidx.compose.ui.autofill;

import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001c\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001d\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001e\u0010\u001bJ\u0017\u0010 \u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/compose/ui/autofill/AutofillApi26Helper;", "", "<init>", "()V", "Landroid/view/ViewStructure;", "structure", "Landroid/view/autofill/AutofillId;", "parent", "", "virtualId", "Lx6/t0;", "setAutofillId", "(Landroid/view/ViewStructure;Landroid/view/autofill/AutofillId;I)V", "getAutofillId", "(Landroid/view/ViewStructure;)Landroid/view/autofill/AutofillId;", LinkHeader.Parameters.Type, "setAutofillType", "(Landroid/view/ViewStructure;I)V", "", "", "hints", "setAutofillHints", "(Landroid/view/ViewStructure;[Ljava/lang/String;)V", "Landroid/view/autofill/AutofillValue;", "value", "", "isText", "(Landroid/view/autofill/AutofillValue;)Z", "isDate", "isList", "isToggle", "", "textValue", "(Landroid/view/autofill/AutofillValue;)Ljava/lang/CharSequence;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AutofillApi26Helper {
    public static final int $stable = 0;
    public static final AutofillApi26Helper INSTANCE = new AutofillApi26Helper();

    private AutofillApi26Helper() {
    }

    public final AutofillId getAutofillId(ViewStructure structure) {
        return structure.getAutofillId();
    }

    public final boolean isDate(AutofillValue value) {
        return value.isDate();
    }

    public final boolean isList(AutofillValue value) {
        return value.isList();
    }

    public final boolean isText(AutofillValue value) {
        return value.isText();
    }

    public final boolean isToggle(AutofillValue value) {
        return value.isToggle();
    }

    public final void setAutofillHints(ViewStructure structure, String[] hints) {
        structure.setAutofillHints(hints);
    }

    public final void setAutofillId(ViewStructure structure, AutofillId parent, int virtualId) {
        structure.setAutofillId(parent, virtualId);
    }

    public final void setAutofillType(ViewStructure structure, int type) {
        structure.setAutofillType(type);
    }

    public final CharSequence textValue(AutofillValue value) {
        return value.getTextValue();
    }
}
