package ea;

import androidx.media3.exoplayer.upstream.CmcdData;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.List;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends AbstractList implements RandomAccess, List {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15090i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f15091l;

    public static /* synthetic */ void a(int i10) {
        String str = (i10 == 2 || i10 == 3 || i10 == 5 || i10 == 6 || i10 == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 2 || i10 == 3 || i10 == 5 || i10 == 6 || i10 == 7) ? 2 : 3];
        switch (i10) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = CmcdData.OBJECT_TYPE_AUDIO_ONLY;
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i10 == 2 || i10 == 3) {
            objArr[1] = "iterator";
        } else if (i10 == 5 || i10 == 6 || i10 == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
        }
        switch (i10) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 2 && i10 != 3 && i10 != 5 && i10 != 6 && i10 != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        int i10 = this.f15090i;
        if (i10 == 0) {
            this.f15091l = obj;
        } else if (i10 == 1) {
            this.f15091l = new Object[]{this.f15091l, obj};
        } else {
            Object[] objArr = (Object[]) this.f15091l;
            int length = objArr.length;
            if (i10 >= length) {
                int i11 = ((length * 3) / 2) + 1;
                int i12 = i10 + 1;
                if (i11 < i12) {
                    i11 = i12;
                }
                Object[] objArr2 = new Object[i11];
                this.f15091l = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
                objArr = objArr2;
            }
            objArr[this.f15090i] = obj;
        }
        this.f15090i++;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.f15091l = null;
        this.f15090i = 0;
        ((AbstractList) this).modCount++;
    }

    @Override // java.lang.Iterable, j$.util.Collection
    public /* synthetic */ void forEach(Consumer consumer) {
        Iterable$CC.$default$forEach(this, consumer);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        int i11;
        if (i10 >= 0 && i10 < (i11 = this.f15090i)) {
            return i11 == 1 ? this.f15091l : ((Object[]) this.f15091l)[i10];
        }
        StringBuilder sbS = a0.c.s(i10, "Index: ", ", Size: ");
        sbS.append(this.f15090i);
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        int i10 = this.f15090i;
        if (i10 == 0) {
            return g.f15086i;
        }
        if (i10 == 1) {
            return new h(this);
        }
        Iterator it = super.iterator();
        if (it != null) {
            return it;
        }
        a(3);
        throw null;
    }

    @Override // java.util.Collection
    public /* synthetic */ Stream parallelStream() {
        return Stream.Wrapper.convert(parallelStream());
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        int i11;
        Object obj;
        if (i10 < 0 || i10 >= (i11 = this.f15090i)) {
            StringBuilder sbS = a0.c.s(i10, "Index: ", ", Size: ");
            sbS.append(this.f15090i);
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        if (i11 == 1) {
            obj = this.f15091l;
            this.f15091l = null;
        } else {
            Object[] objArr = (Object[]) this.f15091l;
            Object obj2 = objArr[i10];
            if (i11 == 2) {
                this.f15091l = objArr[1 - i10];
            } else {
                int i12 = (i11 - i10) - 1;
                if (i12 > 0) {
                    System.arraycopy(objArr, i10 + 1, objArr, i10, i12);
                }
                objArr[this.f15090i - 1] = null;
            }
            obj = obj2;
        }
        this.f15090i--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ boolean removeIf(Predicate predicate) {
        return Collection.CC.$default$removeIf(this, predicate);
    }

    @Override // java.util.List, j$.util.List
    public /* synthetic */ void replaceAll(UnaryOperator unaryOperator) {
        List.CC.$default$replaceAll(this, unaryOperator);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        int i11;
        if (i10 < 0 || i10 >= (i11 = this.f15090i)) {
            StringBuilder sbS = a0.c.s(i10, "Index: ", ", Size: ");
            sbS.append(this.f15090i);
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        if (i11 == 1) {
            Object obj2 = this.f15091l;
            this.f15091l = obj;
            return obj2;
        }
        Object[] objArr = (Object[]) this.f15091l;
        Object obj3 = objArr[i10];
        objArr[i10] = obj;
        return obj3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f15090i;
    }

    @Override // java.util.List, j$.util.List
    public final void sort(Comparator comparator) {
        int i10 = this.f15090i;
        if (i10 >= 2) {
            Arrays.sort((Object[]) this.f15091l, 0, i10, comparator);
        }
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Spliterator spliterator() {
        return Spliterator.Wrapper.convert(spliterator());
    }

    @Override // java.util.Collection
    public /* synthetic */ java.util.stream.Stream stream() {
        return Stream.Wrapper.convert(stream());
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ Object[] toArray(IntFunction intFunction) {
        return toArray((Object[]) intFunction.apply(0));
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream parallelStream() {
        return StreamSupport.stream(Collection.EL.b(this), true);
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List, j$.util.List, j$.util.Collection
    public /* synthetic */ j$.util.Spliterator spliterator() {
        return List.CC.$default$spliterator(this);
    }

    @Override // java.util.Collection, j$.util.Collection
    public /* synthetic */ j$.util.stream.Stream stream() {
        return Collection.CC.$default$stream(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        if (objArr == null) {
            a(4);
            throw null;
        }
        int length = objArr.length;
        int i10 = this.f15090i;
        if (i10 == 1) {
            if (length == 0) {
                Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), 1);
                objArr2[0] = this.f15091l;
                return objArr2;
            }
            objArr[0] = this.f15091l;
        } else {
            if (length < i10) {
                Object[] objArrCopyOf = Arrays.copyOf((Object[]) this.f15091l, i10, objArr.getClass());
                if (objArrCopyOf != null) {
                    return objArrCopyOf;
                }
                a(6);
                throw null;
            }
            if (i10 != 0) {
                System.arraycopy(this.f15091l, 0, objArr, 0, i10);
            }
        }
        int i11 = this.f15090i;
        if (length > i11) {
            objArr[i11] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        if (i10 >= 0 && i10 <= (i11 = this.f15090i)) {
            if (i11 == 0) {
                this.f15091l = obj;
            } else if (i11 == 1 && i10 == 0) {
                this.f15091l = new Object[]{obj, this.f15091l};
            } else {
                Object[] objArr = new Object[i11 + 1];
                if (i11 == 1) {
                    objArr[0] = this.f15091l;
                } else {
                    Object[] objArr2 = (Object[]) this.f15091l;
                    System.arraycopy(objArr2, 0, objArr, 0, i10);
                    System.arraycopy(objArr2, i10, objArr, i10 + 1, this.f15090i - i10);
                }
                objArr[i10] = obj;
                this.f15091l = objArr;
            }
            this.f15090i++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder sbS = a0.c.s(i10, "Index: ", ", Size: ");
        sbS.append(this.f15090i);
        throw new IndexOutOfBoundsException(sbS.toString());
    }
}
