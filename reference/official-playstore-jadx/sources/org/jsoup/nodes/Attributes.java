package org.jsoup.nodes;

import androidx.compose.material3.d;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.SharedConstants;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Range;

/* JADX INFO: loaded from: classes5.dex */
public class Attributes implements Iterable<Attribute>, Cloneable {
    private static final String EmptyString = "";
    private static final int GrowthFactor = 2;
    private static final int InitialCapacity = 3;
    static final char InternalPrefix = '/';
    static final int NotFound = -1;
    protected static final String dataPrefix = "data-";
    private int size = 0;
    String[] keys = new String[3];
    Object[] vals = new Object[3];

    public static class Dataset extends AbstractMap<String, String> {
        private final Attributes attributes;

        public class DatasetIterator implements Iterator<Map.Entry<String, String>> {
            private Attribute attr;
            private Iterator<Attribute> attrIter;

            private DatasetIterator() {
                this.attrIter = Dataset.this.attributes.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                while (this.attrIter.hasNext()) {
                    Attribute next = this.attrIter.next();
                    this.attr = next;
                    if (next.isDataAttribute()) {
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                Dataset.this.attributes.remove(this.attr.getKey());
            }

            @Override // java.util.Iterator
            public Map.Entry<String, String> next() {
                return new Attribute(this.attr.getKey().substring(5), this.attr.getValue());
            }
        }

        public class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private EntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<String, String>> iterator() {
                return new DatasetIterator();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int i10 = 0;
                while (new DatasetIterator().hasNext()) {
                    i10++;
                }
                return i10;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<String, String>> entrySet() {
            return new EntrySet();
        }

        private Dataset(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public String put(String str, String str2) {
            String strDataKey = Attributes.dataKey(str);
            String str3 = this.attributes.hasKey(strDataKey) ? this.attributes.get(strDataKey) : null;
            this.attributes.put(strDataKey, str2);
            return str3;
        }
    }

    private void addObject(String str, Object obj) {
        checkCapacity(this.size + 1);
        String[] strArr = this.keys;
        int i10 = this.size;
        strArr[i10] = str;
        this.vals[i10] = obj;
        this.size = i10 + 1;
    }

    private void checkCapacity(int i10) {
        Validate.isTrue(i10 >= this.size);
        String[] strArr = this.keys;
        int length = strArr.length;
        if (length >= i10) {
            return;
        }
        int i11 = length >= 3 ? this.size * 2 : 3;
        if (i10 <= i11) {
            i10 = i11;
        }
        this.keys = (String[]) Arrays.copyOf(strArr, i10);
        this.vals = Arrays.copyOf(this.vals, i10);
    }

    public static String checkNotNull(Object obj) {
        return obj == null ? "" : (String) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String dataKey(String str) {
        return d.C(dataPrefix, str);
    }

    private int indexOfKeyIgnoreCase(String str) {
        Validate.notNull(str);
        for (int i10 = 0; i10 < this.size; i10++) {
            if (str.equalsIgnoreCase(this.keys[i10])) {
                return i10;
            }
        }
        return -1;
    }

    public static String internalKey(String str) {
        return d.C(DomExceptionUtils.SEPARATOR, str);
    }

    public static boolean isInternalKey(String str) {
        return str != null && str.length() > 1 && str.charAt(0) == '/';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove(int i10) {
        Validate.isFalse(i10 >= this.size);
        int i11 = (this.size - i10) - 1;
        if (i11 > 0) {
            String[] strArr = this.keys;
            int i12 = i10 + 1;
            System.arraycopy(strArr, i12, strArr, i10, i11);
            Object[] objArr = this.vals;
            System.arraycopy(objArr, i12, objArr, i10, i11);
        }
        int i13 = this.size - 1;
        this.size = i13;
        this.keys[i13] = null;
        this.vals[i13] = null;
    }

    public Attributes add(String str, String str2) {
        addObject(str, str2);
        return this;
    }

    public void addAll(Attributes attributes) {
        if (attributes.size() == 0) {
            return;
        }
        checkCapacity(this.size + attributes.size);
        boolean z = this.size != 0;
        for (Attribute attribute : attributes) {
            if (z) {
                put(attribute);
            } else {
                add(attribute.getKey(), attribute.getValue());
            }
        }
    }

    public List<Attribute> asList() {
        ArrayList arrayList = new ArrayList(this.size);
        for (int i10 = 0; i10 < this.size; i10++) {
            if (!isInternalKey(this.keys[i10])) {
                arrayList.add(new Attribute(this.keys[i10], (String) this.vals[i10], this));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Attribute attribute(String str) {
        int iIndexOfKey = indexOfKey(str);
        if (iIndexOfKey == -1) {
            return null;
        }
        return new Attribute(str, checkNotNull(this.vals[iIndexOfKey]), this);
    }

    public Map<String, String> dataset() {
        return new Dataset();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int deduplicate(org.jsoup.parser.ParseSettings r7) {
        /*
            r6 = this;
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            boolean r7 = r7.preserveAttributeCase()
            r0 = r1
        Ld:
            java.lang.String[] r2 = r6.keys
            int r2 = r2.length
            if (r1 >= r2) goto L43
            int r2 = r1 + 1
            r3 = r2
        L15:
            java.lang.String[] r4 = r6.keys
            int r5 = r4.length
            if (r3 >= r5) goto L41
            r5 = r4[r3]
            if (r5 != 0) goto L1f
            goto L41
        L1f:
            if (r7 == 0) goto L29
            r4 = r4[r1]
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L37
        L29:
            if (r7 != 0) goto L3e
            java.lang.String[] r4 = r6.keys
            r5 = r4[r1]
            r4 = r4[r3]
            boolean r4 = r5.equalsIgnoreCase(r4)
            if (r4 == 0) goto L3e
        L37:
            int r0 = r0 + 1
            r6.remove(r3)
            int r3 = r3 + (-1)
        L3e:
            int r3 = r3 + 1
            goto L15
        L41:
            r1 = r2
            goto Ld
        L43:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Attributes.deduplicate(org.jsoup.parser.ParseSettings):int");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.size != attributes.size) {
            return false;
        }
        for (int i10 = 0; i10 < this.size; i10++) {
            int iIndexOfKey = attributes.indexOfKey(this.keys[i10]);
            if (iIndexOfKey == -1) {
                return false;
            }
            Object obj2 = this.vals[i10];
            Object obj3 = attributes.vals[iIndexOfKey];
            if (obj2 == null) {
                if (obj3 != null) {
                    return false;
                }
            } else if (!obj2.equals(obj3)) {
                return false;
            }
        }
        return true;
    }

    public String get(String str) {
        int iIndexOfKey = indexOfKey(str);
        return iIndexOfKey == -1 ? "" : checkNotNull(this.vals[iIndexOfKey]);
    }

    public String getIgnoreCase(String str) {
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        return iIndexOfKeyIgnoreCase == -1 ? "" : checkNotNull(this.vals[iIndexOfKeyIgnoreCase]);
    }

    public Map<String, Range.AttributeRange> getRanges() {
        return (Map) userData(SharedConstants.AttrRangeKey);
    }

    public boolean hasDeclaredValueForKey(String str) {
        int iIndexOfKey = indexOfKey(str);
        return (iIndexOfKey == -1 || this.vals[iIndexOfKey] == null) ? false : true;
    }

    public boolean hasDeclaredValueForKeyIgnoreCase(String str) {
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        return (iIndexOfKeyIgnoreCase == -1 || this.vals[iIndexOfKeyIgnoreCase] == null) ? false : true;
    }

    public boolean hasKey(String str) {
        return indexOfKey(str) != -1;
    }

    public boolean hasKeyIgnoreCase(String str) {
        return indexOfKeyIgnoreCase(str) != -1;
    }

    public int hashCode() {
        return Arrays.hashCode(this.vals) + (((this.size * 31) + Arrays.hashCode(this.keys)) * 31);
    }

    public String html() {
        StringBuilder sbBorrowBuilder = StringUtil.borrowBuilder();
        try {
            html(sbBorrowBuilder, new Document("").outputSettings());
            return StringUtil.releaseBuilder(sbBorrowBuilder);
        } catch (IOException e5) {
            throw new SerializationException(e5);
        }
    }

    public int indexOfKey(String str) {
        Validate.notNull(str);
        for (int i10 = 0; i10 < this.size; i10++) {
            if (str.equals(this.keys[i10])) {
                return i10;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return new Iterator<Attribute>() { // from class: org.jsoup.nodes.Attributes.1
            int expectedSize;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            int f21010i = 0;

            {
                this.expectedSize = Attributes.this.size;
            }

            private void checkModified() {
                if (Attributes.this.size != this.expectedSize) {
                    throw new ConcurrentModificationException("Use Iterator#remove() instead to remove attributes while iterating.");
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                checkModified();
                while (this.f21010i < Attributes.this.size && Attributes.isInternalKey(Attributes.this.keys[this.f21010i])) {
                    this.f21010i++;
                }
                return this.f21010i < Attributes.this.size;
            }

            @Override // java.util.Iterator
            public void remove() {
                Attributes attributes = Attributes.this;
                int i10 = this.f21010i - 1;
                this.f21010i = i10;
                attributes.remove(i10);
                this.expectedSize--;
            }

            @Override // java.util.Iterator
            public Attribute next() {
                checkModified();
                if (this.f21010i >= Attributes.this.size) {
                    throw new NoSuchElementException();
                }
                Attributes attributes = Attributes.this;
                String[] strArr = attributes.keys;
                int i10 = this.f21010i;
                Attribute attribute = new Attribute(strArr[i10], (String) attributes.vals[i10], attributes);
                this.f21010i++;
                return attribute;
            }
        };
    }

    public void normalize() {
        for (int i10 = 0; i10 < this.size; i10++) {
            if (!isInternalKey(this.keys[i10])) {
                String[] strArr = this.keys;
                strArr[i10] = Normalizer.lowerCase(strArr[i10]);
            }
        }
    }

    public Attributes put(String str, String str2) {
        Validate.notNull(str);
        int iIndexOfKey = indexOfKey(str);
        if (iIndexOfKey != -1) {
            this.vals[iIndexOfKey] = str2;
            return this;
        }
        add(str, str2);
        return this;
    }

    public void putIgnoreCase(String str, String str2) {
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        if (iIndexOfKeyIgnoreCase == -1) {
            add(str, str2);
            return;
        }
        this.vals[iIndexOfKeyIgnoreCase] = str2;
        if (this.keys[iIndexOfKeyIgnoreCase].equals(str)) {
            return;
        }
        this.keys[iIndexOfKeyIgnoreCase] = str;
    }

    public void removeIgnoreCase(String str) {
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        if (iIndexOfKeyIgnoreCase != -1) {
            remove(iIndexOfKeyIgnoreCase);
        }
    }

    public int size() {
        return this.size;
    }

    public Range.AttributeRange sourceRange(String str) {
        if (!hasKey(str)) {
            return Range.AttributeRange.UntrackedAttr;
        }
        Map<String, Range.AttributeRange> ranges = getRanges();
        if (ranges == null) {
            return Range.AttributeRange.UntrackedAttr;
        }
        Range.AttributeRange attributeRange = ranges.get(str);
        return attributeRange != null ? attributeRange : Range.AttributeRange.UntrackedAttr;
    }

    public String toString() {
        return html();
    }

    public Map<String, Object> userData() {
        int iIndexOfKey = indexOfKey(SharedConstants.UserDataKey);
        if (iIndexOfKey != -1) {
            return (Map) this.vals[iIndexOfKey];
        }
        HashMap map = new HashMap();
        addObject(SharedConstants.UserDataKey, map);
        return map;
    }

    public Attributes clone() {
        try {
            Attributes attributes = (Attributes) super.clone();
            attributes.size = this.size;
            attributes.keys = (String[]) Arrays.copyOf(this.keys, this.size);
            attributes.vals = Arrays.copyOf(this.vals, this.size);
            return attributes;
        } catch (CloneNotSupportedException e5) {
            throw new RuntimeException(e5);
        }
    }

    public final void html(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        String validKey;
        int i10 = this.size;
        for (int i11 = 0; i11 < i10; i11++) {
            if (!isInternalKey(this.keys[i11]) && (validKey = Attribute.getValidKey(this.keys[i11], outputSettings.syntax())) != null) {
                Attribute.htmlNoValidate(validKey, (String) this.vals[i11], appendable.append(' '), outputSettings);
            }
        }
    }

    public Attributes put(String str, boolean z) {
        if (z) {
            putIgnoreCase(str, null);
            return this;
        }
        remove(str);
        return this;
    }

    public Object userData(String str) {
        Validate.notNull(str);
        if (hasKey(SharedConstants.UserDataKey)) {
            return userData().get(str);
        }
        return null;
    }

    public Attributes put(Attribute attribute) {
        Validate.notNull(attribute);
        put(attribute.getKey(), attribute.getValue());
        attribute.parent = this;
        return this;
    }

    public void remove(String str) {
        int iIndexOfKey = indexOfKey(str);
        if (iIndexOfKey != -1) {
            remove(iIndexOfKey);
        }
    }

    public Attributes userData(String str, Object obj) {
        Validate.notNull(str);
        userData().put(str, obj);
        return this;
    }
}
