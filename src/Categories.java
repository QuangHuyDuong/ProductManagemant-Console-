import java.io.Serializable;
import java.util.Scanner;

public class Categories implements ICategories,Serializable {
	private int catalogId;
	private String catalogName;
	private String desciptions;
	private boolean status;
	private int parentId;
	
	public Categories(int catalogId, String catalogName, String desciptions, boolean status, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.desciptions = desciptions;
		this.status = status;
		this.parentId = parentId;
	}

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDesciptions() {
		return desciptions;
	}

	public void setDesciptions(String desciptions) {
		this.desciptions = desciptions;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public void inputDataCategory() {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Nhap vao ten danh muc");
		do {
			this.catalogName= sc.nextLine();
			if(this.catalogName.length()<6 || this.catalogName.length()>30) {
				System.err.println("Do dai ten danh muc phai tu 6-30 ki tu.");
			}
			else break;
		} while (true);
		System.out.println("Nhap vao mo ta danh muc : ");
		do {
			this.desciptions = sc.nextLine();
			
			String test =this.desciptions.trim();
			if(test.length()==0){
				System.err.println("Khong duoc de trong phan mo ta danh muc.");
			} else break;
		} while (true);
		System.out.println("Nhap trang thai danh muc : ");
		do {
			String s = sc.nextLine();
			if(s.compareTo("true") ==0 || s.compareTo("false") ==0) {
				this.status=Boolean.parseBoolean(s);
				break;
			} else {
				System.err.println("Trang thai san pham phai la true hoac false");
			}
		} while (true);
		
	}

	@Override
	public void displayDataCategory() {
		System.out.println("Ma danh muc : " + this.catalogId + " - Ten danh muc : " + this.catalogName);
		System.out.println("Mo ta : " + this.desciptions);
		System.out.print("Danh muc cha :" + this.parentId );
		if(this.status) {
			System.out.println("- Trang thai : Hoat dong");
			
		} else System.out.println("- Trang thai : Khong hoat dong");
		
	}

	

	
	
	

}
