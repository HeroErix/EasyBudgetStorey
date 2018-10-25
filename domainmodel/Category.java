package domainmodel;

public class Category {
	private String title;
	private double limit;
	private boolean alert;
	
	public Category(String title, double limit) {
		this.title = title;
		this.limit = limit;
		this.alert = true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	public void disable() {
		this.alert = false;
	}
	
	public void enable() {
		this.alert = true;
	}
}