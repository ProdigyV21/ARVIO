package io.ktor.util;

import java.util.List;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J(\u0010\u0005\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&¢\u0006\u0004\b\u0007\u0010\u0006J\u001c\u0010\t\u001a\u00020\b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003H¦\u0002¢\u0006\u0004\b\t\u0010\nJ/\u0010\r\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0011\u0010\u0006J)\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0006J5\u0010\u0015\u001a\u00028\u0000\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H&¢\u0006\u0004\b\u0015\u0010\u0016R\u001e\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/util/Attributes;", "", "T", "Lio/ktor/util/AttributeKey;", "key", "get", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "getOrNull", "", "contains", "(Lio/ktor/util/AttributeKey;)Z", "value", "Lx6/t0;", "put", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "remove", "(Lio/ktor/util/AttributeKey;)V", "take", "takeOrNull", "Lkotlin/Function0;", "block", "computeIfAbsent", "(Lio/ktor/util/AttributeKey;Lr7/a;)Ljava/lang/Object;", "", "getAllKeys", "()Ljava/util/List;", "allKeys", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Attributes {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <T> T get(Attributes attributes, AttributeKey<T> attributeKey) {
            T t2 = (T) attributes.getOrNull(attributeKey);
            if (t2 != null) {
                return t2;
            }
            throw new IllegalStateException("No instance for key " + attributeKey);
        }

        public static <T> T take(Attributes attributes, AttributeKey<T> attributeKey) {
            T t2 = (T) attributes.get(attributeKey);
            attributes.remove(attributeKey);
            return t2;
        }

        public static <T> T takeOrNull(Attributes attributes, AttributeKey<T> attributeKey) {
            T t2 = (T) attributes.getOrNull(attributeKey);
            attributes.remove(attributeKey);
            return t2;
        }
    }

    <T> T computeIfAbsent(AttributeKey<T> key, a<? extends T> block);

    boolean contains(AttributeKey<?> key);

    <T> T get(AttributeKey<T> key);

    List<AttributeKey<?>> getAllKeys();

    <T> T getOrNull(AttributeKey<T> key);

    <T> void put(AttributeKey<T> key, T value);

    <T> void remove(AttributeKey<T> key);

    <T> T take(AttributeKey<T> key);

    <T> T takeOrNull(AttributeKey<T> key);
}
