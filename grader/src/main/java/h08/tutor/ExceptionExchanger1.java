package h08.tutor;

import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.launcher.env.Jagr;

import java.util.Calendar;


public class ExceptionExchanger1 implements ClassTransformer {

    public static void test(TimeStamp timestamp, Calendar calendar, int n) throws Exception {
        switch (n) {
            case 1:
                timestamp.updateWithExc1(calendar);
                break;
            case 2:
                timestamp.updateWithExc2(calendar);
                break;
            case 3:
                timestamp.updateWithExc3(calendar);
                break;
            case 4:
                timestamp.updateWithExc4(calendar);
                break;
            case 5:
                timestamp.updateWithExc5(calendar);
                break;
        }
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if (reader.getClassName().equals("h08/TestTimeStampException")) {
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
            if (name.equals("test")) {
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
