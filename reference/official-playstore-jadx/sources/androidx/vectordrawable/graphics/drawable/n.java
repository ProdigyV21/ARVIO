package androidx.vectordrawable.graphics.drawable;

import android.graphics.Matrix;
import android.graphics.Paint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class n extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f6647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f6648b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f6649c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f6650d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6651e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f6652f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6653g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6654h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f6655i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Matrix f6656j;
    public String k;

    public n() {
        this.f6647a = new Matrix();
        this.f6648b = new ArrayList();
        this.f6649c = 0.0f;
        this.f6650d = 0.0f;
        this.f6651e = 0.0f;
        this.f6652f = 1.0f;
        this.f6653g = 1.0f;
        this.f6654h = 0.0f;
        this.f6655i = 0.0f;
        this.f6656j = new Matrix();
        this.k = null;
    }

    @Override // androidx.vectordrawable.graphics.drawable.o
    public final boolean a() {
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f6648b;
            if (i10 >= arrayList.size()) {
                return false;
            }
            if (((o) arrayList.get(i10)).a()) {
                return true;
            }
            i10++;
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.o
    public final boolean b(int[] iArr) {
        int i10 = 0;
        boolean zB = false;
        while (true) {
            ArrayList arrayList = this.f6648b;
            if (i10 >= arrayList.size()) {
                return zB;
            }
            zB |= ((o) arrayList.get(i10)).b(iArr);
            i10++;
        }
    }

    public final void c() {
        Matrix matrix = this.f6656j;
        matrix.reset();
        matrix.postTranslate(-this.f6650d, -this.f6651e);
        matrix.postScale(this.f6652f, this.f6653g);
        matrix.postRotate(this.f6649c, 0.0f, 0.0f);
        matrix.postTranslate(this.f6654h + this.f6650d, this.f6655i + this.f6651e);
    }

    public String getGroupName() {
        return this.k;
    }

    public Matrix getLocalMatrix() {
        return this.f6656j;
    }

    public float getPivotX() {
        return this.f6650d;
    }

    public float getPivotY() {
        return this.f6651e;
    }

    public float getRotation() {
        return this.f6649c;
    }

    public float getScaleX() {
        return this.f6652f;
    }

    public float getScaleY() {
        return this.f6653g;
    }

    public float getTranslateX() {
        return this.f6654h;
    }

    public float getTranslateY() {
        return this.f6655i;
    }

    public void setPivotX(float f10) {
        if (f10 != this.f6650d) {
            this.f6650d = f10;
            c();
        }
    }

    public void setPivotY(float f10) {
        if (f10 != this.f6651e) {
            this.f6651e = f10;
            c();
        }
    }

    public void setRotation(float f10) {
        if (f10 != this.f6649c) {
            this.f6649c = f10;
            c();
        }
    }

    public void setScaleX(float f10) {
        if (f10 != this.f6652f) {
            this.f6652f = f10;
            c();
        }
    }

    public void setScaleY(float f10) {
        if (f10 != this.f6653g) {
            this.f6653g = f10;
            c();
        }
    }

    public void setTranslateX(float f10) {
        if (f10 != this.f6654h) {
            this.f6654h = f10;
            c();
        }
    }

    public void setTranslateY(float f10) {
        if (f10 != this.f6655i) {
            this.f6655i = f10;
            c();
        }
    }

    public n(n nVar, t.e eVar) {
        p lVar;
        this.f6647a = new Matrix();
        this.f6648b = new ArrayList();
        this.f6649c = 0.0f;
        this.f6650d = 0.0f;
        this.f6651e = 0.0f;
        this.f6652f = 1.0f;
        this.f6653g = 1.0f;
        this.f6654h = 0.0f;
        this.f6655i = 0.0f;
        Matrix matrix = new Matrix();
        this.f6656j = matrix;
        this.k = null;
        this.f6649c = nVar.f6649c;
        this.f6650d = nVar.f6650d;
        this.f6651e = nVar.f6651e;
        this.f6652f = nVar.f6652f;
        this.f6653g = nVar.f6653g;
        this.f6654h = nVar.f6654h;
        this.f6655i = nVar.f6655i;
        String str = nVar.k;
        this.k = str;
        if (str != null) {
            eVar.put(str, this);
        }
        matrix.set(nVar.f6656j);
        ArrayList arrayList = nVar.f6648b;
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            Object obj = arrayList.get(i10);
            if (obj instanceof n) {
                this.f6648b.add(new n((n) obj, eVar));
            } else {
                if (obj instanceof m) {
                    m mVar = (m) obj;
                    m mVar2 = new m(mVar);
                    mVar2.f6638e = 0.0f;
                    mVar2.f6640g = 1.0f;
                    mVar2.f6641h = 1.0f;
                    mVar2.f6642i = 0.0f;
                    mVar2.f6643j = 1.0f;
                    mVar2.k = 0.0f;
                    mVar2.f6644l = Paint.Cap.BUTT;
                    mVar2.f6645m = Paint.Join.MITER;
                    mVar2.f6646n = 4.0f;
                    mVar2.f6637d = mVar.f6637d;
                    mVar2.f6638e = mVar.f6638e;
                    mVar2.f6640g = mVar.f6640g;
                    mVar2.f6639f = mVar.f6639f;
                    mVar2.f6659c = mVar.f6659c;
                    mVar2.f6641h = mVar.f6641h;
                    mVar2.f6642i = mVar.f6642i;
                    mVar2.f6643j = mVar.f6643j;
                    mVar2.k = mVar.k;
                    mVar2.f6644l = mVar.f6644l;
                    mVar2.f6645m = mVar.f6645m;
                    mVar2.f6646n = mVar.f6646n;
                    lVar = mVar2;
                } else if (obj instanceof l) {
                    lVar = new l((l) obj);
                } else {
                    throw new IllegalStateException("Unknown object in the tree!");
                }
                this.f6648b.add(lVar);
                Object obj2 = lVar.f6658b;
                if (obj2 != null) {
                    eVar.put(obj2, lVar);
                }
            }
        }
    }
}
