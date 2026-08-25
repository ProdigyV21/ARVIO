package dagger.hilt.android.lifecycle;

/* JADX INFO: loaded from: classes4.dex */
public interface RetainedLifecycle {

    public interface OnClearedListener {
        void onCleared();
    }

    void addOnClearedListener(OnClearedListener onClearedListener);

    void removeOnClearedListener(OnClearedListener onClearedListener);
}
