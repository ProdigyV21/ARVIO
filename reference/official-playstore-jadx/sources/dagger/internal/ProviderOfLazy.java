package dagger.internal;

import dagger.Lazy;

/* JADX INFO: loaded from: classes4.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<T> provider;

    private ProviderOfLazy(Provider<T> provider) {
        this.provider = provider;
    }

    public static <T> Provider<Lazy<T>> create(Provider<T> provider) {
        return new ProviderOfLazy((Provider) Preconditions.checkNotNull(provider));
    }

    @Deprecated
    public static <T> Provider<Lazy<T>> create(javax.inject.Provider<T> provider) {
        return create(Providers.asDaggerProvider(provider));
    }

    @Override // javax.inject.Provider
    public Lazy<T> get() {
        return DoubleCheck.lazy((Provider) this.provider);
    }
}
