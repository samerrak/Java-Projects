
public class Server extends Staff {
	
	private int targetTipPct;

	public Server(String name, int targetTipPct){
		super(name, false);
		this.targetTipPct = targetTipPct;
		/* TODO: Add your code here */
		/* TODO: Also edit the super call */

	}

	public int getTargetTipPct() {
		return targetTipPct;
	}
}
