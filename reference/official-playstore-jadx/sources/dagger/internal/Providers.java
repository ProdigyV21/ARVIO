package dagger.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class Providers {
    private Providers() {
    }

    public static <T> Provider<T> asDaggerProvider(final javax.inject.Provider<T> provider) {
        Preconditions.checkNotNull(provider);
        return provider instanceof Provider ? (Provider) provider : new Provider<T>() { // from class: dagger.internal.Providers.1
            @Override // javax.inject.Provider
            public T get() {
                return (T) provider.get();
            }
        };
    }
}
