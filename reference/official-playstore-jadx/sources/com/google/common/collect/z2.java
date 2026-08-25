package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: classes4.dex */
public final class z2 extends b {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public transient y2 f14146q;

    @Override // com.google.common.collect.q, com.google.common.collect.u
    public final Map d() {
        Map map = this.f14084o;
        return map instanceof NavigableMap ? new h(this, (NavigableMap) map) : map instanceof SortedMap ? new k(this, (SortedMap) map) : new f(this, map);
    }

    @Override // com.google.common.collect.q, com.google.common.collect.u
    public final Set f() {
        Map map = this.f14084o;
        return map instanceof NavigableMap ? new i(this, (NavigableMap) map) : map instanceof SortedMap ? new l(this, (SortedMap) map) : new g(this, map);
    }

    @Override // com.google.common.collect.q
    public final Collection j() {
        return (List) this.f14146q.get();
    }
}
