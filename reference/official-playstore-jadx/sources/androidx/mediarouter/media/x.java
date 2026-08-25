package androidx.mediarouter.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4505a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f4506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4507c;

    public /* synthetic */ x() {
    }

    public static x a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("routes");
        if (parcelableArrayList != null) {
            for (int i10 = 0; i10 < parcelableArrayList.size(); i10++) {
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i10);
                arrayList.add(bundle2 != null ? new m(bundle2) : null);
            }
        }
        return new x(arrayList, bundle.getBoolean("supportsDynamicGroupRoute", false));
    }

    public String toString() {
        switch (this.f4505a) {
            case 0:
                StringBuilder sb2 = new StringBuilder("MediaRouteProviderDescriptor{ routes=");
                List list = this.f4506b;
                sb2.append(Arrays.toString(list.toArray()));
                sb2.append(", isValid=");
                int size = list.size();
                boolean z = false;
                int i10 = 0;
                while (true) {
                    if (i10 >= size) {
                        z = true;
                    } else {
                        m mVar = (m) list.get(i10);
                        if (mVar != null && mVar.e()) {
                            i10++;
                        }
                    }
                }
                return a0.c.m(" }", z, sb2);
            default:
                return super.toString();
        }
    }

    public x(ArrayList arrayList, boolean z) {
        if (arrayList.isEmpty()) {
            this.f4506b = Collections.EMPTY_LIST;
        } else {
            this.f4506b = Collections.unmodifiableList(new ArrayList(arrayList));
        }
        this.f4507c = z;
    }

    public x(List list, boolean z) {
        this.f4506b = list;
        this.f4507c = z;
    }
}
