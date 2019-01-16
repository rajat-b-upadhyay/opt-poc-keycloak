package dasniko.customer;

import lombok.Value;


@Value
class Customer {
	
	
	private Long id;
    private String city;
    private String name;
    private String street;
    private String zip;
    private String country;
    
    public Customer(Long id, String city, String name, String street, String zip, String country) {
		super();
		this.id = id;
		this.city = city;
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.country = country;
	}
    
    
	public Long getId() {
		return id;
	}
	public String getCity() {
		return city;
	}
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public String getZip() {
		return zip;
	}
	public String getCountry() {
		return country;
	}

}
