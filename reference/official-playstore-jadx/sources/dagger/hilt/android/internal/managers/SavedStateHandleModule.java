package dagger.hilt.android.internal.managers;

import androidx.lifecycle.w0;
import dagger.Module;
import dagger.Provides;

/* JADX INFO: loaded from: classes4.dex */
@Module
abstract class SavedStateHandleModule {
    @Provides
    public static w0 provideSavedStateHandle(SavedStateHandleHolder savedStateHandleHolder) {
        return savedStateHandleHolder.getSavedStateHandle();
    }
}
