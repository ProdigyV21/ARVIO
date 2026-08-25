package androidx.vectordrawable.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class q {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Matrix f6660p = new Matrix();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f6662b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f6663c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f6664d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f6665e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PathMeasure f6666f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final n f6667g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6668h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f6669i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f6670j;
    public float k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6671l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f6672m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Boolean f6673n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final t.e f6674o;

    public q() {
        this.f6663c = new Matrix();
        this.f6668h = 0.0f;
        this.f6669i = 0.0f;
        this.f6670j = 0.0f;
        this.k = 0.0f;
        this.f6671l = 255;
        this.f6672m = null;
        this.f6673n = null;
        this.f6674o = new t.e(0);
        this.f6667g = new n();
        this.f6661a = new Path();
        this.f6662b = new Path();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(n nVar, Matrix matrix, Canvas canvas, int i10, int i11) {
        int i12;
        float f10;
        int i13;
        float f11;
        Matrix matrix2 = nVar.f6647a;
        ArrayList arrayList = nVar.f6648b;
        matrix2.set(matrix);
        Matrix matrix3 = nVar.f6647a;
        matrix3.preConcat(nVar.f6656j);
        canvas.save();
        char c10 = 0;
        int i14 = 0;
        while (i14 < arrayList.size()) {
            o oVar = (o) arrayList.get(i14);
            if (oVar instanceof n) {
                a((n) oVar, matrix3, canvas, i10, i11);
            } else if (oVar instanceof p) {
                p pVar = (p) oVar;
                float f12 = i10 / this.f6670j;
                float f13 = i11 / this.k;
                float fMin = Math.min(f12, f13);
                Matrix matrix4 = this.f6663c;
                matrix4.set(matrix3);
                matrix4.postScale(f12, f13);
                float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                matrix3.mapVectors(fArr);
                float fHypot = (float) Math.hypot(fArr[c10], fArr[1]);
                boolean z = c10;
                i12 = i14;
                float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                float f14 = (fArr[z ? 1 : 0] * fArr[3]) - (fArr[1] * fArr[2]);
                float fMax = Math.max(fHypot, fHypot2);
                float fAbs = fMax > 0.0f ? Math.abs(f14) / fMax : 0.0f;
                if (fAbs != 0.0f) {
                    Path path = this.f6661a;
                    path.reset();
                    androidx.core.graphics.f[] fVarArr = pVar.f6657a;
                    if (fVarArr != null) {
                        androidx.core.graphics.f.b(fVarArr, path);
                    }
                    Path path2 = this.f6662b;
                    path2.reset();
                    if (pVar instanceof l) {
                        path2.setFillType(pVar.f6659c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        path2.addPath(path, matrix4);
                        canvas.clipPath(path2);
                    } else {
                        m mVar = (m) pVar;
                        float f15 = mVar.f6642i;
                        if (f15 != 0.0f || mVar.f6643j != 1.0f) {
                            float f16 = mVar.k;
                            float f17 = (f15 + f16) % 1.0f;
                            float f18 = (mVar.f6643j + f16) % 1.0f;
                            if (this.f6666f == null) {
                                this.f6666f = new PathMeasure();
                            }
                            this.f6666f.setPath(path, z);
                            float length = this.f6666f.getLength();
                            float f19 = f17 * length;
                            float f20 = f18 * length;
                            path.reset();
                            if (f19 > f20) {
                                this.f6666f.getSegment(f19, length, path, true);
                                f10 = 0.0f;
                                this.f6666f.getSegment(0.0f, f20, path, true);
                            } else {
                                f10 = 0.0f;
                                this.f6666f.getSegment(f19, f20, path, true);
                            }
                            path.rLineTo(f10, f10);
                        }
                        path2.addPath(path, matrix4);
                        i0.d dVar = mVar.f6639f;
                        if (dVar.f15985a == null && dVar.f15987c == 0) {
                            f11 = 255.0f;
                            i13 = 16777215;
                        } else {
                            if (this.f6665e == null) {
                                i13 = 16777215;
                                Paint paint = new Paint(1);
                                this.f6665e = paint;
                                paint.setStyle(Paint.Style.FILL);
                            } else {
                                i13 = 16777215;
                            }
                            Paint paint2 = this.f6665e;
                            Shader shader = dVar.f15985a;
                            if (shader != null) {
                                shader.setLocalMatrix(matrix4);
                                paint2.setShader(shader);
                                paint2.setAlpha(Math.round(mVar.f6641h * 255.0f));
                                f11 = 255.0f;
                            } else {
                                paint2.setShader(null);
                                paint2.setAlpha(255);
                                int i15 = dVar.f15987c;
                                float f21 = mVar.f6641h;
                                PorterDuff.Mode mode = t.f6687t;
                                f11 = 255.0f;
                                paint2.setColor((i15 & i13) | (((int) (Color.alpha(i15) * f21)) << 24));
                            }
                            paint2.setColorFilter(null);
                            path2.setFillType(mVar.f6659c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas.drawPath(path2, paint2);
                        }
                        i0.d dVar2 = mVar.f6637d;
                        if (dVar2.f15985a != null || dVar2.f15987c != 0) {
                            if (this.f6664d == null) {
                                Paint paint3 = new Paint(1);
                                this.f6664d = paint3;
                                paint3.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint4 = this.f6664d;
                            Paint.Join join = mVar.f6645m;
                            if (join != null) {
                                paint4.setStrokeJoin(join);
                            }
                            Paint.Cap cap = mVar.f6644l;
                            if (cap != null) {
                                paint4.setStrokeCap(cap);
                            }
                            paint4.setStrokeMiter(mVar.f6646n);
                            Shader shader2 = dVar2.f15985a;
                            if (shader2 != null) {
                                shader2.setLocalMatrix(matrix4);
                                paint4.setShader(shader2);
                                paint4.setAlpha(Math.round(mVar.f6640g * f11));
                            } else {
                                paint4.setShader(null);
                                paint4.setAlpha(255);
                                int i16 = dVar2.f15987c;
                                float f22 = mVar.f6640g;
                                PorterDuff.Mode mode2 = t.f6687t;
                                paint4.setColor((i16 & i13) | (((int) (Color.alpha(i16) * f22)) << 24));
                            }
                            paint4.setColorFilter(null);
                            paint4.setStrokeWidth(mVar.f6638e * fMin * fAbs);
                            canvas.drawPath(path2, paint4);
                        }
                    }
                }
                i14 = i12 + 1;
                c10 = 0;
            }
            i12 = i14;
            i14 = i12 + 1;
            c10 = 0;
        }
        canvas.restore();
    }

    public float getAlpha() {
        return getRootAlpha() / 255.0f;
    }

    public int getRootAlpha() {
        return this.f6671l;
    }

    public void setAlpha(float f10) {
        setRootAlpha((int) (f10 * 255.0f));
    }

    public void setRootAlpha(int i10) {
        this.f6671l = i10;
    }

    public q(q qVar) {
        this.f6663c = new Matrix();
        this.f6668h = 0.0f;
        this.f6669i = 0.0f;
        this.f6670j = 0.0f;
        this.k = 0.0f;
        this.f6671l = 255;
        this.f6672m = null;
        this.f6673n = null;
        t.e eVar = new t.e(0);
        this.f6674o = eVar;
        this.f6667g = new n(qVar.f6667g, eVar);
        this.f6661a = new Path(qVar.f6661a);
        this.f6662b = new Path(qVar.f6662b);
        this.f6668h = qVar.f6668h;
        this.f6669i = qVar.f6669i;
        this.f6670j = qVar.f6670j;
        this.k = qVar.k;
        this.f6671l = qVar.f6671l;
        this.f6672m = qVar.f6672m;
        String str = qVar.f6672m;
        if (str != null) {
            eVar.put(str, this);
        }
        this.f6673n = qVar.f6673n;
    }
}
