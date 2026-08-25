package j$.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class Optional<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Optional f18432b = new Optional();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f18433a;

    public Optional() {
        this.f18433a = null;
    }

    public static <T> Optional<T> empty() {
        return f18432b;
    }

    public Optional(Object obj) {
        this.f18433a = Objects.requireNonNull(obj);
    }

    public static <T> Optional<T> of(T t2) {
        return new Optional<>(t2);
    }

    public static <T> Optional<T> ofNullable(T t2) {
        return t2 == null ? empty() : of(t2);
    }

    public T get() {
        T t2 = (T) this.f18433a;
        if (t2 != null) {
            return t2;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.f18433a != null;
    }

    public T orElse(T t2) {
        T t10 = (T) this.f18433a;
        return t10 != null ? t10 : t2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            return Objects.equals(this.f18433a, ((Optional) obj).f18433a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f18433a);
    }

    public final String toString() {
        Object obj = this.f18433a;
        return obj != null ? String.format("Optional[%s]", obj) : "Optional.empty";
    }
}
