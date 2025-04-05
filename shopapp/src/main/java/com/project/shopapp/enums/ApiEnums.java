package com.project.shopapp.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


public class ApiEnums {

    @AllArgsConstructor // ✅ Tự động tạo constructor có tất cả tham số
    @Getter // ✅ Tự động tạo getter cho biến
    public enum EntityStatus {
        NORMAL(1),
        OK(2),
        NOT_OK(3),
        TEMP(10),
        LOCK(98),
        DELETED(99);

        private final int value;
    }


    public enum CacheDataTypes
    {
        ByteArray , // Can convert to byte array
        Json
    }


    public enum ShowLogLevel
    {
        Default ,
        Production,
        Stacktrace
    }

    public enum Action
    {
        VIEW ,
        CREATE,
        UPDATE,
        DELETED,
        IMPORT,
        EXPORT,
        PRINT,
        EDIT_ANOTHER_USER,
        MENU,
        LOG_IN,
        LOG_OUT
    }

    @AllArgsConstructor
    @Getter
    public enum TypeFunction    // Phân quyền chức năng với người dùng và nhóm quyền
    {
        FUNCTION_USER(1), // Người dùng - Chức năng
        FUNCTION_ROLE(2);    // Nhóm quyền - Chức năng
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeUser
    {
        ADMINISTRATOR(1), //quản trị hệ thống
        MANAGEMENT(2), //ban quản lý
        TECHNICIANS(3);
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeAction    // loại hành động
    {
        ACTION(1), // Hành động
        NOTIFICATION(2), // Thông báo
        WARNING(3);         //Cảnh báo
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum FileType
    {
        IMAGE(1),
        FILE(2);
        private final int value;

    }

    @AllArgsConstructor
    @Getter
    public enum TypeAttactment //Loại file đính kèm
    {
        DOCUMENT(1);         //File đính kèm Tài liệu
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeFCM
    {
        NEW_MESSAGE(1), //tin nhắn
        NEW_EMAIL(2), //email
        NEW_REPLY(3), //trả lời
        NEW_COMMENT(4); //bình luận
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypePaymentStatus    // trang thái thanh toán
    {
        INIT(1), // chưa thanh toán
        FULL(2),    // đã thanh toán hết
        NOT_ENOUGH(3),    // chưa thanh toán hết
        NOT_PAYMENT(4);     // không thanh toán
        private final int value;

    }

    @AllArgsConstructor
    @Getter
    public enum TypePaymentMethod    // phương thức thanh toán
    {
        DIRECT(1), // Bằng tiền mặt
        ONLINE(2),    // Online
        OTHER(3);    // Khác
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeFile    // loại file
    {
        DOCUMENTS(1), // file văn bản
        VIDEO(2),    // file video
        AUDIO(3),    // file âm thanh
        ELECTRONIC_BOOKS(4),    // file sách điện tử
        IMAGES(5),    // file hình ảnh
        ARCHIVES(6),    // file nén
        OTHER(7);           //Loại khác
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeRegister    // hình thức đăng ký
    {
        EMAIL(1), // đăng ký bằng email
        PHONE_NUMBER(2),    // đăng ký bằng sdt
        FACEBOOK(3); // đăng ký bằng face
        private final int value;
    }

    @AllArgsConstructor
    @Getter
    public enum TypeDate    // loại ngày lọc
    {
        DATE_CREATED(1), // tạo
        DATE_ACTIVED(2), // hoạt động
        DATE_UPDATED(3); //cập nhật
        private final int value;
    }

   
}
