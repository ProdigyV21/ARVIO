package androidx.mediarouter.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class z {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final z f4516c = new z(new Bundle(), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f4517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f4518b;

    public z(Bundle bundle, ArrayList arrayList) {
        this.f4517a = bundle;
        this.f4518b = arrayList;
    }

    public static z b(Bundle bundle) {
        if (bundle != null) {
            return new z(bundle, null);
        }
        return null;
    }

    public final void a() {
        if (this.f4518b == null) {
            ArrayList<String> stringArrayList = this.f4517a.getStringArrayList("controlCategories");
            this.f4518b = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.f4518b = Collections.EMPTY_LIST;
            }
        }
    }

    public final ArrayList c() {
        a();
        return new ArrayList(this.f4518b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        a();
        zVar.a();
        return this.f4518b.equals(zVar.f4518b);
    }

    public final int hashCode() {
        a();
        return this.f4518b.hashCode();
    }

    public final String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(c().toArray()) + " }";
    }
}
