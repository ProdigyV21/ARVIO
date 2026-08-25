package io.sentry.android.replay;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16936i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ View f16937l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e0(View view, int i10) {
        super(1);
        this.f16936i = i10;
        this.f16937l = view;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f16936i) {
        }
        return Boolean.valueOf(kotlin.jvm.internal.p.a(((WeakReference) obj).get(), this.f16937l));
    }
}
