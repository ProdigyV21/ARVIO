package b2;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f7111a = new LinkedHashSet();

    public a(e eVar) {
        eVar.c("androidx.savedstate.Restarter", this);
    }

    @Override // b2.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.f7111a));
        return bundle;
    }
}
