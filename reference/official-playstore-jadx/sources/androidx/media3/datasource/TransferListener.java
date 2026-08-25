package androidx.media3.datasource;

/* JADX INFO: loaded from: classes3.dex */
public interface TransferListener {
    void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int i10);

    void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z);

    void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z);

    void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z);
}
