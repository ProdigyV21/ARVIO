package io.sentry.android.replay.viewhierarchy;

import android.graphics.Rect;
import androidx.navigation.o;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f17080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f17081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f17082e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList f17083f;

    public g(int i10, int i11, float f10, g gVar, boolean z, Rect rect) {
        this.f17078a = i10;
        this.f17079b = i11;
        this.f17080c = f10;
        this.f17081d = z;
        this.f17082e = rect;
    }

    public final void a(o oVar) {
        ArrayList arrayList;
        if (!((Boolean) oVar.invoke(this)).booleanValue() || (arrayList = this.f17083f) == null) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((g) it.next()).a(oVar);
        }
    }
}
