package io.ktor.utils.io;

import com.google.common.util.concurrent.r0;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import ka.y;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.c0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a)\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0004*\u00020\u00002\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a1\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a8\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u000bj\u0002`\f2\u0014\b\u0004\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u000bH\u0082\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001f\u0010\u0015\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a\"\u0010\u0018\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0082\u0010¢\u0006\u0004\b\u0018\u0010\u0016\"\u0014\u0010\u0019\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\":\u0010\u001f\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0012\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u000bj\u0002`\f0\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 *(\b\u0002\u0010!\"\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u000b2\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u000b¨\u0006\""}, d2 = {"", "Lx6/t0;", "printStack", "(Ljava/lang/Throwable;)V", "E", "exception", "cause", "tryCopyException", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Ljava/lang/reflect/Constructor;", "constructor", "Lkotlin/Function1;", "Lio/ktor/utils/io/Ctor;", "createConstructor", "(Ljava/lang/reflect/Constructor;)Lr7/l;", "block", "safeCtor", "(Lr7/l;)Lr7/l;", "Ljava/lang/Class;", "", "defaultValue", "fieldsCountOrDefault", "(Ljava/lang/Class;I)I", "accumulator", "fieldsCount", "throwableFields", "I", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/util/WeakHashMap;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ctor", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExceptionUtilsJvmKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, l<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    /* JADX INFO: renamed from: io.ktor.utils.io.ExceptionUtilsJvmKt$safeCtor$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "e", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Throwable, Throwable> {
        final /* synthetic */ l<Throwable, Throwable> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super Throwable, ? extends Throwable> lVar) {
            super(1);
            this.$block = lVar;
        }

        @Override // r7.l
        public final Throwable invoke(Throwable th) {
            Object c0Var;
            try {
                c0Var = (Throwable) this.$block.invoke(th);
            } catch (Throwable th2) {
                c0Var = new c0(th2);
            }
            if (c0Var instanceof c0) {
                c0Var = null;
            }
            return (Throwable) c0Var;
        }
    }

    private static final l<Throwable, Throwable> createConstructor(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length == 0) {
            return new ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$4(constructor);
        }
        if (length != 1) {
            if (length == 2 && p.a(parameterTypes[0], String.class) && p.a(parameterTypes[1], Throwable.class)) {
                return new ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$1(constructor);
            }
            return null;
        }
        Class<?> cls = parameterTypes[0];
        if (p.a(cls, Throwable.class)) {
            return new ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$2(constructor);
        }
        if (p.a(cls, String.class)) {
            return new ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$3(constructor);
        }
        return null;
    }

    private static final int fieldsCount(Class<?> cls, int i10) {
        do {
            int i11 = 0;
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    i11++;
                }
            }
            i10 += i11;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i10;
    }

    public static /* synthetic */ int fieldsCount$default(Class cls, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 0;
        }
        return fieldsCount(cls, i10);
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int i10) {
        Object c0Var;
        l0.f19747a.b(cls);
        try {
            c0Var = Integer.valueOf(fieldsCount$default(cls, 0, 1, null));
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        Object objValueOf = Integer.valueOf(i10);
        if (c0Var instanceof c0) {
            c0Var = objValueOf;
        }
        return ((Number) c0Var).intValue();
    }

    public static final void printStack(Throwable th) {
        th.printStackTrace();
    }

    private static final l<Throwable, Throwable> safeCtor(l<? super Throwable, ? extends Throwable> lVar) {
        return new AnonymousClass1(lVar);
    }

    public static final <E extends Throwable> E tryCopyException(E e5, Throwable th) {
        Object c0Var;
        if (e5 instanceof y) {
            try {
                c0Var = ((y) e5).createCopy();
            } catch (Throwable th2) {
                c0Var = new c0(th2);
            }
            return (E) (c0Var instanceof c0 ? null : c0Var);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        lock.lock();
        try {
            l<Throwable, Throwable> lVar = exceptionCtors.get(e5.getClass());
            if (lVar != null) {
                return (E) lVar.invoke(e5);
            }
            int i10 = 0;
            if (throwableFields != fieldsCountOrDefault(e5.getClass(), 0)) {
                ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
                int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i11 = 0; i11 < readHoldCount; i11++) {
                    lock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    exceptionCtors.put((Class<? extends Throwable>) e5.getClass(), ExceptionUtilsJvmKt$tryCopyException$4$1.INSTANCE);
                    return null;
                } finally {
                    while (i10 < readHoldCount) {
                        lock2.lock();
                        i10++;
                    }
                    writeLock.unlock();
                }
            }
            Iterator it = kotlin.collections.r.k0(e5.getClass().getConstructors(), new Comparator() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$tryCopyException$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(((Constructor) t10).getParameterTypes().length), Integer.valueOf(((Constructor) t2).getParameterTypes().length));
                }
            }).iterator();
            l<Throwable, Throwable> lVarCreateConstructor = null;
            while (it.hasNext() && (lVarCreateConstructor = createConstructor((Constructor) it.next())) == null) {
            }
            ReentrantReadWriteLock reentrantReadWriteLock2 = cacheLock;
            ReentrantReadWriteLock.ReadLock lock3 = reentrantReadWriteLock2.readLock();
            int readHoldCount2 = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
            for (int i12 = 0; i12 < readHoldCount2; i12++) {
                lock3.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = reentrantReadWriteLock2.writeLock();
            writeLock2.lock();
            try {
                exceptionCtors.put((Class<? extends Throwable>) e5.getClass(), lVarCreateConstructor == null ? ExceptionUtilsJvmKt$tryCopyException$5$1.INSTANCE : lVarCreateConstructor);
                while (i10 < readHoldCount2) {
                    lock3.lock();
                    i10++;
                }
                writeLock2.unlock();
                if (lVarCreateConstructor != null) {
                    return (E) lVarCreateConstructor.invoke(th);
                }
                return null;
            } catch (Throwable th3) {
                while (i10 < readHoldCount2) {
                    lock3.lock();
                    i10++;
                }
                writeLock2.unlock();
                throw th3;
            }
        } finally {
            lock.unlock();
        }
    }
}
