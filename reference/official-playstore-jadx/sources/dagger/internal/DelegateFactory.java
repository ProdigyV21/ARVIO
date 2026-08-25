package dagger.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    public static <T> void setDelegate(Provider<T> provider, Provider<T> provider2) {
        setDelegateInternal((DelegateFactory) provider, provider2);
    }

    private static <T> void setDelegateInternal(DelegateFactory<T> delegateFactory, Provider<T> provider) {
        Preconditions.checkNotNull(provider);
        if (((DelegateFactory) delegateFactory).delegate != null) {
            throw new IllegalStateException();
        }
        ((DelegateFactory) delegateFactory).delegate = provider;
    }

    @Override // javax.inject.Provider
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }

    public Provider<T> getDelegate() {
        return (Provider) Preconditions.checkNotNull(this.delegate);
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> provider) {
        setDelegate((Provider) this, (Provider) provider);
    }

    @Deprecated
    public void setDelegatedProvider(javax.inject.Provider<T> provider) {
        setDelegatedProvider((Provider) Providers.asDaggerProvider(provider));
    }

    @Deprecated
    public static <T> void setDelegate(javax.inject.Provider<T> provider, javax.inject.Provider<T> provider2) {
        setDelegateInternal((DelegateFactory) provider, Providers.asDaggerProvider(provider2));
    }
}
