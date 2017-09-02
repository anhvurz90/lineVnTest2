DB bao gồm 3 đối tượng (file interview.sql)
1. Location: 
Mỗi location thuộc về 1 vùng (area_id), và có thể là con của 1 location khác (parent_id)
2. User: 
Mỗi user thuộc 1 location nào với điều kiện location đó ko phải là area (location.parent_id >= 2). 
3. Chat: 
1 User có thể tạo ra nhiều bản ghi chat. Mỗi bản ghi chat sẽ nói về 1 tỉnh nên sẽ có location. 1 bản ghi chat có 3 trạng thái: 0, 1, 2. Trạng thái 0 là trạng thái xóa, ko hiển thị
