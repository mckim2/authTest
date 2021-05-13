package kr.co.thecheck.domainh2.domain.authLog.exception;

public class AlreayLoginException extends Exception {

    public AlreayLoginException() {
        super("이미 로그인 되어있습니다.");
    }
}
