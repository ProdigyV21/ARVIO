package androidx.vectordrawable.graphics.drawable;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f6613a = {R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f6614b = {R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f6615c = {R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f6616d = {R.attr.name, R.attr.pathData, R.attr.fillType};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f6617e = {R.attr.drawable};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f6618f = {R.attr.name, R.attr.animation};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f6619g = {R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f6620h = {R.attr.ordering};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f6621i = {R.attr.valueFrom, R.attr.valueTo, R.attr.valueType, R.attr.propertyName};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f6622j = {R.attr.value, R.attr.interpolator, R.attr.valueType, R.attr.fraction};
    public static final int[] k = {R.attr.propertyName, R.attr.pathData, R.attr.propertyXName, R.attr.propertyYName};

    /* JADX WARN: Code restructure failed: missing block: B:205:0x03ad, code lost:
    
        if (r32 == null) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x03af, code lost:
    
        if (r22 == null) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x03b1, code lost:
    
        r2 = new android.animation.Animator[r22.size()];
        r3 = r22.iterator();
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x03c0, code lost:
    
        if (r3.hasNext() == false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x03c2, code lost:
    
        r2[r11] = (android.animation.Animator) r3.next();
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x03ce, code lost:
    
        if (r33 != 0) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x03d0, code lost:
    
        r32.playTogether(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x03d3, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x03d4, code lost:
    
        r32.playSequentially(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x03d7, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        r22 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:196:0x037f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x038f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.animation.Animator a(android.content.Context r27, android.content.res.Resources r28, android.content.res.Resources.Theme r29, org.xmlpull.v1.XmlPullParser r30, android.util.AttributeSet r31, android.animation.AnimatorSet r32, int r33) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.a.a(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int):android.animation.Animator");
    }

    public static PropertyValuesHolder b(TypedArray typedArray, int i10, int i11, int i12, String str) {
        PropertyValuesHolder propertyValuesHolderOfFloat;
        TypedValue typedValuePeekValue = typedArray.peekValue(i11);
        boolean z = typedValuePeekValue != null;
        int i13 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i12);
        boolean z5 = typedValuePeekValue2 != null;
        int i14 = z5 ? typedValuePeekValue2.type : 0;
        if (i10 == 4) {
            i10 = ((z && c(i13)) || (z5 && c(i14))) ? 3 : 0;
        }
        boolean z10 = i10 == 0;
        PropertyValuesHolder propertyValuesHolderOfInt = null;
        if (i10 == 2) {
            String string = typedArray.getString(i11);
            String string2 = typedArray.getString(i12);
            androidx.core.graphics.f[] fVarArrJ = androidx.core.graphics.o.j(string);
            androidx.core.graphics.f[] fVarArrJ2 = androidx.core.graphics.o.j(string2);
            if (fVarArrJ != null || fVarArrJ2 != null) {
                if (fVarArrJ != null) {
                    i iVar = new i();
                    if (fVarArrJ2 == null) {
                        return PropertyValuesHolder.ofObject(str, iVar, fVarArrJ);
                    }
                    if (androidx.core.graphics.o.a(fVarArrJ, fVarArrJ2)) {
                        return PropertyValuesHolder.ofObject(str, iVar, fVarArrJ, fVarArrJ2);
                    }
                    throw new InflateException(androidx.compose.material3.d.n(" Can't morph from ", string, " to ", string2));
                }
                if (fVarArrJ2 != null) {
                    return PropertyValuesHolder.ofObject(str, new i(), fVarArrJ2);
                }
            }
            return null;
        }
        j jVar = i10 == 3 ? j.f6635a : null;
        if (z10) {
            if (z) {
                float dimension = i13 == 5 ? typedArray.getDimension(i11, 0.0f) : typedArray.getFloat(i11, 0.0f);
                if (z5) {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension, i14 == 5 ? typedArray.getDimension(i12, 0.0f) : typedArray.getFloat(i12, 0.0f));
                } else {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension);
                }
            } else {
                propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, i14 == 5 ? typedArray.getDimension(i12, 0.0f) : typedArray.getFloat(i12, 0.0f));
            }
            propertyValuesHolderOfInt = propertyValuesHolderOfFloat;
        } else if (z) {
            int dimension2 = i13 == 5 ? (int) typedArray.getDimension(i11, 0.0f) : c(i13) ? typedArray.getColor(i11, 0) : typedArray.getInt(i11, 0);
            if (z5) {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2, i14 == 5 ? (int) typedArray.getDimension(i12, 0.0f) : c(i14) ? typedArray.getColor(i12, 0) : typedArray.getInt(i12, 0));
            } else {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, dimension2);
            }
        } else if (z5) {
            propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, i14 == 5 ? (int) typedArray.getDimension(i12, 0.0f) : c(i14) ? typedArray.getColor(i12, 0) : typedArray.getInt(i12, 0));
        }
        if (propertyValuesHolderOfInt != null && jVar != null) {
            propertyValuesHolderOfInt.setEvaluator(jVar);
        }
        return propertyValuesHolderOfInt;
    }

    public static boolean c(int i10) {
        return i10 >= 28 && i10 <= 31;
    }

    public static ValueAnimator d(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ObjectAnimator objectAnimator, XmlPullParser xmlPullParser) {
        ValueAnimator valueAnimator;
        int i10;
        ValueAnimator valueAnimator2;
        TypedArray typedArrayG = i0.b.g(resources, theme, attributeSet, f6619g);
        TypedArray typedArrayG2 = i0.b.g(resources, theme, attributeSet, k);
        ValueAnimator valueAnimator3 = objectAnimator == null ? new ValueAnimator() : objectAnimator;
        long j10 = i0.b.d(xmlPullParser, "duration") ? typedArrayG.getInt(1, 300) : 300;
        long j11 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "startOffset") != null ? typedArrayG.getInt(2, 0) : 0;
        int i11 = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueType") != null ? typedArrayG.getInt(7, 4) : 4;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueFrom") != null && xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueTo") != null) {
            if (i11 == 4) {
                TypedValue typedValuePeekValue = typedArrayG.peekValue(5);
                boolean z = typedValuePeekValue != null;
                int i12 = z ? typedValuePeekValue.type : 0;
                TypedValue typedValuePeekValue2 = typedArrayG.peekValue(6);
                boolean z5 = typedValuePeekValue2 != null;
                i11 = ((z && c(i12)) || (z5 && c(z5 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
            }
            PropertyValuesHolder propertyValuesHolderB = b(typedArrayG, i11, 5, 6, "");
            if (propertyValuesHolderB != null) {
                valueAnimator3.setValues(propertyValuesHolderB);
            }
        }
        valueAnimator3.setDuration(j10);
        valueAnimator3.setStartDelay(j11);
        valueAnimator3.setRepeatCount(xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "repeatCount") != null ? typedArrayG.getInt(3, 0) : 0);
        valueAnimator3.setRepeatMode(xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "repeatMode") != null ? typedArrayG.getInt(4, 1) : 1);
        if (typedArrayG2 != null) {
            ObjectAnimator objectAnimator2 = (ObjectAnimator) valueAnimator3;
            String strC = i0.b.c(typedArrayG2, xmlPullParser, "pathData", 1);
            if (strC != null) {
                String strC2 = i0.b.c(typedArrayG2, xmlPullParser, "propertyXName", 2);
                String strC3 = i0.b.c(typedArrayG2, xmlPullParser, "propertyYName", 3);
                if (i11 != 2) {
                }
                if (strC2 == null && strC3 == null) {
                    throw new InflateException(typedArrayG2.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                Path path = new Path();
                try {
                    androidx.core.graphics.f.b(androidx.core.graphics.o.j(strC), path);
                    PathMeasure pathMeasure = new PathMeasure(path, false);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Float.valueOf(0.0f));
                    float length = 0.0f;
                    do {
                        length += pathMeasure.getLength();
                        arrayList.add(Float.valueOf(length));
                    } while (pathMeasure.nextContour());
                    PathMeasure pathMeasure2 = new PathMeasure(path, false);
                    int iMin = Math.min(100, ((int) (length / 0.5f)) + 1);
                    float[] fArr = new float[iMin];
                    float[] fArr2 = new float[iMin];
                    float[] fArr3 = new float[2];
                    float f10 = length / (iMin - 1);
                    int i13 = 0;
                    valueAnimator = valueAnimator3;
                    float f11 = 0.0f;
                    int i14 = 0;
                    while (true) {
                        if (i14 >= iMin) {
                            break;
                        }
                        int i15 = iMin;
                        pathMeasure2.getPosTan(f11 - ((Float) arrayList.get(i13)).floatValue(), fArr3, null);
                        fArr[i14] = fArr3[0];
                        fArr2[i14] = fArr3[1];
                        int i16 = i13 + 1;
                        f11 += f10;
                        if (i16 < arrayList.size() && f11 > ((Float) arrayList.get(i16)).floatValue()) {
                            pathMeasure2.nextContour();
                            i13 = i16;
                        }
                        i14++;
                        iMin = i15;
                    }
                    PropertyValuesHolder propertyValuesHolderOfFloat = strC2 != null ? PropertyValuesHolder.ofFloat(strC2, fArr) : null;
                    PropertyValuesHolder propertyValuesHolderOfFloat2 = strC3 != null ? PropertyValuesHolder.ofFloat(strC3, fArr2) : null;
                    if (propertyValuesHolderOfFloat == null) {
                        objectAnimator2.setValues(propertyValuesHolderOfFloat2);
                    } else if (propertyValuesHolderOfFloat2 == null) {
                        objectAnimator2.setValues(propertyValuesHolderOfFloat);
                    } else {
                        objectAnimator2.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2);
                    }
                    i10 = 0;
                } catch (RuntimeException e5) {
                    throw new RuntimeException("Error in parsing ".concat(strC), e5);
                }
            } else {
                valueAnimator = valueAnimator3;
                i10 = 0;
                objectAnimator2.setPropertyName(i0.b.c(typedArrayG2, xmlPullParser, "propertyName", 0));
            }
        } else {
            valueAnimator = valueAnimator3;
            i10 = 0;
        }
        int resourceId = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "interpolator") != null ? typedArrayG.getResourceId(i10, i10) : i10;
        if (resourceId > 0) {
            valueAnimator2 = valueAnimator;
            valueAnimator2.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        } else {
            valueAnimator2 = valueAnimator;
        }
        typedArrayG.recycle();
        if (typedArrayG2 != null) {
            typedArrayG2.recycle();
        }
        return valueAnimator2;
    }
}
