package e1;

import android.text.InputFilter;
import android.widget.TextView;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends qb.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f15014a;

    public g(TextView textView) {
        this.f15014a = new f(textView);
    }

    @Override // qb.d
    public final InputFilter[] E(InputFilter[] inputFilterArr) {
        return !o.c() ? inputFilterArr : this.f15014a.E(inputFilterArr);
    }

    @Override // qb.d
    public final void Q(boolean z) {
        if (o.c()) {
            this.f15014a.Q(z);
        }
    }

    @Override // qb.d
    public final void R(boolean z) {
        boolean zC = o.c();
        f fVar = this.f15014a;
        if (zC) {
            fVar.R(z);
        } else {
            fVar.f15013c = z;
        }
    }
}
