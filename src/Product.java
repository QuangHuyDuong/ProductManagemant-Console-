import java.io.Serializable;
import java.util.Scanner;



public class Product implements IProduct,Serializable{
	private String productId;
	private String productName;
	private String title;
	private float importPrice;
	private float exportPrice;
	private float profit;
	private boolean productStatus;
	private String desciptions;
	private Categories catalog;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
			float profit, boolean productStatus, String desciptions, Categories catalog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.productStatus = productStatus;
		this.desciptions = desciptions;
		this.catalog = catalog;
	}


	@Override
	public void inputData() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Nhap tieu de san pham :");
		do {
			this.title= sc.nextLine();
			if(this.title.length()<6 || this.title.length()>30) {
				System.err.println("Tieu de san pham phai co do dai tu 6-30 ki tu");
			} else break;
		} while (true);
		System.out.println("Gia nhap san pham :");
		// Kiem tra xem gia tri ham enterImportPrice co lon hon khong hay khong, neu khong thuc hien lai ham do. neu co gan gia tri cho importprice
		do {
			this.importPrice=enterImportPrice();
			if(this.importPrice < 0 ) {
				System.err.println("Gia nhap phai lon hon 0");
			} else break;
		} while (true);
		//Kiem tra gia ban
		System.out.println("Gia ban san pham :");
		do {
			this.exportPrice=enterExportPrice();
			if(this.exportPrice < 1.2 * this.importPrice ) {
				System.err.println("Gia ban phai lon hon 20% gia nhap");
			} else break;
		} while (true);
		// Nhap mo ta san pham
		System.out.println("Nhap mo ta san pham : ");
		do {
			String demo = sc.nextLine();
			
			if( demo.trim().length() ==0) {
				System.err.println("Khong duoc de trong phan mo ta");
			} else {
				this.desciptions=demo;
				break;
			};
		}while(true);
		// Nhap trang thai san pham
		System.out.println("Nhap trang thai san pham :");
		do {
			String test = sc.nextLine();
			if(test.compareTo("true")==0 || test.compareTo("false")==0) {
				this.productStatus=Boolean.parseBoolean(test);
				break;
			}else {
				System.err.println("Trang thai san pham phai la true hoac false");
			}
		} while (true);
		
	}
	// Tao 1 method rieng(enterImportPrice)de nhap Import Price, chi kiem tra co phai float hay khong
	public static float enterImportPrice(){
		Scanner sc= new Scanner(System.in);
		do {
			try {
				float x =Float.parseFloat(sc.nextLine());
				return x;
			} catch (Exception e) {
				System.err.println("Gia nhap san pham phai la so thuc.");
			}
			
		} while (true);
	}
	//Tao 1 method rieng(enterExportPrice)de nhap Export Price, chi kiem tra co phai float hay khong
	public static float enterExportPrice(){
		Scanner sc= new Scanner(System.in);
		do {
			try {
				float x =Float.parseFloat(sc.nextLine());
				return x;
			} catch (Exception e) {
				System.err.println("Gia ban san pham phai la so thuc.");
			}
			
		} while (true);
	}
	


	@Override
	public void displayData() {
		System.out.println("Ma san pham : " + this.productId + " - Ten san pham :  " + this.productName + " - Trang thai : " + this.productStatus);
		System.out.println("Tieu de san pham : " + this.title);
		System.out.println("Mo ta san pham : " + this.desciptions);
		System.out.println("Gia nhap san pham : " + this.importPrice +" - Gia ban san pham : " + this.exportPrice);
		System.out.println("Loi nhuan : " + this.profit);
		System.out.println("Danh muc san pham : " + this.catalog.getCatalogName() + " - Ma danh muc : " + this.catalog.getCatalogId());
		System.out.println("Trang thai danh muc : " + this.catalog.isStatus() + " - Ma danh muc cha : " + this.catalog.getParentId());
		System.out.println("Mo ta danh muc : " + this.catalog.getDesciptions());
	}
	

	@Override
	public void calProfit() {
		this.profit=this.exportPrice-this.importPrice;
		
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public float getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public String getDesciptions() {
		return desciptions;
	}

	public void setDesciptions(String desciptions) {
		this.desciptions = desciptions;
	}

	public Categories getCatalog() {
		return catalog;
	}

	public void setCatalog(Categories catalog) {
		this.catalog = catalog;
	}


	
	
}
