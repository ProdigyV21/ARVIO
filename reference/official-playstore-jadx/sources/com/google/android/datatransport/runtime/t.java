package com.google.android.datatransport.runtime;

import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class t implements z2.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f12646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f12647b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w f12648c;

    public t(Set set, i iVar, w wVar) {
        this.f12646a = set;
        this.f12647b = iVar;
        this.f12648c = wVar;
    }

    @Override // z2.f
    public final u a(String str, z2.b bVar, z2.d dVar) {
        Set set = this.f12646a;
        if (set.contains(bVar)) {
            return new u(this.f12647b, str, bVar, dVar, this.f12648c);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", bVar, set));
    }
}
