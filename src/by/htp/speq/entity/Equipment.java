package by.htp.speq.entity;

public class Equipment {
	
	public static enum Type{
		OUTFIT,
		ACCESSORY
	}
	
	String title;
	Type type;
	double hourRate;
	
	public Equipment() {
		
	}

	public Equipment(String title, Type type, double hourRate) {
		super();
		this.title = title;
		this.type = type;
		this.hourRate = hourRate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getHourRate() {
		return hourRate;
	}

	public void setHourRate(double hourRate) {
		this.hourRate = hourRate;
	}

	@Override
	public String toString() {
		return "Equipment [title=" + title + ", type=" + type + ", hourRate=" + hourRate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(hourRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (Double.doubleToLongBits(hourRate) != Double.doubleToLongBits(other.hourRate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}	
	
}
