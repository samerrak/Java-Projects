
public class WorkingOwner extends Owner {

    private int targetTipPct;

    public WorkingOwner(String name, int targetTipPct) {
        super(name);
        this.targetTipPct = targetTipPct;
        /* TODO: Add your code here */
        /* TODO: Also edit the super call */

    }

    public int getTargetTipPct() {
        return targetTipPct;
    }
}
