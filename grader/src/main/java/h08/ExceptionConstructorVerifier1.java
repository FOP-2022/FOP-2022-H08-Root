package h08;

import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.launcher.env.Jagr;


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
                super.visitVarInsn(Opcodes.ILOAD, 2);
                super.visitMethodInsn(
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
