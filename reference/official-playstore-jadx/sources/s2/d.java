package s2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f21633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f21634b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f21635c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f21636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f21637e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f21638f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f21639g;

    public d(int i10, String str, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5) {
        this.f21633a = i10;
        this.f21634b = str;
        this.f21635c = Collections.unmodifiableList(arrayList);
        this.f21636d = Collections.unmodifiableList(arrayList2);
        this.f21637e = Collections.unmodifiableList(arrayList3);
        this.f21638f = Collections.unmodifiableList(arrayList4);
        this.f21639g = Collections.unmodifiableList(arrayList5);
    }
}
