package optpoc.keycloack.stockservice;

import lombok.Value;


//@Value
class Stock {
	
	private Long id;
	private String company;
    private String unitPrices;
    private String quantity;
    
    public Stock(Long id, String company, String unitPrices, String quantity) {
		super();
		this.id = id;
		this.company = company;
		this.unitPrices = unitPrices;
		this.quantity = quantity;
	}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUnitPrices() {
		return unitPrices;
	}
	public void setUnitPrices(String unitPrices) {
		this.unitPrices = unitPrices;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
    
	 @Override
	    public String toString() {
	        return "{id=" + id +
	                ", company=" + company +
	                ", unitPrices=" + unitPrices +
	                ", quantity=" + quantity + "}";
	}
}
