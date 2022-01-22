package h08lib;

import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.launcher.env.Jagr;


public class ExceptionConstructorVerifier1 implements ClassTransformer {

    public static boolean hasConstructor = false;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if (reader.getClassName().equals("h08/UpdateTimeBeforeLastUpdateException")) {
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
            Jagr.Default.getInjector().getInstance(Logger.class).info("CV: {} {}", name, descriptor);
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
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                Jagr.Default.getInjector().getInstance(Logger.class).info("MV: {} {}", name, descriptor);
                if (opcode == Opcodes.INVOKESPECIAL
                    && name.equals("<init>")
                    && descriptor.equals("(Ljava/util/Calendar;Z)V")) {
                    Jagr.Default.getInjector().getInstance(Logger.class).info("MV: in if");
                    hasConstructor = true;
                }
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }
        }
    }
}
