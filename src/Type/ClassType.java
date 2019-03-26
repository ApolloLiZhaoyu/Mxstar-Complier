package Type;

import Scope.ClassEntity;

public class ClassType extends Type {
    private ClassEntity classEntity;

    public ClassType(String name) {
        type = types.CLASS;
        typeName = name;
        classEntity = new ClassEntity();
    }

    public ClassType(String name, ClassEntity classEntity) {
        type = types.CLASS;
        typeName = name;
        this.classEntity = classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    @Override
    public boolean match(Type other) {
        if(other instanceof ClassType) {
            return typeName.equals(other.getTypeName());
        } else if(other.getType() == types.NULL) {
            return true;
        } else return false;
    }
}
