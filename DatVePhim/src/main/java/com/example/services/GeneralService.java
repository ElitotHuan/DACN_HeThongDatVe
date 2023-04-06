package com.example.services;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GeneralService<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    T save(T t);

    void remove(Integer id);
}




/*
findAll(): là phương thức trừu tượng trả về một danh sách các đối tượng T. Nó được sử dụng để lấy tất cả các đối tượng T từ cơ sở dữ liệu.
findById(Integer id): là phương thức trừu tượng trả về một Optional<T>. Nó được sử dụng để tìm kiếm một đối tượng T trong cơ sở dữ liệu bằng id.
save(T t): là phương thức trừu tượng trả về một đối tượng T. Nó được sử dụng để lưu đối tượng T vào cơ sở dữ liệu. Nếu đối tượng T chưa tồn tại trong cơ sở dữ liệu, nó sẽ được tạo mới. Nếu đối tượng T đã tồn tại trong cơ sở dữ liệu, nó sẽ được cập nhật.
remove(Integer id): là phương thức trừu tượng không trả về giá trị. Nó được sử dụng để xóa đối tượng T khỏi cơ sở dữ liệu bằng id.
Các phương thức của interface GeneralService được định nghĩa bằng cách sử dụng kiểu dữ liệu generics T, cho phép interface này được sử dụng để thao tác với các đối tượng khác nhau trong hệ thống.
*/