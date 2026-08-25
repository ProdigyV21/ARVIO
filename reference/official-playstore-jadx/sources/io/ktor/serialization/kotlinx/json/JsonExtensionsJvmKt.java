package io.ktor.serialization.kotlinx.json;

import d7.d;
import db.b;
import eb.d0;
import eb.q;
import eb.v;
import f7.e;
import f7.j;
import ga.a;
import ga.m;
import h.f0;
import io.ktor.serialization.kotlinx.SerializerLookupKt;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import k2.c;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.p;
import x6.t0;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a5\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Ldb/b;", "format", "Lio/ktor/utils/io/ByteReadChannel;", "content", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "Lga/m;", "", "deserializeSequence", "(Ldb/b;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "ktor-serialization-kotlinx-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JsonExtensionsJvmKt {

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.json.JsonExtensionsJvmKt$deserializeSequence$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lga/m;", "", "<anonymous>", "(Lka/k0;)Lga/m;"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.serialization.kotlinx.json.JsonExtensionsJvmKt$deserializeSequence$2", f = "JsonExtensionsJvm.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super m<? extends Object>>, Object> {
        final /* synthetic */ ByteReadChannel $content;
        final /* synthetic */ b $format;
        final /* synthetic */ TypeInfo $typeInfo;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteReadChannel byteReadChannel, TypeInfo typeInfo, b bVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$content = byteReadChannel;
            this.$typeInfo = typeInfo;
            this.$format = bVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$content, this.$typeInfo, this.$format, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws IllegalAccessException, InvocationTargetException {
            int i10;
            Object qVar;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            InputStream inputStream$default = BlockingKt.toInputStream$default(this.$content, null, 1, null);
            h<?> hVarSerializerForTypeInfo = SerializerLookupKt.serializerForTypeInfo(this.$format.f14879b, KotlinxSerializationJsonExtensionsKt.argumentTypeInfo(this.$typeInfo));
            b bVar = this.$format;
            d0 d0Var = new d0(new a8.e(inputStream$default), new char[16384]);
            int iC = f0.c(3);
            if (iC == 0) {
                i10 = 1;
            } else {
                if (iC != 1) {
                    if (iC != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    if (d0Var.w() == 8) {
                        d0Var.i((byte) 8);
                    }
                    i10 = 1;
                } else {
                    if (d0Var.w() != 8) {
                        d0Var.t((byte) 8);
                        throw null;
                    }
                    d0Var.i((byte) 8);
                }
                i10 = 2;
            }
            int iC2 = f0.c(i10);
            if (iC2 == 0) {
                qVar = new q(bVar, d0Var, hVarSerializerForTypeInfo);
            } else {
                if (iC2 != 1) {
                    if (iC2 != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new IllegalStateException("AbstractJsonLexer.determineFormat must be called beforehand.");
                }
                qVar = new eb.p(bVar, d0Var, hVarSerializerForTypeInfo);
            }
            return new a(new v(qVar, 0));
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super m<? extends Object>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final Object deserializeSequence(b bVar, ByteReadChannel byteReadChannel, TypeInfo typeInfo, d<? super m<? extends Object>> dVar) {
        return m0.y(x0.f19655d, new AnonymousClass2(byteReadChannel, typeInfo, bVar, null), dVar);
    }
}
