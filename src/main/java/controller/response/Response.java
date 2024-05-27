package controller.response;

public class Response<T> {
    private boolean resultCode;
    private T result;

    public Response(boolean resultCode, T result) {
        this.resultCode = resultCode;
        this.result = result;
    }

    public static <T> Response<T> success() {
        return new Response<T>(false, null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>(true, result);
    }

    public static <T> Response<T> error(T result) {       //에러 메시지를 출력해야하므로 String 타입으로 설정
        return new Response<>(false, null);
    }

    public boolean getResultCode() {
        return resultCode;
    }

    public T getResult() {
        return result;
    }

}
