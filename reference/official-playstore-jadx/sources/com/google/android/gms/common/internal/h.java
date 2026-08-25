package com.google.android.gms.common.internal;

import android.accounts.Account;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Account f12845a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f12846b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f12847c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map f12848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f12849e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f12850f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final t4.a f12851g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Integer f12852h;

    public h(Account account, Set set, String str, String str2) {
        this.f12845a = account;
        Set setUnmodifiableSet = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.f12846b = setUnmodifiableSet;
        Map map = Collections.EMPTY_MAP;
        this.f12848d = map;
        this.f12849e = str;
        this.f12850f = str2;
        this.f12851g = t4.a.f21887i;
        HashSet hashSet = new HashSet(setUnmodifiableSet);
        Iterator it = map.values().iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        this.f12847c = Collections.unmodifiableSet(hashSet);
    }
}
