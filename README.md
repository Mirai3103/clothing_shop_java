
# Website bán quần áo

Đây là một website bán quần áo được xây dựng bằng Spring boot

## Công nghệ sử dụng

- Spring boot - Framework để xây dựng ứng dụng web Java
- Spring Data JPA(Hibernate) - Để tương tác với cơ sở dữ liệu sử dụng JPA
- MySQL - Cơ sở dữ liệu để lưu trữ dữ liệu
- Thymeleaf - Template engine để xây dựng giao diện người dùng
- Tailwind CSS - Framework CSS để styling giao diện người dùng

## Hướng dẫn cài đặt

### Yêu cầu

- JDK 17
- MySQL
- Node.js > v18

### Các bước cài đặt

1. Clone repository

```bash
git clone https://github.com/Mirai3103/clothing_shop_java.git
```

2. Cấu hình cơ sở dữ liệu MySQL

> - Tạo database mới
> - Tạo user mới với quyền truy cập vào database đó

3. Cấu hình các thông tin kết nối đến CSDL trong file application.properties
4. Mở project bằng IDE
5. Mở terminal và truy cập vào thư mục src/main/resources

```bash
cd src/main/resources
```

6. Cài đặt các package cần thiết cho Node.js

```bash
npm install
```

7. Chạy lệnh để build CSS

```bash
npm run watch:css
```

8. Chạy ứng dụng
9. Truy cập vào đường dẫn http://localhost:8000

## To-do list (Các usecase chưa hoàn thành)

| Command | Description | Completed |
| --- | --- | --- |
| CreateProduct | Tạo một sản phẩm mới trong hệ thống | X |
| UpdateProduct | Cập nhật thông tin của một sản phẩm đã tồn tại trong hệ thống |  |
| DeleteProduct | Xóa một sản phẩm khỏi hệ thống |  |
| AddItemToCart | Thêm một mặt hàng vào giỏ hàng | X |
| UpdateCartItemQuantity | Cập nhật số lượng của một mặt hàng trong giỏ hàng | X |
| ClearCart | Làm trống giỏ hàng | X |
| RemoveItemFromCart | Xóa một mặt hàng khỏi giỏ hàng |  |
| ApplyDiscountCode | Áp dụng mã giảm giá cho đơn hàng |  |
| PlaceOrder | Đặt một đơn hàng với các mặt hàng trong giỏ hàng |  |
| CancelOrder | Hủy một đơn hàng đã tồn tại |  |
| CreatePayment | Tạo thanh toán cho một đơn hàng | X |
| AddBlogPost | Thêm một bài đăng mới vào hệ thống |  |
| UpdateBlogPost | Cập nhật thông tin của một bài đăng đã tồn tại trong hệ thống |  |
| DeleteBlogPost | Xóa một bài đăng khỏi hệ thống |  |
| AddDiscountCode | Thêm một mã giảm giá mới vào hệ thống |  |
| UpdateDiscountCode | Cập nhật thông tin của một mã giảm giá đã tồn tại trong hệ thống |  |
| DeleteDiscountCode | Xóa một mã giảm giá khỏi hệ thống |  |

| Query | Description | Completed |
| --- | --- | --- |
| GetProductList | Lấy danh sách các sản phẩm | X |
| GetProductDetail | Lấy thông tin chi tiết của một sản phẩm cụ thể | X |
| SearchProducts | Tìm kiếm các sản phẩm dựa trên tiêu chí đã cho | X |
| FilterProducts | Lọc các sản phẩm dựa trên tiêu chí đã cho |  |
| GetCart | Lấy danh sách các mặt hàng trong giỏ hàng |  |
| CheckoutOrder | Tiến hành thanh toán và tính tổng giá trị đơn hàng |  |
| GetOrderDetail | Lấy thông tin chi tiết của một đơn hàng cụ thể |  |
| GetOrderHistory | Lấy lịch sử đơn hàng cho một người dùng |  |
| GetBlogPosts | Lấy danh sách các bài đăng |  |
| GetBlogPostDetail | Lấy thông tin chi tiết của một bài đăng cụ thể |  |
| GetDiscountCodes | Lấy danh sách các mã giảm giá |  |
| GetRevenueStatistics | Lấy thống kê về doanh thu và doanh số |  |
| GetTopSellingProducts | Lấy danh sách các sản phẩm bán chạy nhất |  |
| GetSuppliers | Lấy danh sách các nhà cung cấp |  |
| GetInventoryLevel | Lấy thông tin về mức tồn kho của một sản phẩm cụ thể |  |
| TrackOrderStatus | Theo dõi trạng thái của một đơn hàng |  |
| VerifyDiscountCode | Xác minh tính hợp lệ của một mã giảm giá |  |
| CheckPromotionConditions | Kiểm tra xem các điều kiện khuyến mãi được đáp ứng hay không |  |
| GetRecommendedProducts | Lấy danh sách các sản phẩm được đề xuất dựa trên sở thích của người dùng |  |
| GetNotifications | Lấy danh sách thông báo cho một người dùng |  |
| GetUserOrderHistory | Lấy lịch sử đơn hàng cho một người dùng |  |