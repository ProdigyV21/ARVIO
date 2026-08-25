package dagger.hilt.android.internal.managers;

import androidx.lifecycle.w0;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class SavedStateHandleModule_ProvideSavedStateHandleFactory implements Factory<w0> {
    private final Provider<SavedStateHandleHolder> savedStateHandleHolderProvider;

    private SavedStateHandleModule_ProvideSavedStateHandleFactory(Provider<SavedStateHandleHolder> provider) {
        this.savedStateHandleHolderProvider = provider;
    }

    public static SavedStateHandleModule_ProvideSavedStateHandleFactory create(Provider<SavedStateHandleHolder> provider) {
        return new SavedStateHandleModule_ProvideSavedStateHandleFactory(provider);
    }

    public static w0 provideSavedStateHandle(SavedStateHandleHolder savedStateHandleHolder) {
        return (w0) Preconditions.checkNotNullFromProvides(SavedStateHandleModule.provideSavedStateHandle(savedStateHandleHolder));
    }

    @Override // javax.inject.Provider
    public w0 get() {
        return provideSavedStateHandle(this.savedStateHandleHolderProvider.get());
    }
}
