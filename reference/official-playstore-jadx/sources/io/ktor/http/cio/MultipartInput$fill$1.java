package io.ktor.http.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)I"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.http.cio.MultipartInput$fill$1", f = "CIOMultipartDataBase.kt", l = {208}, m = "invokeSuspend")
public final class MultipartInput$fill$1 extends j implements p<k0, d<? super Integer>, Object> {
    final /* synthetic */ ByteBuffer $destination;
    final /* synthetic */ int $length;
    final /* synthetic */ int $offset;
    Object L$0;
    int label;
    final /* synthetic */ MultipartInput this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartInput$fill$1(MultipartInput multipartInput, int i10, ByteBuffer byteBuffer, int i11, d<? super MultipartInput$fill$1> dVar) {
        super(2, dVar);
        this.this$0 = multipartInput;
        this.$length = i10;
        this.$destination = byteBuffer;
        this.$offset = i11;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new MultipartInput$fill$1(this.this$0, this.$length, this.$destination, this.$offset, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v4, types: [byte[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ?? r02 = this.label;
        try {
            if (r02 == 0) {
                c.G(obj);
                byte[] bArrBorrow = ByteArrayPoolKt.getByteArrayPool().borrow();
                ByteReadChannel byteReadChannel = this.this$0.tail;
                int iMin = Math.min(this.$length, bArrBorrow.length);
                this.L$0 = bArrBorrow;
                this.label = 1;
                obj = byteReadChannel.readAvailable(bArrBorrow, 0, iMin, this);
                a aVar = a.f15033i;
                r02 = bArrBorrow;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (r02 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                byte[] bArr = (byte[]) this.L$0;
                c.G(obj);
                r02 = bArr;
            }
            int iIntValue = ((Number) obj).intValue();
            if (iIntValue < 0) {
                iIntValue = 0;
            }
            Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(r02, 0, iIntValue).slice().order(ByteOrder.BIG_ENDIAN)), this.$destination, 0, iIntValue, this.$offset);
            ByteArrayPoolKt.getByteArrayPool().recycle(r02);
            return new Integer(iIntValue);
        } catch (Throwable th) {
            ByteArrayPoolKt.getByteArrayPool().recycle(r02);
            throw th;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Integer> dVar) {
        return ((MultipartInput$fill$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
