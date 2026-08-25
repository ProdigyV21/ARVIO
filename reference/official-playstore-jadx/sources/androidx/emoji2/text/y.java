package androidx.emoji2.text;

import android.media.VolumeProvider;
import android.os.Build;
import androidx.appcompat.app.i1;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2653c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f2655e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f2656f;

    public y(i1 i1Var, int i10, int i11, int i12, String str) {
        this.f2656f = i1Var;
        this.f2651a = i10;
        this.f2652b = i11;
        this.f2653c = i12;
        this.f2654d = str;
    }

    public VolumeProvider a() {
        y yVar;
        if (((VolumeProvider) this.f2655e) != null) {
            yVar = this;
        } else if (Build.VERSION.SDK_INT >= 30) {
            yVar = this;
            yVar.f2655e = new androidx.media.i(yVar, this.f2651a, this.f2652b, this.f2653c, (String) this.f2654d);
        } else {
            yVar = this;
            yVar.f2655e = new androidx.media.j(this, yVar.f2651a, yVar.f2652b, yVar.f2653c);
        }
        return (VolumeProvider) yVar.f2655e;
    }

    public void b() {
        this.f2651a = 1;
        this.f2655e = (d0) this.f2654d;
        this.f2653c = 0;
    }

    public boolean c() {
        androidx.emoji2.text.flatbuffer.a aVarB = ((d0) this.f2655e).f2598b.b();
        int iA = aVarB.a(6);
        return !(iA == 0 || ((ByteBuffer) aVarB.f2241n).get(iA + aVarB.f2238i) == 0) || this.f2652b == 65039;
    }

    public y(d0 d0Var) {
        this.f2651a = 1;
        this.f2654d = d0Var;
        this.f2655e = d0Var;
    }
}
