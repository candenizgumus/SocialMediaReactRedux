package com.candenizgumus.java14socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType
{

    USERNAME_OR_PASSWORD_WRONG(1001, "Kullanıcı adı veya parola yanlış.", HttpStatus.I_AM_A_TEAPOT),
    USERNAME_ALREADY_TAKEN(1002, "Bu kullanıcı adı zaten alınmış. Lütfen tekrar deneyin.", HttpStatus.BAD_REQUEST),
    MUSTERI_NOT_FOUND(1004, "Böyle bir müşteri bulunamadı.", HttpStatus.NOT_FOUND),
    PASSWORDS_ARE_NOT_SAME(1005,"Sifreler ayni degil", HttpStatus.BAD_REQUEST),
    EMAIL_TAKEN(1006,"Email daha önce alınmis", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1007,"Token Geçersizdir" ,HttpStatus.BAD_REQUEST ),
    TOKEN_CREATION_FAILED(1008,"Token yaratmada hata meydana geldi" ,HttpStatus.SERVICE_UNAVAILABLE ),
    TOKEN_VERIFY_FAILED(1009,"Token verify edilemedi." ,HttpStatus.SERVICE_UNAVAILABLE ),
    TOKEN_ARGUMENT_NOTVALID(1010,"Token argümanı yanlış formatta" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_IS_NOT_ACTIVE(1011,"Hesap aktif değil." , HttpStatus.SERVICE_UNAVAILABLE),
    AUTH_NOT_FOUND(1012,"Auth bulunamadı." , HttpStatus.BAD_REQUEST),
    ACTIVATIONCODE_WRONG(1013,"Aktivasyon kodu hatalı." , HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(1001, "Girilen bilgiler eksik ya da yanlıştır.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_REPASSWORD_ERROR(1002, "Girilen şifreler uyuşmuyor.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_USERNAME_OR_PASSWORD_ERROR(1003, "Kullanıcı ad ya da şifresi hatalıdır.", HttpStatus.BAD_REQUEST),

    INTERNAL_SERVER_ERROR(9998,"Sunucuda beklenmeyen bir hata oluştu, Lütfen tekrar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER_ERROR_NOT_FOUND_DATA(9002,"Sunucu Hatası: Liste getirilemedi, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_STATUS_ERROR(1014   ,"hesap statü hatası" ,HttpStatus.BAD_REQUEST );
    private Integer code;
    private String message;
    private HttpStatus httpStatus;

}