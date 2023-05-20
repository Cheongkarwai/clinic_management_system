package cheong.clinic_management_system.dto;


public class OrderDetailsDTO {

	private int id;

	private String name;

	private double subtotal;

	private int quantity;

	private String description;
	
	private ItemDTO item;
	
	public OrderDetailsDTO() {
		
	}
	
	
	public OrderDetailsDTO(int id, String name, double subtotal, int quantity, String description, ItemDTO item) {
		this.id = id;
		this.name = name;
		this.subtotal = subtotal;
		this.quantity = quantity;
		this.description = description;
		this.item = item;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemDTO getItem() {
		return item;
	}

	public void setItem(ItemDTO item) {
		this.item = item;
	}
}
