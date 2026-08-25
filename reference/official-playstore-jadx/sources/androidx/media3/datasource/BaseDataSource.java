package androidx.media3.datasource;

import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseDataSource implements DataSource {
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners = new ArrayList<>(1);

    public BaseDataSource(boolean z) {
        this.isNetwork = z;
    }

    @Override // androidx.media3.datasource.DataSource
    public final void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        if (this.listeners.contains(transferListener)) {
            return;
        }
        this.listeners.add(transferListener);
        this.listenerCount++;
    }

    public final void bytesTransferred(int i10) {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i11 = 0; i11 < this.listenerCount; i11++) {
            this.listeners.get(i11).onBytesTransferred(this, dataSpec, this.isNetwork, i10);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public /* synthetic */ Map getResponseHeaders() {
        return a.a(this);
    }

    public final void transferEnded() {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i10 = 0; i10 < this.listenerCount; i10++) {
            this.listeners.get(i10).onTransferEnd(this, dataSpec, this.isNetwork);
        }
        this.dataSpec = null;
    }

    public final void transferInitializing(DataSpec dataSpec) {
        for (int i10 = 0; i10 < this.listenerCount; i10++) {
            this.listeners.get(i10).onTransferInitializing(this, dataSpec, this.isNetwork);
        }
    }

    public final void transferStarted(DataSpec dataSpec) {
        this.dataSpec = dataSpec;
        for (int i10 = 0; i10 < this.listenerCount; i10++) {
            this.listeners.get(i10).onTransferStart(this, dataSpec, this.isNetwork);
        }
    }
}
