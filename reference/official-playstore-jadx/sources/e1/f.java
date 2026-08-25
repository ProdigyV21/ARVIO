package e1;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends qb.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f15011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f15012b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f15013c = true;

    public f(TextView textView) {
        this.f15011a = textView;
        this.f15012b = new d(textView);
    }

    @Override // qb.d
    public final InputFilter[] E(InputFilter[] inputFilterArr) {
        if (!this.f15013c) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i10 = 0; i10 < inputFilterArr.length; i10++) {
                InputFilter inputFilter = inputFilterArr[i10];
                if (inputFilter instanceof d) {
                    sparseArray.put(i10, inputFilter);
                }
            }
            if (sparseArray.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
            int i11 = 0;
            for (int i12 = 0; i12 < length; i12++) {
                if (sparseArray.indexOfKey(i12) < 0) {
                    inputFilterArr2[i11] = inputFilterArr[i12];
                    i11++;
                }
            }
            return inputFilterArr2;
        }
        int length2 = inputFilterArr.length;
        int i13 = 0;
        while (true) {
            d dVar = this.f15012b;
            if (i13 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = dVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i13] == dVar) {
                return inputFilterArr;
            }
            i13++;
        }
    }

    @Override // qb.d
    public final void Q(boolean z) {
        if (z) {
            Z();
        }
    }

    @Override // qb.d
    public final void R(boolean z) {
        this.f15013c = z;
        Z();
        TextView textView = this.f15011a;
        textView.setFilters(E(textView.getFilters()));
    }

    public final void Z() {
        TextView textView = this.f15011a;
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (this.f15013c) {
            if (!(transformationMethod instanceof j) && !(transformationMethod instanceof PasswordTransformationMethod)) {
                transformationMethod = new j(transformationMethod);
            }
        } else if (transformationMethod instanceof j) {
            transformationMethod = ((j) transformationMethod).f15019i;
        }
        textView.setTransformationMethod(transformationMethod);
    }
}
