package io.ktor.util;

import d7.d;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0011\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0019\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001f\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"\u0088\u0001\u0003\u0092\u0001\u00020\u0002ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/util/DigestImpl;", "Lio/ktor/util/Digest;", "Ljava/security/MessageDigest;", "delegate", "constructor-impl", "(Ljava/security/MessageDigest;)Ljava/security/MessageDigest;", "", "bytes", "Lx6/t0;", "plusAssign-impl", "(Ljava/security/MessageDigest;[B)V", "plusAssign", "reset-impl", "(Ljava/security/MessageDigest;)V", "reset", "build-impl", "(Ljava/security/MessageDigest;Ld7/d;)Ljava/lang/Object;", "build", "", "toString-impl", "(Ljava/security/MessageDigest;)Ljava/lang/String;", "toString", "", "hashCode-impl", "(Ljava/security/MessageDigest;)I", "hashCode", "", "other", "", "equals-impl", "(Ljava/security/MessageDigest;Ljava/lang/Object;)Z", "equals", "Ljava/security/MessageDigest;", "getDelegate", "()Ljava/security/MessageDigest;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
final class DigestImpl implements Digest {
    private final MessageDigest delegate;

    private /* synthetic */ DigestImpl(MessageDigest messageDigest) {
        this.delegate = messageDigest;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DigestImpl m6720boximpl(MessageDigest messageDigest) {
        return new DigestImpl(messageDigest);
    }

    /* JADX INFO: renamed from: build-impl, reason: not valid java name */
    public static Object m6721buildimpl(MessageDigest messageDigest, d<? super byte[]> dVar) {
        return messageDigest.digest();
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static MessageDigest m6722constructorimpl(MessageDigest messageDigest) {
        return messageDigest;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6723equalsimpl(MessageDigest messageDigest, Object obj) {
        return (obj instanceof DigestImpl) && p.a(messageDigest, ((DigestImpl) obj).m6729unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6724equalsimpl0(MessageDigest messageDigest, MessageDigest messageDigest2) {
        return p.a(messageDigest, messageDigest2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6725hashCodeimpl(MessageDigest messageDigest) {
        return messageDigest.hashCode();
    }

    /* JADX INFO: renamed from: plusAssign-impl, reason: not valid java name */
    public static void m6726plusAssignimpl(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
    }

    /* JADX INFO: renamed from: reset-impl, reason: not valid java name */
    public static void m6727resetimpl(MessageDigest messageDigest) {
        messageDigest.reset();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6728toStringimpl(MessageDigest messageDigest) {
        return "DigestImpl(delegate=" + messageDigest + ')';
    }

    @Override // io.ktor.util.Digest
    public Object build(d<? super byte[]> dVar) {
        return m6721buildimpl(this.delegate, dVar);
    }

    public boolean equals(Object obj) {
        return m6723equalsimpl(this.delegate, obj);
    }

    public final MessageDigest getDelegate() {
        return this.delegate;
    }

    public int hashCode() {
        return m6725hashCodeimpl(this.delegate);
    }

    @Override // io.ktor.util.Digest
    public void plusAssign(byte[] bArr) {
        m6726plusAssignimpl(this.delegate, bArr);
    }

    @Override // io.ktor.util.Digest
    public void reset() {
        m6727resetimpl(this.delegate);
    }

    public String toString() {
        return m6728toStringimpl(this.delegate);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ MessageDigest m6729unboximpl() {
        return this.delegate;
    }
}
