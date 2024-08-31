package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Account;
import model.Category;
import model.Product;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// Lay du lieu tu Product len jsp
	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select *from product";

		try {
			// Bước 1 kết nối vs CSDL
			conn = DBContext.getConnection();

			// Bước 2: tạo ra đối tượng statement
			ps = conn.prepareStatement(query);

			// Bước 3: thực thi câu lệnh SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1),
						rs.getString(2),
						rs.getString(3), 
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6)));
			}

		} catch (Exception e) {

		}

		return list;
	}
    
	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		String query = "select *from Category";

		try {
			// Bước 1 kết nối vs CSDL
			conn = DBContext.getConnection();

			// Bước 2: tạo ra đối tượng statement
			ps = conn.prepareStatement(query);

			// Bước 3: thực thi câu lệnh SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}

		} catch (Exception e) {

		}

		return list;
	}
	
	
	//Lay product tu cid cua categor
	public List<Product> getProductByCID(String cid) {
		List<Product> list = new ArrayList<>();
		String query = "select *from product "+ "where cateID=?";

		try {
			// Bước 1 kết nối vs CSDL
			conn = DBContext.getConnection();

			// Bước 2: tạo ra đối tượng statement
			ps = conn.prepareStatement(query);
			ps.setString(1, cid);

			// Bước 3: thực thi câu lệnh SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6)));
			}

		} catch (Exception e) {

		}

		return list;
	}
	
	
	
	//Hiển thi 1 product tu id cho trang Detail
	public Product getProductByID(String id) {
		
		String query = "select *from product"+" where id=?";

		try {
			// Bước 1 kết nối vs CSDL
			conn = DBContext.getConnection();

			// Bước 2: tạo ra đối tượng statement
			ps = conn.prepareStatement(query);
			ps.setString(1, id);

			// Bước 3: thực thi câu lệnh SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6),1);
			}

		} catch (Exception e) {

		}

		return null;
	}
	
	//Tìm kiếm product theo tên product
		public List<Product> SearchByName(String txtSearch) {
		List<Product> list= new ArrayList<>();
		String query = "SELECT * FROM product WHERE name LIKE ?";

		try {
			// Bước 1 kết nối vs CSDL
			conn = DBContext.getConnection();

			// Bước 2: tạo ra đối tượng statement
			ps = conn.prepareStatement(query);
			 ps.setString(1, "%" + txtSearch + "%");

			// Bước 3: thực thi câu lệnh SQL
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getDouble(4),
						rs.getString(5),
						rs.getString(6)));
			}

		} catch (Exception e) {

		}

		return list;
	}
		
		//Gọi data lên trang login
		public Account login(String user,String pass) {
			
			String query = "SELECT *FROM Account \r\n"
					+ "WHERE [user] = ?\r\n"
					+ "and pass=?";

			try {
				// Bước 1 kết nối vs CSDL
				conn = DBContext.getConnection();

				// Bước 2: tạo ra đối tượng statement
				ps = conn.prepareStatement(query);
				ps.setString(1, user);
				ps.setString(2, pass);

				// Bước 3: thực thi câu lệnh SQL
				rs = ps.executeQuery();
				while (rs.next()) {
					return new Account(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getInt(4), 
						     rs.getInt(5)); 
							
				}

			} catch (Exception e) {

			}

			return null;
		}
		
		
		//Kiểm tra user đã tồn tại hay chưa khi đăng kí tk
				public Account  checkAccountExits(String user) {
							
							String query = "SELECT *FROM Account \r\n"
									+ "WHERE [user] = ?";
				
							try {
								// Bước 1 kết nối vs CSDL
								conn = DBContext.getConnection();
				
								// Bước 2: tạo ra đối tượng statement
								ps = conn.prepareStatement(query);
								ps.setString(1, user);
								
				
								// Bước 3: thực thi câu lệnh SQL
								rs = ps.executeQuery();
								while (rs.next()) {
									return new Account(rs.getInt(1), 
											rs.getString(2), 
											rs.getString(3), 
											rs.getInt(4), 
										     rs.getInt(5)); 
											
								}
				
							} catch (Exception e) {
				
							}
				
							return null;
						}
				
				//Them tai khoan khi dang ki vao csdl
				public Account  signUp(String user,String pass) {
					
					String query = "INSERT INTO Account\n"
					                +" VALUES(?,?,0,0)";
		
					try {
						// Bước 1 kết nối vs CSDL
						conn = DBContext.getConnection();
		
						// Bước 2: tạo ra đối tượng statement
						ps = conn.prepareStatement(query);
						ps.setString(1, user);
						ps.setString(2, pass);
						
		
						// Bước 3: thực thi câu lệnh SQL
						ps.executeUpdate();
						
						
		
					} catch (Exception e) {
		
					}
		
					return null;
				}
				
				//Upload san pham len manager.jsp bang sell_id
				public List<Product> getProductBySellID(int id) {
					List<Product> list= new ArrayList<>();
					String query = "select *from product\n"
					              + "where sell_ID = ?";
					            
					try {
						// Bước 1 kết nối vs CSDL
						conn = DBContext.getConnection();

						// Bước 2: tạo ra đối tượng statement
						ps = conn.prepareStatement(query);
						 ps.setInt(1, id);

						// Bước 3: thực thi câu lệnh SQL
						rs = ps.executeQuery();
						while (rs.next()) {
							list.add(new Product(rs.getInt(1), 
									rs.getString(2), 
									rs.getString(3), 
									rs.getDouble(4),
									rs.getString(5),
									rs.getString(6)));
						}

					} catch (Exception e) {

					}

					return list;
				}
				
				
				//Them san pham trong manager.jsp
			public void insertProduct(String name,String image,String price,String title,
					String description,String category,int sid) {
				String query="INSERT [dbo].[product] \n"
						 + " ([name], [image], [price], [title], [description], [cateID], [sell_ID]) "
						  +"VALUES(?,?,?,?,?,?,?)";
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();
	
					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, image);
					ps.setString(3, price);
					ps.setString(4, title);
					ps.setString(5, description);
					ps.setString(6, category);
					ps.setInt(7, sid);
					
	
					// Bước 3: thực thi câu lệnh SQL
					ps.executeUpdate();
					
					
	
				} catch (Exception e) {
	
				}
			
			}
			//Xoa san pham trong manager.jsp
			public void deleteProduct(String id) {
				String query="delete from product "
						+ "where id=?";
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();
	
					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					ps.setString(1, id);
					
					
					// Bước 3: thực thi câu lệnh SQL
					ps.executeUpdate();
					
					
	
				} catch (Exception e) {
	
				}
			
			}
				
			//Sữa san pham trong manager.jsp
			public void updateProduct(String name,String image,String price,String title,
					String description,String category, String pid) {
				String query="select *from product\r\n"
						+ "update product\r\n"
						+ "set [name]=?,\r\n"
						+ "image =?,\r\n"
						+ "price = ?,\r\n"
						+ "title = ?,\r\n"
						+ "[description] = ?,\r\n"
						+ "cateID = ?\r\n"
						+ "where id = ?";
						
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();
	
					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, image);
					ps.setString(3, price);
					ps.setString(4, title);
					ps.setString(5, description);
					ps.setString(6, category);
					ps.setString(7, pid);
				    
					
	
					// Bước 3: thực thi câu lệnh SQL
					ps.executeUpdate();
					
					
	
				} catch (Exception e) {
	
				}
			
			}	
			
			
			
			//Load ra 3 san pham 
			public List<Product> getTop3() {
				List<Product> list= new ArrayList<>();
				String query = "select top 3 *from product";
				              
				            
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();

					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					

					// Bước 3: thực thi câu lệnh SQL
					rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new Product(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getDouble(4),
								rs.getString(5),
								rs.getString(6)));
					}

				} catch (Exception e) {

				}

				return list;
			}
			
			//Load ra 3 san pham tiep theo 
			public List<Product> getNextTop3(int amount) {
				List<Product> list= new ArrayList<>();
				String query = "SELECT\r\n"
						+ "*FROM product\r\n"
						+ "ORDER BY id\r\n"
						+ "OFFSET ? ROWS \r\n"
						+ "FETCH NEXT 3 ROWS ONLY";
				              
				            
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();

					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					ps.setInt(1, amount);
					

					// Bước 3: thực thi câu lệnh SQL
					rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new Product(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getDouble(4),
								rs.getString(5),
								rs.getString(6)));
					}

				} catch (Exception e) {

				}

				return list;
			}
			
			
			//dem xem có bao nhiu product để phân trang
			public int getTotalProduct() {
				String query="select COUNT(*) from product";
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();

					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					

					// Bước 3: thực thi câu lệnh SQL
					rs = ps.executeQuery();
					while (rs.next()) {
						return rs.getInt(1);
					}
				} catch (Exception e) {

				}
                 return 0;
				
			}
			
			
			//phân trang cho ManagerProduct.jsp
			public List<Product> pagingProduct(int id,int index) {
				List<Product> list= new ArrayList<>();
				String query = "SELECT * FROM product \r\n"
						+ "WHERE sell_ID = ? \r\n"
						+ "ORDER BY id OFFSET ? ROW \r\n"
						+ "FETCH NEXT 5 ROWS ONLY";
				              
				            
				try {
					// Bước 1 kết nối vs CSDL
					conn = DBContext.getConnection();

					// Bước 2: tạo ra đối tượng statement
					ps = conn.prepareStatement(query);
					ps.setInt(1,id);
					ps.setInt(2, (index-1)*5);
					

					// Bước 3: thực thi câu lệnh SQL
					rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new Product(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getDouble(4),
								rs.getString(5),
								rs.getString(6)));
					}

				} catch (Exception e) {

				}

				return list;
			}
			
	public static void main(String[] args) {
		DAO dao = new DAO();
//		List<Product> list = dao.getAllProduct();
//		List<Category> listC= dao.getAllCategory();
//		List<Product> list = dao.getProductByCID("2");
//		String name ="Giày";
//		List<Product> list = dao.SearchByName(name);
//		int id=1;
//		List<Product> list =dao.getProductBySellID(id);
//		
//		for (Product o : list) {
//			System.out.println(o);
//		}
		List<Product> list =dao.pagingProduct(1, 0);
		for (Product o : list) {
			System.out.println(o);
		}
	}

}
