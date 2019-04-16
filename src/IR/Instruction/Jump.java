package IR.Instruction;

import IR.BasicBlock;
import IR.IRVistor;

public class Jump extends Instruction {
    private BasicBlock targetBB;

    public Jump(BasicBlock bb, BasicBlock targetBB) {
        super(bb);
        this.targetBB = targetBB;
    }

    public BasicBlock getTargetBB() {
        return targetBB;
    }

    @Override
    public void accept(IRVistor vistor) {
        vistor.visit(this);
    }
}