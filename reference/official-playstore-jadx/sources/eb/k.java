package eb;

import com.google.android.gms.internal.auth.d1;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f15159d;

    public k(androidx.core.provider.j jVar, boolean z) {
        super(jVar);
        this.f15159d = z;
    }

    @Override // com.google.android.gms.internal.auth.d1
    public final void j(String str) {
        if (this.f15159d) {
            super.j(str);
        } else {
            h(str);
        }
    }
}
