package com.sample;

public enum Status {
    UNHANDLED("未対応"),
    PROCESSING("処理中"),
    DONE("完了"),
    UNDER_VERIFICATION("検証中");

    private final String dispVal;
    Status (String dispVal){
        this.dispVal = dispVal;
    }

}
