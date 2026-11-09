package arvio.poc

import desktop.config.Paths
import org.objectweb.asm.*
import java.io.File
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import java.util.zip.ZipEntry
import java.security.MessageDigest

/**
 * Experimental, explicitly partial compatibility for the pinned StreamPlay v694 only.
 * dex2jar emits invokeMovieBox with 74,872 bytes of code; JVM limit is 65,535. This prevents
 * the entire extractor class from loading, including unrelated providers. Fail that method
 * explicitly instead of silently fabricating a result. The original .cs3 is never modified.
 */
fun prepareJvmCompatibility(): Boolean {
    val jar = File(Paths.dataDir, "repo").walkTopDown().firstOrNull {
        it.name == "StreamPlay-v694-a63bd63f0203-converted.jar"
    } ?: return false
    val cs3 = File(requireNotNull(System.getenv("ARVIO_CS_PINNED_CS3")))
    val digest = MessageDigest.getInstance("SHA-256").digest(cs3.readBytes()).joinToString("") { "%02x".format(it) }
    require(digest == "a63bd63f0203db0c62ed0e85a1d3f039a9e98d7cdc9fa79f233af6cce825f805")
    val marker = "META-INF/arvio-poc-moviebox-disabled.txt"
    val temp = File(jar.parentFile, "${jar.name}.poc.tmp")
    ZipFile(jar).use { input ->
        if (input.getEntry(marker) != null) return false
        ZipOutputStream(temp.outputStream()).use { output ->
            for (entry in input.entries().asSequence()) {
                output.putNextEntry(ZipEntry(entry.name))
                var bytes = input.getInputStream(entry).use { it.readBytes() }
                if (entry.name == "com/phisher98/StreamPlayExtractor.class") {
                    val reader = ClassReader(bytes)
                    val writer = ClassWriter(reader, 0)
                    var replaced = 0
                    reader.accept(object : ClassVisitor(Opcodes.ASM9, writer) {
                        override fun visitMethod(access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
                            val method = super.visitMethod(access, name, descriptor, signature, exceptions)
                            if (name != "invokeMovieBox") return method
                            replaced++
                            method.visitCode()
                            method.visitTypeInsn(Opcodes.NEW, "java/lang/UnsupportedOperationException")
                            method.visitInsn(Opcodes.DUP)
                            method.visitLdcInsn("MovieBox is unsupported by this JVM proof of concept (method size limit)")
                            method.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V", false)
                            method.visitInsn(Opcodes.ATHROW)
                            method.visitMaxs(3, Type.getArgumentsAndReturnSizes(descriptor) shr 2)
                            method.visitEnd()
                            return null
                        }
                    }, 0)
                    require(replaced == 1) { "Unexpected StreamPlay binary" }
                    bytes = writer.toByteArray()
                }
                output.write(bytes)
                output.closeEntry()
            }
            output.putNextEntry(ZipEntry(marker))
            output.write("Local POC only: invokeMovieBox is unsupported after DEX-to-JVM conversion.".toByteArray())
            output.closeEntry()
        }
    }
    temp.copyTo(jar, overwrite = true)
    check(temp.delete())
    println("POC compatibility: MovieBox explicitly disabled; original StreamPlay .cs3 unchanged")
    return true
}
