package m1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.l0;
import androidx.recyclerview.widget.p1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f20148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f20149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e f20150e;

    public b(e eVar, int i10, int i11, int i12) {
        this.f20150e = eVar;
        this.f20146a = i10;
        this.f20147b = i12;
        this.f20148c = i11;
        this.f20149d = (f) eVar.f20154m.get(i12);
    }

    @Override // androidx.recyclerview.widget.l0
    public final int getItemCount() {
        f fVar = this.f20149d;
        if (fVar == null) {
            return 0;
        }
        return (fVar.f20168c - fVar.f20167b) + 1;
    }

    @Override // androidx.recyclerview.widget.l0
    public final void onBindViewHolder(p1 p1Var, int i10) {
        f fVar;
        d dVar = (d) p1Var;
        TextView textView = dVar.f20151a;
        if (textView != null && (fVar = this.f20149d) != null) {
            int i11 = fVar.f20167b + i10;
            CharSequence[] charSequenceArr = fVar.f20169d;
            textView.setText(charSequenceArr == null ? String.format(fVar.f20170e, Integer.valueOf(i11)) : charSequenceArr[i11]);
        }
        View view = dVar.itemView;
        e eVar = this.f20150e;
        ArrayList arrayList = eVar.f20153l;
        int i12 = this.f20147b;
        eVar.c(view, ((VerticalGridView) arrayList.get(i12)).getSelectedPosition() == i10, i12, false);
    }

    @Override // androidx.recyclerview.widget.l0
    public final p1 onCreateViewHolder(ViewGroup viewGroup, int i10) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.f20146a, viewGroup, false);
        int i11 = this.f20148c;
        return new d(i11 != 0 ? (TextView) viewInflate.findViewById(i11) : (TextView) viewInflate, viewInflate);
    }

    @Override // androidx.recyclerview.widget.l0
    public final void onViewAttachedToWindow(p1 p1Var) {
        ((d) p1Var).itemView.setFocusable(this.f20150e.isActivated());
    }
}
