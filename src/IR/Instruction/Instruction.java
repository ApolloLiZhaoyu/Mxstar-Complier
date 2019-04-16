package IR.Instruction;

import IR.BasicBlock;
import IR.IRVistor;
import IR.Operand.Register;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {
    private BasicBlock bb;
    private Instruction prev;
    private Instruction next;
    private boolean removed;
    protected List<Register> usedRegs;
    protected List<Register> definedRegs;

    public Instruction(BasicBlock bb) {
        this.bb = bb;
        prev = null;
        next = null;
        removed = false;
        usedRegs = new ArrayList<>();
        definedRegs = new ArrayList<>();
    }

    public BasicBlock getBB() {
        return bb;
    }

    public void setPrev(Instruction prev) {
        this.prev = prev;
    }

    public Instruction getPrev() {
        return prev;
    }

    public Instruction getNext() {
        return next;
    }

    public void setNext(Instruction next) {
        this.next = next;
    }

    public void prepend(Instruction inst) {
        if(prev == null) {
            inst.next = this;
            this.prev = inst;
            bb.setHead(inst);
        } else {
            prev.next = inst;
            inst.prev = next;
            inst.next = this;
            this.prev = inst;
        }
    }

    public void append(Instruction inst) {
        if(next == null) {
            inst.prev = this;
            this.next = inst;
            bb.setTail(inst);
        } else {
            next.prev = inst;
            inst.prev = this;
            inst.next = next;
            this.next = inst;
        }
    }

    public void remove() {
        removed = true;
        if (prev == null) {
            bb.setHead(next);
        } else {
            prev.next = next;
        }
        if (next == null) {
            bb.setTail(prev);
        } else {
            next.prev = prev;
        }
    }

    public void replace(Instruction inst) {
        inst.prev = prev;
        inst.next = next;
        if(prev == null) {
            bb.setHead(inst);
        } else {
            prev.next = inst;
        }
        if(next == null) {
            bb.setTail(inst);
        } else {
            next.prev = inst;
        }
    }

    public boolean isRemoved() {
        return removed;
    }

    public abstract void accept(IRVistor vistor);
}