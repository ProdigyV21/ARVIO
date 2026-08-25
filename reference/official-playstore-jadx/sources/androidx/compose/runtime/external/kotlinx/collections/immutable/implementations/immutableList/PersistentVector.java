package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\t\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B7\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000eJE\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0002¢\u0006\u0004\b\u0011\u0010\u0012JA\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J7\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b\u0018\u0010\u0019JI\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ=\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001f\u0010 J5\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002¢\u0006\u0004\b!\u0010\"JA\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u001bH\u0002¢\u0006\u0004\b$\u0010%J?\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u001bH\u0002¢\u0006\u0004\b&\u0010%J\u001f\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0004\b'\u0010(JA\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b*\u0010+J\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0004\b,\u0010-J%\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0004\b,\u0010.J\u001d\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u001a\u001a\u00020\bH\u0016¢\u0006\u0004\b/\u00100J)\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020201H\u0016¢\u0006\u0004\b4\u00105J\u0015\u00107\u001a\b\u0012\u0004\u0012\u00028\u000006H\u0016¢\u0006\u0004\b7\u00108J\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00028\u0000092\u0006\u0010\u001a\u001a\u00020\bH\u0016¢\u0006\u0004\b:\u0010;J\u0018\u0010<\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\bH\u0096\u0002¢\u0006\u0004\b<\u0010=J%\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0004\b>\u0010.R\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010?R\u001c\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010?R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010@\u001a\u0004\bA\u0010\u000eR\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010@¨\u0006B"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVector;", "E", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/AbstractPersistentList;", "", "", "root", "tail", "", ContentDisposition.Parameters.Size, "rootShift", "<init>", "([Ljava/lang/Object;[Ljava/lang/Object;II)V", "rootSize", "()I", "filledTail", "newTail", "pushFilledTail", "([Ljava/lang/Object;[Ljava/lang/Object;[Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVector;", "shift", "pushTail", "([Ljava/lang/Object;I[Ljava/lang/Object;)[Ljava/lang/Object;", "tailIndex", "element", "insertIntoTail", "([Ljava/lang/Object;ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVector;", "index", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;", "elementCarry", "insertIntoRoot", "([Ljava/lang/Object;IILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "removeFromTailAt", "([Ljava/lang/Object;III)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "pullLastBufferFromRoot", "([Ljava/lang/Object;II)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "tailCarry", "pullLastBuffer", "([Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/ObjectRef;)[Ljava/lang/Object;", "removeFromRootAt", "bufferFor", "(I)[Ljava/lang/Object;", "e", "setInRoot", "([Ljava/lang/Object;IILjava/lang/Object;)[Ljava/lang/Object;", "add", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "(ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "removeAt", "(I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "Lkotlin/Function1;", "", "predicate", "removeAll", "(Lr7/l;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder;", "builder", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/PersistentVectorBuilder;", "", "listIterator", "(I)Ljava/util/ListIterator;", "get", "(I)Ljava/lang/Object;", "set", "[Ljava/lang/Object;", "I", "getSize", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PersistentVector<E> extends AbstractPersistentList<E> implements PersistentList<E> {
    public static final int $stable = 8;
    private final Object[] root;
    private final int rootShift;
    private final int size;
    private final Object[] tail;

    public PersistentVector(Object[] objArr, Object[] objArr2, int i10, int i11) {
        this.root = objArr;
        this.tail = objArr2;
        this.size = i10;
        this.rootShift = i11;
        if (!(size() > 32)) {
            PreconditionsKt.throwIllegalArgumentException("Trie-based persistent vector should have at least 33 elements, got " + size());
        }
        int size = size() - UtilsKt.rootSize(size());
        int length = objArr2.length;
        CommonFunctionsKt.m3083assert(size <= (length <= 32 ? length : 32));
    }

    private final Object[] bufferFor(int index) {
        if (rootSize() <= index) {
            return this.tail;
        }
        Object[] objArr = this.root;
        for (int i10 = this.rootShift; i10 > 0; i10 -= 5) {
            objArr = objArr[UtilsKt.indexSegment(index, i10)];
        }
        return objArr;
    }

    private final Object[] insertIntoRoot(Object[] root, int shift, int index, Object element, ObjectRef elementCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            Object[] objArrCopyOf = iIndexSegment == 0 ? new Object[32] : Arrays.copyOf(root, 32);
            r.N(root, objArrCopyOf, iIndexSegment + 1, iIndexSegment, 31);
            elementCarry.setValue(root[31]);
            objArrCopyOf[iIndexSegment] = element;
            return objArrCopyOf;
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(root, 32);
        int i10 = shift - 5;
        objArrCopyOf2[iIndexSegment] = insertIntoRoot((Object[]) root[iIndexSegment], i10, index, element, elementCarry);
        while (true) {
            iIndexSegment++;
            if (iIndexSegment >= 32 || objArrCopyOf2[iIndexSegment] == null) {
                break;
            }
            objArrCopyOf2[iIndexSegment] = insertIntoRoot((Object[]) root[iIndexSegment], i10, 0, elementCarry.getValue(), elementCarry);
        }
        return objArrCopyOf2;
    }

    private final PersistentVector<E> insertIntoTail(Object[] root, int tailIndex, Object element) {
        int size = size() - rootSize();
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        if (size < 32) {
            r.N(this.tail, objArrCopyOf, tailIndex + 1, tailIndex, size);
            objArrCopyOf[tailIndex] = element;
            return new PersistentVector<>(root, objArrCopyOf, size() + 1, this.rootShift);
        }
        Object[] objArr = this.tail;
        Object obj = objArr[31];
        r.N(objArr, objArrCopyOf, tailIndex + 1, tailIndex, size - 1);
        objArrCopyOf[tailIndex] = element;
        return pushFilledTail(root, objArrCopyOf, UtilsKt.presizedBufferWith(obj));
    }

    private final Object[] pullLastBuffer(Object[] root, int shift, int index, ObjectRef tailCarry) {
        Object[] objArrPullLastBuffer;
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 5) {
            tailCarry.setValue(root[iIndexSegment]);
            objArrPullLastBuffer = null;
        } else {
            objArrPullLastBuffer = pullLastBuffer((Object[]) root[iIndexSegment], shift - 5, index, tailCarry);
        }
        if (objArrPullLastBuffer == null && iIndexSegment == 0) {
            return null;
        }
        Object[] objArrCopyOf = Arrays.copyOf(root, 32);
        objArrCopyOf[iIndexSegment] = objArrPullLastBuffer;
        return objArrCopyOf;
    }

    private final PersistentList<E> pullLastBufferFromRoot(Object[] root, int rootSize, int shift) {
        if (shift == 0) {
            if (root.length == 33) {
                root = Arrays.copyOf(root, 32);
            }
            return new SmallPersistentVector(root);
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArrPullLastBuffer = pullLastBuffer(root, shift, rootSize - 1, objectRef);
        Object[] objArr = (Object[]) objectRef.getValue();
        return objArrPullLastBuffer[1] == null ? new PersistentVector((Object[]) objArrPullLastBuffer[0], objArr, rootSize, shift - 5) : new PersistentVector(objArrPullLastBuffer, objArr, rootSize, shift);
    }

    private final PersistentVector<E> pushFilledTail(Object[] root, Object[] filledTail, Object[] newTail) {
        int size = size() >> 5;
        int i10 = this.rootShift;
        if (size <= (1 << i10)) {
            return new PersistentVector<>(pushTail(root, i10, filledTail), newTail, size() + 1, this.rootShift);
        }
        Object[] objArrPresizedBufferWith = UtilsKt.presizedBufferWith(root);
        int i11 = this.rootShift + 5;
        return new PersistentVector<>(pushTail(objArrPresizedBufferWith, i11, filledTail), newTail, size() + 1, i11);
    }

    private final Object[] pushTail(Object[] root, int shift, Object[] tail) {
        int iIndexSegment = UtilsKt.indexSegment(size() - 1, shift);
        Object[] objArrCopyOf = root != null ? Arrays.copyOf(root, 32) : new Object[32];
        if (shift == 5) {
            objArrCopyOf[iIndexSegment] = tail;
            return objArrCopyOf;
        }
        objArrCopyOf[iIndexSegment] = pushTail((Object[]) objArrCopyOf[iIndexSegment], shift - 5, tail);
        return objArrCopyOf;
    }

    private final Object[] removeFromRootAt(Object[] root, int shift, int index, ObjectRef tailCarry) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        if (shift == 0) {
            Object[] objArrCopyOf = iIndexSegment == 0 ? new Object[32] : Arrays.copyOf(root, 32);
            r.N(root, objArrCopyOf, iIndexSegment, iIndexSegment + 1, 32);
            objArrCopyOf[31] = tailCarry.getValue();
            tailCarry.setValue(root[iIndexSegment]);
            return objArrCopyOf;
        }
        int iIndexSegment2 = root[31] == null ? UtilsKt.indexSegment(rootSize() - 1, shift) : 31;
        Object[] objArrCopyOf2 = Arrays.copyOf(root, 32);
        int i10 = shift - 5;
        int i11 = iIndexSegment + 1;
        if (i11 <= iIndexSegment2) {
            while (true) {
                objArrCopyOf2[iIndexSegment2] = removeFromRootAt((Object[]) objArrCopyOf2[iIndexSegment2], i10, 0, tailCarry);
                if (iIndexSegment2 == i11) {
                    break;
                }
                iIndexSegment2--;
            }
        }
        objArrCopyOf2[iIndexSegment] = removeFromRootAt((Object[]) objArrCopyOf2[iIndexSegment], i10, index, tailCarry);
        return objArrCopyOf2;
    }

    private final PersistentList<E> removeFromTailAt(Object[] root, int rootSize, int shift, int index) {
        int size = size() - rootSize;
        CommonFunctionsKt.m3083assert(index < size);
        if (size == 1) {
            return pullLastBufferFromRoot(root, rootSize, shift);
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        int i10 = size - 1;
        if (index < i10) {
            r.N(this.tail, objArrCopyOf, index, index + 1, size);
        }
        objArrCopyOf[i10] = null;
        return new PersistentVector(root, objArrCopyOf, (rootSize + size) - 1, shift);
    }

    private final int rootSize() {
        return UtilsKt.rootSize(size());
    }

    private final Object[] setInRoot(Object[] root, int shift, int index, Object e5) {
        int iIndexSegment = UtilsKt.indexSegment(index, shift);
        Object[] objArrCopyOf = Arrays.copyOf(root, 32);
        if (shift == 0) {
            objArrCopyOf[iIndexSegment] = e5;
            return objArrCopyOf;
        }
        objArrCopyOf[iIndexSegment] = setInRoot((Object[]) objArrCopyOf[iIndexSegment], shift - 5, index, e5);
        return objArrCopyOf;
    }

    @Override // kotlin.collections.f, java.util.List
    public E get(int index) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        return (E) bufferFor(index)[index & 31];
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    public int getSize() {
        return this.size;
    }

    @Override // kotlin.collections.f, java.util.List
    public ListIterator<E> listIterator(int index) {
        ListImplementation.checkPositionIndex$runtime_release(index, size());
        return new PersistentVectorIterator(this.root, this.tail, index, size(), (this.rootShift / 5) + 1);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList<E> removeAt(int index) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        int iRootSize = rootSize();
        return index >= iRootSize ? removeFromTailAt(this.root, iRootSize, this.rootShift, index - iRootSize) : removeFromTailAt(removeFromRootAt(this.root, this.rootShift, index, new ObjectRef(this.tail[0])), iRootSize, this.rootShift, 0);
    }

    @Override // kotlin.collections.f, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList<E> set(int index, E element) {
        ListImplementation.checkElementIndex$runtime_release(index, size());
        if (rootSize() > index) {
            return new PersistentVector(setInRoot(this.root, this.rootShift, index, element), this.tail, size(), this.rootShift);
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        objArrCopyOf[index & 31] = element;
        return new PersistentVector(this.root, objArrCopyOf, size(), this.rootShift);
    }

    @Override // java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> add(E element) {
        int size = size() - rootSize();
        if (size >= 32) {
            return pushFilledTail(this.root, this.tail, UtilsKt.presizedBufferWith(element));
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        objArrCopyOf[size] = element;
        return new PersistentVector(this.root, objArrCopyOf, size() + 1, this.rootShift);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentList<E> removeAll(l<? super E, Boolean> predicate) {
        PersistentVectorBuilder<E> persistentVectorBuilderBuilder = builder();
        persistentVectorBuilderBuilder.removeAllWithPredicate(predicate);
        return persistentVectorBuilderBuilder.build();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection
    public PersistentVectorBuilder<E> builder() {
        return new PersistentVectorBuilder<>(this, this.root, this.tail, this.rootShift);
    }

    @Override // java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList<E> add(int index, E element) {
        ListImplementation.checkPositionIndex$runtime_release(index, size());
        if (index == size()) {
            return add((Object) element);
        }
        int iRootSize = rootSize();
        if (index >= iRootSize) {
            return insertIntoTail(this.root, index - iRootSize, element);
        }
        ObjectRef objectRef = new ObjectRef(null);
        return insertIntoTail(insertIntoRoot(this.root, this.rootShift, index, element, objectRef), 0, objectRef.getValue());
    }
}
