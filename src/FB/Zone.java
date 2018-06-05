package FB;

public class Zone {

	private static int counter = 0;
	private State state;
	private int x;
	private int y;
	private Ile ile;
	private int id;
	private Boolean hasPlayer = false;
	private Boolean isSpecial = false;
	
	public Zone() {
		this.state = State.NORMAL;
		this.x = 0;
		this.y = 0;
		this.id = counter;
		counter++;
	}
	
	public Zone(State etat) {
		this.state = etat;
		this.x = 0;
		this.y = 0;
		this.id = counter;
		counter++;
	}
	
	public Zone(Ile ile) {
		this.state = State.NORMAL;
		this.ile = ile;
		this.x = 0;
		this.y = 0;
		this.id = counter;
		counter++;
	}
	
	public Zone(Ile ile, int x, int y) {
		this.state = State.NORMAL;
		this.ile = ile;
		this.x = x;
		this.y = y;
		this.id = counter;
		this.isSpecial = false;
		counter++;
	}
	
	/**
	 * 
	 * @return L etat de la zone.
	 */
	//Test fait
	public State getState() {
		return this.state;
	}
	
	/**
	 *  Affiche pour chaque zone son 
	 * 
	 */
	
	public String toString() {
		return "Zone" + " "+Integer.toString(this.id-(this.ile.getWidth()*this.ile.height())) + "=" + state ;
	}
	
	/**
	 * Asseche la zone
	 */
	
	//Test fait
	public void dry(){
		if(this.state == State.DROWNING)this.state = State.NORMAL;
		else if(this.state == State.DROWN)this.state = State.DROWN;
	}
	
	/**
	 * Innonde la zone
	 */
	
	//Test fait
	public void drowning(){
		if (this.state == State.NORMAL) this.state = State.DROWNING;
		else if (this.state == State.DROWNING) this.state = State.DROWN;
	}
	
	public Boolean hasKey() {
		return false;
	}
	
	//Test
	public int getX() {
		return this.x;
	}
	
	//Test
	public int getY() {
		return this.y;
	}
	
	public void hasPlayer() {
		this.hasPlayer = true;
	}
	
	public void noMorePlayer() {
		this.hasPlayer = false;
	}
	
	public Boolean getHasPlayer() {
		return this.hasPlayer;
	}
	
	public void setToSpecial() {
		this.isSpecial = true;
	}
	
	public Boolean isSpecial() {
		return this.isSpecial;
	}
}
