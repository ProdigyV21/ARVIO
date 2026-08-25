package dagger.hilt.android.internal.managers;

import android.os.Bundle;
import androidx.lifecycle.w0;
import androidx.lifecycle.z0;
import dagger.hilt.android.internal.ThreadUtil;
import dagger.hilt.internal.Preconditions;
import o1.c;
import o1.d;

/* JADX INFO: loaded from: classes4.dex */
public final class SavedStateHandleHolder {
    private c extras;
    private w0 handle;
    private final boolean nonComponentActivity;

    public SavedStateHandleHolder(c cVar) {
        this.nonComponentActivity = cVar == null;
        this.extras = cVar;
    }

    public void clear() {
        this.extras = null;
    }

    public w0 getSavedStateHandle() {
        ThreadUtil.ensureMainThread();
        Preconditions.checkState(!this.nonComponentActivity, "Activity that does not extend ComponentActivity cannot use SavedStateHandle", new Object[0]);
        w0 w0Var = this.handle;
        if (w0Var != null) {
            return w0Var;
        }
        Preconditions.checkNotNull(this.extras, "The first access to SavedStateHandle should happen between super.onCreate() and super.onDestroy()");
        d dVar = new d(this.extras);
        dVar.b(z0.f3318c, Bundle.EMPTY);
        this.extras = dVar;
        w0 w0VarC = z0.c(dVar);
        this.handle = w0VarC;
        this.extras = null;
        return w0VarC;
    }

    public boolean isInvalid() {
        return this.handle == null && this.extras == null;
    }

    public void setExtras(c cVar) {
        if (this.handle != null) {
            return;
        }
        this.extras = cVar;
    }
}
