package androidx.appcompat.widget;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsSeekBar;
import android.widget.EditText;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public class j0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1603d = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1604a = 2;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1606c;

    public /* synthetic */ j0() {
    }

    public KeyListener a(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        ((io.sentry.internal.debugmeta.c) ((a8.e) this.f1606c).f183l).getClass();
        if (keyListener instanceof e1.e) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new e1.e(keyListener);
    }

    public void b(AttributeSet attributeSet, int i10) {
        switch (this.f1604a) {
            case 0:
                AbsSeekBar absSeekBar = (AbsSeekBar) this.f1605b;
                androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(absSeekBar.getContext(), attributeSet, f1603d, i10);
                Drawable drawableU = i1VarZ.U(0);
                if (drawableU != null) {
                    if (drawableU instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) drawableU;
                        int numberOfFrames = animationDrawable.getNumberOfFrames();
                        AnimationDrawable animationDrawable2 = new AnimationDrawable();
                        animationDrawable2.setOneShot(animationDrawable.isOneShot());
                        for (int i11 = 0; i11 < numberOfFrames; i11++) {
                            Drawable drawableE = e(animationDrawable.getFrame(i11), true);
                            drawableE.setLevel(10000);
                            animationDrawable2.addFrame(drawableE, animationDrawable.getDuration(i11));
                        }
                        animationDrawable2.setLevel(10000);
                        drawableU = animationDrawable2;
                    }
                    absSeekBar.setIndeterminateDrawable(drawableU);
                }
                Drawable drawableU2 = i1VarZ.U(1);
                if (drawableU2 != null) {
                    absSeekBar.setProgressDrawable(e(drawableU2, false));
                }
                i1VarZ.b0();
                return;
            default:
                TypedArray typedArrayObtainStyledAttributes = ((EditText) this.f1605b).getContext().obtainStyledAttributes(attributeSet, j.a.f19025i, i10, 0);
                try {
                    boolean z = true;
                    if (typedArrayObtainStyledAttributes.hasValue(14)) {
                        z = typedArrayObtainStyledAttributes.getBoolean(14, true);
                        break;
                    }
                    typedArrayObtainStyledAttributes.recycle();
                    d(z);
                    return;
                } catch (Throwable th) {
                    typedArrayObtainStyledAttributes.recycle();
                    throw th;
                }
        }
    }

    public e1.b c(InputConnection inputConnection, EditorInfo editorInfo) {
        a8.e eVar = (a8.e) this.f1606c;
        if (inputConnection == null) {
            eVar.getClass();
            inputConnection = null;
        } else {
            io.sentry.internal.debugmeta.c cVar = (io.sentry.internal.debugmeta.c) eVar.f183l;
            cVar.getClass();
            if (!(inputConnection instanceof e1.b)) {
                inputConnection = new e1.b((EditText) cVar.f17367l, inputConnection, editorInfo);
            }
        }
        return (e1.b) inputConnection;
    }

    public void d(boolean z) {
        e1.i iVar = (e1.i) ((io.sentry.internal.debugmeta.c) ((a8.e) this.f1606c).f183l).f17368m;
        if (iVar.f15018m != z) {
            if (iVar.f15017l != null) {
                androidx.emoji2.text.o oVarA = androidx.emoji2.text.o.a();
                e1.h hVar = iVar.f15017l;
                oVarA.getClass();
                p0.g.b(hVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = oVarA.f2627a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    oVarA.f2628b.remove(hVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            iVar.f15018m = z;
            if (z) {
                e1.i.a(iVar.f15016i, androidx.emoji2.text.o.a().b());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable e(Drawable drawable, boolean z) {
        if (drawable instanceof j0.h) {
            j0.h hVar = (j0.h) drawable;
            Drawable drawableA = hVar.a();
            if (drawableA != null) {
                hVar.b(e(drawableA, z));
                return drawable;
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i10 = 0; i10 < numberOfLayers; i10++) {
                    int id = layerDrawable.getId(i10);
                    drawableArr[i10] = e(layerDrawable.getDrawable(i10), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i11 = 0; i11 < numberOfLayers; i11++) {
                    layerDrawable2.setId(i11, layerDrawable.getId(i11));
                    layerDrawable2.setLayerGravity(i11, layerDrawable.getLayerGravity(i11));
                    layerDrawable2.setLayerWidth(i11, layerDrawable.getLayerWidth(i11));
                    layerDrawable2.setLayerHeight(i11, layerDrawable.getLayerHeight(i11));
                    layerDrawable2.setLayerInsetLeft(i11, layerDrawable.getLayerInsetLeft(i11));
                    layerDrawable2.setLayerInsetRight(i11, layerDrawable.getLayerInsetRight(i11));
                    layerDrawable2.setLayerInsetTop(i11, layerDrawable.getLayerInsetTop(i11));
                    layerDrawable2.setLayerInsetBottom(i11, layerDrawable.getLayerInsetBottom(i11));
                    layerDrawable2.setLayerInsetStart(i11, layerDrawable.getLayerInsetStart(i11));
                    layerDrawable2.setLayerInsetEnd(i11, layerDrawable.getLayerInsetEnd(i11));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (((Bitmap) this.f1606c) == null) {
                    this.f1606c = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }

    public j0(AbsSeekBar absSeekBar) {
        this.f1605b = absSeekBar;
    }

    public j0(EditText editText) {
        this.f1605b = editText;
        this.f1606c = new a8.e(editText);
    }
}
