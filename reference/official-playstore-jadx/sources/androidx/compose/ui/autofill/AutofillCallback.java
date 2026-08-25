package androidx.compose.ui.autofill;

import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillManager$AutofillCallback;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/autofill/AutofillCallback;", "Landroid/view/autofill/AutofillManager$AutofillCallback;", "<init>", "()V", "Landroid/view/View;", "view", "", "virtualId", "event", "Lx6/t0;", "onAutofillEvent", "(Landroid/view/View;II)V", "Landroidx/compose/ui/autofill/AndroidAutofill;", "autofill", "register", "(Landroidx/compose/ui/autofill/AndroidAutofill;)V", "unregister", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AutofillCallback extends AutofillManager$AutofillCallback {
    public static final int $stable = 0;
    public static final AutofillCallback INSTANCE = new AutofillCallback();

    private AutofillCallback() {
    }

    public void onAutofillEvent(View view, int virtualId, int event) {
        super.onAutofillEvent(view, virtualId, event);
        Log.d("Autofill Status", event != 1 ? event != 2 ? event != 3 ? "Unknown status event." : "Autofill popup isn't shown because autofill is not available.\n\nDid you set up autofill?\n1. Go to Settings > System > Languages&input > Advanced > Autofill Service\n2. Pick a service\n\nDid you add an account?\n1. Go to Settings > System > Languages&input > Advanced\n2. Click on the settings icon next to the Autofill Service\n3. Add your account" : "Autofill popup was hidden." : "Autofill popup was shown.");
    }

    public final void register(AndroidAutofill autofill) {
        autofill.getAutofillManager().registerCallback(this);
    }

    public final void unregister(AndroidAutofill autofill) {
        autofill.getAutofillManager().unregisterCallback(this);
    }
}
