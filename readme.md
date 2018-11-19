# Update thêm chức năng mới

Hiện tại thì t đã code xong phần để với mỗi loại xe thì sẽ 
giá vé sẽ khác nhau. Và người quản lý có thể cài đặt được theo kiểu 
có giới hạn thời gian gửi hay không ? Quá hạn thời gian gửi thì việc 
tính tiền sẽ như thế nào vd: Thời gian cho phép 6 tiếng với mỗi một 
giờ gửi quá phí sẽ tăng lên theo giờ như thế nào đấy hoặc thời gian 
gửi quá hạn được tính theo ngày. t đã code để người quản lý hoàn 
toàn có thẻ cài đặt được không fix cứng nữa rồi. Nhưng mà còn một vấn 
đề là đang không biết ném nó vào chỗ nào thì thích hợp.

Tạm thời phần check out coi như tạm ổn, thiếu phần vé tháng nữa tính sau 

# Dự tính update 

## 1 
Phần check in loại xe sẽ không nhập bằng tay nữa mà thay vào đó sẽ là 
một cái kiểu để chọn giống như Cúc đang làm với phần id trong user edit 
## 2 
Và người quản lý có thể set up răng bãi xe này sẽ dùng để gửi những xe nào 
chỉ xe máy hay cả xe đạp xe máy và nếu như bãi xe chỉ gồm đơn thuần một 
loại xe nào đấy thì không cần chọn loại xe lúc gửi vào nữa. Nếu có 2 thì 
có 2 lựa chọn nếu có 3 thì 3 lựa chọn về loại xe.
## 3 
Như vậy thì database của chúng ta sẽ vẫn cần phải sửa ở bảng parking 
cần bổ sung thêm một cột nữa là những loại xe nào thì được đi vào 
bãi này.(Cần xong trong ngày chủ nhật)

``Nhật Hiếu có thể nhận phần làm về phần 3 được không ?``

``Cúc ơi c sau khi xong phần edit user thì c làm cho t phần 1 và 2 được không t sẽ làm chung với c về phần này, t nghĩ một mình không làm được cái này nên cần sp ở đây``

# BIG UPDATE 

## Thêm bot vào hệ thống 

nếu như tất cả mọi việc xong dược trong tuần thì t sẽ bổ sung thêm tính năng nhận diện biển số xe vào
input là một ảnh về biển số xe và out put là thông tin về biển số xe (text để input vào phần check in 

Từ mai các bạn có gi quan trọng thì update trực tiếp vào file này nhé.

Moi nguoi tu sua lai link cua anh !!!!