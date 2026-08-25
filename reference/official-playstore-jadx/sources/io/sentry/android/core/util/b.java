package io.sentry.android.core.util;

import android.content.Context;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f16789a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f16790b;

    public b(a aVar) {
        this.f16790b = aVar;
    }

    public final Object a(Context context) {
        if (this.f16789a == null) {
            synchronized (this) {
                try {
                    if (this.f16789a == null) {
                        this.f16789a = this.f16790b.a(context);
                    }
                } finally {
                }
            }
        }
        return this.f16789a;
    }
}
