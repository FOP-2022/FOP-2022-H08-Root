package h08;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class TimeStampVisitor extends ClassVisitor {

    static boolean callsMethod = false;

    public static class TimeStampTransformer implements ClassTransformer {

        @Override
        public String getName() {
            return "TimeStamp";
        }

        @Override
        public void transform(ClassReader reader, ClassWriter writer) {
            if (reader.getClassName().equals(Type.getInternalName(TimeStamp.class))) {
                reader.accept(new TimeStampVisitor(writer), 0);
            } else {
                reader.accept(writer, 0);
            }
        }
    }

    public TimeStampVisitor(ClassVisitor visitor) {
        super(Opcodes.ASM9, visitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals("<init>") && descriptor.equals("()V")) {
            return new TSMethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    private static class TSMethodVisitor extends MethodVisitor {

        public TSMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM9, methodVisitor);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            if (owner.equals("h08/TimeStamp") && name.equals("update") && descriptor.equals("()V")) {
                callsMethod = true;
            }
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
}
