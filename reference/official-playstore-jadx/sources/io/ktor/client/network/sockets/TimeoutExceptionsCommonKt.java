package io.ktor.client.network.sockets;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.NalUnitUtil;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.InternalAPI;
import io.ktor.util.PlatformUtils;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0005\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0005\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0005\u0010\t¨\u0006\n"}, d2 = {"Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/client/request/HttpRequestData;", "request", "mapEngineExceptions", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/client/request/HttpRequestData;)Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "(Lka/k0;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/client/request/HttpRequestData;)Lio/ktor/utils/io/ByteWriteChannel;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimeoutExceptionsCommonKt {

    /* JADX INFO: renamed from: io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$1", f = "TimeoutExceptionsCommon.kt", l = {NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ByteReadChannel $input;
        final /* synthetic */ ByteChannel $replacementChannel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ByteReadChannel byteReadChannel, ByteChannel byteChannel, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$input = byteReadChannel;
            this.$replacementChannel = byteChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$input, this.$replacementChannel, dVar);
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnonymousClass1 anonymousClass1;
            Throwable th;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                try {
                    ByteReadChannel byteReadChannel = this.$input;
                    ByteChannel byteChannel = this.$replacementChannel;
                    this.label = 1;
                    anonymousClass1 = this;
                    try {
                        Object objCopyAndClose$default = ByteReadChannelKt.copyAndClose$default(byteReadChannel, byteChannel, 0L, anonymousClass1, 2, null);
                        a aVar = a.f15033i;
                        if (objCopyAndClose$default == aVar) {
                            return aVar;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        anonymousClass1.$input.cancel(th);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    anonymousClass1 = this;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    c.G(obj);
                } catch (Throwable th4) {
                    th = th4;
                    anonymousClass1 = this;
                    anonymousClass1.$input.cancel(th);
                }
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.network.sockets.TimeoutExceptionsCommonKt$mapEngineExceptions$2", f = "TimeoutExceptionsCommon.kt", l = {ColorSpace.MaxId}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ ByteWriteChannel $output;
        final /* synthetic */ ByteChannel $replacementChannel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteChannel byteChannel, ByteWriteChannel byteWriteChannel, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$replacementChannel = byteChannel;
            this.$output = byteWriteChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$replacementChannel, this.$output, dVar);
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass2) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnonymousClass2 anonymousClass2;
            Throwable th;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                try {
                    ByteChannel byteChannel = this.$replacementChannel;
                    ByteWriteChannel byteWriteChannel = this.$output;
                    this.label = 1;
                    anonymousClass2 = this;
                    try {
                        Object objCopyAndClose$default = ByteReadChannelKt.copyAndClose$default(byteChannel, byteWriteChannel, 0L, anonymousClass2, 2, null);
                        a aVar = a.f15033i;
                        if (objCopyAndClose$default == aVar) {
                            return aVar;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        anonymousClass2.$replacementChannel.close(th);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    anonymousClass2 = this;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    c.G(obj);
                } catch (Throwable th4) {
                    th = th4;
                    anonymousClass2 = this;
                    anonymousClass2.$replacementChannel.close(th);
                }
            }
            return t0.f22605a;
        }
    }

    @InternalAPI
    public static final ByteReadChannel mapEngineExceptions(k0 k0Var, ByteReadChannel byteReadChannel, HttpRequestData httpRequestData) {
        if (PlatformUtils.INSTANCE.getIS_NATIVE()) {
            return byteReadChannel;
        }
        ByteChannel byteChannelByteChannelWithMappedExceptions = TimeoutExceptionsKt.ByteChannelWithMappedExceptions(httpRequestData);
        CoroutinesKt.writer$default(k0Var, (d7.j) null, byteChannelByteChannelWithMappedExceptions, new AnonymousClass1(byteReadChannel, byteChannelByteChannelWithMappedExceptions, null), 1, (Object) null);
        return byteChannelByteChannelWithMappedExceptions;
    }

    @InternalAPI
    public static final ByteWriteChannel mapEngineExceptions(k0 k0Var, ByteWriteChannel byteWriteChannel, HttpRequestData httpRequestData) {
        if (PlatformUtils.INSTANCE.getIS_NATIVE()) {
            return byteWriteChannel;
        }
        ByteChannel byteChannelByteChannelWithMappedExceptions = TimeoutExceptionsKt.ByteChannelWithMappedExceptions(httpRequestData);
        CoroutinesKt.writer$default(k0Var, (d7.j) null, byteChannelByteChannelWithMappedExceptions, new AnonymousClass2(byteChannelByteChannelWithMappedExceptions, byteWriteChannel, null), 1, (Object) null);
        return byteChannelByteChannelWithMappedExceptions;
    }
}
