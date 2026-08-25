package dagger.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class SingleCheck<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    public static <T> Provider<T> provider(Provider<T> provider) {
        return ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) ? provider : new SingleCheck((Provider) Preconditions.checkNotNull(provider));
    }

    @Override // javax.inject.Provider
    public T get() {
        T t2 = (T) this.instance;
        if (t2 != UNINITIALIZED) {
            return t2;
        }
        Provider<T> provider = this.provider;
        if (provider == null) {
            return (T) this.instance;
        }
        T t10 = provider.get();
        this.instance = t10;
        this.provider = null;
        return t10;
    }

    public static <P extends javax.inject.Provider<T>, T> javax.inject.Provider<T> provider(P p10) {
        return provider(Providers.asDaggerProvider(p10));
    }
}
