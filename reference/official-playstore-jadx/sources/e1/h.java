package e1;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends androidx.emoji2.text.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f15015a;

    public h(EditText editText) {
        this.f15015a = new WeakReference(editText);
    }

    @Override // androidx.emoji2.text.j
    public final void onInitialized() {
        i.a((EditText) this.f15015a.get(), 1);
    }
}
