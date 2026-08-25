package com.typesafe.config.impl;

import java.util.Comparator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class u0 implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14625i;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f14625i) {
            case 0:
                return Integer.compare(((Integer) ((Map.Entry) obj).getKey()).intValue(), ((Integer) ((Map.Entry) obj2).getKey()).intValue());
            default:
                return ((g1) obj2).b() - ((g1) obj).b();
        }
    }
}
