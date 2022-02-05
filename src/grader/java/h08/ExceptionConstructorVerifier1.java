package h08;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class ExceptionConstructorVerifier1 implements ClassTransformer {

    public static boolean hasConstructor = false;

    public static void setsTrueInConstructor(boolean b) {

        hasConstructor = b;
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

    class CV extends ClassVisitor {

        public CV(ClassVisitor classVisitor) {
            super(Opcodes.ASM9, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

            if (name.equals("<init>")) {
                return new MV(super.visitMethod(access, name, descriptor, signature, exceptions));
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }

        class MV extends MethodVisitor {

            public MV(MethodVisitor methodVisitor) {
                super(Opcodes.ASM9, methodVisitor);
            }

            @Override
            public void visitCode() {
                visitVarInsn(Opcodes.ILOAD, 2);
                visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "h08/ExceptionConstructorVerifier1",
                    "setsTrueInConstructor",
                    "(Z)V",
                    false
                );
                super.visitCode();
            }
        }
    }
}
