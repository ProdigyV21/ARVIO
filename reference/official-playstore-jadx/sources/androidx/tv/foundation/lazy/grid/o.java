package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f5276a = new LinkedHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i2.k f5277b = i2.j.f16053a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f5278c = new LinkedHashSet();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f5279d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f5280e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f5281f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f5282g = new ArrayList();

    public static void a(c0 c0Var, int i10) {
        long j10 = c0Var.f5186m;
        if (c0Var.f5177c) {
            IntOffset.m5798copyiSbpLlY$default(j10, 0, i10, 1, null);
        } else {
            IntOffset.m5798copyiSbpLlY$default(j10, i10, 0, 2, null);
        }
        int size = c0Var.f5181g.size();
        for (int i11 = 0; i11 < size; i11++) {
            c0Var.a(i11);
        }
    }

    public static void b(c0 c0Var) {
        int size = c0Var.f5181g.size();
        for (int i10 = 0; i10 < size; i10++) {
            c0Var.a(i10);
        }
    }
}
