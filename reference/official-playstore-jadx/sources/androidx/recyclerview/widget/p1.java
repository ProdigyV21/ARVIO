package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class p1 {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    l0 mBindingAdapter;
    int mFlags;
    WeakReference<RecyclerView> mNestedRecyclerView;
    RecyclerView mOwnerRecyclerView;
    int mPosition = -1;
    int mOldPosition = -1;
    long mItemId = -1;
    int mItemViewType = -1;
    int mPreLayoutPosition = -1;
    p1 mShadowedHolder = null;
    p1 mShadowingHolder = null;
    List<Object> mPayloads = null;
    List<Object> mUnmodifiedPayloads = null;
    private int mIsRecyclableCount = 0;
    f1 mScrapContainer = null;
    boolean mInChangeScrap = false;
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    int mPendingAccessibilityState = -1;

    public p1(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = view;
    }

    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
            return;
        }
        if ((1024 & this.mFlags) == 0) {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
            this.mPayloads.add(obj);
        }
    }

    public void addFlags(int i10) {
        this.mFlags = i10 | this.mFlags;
    }

    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    public void clearPayload() {
        List<Object> list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    public boolean doesTransientStatePreventRecycling() {
        if ((this.mFlags & 16) != 0) {
            return false;
        }
        View view = this.itemView;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        return view.hasTransientState();
    }

    public void flagRemovedAndOffsetPosition(int i10, int i11, boolean z) {
        addFlags(8);
        offsetPosition(i11, z);
        this.mPosition = i10;
    }

    public final int getAbsoluteAdapterPosition() {
        RecyclerView recyclerView = this.mOwnerRecyclerView;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.D(this);
    }

    @Deprecated
    public final int getAdapterPosition() {
        return getBindingAdapterPosition();
    }

    public final l0 getBindingAdapter() {
        return this.mBindingAdapter;
    }

    public final int getBindingAdapterPosition() {
        RecyclerView recyclerView;
        l0 adapter;
        int iD;
        if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.getAdapter()) == null || (iD = this.mOwnerRecyclerView.D(this)) == -1) {
            return -1;
        }
        return adapter.findRelativeAdapterPositionIn(this.mBindingAdapter, this, iD);
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i10 = this.mPreLayoutPosition;
        return i10 == -1 ? this.mPosition : i10;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    @Deprecated
    public final int getPosition() {
        int i10 = this.mPreLayoutPosition;
        return i10 == -1 ? this.mPosition : i10;
    }

    public List<Object> getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return FULLUPDATE_PAYLOADS;
        }
        List<Object> list = this.mPayloads;
        return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
    }

    public boolean hasAnyOfTheFlags(int i10) {
        return (i10 & this.mFlags) != 0;
    }

    public boolean isAdapterPositionUnknown() {
        return (this.mFlags & 512) != 0 || isInvalid();
    }

    public boolean isAttachedToTransitionOverlay() {
        return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
    }

    public boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isRecyclable() {
        if ((this.mFlags & 16) != 0) {
            return false;
        }
        View view = this.itemView;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        return !view.hasTransientState();
    }

    public boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    public boolean isScrap() {
        return this.mScrapContainer != null;
    }

    public boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    public boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }

    public boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    public void offsetPosition(int i10, boolean z) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z) {
            this.mPreLayoutPosition += i10;
        }
        this.mPosition += i10;
        if (this.itemView.getLayoutParams() != null) {
            ((a1) this.itemView.getLayoutParams()).f4877c = true;
        }
    }

    public void onEnteredHiddenState(RecyclerView recyclerView) {
        int i10 = this.mPendingAccessibilityState;
        if (i10 != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = i10;
        } else {
            View view = this.itemView;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            this.mWasImportantForAccessibilityBeforeHidden = view.getImportantForAccessibility();
        }
        if (recyclerView.K()) {
            this.mPendingAccessibilityState = 4;
            recyclerView.E0.add(this);
        } else {
            View view2 = this.itemView;
            WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
            view2.setImportantForAccessibility(4);
        }
    }

    public void onLeftHiddenState(RecyclerView recyclerView) {
        int i10 = this.mWasImportantForAccessibilityBeforeHidden;
        if (recyclerView.K()) {
            this.mPendingAccessibilityState = i10;
            recyclerView.E0.add(this);
        } else {
            View view = this.itemView;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            view.setImportantForAccessibility(i10);
        }
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    public void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.g(this);
    }

    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    public void setFlags(int i10, int i11) {
        this.mFlags = (i10 & i11) | (this.mFlags & (~i11));
    }

    public final void setIsRecyclable(boolean z) {
        int i10 = this.mIsRecyclableCount;
        int i11 = z ? i10 - 1 : i10 + 1;
        this.mIsRecyclableCount = i11;
        if (i11 < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            return;
        }
        if (!z && i11 == 1) {
            this.mFlags |= 16;
        } else if (z && i11 == 0) {
            this.mFlags &= -17;
        }
    }

    public void setScrapContainer(f1 f1Var, boolean z) {
        this.mScrapContainer = f1Var;
        this.mInChangeScrap = z;
    }

    public boolean shouldBeKeptAsChild() {
        return (this.mFlags & 16) != 0;
    }

    public boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    public void stopIgnoring() {
        this.mFlags &= -129;
    }

    public String toString() {
        StringBuilder sbP = androidx.fragment.app.a2.p(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
        sbP.append(Integer.toHexString(hashCode()));
        sbP.append(" position=");
        sbP.append(this.mPosition);
        sbP.append(" id=");
        sbP.append(this.mItemId);
        sbP.append(", oldPos=");
        sbP.append(this.mOldPosition);
        sbP.append(", pLpos:");
        sbP.append(this.mPreLayoutPosition);
        StringBuilder sb2 = new StringBuilder(sbP.toString());
        if (isScrap()) {
            sb2.append(" scrap ");
            sb2.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            sb2.append(" invalid");
        }
        if (!isBound()) {
            sb2.append(" unbound");
        }
        if (needsUpdate()) {
            sb2.append(" update");
        }
        if (isRemoved()) {
            sb2.append(" removed");
        }
        if (shouldIgnore()) {
            sb2.append(" ignored");
        }
        if (isTmpDetached()) {
            sb2.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            sb2.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (isAdapterPositionUnknown()) {
            sb2.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb2.append(" no parent");
        }
        sb2.append("}");
        return sb2.toString();
    }

    public void unScrap() {
        this.mScrapContainer.l(this);
    }

    public boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }
}
