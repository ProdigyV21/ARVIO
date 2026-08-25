package io.sentry.android.core.internal.tombstone;

/* JADX INFO: loaded from: classes5.dex */
public enum a {
    TOMBSTONE("Tombstone"),
    SIGNAL_HANDLER("signalhandler"),
    TOMBSTONE_MERGED("TombstoneMerged");

    private final String value;

    a(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
