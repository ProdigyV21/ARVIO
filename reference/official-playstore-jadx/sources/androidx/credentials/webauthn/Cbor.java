package androidx.credentials.webauthn;

import a0.c;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import kotlin.text.a;
import qb.d;
import qb.l;
import x7.i;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002#$B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0001J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006%"}, d2 = {"Landroidx/credentials/webauthn/Cbor;", "", "()V", "TYPE_ARRAY", "", "getTYPE_ARRAY", "()I", "TYPE_BYTE_STRING", "getTYPE_BYTE_STRING", "TYPE_FLOAT", "getTYPE_FLOAT", "TYPE_MAP", "getTYPE_MAP", "TYPE_NEGATIVE_INT", "getTYPE_NEGATIVE_INT", "TYPE_TAG", "getTYPE_TAG", "TYPE_TEXT_STRING", "getTYPE_TEXT_STRING", "TYPE_UNSIGNED_INT", "getTYPE_UNSIGNED_INT", "createArg", "", LinkHeader.Parameters.Type, "arg", "", "decode", "data", "encode", "getArg", "Landroidx/credentials/webauthn/Cbor$Arg;", "offset", "getType", "parseItem", "Landroidx/credentials/webauthn/Cbor$Item;", "Arg", "Item", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Cbor {
    private final int TYPE_UNSIGNED_INT;
    private final int TYPE_NEGATIVE_INT = 1;
    private final int TYPE_BYTE_STRING = 2;
    private final int TYPE_TEXT_STRING = 3;
    private final int TYPE_ARRAY = 4;
    private final int TYPE_MAP = 5;
    private final int TYPE_TAG = 6;
    private final int TYPE_FLOAT = 7;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/credentials/webauthn/Cbor$Arg;", "", "arg", "", "len", "", "(JI)V", "getArg", "()J", "getLen", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Arg {
        private final long arg;
        private final int len;

        public Arg(long j10, int i10) {
            this.arg = j10;
            this.len = i10;
        }

        public static /* synthetic */ Arg copy$default(Arg arg, long j10, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                j10 = arg.arg;
            }
            if ((i11 & 2) != 0) {
                i10 = arg.len;
            }
            return arg.copy(j10, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getArg() {
            return this.arg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getLen() {
            return this.len;
        }

        public final Arg copy(long arg, int len) {
            return new Arg(arg, len);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Arg)) {
                return false;
            }
            Arg arg = (Arg) other;
            return this.arg == arg.arg && this.len == arg.len;
        }

        public final long getArg() {
            return this.arg;
        }

        public final int getLen() {
            return this.len;
        }

        public int hashCode() {
            long j10 = this.arg;
            return (((int) (j10 ^ (j10 >>> 32))) * 31) + this.len;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Arg(arg=");
            sb2.append(this.arg);
            sb2.append(", len=");
            return c.o(sb2, this.len, ')');
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/credentials/webauthn/Cbor$Item;", "", "item", "len", "", "(Ljava/lang/Object;I)V", "getItem", "()Ljava/lang/Object;", "getLen", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Item {
        private final Object item;
        private final int len;

        public Item(Object obj, int i10) {
            this.item = obj;
            this.len = i10;
        }

        public static /* synthetic */ Item copy$default(Item item, Object obj, int i10, int i11, Object obj2) {
            if ((i11 & 1) != 0) {
                obj = item.item;
            }
            if ((i11 & 2) != 0) {
                i10 = item.len;
            }
            return item.copy(obj, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Object getItem() {
            return this.item;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getLen() {
            return this.len;
        }

        public final Item copy(Object item, int len) {
            return new Item(item, len);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item) other;
            return p.a(this.item, item.item) && this.len == item.len;
        }

        public final Object getItem() {
            return this.item;
        }

        public final int getLen() {
            return this.len;
        }

        public int hashCode() {
            return (this.item.hashCode() * 31) + this.len;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Item(item=");
            sb2.append(this.item);
            sb2.append(", len=");
            return c.o(sb2, this.len, ')');
        }
    }

    private final byte[] createArg(int type, long arg) {
        int i10 = type << 5;
        int i11 = (int) arg;
        if (arg < 24) {
            return new byte[]{(byte) ((i10 | i11) & 255)};
        }
        if (arg <= 255) {
            return new byte[]{(byte) ((i10 | 24) & 255), (byte) (i11 & 255)};
        }
        if (arg <= 65535) {
            return new byte[]{(byte) ((i10 | 25) & 255), (byte) ((i11 >> 8) & 255), (byte) (i11 & 255)};
        }
        if (arg <= 4294967295L) {
            return new byte[]{(byte) ((i10 | 26) & 255), (byte) ((i11 >> 24) & 255), (byte) ((i11 >> 16) & 255), (byte) ((i11 >> 8) & 255), (byte) (i11 & 255)};
        }
        throw new IllegalArgumentException("bad Arg");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int encode$lambda$0(k0 k0Var, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = (byte[]) ((Map) k0Var.f19746i).get(bArr);
        byte[] bArr4 = (byte[]) ((Map) k0Var.f19746i).get(bArr2);
        if (bArr.length > bArr2.length) {
            return 1;
        }
        if (bArr.length < bArr2.length) {
            return -1;
        }
        if (bArr3.length > bArr4.length) {
            return 1;
        }
        return bArr3.length < bArr4.length ? -1 : 0;
    }

    private final Arg getArg(byte[] data, int offset) {
        long j10 = ((long) data[offset]) & 31;
        if (j10 < 24) {
            return new Arg(j10, 1);
        }
        if (j10 == 24) {
            return new Arg(((long) data[offset + 1]) & 255, 2);
        }
        if (j10 == 25) {
            return new Arg((((long) data[offset + 2]) & 255) | ((((long) data[offset + 1]) & 255) << 8), 3);
        }
        if (j10 != 26) {
            throw new IllegalArgumentException("Bad arg");
        }
        return new Arg((((long) data[offset + 4]) & 255) | ((((long) data[offset + 1]) & 255) << 24) | ((((long) data[offset + 2]) & 255) << 16) | ((((long) data[offset + 3]) & 255) << 8), 5);
    }

    private final int getType(byte[] data, int offset) {
        return (data[offset] & 255) >> 5;
    }

    private final Item parseItem(byte[] data, int offset) {
        byte[] bArrCopyOfRange;
        byte[] bArrCopyOfRange2;
        int type = getType(data, offset);
        Arg arg = getArg(data, offset);
        System.out.println((Object) ("Type " + type + ' ' + arg.getArg() + ' ' + arg.getLen()));
        if (type == this.TYPE_UNSIGNED_INT) {
            return new Item(Long.valueOf(arg.getArg()), arg.getLen());
        }
        if (type == this.TYPE_NEGATIVE_INT) {
            return new Item(Long.valueOf(((long) (-1)) - arg.getArg()), arg.getLen());
        }
        int i10 = 0;
        if (type == this.TYPE_BYTE_STRING) {
            i iVarX = d.X(arg.getLen() + offset, arg.getLen() + offset + ((int) arg.getArg()));
            if (iVarX.isEmpty()) {
                bArrCopyOfRange2 = new byte[0];
            } else {
                int i11 = iVarX.f22619i;
                int i12 = iVarX.f22620l + 1;
                l.j(i12, data.length);
                bArrCopyOfRange2 = Arrays.copyOfRange(data, i11, i12);
            }
            return new Item(bArrCopyOfRange2, arg.getLen() + ((int) arg.getArg()));
        }
        if (type == this.TYPE_TEXT_STRING) {
            i iVarX2 = d.X(arg.getLen() + offset, arg.getLen() + offset + ((int) arg.getArg()));
            if (iVarX2.isEmpty()) {
                bArrCopyOfRange = new byte[0];
            } else {
                int i13 = iVarX2.f22619i;
                int i14 = iVarX2.f22620l + 1;
                l.j(i14, data.length);
                bArrCopyOfRange = Arrays.copyOfRange(data, i13, i14);
            }
            return new Item(new String(bArrCopyOfRange, a.f19924a), arg.getLen() + ((int) arg.getArg()));
        }
        if (type == this.TYPE_ARRAY) {
            ArrayList arrayList = new ArrayList();
            int len = arg.getLen();
            int arg2 = (int) arg.getArg();
            while (i10 < arg2) {
                Item item = parseItem(data, offset + len);
                arrayList.add(item.getItem());
                len += item.getLen();
                i10++;
            }
            return new Item(x.c1(arrayList), len);
        }
        if (type != this.TYPE_MAP) {
            throw new IllegalArgumentException("Bad type");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int len2 = arg.getLen();
        int arg3 = (int) arg.getArg();
        while (i10 < arg3) {
            Item item2 = parseItem(data, offset + len2);
            int len3 = item2.getLen() + len2;
            Item item3 = parseItem(data, offset + len3);
            int len4 = len3 + item3.getLen();
            linkedHashMap.put(item2.getItem(), item3.getItem());
            i10++;
            len2 = len4;
        }
        return new Item(h0.B0(linkedHashMap), len2);
    }

    public final Object decode(byte[] data) {
        return parseItem(data, 0).getItem();
    }

    public final byte[] encode(Object data) {
        if (data instanceof Number) {
            if (data instanceof Double) {
                throw new IllegalArgumentException("Don't support doubles yet");
            }
            long jLongValue = ((Number) data).longValue();
            return jLongValue >= 0 ? createArg(this.TYPE_UNSIGNED_INT, jLongValue) : createArg(this.TYPE_NEGATIVE_INT, ((long) (-1)) - jLongValue);
        }
        if (data instanceof byte[]) {
            return r.i0(createArg(this.TYPE_BYTE_STRING, r6.length), (byte[]) data);
        }
        if (data instanceof String) {
            return r.i0(createArg(this.TYPE_TEXT_STRING, r6.length()), ((String) data).getBytes(a.f19924a));
        }
        if (data instanceof List) {
            byte[] bArrCreateArg = createArg(this.TYPE_ARRAY, r6.size());
            Iterator it = ((List) data).iterator();
            while (it.hasNext()) {
                bArrCreateArg = r.i0(bArrCreateArg, encode(it.next()));
            }
            return bArrCreateArg;
        }
        if (!(data instanceof Map)) {
            throw new IllegalArgumentException("Bad type");
        }
        byte[] bArrCreateArg2 = createArg(this.TYPE_MAP, r6.size());
        k0 k0Var = new k0();
        k0Var.f19746i = new LinkedHashMap();
        for (Map.Entry entry : ((Map) data).entrySet()) {
            ((Map) k0Var.f19746i).put(encode(entry.getKey()), encode(entry.getValue()));
        }
        ArrayList<byte[]> arrayList = new ArrayList(((Map) k0Var.f19746i).keySet());
        x.W0(arrayList, new a7.a(k0Var, 3));
        for (byte[] bArr : arrayList) {
            bArrCreateArg2 = r.i0(r.i0(bArrCreateArg2, bArr), (byte[]) ((Map) k0Var.f19746i).get(bArr));
        }
        return bArrCreateArg2;
    }

    public final int getTYPE_ARRAY() {
        return this.TYPE_ARRAY;
    }

    public final int getTYPE_BYTE_STRING() {
        return this.TYPE_BYTE_STRING;
    }

    public final int getTYPE_FLOAT() {
        return this.TYPE_FLOAT;
    }

    public final int getTYPE_MAP() {
        return this.TYPE_MAP;
    }

    public final int getTYPE_NEGATIVE_INT() {
        return this.TYPE_NEGATIVE_INT;
    }

    public final int getTYPE_TAG() {
        return this.TYPE_TAG;
    }

    public final int getTYPE_TEXT_STRING() {
        return this.TYPE_TEXT_STRING;
    }

    public final int getTYPE_UNSIGNED_INT() {
        return this.TYPE_UNSIGNED_INT;
    }
}
