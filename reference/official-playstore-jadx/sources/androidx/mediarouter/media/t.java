package androidx.mediarouter.media;

import android.content.ComponentName;
import android.os.Bundle;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4482a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Cloneable f4483b;

    public /* synthetic */ t() {
    }

    public z a() {
        if (((ArrayList) this.f4483b) == null) {
            return z.f4516c;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("controlCategories", (ArrayList) this.f4483b);
        return new z(bundle, (ArrayList) this.f4483b);
    }

    public String toString() {
        switch (this.f4482a) {
            case 0:
                return "ProviderMetadata{ componentName=" + ((ComponentName) this.f4483b).flattenToShortString() + " }";
            default:
                return super.toString();
        }
    }

    public t(ComponentName componentName) {
        this.f4483b = componentName;
    }
}
