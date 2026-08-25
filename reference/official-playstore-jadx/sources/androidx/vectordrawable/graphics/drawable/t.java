package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import com.google.common.util.concurrent.r0;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import t.l0;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final PorterDuff.Mode f6687t = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r f6688l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PorterDuffColorFilter f6689m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorFilter f6690n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f6691o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6692p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f6693q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Matrix f6694r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f6695s;

    public t() {
        this.f6692p = true;
        this.f6693q = new float[9];
        this.f6694r = new Matrix();
        this.f6695s = new Rect();
        r rVar = new r();
        rVar.f6677c = null;
        rVar.f6678d = f6687t;
        rVar.f6676b = new q();
        this.f6688l = rVar;
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f6636i;
        if (drawable == null) {
            return false;
        }
        j0.a.b(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.f6695s;
        copyBounds(rect);
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f6690n;
        if (colorFilter == null) {
            colorFilter = this.f6689m;
        }
        Matrix matrix = this.f6694r;
        canvas.getMatrix(matrix);
        float[] fArr = this.f6693q;
        matrix.getValues(fArr);
        float fAbs = Math.abs(fArr[0]);
        float fAbs2 = Math.abs(fArr[4]);
        float fAbs3 = Math.abs(fArr[1]);
        float fAbs4 = Math.abs(fArr[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iWidth = (int) (rect.width() * fAbs);
        int iMin = Math.min(2048, iWidth);
        int iMin2 = Math.min(2048, (int) (rect.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(rect.left, rect.top);
        if (isAutoMirrored() && j0.b.a(this) == 1) {
            canvas.translate(rect.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        rect.offsetTo(0, 0);
        r rVar = this.f6688l;
        Bitmap bitmap = rVar.f6680f;
        if (bitmap == null || iMin != bitmap.getWidth() || iMin2 != rVar.f6680f.getHeight()) {
            rVar.f6680f = Bitmap.createBitmap(iMin, iMin2, Bitmap.Config.ARGB_8888);
            rVar.k = true;
        }
        if (this.f6692p) {
            r rVar2 = this.f6688l;
            if (rVar2.k || rVar2.f6681g != rVar2.f6677c || rVar2.f6682h != rVar2.f6678d || rVar2.f6684j != rVar2.f6679e || rVar2.f6683i != rVar2.f6676b.getRootAlpha()) {
                r rVar3 = this.f6688l;
                rVar3.f6680f.eraseColor(0);
                Canvas canvas2 = new Canvas(rVar3.f6680f);
                q qVar = rVar3.f6676b;
                qVar.a(qVar.f6667g, q.f6660p, canvas2, iMin, iMin2);
                r rVar4 = this.f6688l;
                rVar4.f6681g = rVar4.f6677c;
                rVar4.f6682h = rVar4.f6678d;
                rVar4.f6683i = rVar4.f6676b.getRootAlpha();
                rVar4.f6684j = rVar4.f6679e;
                rVar4.k = false;
            }
        } else {
            r rVar5 = this.f6688l;
            rVar5.f6680f.eraseColor(0);
            Canvas canvas3 = new Canvas(rVar5.f6680f);
            q qVar2 = rVar5.f6676b;
            qVar2.a(qVar2.f6667g, q.f6660p, canvas3, iMin, iMin2);
        }
        r rVar6 = this.f6688l;
        if (rVar6.f6676b.getRootAlpha() >= 255 && colorFilter == null) {
            paint = null;
        } else {
            if (rVar6.f6685l == null) {
                Paint paint2 = new Paint();
                rVar6.f6685l = paint2;
                paint2.setFilterBitmap(true);
            }
            rVar6.f6685l.setAlpha(rVar6.f6676b.getRootAlpha());
            rVar6.f6685l.setColorFilter(colorFilter);
            paint = rVar6.f6685l;
        }
        canvas.drawBitmap(rVar6.f6680f, (Rect) null, rect, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getAlpha() : this.f6688l.f6676b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f6688l.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f6636i;
        return drawable != null ? j0.a.c(drawable) : this.f6690n;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f6636i != null && Build.VERSION.SDK_INT >= 24) {
            return new s(this.f6636i.getConstantState());
        }
        this.f6688l.f6675a = getChangingConfigurations();
        return this.f6688l;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f6688l.f6676b.f6669i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f6688l.f6676b.f6668h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.isAutoMirrored() : this.f6688l.f6679e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        r rVar = this.f6688l;
        if (rVar == null) {
            return false;
        }
        q qVar = rVar.f6676b;
        if (qVar.f6673n == null) {
            qVar.f6673n = Boolean.valueOf(qVar.f6667g.a());
        }
        if (qVar.f6673n.booleanValue()) {
            return true;
        }
        ColorStateList colorStateList = this.f6688l.f6677c;
        return colorStateList != null && colorStateList.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f6691o && super.mutate() == this) {
            r rVar = this.f6688l;
            r rVar2 = new r();
            rVar2.f6677c = null;
            rVar2.f6678d = f6687t;
            if (rVar != null) {
                rVar2.f6675a = rVar.f6675a;
                q qVar = new q(rVar.f6676b);
                rVar2.f6676b = qVar;
                if (rVar.f6676b.f6665e != null) {
                    qVar.f6665e = new Paint(rVar.f6676b.f6665e);
                }
                if (rVar.f6676b.f6664d != null) {
                    rVar2.f6676b.f6664d = new Paint(rVar.f6676b.f6664d);
                }
                rVar2.f6677c = rVar.f6677c;
                rVar2.f6678d = rVar.f6678d;
                rVar2.f6679e = rVar.f6679e;
            }
            this.f6688l = rVar2;
            this.f6691o = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        r rVar = this.f6688l;
        ColorStateList colorStateList = rVar.f6677c;
        if (colorStateList == null || (mode = rVar.f6678d) == null) {
            z = false;
        } else {
            this.f6689m = a(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        q qVar = rVar.f6676b;
        if (qVar.f6673n == null) {
            qVar.f6673n = Boolean.valueOf(qVar.f6667g.a());
        }
        if (qVar.f6673n.booleanValue()) {
            boolean zB = rVar.f6676b.f6667g.b(iArr);
            rVar.k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j10) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j10);
        } else {
            super.scheduleSelf(runnable, j10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i10) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setAlpha(i10);
        } else if (this.f6688l.f6676b.getRootAlpha() != i10) {
            this.f6688l.f6676b.setRootAlpha(i10);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.f6688l.f6679e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6690n = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i10) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            r0.E(drawable, i10);
        } else {
            setTintList(ColorStateList.valueOf(i10));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.h(drawable, colorStateList);
            return;
        }
        r rVar = this.f6688l;
        if (rVar.f6677c != colorStateList) {
            rVar.f6677c = colorStateList;
            this.f6689m = a(colorStateList, rVar.f6678d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.i(drawable, mode);
            return;
        }
        r rVar = this.f6688l;
        if (rVar.f6678d != mode) {
            rVar.f6678d = mode;
            this.f6689m = a(rVar.f6677c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z5) {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.setVisible(z, z5) : super.setVisible(z, z5);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        l0 l0Var;
        ArrayDeque arrayDeque;
        int i10;
        boolean z;
        int i11;
        int i12;
        int i13;
        ArrayDeque arrayDeque2;
        l0 l0Var2;
        char c10;
        int i14;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        Resources.Theme theme2 = theme;
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.d(drawable, resources, xmlPullParser2, attributeSet, theme2);
            return;
        }
        r rVar = this.f6688l;
        rVar.f6676b = new q();
        TypedArray typedArrayG = i0.b.g(resources, theme2, attributeSet, a.f6613a);
        r rVar2 = this.f6688l;
        q qVar = rVar2.f6676b;
        int i15 = !i0.b.d(xmlPullParser2, "tintMode") ? -1 : typedArrayG.getInt(6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        int i16 = 3;
        if (i15 == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (i15 != 5) {
            if (i15 != 9) {
                switch (i15) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case 15:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        rVar2.f6678d = mode;
        int i17 = 1;
        ColorStateList colorStateListA = i0.b.a(typedArrayG, xmlPullParser2, theme2, "tint", 1);
        if (colorStateListA != null) {
            rVar2.f6677c = colorStateListA;
        }
        boolean z5 = rVar2.f6679e;
        if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "autoMirrored") != null) {
            z5 = typedArrayG.getBoolean(5, z5);
        }
        rVar2.f6679e = z5;
        float f10 = qVar.f6670j;
        if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportWidth") != null) {
            f10 = typedArrayG.getFloat(7, f10);
        }
        qVar.f6670j = f10;
        float f11 = qVar.k;
        if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportHeight") != null) {
            f11 = typedArrayG.getFloat(8, f11);
        }
        qVar.k = f11;
        boolean z10 = false;
        if (qVar.f6670j <= 0.0f) {
            throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f11 > 0.0f) {
            qVar.f6668h = typedArrayG.getDimension(3, qVar.f6668h);
            int i18 = 2;
            float dimension = typedArrayG.getDimension(2, qVar.f6669i);
            qVar.f6669i = dimension;
            if (qVar.f6668h <= 0.0f) {
                throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                float alpha = qVar.getAlpha();
                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "alpha") != null) {
                    alpha = typedArrayG.getFloat(4, alpha);
                }
                qVar.setAlpha(alpha);
                String string = typedArrayG.getString(0);
                if (string != null) {
                    qVar.f6672m = string;
                    qVar.f6674o.put(string, qVar);
                }
                typedArrayG.recycle();
                rVar.f6675a = getChangingConfigurations();
                rVar.k = true;
                r rVar3 = this.f6688l;
                q qVar2 = rVar3.f6676b;
                ArrayDeque arrayDeque3 = new ArrayDeque();
                n nVar = qVar2.f6667g;
                l0 l0Var3 = qVar2.f6674o;
                arrayDeque3.push(nVar);
                int eventType = xmlPullParser2.getEventType();
                int depth = xmlPullParser2.getDepth() + 1;
                int next = eventType;
                boolean z11 = true;
                while (next != i17 && (xmlPullParser2.getDepth() >= depth || next != i16)) {
                    if (next == i18) {
                        String name = xmlPullParser2.getName();
                        n nVar2 = (n) arrayDeque3.peek();
                        i10 = depth;
                        if ("path".equals(name)) {
                            m mVar = new m();
                            mVar.f6638e = 0.0f;
                            mVar.f6640g = 1.0f;
                            mVar.f6641h = 1.0f;
                            mVar.f6642i = 0.0f;
                            mVar.f6643j = 1.0f;
                            mVar.k = 0.0f;
                            Paint.Cap cap = Paint.Cap.BUTT;
                            mVar.f6644l = cap;
                            Paint.Join join = Paint.Join.MITER;
                            mVar.f6645m = join;
                            l0 l0Var4 = l0Var3;
                            mVar.f6646n = 4.0f;
                            TypedArray typedArrayG2 = i0.b.g(resources, theme2, attributeSet, a.f6615c);
                            if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                String string2 = typedArrayG2.getString(0);
                                if (string2 != null) {
                                    mVar.f6658b = string2;
                                }
                                String string3 = typedArrayG2.getString(2);
                                if (string3 != null) {
                                    mVar.f6657a = androidx.core.graphics.o.j(string3);
                                }
                                arrayDeque2 = arrayDeque3;
                                l0Var2 = l0Var4;
                                xmlPullParser2 = xmlPullParser;
                                mVar.f6639f = i0.b.b(typedArrayG2, xmlPullParser2, theme2, "fillColor", 1, 0);
                                float f12 = mVar.f6641h;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "fillAlpha") != null) {
                                    f12 = typedArrayG2.getFloat(12, f12);
                                }
                                mVar.f6641h = f12;
                                int i19 = xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineCap") != null ? typedArrayG2.getInt(8, -1) : -1;
                                Paint.Cap cap2 = mVar.f6644l;
                                if (i19 == 0) {
                                    cap2 = cap;
                                } else if (i19 == 1) {
                                    cap2 = Paint.Cap.ROUND;
                                } else if (i19 == 2) {
                                    cap2 = Paint.Cap.SQUARE;
                                }
                                mVar.f6644l = cap2;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineJoin") != null) {
                                    c10 = '\t';
                                    i14 = typedArrayG2.getInt(9, -1);
                                } else {
                                    c10 = '\t';
                                    i14 = -1;
                                }
                                Paint.Join join2 = mVar.f6645m;
                                if (i14 != 0) {
                                    if (i14 != 1) {
                                        join = i14 != 2 ? join2 : Paint.Join.BEVEL;
                                    } else {
                                        join = Paint.Join.ROUND;
                                    }
                                }
                                mVar.f6645m = join;
                                float f13 = mVar.f6646n;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeMiterLimit") != null) {
                                    f13 = typedArrayG2.getFloat(10, f13);
                                }
                                mVar.f6646n = f13;
                                theme2 = theme;
                                mVar.f6637d = i0.b.b(typedArrayG2, xmlPullParser2, theme2, "strokeColor", 3, 0);
                                float f14 = mVar.f6640g;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeAlpha") != null) {
                                    f14 = typedArrayG2.getFloat(11, f14);
                                }
                                mVar.f6640g = f14;
                                float f15 = mVar.f6638e;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeWidth") != null) {
                                    f15 = typedArrayG2.getFloat(4, f15);
                                }
                                mVar.f6638e = f15;
                                float f16 = mVar.f6643j;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathEnd") != null) {
                                    f16 = typedArrayG2.getFloat(6, f16);
                                }
                                mVar.f6643j = f16;
                                float f17 = mVar.k;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathOffset") != null) {
                                    f17 = typedArrayG2.getFloat(7, f17);
                                }
                                mVar.k = f17;
                                float f18 = mVar.f6642i;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathStart") != null) {
                                    f18 = typedArrayG2.getFloat(5, f18);
                                }
                                mVar.f6642i = f18;
                                int i20 = mVar.f6659c;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "fillType") != null) {
                                    i20 = typedArrayG2.getInt(13, i20);
                                }
                                mVar.f6659c = i20;
                            } else {
                                arrayDeque2 = arrayDeque3;
                                l0Var2 = l0Var4;
                            }
                            typedArrayG2.recycle();
                            nVar2.f6648b.add(mVar);
                            if (mVar.getPathName() != null) {
                                l0Var2.put(mVar.getPathName(), mVar);
                            }
                            rVar3.f6675a = rVar3.f6675a;
                            l0Var = l0Var2;
                            arrayDeque = arrayDeque2;
                            i13 = 1;
                            z11 = false;
                            z = false;
                        } else {
                            ArrayDeque arrayDeque4 = arrayDeque3;
                            l0Var = l0Var3;
                            arrayDeque = arrayDeque4;
                            z = false;
                            if ("clip-path".equals(name)) {
                                l lVar = new l();
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null) {
                                    TypedArray typedArrayG3 = i0.b.g(resources, theme2, attributeSet, a.f6616d);
                                    String string4 = typedArrayG3.getString(0);
                                    if (string4 != null) {
                                        lVar.f6658b = string4;
                                    }
                                    String string5 = typedArrayG3.getString(1);
                                    if (string5 != null) {
                                        lVar.f6657a = androidx.core.graphics.o.j(string5);
                                    }
                                    lVar.f6659c = !i0.b.d(xmlPullParser2, "fillType") ? 0 : typedArrayG3.getInt(2, 0);
                                    typedArrayG3.recycle();
                                }
                                nVar2.f6648b.add(lVar);
                                if (lVar.getPathName() != null) {
                                    l0Var.put(lVar.getPathName(), lVar);
                                }
                                rVar3.f6675a = rVar3.f6675a;
                            } else if ("group".equals(name)) {
                                n nVar3 = new n();
                                TypedArray typedArrayG4 = i0.b.g(resources, theme2, attributeSet, a.f6614b);
                                float f19 = nVar3.f6649c;
                                if (i0.b.d(xmlPullParser2, "rotation")) {
                                    f19 = typedArrayG4.getFloat(5, f19);
                                }
                                nVar3.f6649c = f19;
                                i13 = 1;
                                nVar3.f6650d = typedArrayG4.getFloat(1, nVar3.f6650d);
                                nVar3.f6651e = typedArrayG4.getFloat(2, nVar3.f6651e);
                                float f20 = nVar3.f6652f;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleX") != null) {
                                    f20 = typedArrayG4.getFloat(3, f20);
                                }
                                nVar3.f6652f = f20;
                                float f21 = nVar3.f6653g;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleY") != null) {
                                    f21 = typedArrayG4.getFloat(4, f21);
                                }
                                nVar3.f6653g = f21;
                                float f22 = nVar3.f6654h;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "translateX") != null) {
                                    f22 = typedArrayG4.getFloat(6, f22);
                                }
                                nVar3.f6654h = f22;
                                float f23 = nVar3.f6655i;
                                if (xmlPullParser2.getAttributeValue("http://schemas.android.com/apk/res/android", "translateY") != null) {
                                    f23 = typedArrayG4.getFloat(7, f23);
                                }
                                nVar3.f6655i = f23;
                                String string6 = typedArrayG4.getString(0);
                                if (string6 != null) {
                                    nVar3.k = string6;
                                }
                                nVar3.c();
                                typedArrayG4.recycle();
                                nVar2.f6648b.add(nVar3);
                                arrayDeque.push(nVar3);
                                if (nVar3.getGroupName() != null) {
                                    l0Var.put(nVar3.getGroupName(), nVar3);
                                }
                                rVar3.f6675a = rVar3.f6675a;
                            }
                            i13 = 1;
                        }
                        i12 = i13;
                        i11 = 3;
                    } else {
                        ArrayDeque arrayDeque5 = arrayDeque3;
                        l0Var = l0Var3;
                        arrayDeque = arrayDeque5;
                        i10 = depth;
                        z = z10;
                        i11 = i16;
                        i12 = 1;
                        if (next == i11 && "group".equals(xmlPullParser2.getName())) {
                            arrayDeque.pop();
                        }
                    }
                    next = xmlPullParser2.next();
                    l0 l0Var5 = l0Var;
                    arrayDeque3 = arrayDeque;
                    l0Var3 = l0Var5;
                    i16 = i11;
                    i17 = i12;
                    depth = i10;
                    z10 = z;
                    i18 = 2;
                }
                if (!z11) {
                    this.f6689m = a(rVar.f6677c, rVar.f6678d);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            }
            throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArrayG.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public t(r rVar) {
        this.f6692p = true;
        this.f6693q = new float[9];
        this.f6694r = new Matrix();
        this.f6695s = new Rect();
        this.f6688l = rVar;
        this.f6689m = a(rVar.f6677c, rVar.f6678d);
    }
}
