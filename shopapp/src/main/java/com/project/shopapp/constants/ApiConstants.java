package com.project.shopapp.constants;

public class ApiConstants {
    public static class StatusCode
    {
        public static final int Success200 = 200;
        public static final int Success201 = 201;
        public static final int Valid210 = 210;
        public static final int Valid211 = 211;
        public static final int Valid213 = 213;
        public static final int NoPermision = 222;
        public static final int Error400 = 400;
        public static final int Error401 = 401;
        public static final int Error404 = 404;
        public static final int Error422 = 422;
        public static final int Error500 = 500;
        public static final int Error600 = 600;
    }

    public static class MessageWarning
    {
        public static final String WARNING_2115_EXIT_COUNTRY = "Quốc gia không tồn tại!";

        public static final String WARNING_21115_DUPLICATE_CODE = "Trường mã đã tồn tại!";
        public static final String WARNING_21113_EXIT_CODE = "Trường mã không được để trống!";
        public static final String WARNING_21114_EXIT_NAME = "Trường tên không được để trống!";
        public static final String WARNING_21116_EXIT_USERNAME = "Trường tài khoản không được để trống!";
        public static final String WARNING_21117_EXIT_PASSWORD = "Trường mật khẩu không được để trống!";
        public static final String WARNING_2122_DUPLICATE_USERNAME = "Tài khoản đã tồn tại!";


    }
    public static class MessageResource
    {
            //region common
        public static final String ACCTION_SUCCESS = "Thành công!";
        public static final String EXCEPTION_UNKNOWN = "Lỗi ngoại lệ chưa xác định!";
        public static final String ADD_SUCCESS = "Thêm mới thành công!";
        public static final String UPDATE_SUCCESS = "Sửa thông tin thành công!";
        public static final String UNAUTHORIZE = "Lỗi xác thực!";
        public static final String DELETE_SUCCESS = "Xóa thành công!";

        public static String MISS_DATA_MESSAGE = "Thông tin không đủ!";  //error_code = 210
        public static String BAD_REQUEST_MESSAGE = "Lỗi sai dữ liệu!";  //error_code = 400
        public static String NOT_FOUND_VIEW_MESSAGE = "Không tìm thấy mục cần xem!"; //error_code = 404
        public static String NOT_FOUND_UPDATE_MESSAGE = "Không tìm thấy mục cần sửa!"; //error_code = 404
        public static String NOT_FOUND_DELETE_MESSAGE = "Không tìm thấy mục cần xóa!"; //error_code = 404
        public static String ERROR_EXIST_MESSAGE = "Mục này đã tồn tại!";   //error_code = 211

        public static String ERROR_400_MESSAGE = "Có lỗi xảy ra. Xin vui lòng thử lại sau!";    //error_code = 400
        public static String ERROR_500_MESSAGE = "Hệ thống xảy ra lỗi. Xin vui lòng thử lại sau!";

        public static final String NOT_FOUND = "Không tìm thấy!";
        public static final String INVALID = "Không hợp lệ!";




            //endregion

            //region check role
        public static final String NOPERMISION_VIEW_MESSAGE = "Bạn không có quyền xem dữ liệu tới mục này!";    //error_code = 222
        public static final String NOPERMISION_UPDATE_MESSAGE = "Bạn không có quyền cập nhật mục này!";   //error_code = 222
        public static final String NOPERMISION_CREATE_MESSAGE = "Bạn không có quyền thêm mới mục này!";   //error_code = 222
        public static final String NOPERMISION_DELETE_MESSAGE = "Bạn không có quyền xoá mục này!";   //error_code = 222
        public static final String NOPERMISION_ACCEPT_MESSAGE = "Bạn không có quyền duyệt mục này!";
        public static final String NOPERMISION_ACTION_MESSAGE = "Bạn không có quyền thực hiện thao tác này!";
            //endregion

            //region "user"
        public static final String USER_NOT_FOUND = "Người dùng không tồn tại!";
            //endregion
    }

    public static class ErrorCode
    {
            //region common
        public static final String ERROR_SERVER = "ERROR_SERVER";
        public static final String ERROR_AUTHORIZED = "ERROR_AUTHORIZED";
        public static final String ERROR_VALIDATION = "ERROR_VALIDATION";
        public static final String ERROR_BADREQUEST = "ERROR_BADREQUEST";
        public static final String ERROR_BADREQUEST_SAP = "ERROR_BADREQUEST_SAP";
        public static final String ERROR_NOTFOUND = "ERROR_NOTFOUND";
        public static final String ERROR_UNKNOWN = "ERROR_UNKNOWN";
        public static final String ERROR_UNPERMISSION = "ERROR_UNPERMISSION";
        public static final String ERROR_CONNECTION = "ERROR_CONNECTION";
        public static final String ERROR_TIMEOUT = "ERROR_TIMEOUT";
        public static final String ERROR_PERMISSSION = "ERROR_PERMISSSION";
            //endregion

            //region user
        public static final String USERNAME_INVALID = "USERNAME_INVALID";
        public static final String USER_LOCKED = "USER_LOCKED";
        public static final String USER_PASSWORD_INCORRECT = "USER_PASSWORD_INCORRECT";
            //endregion


    }
}
