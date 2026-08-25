package e1;

import android.text.Editable;
import androidx.emoji2.text.g0;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends Editable.Factory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f15000a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile a f15001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Class f15002c;

    @Override // android.text.Editable.Factory
    public final Editable newEditable(CharSequence charSequence) {
        Class cls = f15002c;
        return cls != null ? new g0(cls, charSequence) : super.newEditable(charSequence);
    }
}
