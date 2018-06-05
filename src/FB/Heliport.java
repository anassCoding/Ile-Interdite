package FB;
public class Heliport extends Zone{
	private Boolean isHeliport;
	private Ile ile;
	private int x;
	private int y;
	
	public Heliport(Ile ile, int x, int y, Boolean h) {
		this.ile = ile;
		this.x = x;
		this.y = y;
		this.isHeliport = h;
	}
	
	public Boolean isH() {
		return this.isHeliport;
	}
}
