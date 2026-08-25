package androidx.recyclerview.widget;

import android.os.Trace;
import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class l0 {
    private final m0 mObservable = new m0();
    private boolean mHasStableIds = false;
    private k0 mStateRestorationPolicy = k0.f4966i;

    public final void bindViewHolder(p1 p1Var, int i10) {
        boolean z = p1Var.mBindingAdapter == null;
        if (z) {
            p1Var.mPosition = i10;
            if (hasStableIds()) {
                p1Var.mItemId = getItemId(i10);
            }
            p1Var.setFlags(1, 519);
            int i11 = androidx.core.os.r.f2132a;
            Trace.beginSection("RV OnBindView");
        }
        p1Var.mBindingAdapter = this;
        onBindViewHolder(p1Var, i10, p1Var.getUnmodifiedPayloads());
        if (z) {
            p1Var.clearPayload();
            ViewGroup.LayoutParams layoutParams = p1Var.itemView.getLayoutParams();
            if (layoutParams instanceof a1) {
                ((a1) layoutParams).f4877c = true;
            }
            int i12 = androidx.core.os.r.f2132a;
            Trace.endSection();
        }
    }

    public boolean canRestoreState() {
        int iOrdinal = this.mStateRestorationPolicy.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 : getItemCount() > 0;
    }

    public final p1 createViewHolder(ViewGroup viewGroup, int i10) {
        try {
            int i11 = androidx.core.os.r.f2132a;
            Trace.beginSection("RV CreateView");
            p1 p1VarOnCreateViewHolder = onCreateViewHolder(viewGroup, i10);
            if (p1VarOnCreateViewHolder.itemView.getParent() != null) {
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            }
            p1VarOnCreateViewHolder.mItemViewType = i10;
            Trace.endSection();
            return p1VarOnCreateViewHolder;
        } catch (Throwable th) {
            int i12 = androidx.core.os.r.f2132a;
            Trace.endSection();
            throw th;
        }
    }

    public int findRelativeAdapterPositionIn(l0 l0Var, p1 p1Var, int i10) {
        if (l0Var == this) {
            return i10;
        }
        return -1;
    }

    public abstract int getItemCount();

    public long getItemId(int i10) {
        return -1L;
    }

    public int getItemViewType(int i10) {
        return 0;
    }

    public final k0 getStateRestorationPolicy() {
        return this.mStateRestorationPolicy;
    }

    public final boolean hasObservers() {
        return this.mObservable.a();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void notifyDataSetChanged() {
        this.mObservable.b();
    }

    public final void notifyItemChanged(int i10) {
        this.mObservable.d(i10, 1, null);
    }

    public final void notifyItemInserted(int i10) {
        this.mObservable.e(i10, 1);
    }

    public final void notifyItemMoved(int i10, int i11) {
        this.mObservable.c(i10, i11);
    }

    public final void notifyItemRangeChanged(int i10, int i11) {
        this.mObservable.d(i10, i11, null);
    }

    public final void notifyItemRangeInserted(int i10, int i11) {
        this.mObservable.e(i10, i11);
    }

    public final void notifyItemRangeRemoved(int i10, int i11) {
        this.mObservable.f(i10, i11);
    }

    public final void notifyItemRemoved(int i10) {
        this.mObservable.f(i10, 1);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(p1 p1Var, int i10);

    public void onBindViewHolder(p1 p1Var, int i10, List<Object> list) {
        onBindViewHolder(p1Var, i10);
    }

    public abstract p1 onCreateViewHolder(ViewGroup viewGroup, int i10);

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    public boolean onFailedToRecycleView(p1 p1Var) {
        return false;
    }

    public void onViewAttachedToWindow(p1 p1Var) {
    }

    public void onViewDetachedFromWindow(p1 p1Var) {
    }

    public void onViewRecycled(p1 p1Var) {
    }

    public void registerAdapterDataObserver(n0 n0Var) {
        this.mObservable.registerObserver(n0Var);
    }

    public void setHasStableIds(boolean z) {
        if (hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = z;
    }

    public void setStateRestorationPolicy(k0 k0Var) {
        this.mStateRestorationPolicy = k0Var;
        this.mObservable.g();
    }

    public void unregisterAdapterDataObserver(n0 n0Var) {
        this.mObservable.unregisterObserver(n0Var);
    }

    public final void notifyItemChanged(int i10, Object obj) {
        this.mObservable.d(i10, 1, obj);
    }

    public final void notifyItemRangeChanged(int i10, int i11, Object obj) {
        this.mObservable.d(i10, i11, obj);
    }
}
