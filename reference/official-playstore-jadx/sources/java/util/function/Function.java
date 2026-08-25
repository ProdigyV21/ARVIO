package java.util.function;

/* JADX INFO: loaded from: classes2.dex */
public interface Function<T, R> {
    /* JADX INFO: renamed from: andThen */
    <V> Function<T, V> mo7027andThen(Function<? super R, ? extends V> function);

    R apply(T t2);
}
