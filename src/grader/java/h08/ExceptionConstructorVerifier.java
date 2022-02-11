package h08;

import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

/**
 * verifier for h08.
 */
public class ExceptionConstructorVerifier implements ClassTransformer {

    public static @Nullable String descriptor = null;
    public static @Nullable Boolean isVar2 = null;
    public static final String correctDescriptor = "(Ljava/util/Calendar;Z)V";

    public static void handleConstructor(String descriptor, boolean correctConstructor) {
        ExceptionConstructorVerifier.descriptor = descriptor;
        ExceptionConstructorVerifier.isVar2 = correctConstructor;
    }

    public static void handleConstructor(String descriptor) {
        ExceptionConstructorVerifier.descriptor = descriptor;
        isVar2 = null; // is incorrect and could not be determined
    }

    public static void reset() {
        descriptor = null;
        isVar2 = null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if (reader.getClassName().equals("h08/BadUpdateTimeException")) {
            reader.accept(new CV(writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    static class CV extends ClassVisitor {

        public CV(ClassVisitor classVisitor) {
            super(Opcodes.ASM9, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if (name.equals("<init>")) {
                return new MV(super.visitMethod(access, name, descriptor, signature, exceptions), descriptor);
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        static class MV extends MethodVisitor {

            final String descriptor;

            public MV(MethodVisitor methodVisitor, String descriptor) {
                super(Opcodes.ASM9, methodVisitor);
                this.descriptor = descriptor;
            }

            @Override
            public void visitCode() {
                visitLdcInsn(descriptor);
                final String handleConstructorDescriptor;
                if (descriptor.equals(correctDescriptor)) {
                    // correct constructor
                    visitVarInsn(Opcodes.ILOAD, 2);
                    handleConstructorDescriptor = "(Ljava/lang/String;Z)V";
                } else {
                    handleConstructorDescriptor = "(Ljava/lang/String;)V";
                }
                visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "h08/ExceptionConstructorVerifier",
                    "handleConstructor",
                    handleConstructorDescriptor,
                    false
                );
                super.visitCode();
            }
        }
    }
}
