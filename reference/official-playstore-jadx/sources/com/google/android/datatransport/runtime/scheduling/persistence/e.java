package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12618a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f12619b;

    public /* synthetic */ e(Provider provider, int i10) {
        this.f12618a = i10;
        this.f12619b = provider;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        switch (this.f12618a) {
            case 0:
                String packageName = ((Context) this.f12619b.get()).getPackageName();
                if (packageName != null) {
                    return packageName;
                }
                throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
            default:
                return new p((Context) this.f12619b.get(), Integer.valueOf(p.f12642n).intValue(), "com.google.android.datatransport.events");
        }
    }
}
