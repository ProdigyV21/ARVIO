package androidx.appcompat.widget;

import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextClassifier;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class m1 implements l1, androidx.appcompat.view.menu.o {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ View f1665i;

    public /* synthetic */ m1(View view) {
        this.f1665i = view;
    }

    @Override // androidx.appcompat.widget.l1
    public void a(int[] iArr, int i10) {
        super/*android.widget.TextView*/.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i10);
    }

    @Override // androidx.appcompat.view.menu.o
    public boolean b(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        return false;
    }

    @Override // androidx.appcompat.widget.l1
    public void c(int i10) {
    }

    @Override // androidx.appcompat.widget.l1
    public int d() {
        return super/*android.widget.TextView*/.getAutoSizeTextType();
    }

    @Override // androidx.appcompat.widget.l1
    public int e() {
        return super/*android.widget.TextView*/.getAutoSizeMinTextSize();
    }

    @Override // androidx.appcompat.view.menu.o
    public void f(androidx.appcompat.view.menu.q qVar) {
        Toolbar toolbar = (Toolbar) this.f1665i;
        m mVar = toolbar.f1480i.D;
        if (mVar == null || !mVar.k()) {
            Iterator it = toolbar.Q.f2230b.iterator();
            while (it.hasNext()) {
                ((androidx.core.view.i0) it.next()).b(qVar);
            }
        }
        androidx.appcompat.app.e1 e1Var = toolbar.b0;
        if (e1Var != null) {
            e1Var.f(qVar);
        }
    }

    @Override // androidx.appcompat.widget.l1
    public int[] g() {
        return super/*android.widget.TextView*/.getAutoSizeTextAvailableSizes();
    }

    @Override // androidx.appcompat.widget.l1
    public TextClassifier h() {
        return super/*android.widget.TextView*/.getTextClassifier();
    }

    @Override // androidx.appcompat.widget.l1
    public int i() {
        return super/*android.widget.TextView*/.getAutoSizeMaxTextSize();
    }

    @Override // androidx.appcompat.widget.l1
    public void j(TextClassifier textClassifier) {
        super/*android.widget.TextView*/.setTextClassifier(textClassifier);
    }

    @Override // androidx.appcompat.widget.l1
    public void k(int i10, int i11, int i12, int i13) {
        super/*android.widget.TextView*/.setAutoSizeTextTypeUniformWithConfiguration(i10, i11, i12, i13);
    }

    @Override // androidx.appcompat.widget.l1
    public void l(int i10) {
    }

    @Override // androidx.appcompat.widget.l1
    public int m() {
        return super/*android.widget.TextView*/.getAutoSizeStepGranularity();
    }

    @Override // androidx.appcompat.widget.l1
    public void n(int i10) {
        super/*android.widget.TextView*/.setAutoSizeTextTypeWithDefaults(i10);
    }
}
