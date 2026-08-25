package androidx.loader.content;

import android.content.Context;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {
    boolean mAbandoned;
    boolean mContentChanged;
    Context mContext;
    int mId;
    d<Object> mListener;
    c<Object> mOnLoadCanceledListener;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(Object obj) {
        StringBuilder sb2 = new StringBuilder(64);
        p0.g.a(sb2, obj);
        sb2.append("}");
        return sb2.toString();
    }

    public void deliverCancellation() {
        c<Object> cVar = this.mOnLoadCanceledListener;
        if (cVar != null) {
            cVar.a();
        }
    }

    public void deliverResult(Object obj) {
        d<Object> dVar = this.mListener;
        if (dVar != null) {
            dVar.a(obj);
        }
    }

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public void forceLoad() {
        onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void onAbandon() {
    }

    public abstract boolean onCancelLoad();

    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    public abstract void onForceLoad();

    public void onReset() {
    }

    public abstract void onStartLoading();

    public void onStopLoading() {
    }

    public void registerListener(int i10, d<Object> dVar) {
        if (this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = dVar;
        this.mId = i10;
    }

    public void registerOnLoadCanceledListener(c<Object> cVar) {
        if (this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mOnLoadCanceledListener = cVar;
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z;
        return z;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(64);
        p0.g.a(sb2, this);
        sb2.append(" id=");
        return androidx.compose.material3.d.j(this.mId, "}", sb2);
    }

    public void unregisterListener(d<Object> dVar) {
        d<Object> dVar2 = this.mListener;
        if (dVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (dVar2 != dVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }

    public void unregisterOnLoadCanceledListener(c<Object> cVar) {
        c<Object> cVar2 = this.mOnLoadCanceledListener;
        if (cVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (cVar2 != cVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mOnLoadCanceledListener = null;
    }
}
