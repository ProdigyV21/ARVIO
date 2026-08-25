package e1;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements TransformationMethod {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TransformationMethod f15019i;

    public j(TransformationMethod transformationMethod) {
        this.f15019i = transformationMethod;
    }

    @Override // android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.f15019i;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        if (charSequence == null || o.a().b() != 1) {
            return charSequence;
        }
        o oVarA = o.a();
        oVarA.getClass();
        return oVarA.f(charSequence, 0, charSequence.length());
    }

    @Override // android.text.method.TransformationMethod
    public final void onFocusChanged(View view, CharSequence charSequence, boolean z, int i10, Rect rect) {
        TransformationMethod transformationMethod = this.f15019i;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z, i10, rect);
        }
    }
}
