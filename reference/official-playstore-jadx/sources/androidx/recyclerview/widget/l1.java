package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class l1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4973a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4976d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4977e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4978f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4979g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4980h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4981i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4982j;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4983l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f4984m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4985n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4986o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4987p;

    public final void a(int i10) {
        if ((this.f4976d & i10) != 0) {
            return;
        }
        throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i10) + " but it is " + Integer.toBinaryString(this.f4976d));
    }

    public final int b() {
        return this.f4979g ? this.f4974b - this.f4975c : this.f4977e;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("State{mTargetPosition=");
        sb2.append(this.f4973a);
        sb2.append(", mData=null, mItemCount=");
        sb2.append(this.f4977e);
        sb2.append(", mIsMeasuring=");
        sb2.append(this.f4981i);
        sb2.append(", mPreviousLayoutItemCount=");
        sb2.append(this.f4974b);
        sb2.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        sb2.append(this.f4975c);
        sb2.append(", mStructureChanged=");
        sb2.append(this.f4978f);
        sb2.append(", mInPreLayout=");
        sb2.append(this.f4979g);
        sb2.append(", mRunSimpleAnimations=");
        sb2.append(this.f4982j);
        sb2.append(", mRunPredictiveAnimations=");
        return a0.c.r(sb2, this.k, '}');
    }
}
