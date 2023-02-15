package org.tlbc.worship.model;

public enum HostsEnum {
    ZHAOYI("赵颐执事"), FANGDAJIANG("方大江弟兄"), WANGHUIYU("王绘宇弟兄");

    private final String worker;
    HostsEnum(String worker) {
        this.worker = worker;
    }
    public String getWorker() {
        return this.worker;
    }
}
