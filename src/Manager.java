
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Manager {

	private static List<Categories> listOfCategory = new ArrayList<Categories>();
	private static List<Product> listOfProducts = new ArrayList<Product>();

	public static void main(String[] args) {

		//// Doc file

		try {
			// khoi tao doi tuong
			File file1 = new File("D:/List/ListCategories.txt");
			File file2 = new File("D:/List/ListProducts.txt");
			// khoi tao input strem
			FileInputStream fis1 = new FileInputStream(file1);
			FileInputStream fis2 = new FileInputStream(file2);
			//
			ObjectInputStream ois1 = new ObjectInputStream(fis1);
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			//
			listOfCategory = (List<Categories>) ois1.readObject();
			listOfProducts = (List<Product>) ois2.readObject();
			//
			ois1.close();
			ois2.close();
			fis1.close();
			fis2.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Collections.sort(listOfCategory, new Comparator<Categories>() {

			@Override
			public int compare(Categories o1, Categories o2) {
				// TODO Auto-generated method stub
				return o1.getCatalogName().compareTo(o2.getCatalogName());
			}
			
		});
		

		Scanner sc = new Scanner(System.in);
		Manager test = new Manager();

		do {
			System.out.println("**********MENU**********");
			System.out.println("1. Quan ly danh muc.");
			System.out.println("2. Quan ly san pham.");
			System.out.println("3. Thoat.");
			int choice;
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					break;
				} catch (Exception e) {
					System.err.println("Ban phai nhap so tu 1 - 3.");
				}
			} while (true);
			switch (choice) {
			case 1:
				boolean flag = true;
				do {
					System.out.println("***********QUAN LY DANH MUC***********");
					System.out.println("1. Hien danh sach danh muc.");
					System.out.println("2. Them danh muc.");
					System.out.println("3. Xoa danh muc.");
					System.out.println("4. Tim kiem danh muc.");
					System.out.println("5. Quay lai.");
					System.out.print("Luc chon cua ban : ");
					int choice2;
					do {
						try {
							choice2 = Integer.parseInt(sc.nextLine());
							break;
						} catch (Exception e) {
							System.err.println("Vui long nhap tu 1 - 5.");
						}
					} while (true);
					switch (choice2) {
					case 1:

						test.listCategoryManagement(sc);
						break;
					case 2:
						test.addCategories(sc);
						break;
					case 3:
						System.out.println("Nhap ma danh muc can xoa : ");
						int deletedCategoryID;
						do {
							try {
								deletedCategoryID = Integer.parseInt(sc.nextLine());
								break;
							} catch (Exception e) {
								System.err.println("Ma danh muc phai la so nguyen.");
							}
						} while (true);
						boolean isContainCategory = false;
						for (Categories ctg : listOfCategory) {
							if (ctg.getParentId() == deletedCategoryID) {
								isContainCategory = true;
								break;
							}
						}
						boolean isContaianProduct = false;
						for (Product pro : listOfProducts) {
							
							if (pro.getCatalog().getCatalogId() == deletedCategoryID) {
								isContaianProduct = true;
								
							}
						}
						if (isContaianProduct || isContainCategory) {
							System.err.println("Khong the xoa danh muc nay.");
						} else {
							Iterator<Categories> myList = listOfCategory.iterator();
							while (myList.hasNext()) {
								Categories c = myList.next();
								if (c.getCatalogId() == deletedCategoryID) {
									myList.remove();
								}
							}
						}
						
						break;
					case 4:
						test.searchByName(sc);
						break;
					case 5:
						flag = false;
						break;
					default:
						System.err.println("Vui long nhap tu 1 - 5");
						break;
					}
				} while (flag);

				break;
			case 2:
				test.productManager(sc);
				break;
			case 3:
				test.writeToFile(listOfCategory, listOfProducts);
				System.exit(0);
			default:
				System.err.println("Vui long nhap tu 1 - 3");
				break;
			}
		} while (true);

	}

	// ********** Chuc nag 1 : Quan li danh sach danh muc**************
	public void listCategoryManagement(Scanner sc) {
		boolean exit = true;
		do {
			System.out.println("*************Danh sach danh muc*************");
			System.out.println("1. Danh sach cay danh muc.");
			System.out.println("2. Thong tin chi tiet danh muc.");
			System.out.println("3. Quay lai.");
			int choice3;
			do {
				try {
					choice3 = Integer.parseInt(sc.nextLine());
					break;
				} catch (Exception e) {
					System.err.println("Vui long nhap tu 1 - 3.");
				}
			} while (true);
			switch (choice3) {
			case 1:
				
				List<Integer> listOfLevel = new ArrayList<Integer>();
				
				showListCategories(0, listOfLevel);
				
				break;
			case 2:
				System.out.println("Nhap vao ten danh muc : ");
				String searchName = sc.nextLine();
				int idOfFather = 0;
				boolean isNameExisted = false;
				for (Categories categories : listOfCategory) {
					if (searchName.compareTo(categories.getCatalogName()) == 0) {
						categories.displayDataCategory();
						isNameExisted = true;
						idOfFather = categories.getParentId();
					}
				}
				if (isNameExisted) {
					if (idOfFather != 0) {
						for (Categories categories : listOfCategory) {
							if (categories.getCatalogId() == idOfFather) {
								System.out.println("Thong tin danh muc cha :");
								categories.displayDataCategory();
							}
						}
					}
				} else {
					System.err.println("Khong tim thay danh muc nay.");
				}
				break;
			case 3:
				exit = false;

			}
		} while (exit);
	}

	/////////// CHuc nag 2 : Method them danh muc.
	public void addCategories(Scanner sc) {
		System.out.println("Nhap vao so luong danh muc muon nhap : ");
		int n;
		do {
			try {
				n = Integer.parseInt(sc.nextLine());

				break;
			} catch (Exception e) {
				System.out.println("So luong phai la so nguyen duong, vui long nhap lai.");
			}
		} while (true);
		for (int i = 0; i < n; i++) {
			System.out.println("Nhap thong tin danh muc thu " + (i + 1));

			Categories cate = new Categories();
			System.out.print("Nhap ma danh muc : ");

			int a;
			do {

				do {
					try {
						a = Integer.parseInt(sc.nextLine());
						if (a < 0) {
							System.err.println("Ma danh muc phai lon hon 0.");
						} else
							break;
					} catch (Exception e) {
						System.err.println("Ma danh muc phai la so nguyen, vui long nhap lai.");
					}
				} while (true);

				boolean isIdExisted = false;
				for (Categories c : listOfCategory) {
					if (a == c.getCatalogId()) {
						isIdExisted = true;
						break;
					}

				}
				if (isIdExisted) {
					System.err.println("Ma danh muc bi trung, vui long nhap lai.");
				} else {

					break;
				}
			} while (true);
			cate.setCatalogId(a);
			cate.inputDataCategory();
			// Nhap ma danh muc cha
			boolean isParentIdExisted = false;
			System.out.println("Nhap ma danh muc cha :");
			do {
				do {
					try {
						cate.setParentId(Integer.parseInt(sc.nextLine()));
						break;
					} catch (Exception e) {
						System.out.println("Ma danh muc cha phai la so nguyen");
					}

				} while (true);
				for (Categories categories : listOfCategory) {
					if (categories.getCatalogId() == cate.getParentId()) {
						isParentIdExisted = true;
						break;
					}
				}
				if (isParentIdExisted) {
					break;
				} else {
					System.out.println("Khong tim thay danh muc nay");

				}

			} while (true);
			// Them phan tu vao danh sach

			listOfCategory.add(cate);

		}
	}

	// Hien toan bo danh sach  theo hinh cay
	public void showListCategories(int idParent, List<Integer> a) {
		List<Integer> n = new ArrayList<>(a);
		n.add(1);
		int i = n.get(n.size() - 1);
		for (Categories cate : listOfCategory) {
			if (cate.getParentId() == idParent) {
				for (int j = 0; j < n.size() - 1; j++) {
					System.out.print("    ");
				}
				for (int j = 0; j < n.size() - 1; j++) {
					System.out.print(n.get(j) + ". ");
				}
				System.out.println(i + ". " + cate.getCatalogName() + " - ID : " + cate.getCatalogId());
				showListCategories(cate.getCatalogId(), n);

				;
				i++;
			}

		}
		

	}

	// Tim kiem danh muc theo ten danh muc
	public void searchByName(Scanner sc) {
		System.out.println("Nhap ten danh muc can tim : ");
		String name = sc.nextLine();
		for (Categories c : listOfCategory) {
			if (name.compareTo(c.getCatalogName()) == 0) {
				c.displayDataCategory();

			}
		}
	}

	// *******************Quan li san pham ************************
	public void productManager(Scanner sc) {
		boolean flag2 = true;
		do {
			System.out.println("**********QUAN LY SAN PHAM**********");
			System.out.println("1. Them san pham moi.");
			System.out.println("2. Tinh loi nhuan san pham.");
			System.out.println("3. Hien thi thong tin san pham.");
			System.out.println("4. Sap xep san pham.");
			System.out.println("5. Cap nhat thong tin san pham.");
			System.out.println("6. cap nhat trang thai san pham.");
			System.out.println("7. Quay lai");
			System.out.println("Lua chon cua ban :");
			int choice3 = 0;
			try {
				choice3 = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("vui long nhap tu 1 - 6.");
			}
			switch (choice3) {
			case 1:
				addProduct(sc);
				break;
			case 2:
				for (Product product : listOfProducts) {
					product.calProfit();
				}
				System.out.println("Da tinh xong.");
				break;
			case 3:
				boolean flag4 = true;
				do {
					System.out.println("**********Hien thi danh muc*********");
					System.out.println("1. Hien thi san pham theo danh muc.");
					System.out.println("2. Hien thi thong tin chi tiet san pham theo ten.");
					System.out.println("3. Quay lai.");
					int choice4;
					do {
						try {
							choice4 = Integer.parseInt(sc.nextLine());
							break;
						} catch (Exception e) {
							System.err.println("Vui long nhap tu 1 - 3");
						}
					} while (true);
					switch (choice4) {
					case 1:
						int sttCate = 1;
						for (Categories categories : listOfCategory) {
							int sttPro = 1;
							int position=0;
							for (Product pro : listOfProducts) {
								if(categories.getCatalogName().compareTo(pro.getCatalog().getCatalogName())==0) {
									System.out.println(sttCate + ". " + categories.getCatalogName());
									sttCate++;
									
									position = listOfProducts.indexOf(pro);
									break;
								}
								
							}
							for(int j = position ; j< listOfProducts.size() ; j ++) {
								if(categories.getCatalogName().compareTo(listOfProducts.get(j).getCatalog().getCatalogName()) ==0) {
									System.out.println("    " + (sttCate-1) + "."+ sttPro + ". " +listOfProducts.get(j).getProductName() + " - " + listOfProducts.get(j).getProductId());
									sttPro++;
								}
							}
						}
						break;
					case 2:
						System.out.println("Nhap ten san pham can tim : ");
						String findProByName = sc.nextLine();
						boolean isNameExisted = false;
						for (Product pro : listOfProducts) {
							if(pro.getProductName().compareTo(findProByName) ==0) {
								pro.displayData();
								isNameExisted = true;
								break;
							}
						}
						if(!isNameExisted) {
							
							System.err.println("Khong tim thay san pham nay.");
						}
						break;
					case 3:
						flag4 = false;
						break;
					default:
						System.err.println("Vui long nhap tu 1 - 3");
						break;
					}
				} while (flag4);
				break;

			case 4:
				boolean flag5 = true;
				do {
					System.out.println("1. Sap xep san pham theo loi nhuan tang dan.");
					System.out.println("2. Sap xep san pham theo loi nhuan giam dan.");
					System.out.println("3. Quay lai.");
					int choice5 = 0;
					do {
						try {
							choice5 = Integer.parseInt(sc.nextLine());
							break;
						} catch (Exception e) {
							System.out.println("Vui long nhap tu 1 - 3.");
						}
					} while (true);
					switch (choice5) {
					case 1:
						for (int i = 0; i < listOfProducts.size() - 1; i++) {
							for (int j = 0; j < listOfProducts.size() - 1 - i; j++) {
								if (listOfProducts.get(j).getProfit() > listOfProducts.get(j + 1).getProfit()) {
									Product temp = listOfProducts.get(j + 1);
									listOfProducts.set(j + 1, listOfProducts.get(j));
									listOfProducts.set(j, temp);

								}
							}
						}
						System.out.println("Da sap xep xong:");
						int k = 1;
						for (Product pro : listOfProducts) {
							System.out.print(k + ". ");
							pro.displayData();
							System.out.println();
							k++;
						}
						break;
					case 2:
						for (int i = 0; i < listOfProducts.size() - 1; i++) {
							for (int j = 0; j < listOfProducts.size() - 1 - i; j++) {
								if (listOfProducts.get(j).getProfit() < listOfProducts.get(j + 1).getProfit()) {
									Product temp = listOfProducts.get(j + 1);
									listOfProducts.set(j + 1, listOfProducts.get(j));
									listOfProducts.set(j, temp);

								}
							}
						}
						System.out.println("Da sap xep xong:");
						int m = 1;
						for (Product pro : listOfProducts) {
							System.out.print(m + ". ");
							pro.displayData();
							System.out.println();
							m++;
						}
						/*Collections.sort(listOfProducts);
						for (Product pro : listOfProducts) {
							pro.displayData();
						}*/
						break;
					case 3:
						flag5 = false;
						break;
					}
				} while (flag5);
				break;
			case 5:
				System.out.println("Nhap ma san pham can cap nhat thong tin :");
				String idChange = sc.nextLine();
				boolean isIdChangeExisted = false;
				for (Product pro : listOfProducts) {
					if (idChange.compareTo(pro.getProductId()) == 0) {
						pro.inputData();
						
						isIdChangeExisted = true;
						break;

					}
				}
				if (!isIdChangeExisted) {
					System.err.println("Khong tim thay ma san pham.");
				}
				break;
			case 6:
				System.out.println("Nhap ma san pham can cap nhat trang thai : ");
				String idToChange = sc.nextLine();
				boolean checkIdStatus = false;
				for (Product pro : listOfProducts) {
					if(pro.getProductId().compareTo(idToChange)==0) {
						System.out.println("Nhap trang thai san pham : ");
						
						do {
							String status = sc.nextLine();
							if(status.equals("true") || status.equals("false")) {
								pro.setProductStatus(Boolean.parseBoolean(status));
								checkIdStatus = true;
								break;
							} else {
								System.err.println("Trang thai san pham phai la true hoac false");
							}
							
						} while (true);
					}
				}
				if (!checkIdStatus)
					System.err.println("Khong tim thay ma san pham.");
				else
					System.out.println("Da cap nhat xong.");
				break;
			case 7:
				flag2 = false;

				break;
			default:
				System.out.println("Vui long nhap tu 1 - 7");
				break;
			}
		} while (flag2);
	}

	// Them san pham moi
	public void addProduct(Scanner sc) {
		System.out.println("Nhap so luong san pham can them.");
		int numOfProduct;
		do {
			try {
				numOfProduct = Integer.parseInt(sc.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("So san pham phai la so nguyen.");
			}
		} while (true);
		for (int i = 0; i < numOfProduct; i++) {
			Product tempProduct = new Product();
			System.out.println("Nhap thong tin san pham thu " + (i+1));
			System.out.println("Nhap ma san pham : ");

			
			
			do {
				boolean abc = false;
				tempProduct.setProductId(sc.nextLine());
				if (tempProduct.getProductId().startsWith("C") && tempProduct.getProductId().length() == 4) {
					for (Product pro : listOfProducts) {
						if (tempProduct.getProductId().equals(pro.getProductId())) {
							abc= true;
							break;
						}

					}
					if (abc) {
						System.err.println("Ma san pham da ton tai, vui long nhap lai.");
					} else {
						break;
					}
				} else {
					System.err.println("Ma san pham phai bat dau bang C va gom 4 ki tu ");
				}
			} while (true);
			System.out.println("Nhap ten san pham : ");
			
			do {
				boolean isNameExisted = false;
				tempProduct.setProductName(sc.nextLine());
				for (Product pro : listOfProducts) {
					if ( tempProduct.getProductName().equals(pro.getProductName())) {
						isNameExisted = true;
						break;
					}
				}
				if (isNameExisted) {
					System.err.println("Ten san pham da ton tai, vui long nhap lai.");
				} else {
					break;
				}

			} while (true);
			tempProduct.inputData();
			// Nhap danh muc cho product
			System.out.println("Nhap ma danh muc cua san pham : ");
			int id = 0;
			boolean isCatagoryExisted = false;
			do {
				try {
					id = Integer.parseInt(sc.nextLine());
					if (id > 0) {
						for (Categories c : listOfCategory) {
							if (c.getCatalogId() == id) {
								isCatagoryExisted = true;

								tempProduct.setCatalog(c);
								break;
							}
						}
						if (isCatagoryExisted) {
							listOfProducts.add(tempProduct);
							break;
						} else {
							System.err.println("Khong tim thay danh muc nay.");
						}
					} else {
						System.err.println("Ma danh muc phai lon hon 0.");
					}

				} catch (Exception e) {
					System.err.println("Ma danh muc phai la so nguyen.");
				}
			} while (true);

		}

	}

	/// Ghi file ra tep
	public void writeToFile(List<Categories> listOfCategories, List<Product> listOfProducts) {
		try {
			// Khoi tao file
			File file1 = new File("D:/List/ListCategories.txt");
			File file2 = new File("D:/List/ListProducts.txt");
			// khoi tao file ouput stream
			FileOutputStream fos1 = new FileOutputStream(file1);
			FileOutputStream fos2 = new FileOutputStream(file2);
			// khoi tao object output
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			// ghi file
			oos1.writeObject(listOfCategories);
			oos2.writeObject(listOfProducts);
			// dong
			oos1.close();
			oos2.close();
			fos1.close();
			fos2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
