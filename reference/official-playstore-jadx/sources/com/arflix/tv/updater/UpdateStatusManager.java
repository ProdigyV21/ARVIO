package com.arflix.tv.updater;

import com.arflix.tv.updater.UpdateStatus;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u0003R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/updater/UpdateStatusManager;", "", "<init>", "()V", "Lcom/arflix/tv/updater/UpdateStatus;", "newStatus", "Lx6/t0;", "updateStatus", "(Lcom/arflix/tv/updater/UpdateStatus;)V", "reset", "Lna/q0;", "_status", "Lna/q0;", "Lna/h1;", "status", "Lna/h1;", "getStatus", "()Lna/h1;", "", "sessionIgnoredTag", "Ljava/lang/String;", "getSessionIgnoredTag", "()Ljava/lang/String;", "setSessionIgnoredTag", "(Ljava/lang/String;)V", "Lcom/arflix/tv/updater/AppUpdate;", "lastUpdate", "Lcom/arflix/tv/updater/AppUpdate;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdateStatusManager {
    public static final int $stable = 8;
    private final q0<UpdateStatus> _status;
    private AppUpdate lastUpdate;
    private String sessionIgnoredTag;
    private final h1<UpdateStatus> status;

    @Inject
    public UpdateStatusManager() {
        j1 j1VarB = y0.b(UpdateStatus.Idle.INSTANCE);
        this._status = j1VarB;
        this.status = y0.e(j1VarB);
    }

    public final String getSessionIgnoredTag() {
        return this.sessionIgnoredTag;
    }

    public final h1<UpdateStatus> getStatus() {
        return this.status;
    }

    public final void reset() {
        this.lastUpdate = null;
        this._status.setValue(UpdateStatus.Idle.INSTANCE);
    }

    public final void setSessionIgnoredTag(String str) {
        this.sessionIgnoredTag = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.arflix.tv.updater.UpdateStatus$Failure] */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.arflix.tv.updater.UpdateStatus$Failure] */
    public final void updateStatus(UpdateStatus newStatus) {
        if (newStatus instanceof UpdateStatus.UpdateAvailable) {
            this.lastUpdate = ((UpdateStatus.UpdateAvailable) newStatus).getUpdate();
        } else if (newStatus instanceof UpdateStatus.Downloading) {
            this.lastUpdate = ((UpdateStatus.Downloading) newStatus).getUpdate();
        } else if (newStatus instanceof UpdateStatus.ReadyToInstall) {
            this.lastUpdate = ((UpdateStatus.ReadyToInstall) newStatus).getUpdate();
        } else if (newStatus instanceof UpdateStatus.Installing) {
            UpdateStatus.Installing installing = (UpdateStatus.Installing) newStatus;
            if (installing.getUpdate() != null) {
                this.lastUpdate = installing.getUpdate();
            }
        } else if (newStatus instanceof UpdateStatus.Failure) {
            newStatus = (UpdateStatus.Failure) newStatus;
            AppUpdate update = newStatus.getUpdate();
            if (update == null) {
                update = this.lastUpdate;
            }
            if (update != null) {
                newStatus = UpdateStatus.Failure.copy$default(newStatus, null, update, 1, null);
            }
        }
        this._status.setValue(newStatus);
    }
}
