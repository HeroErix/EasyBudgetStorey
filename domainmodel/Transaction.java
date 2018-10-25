package domainmodel;
import java.util.Date;

public class Transaction {
	private Category category;
	private double value;
	private final int id;
	private Date date;
	
	public Transaction(double val, Category cat, int id, Date date) {
		this.value = val;
		this.category = cat;
		this.id = id;
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category cat) {
		this.category = cat;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}