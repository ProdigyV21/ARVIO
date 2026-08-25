package io.sentry.android.replay;

import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f16874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f16875m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16876i;

    static {
        int i10 = 1;
        f16874l = new c(i10, 0);
        f16875m = new c(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(int i10, int i11) {
        super(i10);
        this.f16876i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f16876i) {
            case 0:
                return String.valueOf(kotlin.text.o.i0(((kotlin.text.i) obj).getValue())).toUpperCase(Locale.ROOT);
            default:
                Map.Entry entry = (Map.Entry) obj;
                return androidx.compose.foundation.c.m('=', (String) entry.getKey(), (String) entry.getValue());
        }
    }
}
