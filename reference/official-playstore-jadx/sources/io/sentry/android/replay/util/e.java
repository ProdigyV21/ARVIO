package io.sentry.android.replay.util;

import a8.g0;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import androidx.navigation.o;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17046i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f17047l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f17048m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f17049n;

    public e() {
        this.f17046i = 0;
        this.f17047l = f4.f.o(3, d.f17043l);
        this.f17048m = f4.f.o(3, new g0(this, 27));
        this.f17049n = f4.f.o(3, d.f17044m);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, x6.s] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f17046i) {
            case 0:
                ?? r02 = this.f17047l;
                if (r02.isInitialized() && !((Bitmap) r02.getValue()).isRecycled()) {
                    ((Bitmap) r02.getValue()).recycle();
                    break;
                }
                break;
            default:
                ((com.google.android.gms.common.api.internal.g0) this.f17049n).n(true, true, null);
                break;
        }
    }

    public List i(Bitmap bitmap, io.sentry.android.replay.viewhierarchy.g gVar, Matrix matrix) {
        if (bitmap.isRecycled()) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        Canvas canvas = new Canvas(bitmap);
        if (matrix != null) {
            canvas.setMatrix(matrix);
        }
        gVar.a(new o(this, bitmap, matrix, arrayList, canvas));
        return arrayList;
    }

    public e(xb.l lVar, xb.k kVar, com.google.android.gms.common.api.internal.g0 g0Var) {
        this.f17046i = 1;
        this.f17049n = g0Var;
        this.f17047l = lVar;
        this.f17048m = kVar;
    }
}
